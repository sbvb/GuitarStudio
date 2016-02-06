package com.brunocalou.guitarstudio;

/**
 * Created by bruno on 04/02/16.
 */
public class AudioProcessor {
    static {
        System.loadLibrary("AudioProcessor");
    }

    public native boolean startProcess();

    public native void stopProcess();
}
