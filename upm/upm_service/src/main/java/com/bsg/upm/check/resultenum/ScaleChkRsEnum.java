package com.bsg.upm.check.resultenum;

public enum ScaleChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_ID_NOT_EXIST(SCALE, "该规模(id:{})不存在。"),

    ADD_ILLEGAL_TYPE_MUST_NOT_BE_BLANK(SCALE + 11011, "服务类型不能为空。"), 
    ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK(SCALE + 11011, "规模ID不能为空。"), 
    ADD_ILLEGAL_TYPE_NOT_EXIST(SCALE + 11012, "该服务类型({})不存在。"),

    ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK(SCALE + 11021, "规模名称不能为空。"), 
    ADD_ILLEGAL_NAME_EXIST(SCALE + 11022, "该规模名称({})已存在。"),

    ADD_ILLEGAL_CPU_CNT_MUST_NOT_BE_NULL(SCALE + 11031, "CPU数量不能为空。"), 

    ADD_ILLEGAL_MEM_SIZE_MUST_NOT_BE_NULL(SCALE + 11041, "内存容量不能为空。"), 
    ADD_ILLEGAL_CPU_MEM_EXIST(SCALE + 11042, "该CPU和内容已存在。"),

    ADD_ILLEGAL_SEQUENCE_MUST_NOT_BE_NULL(SCALE + 11051, "显示顺序不能为空。"), 

    
    UPDATE_ILLEGAL_ENABLED(SCALE + 12001, "该规模({})已启用，无法编辑"),

    UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY(SCALE + 12021, "规模名称不能为空。"), 
    UPDATE_ILLEGAL_NAME_EXIST(SCALE + 12022, "该规模名称({})已存在。"),

    REMOVE_ILLEGAL_USED_IN_ORDER_CONFIG(SCALE + 99001, "该规模({})已关联工单配置，无法删除"), 
    REMOVE_ILLEGAL_USED_IN_COMBO(SCALE + 99002, "该规模({})已关联套餐，无法删除"); 

    private int code;
    private String msg;

    private ScaleChkRsEnum(int code, String msg) {
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
