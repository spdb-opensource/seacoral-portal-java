package com.bsg.upm.check.resultenum;

public enum UnitChkRsEnum implements ChkRsInterface {

    // 启用11xxx,停止12xxx,备份13xxx,还原14xxx,迁移15xxx,重建16xxx,隔离17xxx,回切18xxx,切换19xxx,升级20xxx
    SUCCESS(OK, "OK"), 
    ILLEGAL_ID_NOT_EXIST(UNIT, "该单元(id:{})不存在。"),
    ILLEGAL_NOT_SUPPORT_OPERATE(UNIT + 1, "该单元(type:{})不支持此操作。"),
    ILLEGAL_BECAUSE_TASK_NOT_COMPLETE(UNIT + 2, "该单元({})任务尚未结束，无法对此进行此操作。"), 

    BACKUP_ILLEGAL_TYPE_BLANK(UNIT + 13011, "备份类型不能为空。"), 
    BACKUP_ILLEGAL_TYPE_NOT_EXIST(UNIT + 13012, "该备份类型(code:{})不存在。"), 
    BACKUP_ILLEGAL_TABLES_MUST_NOT_BE_BLANK(UNIT + 13021, "导表备份时，必须选择库表。"),
    BACKUP_ILLEGAL_RETENTION_NULL(UNIT + 13031, "备份文件保留天数不能为空"),

    MIGRATE_ILLEGAL_HOST_ID_NOT_EXIST(UNIT + 15011, "该主机(id:{})不存在。"), 
    MIGRATE_ILLEGAL_HOST_NOT_ENABLED(UNIT + 15012, "该主机({})未启用，不能选择该主机。"),

    REBUILD_ILLEGAL_HOST_ID_NOT_EXIST(UNIT + 16011, "该主机(id:{})不存在。"), 
    REBUILD_ILLEGAL_HOST_NOT_ENABLED(UNIT + 16012, "该主机({})未启用，不能选择该主机。"),

    UPDATE_IMAGE_ILLEGAL_IMAGE_ID_NOT_EXIST(UNIT + 20011, "该镜像(id:{})不存在。"), 
    UPDATE_IMAGE_ILLEGAL_IMAGE_NOT_ENABLED(UNIT + 20012, "该镜像({})未启用，不能选择该镜像。"),
    UPDATE_IMAGE_ILLEGAL_IMAGE_TYPE_NOT_MATCH(UNIT + 20013, "该镜像类型与单元类型不匹配。");

    private int code;
    private String msg;

    private UnitChkRsEnum(int code, String msg) {
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
