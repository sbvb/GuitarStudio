//
// Created by bruno on 04/02/16.
//

#include "com_brunocalou_guitarstudio_AudioProcessor.h"
#include "AndroidLog.h"
#include "EffectChain.h"
#include "DistortionEffect.h"
#include "VolumeEffect.h"
#include "DelayEffect.h"
#include "opensl_io.h"
#include <chrono>
#include <inttypes.h>

#define LOG_PERFORMANCE (true && LOG_ENABLED)
#define BUFFERFRAMES 1024
#define VECSAMPS_MONO 64
#define VECSAMPS_STEREO 128
#define SR 44100

const float CONV16BIT = 32767;

#ifdef LOG_PERFORMANCE
std::chrono::system_clock::time_point current_time;
std::chrono::system_clock::time_point last_time;
float loop_counter = 0;
float max_loop_counter = 1000;
#endif

static bool running;

DistortionEffect distortion(100, 10000);
DelayEffect delay;
VolumeEffect volume(255);

EffectChain effect_chain;

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
    int16_t processed_buffer[VECSAMPS_MONO];
    p = android_OpenAudioDevice(SR, 1, 2, BUFFERFRAMES);
    if (p == NULL) return false;
    running = true;

    //Temporary
//    effect_chain.remove(&distortion);
//    effect_chain.remove(&delay);
//    effect_chain.remove(&volume);
//    effect_chain.add(&distortion);
//    effect_chain.add(&delay);
//    effect_chain.add(&volume);

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
        //Convert the audio in buffer
        for (i = 0; i < samps; i++) {
            processed_buffer[i] = inbuffer[i] * CONV16BIT;
        }
        //Apply all the effects on the effect chain
        effect_chain.apply(processed_buffer, samps);

        //Write the processed audio to the out buffer
        for (i = 0, j = 0; i < samps; i++, j += 2)
            outbuffer[j] = outbuffer[j + 1] = processed_buffer[i] / CONV16BIT;
        android_AudioOut(p, outbuffer, samps * 2);
    }
    android_CloseAudioDevice(p);
    return true;
}

JNIEXPORT void JNICALL Java_com_brunocalou_guitarstudio_AudioProcessor_stopProcess(JNIEnv *env,
                                                                                   jobject) {
#ifdef LOG_ENABLED
    LOGD("Stop");
#endif
    running = false;
}

void Java_com_brunocalou_guitarstudio_AudioProcessor__1addEffect(JNIEnv *env, jobject jobject1,
                                                                 jlong effect_ptr) {
    effect_chain.add(reinterpret_cast<Effect *>((int64_t)effect_ptr));
}

void Java_com_brunocalou_guitarstudio_AudioProcessor__1removeEffect(JNIEnv *env, jobject jobject1,
                                                                    jlong effect_ptr) {
    effect_chain.remove(reinterpret_cast<Effect *>((int64_t)effect_ptr));
}

void Java_com_brunocalou_guitarstudio_AudioProcessor__1clearEffects(JNIEnv *env, jobject jobject1) {
    effect_chain.clear();
}