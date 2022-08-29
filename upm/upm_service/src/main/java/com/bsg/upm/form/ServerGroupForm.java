package com.bsg.upm.form;

import java.io.Serializable;
import java.util.List;

/**
 * 服务组form实体
 * 
 * @author swn
 *
 */
public class ServerGroupForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String type;
	private String name;
	private String region;
	private String domain;
	private Integer port;
	private String description;

	// 扩容
	private Integer cnpCnt;
	private Long memSize;
	private Long dataDirSize;
	private Long logDirSize;
	private Integer networkBandwidth;
	private Long backupDirSize;

	// 升级
	private String imageId;//镜像版本id
	private Integer majorVersion; // 主版本
	private Integer minVersion; // 次版本
	private Integer patchVersion; // 修订版本
	private Integer buildVersion; // 编译版本

	// 备份
	private String storageType;
	private String storage;
	private Integer retention;
	//备份时间 yeht
	private String schedule;
	
	private Integer proxyCnpCnt;
	private Long proxyMemSize;
	private Long proxyDataDirSize;
	private Long proxyLogDirSize;
	private Integer proxyNetworkBandwidth;
	private Long proxyBackupDirSize;
	
	private Integer cmhaCnpCnt;
	private Long cmhaMemSize;
	private Long cmhaDataDirSize;
	private Long cmhaLogDirSize;
	private Integer cmhaNetworkBandwidth;
	private Long cmhaBackupDirSize;
	
	// 升级
	private String cmhaImageId;//cmha镜像版本id
	private String proxyImageId;//proxy镜像版本id
	private String endpointId;//备份id
	

	public String getEndpointId() {
		return endpointId;
	}

	public void setEndpointId(String endpointId) {
		this.endpointId = endpointId;
	}

	public String getCmhaImageId() {
		return cmhaImageId;
	}

	public void setCmhaImageId(String cmhaImageId) {
		this.cmhaImageId = cmhaImageId;
	}

	public String getProxyImageId() {
		return proxyImageId;
	}

	public void setProxyImageId(String proxyImageId) {
		this.proxyImageId = proxyImageId;
	}

	public String getType() {
		return type;
	}

	public String getImageId() {
		return imageId;
	}
	

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCnpCnt() {
		return cnpCnt;
	}

	public void setCnpCnt(Integer cnpCnt) {
		this.cnpCnt = cnpCnt;
	}

	public Long getMemSize() {
		return memSize;
	}

	public void setMemSize(Long memSize) {
		this.memSize = memSize;
	}

	public Long getDataDirSize() {
		return dataDirSize;
	}

	public void setDataDirSize(Long dataDirSize) {
		this.dataDirSize = dataDirSize;
	}

	public Long getLogDirSize() {
		return logDirSize;
	}

	public void setLogDirSize(Long logDirSize) {
		this.logDirSize = logDirSize;
	}

	public Integer getNetworkBandwidth() {
		return networkBandwidth;
	}

	public void setNetworkBandwidth(Integer networkBandwidth) {
		this.networkBandwidth = networkBandwidth;
	}

	public Integer getMajorVersion() {
		return majorVersion;
	}

	public void setMajorVersion(Integer majorVersion) {
		this.majorVersion = majorVersion;
	}

	public Integer getMinVersion() {
		return minVersion;
	}

	public void setMinVersion(Integer minVersion) {
		this.minVersion = minVersion;
	}

	public Integer getPatchVersion() {
		return patchVersion;
	}

	public void setPatchVersion(Integer patchVersion) {
		this.patchVersion = patchVersion;
	}

	public Integer getBuildVersion() {
		return buildVersion;
	}

	public void setBuildVersion(Integer buildVersion) {
		this.buildVersion = buildVersion;
	}

	public String getStorageType() {
		return storageType;
	}

	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}

	public Integer getRetention() {
		return retention;
	}

	public void setRetention(Integer retention) {
		this.retention = retention;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public Long getBackupDirSize() {
		return backupDirSize;
	}

	public void setBackupDirSize(Long backupDirSize) {
		this.backupDirSize = backupDirSize;
	}

	public Integer getProxyCnpCnt() {
		return proxyCnpCnt;
	}

	public void setProxyCnpCnt(Integer proxyCnpCnt) {
		this.proxyCnpCnt = proxyCnpCnt;
	}

	public Long getProxyMemSize() {
		return proxyMemSize;
	}

	public void setProxyMemSize(Long proxyMemSize) {
		this.proxyMemSize = proxyMemSize;
	}

	public Long getProxyDataDirSize() {
		return proxyDataDirSize;
	}

	public void setProxyDataDirSize(Long proxyDataDirSize) {
		this.proxyDataDirSize = proxyDataDirSize;
	}

	public Long getProxyLogDirSize() {
		return proxyLogDirSize;
	}

	public void setProxyLogDirSize(Long proxyLogDirSize) {
		this.proxyLogDirSize = proxyLogDirSize;
	}

	public Integer getProxyNetworkBandwidth() {
		return proxyNetworkBandwidth;
	}

	public void setProxyNetworkBandwidth(Integer proxyNetworkBandwidth) {
		this.proxyNetworkBandwidth = proxyNetworkBandwidth;
	}

	public Long getProxyBackupDirSize() {
		return proxyBackupDirSize;
	}

	public void setProxyBackupDirSize(Long proxyBackupDirSize) {
		this.proxyBackupDirSize = proxyBackupDirSize;
	}

	public Integer getCmhaCnpCnt() {
		return cmhaCnpCnt;
	}

	public void setCmhaCnpCnt(Integer cmhaCnpCnt) {
		this.cmhaCnpCnt = cmhaCnpCnt;
	}

	public Long getCmhaMemSize() {
		return cmhaMemSize;
	}

	public void setCmhaMemSize(Long cmhaMemSize) {
		this.cmhaMemSize = cmhaMemSize;
	}

	public Long getCmhaDataDirSize() {
		return cmhaDataDirSize;
	}

	public void setCmhaDataDirSize(Long cmhaDataDirSize) {
		this.cmhaDataDirSize = cmhaDataDirSize;
	}

	public Long getCmhaLogDirSize() {
		return cmhaLogDirSize;
	}

	public void setCmhaLogDirSize(Long cmhaLogDirSize) {
		this.cmhaLogDirSize = cmhaLogDirSize;
	}

	public Integer getCmhaNetworkBandwidth() {
		return cmhaNetworkBandwidth;
	}

	public void setCmhaNetworkBandwidth(Integer cmhaNetworkBandwidth) {
		this.cmhaNetworkBandwidth = cmhaNetworkBandwidth;
	}

	public Long getCmhaBackupDirSize() {
		return cmhaBackupDirSize;
	}

	public void setCmhaBackupDirSize(Long cmhaBackupDirSize) {
		this.cmhaBackupDirSize = cmhaBackupDirSize;
	}

	@Override
	public String toString() {
		return "ServerGroupForm [type=" + type + ", name=" + name + ", region=" + region + ", domain=" + domain
				+ ", port=" + port + ", description=" + description + ", cnpCnt=" + cnpCnt + ", memSize=" + memSize
				+ ", dataDirSize=" + dataDirSize + ", logDirSize=" + logDirSize + ", networkBandwidth="
				+ networkBandwidth + ", backupDirSize=" + backupDirSize + ", imageId=" + imageId + ", majorVersion="
				+ majorVersion + ", minVersion=" + minVersion + ", patchVersion=" + patchVersion + ", buildVersion="
				+ buildVersion + ", storageType=" + storageType + ", storage=" + storage + ", retention=" + retention
				+ ", schedule=" + schedule + ", proxyCnpCnt=" + proxyCnpCnt + ", proxyMemSize=" + proxyMemSize
				+ ", proxyDataDirSize=" + proxyDataDirSize + ", proxyLogDirSize=" + proxyLogDirSize
				+ ", proxyNetworkBandwidth=" + proxyNetworkBandwidth + ", proxyBackupDirSize=" + proxyBackupDirSize
				+ ", cmhaCnpCnt=" + cmhaCnpCnt + ", cmhaMemSize=" + cmhaMemSize + ", cmhaDataDirSize=" + cmhaDataDirSize
				+ ", cmhaLogDirSize=" + cmhaLogDirSize + ", cmhaNetworkBandwidth=" + cmhaNetworkBandwidth
				+ ", cmhaBackupDirSize=" + cmhaBackupDirSize + ", cmhaImageId=" + cmhaImageId + ", proxyImageId="
				+ proxyImageId + ", endpointId=" + endpointId + "]";
	}


}
