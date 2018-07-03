package com.codeproj.recipesimplifierbase.common.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BaseException(String s) {
        super(s);
    }

    public BaseException(String string, Throwable root) {
        super(string, root);
    }

    public abstract HttpStatus getHttpStatus();
}