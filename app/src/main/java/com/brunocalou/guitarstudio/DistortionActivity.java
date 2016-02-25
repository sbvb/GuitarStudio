package com.brunocalou.guitarstudio;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.SeekBar;

/**
 * Created by bruno on 24/02/16.
 */
public class DistortionActivity extends Activity {

    private DistortionEffect distortion;
    private SeekBar gain;
    private SeekBar level;
    Intent audio_processor_service_intent;
    AudioProcessorService audio_processor_service;
    private final String LOG_TAG = "distortion_activity";
    private int max_threshold = 200;
    private int min_threshold = 30;
    private boolean is_bound = false;

    public void setDistortion(DistortionEffect distortion) {
        this.distortion = distortion;
        setupComponents();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
        if (is_bound) {
            unbindService(service_connection);
            is_bound = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate");
        setContentView(R.layout.activity_distortion);
        gain = (SeekBar) findViewById(R.id.distortion_gain_seek_bar);
        level = (SeekBar) findViewById(R.id.distortion_level_seek_bar);

        audio_processor_service_intent = new Intent(this, AudioProcessorService.class);
        this.bindService(audio_processor_service_intent, service_connection, Context.BIND_AUTO_CREATE);
    }

    private void setupComponents() {
        if (distortion != null) {
            gain.setMax(max_threshold);
            level.setMax(distortion.getMaxLevel());

            Log.v(LOG_TAG, "Max level = " + distortion.getMaxLevel());

            int threshold = distortion.getThreshold();
            if (threshold < min_threshold) {
                threshold = min_threshold;
                distortion.setThreshold(min_threshold);
            } else if (threshold > max_threshold) {
                threshold = max_threshold + min_threshold;
                distortion.setThreshold(max_threshold);
            }

            gain.setProgress(max_threshold - threshold + min_threshold);
            level.setProgress(distortion.getLevel());

            Log.v(LOG_TAG, "Setting gain to " + threshold);
            Log.v(LOG_TAG, "Setting level to " + distortion.getLevel());

            gain.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (distortion != null) {
                        int threshold = max_threshold - progress + min_threshold;
                        distortion.setThreshold(threshold);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            level.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if (distortion != null) {
                        distortion.setLevel(progress);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }
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

            EffectList effects = audio_processor_service.getEffectList();

            for (int i = 0; i < effects.size(); i += 1) {
                if (effects.get(i).name == getString(R.string.DISTORTION_EFFECT_NAME)) {
                    distortion = (DistortionEffect) effects.get(i).getEffect();
                    break;
                }
            }
            setupComponents();
        }
    };
}
