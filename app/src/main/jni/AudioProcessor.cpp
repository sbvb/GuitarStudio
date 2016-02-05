//
// Created by bruno on 04/02/16.
//

#include <iostream>
#include "com_brunocalou_guitarstudio_AudioProcessor.h"
#include "AndroidLog.h"

/*
 * Class:     com_brunocalou_guitarstudio_AudioProcessor
 * Method:    init
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_brunocalou_guitarstudio_AudioProcessor_init(JNIEnv *, jobject) {
#ifdef LOG_ENABLED
    LOGD("Init");
#endif
}

/*
 * Class:     com_brunocalou_guitarstudio_AudioProcessor
 * Method:    start
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_brunocalou_guitarstudio_AudioProcessor_start(JNIEnv *env, jobject) {
#ifdef LOG_ENABLED
    LOGD("Start");
#endif
}

/*
 * Class:     com_brunocalou_guitarstudio_AudioProcessor
 * Method:    stop
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_brunocalou_guitarstudio_AudioProcessor_stop(JNIEnv *env, jobject) {
#ifdef LOG_ENABLED
    LOGD("Stop");
#endif
}

/*
 * Class:     com_brunocalou_guitarstudio_AudioProcessor
 * Method:    finish
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_brunocalou_guitarstudio_AudioProcessor_finish(JNIEnv *, jobject) {
    //Release the resources
#ifdef LOG_ENABLED
    LOGD("Finish");
#endif
}