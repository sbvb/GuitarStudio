//
// Created by bruno on 07/02/16.
//

#include "DistortionEffect.h"

const uint16_t MAX_INT16 = 32767;

DistortionEffect::DistortionEffect(uint8_t level, uint16_t threshold) : Effect(level) {
    setThreshold(threshold);
}

void DistortionEffect::setThreshold(uint16_t threshold) {
    if (threshold > MAX_INT16) {
        threshold = MAX_INT16;
    }
    this->threshold = threshold;
}

void DistortionEffect::doEffect(int16_t *buffer, int size) {
    for (int i = 0; i < size; i++) {
        if (buffer[i] > threshold) {
            buffer[i] = threshold;
        } else if (buffer[i] < -threshold) {
            buffer[i] = -threshold;
        }
    }
}
