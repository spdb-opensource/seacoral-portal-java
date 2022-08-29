package com.bsg.upm.check.resultenum;

public enum UserChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_USERNAME_NOT_EXIST(USER, "Illegal argument: user (username:{}) does not exist."),

    ADD_ILLEGAL_USERNAME_MUST_NOT_BE_BLANK(USER + 11011, "Illegal argument: 'username' must not be blank."), 
    ADD_ILLEGAL_USERNAME_EXIST(USER + 11012, "Illegal argument: 'username':'{}' already exists."),

    ADD_ILLEGAL_AUTHTYPE_MUST_NOT_BE_BLANK(USER + 11021, "Illegal argument: 'authType' must not be blank."), 
    ADD_ILLEGAL_AUTHTYPE_FORMAT_ERROR(USER + 11022, "Illegal argument: 'authType':'{}' format error."),

    ADD_ILLEGAL_PASSWORD_MUST_NOT_BE_BLANK(USER + 11031, "Illegal argument: 'password' must not be blank."),

    ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK(USER + 11041, "Illegal argument: 'name' must not be blank."),

    ADD_ILLEGAL_TELEPHONE_MUST_NOT_BE_BLANK(USER + 11051, "Illegal argument: 'telephone' must not be blank."), 
    ADD_ILLEGAL_TELEPHONE_EXIST(USER + 11052, "Illegal argument: 'telephone':'{}' already exists."),

    ADD_ILLEGAL_EMAIL_MUST_NOT_BE_BLANK(USER + 11061, "Illegal argument: 'email' must not be blank."), 
    ADD_ILLEGAL_EMAIL_EXIST(USER + 11062, "Illegal argument: 'email':'{}' already exists."),

    ADD_ILLEGAL_COMPANY_MUST_NOT_BE_BLANK(USER + 11071, "Illegal argument: 'company' must not be blank."),

    ADD_ILLEGAL_EMER_CONTACT_MUST_NOT_BE_BLANK(USER + 11081, "Illegal argument: 'emerContact' must not be blank."),

    ADD_ILLEGAL_EMER_TEL_MUST_NOT_BE_BLANK(USER + 11091, "Illegal argument: 'emerTel' must not be blank."),

    ADD_ILLEGAL_ROLE_ID_MUST_NOT_BE_NULL(USER + 11101, "Illegal argument: 'roleId' must not be null."), 
    ADD_ILLEGAL_ROLE_ID_NOT_EXIST(USER + 11102, "Illegal argument: 'roleId':'{}' does not exist."),

    ADD_ILLEGAL_GROUP_IDS_MUST_NOT_BE_NULL(USER + 11111, "Illegal argument: 'groupIds' must not be null."), 
    ADD_ILLEGAL_GROUP_ID_NOT_EXIST(USER + 11112, "Illegal argument: 'groupId':'{}' does not exist."),

    UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY(USER + 12041, "Illegal argument: 'name' must not be empty."),

    UPDATE_ILLEGAL_TELEPHONE_MUST_NOT_BE_EMPTY(USER + 12051, "Illegal argument: 'telephone' must not be empty."), 
    UPDATE_ILLEGAL_TELEPHONE_EXIST(USER + 12052, "Illegal argument: 'telephone':'{}' already exists."),

    UPDATE_ILLEGAL_EMAIL_MUST_NOT_BE_EMPTY(USER + 12061, "Illegal argument: 'email' must not be empty."), 
    UPDATE_ILLEGAL_EMAIL_EXIST(USER + 12062, "Illegal argument: 'email':'{}' already exists."),

    UPDATE_ILLEGAL_COMPANY_MUST_NOT_BE_EMPTY(USER + 12071, "Illegal argument: 'company' must not be empty."),

    UPDATE_ILLEGAL_EMER_CONTACT_MUST_NOT_BE_EMPTY(USER + 12081, "Illegal argument: 'emerContact' must not be empty."),

    UPDATE_ILLEGAL_EMER_TEL_MUST_NOT_BE_EMPTY(USER + 12091, "Illegal argument: 'emerTel' must not be empty."),

    UPDATE_ILLEGAL_ROLE_ID_NOT_EXIST(USER + 12102, "Illegal argument: 'roleId':'{}' does not exist."),

    UPDATE_ILLEGAL_GROUP_ID_NOT_EXIST(USER + 12112, "Illegal argument: 'groupId':'{}' does not exist."),

    PWD_ILLEGAL_FORBID_UPDATE(USER + 13001, "Illegal operate: password is not allowed to update."),

    PWD_ILLEGAL_ORIGINAL_PWD_MUST_NOT_BE_BLANK(USER + 13011, "Illegal argument: 'originalPwd' must not be blank."), 
    PWD_ILLEGAL_ORIGINAL_PWD_ERROR(USER + 13012, "Illegal argument: 'originalPwd' error."),

    PWD_ILLEGAL_NEW_PWD_MUST_NOT_BE_BLANK(USER + 13021, "Illegal argument: 'newPwd' must not be blank."), 
    PWD_ILLEGAL_NEW_PWD_NOT_SAME_WITH_NEW_PWD(USER + 13022, "Illegal argument: 'originalPwd' and 'newPwd' can't be the same.");

    private int code;
    private String msg;

    private UserChkRsEnum(int code, String msg) {
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
