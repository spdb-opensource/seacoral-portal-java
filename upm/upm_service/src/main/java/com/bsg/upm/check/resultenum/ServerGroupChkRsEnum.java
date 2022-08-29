package com.bsg.upm.check.resultenum;

public enum ServerGroupChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_ID_NOT_EXIST(SERVERGROUP, "该服务组(id:{})不存在。"),

    ADD_ILLEGAL_TYPE_MUST_NOT_BE_BLANK(SERVERGROUP + 11011, "服务类型不能为空。"),
    ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK(SERVERGROUP + 11011, "服务名称不能为空。"),
    ADD_ILLEGAL_UNIT_ID_MUST_NOT_BE_BLANK(SERVERGROUP + 11011, "单元ID不能为空。"),
    ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK(SERVERGROUP + 11020, "服务类型不能为空。"),
    ADD_ILLEGAL_DATABASE_NAME_MUST_NOT_BE_BLANK(SERVERGROUP + 11020, "库名称不能为空。"),
    ADD_ILLEGAL_USERNAME_MUST_NOT_BE_BLANK(SERVERGROUP + 11020, "用户名不能为空。"),
    ADD_ILLEGAL_IP_MUST_NOT_BE_BLANK(SERVERGROUP + 11020, "ip不能为空。"),
    ADD_ILLEGAL_ORDER_ID_MUST_NOT_BE_BLANK(SERVERGROUP + 11020, "订单组ID不能为空。"),
    ADD_ILLEGAL_TASK_ID_MUST_NOT_BE_BLANK(SERVERGROUP + 11020, "任务ID不能为空。"),
    ADD_ILLEGAL_IMAGEID_MUST_NOT_BE_BLANK(SERVERGROUP + 11019, "镜像版本不能为空。"),
	ADD_ILLEGAL_MAJOR_VERSION_MUST_NOT_BE_BLANK(SERVERGROUP + 11012, "主版本不能为空。"),
	ADD_ILLEGAL_MIN_VERSION_MUST_NOT_BE_BLANK(SERVERGROUP + 11013, "次版本不能为空。"),
	ADD_ILLEGAL_PATCH_VERSION_MUST_NOT_BE_BLANK(SERVERGROUP + 11014, "修订版本不能为空。"),
	ADD_ILLEGAL_BUILD_VERSION_MUST_NOT_BE_BLANK(SERVERGROUP + 11015, "编译版本不能为空。"),
	ADD_ILLEGAL_STORAGE_REMOTE_ID_MUST_NOT_BE_BLANK(SERVERGROUP + 11016, "外置存储编码不能为空。"),
	ADD_ILLEGAL_BACKUP_TYPE_MUST_NOT_BE_BLANK(SERVERGROUP + 11017, "备份类型不能为空。"),
	REMOVE_ILLEGAL_WITH_ENABLED(SERVERGROUP + 99002, "该服务组({})已启用，无法删除。"),
	REMOVE_ILLEGAL_WITH_ORDERGROUP_EXIST(SERVERGROUP + 11019,  "该申请单已经存在，请前往申请单页面查看。"),
	ADD_ILLEGAL_RETENTION_MUST_NOT_BE_BLANK(SERVERGROUP + 11018, "备份文件保留天数不能为空。");


    private int code;
    private String msg;

    private ServerGroupChkRsEnum(int code, String msg) {
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
