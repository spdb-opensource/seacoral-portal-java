package com.bsg.upm.check.resultenum;

public enum AreaChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_ID_NOT_EXIST(AREA, "该区域(id:{})不存在。"),

    ADD_ILLEGAL_SITE_ID_MUST_NOT_BE_NULL(AREA + 11011, "站点不能为空。"), 
    ADD_ILLEGAL_SITE_ID_NOT_EXIST(AREA + 11012, "该站点(id:{})不存在。"),

    ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK(AREA + 11021, "区域名称不能为空。"), 
    ADD_ILLEGAL_NAME_EXIST(AREA + 11022, "该区域名称({})已存在。"),

    ADD_ILLEGAL_BACKUP_STORAGE_ID_MUST_NOT_BE_NULL(AREA + 11031, "备份存储不能为空。"), 
    ADD_ILLEGAL_BACKUP_STORAGE_ID_NOT_EXIST(AREA + 11032, "该备份存储(id:{})不存在。"), 
    ADD_ILLEGAL_BACKUP_STORAGE_ID_NOT_MATCH(AREA + 11033, "该站点({})下备份存储({})不存在。"), 
    ADD_ILLEGAL_BACKUP_STORAGE_DISABLED(AREA + 11034, "该备份存储({})已停用。"), 


    UPDATE_ILLEGAL_ENABLED(AREA + 12001, "该区域({})已启用，无法编辑。"),

    UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY(AREA + 12021, "区域名称不能为空。"), 
    UPDATE_ILLEGAL_NAME_EXIST(AREA + 12022, "该区域名称({})已存在。"),

    UPDATE_ILLEGAL_BACKUP_STORAGE_ASSOCIATED_WITH_HOST(AREA + 12031, "该区域已存在主机，无法变更备份存储。"), 
    UPDATE_ILLEGAL_BACKUP_STORAGE_ID_NOT_EXIST(AREA + 12032, "该备份存储(id:{})不存在。"), 
    UPDATE_ILLEGAL_BACKUP_STORAGE_ID_NOT_MATCH(AREA + 12033, "该站点({})下备份存储({})不存在。"), 
    UPDATE_ILLEGAL_BACKUP_STORAGE_DISABLED(AREA + 12034, "该备份存储({})已停用。"), 

    REMOVE_ILLEGAL_ASSOCIATED_WITH_CLUSTER(AREA + 99001, "该区域({})已关联集群，无法删除。");

    private int code;
    private String msg;

    private AreaChkRsEnum(int code, String msg) {
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
