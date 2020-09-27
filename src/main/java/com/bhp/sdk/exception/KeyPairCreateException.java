package com.bhp.sdk.exception;

/**
 * @author Trigger
 */
public class KeyPairCreateException extends RuntimeException {

    public KeyPairCreateException() {
    }

    public KeyPairCreateException(String message) {
        super(message);
    }


    public KeyPairCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public KeyPairCreateException(Throwable cause) {
        super(cause);
    }

    public KeyPairCreateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
