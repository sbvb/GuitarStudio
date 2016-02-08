LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_C_INCLUDES := $(LOCAL_PATH)
LOCAL_CFLAGS := -O3
LOCAL_CPPFLAGS :=$(LOCAL_CFLAGS)

LOCAL_LDLIBS += -llog -lOpenSLES

LOCAL_MODULE := AudioProcessor
LOCAL_SRC_FILES := AudioProcessor.cpp \
opensl_io.c \
Effect.cpp \
EffectChain.cpp \
DistortionEffect.cpp \
VolumeEffect.cpp \
DelayEffect.cpp \
com_brunocalou_guitarstudio_Effect.cpp \
com_brunocalou_guitarstudio_DistortionEffect.cpp \

include $(BUILD_SHARED_LIBRARY)

