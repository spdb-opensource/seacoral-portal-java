package com.bsg.upm.mgm.query;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author swn
 * @date 2019年8月13日
 */
public class MgmBackupFileQuery implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JSONField(name = "id")
    private String id;

    @JSONField(name = "unit_id")
    private String unitId;

    @JSONField(name = "app_id")
    private String appId;

    @JSONField(name = "site_id")
    private String siteId;

    @JSONField(name = "valid")
    private Boolean valid;

    @JSONField(name = "status")
    private String status;

    @JSONField(name = "backup_file_id")
    private String backupFileId;

    @JSONField(name = "created_user")
    private String createdUser;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBackupFileId() {
        return backupFileId;
    }

    public void setBackupFileId(String backupFileId) {
        this.backupFileId = backupFileId;
    }

    /**
     * @return the createdUser
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * @param createdUser
     *            the createdUser to set
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MgmBackupFileQuery [id=" + id + ", unitId=" + unitId + ", appId=" + appId + ", siteId=" + siteId
                + ", valid=" + valid + ", status=" + status + ", backupFileId=" + backupFileId + ", createdUser="
                + createdUser + "]";
    }

}
