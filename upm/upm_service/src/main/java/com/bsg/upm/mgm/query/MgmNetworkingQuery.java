package com.bsg.upm.mgm.query;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author swn
 * @date 2019年7月11日
 */
public class MgmNetworkingQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "id")
	private String id;

	@JSONField(name = "site_id")
	private String siteId;

	@JSONField(name = "cluster_id")
	private String clusterId;

	@JSONField(name = "topology")
	private String topology;
	
	@JSONField(name = "enabled")
	private Boolean enabled;

	public Boolean getEnabled() {
		return enabled;
	}
	

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	

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

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getClusterId() {
		return clusterId;
	}

	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	public String getTopology() {
		return topology;
	}

	public void setTopology(String topology) {
		this.topology = topology;
	}


	@Override
	public String toString() {
		return "MgmNetworkingQuery [name=" + name + ", id=" + id + ", siteId=" + siteId + ", clusterId=" + clusterId
				+ ", topology=" + topology + ", enabled=" + enabled + "]";
	}

	

}
