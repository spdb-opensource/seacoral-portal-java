package com.bsg.upm.check.resultenum;

public enum SiteConfigChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ADD_ILLEGAL_SITE_CONFIG_EXIST(SITE_CONFIG + 11011, "该站点({})配置已经存在。"), 
    ADD_ILLEGAL_SITE_ID_NOT_EXIST(SITE_CONFIG + 11012, "该站点(id:{})不存在。"),

    ADD_ILLEGAL_ALARM_ON_MUST_NOT_BE_NULL(SITE_CONFIG + 11021, "告警开关不能为空。"),

    ADD_ILLEGAL_ALARM_ADDR_MUST_NOT_BE_BLANK(SITE_CONFIG + 11031, "告警地址不能为空。"),

    ADD_ILLEGAL_MAIL_ON_MUST_NOT_BE_NULL(SITE_CONFIG + 11041, "邮件开关不能为空。"),

    ADD_ILLEGAL_MAIL_HOST_MUST_NOT_BE_BLANK(SITE_CONFIG + 11051, "邮件服务器不能为空。"),

    ADD_ILLEGAL_MAIL_PROTOCOL_MUST_NOT_BE_BLANK(SITE_CONFIG + 11061, "邮件协议不能为空。"),

    ADD_ILLEGAL_MAIL_PORT_MUST_NOT_BE_NULL(SITE_CONFIG + 11071, "邮件端口不能为空。"),

    ADD_ILLEGAL_MAIL_USERNAME_MUST_NOT_BE_BLANK(SITE_CONFIG + 11081, "用户名不能为空。"),

    ADD_ILLEGAL_MAIL_PASSWORD_MUST_NOT_BE_BLANK(SITE_CONFIG + 11091, "密码不能为空。"),


    UPDATE_ILLEGAL_SITE_CONFIG_NOT_EXIST(SITE_CONFIG + 12011, "该站点({})配置不已经存在。"), 
    UPDATE_ILLEGAL_SITE_ID_NOT_EXIST(SITE_CONFIG + 12012, "该站点(id:{})不存在。"),

    UPDATE_ILLEGAL_ALARM_ADDR_MUST_NOT_BE_EMPTY(SITE_CONFIG + 12031, "告警地址不能为空。"),

    UPDATE_ILLEGAL_MAIL_HOST_MUST_NOT_BE_EMPTY(SITE_CONFIG + 12051, "邮件服务器不能为空。"),

    UPDATE_ILLEGAL_MAIL_PROTOCOL_MUST_NOT_BE_EMPTY(SITE_CONFIG + 12061, "邮件协议不能为空。"),

    UPDATE_ILLEGAL_MAIL_USERNAME_MUST_NOT_BE_EMPTY(SITE_CONFIG + 12081, "用户名不能为空。"),

    UPDATE_ILLEGAL_MAIL_PASSWORD_MUST_NOT_BE_EMPTY(SITE_CONFIG + 12091, "密码不能为空。"),


    REMOVE_ILLEGAL_SITE_ID_NOT_EXIST(SITE + 99001, "该站点(id:{})不存在。");

    private int code;
    private String msg;

    private SiteConfigChkRsEnum(int code, String msg) {
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
