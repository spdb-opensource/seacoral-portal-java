package com.bsg.upm.check.resultenum;

public enum BusinessSystemChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_ID_NOT_EXIST(BUSINESS_SYSTEM, "该业务系统(id:{})不存在。"),

    ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK(BUSINESS_SYSTEM + 11011, "业务系统名称不能为空。"), 
    ADD_ILLEGAL_NAME_EXIST(BUSINESS_SYSTEM + 11012, "该业务系统名称({})已存在。"),


    UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY(BUSINESS_SYSTEM + 11011, "业务系统名称不能为空。"), 
    UPDATE_ILLEGAL_NAME_EXIST(BUSINESS_SYSTEM + 11012, "该业务系统名称({})已存在。"),

    REMOVE_ILLEGAL_ASSOCIATED_WITH_BUSINESS_SUBSYSTEM(BUSINESS_SYSTEM + 99001, "该业务系统({})已关联业务子系统，无法删除。");

    private int code;
    private String msg;

    private BusinessSystemChkRsEnum(int code, String msg) {
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
