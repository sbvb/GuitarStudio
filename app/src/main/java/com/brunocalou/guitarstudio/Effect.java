package com.brunocalou.guitarstudio;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by bruno on 08/02/16.
 */
public abstract class Effect implements Serializable {
    static {
        System.loadLibrary("AudioProcessor");
    }

    protected long nativePtr;

    public void reload() {
        if (isEnabled()) {
            enable();
        } else {
            disable();
        }
    }

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

    public void enable() {
        Log.d("Effect", "Enabled effect");
        _enable(nativePtr);
    }

    public void disable() {
        Log.d("Effect", "Disabled effect");
        _disable(nativePtr);
    }

    public boolean isEnabled() {
        return _isEnabled(nativePtr);
    }

    public void setLevel(int level) {
        int max_8 = 255;
        if (level > max_8) {
            level = max_8;
        } else if (level < 0) {
            level = 0;
        }
        Log.d("Effect", "Set level to " + level);
        _setLevel(nativePtr, level);
    }

    public int getMaxLevel() {
        return 255;
    }

    public int getLevel() {
        return _getLevel(nativePtr);
    }

    //Native methods

    private native long _initNative();

    private native void _destroyNative(long ptr);

    private native void _enable(long ptr);

    private native void _disable(long ptr);

    private native boolean _isEnabled(long ptr);

    private native void _setLevel(long ptr, int level);

    private native int _getLevel(long ptr);
}
