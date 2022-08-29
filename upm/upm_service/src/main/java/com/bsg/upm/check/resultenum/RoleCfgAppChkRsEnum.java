package com.bsg.upm.check.resultenum;

public enum RoleCfgAppChkRsEnum implements ChkRsInterface {

	SUCCESS(OK, "OK"),

    ILLEGAL_ROLE_ID_NOT_EXIST(ROLE_CFG_APP, "Illegal argument: 'role':'(id:{})' does not exist."), 
    ILLEGAL_ROLE_CFG_EXIST(ROLE_CFG_APP + 1, "Illegal argument: 'role':'(id:{})' data scope config already exists."), 
    ILLEGAL_ROLE_CFG_NOT_EXIST(ROLE_CFG_APP + 2, "Illegal argument: 'role':'(id:{})' data scope config does not exist."),
    ILLEGAL_FORMAT(ROLE_CFG_APP + 3, "Illegal argument: format error."),
    
    ADD_ILLEGAL_APP_IDS_MUST_NOT_BE_NULL(ROLE_CFG_APP + 11011, "Illegal argument: 'appId' must not be null."), 
    ADD_ILLEGAL_APP_ID_NOT_EXIST(ROLE_CFG_APP + 11012, "Illegal argument: 'appId':'{}' does not exist."),
    
    UPDATE_ILLEGAL_APP_IDS_MUST_NOT_BE_NULL(ROLE_CFG_APP + 12011, "Illegal argument: 'appId' must not be null."),
    UPDATE_ILLEGAL_APP_ID_NOT_EXIST(ROLE_CFG_APP + 12012, "Illegal argument: 'appId':'{}' does not exist."); 
    private int code;
    private String msg;

    private RoleCfgAppChkRsEnum(int code, String msg) {
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
