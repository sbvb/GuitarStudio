package com.brunocalou.guitarstudio;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
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

import java.io.FileDescriptor;
import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    MediaRecorder recorder;
    MediaPlayer player;
    ParcelFileDescriptor[] audio;
    FileDescriptor input_audio;
    FileDescriptor output_audio;
    String audio_file;
    String LOG_KEY = "audio_debug";

    public MainActivity() {
        recorder = null;
        player = null;
        Log.d(LOG_KEY, "Constructor");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_KEY, "onDestroy");
        cleanUpMediaPlayer();
        cleanUpMediaRecorder();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_KEY, "onStop");
        cleanUpMediaPlayer();
        cleanUpMediaRecorder();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_KEY, "onPause");
        cleanUpMediaPlayer();
        cleanUpMediaRecorder();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_KEY, "onResume");
        cleanUpMediaPlayer();
        setUpMediaPlayer();

        cleanUpMediaRecorder();
        setUpMediaRecorder();
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


        try {
            audio = ParcelFileDescriptor.createPipe();
            input_audio = audio[0].getFileDescriptor();
            output_audio = audio[1].getFileDescriptor();
        } catch (IOException e) {
            e.printStackTrace();
        }

        audio_file =  Environment.getExternalStorageDirectory().getAbsolutePath();
        audio_file += "/audiorecordtest.3gp";

        setUpMediaRecorder();
        setUpMediaPlayer();
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

    public void setUpMediaRecorder() {
        Log.d(LOG_KEY, "setUpMediaRecorder");
//        if (recorder == null) {
//            recorder = new MediaRecorder();
//            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
////            recorder.setOutputFile(input_audio);
//            recorder.setOutputFile(audio_file);
//            try {
//                recorder.prepare();
//                recorder.start();   // Recording is now started
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
////        recorder.stop();
////        recorder.reset();   // You can reuse the object by going back to setAudioSource() step
////        recorder.release(); // Now the object cannot be reused
    }

    public void setUpMediaPlayer() {
        Log.d(LOG_KEY, "setUpMediaPlayer");
        if (player == null) {
            player = new MediaPlayer();
            try {
                player.setDataSource(audio_file);
                player.prepare();
                player.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void cleanUpMediaRecorder() {
        Log.d(LOG_KEY, "cleanUpMediaRecorder");
//        if (recorder != null) {
//            try {
//                recorder.stop();
//                recorder.reset();
//                recorder.release();
//            } catch (IllegalStateException e) {
//                e.printStackTrace();
//            }
//            recorder = null;
//        }
    }

    public void cleanUpMediaPlayer() {
        Log.d(LOG_KEY, "cleanUpMediaPlayer");
        if (player != null) {
            try {
                player.stop();
                player.reset();
                player.release();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            player = null;
        }
    }
}
