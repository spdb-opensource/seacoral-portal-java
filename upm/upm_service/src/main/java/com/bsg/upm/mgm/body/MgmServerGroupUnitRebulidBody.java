package com.bsg.upm.mgm.body;

import java.io.Serializable;
import java.util.Arrays;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author swn
 * @date 2019年8月12日
 */
public class MgmServerGroupUnitRebulidBody implements Serializable {

	private static final long serialVersionUID = 1L;

	@JSONField(name = "node")
	private String node;

	public String getNode() {
		return node;
	}
	
	public void setNode(String node) {
		this.node = node;
	}

	@Override
	public String toString() {
		return "MgmServerGroupUnitRebulidBody [node=" + node + "]";
	}
	
	
}
