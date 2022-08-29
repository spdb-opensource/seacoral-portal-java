package com.bsg.upm.check.resultenum;

public enum SiteChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_ID_NOT_EXIST(SITE, "该站点(id:{})不存在。"),

    ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK(SITE + 11011, "站点名称不能为空。"), 
    ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK(SITE + 11011, "站点ID不能为空。"), 
    ADD_ILLEGAL_NAME_EXIST(SITE + 11012, "站点名称({})已经存在。"),

    ADD_ILLEGAL_REGION_MUST_NOT_BE_BLANK(SITE + 11021, "地域不能为空。"), 
    ADD_ILLEGAL_REGION_NOT_EXIST(SITE + 11022, "地域(code:{})不存在。"),
    
    ADD_ILLEGAL_CONSUL_ADDRS_MUST_NOT_BE_BLANK(SITE + 11031, "consul地址不能为空。"), 
    ADD_ILLEGAL_CONSUL_ADDRS_FORMAT_ERROR(SITE + 11032, "consul地址格式错误(格式为ip:port)。"),
    
    ADD_ILLEGAL_TYPE_MUST_NOT_BE_BLANK(SITE + 11041, "类型不能为空。"), 
    
    ADD_ILLEGAL_DOMAIN_MUST_NOT_BE_BLANK(SITE + 11041, "域名不能为空。"), 
    
    ADD_ILLEGAL_PORT_MUST_NOT_BE_BLANK(SITE + 11041, "端口不能为空。"), 

    REMOVE_ILLEGAL_EXIST_BACKUP_STORAGE(SITE + 99001, "该站点下({})存在资源(备份存储)未释放，无法删除。"), 
    REMOVE_ILLEGAL_EXIST_PORT(SITE + 99002, "该站点下({})存在资源(端口)未释放，无法删除。"), 
    REMOVE_ILLEGAL_EXIST_SOFTWARE_IMAGE(SITE + 99003, "该站点下({})存在资源(软件镜像)未释放，无法删除。"), 
    REMOVE_ILLEGAL_EXIST_SAN(SITE + 99004, "该站点下({})存在资源(SAN)未释放，无法删除。"); 

    private int code;
    private String msg;

    private SiteChkRsEnum(int code, String msg) {
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
