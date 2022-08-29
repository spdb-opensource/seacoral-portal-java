package com.bsg.upm.check.resultenum;

public enum ServerGroupUserChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

	ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK(SERVERGROUP_USER + 11012, "用户名不能为空。"),
	ADD_ILLEGAL_WHITEIP_MUST_NOT_BE_BLANK(SERVERGROUP_USER + 11012, "白名单不能为空。"),
	ADD_ILLEGAL_PASSWORD_MUST_NOT_BE_BLANK(SERVERGROUP_USER + 11012, "密码不能为空。");


    private int code;
    private String msg;

    private ServerGroupUserChkRsEnum(int code, String msg) {
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
