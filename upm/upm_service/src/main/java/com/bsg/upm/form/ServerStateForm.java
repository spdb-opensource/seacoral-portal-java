package com.bsg.upm.form;

import java.io.Serializable;
import java.util.List;

/**
 * 服务组升级form实体
 * 
 * @author yeht
 *
 */
public class ServerStateForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String databaseState;
	private String cmhaState;
	private String proxyState;
	private String state;
	public String getDatabaseState() {
		return databaseState;
	}
	public void setDatabaseState(String databaseState) {
		this.databaseState = databaseState;
	}
	public String getCmhaState() {
		return cmhaState;
	}
	public void setCmhaState(String cmhaState) {
		this.cmhaState = cmhaState;
	}
	public String getProxyState() {
		return proxyState;
	}
	public void setProxyState(String proxyState) {
		this.proxyState = proxyState;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "ServerStateForm [databaseState=" + databaseState + ", cmhaState=" + cmhaState + ", proxyState="
				+ proxyState + ", state=" + state + "]";
	}
	

}
