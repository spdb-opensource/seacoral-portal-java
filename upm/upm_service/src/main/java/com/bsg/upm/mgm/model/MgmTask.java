package com.bsg.upm.mgm.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsg.upm.mgm.model.MgmCluster.Site;

public class MgmTask {

	 /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JSONField(name = "id")
    private String id;

    @JSONField(name = "created_user")
    private String createdUser;

    @JSONField(name = "action")
    private String action;

    @JSONField(name = "relate_id")
    private String relateId;
    
    @JSONField(name = "status")
    private String status;

    @JSONField(name = "error")
    private String error;

    @JSONField(name = "created_at")
    private String createdAt;

    @JSONField(name = "finished_at")
    private String finishedAt;

	public String getId() {
		return id;
	}
	

	public void setId(String id) {
		this.id = id;
	}
	

	public String getCreatedUser() {
		return createdUser;
	}
	

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	

	public String getAction() {
		return action;
	}
	

	public void setAction(String action) {
		this.action = action;
	}
	

	public String getRelateId() {
		return relateId;
	}
	

	public void setRelateId(String relateId) {
		this.relateId = relateId;
	}
	

	public String getStatus() {
		return status;
	}
	

	public void setStatus(String status) {
		this.status = status;
	}
	

	public String getError() {
		return error;
	}
	

	public void setError(String error) {
		this.error = error;
	}
	

	public String getCreatedAt() {
		return createdAt;
	}
	

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	

	public String getFinishedAt() {
		return finishedAt;
	}
	

	public void setFinishedAt(String finishedAt) {
		this.finishedAt = finishedAt;
	}
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "MgmTask [id=" + id + ", createdUser=" + createdUser + ", action=" + action + ", relateId=" + relateId
				+ ", status=" + status + ", error=" + error + ", createdAt=" + createdAt + ", finishedAt=" + finishedAt
				+ "]";
	}
	
    
    
}
