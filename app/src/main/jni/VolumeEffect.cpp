//
// Created by bruno on 07/02/16.
//

#include "VolumeEffect.h"

VolumeEffect::VolumeEffect(uint8_t volume) : Effect(volume) {
}

void VolumeEffect::setVolume(uint8_t volume) {
    setLevel(volume);
}

void VolumeEffect::doEffect(int16_t *buffer, int size) {
}