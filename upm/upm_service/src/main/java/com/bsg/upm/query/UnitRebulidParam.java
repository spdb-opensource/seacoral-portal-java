package com.bsg.upm.query;

import java.io.Serializable;

import javax.print.DocFlavor.STRING;

public class UnitRebulidParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String appId;
	private String unitId;
//	private Boolean enabled;
	private String hostId;
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
	
	public String getHostId() {
		return hostId;
	}
	
	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	@Override
	public String toString() {
		return "UnitRebulidParam [appId=" + appId + ", unitId=" + unitId + ", hostId=" + hostId + "]";
	}
	
	
}