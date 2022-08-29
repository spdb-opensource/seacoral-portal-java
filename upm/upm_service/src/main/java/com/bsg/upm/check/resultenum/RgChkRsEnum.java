package com.bsg.upm.check.resultenum;

public enum RgChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ADD_ILLEGAL_SAN_ID_MUST_NOT_BE_NULL(RG + 11011, "Illegal argument: 'sanId' must not be null."), 
    ADD_ILLEGAL_SAN_ID_NOT_EXIST(RG + 11012, "Illegal argument: 'sanId':'{}' does not exist."),

    ADD_ILLEGAL_CODE_MUST_NOT_BE_BLANK(RG + 11021, "Illegal argument: 'code' must not be blank."),
    
    ADD_ILLEGAL_TYPE_MUST_NOT_BE_BLANK(RG + 11021, "Illegal argument: 'type' must not be blank."),

    ENABLED_ILLEGAL_SAN_ID_NOT_EXIST(RG + 15001, "Illegal argument: 'sanId':'{}' does not exist."),

    DISABLED_ILLEGAL_SAN_ID_NOT_EXIST(RG + 16001, "Illegal argument: 'sanId':'{}' does not exist."),

    REMOVE_ILLEGAL_SAN_ID_NOT_EXIST(RG + 99001, "Illegal argument: 'sanId':'{}' does not exist."),
    
	REMOVE_ILLEGAL_ASSOCIATED_WITH_ENABLED(RG + 99002, "该存储({})已启用，无法删除。");

    private int code;
    private String msg;

    private RgChkRsEnum(int code, String msg) {
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
