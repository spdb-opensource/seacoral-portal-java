package com.bsg.upm.check.resultenum;

public enum ServParameterCfgChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_SERV_GROUP_ID_NOT_EXIST(SERV_PARAMETER_CONFIG, "该服务组(id:{})不存在。"), 
    ILLEGAL_SERV_GROUP_NOT_CREATE_SUCCESS(SERV_PARAMETER_CONFIG + 1, "该服务组({})未创建成功，无法对此进行此操作。"),
    
    ILLEGAL_SERV_GROUP_PARAM_EDIT_KEY_NOT_BE_BLANK(SERV_PARAMETER_CONFIG + 3, "KEY不能为空"), 
    ILLEGAL_SERV_GROUP_PARAM_EDIT_VALUE_NOT_BE_BLANK(SERV_PARAMETER_CONFIG + 4, "当前值不能为空"), 
    ILLEGAL_SERV_GROUP_NOT_MATCH(SERV_PARAMETER_CONFIG + 2, "参数错误，该服务组与类型不匹配");

    private int code;
    private String msg;

    private ServParameterCfgChkRsEnum(int code, String msg) {
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
