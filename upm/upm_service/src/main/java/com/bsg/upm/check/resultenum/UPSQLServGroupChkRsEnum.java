package com.bsg.upm.check.resultenum;

public enum UPSQLServGroupChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_ID_NOT_EXIST(UPSQL_SERV_GROUP, "该UPSQL服务组(id:{})不存在。"), 
    ILLEGAL_NOT_CREATE_SUCCESS(UPSQL_SERV_GROUP + 1, "该服务组({})尚未创建成功，无法进行此操作。"), 
    ILLEGAL_TASK_NOT_COMPLETE(UPSQL_SERV_GROUP + 2, "该服务组({})任务尚未结束，无法进行此操作。"), 
    
    CLONE_ILLEGAL_NOT_SCALE_UP_SUCCESS(UPSQL_SERV_GROUP + 11001, "该服务组({})扩容尚未成功，无法克隆。"),
    CLONE_ILLEGAL_NOT_IMAGE_UPDATE_SUCCESS(UPSQL_SERV_GROUP + 11002, "该服务组({})升级尚未成功，无法克隆。"),
    CLONE_ILLEGAL_NOT_SCALE_OUT_COMPLETE(UPSQL_SERV_GROUP + 11003, "该服务组({})分片扩展尚未完成，无法克隆。"), 

    SCALE_UP_ILLEGAL_LAST_SCALE_UP_ORDER_GROUP_UNAPPROVED(UPSQL_SERV_GROUP + 14001, "该服务组({})最近一次扩容单尚未审批，无法再次扩容。"),
    SCALE_UP_ILLEGAL_LAST_SCALE_UP_ORDER_GROUP_NOT_EXECUTE(UPSQL_SERV_GROUP + 14002, "该服务组({})最近一次扩容单审批通过尚未执行，无法再次扩容。"),
    SCALE_UP_ILLEGAL_LAST_SCALE_UP_ORDER_GROUP_FAILURE(UPSQL_SERV_GROUP + 14003, "该服务组({})最近一次扩容失败，无法再次扩容。"),
    SCALE_UP_ILLEGAL_LAST_SCALE_OUT_ORDER_GROUP_UNAPPROVED(UPSQL_SERV_GROUP + 14004, "该服务组({})最近一次分片扩展单尚未审批，无法扩容。"),
    SCALE_UP_ILLEGAL_LAST_SCALE_OUT_ORDER_GROUP_NOT_EXECUTE(UPSQL_SERV_GROUP + 14005, "该服务组({})最近一次分片扩展单审批通过尚未执行，无法扩容。"),
    SCALE_UP_ILLEGAL_LAST_SCALE_OUT_ORDER_GROUP_FAILURE(UPSQL_SERV_GROUP + 14006, "该服务组({})最近一次分片扩展分片准备失败，无法扩容。"),
    SCALE_UP_ILLEGAL_TYPE_MUST_NOT_BE_BLANK(UPREDIS_SERV_GROUP + 14021, "服务类型不能为空。"),
    SCALE_UP_ILLEGAL_SCALE_NOT_EXIST(UPREDIS_SERV_GROUP + 14031, "该性能套餐不存在。"),

    IMAGE_UPDATE_ILLEGAL_LAST_IMAGE_UPDATE_ORDER_GROUP_UNAPPROVED(UPSQL_SERV_GROUP + 15001, "该服务组({})最近一次升级单尚未审批，无法再次升级。"),
    IMAGE_UPDATE_ILLEGAL_LAST_IMAGE_UPDATE_ORDER_GROUP_NOT_EXECUTE(UPSQL_SERV_GROUP + 15002, "该服务组({})最近一次升级单审批通过尚未执行，无法再次升级。"),
    IMAGE_UPDATE_ILLEGAL_LAST_IMAGE_UPDATE_ORDER_GROUP_FAILURE(UPSQL_SERV_GROUP + 15003, "该服务组({})最近一次升级失败，无法再次升级。"),
    IMAGE_UPDATE_ILLEGAL_LAST_SCALE_OUT_ORDER_GROUP_UNAPPROVED(UPSQL_SERV_GROUP + 14004, "该服务组({})最近一次分片扩展单尚未审批，无法升级。"),
    IMAGE_UPDATE_ILLEGAL_LAST_SCALE_OUT_ORDER_GROUP_NOT_EXECUTE(UPSQL_SERV_GROUP + 14005, "该服务组({})最近一次分片扩展单审批通过尚未执行，无法升级。"),
    IMAGE_UPDATE_ILLEGAL_LAST_SCALE_OUT_ORDER_GROUP_FAILURE(UPSQL_SERV_GROUP + 14006, "该服务组({})最近一次分片扩展分片准备失败，无法升级。"),
    IMAGE_UPDATE_ILLEGAL_TYPE_MUST_NOT_BE_BLANK(UPSQL_SERV_GROUP + 15021, "服务类型不能为空。"),
    IMAGE_UPDATE_ILLEGAL_VERSION_FORMAT_ERROR(UPSQL_SERV_GROUP + 15021, "镜像版本不能为空。"),
    IMAGE_UPDATE_ILLEGAL_VERSION_NOT_EXIST(UPSQL_SERV_GROUP + 15022, "该版本({})不存在."), 
    IMAGE_UPDATE_ILLEGAL_IMAGE_NOT_ENABLED(UPSQL_SERV_GROUP + 15022, "该版本({})未启用."), 
    
    BACKUP_ILLEGAL_TYPE_BLANK(UPSQL_SERV_GROUP + 17011, "备份类型不能为空。"), 
    BACKUP_ILLEGAL_TYPE_NOT_EXIST(UPSQL_SERV_GROUP + 17012, "该备份类型(code:{})不存在。"), 
    BACKUP_ILLEGAL_TABLES_MUST_NOT_BE_BLANK(UPSQL_SERV_GROUP + 17021, "导表备份时，必须选择库表。"),
    BACKUP_ILLEGAL_RETENTION_NULL(UPSQL_SERV_GROUP + 17031, "备份文件保留天数不能为空");

    private int code;
    private String msg;

    private UPSQLServGroupChkRsEnum(int code, String msg) {
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
