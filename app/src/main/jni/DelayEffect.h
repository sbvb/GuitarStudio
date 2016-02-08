//
// Created by bruno on 07/02/16.
//

#ifndef GUITARSTUDIO_DELAYEFFECT_H
#define GUITARSTUDIO_DELAYEFFECT_H

#include "Effect.h"

class DelayEffect : public Effect {
public:
    DelayEffect(uint8_t level = 100);

protected:
    void doEffect(int16_t *buffer, int size);

};


#endif //GUITARSTUDIO_DELAYEFFECT_H

