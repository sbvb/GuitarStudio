package com.brunocalou.guitarstudio;

import android.util.Log;

import java.math.BigInteger;

/**
 * Created by bruno on 04/02/16.
 */
public class AudioProcessor {
    static {
        System.loadLibrary("AudioProcessor");
    }

    public void addEffect(Effect effect) {
        Log.d("AudioProcessor", "Will add the effect");
        _addEffect(effect.getNativePtr());
        Log.d("AudioProcessor", "Added the effect");
    }

    public void removeEffect(Effect effect) {
        _removeEffect(effect.getNativePtr());
    }

    public void clearEffects() {
        _clearEffects();
    }

    public native boolean startProcess();

    public native void stopProcess();

    private native void _addEffect(long ptr);

    private native void _removeEffect(long ptr);

    private native void _clearEffects();
}
