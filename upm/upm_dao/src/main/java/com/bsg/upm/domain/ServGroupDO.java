package com.bsg.upm.domain;

import java.io.Serializable;

public class ServGroupDO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String relateId;
	
	private String businessSubsystemId;

	public String getId() {
		return id;
	}
	

	public void setId(String id) {
		this.id = id;
	}
	

	public String getRelateId() {
		return relateId;
	}
	

	public void setRelateId(String relateId) {
		this.relateId = relateId;
	}
	

	public String getBusinessSubsystemId() {
		return businessSubsystemId;
	}
	

	public void setBusinessSubsystemId(String businessSubsystemId) {
		this.businessSubsystemId = businessSubsystemId;
	}


	@Override
	public String toString() {
		return "ServGroup [id=" + id + ", relateId=" + relateId + ", businessSubsystemId=" + businessSubsystemId + "]";
	}
	
	
	
}
