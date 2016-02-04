//
// Created by bruno on 04/02/16.
//

#include "com_brunocalou_guitarstudio_AudioProcessor.h"
#include <iostream>
#include <android/log.h>

#define LOG_PRIORITY ANDROID_LOG_DEBUG
#define LOG_TAG "AudioProcessor"

/*
 * Class:     com_brunocalou_guitarstudio_AudioProcessor
 * Method:    start
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_brunocalou_guitarstudio_AudioProcessor_start (JNIEnv *env, jobject) {
    __android_log_write(LOG_PRIORITY, LOG_TAG, "Started");
}

/*
 * Class:     com_brunocalou_guitarstudio_AudioProcessor
 * Method:    stop
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_brunocalou_guitarstudio_AudioProcessor_stop (JNIEnv *env, jobject) {
    __android_log_write(LOG_PRIORITY, LOG_TAG, "Stopped");
}