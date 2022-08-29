package com.bsg.upm.check.resultenum;

public enum PortChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_ID_NOT_EXIST(PORT, "该端口(id:{})不存在。"),

    ADD_ILLEGAL_SITE_ID_MUST_NOT_BE_NULL(PORT + 11011, "站点不能为空。"), 
    ADD_ILLEGAL_SITE_ID_NOT_EXIST(PORT + 11012, "该站点(id:{})不存在。"),

    ADD_ILLEGAL_START_MUST_NOT_BE_NULL(PORT + 11021, "起始端口不能为空。"), 

    ADD_ILLEGAL_END_MUST_NOT_BE_NULL(PORT + 11031, "结束端口不能为空。"), 
    ADD_ILLEGAL_PORT_EXIST(PORT + 11032, "端口已存在。"),

    REMOVE_ILLEGAL_ASSOCIATED_WITH_SERVER(PORT + 99001, "该端口({})已被服务组({})占用，无法删除。");

    private int code;
    private String msg;

    private PortChkRsEnum(int code, String msg) {
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
