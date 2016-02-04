LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_LDLIBS += -llog

LOCAL_MODULE := AudioProcessor
LOCAL_SRC_FILES := AudioProcessor.cpp
include $(BUILD_SHARED_LIBRARY)

