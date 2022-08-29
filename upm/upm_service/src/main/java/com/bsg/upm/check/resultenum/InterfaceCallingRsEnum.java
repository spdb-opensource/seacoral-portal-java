package com.bsg.upm.check.resultenum;

public enum InterfaceCallingRsEnum implements ChkRsInterface {

    /**
     * Calling mgm interface failed
     */
    CALLING_MGM_INTERFACE_FAILED(INTERFACE_CALLING + 100, "调用MGM接口异常。"),

    /**
     * Calling horus interface failed
     */
    CALLING_HORUS_INTERFACE_FAILED(INTERFACE_CALLING + 200, "调用Horus接口异常。");

    private int code;
    private String msg;

    private InterfaceCallingRsEnum(int code, String msg) {
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
