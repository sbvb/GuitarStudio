LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_C_INCLUDES := $(LOCAL_PATH)
LOCAL_CFLAGS := -O3
LOCAL_CPPFLAGS :=$(LOCAL_CFLAGS)

LOCAL_LDLIBS += -llog -lOpenSLES

LOCAL_MODULE := AudioProcessor
LOCAL_SRC_FILES := AudioProcessor.cpp opensl_io.c

include $(BUILD_SHARED_LIBRARY)

