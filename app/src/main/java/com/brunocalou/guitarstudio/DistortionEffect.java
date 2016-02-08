package com.brunocalou.guitarstudio;

/**
 * Created by bruno on 08/02/16.
 */
public class DistortionEffect extends Effect {

    DistortionEffect() {
        nativePtr = _initNative();
    }
    public void setThreshold(int threshold) {
        int max_16 = 32767;

        if (threshold < 0) {
            threshold = 0;
        } else if (threshold > max_16) {
            threshold = max_16;
        }
        _setThreshold(getNativePtr(), threshold);
    }

    public native void _setThreshold(long ptr, int threshold);

    private native long _initNative();
}
