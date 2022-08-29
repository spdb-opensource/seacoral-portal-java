package com.bsg.upm.query;

import java.io.Serializable;

import javax.print.DocFlavor.STRING;

public class BackupFileParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String siteId;
    private String servGroupId;
    private Boolean valid;
    private String status;
    private String appId;
    private String unitId;
    private String backupFileId;
    private String strategyId;// 备份策略ID
    private Boolean enabled;
    private String retention;
    private Boolean userName;
    private String endpointId;

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getServGroupId() {
        return servGroupId;
    }

    public void setServGroupId(String servGroupId) {
        this.servGroupId = servGroupId;
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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getBackupFileId() {
        return backupFileId;
    }

    public void setBackupFileId(String backupFileId) {
        this.backupFileId = backupFileId;
    }

    public String getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getRetention() {
        return retention;
    }

    public void setRetention(String retention) {
        this.retention = retention;
    }

    /**
     * @return the userName
     */
    public Boolean getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(Boolean userName) {
        this.userName = userName;
    }

    public String getEndpointId() {
		return endpointId;
	}

	public void setEndpointId(String endpointId) {
		this.endpointId = endpointId;
	}

	@Override
	public String toString() {
		return "BackupFileParam [siteId=" + siteId + ", servGroupId=" + servGroupId + ", valid=" + valid + ", status="
				+ status + ", appId=" + appId + ", unitId=" + unitId + ", backupFileId=" + backupFileId
				+ ", strategyId=" + strategyId + ", enabled=" + enabled + ", retention=" + retention + ", userName="
				+ userName + ", endpointId=" + endpointId + "]";
	}

}