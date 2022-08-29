package com.bsg.upm.mgm.body;

import java.io.Serializable;
import java.util.Arrays;

import com.alibaba.fastjson.annotation.JSONField;

public class MgmNetworkingBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "site_id")
	private String siteId;

	@JSONField(name = "cluster_id")
	private String clusterId;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "topology")
	private String[] topology;

	@JSONField(name = "ip_summary")
	private IP ipSummary;

	@JSONField(name = "enabled")
	private Boolean enabled;

	@JSONField(name = "desc")
	private String desc;

	@JSONField(name = "created_user")
	private String createdUser;

	@JSONField(name = "modified_user")
	private String modifiedUser;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getTopology() {
		return topology;
	}

	public void setTopology(String[] topology) {
		this.topology = topology;
	}

	public IP getIpSummary() {
		return ipSummary;
	}

	public void setIpSummary(IP ipSummary) {
		this.ipSummary = ipSummary;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	@Override
	public String toString() {
		return "MgmNetworkingBody [siteId=" + siteId + ", clusterId=" + clusterId + ", name=" + name + ", topology="
				+ Arrays.toString(topology) + ", enabled=" + enabled + ", desc=" + desc + ", createdUser=" + createdUser
				+ ", modifiedUser=" + modifiedUser + "]";
	}

	public static class IP {

		@JSONField(name = "start_ip")
		private String startIp;

		@JSONField(name = "end_ip")
		private String endIp;

		@JSONField(name = "gateway")
		private String gateway;

		@JSONField(name = "prefix")
		private Integer prefix;

		@JSONField(name = "vlan")
		private Integer vlan;

		public String getStartIp() {
			return startIp;
		}

		public void setStartIp(String startIp) {
			this.startIp = startIp;
		}

		public String getEndIp() {
			return endIp;
		}

		public void setEndIp(String endIp) {
			this.endIp = endIp;
		}

		public String getGateway() {
			return gateway;
		}

		public void setGateway(String gateway) {
			this.gateway = gateway;
		}

		public Integer getPrefix() {
			return prefix;
		}

		public void setPrefix(Integer prefix) {
			this.prefix = prefix;
		}

		public Integer getVlan() {
			return vlan;
		}

		public void setVlan(Integer vlan) {
			this.vlan = vlan;
		}

		@Override
		public String toString() {
			return "IP [startIp=" + startIp + ", endIp=" + endIp + ", gateway=" + gateway + ", prefix=" + prefix
					+ ", vlan=" + vlan + "]";
		}

	}

}
