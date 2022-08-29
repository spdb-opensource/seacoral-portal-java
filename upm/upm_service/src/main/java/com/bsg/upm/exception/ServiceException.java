package com.bsg.upm.exception;

/**
 * 异常处理类
 * 
 * @author HCK
 * @date 2018年5月7日
 */
public class ServiceException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ServiceException() {

    }

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(Throwable e) {
        super(e);
    }
}
