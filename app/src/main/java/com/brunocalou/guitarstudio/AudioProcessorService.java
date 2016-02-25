package com.brunocalou.guitarstudio;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by bruno on 24/02/16.
 */
public class AudioProcessorService extends Service {

    private static final String LOG_TAG = "Service";
    private IBinder m_binder = new MyBinder();
    AudioThread audio_thread;
    DistortionEffect distortion = null;
    EffectList effects = new EffectList();

    private void createEffects() {
        if (distortion == null) {
            distortion = new DistortionEffect();
            distortion.setThreshold(200);
            distortion.setLevel((byte) 200);
            effects.add(new EffectListItem(getString(R.string.DISTORTION_EFFECT_NAME), distortion, DistortionActivity.class));
        }
    }

    private void reloadEffects() {
        Log.d(LOG_TAG, "Reload effects");
        if (distortion != null) {
            distortion.reload();
        }
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
                    Log.e(LOG_TAG, "Audio thread error");
                }
            };
            reloadEffects();
            addEffects();
            audio_thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void destroyEffects() {
        distortion.destroy();
        distortion = null;
        effects.clear();
    }

    private void stopAudioThread() {
        if (audio_thread != null) {
            audio_thread.finish();
            audio_thread = null;
        }
    }

    public EffectList getEffectList() {
        return effects;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.v(LOG_TAG, "On bind");
        return m_binder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.v(LOG_TAG, "On Rebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(LOG_TAG, "in onUnbind");
        return true;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "Service created");
        createEffects();
        startAudioThread();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "Service started");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "Service destroyed");
        destroyEffects();
        stopAudioThread();
    }

    public class MyBinder extends Binder {
        AudioProcessorService getService () {
            return AudioProcessorService.this;
        }
    }
}
