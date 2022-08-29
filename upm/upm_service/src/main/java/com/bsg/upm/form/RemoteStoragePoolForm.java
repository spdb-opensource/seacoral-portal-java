package com.bsg.upm.form;

import java.io.Serializable;

public class RemoteStoragePoolForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * RG代码
	 */
	private String code;

	private Boolean enabled;

	private String performance;

	private String description;

	/**
	 * 获取RG代码
	 * 
	 * @return code RG代码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置RG代码
	 * 
	 * @param code
	 *            RG代码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/*public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}*/

	
	public String getPerformance() {
		return performance;
	}

	public Boolean getEnabled() {
		return enabled;
	}
	

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "RemoteStoragePoolForm [code=" + code + ", enabled=" + enabled + ", performance=" + performance
				+ ", description=" + description + "]";
	}

}
