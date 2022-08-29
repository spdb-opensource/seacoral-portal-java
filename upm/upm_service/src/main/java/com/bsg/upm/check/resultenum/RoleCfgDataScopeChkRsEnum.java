package com.bsg.upm.check.resultenum;

public enum RoleCfgDataScopeChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_ROLE_ID_NOT_EXIST(ROLE_CFG_DATA_SCOPE, "该角色(id:{})不存在。"), 
    ILLEGAL_ROLE_CFG_EXIST(ROLE_CFG_DATA_SCOPE + 1, "该角色({})数据范围配置已存在。"), 
    ILLEGAL_ROLE_CFG_NOT_EXIST(ROLE_CFG_DATA_SCOPE + 2, "该角色({})数据范围配置不存在。"), 

    ADD_ILLEGAL_ORDER_GROUP_MUST_NOT_BE_BLANK(ROLE_CFG_DATA_SCOPE + 11011, "工单组可见范围不能为空。"),
    ADD_ILLEGAL_ORDER_GROUP_NOT_EXIST(ROLE_CFG_DATA_SCOPE + 11012, "该工单组可见范围(code:{})不存在。"),

    ADD_ILLEGAL_SERV_GROUP_MUST_NOT_BE_BLANK(ROLE_CFG_DATA_SCOPE + 11021, "服务组可见范围不能为空。"),
    ADD_ILLEGAL_SERV_GROUP_NOT_EXIST(ROLE_CFG_DATA_SCOPE + 11022, "该服务组可见范围(code:{})不存在。"),

    ADD_ILLEGAL_ALARM_MUST_NOT_BE_BLANK(ROLE_CFG_DATA_SCOPE + 11031, "告警可见范围不能为空。"),
    ADD_ILLEGAL_ALARM_NOT_EXIST(ROLE_CFG_DATA_SCOPE + 11032, "该告警可见范围(code:{})不存在。"),

    ADD_ILLEGAL_OPERATE_LOG_MUST_NOT_BE_BLANK(ROLE_CFG_DATA_SCOPE + 11041, "操作日志可见范围不能为空。"),
    ADD_ILLEGAL_OPERATE_LOG_NOT_EXIST(ROLE_CFG_DATA_SCOPE + 11042, "该操作日志可见范围(code:{})不存在。"),

    UPDATE_ILLEGAL(ROLE_CFG_DATA_SCOPE + 12001, "该角色({})为系统资源，禁止修改配置。"), 

    UPDATE_ILLEGAL_ORDER_GROUP_NOT_EXIST(ROLE_CFG_DATA_SCOPE + 12012,  "该工单组可见范围(code:{})不存在。"),

    UPDATE_ILLEGAL_SERV_GROUP_NOT_EXIST(ROLE_CFG_DATA_SCOPE + 12022, "该服务组可见范围(code:{})不存在。"),

    UPDATE_ILLEGAL_ALARM_NOT_EXIST(ROLE_CFG_DATA_SCOPE + 12032, "该告警可见范围(code:{})不存在。"),

    UPDATE_ILLEGAL_OPERATE_LOG_NOT_EXIST(ROLE_CFG_DATA_SCOPE + 12042, "该操作日志可见范围(code:{})不存在。");

    private int code;
    private String msg;

    private RoleCfgDataScopeChkRsEnum(int code, String msg) {
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
