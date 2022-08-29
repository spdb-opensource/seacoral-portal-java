package com.bsg.upm.check.resultenum;

public enum ComboGroupChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_ID_NOT_EXIST(COMBO_GROUP, "该套餐(id:{})不存在。"),

    ADD_ILLEGAL_TYPE_MUST_NOT_BE_BLANK(COMBO_GROUP + 11011, "套餐类型不能为空。"), 
    ADD_ILLEGAL_TYPE_NOT_EXIST(COMBO_GROUP + 11012, "该套餐类型({})不存在。"),

    ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK(COMBO_GROUP + 11021, "套餐名称不能为空。"), 
    ADD_ILLEGAL_NAME_EXIST(COMBO_GROUP + 11022, "该套餐名称({})已存在。"),

    ADD_ILLEGAL_SEQUENCE_MUST_NOT_BE_NULL(COMBO_GROUP + 11031, "显示顺序不能为空。"), 


    ADD_ILLEGAL_COMBOS_FORMAT_ERROR(COMBO_GROUP + 11051, "BODY格式错误。"),

    ADD_ILLEGAL_ARCH_ID_MUST_NOT_BE_NULL(COMBO_GROUP + 11061, "{}架构不能为空。"), 
    ADD_ILLEGAL_ARCH_ID_NOT_EXIST(COMBO_GROUP + 11062, "{}架构(id:{})不存在。"), 

    ADD_ILLEGAL_SCALE_ID_MUST_NOT_BE_NULL(COMBO_GROUP + 11071, "{}规模不能为空。"), 
    ADD_ILLEGAL_SCALE_ID_NOT_EXIST(COMBO_GROUP + 11072, "{}规模(id:{})不存在。"), 

    ADD_ILLEGAL_DATA_DIR_SIZE_MUST_NOT_BE_NULL(COMBO_GROUP + 11081, "{}数据磁盘大小不能为空。"),

    ADD_ILLEGAL_DATA_DIR_TYPE_MUST_NOT_BE_BLANK(COMBO_GROUP + 11091, "{}数据磁盘类型不能为空。"),
    ADD_ILLEGAL_DATA_DIR_TYPE_NOT_EXIST(COMBO_GROUP + 11092, "{}数据磁盘类型(code:{})不存在。"),

    ADD_ILLEGAL_LOG_DIR_SIZE_MUST_NOT_BE_NULL(COMBO_GROUP + 11101, "{}日志磁盘类型不能为空。"),

    ADD_ILLEGAL_LOG_DIR_TYPE_MUST_NOT_BE_BLANK(COMBO_GROUP + 11111, "{}日志磁盘类型不能为空。"),
    ADD_ILLEGAL_LOG_DIR_TYPE_NOT_EXIST(COMBO_GROUP + 11112, "{}日志磁盘类型(code:{})不存在。"),

    ADD_ILLEGAL_NETWORK_BANDWIDTH_MUST_NOT_BE_NULL(COMBO_GROUP + 11121, "{}带宽不能为空。"),

    
    UPDATE_ILLEGAL_ENABLED(COMBO_GROUP + 12001, "该套餐({})已启用，无法编辑。"),
    
    UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY(COMBO_GROUP + 12021, "套餐名称不能为空。"), 
    UPDATE_ILLEGAL_NAME_EXIST(COMBO_GROUP + 11022, "该套餐名称({})已存在。"),

    UPDATE_ILLEGAL_COMBOS_FORMAT_ERROR(COMBO_GROUP + 12051, "BODY格式错误。"),

    UPDATE_ILLEGAL_ARCH_ID_NOT_EXIST(COMBO_GROUP + 12062, "{}架构不能为空。"), 
    UPDATE_ILLEGAL_ARCH_ID_NOT_MATCH(COMBO_GROUP + 120631, "{}架构(id:{})不存在。"), 

    UPDATE_ILLEGAL_SCALE_ID_NOT_EXIST(COMBO_GROUP + 12072, "{}规模不能为空。"), 
    UPDATE_ILLEGAL_SCALE_ID_NOT_MATCH(COMBO_GROUP + 12073, "{}规模(id:{})不存在。"), 

    UPDATE_ILLEGAL_DATA_DIR_TYPE_NOT_EXIST(COMBO_GROUP + 12092, "{}数据磁盘类型(code:{})不存在。"),

    UPDATE_ILLEGAL_LOG_DIR_TYPE_NOT_EXIST(COMBO_GROUP + 12112, "{}日志磁盘类型(code:{})不存在。");

    private int code;
    private String msg;

    private ComboGroupChkRsEnum(int code, String msg) {
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
