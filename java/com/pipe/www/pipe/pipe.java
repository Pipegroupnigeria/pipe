package com.pipe.www.pipe;

import android.Manifest;
import android.animation.Animator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.github.ybq.android.spinkit.style.ThreeBounce;
import com.pipe.www.pipe.model.SharedPref;

public class pipe extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView powered;
    private ImageView threebounce;
    //private TextView beperfect;
    private ConstraintLayout constraint;
    private int shortAnimatee;
    private SharedPref firstLaunchShared;
    // private TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firstLaunchShared = new SharedPref(this, getString(R.string.firstLaunch));






        // request for permission from user
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO},10);
            // ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},10);

        }



        constraint = (ConstraintLayout) findViewById(R.id.pipe);
        //beperfect = (TextView)findViewById(R.id.beperfect);
        //welcome = (TextView)findViewById(R.id.welcome);
        ThreeBounce threeBounceStyle = new ThreeBounce();
        threebounce = (ImageView) findViewById(R.id.imagethreebounce);


        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(threebounce);
        Glide.with(this).load(R.drawable.dotloading).into(imageViewTarget);


        //threebounce.setIndeterminateDrawable(threeBounceStyle);
        powered = (TextView)findViewById(R.id.powered);
        shortAnimatee = 2000;//getResources().getInteger(android.R.integer.config_longAnimTime);


        constraintAnimate();





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

    private void constraintAnimate() {
        constraint.setAlpha(0f);
        constraint.setVisibility(View.VISIBLE);

        constraint.animate().alpha(1f).setDuration(shortAnimatee)
                .setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                /*beperfectAnimate();*/
                poweredAnimate();

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

   /* private void beperfectAnimate() {
        beperfect.setAlpha(0f);
        beperfect.setVisibility(View.VISIBLE);

        beperfect.animate().alpha(1f).setDuration(shortAnimatee).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                poweredAnimate();

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
*/
    private void poweredAnimate() {
        powered.setAlpha(0f);
        powered.setVisibility(View.VISIBLE);

        powered.animate().alpha(1f).setDuration(shortAnimatee).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                threebounceAnimate();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {




                        if(firstLaunchShared.getInt() == 0){

                            startActivity(new Intent(pipe.this, SlideShow.class));
                            //powered.setVisibility(View.GONE);
                            //threebounce.setVisibility(View.GONE);
                            finish();


                        }else {

                            startActivity(new Intent(pipe.this, Welcome.class));
                            //powered.setVisibility(View.GONE);
                            //threebounce.setVisibility(View.GONE);
                            finish();
                        }


                        //powereddisapper();
                        /*beperfectdisapper();*/



                    }
                }, 4000);




            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /*private void beperfectdisapper() {
        beperfect.animate().alpha(0f).setDuration(shortAnimatee).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                welcomeAnimateNext();



            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
*/

    private void powereddisapper() {
        powered.animate().alpha(0f).setDuration(shortAnimatee).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                startActivity(new Intent(pipe.this,Welcome.class));
                //powered.setVisibility(View.GONE);
                //threebounce.setVisibility(View.GONE);
                finish();


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /*private void welcomeAnimateNext() {
       // beperfect.setText("welcome");

        welcome.setAlpha(0f);
        welcome.setVisibility(View.VISIBLE);

        welcome.animate().alpha(1f).setDuration(3000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        threebounce.setVisibility(View.GONE);
                        startActivity(new Intent(pipe.this,Welcome.class));
                        finish();


                    }
                }, 2000);



            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
*/
    private void threebounceAnimate() {
        threebounce.setAlpha(0f);
        threebounce.setVisibility(View.VISIBLE);

        threebounce.animate().alpha(1f).setDuration(shortAnimatee).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
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
        getMenuInflater().inflate(R.menu.pipe, menu);
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
