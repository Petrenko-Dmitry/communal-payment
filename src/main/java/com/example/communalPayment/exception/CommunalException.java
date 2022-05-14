package com.example.communalPayment.exception;

public class CommunalException extends RuntimeException {

    public CommunalException() {
        super();
    }
    public CommunalException(String message) {
        super(message);
    }
    public CommunalException(String message, Throwable cause) {
        super(message, cause);
    }
}
