package com.pipe.www.pipe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
/*import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;*/
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.pipe.www.pipe.model.User;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int IMAGE_CODE = 234;
    private EditText fullname;
    private EditText email;
    private EditText institution;
    private EditText password;
    private EditText confirmpassword;
    private ImageView profileimage;
    private FirebaseAuth fireAuth;
    private DatabaseReference firestore;
    private StorageReference firestorage;
    private StorageReference storageReference;
    private FirebaseUser currentUser;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fireAuth = FirebaseAuth.getInstance();
        firestore = FirebaseDatabase.getInstance().getReference().child(getString(R.string.users));
        firestorage = FirebaseStorage.getInstance().getReference().child(getString(R.string.users));


        fullname = (EditText)findViewById(R.id.fullname);
        email  = (EditText)findViewById(R.id.email);
        institution = (EditText)findViewById(R.id.institution);
        password = (EditText)findViewById(R.id.password);
        confirmpassword = (EditText)findViewById(R.id.comfirmpassword);
        profileimage = (ImageView)findViewById(R.id.profilepic);

        user = new User();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/
    }

    public void profilepic(View view) {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_CODE);



    }

    public void signup(View view) {

        Toast.makeText(this, "Please wait....", Toast.LENGTH_SHORT).show();


        // check to see if any entry is empty, if so, do not upload
        String[] textentry = new String[] {
                fullname.getText().toString(),
                email.getText().toString(),
                institution.getText().toString(),
                password.getText().toString(),
                confirmpassword.getText().toString()

        };

        boolean checker = false;

        for (String aTextentry : textentry) {
            if (aTextentry.isEmpty()) {
                checker = true;
            }
        }




        if (checker){

            Toast.makeText(this, "Please fill in the empty field", Toast.LENGTH_SHORT).show();
        }else{


            fireAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener((authResult) -> {


                currentUser = authResult.getUser();

                user.setFullname(fullname.getText().toString());
                user.setEmail(email.getText().toString());
                user.setInstitution(institution.getText().toString());
                user.setUserid(currentUser.getUid());
                user.setTimejoin(new Date().getTime());

                // add the user model to the firestore

                if(user.getProfilelink() == null){
                    Toast.makeText(this, "Please wait for picture to Upload", Toast.LENGTH_SHORT).show();
                }else{

                    firestore.child(currentUser.getUid()).setValue(user);
                    startActivity(new Intent(SignUp.this,Login.class));
                    Toast.makeText(this, "Sucessfully Signed Up", Toast.LENGTH_LONG).show();
                    finish();

                }






            } );



        }


    }

    public void show(View view) {

        password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        confirmpassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

            if(requestCode == IMAGE_CODE){

                Uri uri = data.getData();

                Glide.with(SignUp.this).load(uri).asBitmap().placeholder(R.mipmap.profileavatar).into(profileimage);

                if(uri == null){
                    return;
                }

                try{
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    if (inputStream != null) {
                        storageReference = firestorage.child(""+uri.hashCode());

                        UploadTask uploadTask =storageReference.putStream(inputStream);

                        Task<Uri> task = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                return storageReference.getDownloadUrl();
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                Toast.makeText(SignUp.this, "Upload is Successfull", Toast.LENGTH_SHORT).show();
                                user.setProfilelink(uri.toString());

                               /* Map<String,Object> updateMap = new HashMap<>();

                                updateMap.put(getString(R.string.profilelink),uri.toString());
                                firestore.child(currentUser.getUid()).updateChildren(updateMap);
*/



                            }
                        });





                    }




                }catch (Exception e){


                }





            }



        }







    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
