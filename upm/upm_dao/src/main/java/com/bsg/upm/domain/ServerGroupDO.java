package com.bsg.upm.domain;

import java.io.Serializable;
import java.util.Date;

public class ServerGroupDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String area;

	private String owner;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "ServerGroupDO [id=" + id + ", area=" + area + ", owner=" + owner + "]";
	}

}
