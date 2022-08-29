package com.bsg.upm.form;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.bsg.upm.domain.OrderDO;


/**
 * 
 * @author ZhuXH
 * @date 2019年7月15日
 */
public class OrderForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    private String type;

    /**
     * 镜像版本
     */
    private VersionForm version;

    /**
     * 架构，数据改成字典中arch_type和arch_cnt类型的code
     */
    private ArchForm arch;
    
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
     * 数据目录性能等级
     */
    private String dataDirPerformance;

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
    private String logDirPerformance;
    /**
     * 日志目录大小
     */
    private Long logDirSize;

    /**
     * 日志目录类型
     */
    private String logDirType;

    /**
     * 带宽
     */
    private Integer networkBandwidth;


    /**
     * 集群高可用
     */
    private Boolean clusterHA;

    /**
     * 主机高可用
     */
    private Boolean hostHA;

    /**
     * 网络高可用
     */
    private Boolean networkHA;

    /**
     * 存储高可用
     */
    private Boolean storageHA;

    /**
     * 分片数量
     */
    private Integer cnt;

    /**
     * 字符集
     */
    private Map<String, String> cfgs;
    
    /**
     * 高可用方式 true ,需要加cmha、proxy
     */
    private Boolean hamode;
    /**
     * 是否容器化漂移
     * 0:none,1:container_switch,2:database_switch
     */
    private String hacontainer;
    
    /**
     * 数据库用户
     */
    private List<UserForm> users;
    
    /**
     * schema
     */
    private List<SchemaForm> schemas;
    
    /**
     * 备份时间
     */
    private String schedule;
    
	/**
     * 硬件架构
     */
    private String architecture;

    /**
     * cmha
     */
    private List<ProxyForm> cmha;
    
    /**
     * proxy
     */
    private List<ProxyForm> proxy;
    public String getType() {
        return type;
    }

    /**
     * @return the 镜像版本
     */
    public VersionForm getVersion() {
        return version;
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
     * @return the 数据目录性能等级
     */
    public String getDataDirPerformance() {
        return dataDirPerformance;
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
     * @return the 主机高可用
     */
    public Boolean getHostHA() {
        return hostHA;
    }

    /**
     * @return the 网络高可用
     */
    public Boolean getNetworkHA() {
        return networkHA;
    }

    /**
     * @return the 存储高可用
     */
    public Boolean getStorageHA() {
        return storageHA;
    }

    /**
     * @return the 分片数量
     */
    public Integer getCnt() {
        return cnt;
    }

    /**
     * @return the 字符集
     */
   

    /**
     * @param 类型
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }



	

	/**
     * @param 镜像版本
     *            the version to set
     */
    public void setVersion(VersionForm version) {
        this.version = version;
    }

	public ArchForm getArch() {
		return arch;
	}

	public void setArch(ArchForm arch) {
		this.arch = arch;
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
     * @param 数据目录性能等级
     *            the dataDirPerformance to set
     */
    public void setDataDirPerformance(String dataDirPerformance) {
        this.dataDirPerformance = dataDirPerformance;
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
     * @param 主机高可用
     *            the hostHA to set
     */
    public void setHostHA(Boolean hostHA) {
        this.hostHA = hostHA;
    }

    /**
     * @param 网络高可用
     *            the networkHA to set
     */
    public void setNetworkHA(Boolean networkHA) {
        this.networkHA = networkHA;
    }

    /**
     * @param 存储高可用
     *            the storageHA to set
     */
    public void setStorageHA(Boolean storageHA) {
        this.storageHA = storageHA;
    }

    /**
     * @param 分片数量
     *            the cnt to set
     */
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    /**
     * @param 字符集
     *            the characterSet to set
     */
   

    /**
     * @return the 数据目录性能等级
     */
    public String getLogDirPerformance() {
        return logDirPerformance;
    }

    public Map<String, String> getCfgs() {
		return cfgs;
	}
	

	public void setCfgs(Map<String, String> cfgs) {
		this.cfgs = cfgs;
	}
	

	/**
     * @param 数据目录性能等级
     *            the logDirPerformance to set
     */
    public void setLogDirPerformance(String logDirPerformance) {
        this.logDirPerformance = logDirPerformance;
    }
    
    public Boolean getHamode() {
		return hamode;
	}

	public void setHamode(Boolean hamode) {
		this.hamode = hamode;
	}




	public String getHacontainer() {
		return hacontainer;
	}

	public void setHacontainer(String hacontainer) {
		this.hacontainer = hacontainer;
	}

	public List<UserForm> getUsers() {
		return users;
	}

	public void setUsers(List<UserForm> users) {
		this.users = users;
	}

	public List<SchemaForm> getSchemas() {
		return schemas;
	}

	public void setSchemas(List<SchemaForm> schemas) {
		this.schemas = schemas;
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

	public List<ProxyForm> getCmha() {
		return cmha;
	}

	public void setCmha(List<ProxyForm> cmha) {
		this.cmha = cmha;
	}

	public List<ProxyForm> getProxy() {
		return proxy;
	}

	public void setProxy(List<ProxyForm> proxy) {
		this.proxy = proxy;
	}

	@Override
	public String toString() {
		return "OrderForm [type=" + type + ", version=" + version + ", arch=" + arch + ", cpuCnt=" + cpuCnt
				+ ", memSize=" + memSize + ", port=" + port + ", dataDirPerformance=" + dataDirPerformance
				+ ", dataDirSize=" + dataDirSize + ", dataDirType=" + dataDirType + ", logDirPerformance="
				+ logDirPerformance + ", logDirSize=" + logDirSize + ", logDirType=" + logDirType
				+ ", networkBandwidth=" + networkBandwidth + ", clusterHA=" + clusterHA + ", hostHA=" + hostHA
				+ ", networkHA=" + networkHA + ", storageHA=" + storageHA + ", cnt=" + cnt + ", cfgs=" + cfgs
				+ ", hamode=" + hamode + ", hacontainer=" + hacontainer + ", users=" + users + ", schemas=" + schemas
				+ ", schedule=" + schedule + ", architecture=" + architecture + ", cmha=" + cmha + ", proxy=" + proxy
				+ "]";
	}

	public static class VersionForm {

        /**
         * 主版本
         */
        private Integer major;

        /**
         * 次版本
         */
        private Integer minor;

        /**
         * 修订版本
         */
        private Integer patch;

        /**
         * 编译版本
         */
        private Integer build;

        /**
         * @return the 主版本
         */
        public Integer getMajor() {
            return major;
        }

        /**
         * @param 主版本
         *            the major to set
         */
        public void setMajor(Integer major) {
            this.major = major;
        }

        /**
         * @return the 次版本
         */
        public Integer getMinor() {
            return minor;
        }

        /**
         * @param 次版本
         *            the minor to set
         */
        public void setMinor(Integer minor) {
            this.minor = minor;
        }

        /**
         * @return the 修订版本
         */
        public Integer getPatch() {
            return patch;
        }

        /**
         * @param 修订版本
         *            the patch to set
         */
        public void setPatch(Integer patch) {
            this.patch = patch;
        }

        /**
         * @return the 编译版本
         */
        public Integer getBuild() {
            return build;
        }

        /**
         * @param 编译版本
         *            the build to set
         */
        public void setBuild(Integer build) {
            this.build = build;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "Version [major=" + major + ", minor=" + minor + ", patch=" + patch + ", build=" + build + "]";
        }

    }
    
    public static class ArchForm {

        private String mode;
        private Integer replicas;
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
		@Override
		public String toString() {
			return "ArchForm [mode=" + mode + ", replicas=" + replicas + "]";
		}

    }
    
    public static class UserForm {

        private String dbuser;
        private String dbpwd;
        
		public String getDbuser() {
			return dbuser;
		}

		public void setDbuser(String dbuser) {
			this.dbuser = dbuser;
		}

		public String getDbpwd() {
			return dbpwd;
		}

		public void setDbpwd(String dbpwd) {
			this.dbpwd = dbpwd;
		}

		@Override
		public String toString() {
			return "UserForm [dbuser=" + dbuser + ", dbpwd=" + dbpwd + "]";
		}

    }
    
    public static class SchemaForm {

        private String dbname;
        
		public String getDbname() {
			return dbname;
		}
		/**
	     * 字符集
	     */
	    private String characterSet;
		public String getCharacterSet() {
			return characterSet;
		}

		public void setCharacterSet(String characterSet) {
			this.characterSet = characterSet;
		}

		public void setDbname(String dbname) {
			this.dbname = dbname;
		}

		@Override
		public String toString() {
			return "SchemaForm [dbname=" + dbname + ", characterSet=" + characterSet + "]";
		}

    }
    
    public static class ProxyForm {

    	/**
	     * 服务架构模式
	     * clonere、plication_async、replication_semi_sync
	     */
        private String mode;
        /**
	     * 服务架构单元数量
	     * 最小3
	     */
        private Integer replicas;
        /**
         * 端口
         */
        private Integer port;
        /**
         * cpu
         */
        private Integer cpu;
        /**
         * cpu
         */
        private Long memory;
        /**
	     * 存储性能等级
	     * high、medium、low
	     */
        private String performance;
        /**
         * log存储容量
         */
        private Long log_capacity;
        /**
         * data存储容量
         */
        private Long data_capacity;
        
        /**
         * 镜像id
         */
        private String img_id;
        
        
//        private VolumeForm volumes;

		public String getImg_id() {
			return img_id;
		}

		public void setImg_id(String img_id) {
			this.img_id = img_id;
		}

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

		public Integer getPort() {
			return port;
		}

		public void setPort(Integer port) {
			this.port = port;
		}

		public Integer getCpu() {
			return cpu;
		}

		public void setCpu(Integer cpu) {
			this.cpu = cpu;
		}

		public Long getMemory() {
			return memory;
		}

		public void setMemory(Long memory) {
			this.memory = memory;
		}

		public String getPerformance() {
			return performance;
		}

		public void setPerformance(String performance) {
			this.performance = performance;
		}


		public Long getLog_capacity() {
			return log_capacity;
		}

		public void setLog_capacity(Long log_capacity) {
			this.log_capacity = log_capacity;
		}

		public Long getData_capacity() {
			return data_capacity;
		}

		public void setData_capacity(Long data_capacity) {
			this.data_capacity = data_capacity;
		}

		@Override
		public String toString() {
			return "ProxyForm [mode=" + mode + ", replicas=" + replicas + ", port=" + port + ", cpu=" + cpu
					+ ", memory=" + memory + ", performance=" + performance + ", log_capacity=" + log_capacity
					+ ", data_capacity=" + data_capacity + ", img_id=" + img_id + "]";
		}
        
        

    }
    
    public static class VolumeForm {

    	/**
	     * 存储类型
	     * data、log
	     */
        private String type;
        /**
         * 存储卷容量
         */
        private Integer capacity;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Integer getCapacity() {
			return capacity;
		}
		public void setCapacity(Integer capacity) {
			this.capacity = capacity;
		}
		@Override
		public String toString() {
			return "VolumeForm [type=" + type + ", capacity=" + capacity + "]";
		}
        

    }
}
