package com.bsg.upm.mgm.body;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class MgmDatabaseBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "character_set")
	private String characterSet;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCharacterSet() {
		return characterSet;
	}

	public void setCharacterSet(String characterSet) {
		this.characterSet = characterSet;
	}

	@Override
	public String toString() {
		return "MgmDatabaseBody [name=" + name + ", characterSet=" + characterSet + "]";
	}

}
