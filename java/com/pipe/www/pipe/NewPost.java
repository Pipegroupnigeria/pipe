package com.pipe.www.pipe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.pipe.www.pipe.model.Post;
import com.pipe.www.pipe.model.User;

import java.io.InputStream;
import java.util.Date;

public class NewPost extends AppCompatActivity {

    private static final int IMAGE_CODE = 123;
    private AppBarConfiguration mAppBarConfiguration;
    private EditText posttext;
    private RelativeLayout postimagelay;
    private ImageView postimage;
    private EditText postcapture;
    private StorageReference poststorage;
    private DatabaseReference postbase;
    private FirebaseUser currentUser;

    private final String TEXT = getString(R.string.text);
    private final String IMAGE = getString(R.string.image);
    private DatabaseReference userbase;
    private User user;
    private StorageReference storageReference;
    private Uri imageUri;

    private enum PostChoice{TEXT, IMAGE}
    PostChoice postChoice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initilize the views
        posttext = (EditText)findViewById(R.id.posttext);
        postimagelay = (RelativeLayout)findViewById(R.id.postimagelay);
        postimage = (ImageView)findViewById(R.id.postimage);
        postcapture = (EditText)findViewById(R.id.postcapture);

        // current state of the post is a text
        postChoice = PostChoice.TEXT;



        // initialize the firebase instances

        postbase = FirebaseDatabase.getInstance().getReference().child(getString(R.string.post));
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        poststorage = FirebaseStorage.getInstance().getReference().child(getString(R.string.post));


        userbase = FirebaseDatabase.getInstance().getReference().child(getString(R.string.users)).child(currentUser.getUid());


        userbase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                user = (User)dataSnapshot.getValue();




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

       /* NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_post, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void post(View view) {





        if(postChoice == PostChoice.TEXT){

            // post the text post here and send it to the server
            postText();

        }else if(postChoice == PostChoice.IMAGE){

            // post the image with capture here and send it to the server
            postImage();

        }



    }

    public void image(View view) {

        postimagelay.setVisibility(View.VISIBLE);
        posttext.setVisibility(View.GONE);

        // set the post state to Image
        postChoice = PostChoice.IMAGE;

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_CODE);

    }

    public void write(View view) {

        posttext.setVisibility(View.VISIBLE);
        postimagelay.setVisibility(View.GONE);

        // set the post state to Text
        postChoice = PostChoice.TEXT;
    }

    public void back(View view) {
        onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && data != null){

            if(requestCode == IMAGE_CODE){

                 imageUri = data.getData();

                Glide.with(NewPost.this).load(imageUri).asBitmap().placeholder(R.mipmap.profileavatar).into(postimage);


            }



        }


    }

    private void postText(){

        Post post = new Post();
        post.setType(TEXT);
        post.setTextmessage(posttext.getText().toString());
        post.setUserid(currentUser.getUid());
        post.setUsername(user.getFullname());
        post.setDate(new Date().getTime());
        post.setUserphotourl(user.getProfilelink());

        DatabaseReference ref = postbase.push();
        post.setPostid(ref.getKey());

        ref.setValue(post).addOnSuccessListener(aVoid -> Toast.makeText(this, "Post is Successfully Uploaded", Toast.LENGTH_LONG).show());






    }

    private void postImage(){

        if(imageUri == null){
            return;
        }


        Post post = new Post();
        post.setType(IMAGE);
        post.setCapture(postcapture.getText().toString());
        post.setUserid(currentUser.getUid());
        post.setUsername(user.getFullname());
        post.setDate(new Date().getTime());
        post.setUserphotourl(user.getProfilelink());

        try{
            InputStream inputStream = getContentResolver().openInputStream(imageUri);
            if (inputStream != null) {
                storageReference = poststorage.child(""+imageUri.hashCode());

                UploadTask uploadTask =storageReference.putStream(inputStream);

                Task<Uri> task = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        return storageReference.getDownloadUrl();
                    }
                }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        Toast.makeText(NewPost.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        post.setImageurl(uri.toString());

                        DatabaseReference ref = postbase.push();
                        post.setPostid(ref.getKey());

                        ref.setValue(post).addOnSuccessListener((aVoid) -> Toast.makeText(NewPost.this, "Post is Successfully Uploaded", Toast.LENGTH_LONG).show());



                               /* Map<String,Object> updateMap = new HashMap<>();

                                updateMap.put(getString(R.string.profilelink),uri.toString());
                                firestore.child(currentUser.getUid()).updateChildren(updateMap);
*/



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(NewPost.this, "Failed to Upload Post", Toast.LENGTH_LONG).show();
                    }
                });





            }




        }catch (Exception e){

            Toast.makeText(this, "Posting of Image Unsuccessful", Toast.LENGTH_SHORT).show();


        }


    }
}
