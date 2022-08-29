package com.bsg.upm.check.resultenum;

public enum DatabaseChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

	ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK(DATABASE + 11012, "库名不能为空。"),
	ADD_ILLEGAL_CHARACTERSET_MUST_NOT_BE_BLANK(DATABASE + 11012, "库字符集不能为空。");


    private int code;
    private String msg;

    private DatabaseChkRsEnum(int code, String msg) {
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
