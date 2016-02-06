package com.brunocalou.guitarstudio;

/**
 * Created by bruno on 05/02/16.
 */
public class AudioProcessorException extends Exception {
    public AudioProcessorException(String message) {
        super(message);
    }

    public AudioProcessorException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public AudioProcessorException(Throwable throwable) {
        super(throwable);
    }

    public AudioProcessorException() {
    }
}
