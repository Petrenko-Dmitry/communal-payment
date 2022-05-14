package com.example.communalPayment.exception;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Error {
    private String message;
    private int code;

    public Error() {
    }

    public Error(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
