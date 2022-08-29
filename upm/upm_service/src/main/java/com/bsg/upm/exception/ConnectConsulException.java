package com.bsg.upm.exception;

public class ConnectConsulException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ConnectConsulException() {

    }

    public ConnectConsulException(String msg) {
        super(msg);
    }

    public ConnectConsulException(Throwable e) {
        super(e);
    }
}
