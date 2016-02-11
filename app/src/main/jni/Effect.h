//
// Created by bruno on 06/02/16.
//

#ifndef GUITARSTUDIO_EFFECT_H
#define GUITARSTUDIO_EFFECT_H

#include <inttypes.h>

class Effect {
public:

    Effect(uint8_t level = 100);

    void setLevel(uint8_t level);

    uint8_t getLevel();

    void apply(int16_t *buffer, int size);

    void enable();

    void disable();

    bool isEnabled();

protected:
    virtual void doEffect(int16_t *buffer, int size) = 0;

private:
    bool is_enabled;
    float level; // 0 to 2.55
};


#endif //GUITARSTUDIO_EFFECT_H
