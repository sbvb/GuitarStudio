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
        audio_processor.stopProcess();
    }

    public void onError(AudioProcessorException e) {};
}
