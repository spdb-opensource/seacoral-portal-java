package com.bsg.upm.dto;

import java.io.Serializable;
import java.util.Arrays;

import org.aspectj.weaver.AjAttribute.PrivilegedAttribute;

import com.bsg.upm.dto.BaseDTO.InfoDTO;

public class NetworkingDTO extends BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	/**
	 * 站点
	 */
	private SiteDTO site;

	/**
	 * 起始ip
	 */
	private String startIp;

	/**
	 * 结束ip
	 */
	private String endIp;

	/**
	 * 网关
	 */
	private String gateway;

	/**
	 * 掩码
	 */
	private Integer mask;

	/**
	 * ip
	 */
	private IPDTO ip;

	/**
	 * 标记vlanID
	 */
	private Integer vlanId;

	/**
	 * 拓扑
	 */
	private String[] topologies;

	/**
	 * 集群
	 */
	private ClusterDTO cluster;

	/**
	 * 启用/停用，根据不同值显示不同图片
	 */
	private Boolean enabled;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 创建
	 */
	private InfoDTO created;

	/**
	 * 变更
	 */
	private InfoDTO modified;
	
	/**
	 * mgmpath,管理端地址
	 */
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SiteDTO getSite() {
		return site;
	}

	public void setSite(SiteDTO site) {
		this.site = site;
	}

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

	public Integer getMask() {
		return mask;
	}

	public void setMask(Integer mask) {
		this.mask = mask;
	}

	public IPDTO getIp() {
		return ip;
	}

	public void setIp(IPDTO ip) {
		this.ip = ip;
	}

	public Integer getVlanId() {
		return vlanId;
	}

	public void setVlanId(Integer vlanId) {
		this.vlanId = vlanId;
	}

	public String[] getTopologies() {
		return topologies;
	}

	public void setTopologies(String[] topologies) {
		this.topologies = topologies;
	}

	public ClusterDTO getCluster() {
		return cluster;
	}

	public void setCluster(ClusterDTO cluster) {
		this.cluster = cluster;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public InfoDTO getCreated() {
		return created;
	}

	public void setCreated(InfoDTO created) {
		this.created = created;
	}

	public InfoDTO getModified() {
		return modified;
	}

	public void setModified(InfoDTO modified) {
		this.modified = modified;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NetworkingDTO [id=" + id + ", site=" + site + ", startIp=" + startIp + ", endIp=" + endIp + ", gateway="
				+ gateway + ", mask=" + mask + ", vlanId=" + vlanId + ", topologies=" + Arrays.toString(topologies) + ", cluster="
				+ cluster + ", enabled=" + enabled + ", description=" + description + ", created=" + created
				+ ", modified=" + modified + "]";
	}

	public class SiteDTO {

		/**
		 * 站点编码
		 */
		private String id;

		/**
		 * 站点名称
		 */
		private String name;

		/**
		 * 获取 站点编码
		 * 
		 * @return id
		 */
		public String getId() {
			return id;
		}

		/**
		 * 设置站点编码
		 * 
		 * @param id
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * 获取 站点名称
		 * 
		 * @return name
		 */
		public String getName() {
			return name;
		}

		/**
		 * 设置站点名称
		 * 
		 * @param name
		 */
		public void setName(String name) {
			this.name = name;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "SiteDTO [id=" + id + ", name=" + name + "]";
		}

	}

	public class IPDTO {
		private Integer total;
		private Integer usedCnt;

		public Integer getTotal() {
			return total;
		}

		public void setTotal(Integer total) {
			this.total = total;
		}

		public Integer getUsedCnt() {
			return usedCnt;
		}

		public void setUsedCnt(Integer usedCnt) {
			this.usedCnt = usedCnt;
		}

		@Override
		public String toString() {
			return "IP [total=" + total + ", usedCnt=" + usedCnt + "]";
		}

	}

	public class ClusterDTO {
		private String id;
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

}
