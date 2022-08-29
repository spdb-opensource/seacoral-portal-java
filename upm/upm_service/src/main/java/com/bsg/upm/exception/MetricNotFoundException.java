package com.bsg.upm.exception;

public class MetricNotFoundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public MetricNotFoundException() {

    }

    public MetricNotFoundException(String msg) {
        super(msg);
    }

    public MetricNotFoundException(Throwable e) {
        super(e);
    }
}
