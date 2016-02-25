//
// Created by bruno on 06/02/16.
//

#include "Effect.h"
#include "AndroidLog.h"

const int32_t MAX_INT16 = 32767;
const int32_t MIN_INT16 = -32768;

Effect::Effect(uint8_t level) {
    enable();
    setLevel(level);
}

void Effect::setLevel(uint8_t level) {
    this->level = level / 100.0f;
    LOGD("set effect level to %i", level);
}

uint8_t Effect::getLevel() {
    LOGD("effect level = %i", (uint8_t) (this->level * 100));
    return (uint8_t) (this->level * 100);
}

void Effect::enable() {
    this->is_enabled = true;
}

void Effect::disable() {
    this->is_enabled = false;
}

bool Effect::isEnabled() {
    return this->is_enabled;
}

void Effect::apply(int16_t *buffer, int size) {
    if (this->is_enabled) {
        doEffect(buffer, size);
        if (level != 1) {
            for (int i = 0; i < size; i++) {
                if (level != 0) {
                    if (buffer[i] > 0) {
                        if (buffer[i] > MAX_INT16 / level) {
                            //Overflow
                            buffer[i] = MAX_INT16;
                            continue;
                        }
                    } else {
                        if (buffer[i] < MIN_INT16 / level) {
                            //Overflow
                            buffer[i] = MIN_INT16;
                            continue;
                        }
                    }
                }
                buffer[i] *= level;
            }
        }
    }
}