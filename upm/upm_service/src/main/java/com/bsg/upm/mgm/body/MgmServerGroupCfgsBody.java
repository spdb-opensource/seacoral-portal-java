package com.bsg.upm.mgm.body;

import java.io.Serializable;
import java.util.Arrays;

import com.alibaba.fastjson.annotation.JSONField;

public class MgmServerGroupCfgsBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "key")
	private String key;
	
	@JSONField(name = "value")
	private String value;

	public String getKey() {
		return key;
	}
	

	public void setKey(String key) {
		this.key = key;
	}
	

	public String getValue() {
		return value;
	}
	

	public void setValue(String value) {
		this.value = value;
	}


	@Override
	public String toString() {
		return "MgmServerGroupCfgsBody [key=" + key + ", value=" + value + "]";
	}
	

	
}
