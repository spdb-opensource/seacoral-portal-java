package com.bsg.upm.mgm.body;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月5日
 */
public class MgmHostBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(name = "cluster_id")
	private String clusterId;
	
	@JSONField(name = "role")
	private String role;

	@JSONField(name = "ssh")
	private Ssh ssh;

	@JSONField(name = "room")
	private String room;

	@JSONField(name = "seat")
	private String seat;

	@JSONField(name = "ntp_server")
	private String ntpServer;

	@JSONField(name = "storage_host")
	private List<StorageHost> storageHost;

	@JSONField(name = "storage_remote_id")
	private String storageRemoteId;

	@JSONField(name = "max_unit")
	private Integer maxUnit;

	@JSONField(name = "max_usage")
	private MaxUsage maxUsage;

	@JSONField(name = "enabled")
	private Boolean enabled;

	@JSONField(name = "desc")
	private String desc;

	@JSONField(name = "created_user")
	private String createdUser;

	@JSONField(name = "modified_user")
	private String modifiedUser;

	/**
	 * @return the clusterId
	 */
	public String getClusterId() {
		return clusterId;
	}

	/**
	 * @param clusterId
	 *            the clusterId to set
	 */
	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the ssh
	 */
	public Ssh getSsh() {
		return ssh;
	}

	/**
	 * @param ssh
	 *            the ssh to set
	 */
	public void setSsh(Ssh ssh) {
		this.ssh = ssh;
	}

	/**
	 * @return the room
	 */
	public String getRoom() {
		return room;
	}

	/**
	 * @param room
	 *            the room to set
	 */
	public void setRoom(String room) {
		this.room = room;
	}

	/**
	 * @return the seat
	 */
	public String getSeat() {
		return seat;
	}

	/**
	 * @param seat
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
	 * @return the storageHost
	 */
	public List<StorageHost> getStorageHost() {
		return storageHost;
	}

	/**
	 * @param storageHost
	 *            the storageHost to set
	 */
	public void setStorageHost(List<StorageHost> storageHost) {
		this.storageHost = storageHost;
	}

	/**
	 * @return the storageRemoteId
	 */
	public String getStorageRemoteId() {
		return storageRemoteId;
	}

	/**
	 * @param storageRemoteId
	 *            the storageRemoteId to set
	 */
	public void setStorageRemoteId(String storageRemoteId) {
		this.storageRemoteId = storageRemoteId;
	}

	/**
	 * @return the maxUnit
	 */
	public Integer getMaxUnit() {
		return maxUnit;
	}

	/**
	 * @param maxUnit
	 *            the maxUnit to set
	 */
	public void setMaxUnit(Integer maxUnit) {
		this.maxUnit = maxUnit;
	}

	/**
	 * @return the maxUsage
	 */
	public MaxUsage getMaxUsage() {
		return maxUsage;
	}

	/**
	 * @param maxUsage
	 *            the maxUsage to set
	 */
	public void setMaxUsage(MaxUsage maxUsage) {
		this.maxUsage = maxUsage;
	}

	/**
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the createdUser
	 */
	public String getCreatedUser() {
		return createdUser;
	}

	/**
	 * @param createdUser
	 *            the createdUser to set
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	/**
	 * @return the modifiedUser
	 */
	public String getModifiedUser() {
		return modifiedUser;
	}

	/**
	 * @param modifiedUser
	 *            the modifiedUser to set
	 */
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	@Override
	public String toString() {
		return "MgmHostBody [clusterId=" + clusterId + ", role=" + role + ", ssh=" + ssh + ", room=" + room + ", seat="
				+ seat + ", ntpServer=" + ntpServer + ", storageHost=" + storageHost + ", storageRemoteId="
				+ storageRemoteId + ", maxUnit=" + maxUnit + ", maxUsage=" + maxUsage + ", enabled=" + enabled
				+ ", desc=" + desc + ", createdUser=" + createdUser + ", modifiedUser=" + modifiedUser + "]";
	}

}
