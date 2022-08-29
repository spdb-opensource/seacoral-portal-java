package com.bsg.upm.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月6日
 */
public class HostForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 集群编码
	 */
	private String clusterId;
	
	/**
	 * 站点id
	 */
	private String siteId;

	public String getSiteId() {
		return siteId;
	}
	

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	

	/**
	 * 主机IP
	 */
	private String ip;

	/**
	 * SSH端口
	 */
	private Integer sshPort;

	/**
	 * SSH用户名
	 */
	private String sshUsername;

	/**
	 * SSH密码
	 */
	private String sshPassword;

	/**
	 * 机房
	 */
	private String room;

	/**
	 * 机位
	 */
	private String seat;

	/**
	 * NTP服务地址
	 */
	private String ntpServer;
	
	private Boolean enabled;

	/**
	 * 存储设备
	 */
	private List<StorageForm> storage = new ArrayList<>();

	/**
	 * 外置存储编码
	 */
	private String storageRemoteId;

	/**
	 * 最大单元数量
	 */
	private Integer maxUnitCnt;

	/**
	 * 最大使用率
	 */
	private MaxUsage maxUsage;

	/**
	 * 描述
	 */
	private String description;
	
	/**
     * 硬件架构
     */
    private String arch;
    
    /**
     * 主机角色(是否可被迁移）
     * dbscale-spare  dbscale-node
     */
    private String role;
    

	public String getArch() {
		return arch;
	}


	public void setArch(String arch) {
		this.arch = arch;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	/**
	 * @return the 集群编码
	 */
	public String getClusterId() {
		return clusterId;
	}

	/**
	 * @param 集群编码
	 *            the clusterId to set
	 */
	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	/**
	 * @return the 主机IP
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param 主机IP
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the SSH用户名
	 */
	public String getSshUsername() {
		return sshUsername;
	}

	/**
	 * @param SSH用户名
	 *            the sshUsername to set
	 */
	public void setSshUsername(String sshUsername) {
		this.sshUsername = sshUsername;
	}

	/**
	 * @return the SSH密码
	 */
	public String getSshPassword() {
		return sshPassword;
	}

	/**
	 * @param SSH密码
	 *            the sshPassword to set
	 */
	public void setSshPassword(String sshPassword) {
		this.sshPassword = sshPassword;
	}

	/**
	 * @return the SSH端口
	 */
	public Integer getSshPort() {
		return sshPort;
	}

	/**
	 * @param SSH端口
	 *            the sshPort to set
	 */
	public void setSshPort(Integer sshPort) {
		this.sshPort = sshPort;
	}

	/**
	 * @return the 机房
	 */
	public String getRoom() {
		return room;
	}

	/**
	 * @param 机房
	 *            the room to set
	 */
	public void setRoom(String room) {
		this.room = room;
	}

	/**
	 * @return the 机位
	 */
	public String getSeat() {
		return seat;
	}

	/**
	 * @param 机位
	 *            the seat to set
	 */
	public void setSeat(String seat) {
		this.seat = seat;
	}

	public String getNtpServer() {
		return ntpServer;
	}

	public void setNtpServer(String ntpServer) {
		this.ntpServer = ntpServer;
	}

	/**
	 * @return the 存储设备
	 */
	public List<StorageForm> getStorage() {
		return storage;
	}

	/**
	 * @param 存储设备
	 *            the storage to set
	 */
	public void setStorage(List<StorageForm> storage) {
		this.storage = storage;
	}

	/**
	 * @return the 外置存储编码
	 */
	public String getStorageRemoteId() {
		return storageRemoteId;
	}

	/**
	 * @param 外置存储编码
	 *            the storageRemoteId to set
	 */
	public void setStorageRemoteId(String storageRemoteId) {
		this.storageRemoteId = storageRemoteId;
	}

	/**
	 * @return the 最大单元数量
	 */
	public Integer getMaxUnitCnt() {
		return maxUnitCnt;
	}

	/**
	 * @param 最大单元数量
	 *            the maxUnitCnt to set
	 */
	public void setMaxUnitCnt(Integer maxUnitCnt) {
		this.maxUnitCnt = maxUnitCnt;
	}

	/**
	 * @return the 最大使用率
	 */
	public MaxUsage getMaxUsage() {
		return maxUsage;
	}

	/**
	 * @param 最大使用率
	 *            the maxUsage to set
	 */
	public void setMaxUsage(MaxUsage maxUsage) {
		this.maxUsage = maxUsage;
	}

	/**
	 * @return the 描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param 描述
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	@Override
	public String toString() {
		return "HostForm [clusterId=" + clusterId + ", siteId=" + siteId + ", ip=" + ip + ", sshPort=" + sshPort
				+ ", sshUsername=" + sshUsername + ", sshPassword=" + sshPassword + ", room=" + room + ", seat=" + seat
				+ ", ntpServer=" + ntpServer + ", enabled=" + enabled + ", storage=" + storage + ", storageRemoteId="
				+ storageRemoteId + ", maxUnitCnt=" + maxUnitCnt + ", maxUsage=" + maxUsage + ", description="
				+ description + ", arch=" + arch + ", role=" + role + "]";
	}

	

}
