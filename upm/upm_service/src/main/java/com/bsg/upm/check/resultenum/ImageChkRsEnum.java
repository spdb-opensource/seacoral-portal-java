package com.bsg.upm.check.resultenum;

public enum ImageChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"),

    ILLEGAL_ID_NOT_EXIST(SOFTWARE_IMAGE, "该软件镜像(id:{})不存在。"),

    ADD_ILLEGAL_SITE_ID_MUST_NOT_BE_NULL(SOFTWARE_IMAGE + 11011, "站点不能为空。"), 
    ADD_ILLEGAL_SITE_ID_NOT_EXIST(SOFTWARE_IMAGE + 11012, "该站点(id:{})不存在。"),

    ADD_ILLEGAL_TYPE_MUST_NOT_BE_BLANK(SOFTWARE_IMAGE + 11021, "软件类型不能为空。"), 
    ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK(SOFTWARE_IMAGE + 11021, "软件ID不能为空。"), 
    ADD_ILLEGAL_TYPE_NOT_EXIST(SOFTWARE_IMAGE + 11022, "该软件类型({})不存在。"),

    ADD_ILLEGAL_MAJOR_VERSION_MUST_NOT_BE_NULL(SOFTWARE_IMAGE + 11031, "主版本不能为空。"), 

    ADD_ILLEGAL_MINOR_VERSION_MUST_NOT_BE_NULL(SOFTWARE_IMAGE + 11041, "次版本不能为空。"), 

    ADD_ILLEGAL_PATCH_VERSION_MUST_NOT_BE_NULL(SOFTWARE_IMAGE + 11051, "修订版本不能为空。"), 

    ADD_ILLEGAL_BUILD_VERSION_MUST_NOT_BE_NULL(SOFTWARE_IMAGE + 11061, "编译版本不能为空。"), 
    ADD_ILLEGAL_VERSION_EXIST(SOFTWARE_IMAGE + 11062, "该版本({})已存在。"),

    ADD_ILLEGAL_PATH_MUST_NOT_BE_BLANK(SOFTWARE_IMAGE + 11071, "镜像路径不能为空。"), 


    ENABLED_ILLEGAL_BECAUSE_OUT(SOFTWARE_IMAGE + 15001, "该软件镜像({})未入库或未入库成功，不能启用。"),

    DISABLED_ILLEGAL_BECAUSE_OUT(SOFTWARE_IMAGE + 16001, "该软件镜像({})未入库或未入库成功，不能启用。"),

    RELEASE_ILLEGAL_BECAUSE_OUT(SOFTWARE_IMAGE + 17001, "该软件镜像({})未入库或未入库成功，不能发布。"),
    
    REMOVE_ILLEGAL_ASSOCIATED_WITH_SERV_GROUP(SOFTWARE_IMAGE + 99001, "该软件镜像({})已关联服务组，无法删除。"),
    
    REMOVE_ILLEGAL_ASSOCIATED_WITH_ENABLED(SOFTWARE_IMAGE + 99002, "该软件镜像({})已启用，无法删除。"),

    UPDATE_TEMPLATE_ILLEGAL_BECAUSE_OUT(IMAGE_TEMPLATE + 12001, "该软件镜像({})未入库或未入库成功，不能编辑模板配置。");
    
    private int code;
    private String msg;

    private ImageChkRsEnum(int code, String msg) {
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
