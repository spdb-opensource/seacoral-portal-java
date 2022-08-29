package com.bsg.upm.check.resultenum;

public enum ConsulChkRsEnum implements ChkRsInterface {

    /**
     * Connect to consul failed
     */
    CONNECT_FAILED(CONSUL + 1, "consul地址 {}连接异常。"),
    
    /**
     * service address could not be found on consul
     */
    SERVICE_NOT_FOUND(CONSUL + 2, "consul{}上找不到状态为passing的{}服务。");

    private int code;
    private String msg;

    private ConsulChkRsEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
