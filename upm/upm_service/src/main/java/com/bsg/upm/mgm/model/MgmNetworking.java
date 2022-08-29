package com.bsg.upm.mgm.model;

import java.io.Serializable;
import java.util.Arrays;

import com.alibaba.fastjson.annotation.JSONField;

public class MgmNetworking extends MgmModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "id")
	private String id;

	@JSONField(name = "name")
	private String name;

	@JSONField(name = "site")
	private Site site;

	@JSONField(name = "cluster")
	private Cluster cluster;

	@JSONField(name = "topology")
	private String[] topology;

	@JSONField(name = "ip_summary")
	private IP ipSummary;

	@JSONField(name = "enabled")
	private Boolean enabled;

	@JSONField(name = "desc")
	private String desc;

	@JSONField(name = "created")
	private Info created;

	@JSONField(name = "modified")
	private Info modified;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Cluster getCluster() {
		return cluster;
	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
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

	public Info getCreated() {
		return created;
	}

	public void setCreated(Info created) {
		this.created = created;
	}

	public Info getModified() {
		return modified;
	}

	public void setModified(Info modified) {
		this.modified = modified;
	}

	@Override
	public String toString() {
		return "MgmNetworking [id=" + id + ", name=" + name + ", topology=" + Arrays.toString(topology) + ", enabled="
				+ enabled + ", desc=" + desc + ", created=" + created + ", modified=" + modified + "]";
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

		@JSONField(name = "total")
		private Integer total;

		@JSONField(name = "used")
		private Integer used;

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

		public Integer getTotal() {
			return total;
		}

		public void setTotal(Integer total) {
			this.total = total;
		}

		public Integer getUsed() {
			return used;
		}

		public void setUsed(Integer used) {
			this.used = used;
		}

		@Override
		public String toString() {
			return "IP [startIp=" + startIp + ", endIp=" + endIp + ", gateway=" + gateway + ", prefix=" + prefix
					+ ", vlan=" + vlan + ", total=" + total + ", used=" + used + "]";
		}

	}

	public static class Cluster {

		@JSONField(name = "id")
		private String id;

		@JSONField(name = "name")
		private String name;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Cluster [id=" + id + ", name=" + name + "]";
		}

	}

	public static class Site {

		@JSONField(name = "id")
		private String id;

		@JSONField(name = "name")
		private String name;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Site [id=" + id + ", name=" + name + "]";
		}

	}
}
