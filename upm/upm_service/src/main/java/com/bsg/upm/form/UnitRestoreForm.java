package com.bsg.upm.form;

import java.io.Serializable;

public class UnitRestoreForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String backupFileId;

    public String getBackupFileId() {
        return backupFileId;
    }

    public void setBackupFileId(String backupFileId) {
        this.backupFileId = backupFileId;
    }

    @Override
    public String toString() {
        return "UnitRestoreForm [backupFileId=" + backupFileId + "]";
    }

}
