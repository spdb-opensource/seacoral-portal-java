package com.bsg.upm.dto;

import java.io.Serializable;

/**
 * 权限数据传输对象
 * 
 * @author ZhuXH
 *
 */
public class AppDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Long id;

	/**
	 * 权限名称
	 */
	private String name;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 编码
	 */
	private String code;

	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 显示顺序
	 */
	private Integer sequence;

	/**
	 * 父id
	 */
	private Long pid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AppDTO [id=" + id + ",name=" + name + ",type=" + type + ",code=" + code + ",icon=" + icon + ",sequence="
				+ sequence + ",pid=" + pid + "]";
	}
}
