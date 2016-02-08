//
// Created by bruno on 08/02/16.
//

#include "com_brunocalou_guitarstudio_Effect.h"
#include "Effect.h"
#include <inttypes.h>

void Java_com_brunocalou_guitarstudio_Effect__1destroyNative(JNIEnv *env, jobject jobject1,
                                                             jlong ptr) {
    if (ptr != 0) {
        delete reinterpret_cast<Effect *>((int64_t)ptr);
    }
}

void Java_com_brunocalou_guitarstudio_Effect__1enable(JNIEnv *env, jobject jobject1,
                                                      jlong ptr) {
    (reinterpret_cast<Effect *>((int64_t)ptr))->enable();
}

void Java_com_brunocalou_guitarstudio_Effect__1disable(JNIEnv *env, jobject jobject1,
                                                       jlong ptr) {
    (reinterpret_cast<Effect *>((int64_t)ptr))->disable();
}

void Java_com_brunocalou_guitarstudio_Effect__1setLevel(JNIEnv *env, jobject jobject1,
                                                        jlong ptr, jint level) {
    (reinterpret_cast<Effect *>((int64_t)ptr))->setLevel(level);
}

