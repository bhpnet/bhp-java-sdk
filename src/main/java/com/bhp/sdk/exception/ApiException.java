package com.bhp.sdk.exception;

/**
 * @author Trigger
 */
public class ApiException extends RuntimeException {

    private int code;

    public ApiException(String message) {
        super(message);
    }

    public ApiException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

/*    @Override
    public String getMessage() {
        if (this.code != 0) {
            return code + ":" + super.getMessage();
        }
        return super.getMessage();
    }*/
}
