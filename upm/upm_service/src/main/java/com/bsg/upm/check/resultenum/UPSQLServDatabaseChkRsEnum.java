package com.bsg.upm.check.resultenum;

public enum UPSQLServDatabaseChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_SERV_GROUP_ID_NOT_EXIST(UPSQL_DATABASE, "该服务组(id:{})不存在。"), 
    ILLEGAL_SERV_GROUP_TYPE_ERROR(UPSQL_DATABASE + 1, "该服务组(type:{})不支持对库进行操作。"), 
    ILLEGAL_SERV_GROUP_NOT_CREATE_SUCCESS(UPSQL_DATABASE + 2, "该服务组({})未创建成功，无法对此进行此操作。"),

    ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK(UPSQL_DATABASE + 11011, "库名不能为空。"),

    ADD_ILLEGAL_CHARACTERSET_MUST_NOT_BE_BLANK(UPSQL_DATABASE + 11021, "字符集不能为空。");

    private int code;
    private String msg;

    private UPSQLServDatabaseChkRsEnum(int code, String msg) {
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
