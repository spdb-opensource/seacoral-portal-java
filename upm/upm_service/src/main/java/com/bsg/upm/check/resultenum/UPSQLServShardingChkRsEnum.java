package com.bsg.upm.check.resultenum;

public enum UPSQLServShardingChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_SERV_GROUP_ID_NOT_EXIST(UPSQL_SHARDING_STRATEGY, "该服务组(id:{})不存在。"), 
    ILLEGAL_SERV_GROUP_TYPE_ERROR(UPSQL_SHARDING_STRATEGY + 1, "该服务组(type:{})不支持分库策略。"), 
    ILLEGAL_SERV_GROUP_NOT_CREATE_SUCCESS(UPSQL_SHARDING_STRATEGY + 2, "该服务组({})未创建成功，无法对此进行此操作。"),
    
    ILLEGAL_CONTENT_MUST_NOT_BE_BLANK(UPSQL_SHARDING_STRATEGY + 11011, "分库策略内容不能为空。");

    private int code;
    private String msg;

    private UPSQLServShardingChkRsEnum(int code, String msg) {
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
