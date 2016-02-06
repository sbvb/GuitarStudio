//
// Created by bruno on 04/02/16.
//

#include "com_brunocalou_guitarstudio_AudioProcessor.h"
#include "AndroidLog.h"
#include "opensl_io.h"
#include <time.h>
#include <chrono>

#define LOG_PERFORMANCE (true && LOG_ENABLED)
#define BUFFERFRAMES 1024
#define VECSAMPS_MONO 64
#define VECSAMPS_STEREO 128
#define SR 44100

#ifdef LOG_PERFORMANCE
std::chrono::system_clock::time_point current_time;
std::chrono::system_clock::time_point last_time;
float loop_counter = 0;
float max_loop_counter = 1000;
#endif

static bool running;

/*
 * Class:     com_brunocalou_guitarstudio_AudioProcessor
 * Method:    start
 * Signature: ()V
 */
JNIEXPORT jboolean JNICALL Java_com_brunocalou_guitarstudio_AudioProcessor_startProcess(JNIEnv *env,
                                                                                        jobject) {
#ifdef LOG_ENABLED
    LOGD("Start");
#endif
#ifdef LOG_PERFORMANCE
    current_time = std::chrono::system_clock::now();
    last_time = std::chrono::system_clock::now();
    loop_counter = 0;
#endif
    OPENSL_STREAM *p;
    int samps, i, j;
    float inbuffer[VECSAMPS_MONO], outbuffer[VECSAMPS_STEREO];
    p = android_OpenAudioDevice(SR, 1, 2, BUFFERFRAMES);
    if (p == NULL) return false;
    running = true;
    while (running) {
#ifdef LOG_PERFORMANCE
        loop_counter++;
        if (loop_counter >= max_loop_counter) {
            current_time = std::chrono::system_clock::now();
            auto diff = (std::chrono::duration_cast<std::chrono::milliseconds>(
                    current_time.time_since_epoch() - last_time.time_since_epoch())).count();
            LOGD("%f ms", diff / loop_counter);
            last_time = std::chrono::system_clock::now();
            loop_counter = 0;
        }
#endif
        samps = android_AudioIn(p, inbuffer, VECSAMPS_MONO);
        for (i = 0, j = 0; i < samps; i++, j += 2)
            outbuffer[j] = outbuffer[j + 1] = inbuffer[i];
        android_AudioOut(p, outbuffer, samps * 2);
    }
    android_CloseAudioDevice(p);
    return true;
}

/*
 * Class:     com_brunocalou_guitarstudio_AudioProcessor
 * Method:    stop
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_brunocalou_guitarstudio_AudioProcessor_stopProcess(JNIEnv *env,
                                                                                   jobject) {
#ifdef LOG_ENABLED
    LOGD("Stop");
#endif
    running = false;
}
