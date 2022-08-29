package com.bsg.upm.check.resultenum;

public enum ClusterChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_ID_NOT_EXIST(CLUSTER, "该集群(id:{})不存在。"),

    ADD_ILLEGAL_AREA_CODE_MUST_NOT_BE_BLANK(CLUSTER + 11011, "区域不能为空。"), 
    ADD_ILLEGAL_AREA_CODE_NOT_EXIST(CLUSTER + 11012, "该区域(id:{})不存在。"),

    ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK(CLUSTER + 11021, "集群名称不能为空。"), 
    ADD_ILLEGAL_NAME_EXIST(CLUSTER + 11022, "该集群名称({})已存在。"),

    ADD_ILLEGAL_IMAGE_TYPES_MUST_NOT_BE_BLANK(CLUSTER + 11031, "包含软件不能为空。"), 
    ADD_ILLEGAL_IMAGE_TYPES_NOT_EXIST(CLUSTER + 11032, "包含软件({})不存在。"), 

    ADD_ILLEGAL_HA_TAG_MUST_NOT_BE_BLANK(CLUSTER + 11041, "高可用不能为空。"), 

    ADD_ILLEGAL_SITE_ID_MUST_NOT_BE_BLANK(CLUSTER + 11051, "站点不能为空。"), 
    
    UPDATE_ILLEGAL_ENABLED(CLUSTER + 12001, "该集群({})已启用，无法编辑。"),

    UPDATE_ILLEGAL_AREA_ASSOCIATED_WITH_HOST(CLUSTER + 12011, "该集群存在主机已入库，无法编辑区域。"),
    UPDATE_ILLEGAL_AREA_CODE_NOT_EXIST(CLUSTER + 12012, "该区域(id:{})不存在。"),
    UPDATE_ILLEGAL_AREA_DISABLED(CLUSTER + 12013, "该区域({})已停用。"),
    UPDATE_ILLEGAL_AREA_CODE_MUST_NOT_BE_BLANK(CLUSTER + 12014, "区域不能为空。"),

    UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY(CLUSTER + 12021, "集群名称不能为空。"), 
    UPDATE_ILLEGAL_NAME_EXIST(CLUSTER + 12022,  "该集群名称({})已存在。"),

    UPDATE_ILLEGAL_IMAGE_TYPES_MUST_NOT_BE_BLANK(CLUSTER + 12031, "包含软件不能为空。"), 
    
    UPDATE_ILLEGAL_HA_TAG_MUST_NOT_BE_BLANK(CLUSTER + 12041, "高可用不能为空。"),
    
    REMOVE_ILLEGAL_ASSOCIATED_WITH_NETWORKING(CLUSTER + 99001, "该集群({})已关联网段，无法删除。"),
    REMOVE_ILLEGAL_ASSOCIATED_WITH_HOST(CLUSTER + 99002, "该集群({})已关联主机，无法删除。"),
    REMOVE_ILLEGAL_ASSOCIATED_WITH_ENABLED(CLUSTER + 99003, "该集群({})已启用，无法删除。"),

    UPLOAD_ILLEGAL_SITE_ID_NOT_EXIST(CLUSTER + 90001, "该站点(id:{})不存在。"),
    UPLOAD_NO_DATA(CLUSTER + 90002, "模板无数据。"),

    UPLOAD_AREA_NAME_MUST_NOT_BE_EMPTY(CLUSTER + 90011, "模板格式错误：第{}行 区域名称不能为空。"), 
    UPLOAD_AREA_NAME_NOT_EXIST(CLUSTER + 90012, "模板格式错误：第{}行 区域名称不存在。"),  
    UPLOAD_AREA_DISABLED(CLUSTER + 90013, "模板格式错误：第{}行 区域已停用。"),

    UPLOAD_CLUSTER_NAME_MUST_NOT_BE_EMPTY(CLUSTER + 90021, "模板格式错误：第{}行 集群名称不能为空。"), 
    UPLOAD_CLUSTER_NAME_REPEATED(CLUSTER + 90022, "模板格式错误：第{}行和第{}行 集群名称重复。"), 
    UPLOAD_CLUSTER_NAME_EXIST(CLUSTER + 90023, "模板格式错误：第{}行 集群名称已经存在。"), 

    UPLOAD_SOFTWARE_TYPES_MUST_NOT_BE_EMPTY(CLUSTER + 90031, "模板格式错误：第{}行 包含软件不能为空。"), 
    UPLOAD_SOFTWARE_TYPES_NOT_EXIST(CLUSTER + 90032, "模板格式错误：第{}行 包含软件不存在。"), 

    UPLOAD_NETWORK_TAG_MUST_NOT_BE_EMPTY(CLUSTER + 90041, "模板格式错误：第{}行 网络标签不能为空。"); 

    private int code;
    private String msg;

    private ClusterChkRsEnum(Integer code, String msg) {
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
