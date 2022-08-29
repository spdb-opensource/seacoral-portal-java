package com.bsg.upm.check.resultenum;

public enum ServerGroupUnitChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_ID_NOT_EXIST(SERVERGROUP_UNIT, "该服务组单元(id:{})不存在。"),
    
    MONITORREGISTER_ILLEGAL_ID_MUST_NOT_BE_EMPTY(SERVERGROUP_UNIT + 11029, "单元id不能为空。"),
    MONITORREGISTER_ILLEGAL_NAME_MUST_NOT_BE_EMPTY(SERVERGROUP_UNIT + 11021, "单元名称不能为空。"),
    MONITORREGISTER_ILLEGAL_CODE_MUST_NOT_BE_EMPTY(SERVERGROUP_UNIT + 11021, "relateCode不能为空。"),
    MONITORREGISTER_ILLEGAL_CONTAINER_MUST_NOT_BE_EMPTY(SERVERGROUP_UNIT + 11022, "容器不能为空。"),
    MONITORREGISTER_ILLEGAL_CATEGRRY_MUST_NOT_BE_EMPTY(SERVERGROUP_UNIT + 11023, "类别不能为空。"),
    MONITORREGISTER_ILLEGAL_CONTAINER_NAME_MUST_NOT_BE_EMPTY(SERVERGROUP_UNIT + 11024, "容器名称不能为空。"),
    MONITORREGISTER_ILLEGAL_HOST_NAME_MUST_NOT_BE_EMPTY(SERVERGROUP_UNIT + 11025, "主机名称不能为空。"),
    MONITORREGISTER_ILLEGAL_CONTAINER_CATEGRRY_MUST_NOT_BE_EMPTY(SERVERGROUP_UNIT + 11026, "容器类别不能为空。"),
    MONITORREGISTER_ILLEGAL_TAG_MUST_NOT_BE_EMPTY(SERVERGROUP_UNIT + 11028, "标签不能为空。"),
    
    ADD_ILLEGAL_SERVER_GROUP_ID_MUST_NOT_BE_BLANK(SERVERGROUP_UNIT + 11011, "服务组id不能为空。"),
    ADD_ILLEGAL_STORAGE_TYPE_MUST_NOT_BE_BLANK(SERVERGROUP_UNIT + 11012, "存储类型不能为空。"),
    ADD_ILLEGAL_TYPE_MUST_NOT_BE_BLANK(SERVERGROUP_UNIT + 11013, "备份类型不能为空。"),
    ADD_ILLEGAL_RETENTION_MUST_NOT_BE_BLANK(SERVERGROUP_UNIT + 11014, "备份文件保留天数不能为空。");
	


    private int code;
    private String msg;

    private ServerGroupUnitChkRsEnum(int code, String msg) {
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
