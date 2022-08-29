package com.bsg.upm.check.resultenum;

public enum BusinessSubsystemChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_ID_NOT_EXIST(BUSINESS_SUBSYSTEM, "该业务子系统(id:{})不存在。"),

    ADD_ILLEGAL_BUSINESS_SYSTEM_ID_MUST_NOT_BE_NULL(BUSINESS_SUBSYSTEM + 11011, "业务系统不能为空。"), 
    ADD_ILLEGAL_BUSINESS_SYSTEM_ID_NOT_EXIST(BUSINESS_SUBSYSTEM + 11012, "该业务系统(id:{})不存在。"),

    ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK(BUSINESS_SUBSYSTEM + 11021, "业务子系统名称不能为空。"), 
    ADD_ILLEGAL_NAME_EXIST(BUSINESS_SUBSYSTEM + 11022, "该业务子系统名称({})已存在。"),


    UPDATE_ILLEGAL_BUSINESS_SYSTEM_ID_NOT_EXIST(BUSINESS_SUBSYSTEM + 12012, "该业务系统(id:{})不存在。"),

    UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY(BUSINESS_SUBSYSTEM + 12021, "业务子系统名称不能为空。"), 
    UPDATE_ILLEGAL_NAME_EXIST(BUSINESS_SUBSYSTEM + 12022, "该业务子系统名称({})已存在。"),

    REMOVE_ILLEGAL_ASSOCIATED_WITH_ORDER_GROUP(BUSINESS_SUBSYSTEM + 99001, "该业务子系统({})已关联服务组，无法删除。");

    private int code;
    private String msg;

    private BusinessSubsystemChkRsEnum(int code, String msg) {
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
