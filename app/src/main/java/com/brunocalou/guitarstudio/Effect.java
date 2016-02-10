package com.brunocalou.guitarstudio;

import android.util.Log;

import java.math.BigInteger;

/**
 * Created by bruno on 08/02/16.
 */
public abstract class Effect {
    static {
        System.loadLibrary("AudioProcessor");
    }

    protected long nativePtr;

    public abstract void reload();

    @Override
    protected void finalize() throws Throwable {
        _destroyNative(nativePtr);
        nativePtr = 0;
    }

    public void destroy() {
        _destroyNative(nativePtr);
        nativePtr = 0;
    }

    public long getNativePtr() {
        Log.d("Effect", "Native pointer = " + nativePtr);
        return nativePtr;
    }

    public void enable (){
        _enable(nativePtr);
    }

    public void disable() {
        _disable(nativePtr);
    }

    public void setLevel(int level) {
        int max_8 = 255;
        if (level > max_8) {
            level = max_8;
        }
        _setLevel(nativePtr, level);
    }

    //Native methods

    private native long _initNative();

    private native void _destroyNative(long ptr);

    private native void _enable(long ptr);

    private native void _disable(long ptr);

    private native void _setLevel(long ptr, int level);
}
