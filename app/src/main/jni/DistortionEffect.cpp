//
// Created by bruno on 07/02/16.
//

#include "DistortionEffect.h"

const uint16_t MAX_INT16 = 32767;

DistortionEffect::DistortionEffect(uint8_t level, uint16_t threshold) : Effect(level) {
    setThreshold(threshold);
}

void DistortionEffect::setThreshold(uint16_t threshold) {
    if (threshold > MAX_INT16 / 2) {
        // Half of the max int 16 size because the buffer is signed 16,
        // so the maximum buffer value is half the maximum int 16 size
        threshold = MAX_INT16 / 2;
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
