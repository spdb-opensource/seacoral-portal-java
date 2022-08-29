package com.bsg.upm.check.resultenum;

public enum SanChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_ID_NOT_EXIST(SAN, "Illegal argument: san (id:{}) does not exist."),

    ADD_ILLEGAL_SITE_ID_MUST_NOT_BE_NULL(SAN + 11011, "Illegal argument: 'siteId' must not be null."), 
    ADD_ILLEGAL_San_ID_MUST_NOT_BE_NULL(SAN + 11011, "Illegal argument: 'storage_remote_id' must not be null."), 
    ADD_ILLEGAL_SITE_ID_NOT_EXIST(SAN + 11012, "Illegal argument: 'siteId':'{}' does not exist."),

    ADD_ILLEGAL_VENDOR_ID_MUST_NOT_BE_NULL(SAN + 11021, "Illegal argument: 'vendorId' must not be null."), 
    ADD_ILLEGAL_VENDOR_ID_NOT_EXIST(SAN + 11022, "Illegal argument: 'vendorId':'{}' does not exist."),

    ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK(SAN + 11031, "Illegal argument: 'name' must not be blank."), 
    ADD_ILLEGAL_NAME_EXIST(SAN + 11032, "Illegal argument: 'name':'{}' already exists."),

    ADD_ILLEGAL_DOMAIN_MUST_NOT_BE_BLANK(SAN + 11041, "Illegal argument: 'domain' must not be blank."),

    ADD_ILLEGAL_LUN_START_MUST_NOT_BE_NULL(SAN + 11051, "Illegal argument: 'lunStart' must not be null."),

    ADD_ILLEGAL_LUN_END_MUST_NOT_BE_NULL(SAN + 11061, "Illegal argument: 'lunEnd' must not be null."),

    ADD_ILLEGAL_HOST_LUN_START_MUST_NOT_BE_NULL(SAN + 11071, "Illegal argument: 'hostLunStart' must not be null."),

    ADD_ILLEGAL_HOST_LUN_END_MUST_NOT_BE_NULL(SAN + 11081, "Illegal argument: 'hostLunEnd' must not be null."),

    ADD_ILLEGAL_DESCRIPTION_MUST_NOT_BE_NULL(SAN + 11091, "Illegal argument: 'description' must not be null.");

    private int code;
    private String msg;

    private SanChkRsEnum(int code, String msg) {
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
