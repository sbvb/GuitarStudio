//
// Created by bruno on 07/02/16.
//

#ifndef GUITARSTUDIO_DISTORTIONEFFECT_H
#define GUITARSTUDIO_DISTORTIONEFFECT_H

#include "Effect.h"
#include <inttypes.h>

class DistortionEffect : public Effect {
public:
    DistortionEffect(uint8_t level = 100, uint16_t threshold = 32767);

    void setThreshold(uint16_t threshold);

protected:
    void doEffect(int16_t *buffer, int size);

private:
    uint16_t threshold;
};


#endif //GUITARSTUDIO_DISTORTIONEFFECT_H
