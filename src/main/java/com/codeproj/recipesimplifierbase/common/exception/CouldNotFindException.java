package com.codeproj.recipesimplifierbase.common.exception;

import org.springframework.http.HttpStatus;

public class CouldNotFindException extends BaseException {

    private static final long serialVersionUID = 1L;

    public CouldNotFindException(String s) {
        super(s);
    }

    public CouldNotFindException(String string, Throwable root) {
        super(string, root);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

}
