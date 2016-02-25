package com.brunocalou.guitarstudio;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
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
import android.view.WindowManager;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String LOG_KEY = "main_activity";
    EffectList effects = new EffectList();
    EffectListAdapter adapter;
    Intent audio_processor_service_intent;
    AudioProcessorService audio_processor_service;
    private boolean is_bound = false;

    public MainActivity() {
        Log.d(LOG_KEY, "Constructor");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_KEY, "onDestroy");
        if (is_bound) {
            this.unbindService(service_connection);
            stopService(audio_processor_service_intent);
            is_bound = false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_KEY, "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_KEY, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_KEY, "onResume");
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

        audio_processor_service_intent = new Intent(this, AudioProcessorService.class);
        this.startService(audio_processor_service_intent);
        this.bindService(audio_processor_service_intent, service_connection, Context.BIND_AUTO_CREATE);

        adapter = new EffectListAdapter(this, effects);
        ((ListView) findViewById(R.id.effectListView)).setAdapter(adapter);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
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

    private ServiceConnection service_connection = new ServiceConnection() {
        private static final String LOG_TAG = "service_connection";

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.v(LOG_TAG, "Service disconnected");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.v(LOG_TAG, "Service connected");
            AudioProcessorService.MyBinder binder = (AudioProcessorService.MyBinder) service;
            audio_processor_service = binder.getService();
            is_bound = true;

            adapter.setEffectList(audio_processor_service.getEffectList());
        }
    };
}
