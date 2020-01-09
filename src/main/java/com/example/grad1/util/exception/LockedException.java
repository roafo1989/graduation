package com.example.grad1.util.exception;

import org.springframework.http.HttpStatus;

public class LockedException extends ApplicationException {
    public static final String LOCKED_EXCEPTION = "Resource is locked";

    public LockedException(String message) {
        super(LOCKED_EXCEPTION, HttpStatus.LOCKED, message);
    }
}
