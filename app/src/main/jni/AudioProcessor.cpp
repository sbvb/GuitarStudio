//
// Created by bruno on 04/02/16.
//

#include "com_brunocalou_guitarstudio_AudioProcessor.h"
#include "AndroidLog.h"
#include "opensl_io.h"
#define BUFFERFRAMES 1024
#define VECSAMPS_MONO 64
#define VECSAMPS_STEREO 128
#define SR 44100

static bool running;
/*
 * Class:     com_brunocalou_guitarstudio_AudioProcessor
 * Method:    start
 * Signature: ()V
 */
JNIEXPORT jboolean JNICALL Java_com_brunocalou_guitarstudio_AudioProcessor_startProcess(JNIEnv *env, jobject) {
#ifdef LOG_ENABLED
    LOGD("Start");
#endif
    OPENSL_STREAM  *p;
    int samps, i, j;
    float  inbuffer[VECSAMPS_MONO], outbuffer[VECSAMPS_STEREO];
    p = android_OpenAudioDevice(SR,1,2,BUFFERFRAMES);
    if(p == NULL) return false;
    running = true;
    while(running) {
        samps = android_AudioIn(p,inbuffer,VECSAMPS_MONO);
        for(i = 0, j=0; i < samps; i++, j+=2)
            outbuffer[j] = outbuffer[j+1] = inbuffer[i];
        android_AudioOut(p,outbuffer,samps*2);
    }
    android_CloseAudioDevice(p);
    return true;
}

/*
 * Class:     com_brunocalou_guitarstudio_AudioProcessor
 * Method:    stop
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_brunocalou_guitarstudio_AudioProcessor_stopProcess(JNIEnv *env, jobject) {
#ifdef LOG_ENABLED
    LOGD("Stop");
#endif
    running = false;
}
