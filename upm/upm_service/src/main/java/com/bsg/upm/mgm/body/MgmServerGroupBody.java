package com.bsg.upm.mgm.body;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月18日
 */
public class MgmServerGroupBody implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "desc")
    private String desc;
    
    /*
     * add by yeht
     * 硬件架构amd64、arm64
     */
    @JSONField(name = "arch")
    private String arch;

    @JSONField(name = "created_user")
    private String createdUser;

    @JSONField(name = "spec")
    private SpecBody spec;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    public String getArch() {
		return arch;
	}

	public void setArch(String arch) {
		this.arch = arch;
	}

	/**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @return the createdUser
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * @return the spec
     */
    public SpecBody getSpec() {
        return spec;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param desc
     *            the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @param createdUser
     *            the createdUser to set
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    /**
     * @param spec
     *            the spec to set
     */
    public void setSpec(SpecBody spec) {
        this.spec = spec;
    }

    @Override
	public String toString() {
		return "MgmServerGroupBody [name=" + name + ", desc=" + desc + ", arch=" + arch + ", createdUser=" + createdUser
				+ ", spec=" + spec + ", options=" + options + "]";
	}

    public static class SpecBody {
        @JSONField(name = "database")
        private DatabaseBody database;
        
        //add by yeht
        @JSONField(name = "cmha")
        private DatabaseBody cmha;
        @JSONField(name = "proxy")
        private DatabaseBody proxy;

        /**
         * @return the database
         */
        public DatabaseBody getDatabase() {
            return database;
        }

        /**
         * @param database
         *            the database to set
         */
        public void setDatabase(DatabaseBody database) {
            this.database = database;
        }

        public DatabaseBody getCmha() {
			return cmha;
		}

		public void setCmha(DatabaseBody cmha) {
			this.cmha = cmha;
		}

		public DatabaseBody getProxy() {
			return proxy;
		}

		public void setProxy(DatabaseBody proxy) {
			this.proxy = proxy;
		}

		@Override
		public String toString() {
			return "SpecBody [database=" + database + ", cmha=" + cmha + ", proxy=" + proxy + "]";
		}

    }

    public static class DatabaseBody {
        @JSONField(name = "image")
        private ImageBody image;

        @JSONField(name = "services")
        private ServiceBody services;

        

        /**
         * @return the image
         */
        public ImageBody getImage() {
            return image;
        }

        /**
         * @return the services
         */
        public ServiceBody getServices() {
            return services;
        }

        /**
         * @param image
         *            the image to set
         */
        public void setImage(ImageBody image) {
            this.image = image;
        }

        /**
         * @param services
         *            the services to set
         */
        public void setServices(ServiceBody services) {
            this.services = services;
        }

		@Override
		public String toString() {
			return "DatabaseBody [image=" + image + ", services=" + services + "]";
		}

    }

    public static class ImageBody {
        @JSONField(name = "id")
        private String id;

        /**
         * @return the id
         */
        public String getId() {
            return id;
        }

        /**
         * @param id
         *            the id to set
         */
        public void setId(String id) {
            this.id = id;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "ImageBody [id=" + id + "]";
        }

    }

    public static class ServiceBody {
        @JSONField(name = "num")
        private Integer num;

        @JSONField(name = "arch")
        private ArchBody arch;

        @JSONField(name = "ports")
        private List<PortBody> ports;

        @JSONField(name = "options")
        public Map<String, String> options;

        @JSONField(name = "conditions")
        private ConditionBody conditions;

        @JSONField(name = "backup")
        private BackupBody backup;
        
        @JSONField(name = "units")
        private UnitBody units;

        /**
         * @return the num
         */
        public Integer getNum() {
            return num;
        }

        /**
         * @return the arch
         */
        public ArchBody getArch() {
            return arch;
        }

        /**
         * @return the ports
         */
        public List<PortBody> getPorts() {
            return ports;
        }

        /**
         * @return the options
         */
//        public OptionBody getOptions() {
//            return options;
//        }

        /**
         * @param num
         *            the num to set
         */
        public void setNum(Integer num) {
            this.num = num;
        }

     /*   public OptionBody getOptions() {
			return options;
		}
		

		public void setOptions(OptionBody options) {
			this.options = options;
		}*/
		

		/**
         * @param arch
         *            the arch to set
         */
        public void setArch(ArchBody arch) {
            this.arch = arch;
        }

        public Map<String, String> getOptions() {
			return options;
		}
		

		public void setOptions(Map<String, String> options) {
			this.options = options;
		}
		

		/**
         * @param ports
         *            the ports to set
         */
        public void setPorts(List<PortBody> ports) {
            this.ports = ports;
        }

        /**
         * @param options
         *            the options to set
         */
//        public void setOptions(OptionBody options) {
//            this.options = options;
//        }

        /**
         * @return the conditions
         */
        public ConditionBody getConditions() {
            return conditions;
        }

        /**
         * @param conditions
         *            the conditions to set
         */
        public void setConditions(ConditionBody conditions) {
            this.conditions = conditions;
        }

        /**
         * @return the backup
         */
        public BackupBody getBackup() {
            return backup;
        }

        /**
         * @param backup
         *            the backup to set
         */
        public void setBackup(BackupBody backup) {
            this.backup = backup;
        }
        

        public UnitBody getUnits() {
			return units;
		}

		public void setUnits(UnitBody units) {
			this.units = units;
		}

		@Override
		public String toString() {
			return "ServiceBody [num=" + num + ", arch=" + arch + ", ports=" + ports + ", options=" + options
					+ ", conditions=" + conditions + ", backup=" + backup + ", units=" + units + "]";
		}

    }

    public static class ArchBody {
        @JSONField(name = "mode")
        private String mode;

        @JSONField(name = "replicas")
        private Integer replicas;

        /**
         * @return the mode
         */
        public String getMode() {
            return mode;
        }

        /**
         * @return the replicas
         */
        public Integer getReplicas() {
            return replicas;
        }

        /**
         * @param mode
         *            the mode to set
         */
        public void setMode(String mode) {
            this.mode = mode;
        }

        /**
         * @param replicas
         *            the replicas to set
         */
        public void setReplicas(Integer replicas) {
            this.replicas = replicas;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "ArchBody [mode=" + mode + ", replicas=" + replicas + "]";
        }

    }

    public static class PortBody {
        @JSONField(name = "name")
        private String name;

        @JSONField(name = "port")
        private Integer port;

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @return the port
         */
        public Integer getPort() {
            return port;
        }

        /**
         * @param name
         *            the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @param port
         *            the port to set
         */
        public void setPort(Integer port) {
            this.port = port;
        }

        
        
        
       


		/*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
		

    }

    public Map<String, String> options;
    
    
    public Map<String, String> getOptions() {
		return options;
	}
	

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}

    public static class ConditionBody {
        @JSONField(name = "cluster")
        private ClusterBody cluster;

        @JSONField(name = "host")
        private HostBody host;

        @JSONField(name = "storage_remote")
        private StorageRemoteBody storageRemote;

        @JSONField(name = "network")
        private NetworkBody network;

        /**
         * @return the cluster
         */
        public ClusterBody getCluster() {
            return cluster;
        }

        /**
         * @return the host
         */
        public HostBody getHost() {
            return host;
        }

        /**
         * @return the storageRemote
         */
        public StorageRemoteBody getStorageRemote() {
            return storageRemote;
        }

        /**
         * @return the network
         */
        public NetworkBody getNetwork() {
            return network;
        }

        /**
         * @param cluster
         *            the cluster to set
         */
        public void setCluster(ClusterBody cluster) {
            this.cluster = cluster;
        }

        /**
         * @param host
         *            the host to set
         */
        public void setHost(HostBody host) {
            this.host = host;
        }

        /**
         * @param storageRemote
         *            the storageRemote to set
         */
        public void setStorageRemote(StorageRemoteBody storageRemote) {
            this.storageRemote = storageRemote;
        }

        /**
         * @param network
         *            the network to set
         */
        public void setNetwork(NetworkBody network) {
            this.network = network;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "ConditionBody [cluster=" + cluster + ", host=" + host + ", storageRemote=" + storageRemote
                    + ", network=" + network + "]";
        }

    }

    public static class ClusterBody {
        @JSONField(name = "candidates_id")
        private List<String> candidatesId;

        @JSONField(name = "high_availability")
        private Boolean highAvailability;

        /**
         * @return the highAvailability
         */
        public Boolean getHighAvailability() {
            return highAvailability;
        }

        /**
         * @return the candidatesId
         */
        public List<String> getCandidatesId() {
            return candidatesId;
        }

        /**
         * @param candidatesId
         *            the candidatesId to set
         */
        public void setCandidatesId(List<String> candidatesId) {
            this.candidatesId = candidatesId;
        }

        /**
         * @param highAvailability
         *            the highAvailability to set
         */
        public void setHighAvailability(Boolean highAvailability) {
            this.highAvailability = highAvailability;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "ClusterBody [candidatesId=" + candidatesId + ", highAvailability=" + highAvailability + "]";
        }

    }

    public static class HostBody {
        @JSONField(name = "candidates_id")
        private List<String> candidatesId;

        @JSONField(name = "high_availability")
        private Boolean highAvailability;

        /**
         * @return the highAvailability
         */
        public Boolean getHighAvailability() {
            return highAvailability;
        }

        /**
         * @param highAvailability
         *            the highAvailability to set
         */
        public void setHighAvailability(Boolean highAvailability) {
            this.highAvailability = highAvailability;
        }

        /**
         * @return the candidatesId
         */
        public List<String> getCandidatesId() {
            return candidatesId;
        }

        /**
         * @param candidatesId
         *            the candidatesId to set
         */
        public void setCandidatesId(List<String> candidatesId) {
            this.candidatesId = candidatesId;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "HostBody [candidatesId=" + candidatesId + ", highAvailability=" + highAvailability + "]";
        }

    }

    public static class StorageRemoteBody {
        @JSONField(name = "candidates_id")
        private List<String> candidatesId;

        @JSONField(name = "high_availability")
        private Boolean highAvailability;

        /**
         * @return the highAvailability
         */
        public Boolean getHighAvailability() {
            return highAvailability;
        }

        /**
         * @param highAvailability
         *            the highAvailability to set
         */
        public void setHighAvailability(Boolean highAvailability) {
            this.highAvailability = highAvailability;
        }

        /**
         * @return the candidatesId
         */
        public List<String> getCandidatesId() {
            return candidatesId;
        }

        /**
         * @param candidatesId
         *            the candidatesId to set
         */
        public void setCandidatesId(List<String> candidatesId) {
            this.candidatesId = candidatesId;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "StorageRemoteBody [candidatesId=" + candidatesId + ", highAvailability=" + highAvailability + "]";
        }

    }

    public static class NetworkBody {
        @JSONField(name = "candidates_id")
        private List<String> candidatesId;

        @JSONField(name = "high_availability")
        private Boolean highAvailability;

        /**
         * @return the highAvailability
         */
        public Boolean getHighAvailability() {
            return highAvailability;
        }

        /**
         * @param highAvailability
         *            the highAvailability to set
         */
        public void setHighAvailability(Boolean highAvailability) {
            this.highAvailability = highAvailability;
        }

        /**
         * @return the candidatesId
         */
        public List<String> getCandidatesId() {
            return candidatesId;
        }

        /**
         * @param candidatesId
         *            the candidatesId to set
         */
        public void setCandidatesId(List<String> candidatesId) {
            this.candidatesId = candidatesId;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "NetworkBody [candidatesId=" + candidatesId + ", highAvailability=" + highAvailability + "]";
        }

    }

    public static class BackupBody {
        @JSONField(name = "storage")
        private BackupStorageBody storage;

        /**
         * @return the storage
         */
        public BackupStorageBody getStorage() {
            return storage;
        }

        /**
         * @param storage
         *            the storage to set
         */
        public void setStorage(BackupStorageBody storage) {
            this.storage = storage;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "BackupBody [storage=" + storage + "]";
        }

    }

    public static class BackupStorageBody {
        @JSONField(name = "type")
        private String type;

        @JSONField(name = "performance")
        private String performance;

        @JSONField(name = "capacity")
        private Long capacity;

        /**
         * @return the type
         */
        public String getType() {
            return type;
        }

        /**
         * @return the performance
         */
        public String getPerformance() {
            return performance;
        }

        /**
         * @return the capacity
         */
        public Long getCapacity() {
            return capacity;
        }

        /**
         * @param type
         *            the type to set
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * @param performance
         *            the performance to set
         */
        public void setPerformance(String performance) {
            this.performance = performance;
        }

        /**
         * @param capacity
         *            the capacity to set
         */
        public void setCapacity(Long capacity) {
            this.capacity = capacity;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "BackupStorageBody [type=" + type + ", performance=" + performance + ", capacity=" + capacity + "]";
        }

    }

    public static class UnitBody {
        @JSONField(name = "resources")
        private ResourceBody resources;
        
        /*
         * 容器漂移方式
         */
        @JSONField(name = "ha")
        private Boolean ha;

        /**
         * @return the resources
         */
        public ResourceBody getResources() {
            return resources;
        }

        /**
         * @param resources
         *            the resources to set
         */
        public void setResources(ResourceBody resources) {
            this.resources = resources;
        }

        public Boolean getHa() {
			return ha;
		}

		public void setHa(Boolean ha) {
			this.ha = ha;
		}

		@Override
		public String toString() {
			return "UnitBody [resources=" + resources + ", ha=" + ha + "]";
		}

    }

    public static class ResourceBody {
        @JSONField(name = "requests")
        private RequestBody requests;

        /**
         * @return the requests
         */
        public RequestBody getRequests() {
            return requests;
        }

        /**
         * @param requests
         *            the requests to set
         */
        public void setRequests(RequestBody requests) {
            this.requests = requests;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "ResourceBody [requests=" + requests + "]";
        }

    }

    public static class RequestBody {
        @JSONField(name = "cpu")
        private Integer cpu;

        @JSONField(name = "memory")
        private Long memory;

        @JSONField(name = "net_bandwidth")
        private Integer netBandwidth;

        @JSONField(name = "storage")
        private StorageBody storage;

        /**
         * @return the cpu
         */
        public Integer getCpu() {
            return cpu;
        }

        /**
         * @return the memory
         */
        public Long getMemory() {
            return memory;
        }

        /**
         * @return the netBandwidth
         */
        public Integer getNetBandwidth() {
            return netBandwidth;
        }

        /**
         * @return the storage
         */
        public StorageBody getStorage() {
            return storage;
        }

        /**
         * @param cpu
         *            the cpu to set
         */
        public void setCpu(Integer cpu) {
            this.cpu = cpu;
        }

        /**
         * @param memory
         *            the memory to set
         */
        public void setMemory(Long memory) {
            this.memory = memory;
        }

        /**
         * @param netBandwidth
         *            the netBandwidth to set
         */
        public void setNetBandwidth(Integer netBandwidth) {
            this.netBandwidth = netBandwidth;
        }

        /**
         * @param storage
         *            the storage to set
         */
        public void setStorage(StorageBody storage) {
            this.storage = storage;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "RequestBody [cpu=" + cpu + ", memory=" + memory + ", netBandwidth=" + netBandwidth + ", storage="
                    + storage + "]";
        }

    }

    public static class StorageBody {
        @JSONField(name = "type")
        private String type;

        @JSONField(name = "performance")
        private String performance;

        @JSONField(name = "volumes")
        private List<VolumeBody> volumes;

        /**
         * @return the type
         */
        public String getType() {
            return type;
        }

        /**
         * @return the performance
         */
        public String getPerformance() {
            return performance;
        }

        /**
         * @return the volumes
         */
        public List<VolumeBody> getVolumes() {
            return volumes;
        }

        /**
         * @param type
         *            the type to set
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * @param performance
         *            the performance to set
         */
        public void setPerformance(String performance) {
            this.performance = performance;
        }

        /**
         * @param volumes
         *            the volumes to set
         */
        public void setVolumes(List<VolumeBody> volumes) {
            this.volumes = volumes;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "StorageBody [type=" + type + ", performance=" + performance + ", volumes=" + volumes + "]";
        }

    }

    public static class VolumeBody {
        @JSONField(name = "type")
        private String type;

        @JSONField(name = "capacity")
        private Long capacity;

        /**
         * @return the type
         */
        public String getType() {
            return type;
        }

        /**
         * @return the capacity
         */
        public Long getCapacity() {
            return capacity;
        }

        /**
         * @param type
         *            the type to set
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * @param capacity
         *            the capacity to set
         */
        public void setCapacity(Long capacity) {
            this.capacity = capacity;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "VolumesBody [type=" + type + ", capacity=" + capacity + "]";
        }

    }
}
