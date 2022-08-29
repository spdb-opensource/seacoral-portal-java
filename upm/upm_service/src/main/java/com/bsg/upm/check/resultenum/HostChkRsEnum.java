package com.bsg.upm.check.resultenum;

public enum HostChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_ID_NOT_EXIST(HOST, "该主机(id:{})不存在。"),
    ILLEGAL_BECAUSE_OUT(HOST + 1, "该主机({})未入库或未入库成功，无法进行此操作。"),

    ADD_ILLEGAL_CLUSTER_ID_MUST_NOT_BE_NULL(HOST + 11011, "集群不能为空。"), 
    ADD_ILLEGAL_CLUSTER_ID_NOT_EXIST(HOST + 11012, "该集群(id:{})不存在。"), 
    ADD_ILLEGAL_CLUSTER_DISABLED(HOST + 11013, "该集群({})已停用。"), 

    ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK(HOST + 11021, "主机名称不能为空。"), 
    ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK(HOST + 11023, "主机ID不能为空。"), 
    ADD_ILLEGAL_NAME_EXIST(HOST + 11022, "该主机名称({})已存在。"), 

    ADD_ILLEGAL_IP_MUST_NOT_BE_BLANK(HOST + 11031, "主机IP不能为空。"), 
    ADD_ILLEGAL_IP_FORMAT_ERROR(HOST + 11032, "该主机IP({})格式错误。"), 
    ADD_ILLEGAL_IP_EXIST(HOST + 11033, "该主机IP({})已存在。"), 

    ADD_ILLEGAL_PORT_MUST_NOT_BE_NULL(HOST + 11041, "SSH端口不能为空。"), 

    ADD_ILLEGAL_USERNAME_MUST_NOT_BE_BLANK(HOST + 11051, "SSH用户名不能为空。"), 

    ADD_ILLEGAL_PASSWORD_MUST_NOT_BE_BLANK(HOST + 11061, "SSH密码不能为空。"), 

    ADD_ILLEGAL_ROOM_MUST_NOT_BE_BLANK(HOST + 11071, "机房不能为空。"), 

    ADD_ILLEGAL_SEAT_MUST_NOT_BE_BLANK(HOST + 11081, "机位不能为空。"), 

    ADD_ILLEGAL_HDD_SSD_SAN_NOT_ALL_BLANK(HOST + 11091, "HDD设备、SSD设备、外置存储至少输入其中一项。"),  

    ADD_ILLEGAL_MAX_UNIT_CNT_MUST_NOT_BE_NULL(HOST + 11101, "最大单元数量不能为空。"), 
    ADD_ILLEGAL_MAX_UNIT_CNT_BELOW_LIMIT(HOST + 11102, "最大单元数量上限必须大于{}。"), 

    ADD_ILLEGAL_MAX_CPU_USAGE_MUST_NOT_BE_NULL(HOST + 11111, "CPU最大使用率不能为空。"),
    ADD_ILLEGAL_MAX_CPU_USAGE_BELOW_LIMIT(HOST + 11112, "CPU最大使用率必须大于{}。"),
    ADD_ILLEGAL_MAX_CPU_USAGE_OVER_LIMIT(HOST + 11113, "CPU最大使用率不能超过{}。"),
    
    ADD_ILLEGAL_MAX_MEM_USAGE_MUST_NOT_BE_NULL(HOST + 11121, "内存最大使用率不能为空。"),
    ADD_ILLEGAL_MAX_MEM_USAGE_BELOW_LIMIT(HOST + 11122, "内存最大使用率必须大于{}。"),
    ADD_ILLEGAL_MAX_MEM_USAGE_OVER_LIMIT(HOST + 11123, "内存最大使用率不能超过{}。"),
    
    ADD_ILLEGAL_MAX_BANDWIDTH_USAGE_MUST_NOT_BE_NULL(HOST + 11131, "带宽最大使用率不能为空。"),
    ADD_ILLEGAL_MAX_BANDWIDTH_USAGE_BELOW_LIMIT(HOST + 11132, "带宽最大使用率必须大于{}。"),
    ADD_ILLEGAL_MAX_BANDWIDTH_USAGE_OVER_LIMIT(HOST + 11133, "带宽最大使用率不能超过{}。"),
    
    ADD_ILLEGAL_MAX_STORAGE_USAGE_MUST_NOT_BE_NULL(HOST + 11141, "存储最大使用率不能为空。"),
    ADD_ILLEGAL_MAX_STORAGE_USAGE_BELOW_LIMIT(HOST + 11142, "存储最大使用率必须大于{}。"),
    ADD_ILLEGAL_MAX_STORAGE_USAGE_OVER_LIMIT(HOST + 11143, "存储最大使用率不能超过{}。"),


    UPDATE_ILLEGAL_ENABLED(HOST + 12001, "该主机({})已启用，无法编辑。"),

    UPDATE_ILLEGAL_CLUSTER_ID_NOT_EXIST(HOST + 12012, "该集群(id:{})不存在。"), 
    UPDATE_ILLEGAL_CLUSTER_DISABLED(HOST + 12013, "该集群({})已停用。"), 

    UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY(HOST + 12021, "主机名称不能为空。"), 
    UPDATE_ILLEGAL_NAME_EXIST(HOST + 12022, "该主机名称({})已存在。"), 

    UPDATE_ILLEGAL_IP_MUST_NOT_BE_EMPTY(HOST + 12031, "主机IP不能为空。"), 
    UPDATE_ILLEGAL_IP_FORMAT_ERROR(HOST + 12032, "该主机IP({})格式错误。"), 
    UPDATE_ILLEGAL_IP_EXIST(HOST + 12033, "该主机IP({})已存在。"), 

    UPDATE_ILLEGAL_USERNAME_MUST_NOT_BE_EMPTY(HOST + 12051, "SSH用户名不能为空。"), 

    UPDATE_ILLEGAL_PASSWORD_MUST_NOT_BE_EMPTY(HOST + 12061, "SSH密码不能为空。"), 

    UPDATE_ILLEGAL_ROOM_MUST_NOT_BE_EMPTY(HOST + 12071, "机房不能为空。"), 

    UPDATE_ILLEGAL_SEAT_MUST_NOT_BE_EMPTY(HOST + 12081, "机位不能为空。"),

    UPDATE_ILLEGAL_SAN_ID_NOT_EXIST(HOST + 12092, "SAN存储(id:{})不存在。"), 

    UPDATE_ILLEGAL_MAX_CONTAINER_CNT_BELOW_LIMIT(HOST + 12102, "容器上限必须大于{}。"), 

    UPDATE_ILLEGAL_MAX_USAGE_BELOW_LIMIT(HOST + 12112, "最大使用率必须大于{}。"),
    UPDATE_ILLEGAL_MAX_USAGE_OVER_LIMIT(HOST + 12113, "最大使用率不能超过{}。"),

    IN_ILLEGAL_BECAUSE_ALREADY_IN(HOST + 13001, "该主机({})已入库成功，无法再次入库。"), 
    IN_ILLEGAL_BECAUSE_TASK_NOT_COMPLETE(HOST + 13002, "该主机({})任务尚未结束，无法入库。"),

    OUT_ILLEGAL_BECAUSE_ALREADY_OUT(HOST + 14001, "该主机({})未入库，无法出库。"), 
    OUT_ILLEGAL_BECAUSE_TASK_NOT_COMPLETE(HOST + 14002, "该主机({})任务尚未结束，无法出库。"),

    ENABLED_ILLEGAL_BECAUSE_OUT(HOST + 15001, "该主机({})未入库成功，无法启用。"), 

    DISABLED_ILLEGAL_BECAUSE_OUT(HOST + 16001, "该主机({})未入库成功，无法停用。"), 

    REMOVE_ILLEGAL_BECAUSE_IN(HOST + 99001, "该主机({})未出库，无法删除。"), 
    REMOVE_ILLEGAL_WITH_ENABLED(HOST + 99002, "该主机({})已启用，无法删除。"), 

    UPLOAD_ILLEGAL_SITE_ID_NOT_EXIST(HOST + 90001, "该站点(id:{})不存在。"),
    UPLOAD_NO_DATA(HOST + 90002, "模板无数据。"),

    UPLOAD_CLUSTER_NAME_MUST_NOT_BE_EMPTY(HOST + 98011, "模板格式错误：第{}行 集群名称不能为空。"), 
    UPLOAD_CLUSTER_NAME_NOT_EXIST(HOST + 98012, "模板格式错误：第{}行 集群名称不存在。"), 
    UPLOAD_CLUSTER_NAME_DISABLED(HOST + 98013, "模板格式错误：第{}行 集群已停用。"), 

    UPLOAD_NAME_MUST_NOT_BE_BLANK(HOST + 98021, "模板格式错误：第{}行 主机名称不能为空。"), 
    UPLOAD_NAME_REPEATED(HOST + 98022, "模板格式错误：第{}行和第{}行 主机名称重复。"), 
    UPLOAD_NAME_EXIST(HOST + 98023, "模板格式错误：第{}行 主机名称已存在。"), 

    UPLOAD_IP_MUST_NOT_BE_BLANK(HOST + 98031, "模板格式错误：第{}行 主机IP不能为空。"), 
    UPLOAD_IP_FORMAT_ERROR(HOST + 98032, "模板格式错误：第{}行 主机IP格式错误。"),  
    UPLOAD_IP_REPEATED(HOST + 98033, "模板格式错误：第{}行和第{}行 主机IP重复。"), 
    UPLOAD_IP_EXIST(HOST + 98034, "模板格式错误：第{}行 主机IP已存在。"), 

    UPLOAD_PORT_MUST_NOT_BE_BLANK(HOST + 98041, "模板格式错误：第{}行 SSH端口不能为空。"), 
    UPLOAD_PORT_FORMAT_ERROR(HOST + 98042, "模板格式错误：第{}行 SSH端口格式错误。"), 

    UPLOAD_USERNAME_MUST_NOT_BE_BLANK(HOST + 98051, "模板格式错误：第{}行 SSH用户名不能为空。"), 

    UPLOAD_PASSWORD_MUST_NOT_BE_BLANK(HOST + 98061, "模板格式错误：第{}行 SSH密码不能为空。"), 

    UPLOAD_ROOM_MUST_NOT_BE_BLANK(HOST + 98071, "模板格式错误：第{}行 机房不能为空。"), 

    UPLOAD_SEAT_MUST_NOT_BE_BLANK(HOST + 98081, "模板格式错误：第{}行 机位不能为空。"),

    UPLOAD_HDD_SSD_SAN_NOT_ALL_BLANK(HOST + 98091, "模板格式错误：第{}行 HDD设备、SSD设备、SAN存储至少输入其中一项。"), 
    UPLOAD_SAN_NAME_NOT_EXIST(HOST + 98092, "模板格式错误：第{}行 SAN存储不存在。"), 

    UPLOAD_MAX_CONTAINER_CNT_MUST_NOT_BE_NULL(HOST + 98100, "模板格式错误：第{}行 容器上限不能为空。"),
    UPLOAD_MAX_CONTAINER_CNT_FORMAT_ERROR(HOST + 98101,  "模板格式错误：第{}行 容器上限格式错误。"),
    UPLOAD_MAX_CONTAINER_CNT_BELOW_LIMIT(HOST + 98102, "模板格式错误：第{}行 容器上限必须大于{}。"),

    MONITORREGISTER_ILLEGAL_IP_MUST_NOT_BE_EMPTY(HOST + 98120, "主机IP不能为空。"),
    MONITORREGISTER_ILLEGAL_NAME_MUST_NOT_BE_EMPTY(HOST + 98121, "主机ID不能为空。"),
    MONITORREGISTER_ILLEGAL_IP_FORMAT_ERROR(HOST + 98127, "该主机IP({})格式错误。"),
    MONITORREGISTER_ILLEGAL_SSHPORT_MUST_NOT_BE_EMPTY(HOST + 98122, "主机ssh端口号不能为空。"),
    MONITORREGISTER_ILLEGAL_OSUSER_MUST_NOT_BE_EMPTY(HOST + 98123, "主机用户名不能为空。"),
    MONITORREGISTER_ILLEGAL_OSPWD_MUST_NOT_BE_EMPTY(HOST + 98124, "主机密码不能为空。"),
    MONITORREGISTER_ILLEGAL_CHECKTYPE_MUST_NOT_BE_EMPTY(HOST + 98125, "主机检查类型不能为空。"),
    MONITORREGISTER_ILLEGAL_TAG_MUST_NOT_BE_EMPTY(HOST + 98126, "主机标签不能为空。"),
    
    MONITORCANCEL_ILLEGAL_IP_MUST_NOT_BE_EMPTY(HOST + 98130, "主机IP不能为空。"),
    MONITORCANCEL_ILLEGAL_ID_MUST_NOT_BE_EMPTY(HOST + 98131, "主机ID不能为空。"),
    
    MONITOR_ILLEGAL_ID_MUST_NOT_BE_EMPTY(HOST + 98131, "主机ID不能为空。"),
    MONITOR_ILLEGAL_STARTTIMEORENDTIME_MUST_NOT_BE_EMPTY(HOST + 98131, "起始时间或结束时间不能为空。"),
    
    UPLOAD_MAX_USAGE_MUST_NOT_BE_NULL(HOST + 98110, "模板格式错误：第{}行 最大使用率不能为空。"),
    UPLOAD_MAX_USAGE_FORMAT_ERROR(HOST + 98111, "模板格式错误：第{}行 最大使用率格式错误。"),
    UPLOAD_MAX_USAGE_BELOW_LIMIT(HOST + 98112, "模板格式错误：第{}行 最大使用率必须大于{}。"),
    UPLOAD_MAX_USAGE_OVER_LIMIT(HOST + 98113, "模板格式错误：第{}行 最大使用率不能超过{}。");

    private int code;
    private String msg;

    private HostChkRsEnum(int code, String msg) {
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
