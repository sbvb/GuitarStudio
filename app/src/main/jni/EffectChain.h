//
// Created by bruno on 06/02/16.
//

#ifndef GUITARSTUDIO_EFFECTCHAIN_H
#define GUITARSTUDIO_EFFECTCHAIN_H

#include "Effect.h"
#include <list>

class EffectChain : public Effect {
public:
    EffectChain();

    void add(Effect *effect);

    void remove(Effect *effect);

    void clear();

protected:
    void doEffect(int16_t *buffer, int size);

private:
    std::list<Effect *> chain;
};


#endif //GUITARSTUDIO_EFFECTCHAIN_H
