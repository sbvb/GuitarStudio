//
// Created by bruno on 07/02/16.
//

#include "DistortionEffect.h"
#include "AndroidLog.h"
#include <math.h>

const uint16_t MAX_INT16 = 32767;

DistortionEffect::DistortionEffect(uint8_t level, uint16_t threshold) : Effect(level) {
    setThreshold(threshold);
    input_multiplier = 1;
}

void DistortionEffect::setThreshold(uint16_t threshold) {
    LOGD("Set threshold to %i", threshold);
    if (threshold > MAX_INT16 / 2) {
        // Half of the max int 16 size because the buffer is signed 16,
        // so the maximum buffer value is half the maximum int 16 size
        threshold = MAX_INT16 / 2;
    }
    this->threshold = threshold;

    if (threshold < 100) {
        float pot = ((100.0 - threshold) / 100.0) * 5;
        input_multiplier = exp (pot);
        LOGD("Pot = %f", pot);
    } else {
        input_multiplier = 1;
    }
    LOGD("Set input multiplier to %f", input_multiplier);
}

void DistortionEffect::doEffect(int16_t *buffer, int size) {
    for (int i = 0; i < size; i++) {
        buffer[i] *= input_multiplier;
        if (buffer[i] > threshold) {
            buffer[i] = threshold;
        } else if (buffer[i] < -threshold) {
            buffer[i] = -threshold;
        }
    }
}
