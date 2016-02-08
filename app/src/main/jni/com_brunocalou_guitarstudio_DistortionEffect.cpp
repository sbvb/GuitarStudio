//
// Created by bruno on 08/02/16.
//

#include "com_brunocalou_guitarstudio_DistortionEffect.h"
#include "DistortionEffect.h"
#include "AndroidLog.h"
#include <inttypes.h>

void Java_com_brunocalou_guitarstudio_DistortionEffect__1setThreshold(JNIEnv *env, jobject jobject1,
                                                                      jlong ptr, jint threshold) {

    (reinterpret_cast<DistortionEffect *>((int64_t)ptr))->setThreshold((uint16_t)threshold);
}

jlong Java_com_brunocalou_guitarstudio_DistortionEffect__1initNative(JNIEnv *env,
                                                                     jobject jobject1) {
    LOGD("Will create the distortion");
    DistortionEffect *distortion = new DistortionEffect();
    LOGD("Created the distortion");
    return (int64_t) distortion;
}