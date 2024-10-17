package com.learning.java.lld.flipkartgymbooking.exception;

public class ValidationException extends RuntimeException {

    private int httpStatus;
    private int errorCode;
    private String errorMessage;
    public ValidationException(int httpStatus, int code, String errorMessage) {
        super(errorMessage);
        this.httpStatus = httpStatus;
        this.errorCode = code;
        this.errorMessage = errorMessage;
    }
}
