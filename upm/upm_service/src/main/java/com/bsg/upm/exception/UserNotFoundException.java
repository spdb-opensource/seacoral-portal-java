package com.bsg.upm.exception;

public class UserNotFoundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {

    }

    public UserNotFoundException(String msg) {
        super(msg);
    }

    public UserNotFoundException(Throwable e) {
        super(e);
    }
}
