package com.bsg.upm.mgm.query;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author swn
 * @date 2019年7月11日
 */
public class MgmServerGroupQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "id")
	private String id;
	
	@JSONField(name = "subscription_id")
	private String subscription_id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubscription_id() {
		return subscription_id;
	}

	public void setSubscription_id(String subscription_id) {
		this.subscription_id = subscription_id;
	}

	@Override
	public String toString() {
		return "MgmServerGroupQuery [name=" + name + ", id=" + id + ", subscription_id=" + subscription_id + "]";
	}

}
