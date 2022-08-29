package com.bsg.upm.exception;

public class ServiceNotFoundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ServiceNotFoundException() {

    }

    public ServiceNotFoundException(String msg) {
        super(msg);
    }

    public ServiceNotFoundException(Throwable e) {
        super(e);
    }
}
