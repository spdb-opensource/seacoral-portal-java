package com.bsg.upm.check.resultenum;

public enum ServBackupFileChkRsEnum implements ChkRsInterface {

    SUCCESS(OK, "OK"), 
    
    REMOVE_ILLEGAL_SITE_ID_NOT_EXIST(SERV_BACKUP_FILE, "该站点(id:{})不存在，无法删除。");

    private int code;
    private String msg;

    private ServBackupFileChkRsEnum(int code, String msg) {
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
