package com.bsg.upm.check.resultenum;

public enum ServBackupStrategyChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_SERV_GROUP_ID_NOT_EXIST(SERV_BACKUP_STRATEGY, "该服务组(id:{})不存在。"), 
    ILLEGAL_SERV_GROUP_TYPE_ERROR(SERV_BACKUP_STRATEGY + 1,  "该服务组(type:{})不支持备份。"), 
    ILLEGAL_ID_NOT_EXIST(SERV_BACKUP_STRATEGY + 2, "该备份策略(id:{})不存在。"), 

    ADD_ILLEGAL_SERV_GROUP_NOT_CREATE_SUCCESS(SERV_BACKUP_STRATEGY + 11001, "该服务组({})未创建成功，无法新增备份策略。"),
    
    ADD_ILLEGAL_TYPE_MUST_NOT_BE_BLANK(SERV_BACKUP_STRATEGY + 11011, "备份类型不能为空。"), 
    ADD_ILLEGAL_TYPE_NOT_EXIST(SERV_BACKUP_STRATEGY + 11012, "该备份类型(code:{})不存在。"),

    ADD_ILLEGAL_TABLES_MUST_NOT_BE_BLANK(SERV_BACKUP_STRATEGY + 11021, "导表备份时，必须选择库表。"),

    ADD_ILLEGAL_CRON_EXPRESSION_MUST_NOT_BE_BLANK(SERV_BACKUP_STRATEGY + 11031, "备份周期不能为空。"),


    UPDATE_ILLEGAL_ENABLED(SERV_BACKUP_STRATEGY + 12001, "该备份策略已启用，无法编辑"),

    UPDATE_ILLEGAL_TYPE_MUST_NOT_BE_BLANK(SERV_BACKUP_STRATEGY + 12011, "备份类型不能为空。"), 
    UPDATE_ILLEGAL_TYPE_NOT_EXIST(SERV_BACKUP_STRATEGY + 12012, "该备份类型(code:{})不存在。"),

    UPDATE_ILLEGAL_TABLES_MUST_NOT_BE_BLANK(SERV_BACKUP_STRATEGY + 12021, "导表备份时，必须选择表。"),

    UPDATE_ILLEGAL_CRON_EXPRESSION_MUST_NOT_BE_EMPTY(SERV_BACKUP_STRATEGY + 12031, "备份周期不能为空。");

    private int code;
    private String msg;

    private ServBackupStrategyChkRsEnum(int code, String msg) {
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
