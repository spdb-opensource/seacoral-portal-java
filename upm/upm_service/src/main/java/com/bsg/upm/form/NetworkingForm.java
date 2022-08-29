package com.bsg.upm.form;

import java.io.Serializable;
import java.util.Arrays;

public class NetworkingForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 站点编码
	 */
	private String siteId;

	/**
	 * 网段名称
	 */
	// private String name;

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
	 * 标记vlanID
	 */
	private Integer vlanId;

	/**
	 * 拓扑
	 */
	private String[] topologies;

	/**
	 * 集群编码
	 */
	private String clusterId;
	
	private Boolean enabled;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 获取网段名称
	 * 
	 * @return name 网段名称
	 */
	/*
	 * public String getName() { return name; }
	 */

	/**
	 * 设置网段名称
	 * 
	 * @param name
	 *            网段名称
	 */
	/*
	 * public void setName(String name) { this.name = name; }
	 */

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	/**
	 * 获取起始ip
	 * 
	 * @return startIp 起始ip
	 */
	public String getStartIp() {
		return startIp;
	}

	/**
	 * 设置起始ip
	 * 
	 * @param startIp
	 *            起始ip
	 */
	public void setStartIp(String startIp) {
		this.startIp = startIp;
	}

	/**
	 * 获取结束ip
	 * 
	 * @return endIp 结束ip
	 */
	public String getEndIp() {
		return endIp;
	}

	/**
	 * 设置结束ip
	 * 
	 * @param endIp
	 *            结束ip
	 */
	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}

	/**
	 * 获取网关
	 * 
	 * @return gateway 网关
	 */
	public String getGateway() {
		return gateway;
	}

	/**
	 * 设置网关
	 * 
	 * @param gateway
	 *            网关
	 */
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	/**
	 * 获取掩码
	 * 
	 * @return mask 掩码
	 */
	public Integer getMask() {
		return mask;
	}

	/**
	 * 设置掩码
	 * 
	 * @param mask
	 *            掩码
	 */
	public void setMask(Integer mask) {
		this.mask = mask;
	}

	/**
	 * 获取标记vlanID
	 * 
	 * @return vlanId 标记vlanID
	 */
	public Integer getVlanId() {
		return vlanId;
	}

	/**
	 * 设置标记vlanID
	 * 
	 * @param vlanId
	 *            标记vlanID
	 */
	public void setVlanId(Integer vlanId) {
		this.vlanId = vlanId;
	}

	public String[] getTopologies() {
		return topologies;
	}

	public void setTopologies(String[] topologies) {
		this.topologies = topologies;
	}

	public String getClusterId() {
		return clusterId;
	}

	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	/**
	 * 获取描述
	 * 
	 * @return description 描述
	 */
	public String getDescription() {
		return description;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * 设置描述
	 * 
	 * @param description
	 *            描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "NetworkingForm [siteId=" + siteId + ", startIp=" + startIp + ", endIp=" + endIp + ", gateway=" + gateway
				+ ", mask=" + mask + ", vlanId=" + vlanId + ", topologies=" + Arrays.toString(topologies)
				+ ", clusterId=" + clusterId + ", enabled=" + enabled + ", description=" + description + "]";
	}

}
