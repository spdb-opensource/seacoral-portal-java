package com.bsg.upm.check.resultenum;

public enum LoginChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    INVALID(LOGIN + 1, "无效的用户名和密码。"),
    
    ILLEGAL_USERNAME_DISABLED(LOGIN + 1, "该用户({})已被停用。"),

    ILLEGAL_USERNAME_MUST_NOT_BE_BLANK(LOGIN + 11011, "用户名不能为空。"), 

    ILLEGAL_PASSWORD_MUST_NOT_BE_BLANK(LOGIN + 11021, "密码不能为空。");

    
    private int code;
    private String msg;

    private LoginChkRsEnum(int code, String msg) {
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
