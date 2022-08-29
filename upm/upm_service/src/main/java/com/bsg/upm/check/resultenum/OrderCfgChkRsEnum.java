package com.bsg.upm.check.resultenum;

public enum OrderCfgChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_GROUP_TYPE_EXIST(ORDER_CONFIG, "{}工单配置已存在。"), 
    ILLEGAL_GROUP_TYPE_NOT_EXIST(ORDER_CONFIG + 1, "{}工单配置不存在。"), 

    ADD_ILLEGAL_FORMAT_ERROR(ORDER_CONFIG + 11011, "BODY体格式错误。"),

    ADD_ILLEGAL_ARCH_ID_MUST_NOT_BE_NULL(ORDER_CONFIG + 11021, "{}架构不能为空。"), 
    ADD_ILLEGAL_ARCH_ID_NOT_EXIST(ORDER_CONFIG + 11022, "{}架构(id:{})不存在。"), 

    ADD_ILLEGAL_SCALE_ID_MUST_NOT_BE_NULL(ORDER_CONFIG + 11031, "{}规模不能为空。"), 
    ADD_ILLEGAL_SCALE_ID_NOT_EXIST(ORDER_CONFIG + 11032, "{}规模(id:{})不存在。"), 

    ADD_ILLEGAL_DATA_DIR_SIZE_MUST_NOT_BE_NULL(ORDER_CONFIG + 11041, "{}数据磁盘大小不能为空。"),

    ADD_ILLEGAL_DATA_DIR_TYPE_MUST_NOT_BE_BLANK(ORDER_CONFIG + 11051, "{}数据磁盘类型不能为空。"),
    ADD_ILLEGAL_DATA_DIR_TYPE_NOT_EXIST(ORDER_CONFIG + 11052, "{}数据磁盘类型(code:{})不存在。"),

    ADD_ILLEGAL_LOG_DIR_SIZE_MUST_NOT_BE_NULL(ORDER_CONFIG + 11061, "{}日志磁盘类型不能为空。"),

    ADD_ILLEGAL_LOG_DIR_TYPE_MUST_NOT_BE_BLANK(ORDER_CONFIG + 11071, "{}日志磁盘类型不能为空。"),
    ADD_ILLEGAL_LOG_DIR_TYPE_NOT_EXIST(ORDER_CONFIG + 11072, "{}日志磁盘类型(code:{})不存在。"),

    ADD_ILLEGAL_NETWORK_BANDWIDTH_MUST_NOT_BE_NULL(ORDER_CONFIG + 11081, "{}带宽不能为空。"),

    ADD_ILLEGAL_NETWORK_HA_MUST_NOT_BE_NULL(ORDER_CONFIG + 11091, "{}网络高可用不能为空。"),

    ADD_ILLEGAL_NODE_HA_MUST_NOT_BE_NULL(ORDER_CONFIG + 11101, "{}节点高可用不能为空。"),

    ADD_ILLEGAL_STORAGE_HA_MUST_NOT_BE_NULL(ORDER_CONFIG + 11111, "{}存储高可用不能为空。"),


    UPDATE_ILLEGAL_FORMAT_ERROR(ORDER_CONFIG + 12011, "BODY体格式错误。"),

    UPDATE_ILLEGAL_ARCH_ID_NOT_EXIST(ORDER_CONFIG + 12022, "{}架构(id:{})不存在。"), 

    UPDATE_ILLEGAL_SCALE_ID_NOT_EXIST(ORDER_CONFIG + 12032, "{}规模(id:{})不存在。"), 

    UPDATE_ILLEGAL_DATA_DIR_TYPE_NOT_EXIST(ORDER_CONFIG + 12052, "{}数据磁盘类型({})不存在。"),

    UPDATE_ILLEGAL_LOG_DIR_TYPE_NOT_EXIST(ORDER_CONFIG + 12072, "{}日志磁盘类型({})不存在。");

    private int code;
    private String msg;

    private OrderCfgChkRsEnum(int code, String msg) {
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
