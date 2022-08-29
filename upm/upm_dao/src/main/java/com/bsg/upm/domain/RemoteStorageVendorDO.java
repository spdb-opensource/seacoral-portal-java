package com.bsg.upm.domain;

import java.io.Serializable;

public class RemoteStorageVendorDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 代码
	 */
	private String code;

	/**
	 * 版本
	 */
	private String version;

	/**
	 * 获取
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取名称
	 * 
	 * @return name 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取代码
	 * 
	 * @return code 代码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置代码
	 * 
	 * @param code
	 *            代码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取版本
	 * 
	 * @return version 版本
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * 设置版本
	 * 
	 * @param version
	 *            版本
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SanVendorDO [id=" + id + ", name=" + name + ", code=" + code + ", version=" + version + "]";
	}

}
