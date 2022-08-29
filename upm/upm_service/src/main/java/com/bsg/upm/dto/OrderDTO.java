package com.bsg.upm.dto;

import java.util.List;
import java.io.Serializable;
import java.util.Map;

import com.bsg.upm.domain.CmhaProxyDO;
import com.bsg.upm.domain.DbUserDO;
import com.bsg.upm.domain.SchemaDO;
import com.bsg.upm.domain.UserDO;
import com.bsg.upm.dto.BaseDTO.TypeDTO;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月15日
 */
public class OrderDTO extends BaseDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private String id;

    /**
     * 类型
     */
    private String type;

    /**
     * 分片数量
     */
    private int cnt;
    /**
     * 主版本
     */
    private VersionDTO version;

    /**
     * 架构编码
     */

    private ArchDTO arch;

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
     * 数据目录大小
     */
    private Long dataDirSize;

    /**
     * 数据目录
     */
    private TypeDTO dataDirType;

    /**
     * 数据目录性能等级
     */
    private TypeDTO dataDirPerformance;
    /**
     * 日志目录大小
     */
    private Long logDirSize;

    /**
     * 日志目录
     */
    private TypeDTO logDirType;

    /**
     * 日志目录性能等级
     */
    private TypeDTO logDirPerformance;

    /**
     * 带宽
     */
    private Integer networkBandwidth;

    /**
     * 网络高可用
     */
    private Boolean networkHA;

    /**
     * 主机可用
     */
    private Boolean hostHA;

    /**
     * 集群可用
     */
    private Boolean clusterHA;

    /**
     * 存储高可用
     */
    private Boolean storageHA;

    /**
     * 字符集
     */
    private Map<String, String> cfgs;
    /**
     * 变更前镜像
     */
    private VersionDTO preVersion;

    /**
     * 变更前架构
     */
    private ArchDTO preArch;

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
    private Long preCnt;

    /**
     * 变更前带宽
     */
    private Integer preNetworkBandwidth;
    
    /**
     * 高可用方式0:none,1:container_switch,2:database_switch
     */
    private String haMode;
    
    /**
	 * 变更前架构
	 */
    private SchemaDTO schema;
    
    /**
	 * 变更前架构
	 */
    private UserDTO user;    
    /**
     * 备份时间
     */
    private String schedule;
    
	/**
     * 物理架构名称
     */
    private String architecture;
    
    /**
     * 是否容器化漂移
     */
    private String hacontainer;
    
    /**
     * cmha
     */
    private List<CmhaProxyDTO> cmha;
    
    /**
     * proxy
     */
    private List<CmhaProxyDTO> proxy;
    
    /**
   	 * schema
   	 */
    private List<DbUserDTO> schemas;
       
       /**
   	 * 数据库用户
   	 */
    private List<DbUserDTO> users;
    
    

    public List<CmhaProxyDTO> getCmha() {
		return cmha;
	}

	public void setCmha(List<CmhaProxyDTO> cmha) {
		this.cmha = cmha;
	}

	public List<CmhaProxyDTO> getProxy() {
		return proxy;
	}

	public void setProxy(List<CmhaProxyDTO> proxy) {
		this.proxy = proxy;
	}

	public List<DbUserDTO> getSchemas() {
		return schemas;
	}

	public void setSchemas(List<DbUserDTO> schemas) {
		this.schemas = schemas;
	}

	public List<DbUserDTO> getUsers() {
		return users;
	}

	public void setUsers(List<DbUserDTO> users) {
		this.users = users;
	}

	/**
     * @return the 分片数量
     */
    public String getHaMode() {
		return haMode;
	}

    /**
     * @param 高可用方式
     *            the haMode to set
     */
	public void setHaMode(String haMode) {
		this.haMode = haMode;
	}

	/**
     * @return the
     */
    public String getId() {
        return id;
    }

    /**
     * @return the 类型
     */
    public String getType() {
        return type;
    }

    /**
     * @return the 分片数量
     */
    public int getCnt() {
        return cnt;
    }

    /**
     * @return the 主版本
     */
    public VersionDTO getVersion() {
        return version;
    }

    /**
     * @return the 架构编码
     */
    /*
     * public ArchDTO getArch() { return arch; }
     */

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
     * @return the 数据目录
     */
    public TypeDTO getDataDirType() {
        return dataDirType;
    }

    /**
     * @return the 数据目录性能等级
     */
    public TypeDTO getDataDirPerformance() {
        return dataDirPerformance;
    }

    /**
     * @return the 日志目录大小
     */
    public Long getLogDirSize() {
        return logDirSize;
    }

    /**
     * @return the 日志目录
     */
    public TypeDTO getLogDirType() {
        return logDirType;
    }

    /**
     * @return the 带宽
     */
    public Integer getNetworkBandwidth() {
        return networkBandwidth;
    }

    /**
     * @return the 网络高可用
     */
    public Boolean getNetworkHA() {
        return networkHA;
    }

    /**
     * @return the 主机可用
     */
    public Boolean getHostHA() {
        return hostHA;
    }

    /**
     * @return the 集群可用
     */
    public Boolean getClusterHA() {
        return clusterHA;
    }

    /**
     * @return the 存储高可用
     */
    public Boolean getStorageHA() {
        return storageHA;
    }

    /**
     * @return the 变更前镜像
     */
    public VersionDTO getPreVersion() {
        return preVersion;
    }

    /**
     * @return the 变更前架构
     */
    /*
     * public ArchDTO getPreArch() { return preArch; }
     */



	public String getSchedule() {
		return schedule;
	}

	public SchemaDTO getSchema() {
		return schema;
	}

	public void setSchema(SchemaDTO schema) {
		this.schema = schema;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
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
     * @return the 变更前带宽
     */
    public Integer getPreNetworkBandwidth() {
        return preNetworkBandwidth;
    }

    /**
     * @param the
     *            id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param 类型
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param 分片数量
     *            the cnt to set
     */
    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    /**
     * @param 主版本
     *            the version to set
     */
    public void setVersion(VersionDTO version) {
        this.version = version;
    }

    /**
     * @param 架构编码
     *            the arch to set
     */
    /*
     * public void setArch(ArchDTO arch) { this.arch = arch; }
     */

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
     * @param 数据目录
     *            the dataDirType to set
     */
    public void setDataDirType(TypeDTO dataDirType) {
        this.dataDirType = dataDirType;
    }

    /**
     * @param 数据目录性能等级
     *            the dataDirPerformance to set
     */
    public void setDataDirPerformance(TypeDTO dataDirPerformance) {
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
     * @param 日志目录
     *            the logDirType to set
     */
    public void setLogDirType(TypeDTO logDirType) {
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
     * @param 网络高可用
     *            the networkHA to set
     */
    public void setNetworkHA(Boolean networkHA) {
        this.networkHA = networkHA;
    }

    /**
     * @param 主机可用
     *            the hostHA to set
     */
    public void setHostHA(Boolean hostHA) {
        this.hostHA = hostHA;
    }

    /**
     * @param 集群可用
     *            the clusterHA to set
     */
    public void setClusterHA(Boolean clusterHA) {
        this.clusterHA = clusterHA;
    }

    /**
     * @param 存储高可用
     *            the storageHA to set
     */
    public void setStorageHA(Boolean storageHA) {
        this.storageHA = storageHA;
    }

    /**
     * @param 变更前镜像
     *            the preVersion to set
     */
    public void setPreVersion(VersionDTO preVersion) {
        this.preVersion = preVersion;
    }

    /**
     * @param 变更前架构
     *            the preArch to set
     */
    /*
     * public void setPreArch(ArchDTO preArch) { this.preArch = preArch; }
     */

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
     * @param 变更前带宽
     *            the preNetworkBandwidth to set
     */
    public void setPreNetworkBandwidth(Integer preNetworkBandwidth) {
        this.preNetworkBandwidth = preNetworkBandwidth;
    }

    /**
     * @return the 字符集
     */

    /**
     * @return the 日志目录性能等级
     */
    public TypeDTO getLogDirPerformance() {
        return logDirPerformance;
    }

    public Map<String, String> getCfgs() {
        return cfgs;
    }

    public void setCfgs(Map<String, String> cfgs) {
        this.cfgs = cfgs;
    }

    /**
     * @param 日志目录性能等级
     *            the logDirPerformance to set
     */
    public void setLogDirPerformance(TypeDTO logDirPerformance) {
        this.logDirPerformance = logDirPerformance;
    }

    /**
     * @return the 变更前分片数量
     */
    public Long getPreCnt() {
        return preCnt;
    }

    /**
     * @param 变更前分片数量
     *            the preCnt to set
     */
    public void setPreCnt(Long preCnt) {
        this.preCnt = preCnt;
    }

    public ArchDTO getArch() {
        return arch;
    }

    public void setArch(ArchDTO arch) {
        this.arch = arch;
    }

    public ArchDTO getPreArch() {
        return preArch;
    }

    public void setPreArch(ArchDTO preArch) {
        this.preArch = preArch;
    }

    /**
     * @return the 变更前日志目录大小
     */
    public Long getPreLogDirSize() {
        return preLogDirSize;
    }

    /**
     * @param 变更前日志目录大小
     *            the preLogDirSize to set
     */
    public void setPreLogDirSize(Long preLogDirSize) {
        this.preLogDirSize = preLogDirSize;
    }
    

    @Override
	public String toString() {
		return "OrderDTO [id=" + id + ", type=" + type + ", cnt=" + cnt + ", version=" + version + ", arch=" + arch
				+ ", cpuCnt=" + cpuCnt + ", memSize=" + memSize + ", port=" + port + ", dataDirSize=" + dataDirSize
				+ ", dataDirType=" + dataDirType + ", dataDirPerformance=" + dataDirPerformance + ", logDirSize="
				+ logDirSize + ", logDirType=" + logDirType + ", logDirPerformance=" + logDirPerformance
				+ ", networkBandwidth=" + networkBandwidth + ", networkHA=" + networkHA + ", hostHA=" + hostHA
				+ ", clusterHA=" + clusterHA + ", storageHA=" + storageHA + ", cfgs=" + cfgs + ", preVersion="
				+ preVersion + ", preArch=" + preArch + ", preCpuCnt=" + preCpuCnt + ", preMemSize=" + preMemSize
				+ ", preDataDirSize=" + preDataDirSize + ", preLogDirSize=" + preLogDirSize + ", preCnt=" + preCnt
				+ ", preNetworkBandwidth=" + preNetworkBandwidth + ", haMode=" + haMode + ", schema=" + schema
				+ ", user=" + user + ", schedule=" + schedule + ", architecture=" + architecture + ", hacontainer="
				+ hacontainer + ", cmha=" + cmha + ", proxy=" + proxy + ", schemas=" + schemas + ", users=" + users
				+ "]";
	}

    public class ArchDTO {
        private String mode;
        private Integer replicas;
        private String name;

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public Integer getReplicas() {
            return replicas;
        }

        public void setReplicas(Integer replicas) {
            this.replicas = replicas;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "ArchDTO [mode=" + mode + ", replicas=" + replicas + ", name=" + name + "]";
        }

    }
}
