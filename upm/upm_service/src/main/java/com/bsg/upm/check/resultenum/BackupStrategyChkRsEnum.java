package com.bsg.upm.check.resultenum;

public enum BackupStrategyChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

	ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK(BACKUPFILE + 11012, "备份策略名不能为空。"),
	ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK(BACKUPFILE + 11013, "服务ID不能为空。"),
	ADD_ILLEGAL_CRONEXPRESSION_MUST_NOT_BE_BLANK(BACKUPFILE + 11014, "备份周期不能为空。"),
	ADD_ILLEGAL_TYPE_MUST_NOT_BE_BLANK(BACKUPFILE + 11015, "备份类型不能为空。"),
	ADD_ILLEGAL_RETENTION_MUST_NOT_BE_BLANK(BACKUPFILE + 11016, "有效天数不能为空。");


    private int code;
    private String msg;

    private BackupStrategyChkRsEnum(int code, String msg) {
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
