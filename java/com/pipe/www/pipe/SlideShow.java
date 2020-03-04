package com.pipe.www.pipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.pipe.www.pipe.adapter.SlidepagerAdapter;
import com.pipe.www.pipe.model.SharedPref;
import com.pipe.www.pipe.model.SlideModel;

import java.util.ArrayList;

public class SlideShow extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager slidepager;
    private TextView skip;
    private TextView finish;
    private ImageView firstp;
    private ImageView secondp;
    private ImageView thirdp;
    private ArrayList<SlideModel> slidemodels;
    private SlidepagerAdapter slideAdapter;
    private ImageView[] imageArray;
    private SharedPref firstLaunchShared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firstLaunchShared = new SharedPref(this, getString(R.string.firstLaunch)); // to check for first time launch


        // initialization of the views
        slidepager = (ViewPager) findViewById(R.id.slidepager);
        skip = (TextView)findViewById(R.id.skip);
        finish = (TextView)findViewById(R.id.finish);
        finish.setVisibility(View.GONE);
        firstp = (ImageView)findViewById(R.id.firstp);
        secondp = (ImageView)findViewById(R.id.secondp);
        thirdp = (ImageView)findViewById(R.id.thridp);


        imageArray = new ImageView[]{firstp, secondp, thirdp};


        slidemodels = new ArrayList<SlideModel>();

        slidemodels.add(new SlideModel(R.mipmap.pipedance,"Connect with other Students",
                "Connect perfectly with students from other campauses and share ideas or even the latest news on the block"));
        slidemodels.add(new SlideModel(R.mipmap.pipecollab,"Plan with Pipe","Your perfect dissertation cannot come to life without proper planning. Let Pipe be your plan buddy"));
        slidemodels.add(new SlideModel(R.mipmap.pipemeet,"Get unlimited Resources","Let's get you perfectly eqipped with resources on different subject areas by best minds on the planet"));

        slideAdapter = new SlidepagerAdapter(this,slidemodels);
        slidepager.setAdapter(slideAdapter);



        // the virepager on pagechange listener
        slidepager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


                // chenge the dot picture
                changeImage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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


    public void changeImage(int p){


        switch(p){

            case 0:
                imageArray[0].setImageResource(R.drawable.circle);
                imageArray[1].setImageResource(R.drawable.circlegrey);
                imageArray[2].setImageResource(R.drawable.circlegrey);
                break;
            case 1:
                imageArray[0].setImageResource(R.drawable.circlegrey);
                imageArray[1].setImageResource(R.drawable.circle);
                imageArray[2].setImageResource(R.drawable.circlegrey);
                break;
            case 2:
                imageArray[0].setImageResource(R.drawable.circlegrey);
                imageArray[1].setImageResource(R.drawable.circlegrey);
                imageArray[2].setImageResource(R.drawable.circle);
                finish.setVisibility(View.VISIBLE);
                break;
            default:
                imageArray[0].setImageResource(R.drawable.circle);
                imageArray[1].setImageResource(R.drawable.circlegrey);
                imageArray[2].setImageResource(R.drawable.circlegrey);

        }


    }

    public void next(View view){

        firstLaunchShared.setInt(1);
        startActivity(new Intent(this, Welcome.class));
        finish();

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
        getMenuInflater().inflate(R.menu.slide_show, menu);
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
