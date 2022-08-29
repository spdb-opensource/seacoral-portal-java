package com.bsg.upm.check.resultenum;

public enum GroupChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_ID_NOT_EXIST(GROUP, "该组别(id:{})不存在。"),

    ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK(GROUP + 11011, "组别名称不能为空。"),  
    ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK(GROUP + 11013, "组别ID不能为空。"),  
    ADD_ILLEGAL_NAME_EXIST(GROUP + 11012, "该组别名称({})已存在。"),


    UPDATE_ILLEGAL(GROUP + 12001, "该组别({})为系统资源，禁止编辑。"), 
    UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY(GROUP + 12011, "组别名称不能为空。"), 
    UPDATE_ILLEGAL_NAME_EXIST(GROUP + 12012, "该组别名称({})已存在。"),

    REMOVE_ILLEGAL(GROUP + 99001, "该组别({})为系统资源，禁止删除。"), 
    REMOVE_ILLEGAL_ASSOCIATED_WITH_USER(GROUP + 99002, "该组别({})已关联用户，无法删除。");

    private int code;
    private String msg;

    private GroupChkRsEnum(int code, String msg) {
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
