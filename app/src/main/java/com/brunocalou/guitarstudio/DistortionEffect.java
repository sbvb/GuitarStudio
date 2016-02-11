package com.brunocalou.guitarstudio;

/**
 * Created by bruno on 08/02/16.
 */
public class DistortionEffect extends Effect {

    private int threshold;

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
        this.threshold = threshold;
        _setThreshold(getNativePtr(), threshold);
    }

    @Override
    public void reload() {
        super.reload();
        if (getNativePtr() == 0) {
            nativePtr = _initNative();
            setThreshold(threshold);
        }
    }

    public native void _setThreshold(long ptr, int threshold);

    private native long _initNative();
}
