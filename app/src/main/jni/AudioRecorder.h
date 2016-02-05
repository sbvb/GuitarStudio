//
// Created by bruno on 05/02/16.
//

#ifndef GUITARSTUDIO_AUDIORECORDER_H
#define GUITARSTUDIO_AUDIORECORDER_H

#include <SLES/OpenSLES.h>
#include <SLES/OpenSLES_Android.h>

class AudioRecorder {
public:
    AudioRecorder();
    ~AudioRecorder();
    void start();
    void stop();

private:

};


#endif //GUITARSTUDIO_AUDIORECORDER_H
