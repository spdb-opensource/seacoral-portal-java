package com.bsg.upm.check.resultenum;

public enum DictChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_ID_NOT_EXIST(DICT, "该字典(id:{})不存在。"),

    ADD_ILLEGAL_DICT_CODE_MUST_NOT_BE_EMPTY(DICT + 11021, "字典类型代码不能为空。"),  
    ADD_ILLEGAL_NAME_MUST_NOT_BE_EMPTY(DICT + 11022, "类型项名称不能为空。"),  
    ADD_ILLEGAL_CODE_MUST_NOT_BE_EMPTY(DICT + 11023, "类型项代码不能为空。"),  
    ADD_ILLEGAL_SEQUENCE_MUST_NOT_BE_EMPTY(DICT + 11024, "显示顺序不能为空。"), 
    ADD_ILLEGAL_NAME_EXIST(DICT + 11025, "该字典名称({})已存在。"),
    ADD_ILLEGAL_DICT_TYPE_NOT_EXIST(DICT + 11026, "该字典({})不存在。"),
    ADD_ILLEGAL_CODE_EXIST(DICT + 11027, "该字典类型项({})已存在。"),
    
    UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY(DICT + 12021, "字典名称不能为空。"),  
    UPDATE_ILLEGAL_ID_MUST_NOT_BE_EMPTY(DICT + 12023, "字典ID不能为空。"),  
    UPDATE_ILLEGAL_NAME_EXIST(DICT + 12022, "该字典名称({})已存在。");

    private int code;
    private String msg;

    private DictChkRsEnum(int code, String msg) {
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
