package com.brunocalou.guitarstudio;

import android.util.Log;

/**
 * Created by bruno on 05/02/16.
 */
public class AudioThread extends Thread {
    protected AudioProcessor audio_processor = new AudioProcessor();

    public void run() {
        setPriority(Thread.MAX_PRIORITY);
        if (!audio_processor.startProcess()) onError(new AudioProcessorException("Could not start the audio processor"));
    }

    public void finish() {
        Log.d("AudioThread", "Finished audio thread");
        clearEffects();
        audio_processor.stopProcess();
    }

    public void onError(AudioProcessorException e) {};

    public void addEffect(Effect effect) {
        Log.d("AudioThread", "Will add the effect");
        audio_processor.addEffect(effect);
        Log.d("AudioThread", "Added the effect");
    }

    public void removeEffect(Effect effect) {
        audio_processor.removeEffect(effect);
    }

    public void clearEffects() {
        audio_processor.clearEffects();
    }
}
