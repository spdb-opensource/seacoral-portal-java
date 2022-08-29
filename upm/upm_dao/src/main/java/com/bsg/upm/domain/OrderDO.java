package com.bsg.upm.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author ZhuXH
 * @date 2019年7月15日
 */
public class OrderDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 工单编码
	 */
	private String id;

	/**
	 * 工单组编码
	 */
	private String orderGroupId;

	/**
	 * 工单类型
	 */
	private String type;

	/**
	 * 镜像主版本
	 */
	private Integer majorVersion;

	/**
	 * 镜像次版本
	 */
	private Integer minorVersion;

	/**
	 * 镜像修订版本
	 */
	private Integer patchVersion;

	/**
	 * 镜像编译版本
	 */
	private Integer buildVersion;

	/**
	 * 架构编码
	 */
	// private Long archId;
	private String archTypeCode;
	private Integer unitCnt;

	/**
	 * CPU数量
	 */
	private Integer cpuCnt;

	/**
	 * 内存容量
	 */
	private Long memSize;

	/**
	 * 端口
	 */
	private Integer port;

	/**
	 * 分片数量
	 */
	private Integer cnt;
	/**
	 * 数据目录大小
	 */
	private Long dataDirSize;

	/**
	 * 数据目录类型
	 */
	private String dataDirType;

	/**
	 * 数据目录性能等级
	 */
	private String dataDirPerformance;

	/**
	 * 日志目录大小
	 */
	private Long logDirSize;

	/**
	 * 日志目录类型
	 */
	private String logDirType;

	/**
	 * 日志目录性能等级
	 */
	private String logDirPerformance;
	/**
	 * 带宽
	 */
	private Integer networkBandwidth;


	/**
	 * 集群高可用
	 */
	private Boolean clusterHA;

	/**
	 * 网络高可用
	 */
	private Boolean networkHA;

	/**
	 * 主机高可用
	 */
	private Boolean hostHA;

	/**
	 * 存储高可用
	 */
	private Boolean storageHA;

	/**
	 * 字符集
	 */
	private String cfgs;
	/**
	 * 架构
	 */
	// private ArchDO Arch;

	/**
	 * 变更前主版本
	 */
	private Integer preMajorVersion;

	/**
	 * 变更前次版本
	 */
	private Integer preMinorVersion;

	/**
	 * 变更前修订版本
	 */
	private Integer prePatchVersion;

	/**
	 * 变更前编译版本
	 */
	private Integer preBuildVersion;

	/**
	 * 变更前架构编码
	 */
	// private Long preArchId;
	private String preArchTypeCode;
	private Integer preUnitCnt;

	/**
	 * 变更前CPU数量
	 */
	private Integer preCpuCnt;

	/**
	 * 变更前内存容量
	 */
	private Long preMemSize;

	/**
	 * 变更前数据目录大小
	 */
	private Long preDataDirSize;

	/**
	 * 变更前日志目录大小
	 */
	private Long preLogDirSize;


	/**
	 * 变更前分片数量
	 */
	private Integer preCnt;
	/**
	 * 变更前带宽
	 */
	private Integer preNetworkBandwidth;

	/**
	 * 变更前架构
	 */
	// private ArchDO preArch;
	
	  /**
     * 高可用方式0:none,1:container_switch,2:database_switch
     */
    private String hamode;
    
    /**
	 * schema
	 */
    private List<DbUserDO> schemas;
    
    /**
	 * 数据库用户
	 */
    private List<DbUserDO> users;
    
    
    /**
     * 备份时间
     */
    private String schedule;
    
	/**
     * 硬件架构
     */
    private String architecture;
    
    /**
     * 是否容器化漂移
     */
    private String hacontainer;
    
    /**
     * cmha
     */
    private List<CmhaProxyDO> cmha;
    
    /**
     * proxy
     */
    private List<CmhaProxyDO> proxy;
    
    private String cmhaImageId;//cmha镜像版本id
	private String proxyImageId;//proxy镜像版本id
	private String imageId;//镜像版本id

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

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public List<CmhaProxyDO> getCmha() {
		return cmha;
	}

	public void setCmha(List<CmhaProxyDO> cmha) {
		this.cmha = cmha;
	}

	public List<CmhaProxyDO> getProxy() {
		return proxy;
	}

	public void setProxy(List<CmhaProxyDO> proxy) {
		this.proxy = proxy;
	}

	/**
	 * @return the 工单编码
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the 工单组编码
	 */
	public String getOrderGroupId() {
		return orderGroupId;
	}

	/**
	 * @return the 工单类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the 镜像主版本
	 */
	public Integer getMajorVersion() {
		return majorVersion;
	}

	/**
	 * @return the 镜像次版本
	 */
	public Integer getMinorVersion() {
		return minorVersion;
	}

	/**
	 * @return the 镜像修订版本
	 */
	public Integer getPatchVersion() {
		return patchVersion;
	}

	/**
	 * @return the 镜像编译版本
	 */
	public Integer getBuildVersion() {
		return buildVersion;
	}

	/**
	 * @return the CPU数量
	 */
	public Integer getCpuCnt() {
		return cpuCnt;
	}

	/**
	 * @return the 内存容量
	 */
	public Long getMemSize() {
		return memSize;
	}

	/**
	 * @return the 端口
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @return the 数据目录大小
	 */
	public Long getDataDirSize() {
		return dataDirSize;
	}

	/**
	 * @return the 数据目录类型
	 */
	public String getDataDirType() {
		return dataDirType;
	}

	/**
	 * @return the 数据目录性能等级
	 */
	public String getDataDirPerformance() {
		return dataDirPerformance;
	}

	/**
	 * @return the 日志目录大小
	 */
	public Long getLogDirSize() {
		return logDirSize;
	}

	/**
	 * @return the 日志目录类型
	 */
	public String getLogDirType() {
		return logDirType;
	}

	/**
	 * @return the 带宽
	 */
	public Integer getNetworkBandwidth() {
		return networkBandwidth;
	}


	/**
	 * @return the 集群高可用
	 */
	public Boolean getClusterHA() {
		return clusterHA;
	}

	/**
	 * @return the 网络高可用
	 */
	public Boolean getNetworkHA() {
		return networkHA;
	}

	/**
	 * @return the 主机高可用
	 */
	public Boolean getHostHA() {
		return hostHA;
	}

	/**
	 * @return the 存储高可用
	 */
	public Boolean getStorageHA() {
		return storageHA;
	}

	/**
	 * @return the 字符集
	 */
	

	/**
	 * @return the arch
	 */
	/*
	 * public ArchDO getArch() { return Arch; }
	 */

	/**
	 * @return the 变更前主版本
	 */
	public Integer getPreMajorVersion() {
		return preMajorVersion;
	}


	public String getCfgs() {
		return cfgs;
	}
	

	public void setCfgs(String cfgs) {
		this.cfgs = cfgs;
	}
	

	/**
	 * @return the 变更前次版本
	 */
	public Integer getPreMinorVersion() {
		return preMinorVersion;
	}

	/**
	 * @return the 变更前修订版本
	 */
	public Integer getPrePatchVersion() {
		return prePatchVersion;
	}

	/**
	 * @return the 变更前编译版本
	 */
	public Integer getPreBuildVersion() {
		return preBuildVersion;
	}

	/**
	 * @return the 变更前架构编码
	 */
	/*public Long getPreArchId() {
		return preArchId;
	}*/

	/**
	 * @return the 变更前CPU数量
	 */
	public Integer getPreCpuCnt() {
		return preCpuCnt;
	}

	/**
	 * @return the 变更前内存容量
	 */
	public Long getPreMemSize() {
		return preMemSize;
	}

	/**
	 * @return the 变更前数据目录大小
	 */
	public Long getPreDataDirSize() {
		return preDataDirSize;
	}

	/**
	 * @return the 变更前日志目录大小
	 */
	public Long getPreLogDirSize() {
		return preLogDirSize;
	}

	/**
	 * @return the 变更前带宽
	 */
	public Integer getPreNetworkBandwidth() {
		return preNetworkBandwidth;
	}

	/**
	 * @return the 变更前架构
	 */
	/*
	 * public ArchDO getPreArch() { return preArch; }
	 */

	/**
	 * @param 工单编码
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param 工单组编码
	 *            the orderGroupId to set
	 */
	public void setOrderGroupId(String orderGroupId) {
		this.orderGroupId = orderGroupId;
	}

	public String getHamode() {
		return hamode;
	}

	public void setHamode(String hamode) {
		this.hamode = hamode;
	}


	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getArchitecture() {
		return architecture;
	}

	public void setArchitecture(String architecture) {
		this.architecture = architecture;
	}

	public String getHacontainer() {
		return hacontainer;
	}

	public void setHacontainer(String hacontainer) {
		this.hacontainer = hacontainer;
	}


	public List<DbUserDO> getSchemas() {
		return schemas;
	}

	public void setSchemas(List<DbUserDO> schemas) {
		this.schemas = schemas;
	}

	public List<DbUserDO> getUsers() {
		return users;
	}

	public void setUsers(List<DbUserDO> users) {
		this.users = users;
	}

	/**
	 * @param 工单类型
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param 镜像主版本
	 *            the majorVersion to set
	 */
	public void setMajorVersion(Integer majorVersion) {
		this.majorVersion = majorVersion;
	}

	/**
	 * @param 镜像次版本
	 *            the minorVersion to set
	 */
	public void setMinorVersion(Integer minorVersion) {
		this.minorVersion = minorVersion;
	}

	/**
	 * @param 镜像修订版本
	 *            the patchVersion to set
	 */
	public void setPatchVersion(Integer patchVersion) {
		this.patchVersion = patchVersion;
	}

	/**
	 * @param 镜像编译版本
	 *            the buildVersion to set
	 */
	public void setBuildVersion(Integer buildVersion) {
		this.buildVersion = buildVersion;
	}

	/**
	 * @param CPU数量
	 *            the cpuCnt to set
	 */
	public void setCpuCnt(Integer cpuCnt) {
		this.cpuCnt = cpuCnt;
	}

	/**
	 * @param 内存容量
	 *            the memSize to set
	 */
	public void setMemSize(Long memSize) {
		this.memSize = memSize;
	}

	/**
	 * @param 端口
	 *            the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @param 数据目录大小
	 *            the dataDirSize to set
	 */
	public void setDataDirSize(Long dataDirSize) {
		this.dataDirSize = dataDirSize;
	}

	/**
	 * @param 数据目录类型
	 *            the dataDirType to set
	 */
	public void setDataDirType(String dataDirType) {
		this.dataDirType = dataDirType;
	}

	/**
	 * @param 数据目录性能等级
	 *            the dataDirPerformance to set
	 */
	public void setDataDirPerformance(String dataDirPerformance) {
		this.dataDirPerformance = dataDirPerformance;
	}

	/**
	 * @param 日志目录大小
	 *            the logDirSize to set
	 */
	public void setLogDirSize(Long logDirSize) {
		this.logDirSize = logDirSize;
	}

	/**
	 * @param 日志目录类型
	 *            the logDirType to set
	 */
	public void setLogDirType(String logDirType) {
		this.logDirType = logDirType;
	}

	/**
	 * @param 带宽
	 *            the networkBandwidth to set
	 */
	public void setNetworkBandwidth(Integer networkBandwidth) {
		this.networkBandwidth = networkBandwidth;
	}




	/**
	 * @param 集群高可用
	 *            the clusterHA to set
	 */
	public void setClusterHA(Boolean clusterHA) {
		this.clusterHA = clusterHA;
	}

	/**
	 * @param 网络高可用
	 *            the networkHA to set
	 */
	public void setNetworkHA(Boolean networkHA) {
		this.networkHA = networkHA;
	}

	/**
	 * @param 主机高可用
	 *            the hostHA to set
	 */
	public void setHostHA(Boolean hostHA) {
		this.hostHA = hostHA;
	}

	/**
	 * @param 存储高可用
	 *            the storageHA to set
	 */
	public void setStorageHA(Boolean storageHA) {
		this.storageHA = storageHA;
	}

	/**
	 * @param 字符集
	 *            the characterSet to set
	 */
	
	/**
	 * @param arch
	 *            the arch to set
	 */
	/*
	 * public void setArch(ArchDO arch) { Arch = arch; }
	 */

	/**
	 * @param 变更前主版本
	 *            the preMajorVersion to set
	 */
	public void setPreMajorVersion(Integer preMajorVersion) {
		this.preMajorVersion = preMajorVersion;
	}

	/**
	 * @param 变更前次版本
	 *            the preMinorVersion to set
	 */
	public void setPreMinorVersion(Integer preMinorVersion) {
		this.preMinorVersion = preMinorVersion;
	}

	/**
	 * @param 变更前修订版本
	 *            the prePatchVersion to set
	 */
	public void setPrePatchVersion(Integer prePatchVersion) {
		this.prePatchVersion = prePatchVersion;
	}

	/**
	 * @param 变更前编译版本
	 *            the preBuildVersion to set
	 */
	public void setPreBuildVersion(Integer preBuildVersion) {
		this.preBuildVersion = preBuildVersion;
	}

	/**
	 * @param 变更前架构编码
	 *            the preArchId to set
	 */
	/*public void setPreArchId(Long preArchId) {
		this.preArchId = preArchId;
	}*/

	/**
	 * @param 变更前CPU数量
	 *            the preCpuCnt to set
	 */
	public void setPreCpuCnt(Integer preCpuCnt) {
		this.preCpuCnt = preCpuCnt;
	}

	/**
	 * @param 变更前内存容量
	 *            the preMemSize to set
	 */
	public void setPreMemSize(Long preMemSize) {
		this.preMemSize = preMemSize;
	}

	/**
	 * @param 变更前数据目录大小
	 *            the preDataDirSize to set
	 */
	public void setPreDataDirSize(Long preDataDirSize) {
		this.preDataDirSize = preDataDirSize;
	}

	/**
	 * @param 变更前日志目录大小
	 *            the preLogDirSize to set
	 */
	public void setPreLogDirSize(Long preLogDirSize) {
		this.preLogDirSize = preLogDirSize;
	}

	/**
	 * @param 变更前带宽
	 *            the preNetworkBandwidth to set
	 */
	public void setPreNetworkBandwidth(Integer preNetworkBandwidth) {
		this.preNetworkBandwidth = preNetworkBandwidth;
	}

	/**
	 * @param 变更前架构
	 *            the preArch to set
	 */
	/*
	 * public void setPreArch(ArchDO preArch) { this.preArch = preArch; }
	 */

	/**
	 * @return the 日志目录性能等级
	 */
	public String getLogDirPerformance() {
		return logDirPerformance;
	}

	/**
	 * @param 日志目录性能等级
	 *            the logDirPerformance to set
	 */
	public void setLogDirPerformance(String logDirPerformance) {
		this.logDirPerformance = logDirPerformance;
	}

	/**
	 * @return the 分片数量
	 */
	public Integer getCnt() {
		return cnt;
	}


	/**
	 * @return the 变更前分片数量
	 */
	public Integer getPreCnt() {
		return preCnt;
	}

	/**
	 * @param 分片数量
	 *            the cnt to set
	 */
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}


	/**
	 * @param 变更前分片数量
	 *            the preCnt to set
	 */
	public void setPreCnt(Integer preCnt) {
		this.preCnt = preCnt;
	}

	public String getArchTypeCode() {
		return archTypeCode;
	}

	public void setArchTypeCode(String archTypeCode) {
		this.archTypeCode = archTypeCode;
	}


	public Integer getUnitCnt() {
		return unitCnt;
	}
	

	public void setUnitCnt(Integer unitCnt) {
		this.unitCnt = unitCnt;
	}
	

	public String getPreArchTypeCode() {
		return preArchTypeCode;
	}

	public void setPreArchTypeCode(String preArchTypeCode) {
		this.preArchTypeCode = preArchTypeCode;
	}


	public Integer getPreUnitCnt() {
		return preUnitCnt;
	}
	

	public void setPreUnitCnt(Integer preUnitCnt) {
		this.preUnitCnt = preUnitCnt;
	}
	

	@Override
	public String toString() {
		return "OrderDO [id=" + id + ", orderGroupId=" + orderGroupId + ", type=" + type + ", majorVersion="
				+ majorVersion + ", minorVersion=" + minorVersion + ", patchVersion=" + patchVersion + ", buildVersion="
				+ buildVersion + ", archTypeCode=" + archTypeCode + ", unitCnt=" + unitCnt + ", cpuCnt=" + cpuCnt
				+ ", memSize=" + memSize + ", port=" + port + ", cnt=" + cnt + ", dataDirSize=" + dataDirSize
				+ ", dataDirType=" + dataDirType + ", dataDirPerformance=" + dataDirPerformance + ", logDirSize="
				+ logDirSize + ", logDirType=" + logDirType + ", logDirPerformance=" + logDirPerformance
				+ ", networkBandwidth=" + networkBandwidth + ", clusterHA=" + clusterHA + ", networkHA=" + networkHA
				+ ", hostHA=" + hostHA + ", storageHA=" + storageHA + ", cfgs=" + cfgs + ", preMajorVersion="
				+ preMajorVersion + ", preMinorVersion=" + preMinorVersion + ", prePatchVersion=" + prePatchVersion
				+ ", preBuildVersion=" + preBuildVersion + ", preArchTypeCode=" + preArchTypeCode + ", preUnitCnt="
				+ preUnitCnt + ", preCpuCnt=" + preCpuCnt + ", preMemSize=" + preMemSize + ", preDataDirSize="
				+ preDataDirSize + ", preLogDirSize=" + preLogDirSize + ", preCnt=" + preCnt + ", preNetworkBandwidth="
				+ preNetworkBandwidth + ", hamode=" + hamode + ", schemas=" + schemas + ", users=" + users
				+ ", schedule=" + schedule + ", architecture=" + architecture + ", hacontainer=" + hacontainer
				+ ", cmha=" + cmha + ", proxy=" + proxy + ", cmhaImageId=" + cmhaImageId + ", proxyImageId="
				+ proxyImageId + ", imageId=" + imageId + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
