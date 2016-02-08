//
// Created by bruno on 06/02/16.
//

#include "EffectChain.h"
#include "AndroidLog.h"

EffectChain::EffectChain() : chain(std::list<Effect *>()) {

}

void EffectChain::add(Effect *effect) {
    LOGD("Added the effect to the effect chain");
    chain.push_back(effect);
}

void EffectChain::remove(Effect *effect) {
    LOGD("Removed the effect from the effect chain");
    chain.remove(effect);
}

void EffectChain::clear() {
    chain.clear();
}

void EffectChain::doEffect(int16_t *buffer, int size) {
    //Applies all the effects
    for (std::list<Effect *>::const_iterator iterator = chain.begin(), end = chain.end();
         iterator != end; iterator++) {
        (*iterator)->apply(buffer, size);
    }
}