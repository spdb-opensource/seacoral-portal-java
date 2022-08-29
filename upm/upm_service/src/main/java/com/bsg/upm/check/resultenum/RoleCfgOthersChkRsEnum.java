package com.bsg.upm.check.resultenum;

public enum RoleCfgOthersChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_ROLE_ID_NOT_EXIST(ROLE_CFG_OTHERS, "该角色(id:{})不存在。"), 
    ILLEGAL_ROLE_CFG_EXIST(ROLE_CFG_OTHERS + 1, "该角色({})其他配置已存在。"), 
    ILLEGAL_ROLE_CFG_NOT_EXIST(ROLE_CFG_OTHERS + 2, "该角色({})其他配置不存在。"), 

    ADD_ILLEGAL_ORDER_AUTO_AUDIT_MUST_NOT_BE_NULL(ROLE_CFG_OTHERS + 11011, "工单组自动审批不能为空。"),
    ADD_ILLEGAL_ORDER_AUTO_EXECUTE_MUST_NOT_BE_NULL(ROLE_CFG_OTHERS + 11012, "工单组自动执行不能为空。"),
    
    UPDATE_ILLEGAL(ROLE_CFG_OTHERS + 12001, "该角色({})为系统资源，禁止修改配置。");

    private int code;
    private String msg;

    private RoleCfgOthersChkRsEnum(int code, String msg) {
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
