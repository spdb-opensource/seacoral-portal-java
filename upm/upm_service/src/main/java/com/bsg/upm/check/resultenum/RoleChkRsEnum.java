package com.bsg.upm.check.resultenum;

public enum RoleChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_ID_NOT_EXIST(ROLE, "该角色(id:{})不存在。"),

    ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK(ROLE + 11011, "角色名称不能为空。"), 
    ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK(ROLE + 11013, "角色ID不能为空。"), 
    ADD_ILLEGAL_NAME_EXIST(ROLE + 11012, "该角色名称({})已存在。"),


    UPDATE_ILLEGAL(ROLE + 12001, "该角色({})为系统资源，禁止编辑。"),
    UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY(ROLE + 12011, "角色名称不能为空。"), 
    UPDATE_ILLEGAL_NAME_EXIST(ROLE + 12012, "该角色名称({})已存在。"),

    REMOVE_ILLEGAL(ROLE + 99001, "该角色({})为系统资源，禁止删除。"),
    REMOVE_ILLEGAL_ASSOCIATED_WITH_USER(ROLE + 99001, "该角色({})已关联用户，禁止删除。");

    private int code;
    private String msg;

    private RoleChkRsEnum(int code, String msg) {
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
