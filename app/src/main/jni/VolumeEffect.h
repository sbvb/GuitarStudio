//
// Created by bruno on 07/02/16.
//

#ifndef GUITARSTUDIO_VOLUMEEFFECT_H
#define GUITARSTUDIO_VOLUMEEFFECT_H

#include "Effect.h"

class VolumeEffect : public Effect {
public:
    VolumeEffect(uint8_t volume = 100);
    void setVolume(uint8_t volume); // 0 to 100

protected:
    void doEffect(int16_t *buffer, int size);
};


#endif //GUITARSTUDIO_VOLUMEEFFECT_H

