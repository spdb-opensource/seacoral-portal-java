package com.bsg.upm.check.resultenum;

public enum BackupFileChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    REMOVE_ILLEGAL_ID_MUST_NOT_BE_BLANK(BACKUPFILE + 11011, "备份文件ID不能为空。"),
	REMOVE_ILLEGAL_StrategyID_MUST_NOT_BE_BLANK(BACKUPFILE + 11011, "备份策略ID不能为空。"); 

    private int code;
    private String msg;

    private BackupFileChkRsEnum(int code, String msg) {
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
