package com.bsg.upm.check.resultenum;

public enum BackupStorageChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_ID_NOT_EXIST(BACKUP_STORAGE, "该备份存储(id:{})不存在。"),

    ADD_ILLEGAL_SITE_ID_MUST_NOT_BE_NULL(BACKUP_STORAGE + 11011, "站点不能为空。"), 
    ADD_ILLEGAL_SITE_ID_NOT_EXIST(BACKUP_STORAGE + 11012, "该站点(id:{})不存在。"),

    ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK(BACKUP_STORAGE + 11021, "备份存储名称不能为空。"), 
    ADD_ILLEGAL_NAME_EXIST(BACKUP_STORAGE + 11022, "该备份存储名称({})已经存在。"),

    ADD_ILLEGAL_TYPE_MUST_NOT_BE_BLANK(BACKUP_STORAGE + 11031, "备份存储类型不能为空。"), 
    ADD_ILLEGAL_TYPE_NOT_EXIST(BACKUP_STORAGE + 11032, "该备份存储类型(code:{})不存在。"),

    ADD_ILLEGAL_IP_MUST_NOT_BE_BLANK(BACKUP_STORAGE + 11041, "IP地址不能为空。"), 
    ADD_ILLEGAL_IP_FORMAT_ERROR(BACKUP_STORAGE + 11042, "IP地址格式错误。"), 

    ADD_ILLEGAL_DIR_MUST_NOT_BE_BLANK(BACKUP_STORAGE + 11051, "源目录不能为空。"), 

    ADD_ILLEGAL_MOUNT_DIR_MUST_NOT_BE_BLANK(BACKUP_STORAGE + 11061, "挂载目录不能为空。"), 
    ADD_ILLEGAL_MOUNT_DIR_EXIST(BACKUP_STORAGE + 11062, "IP地址、源目录和挂载目录已经存在。"), 


    UPDATE_ILLEGAL_ENABLED(BACKUP_STORAGE + 12001, "该备份存储({})已启用，无法编辑。"),

    UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY(BACKUP_STORAGE + 12021, "备份存储名称不能为空。"), 
    UPDATE_ILLEGAL_NAME_EXIST(BACKUP_STORAGE + 12022, "该备份存储名称({})已经存在。"),

    REMOVE_ILLEGAL_ASSOCIATED_WITH_AREA(BACKUP_STORAGE + 99001, "该备份存储({})已关联区域，无法删除。");

    private int code;
    private String msg;

    private BackupStorageChkRsEnum(int code, String msg) {
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
