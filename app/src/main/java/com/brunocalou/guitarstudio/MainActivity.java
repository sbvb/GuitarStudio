package com.brunocalou.guitarstudio;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String LOG_KEY = "main_activity";
    AudioThread audio_thread;
    DistortionEffect distortion;

    public MainActivity() {
        Log.d(LOG_KEY, "Constructor");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_KEY, "onDestroy");
        destroyEffects();
        stopAudioThread();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_KEY, "onStop");
        stopAudioThread();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_KEY, "onPause");
        stopAudioThread();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_KEY, "onResume");
        startAudioThread();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_KEY, "onCreate");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        startAudioThread();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_camara) {
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

    private void stopAudioThread() {
        if (audio_thread != null) {
            audio_thread.clearEffects();
            audio_thread.finish();
            audio_thread = null;
        }
    }

    private void destroyEffects() {
        distortion.destroy();
        distortion = null;
    }

    private void createEffects() {
        distortion = new DistortionEffect();
        distortion.setThreshold(200);
        distortion.setLevel((byte)200);
    }

    private void addEffects() {
        audio_thread.addEffect(distortion);
    }

    private void startAudioThread() {
        try {
            audio_thread = new AudioThread() {
                @Override
                public void onError(AudioProcessorException e) {
                    e.printStackTrace();
                    Log.e(LOG_KEY, "Audio thread error");
                }
            };
            createEffects();
            addEffects();
            audio_thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
