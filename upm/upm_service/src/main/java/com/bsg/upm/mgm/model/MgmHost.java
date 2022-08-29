package com.bsg.upm.mgm.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月5日
 */
public class MgmHost extends MgmModel implements Serializable {

    /**
     * 
     */
    private static final Long serialVersionUID = 1L;

    @JSONField(name = "id")
    private String id;

    @JSONField(name = "cluster")
    private Cluster cluster;

    @JSONField(name = "room")
    private String room;

    @JSONField(name = "seat")
    private String seat;
    
    @JSONField(name = "ntp_server")
    private String ntpServer;

    @JSONField(name = "storage_host")
    private List<StorageHost> storageHost;

    @JSONField(name = "storage_remote")
    private StorageRemote storageRemote;

    @JSONField(name = "node")
    private Node node;

    @JSONField(name = "units")
    private List<Unit> units;

    @JSONField(name = "max_unit")
    private Integer maxUnit;
    
    @JSONField(name = "existing_unit")
    private Integer existingUnit;

    @JSONField(name = "max_usage")
    private MaxUsage maxUsage;

    @JSONField(name = "enabled")
    private Boolean enabled;

    @JSONField(name = "desc")
    private String desc;

    @JSONField(name = "task")
    private Task task;

    @JSONField(name = "created")
    private Info created;

    @JSONField(name = "modified")
    private Info modified;
    
    @JSONField(name = "arch")
    private String arch;
    
    @JSONField(name = "role")
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

    public List<Unit> getUnits() {
		return units;
	}
	

	public void setUnits(List<Unit> units) {
		this.units = units;
	}
	

	/**
     * @return the cluster
     */
    public Cluster getCluster() {
        return cluster;
    }

    /**
     * @param cluster
     *            the cluster to set
     */
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
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
     * @return the storageRemote
     */
    public StorageRemote getStorageRemote() {
        return storageRemote;
    }

    /**
     * @param storageRemote
     *            the storageRemote to set
     */
    public void setStorageRemote(StorageRemote storageRemote) {
        this.storageRemote = storageRemote;
    }

    /**
     * @return the node
     */
    public Node getNode() {
        return node;
    }

    /**
     * @param node
     *            the node to set
     */
    public void setNode(Node node) {
        this.node = node;
    }

   

    public Integer getExistingUnit() {
		return existingUnit;
	}
	

	public void setExistingUnit(Integer existingUnit) {
		this.existingUnit = existingUnit;
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
     * @return the task
     */
    public Task getTask() {
        return task;
    }

    /**
     * @param task
     *            the task to set
     */
    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * @return the created
     */
    public Info getCreated() {
        return created;
    }

    /**
     * @param created
     *            the created to set
     */
    public void setCreated(Info created) {
        this.created = created;
    }

    /**
     * @return the modified
     */
    public Info getModified() {
        return modified;
    }

    /**
     * @param modified
     *            the modified to set
     */
    public void setModified(Info modified) {
        this.modified = modified;
    }





	@Override
	public String toString() {
		return "MgmHost [id=" + id + ", cluster=" + cluster + ", room=" + room + ", seat=" + seat + ", ntpServer="
				+ ntpServer + ", storageHost=" + storageHost + ", storageRemote=" + storageRemote + ", node=" + node
				+ ", units=" + units + ", maxUnit=" + maxUnit + ", existingUnit=" + existingUnit + ", maxUsage="
				+ maxUsage + ", enabled=" + enabled + ", desc=" + desc + ", task=" + task + ", created=" + created
				+ ", modified=" + modified + ", arch=" + arch + ", role=" + role + "]";
	}





	public static class MaxUsage {
        @JSONField(name = "cpu")
        private Integer cpu;

        @JSONField(name = "mem")
        private Integer memory;

        @JSONField(name = "storage_host")
        private Integer storageHost;

        @JSONField(name = "net_bandwidth")
        private Integer netBandwidth;

        /**
         * @return the cpu
         */
        public Integer getCpu() {
            return cpu;
        }

        /**
         * @param cpu
         *            the cpu to set
         */
        public void setCpu(Integer cpu) {
            this.cpu = cpu;
        }

        /**
         * @return the memory
         */
        public Integer getMemory() {
            return memory;
        }

        /**
         * @param memory
         *            the memory to set
         */
        public void setMemory(Integer memory) {
            this.memory = memory;
        }

        /**
         * @return the storageHost
         */
        public Integer getStorageHost() {
            return storageHost;
        }

        /**
         * @param storageHost
         *            the storageHost to set
         */
        public void setStorageHost(Integer storageHost) {
            this.storageHost = storageHost;
        }

        /**
         * @return the netBandwidth
         */
        public Integer getNetBandwidth() {
            return netBandwidth;
        }

        /**
         * @param netBandwidth
         *            the netBandwidth to set
         */
        public void setNetBandwidth(Integer netBandwidth) {
            this.netBandwidth = netBandwidth;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "MaxUsage [cpu=" + cpu + ", memory=" + memory + ", storageHost=" + storageHost + ", netBandwidth="
                    + netBandwidth + "]";
        }

    }

    public static class Unit {
        @JSONField(name = "id")
        private String id;

        @JSONField(name = "name")
        private String name;

        @JSONField(name = "type")
        private String type;

        @JSONField(name = "cpu")
        private Integer cpu;

        @JSONField(name = "mem")
        private Integer memory;

        @JSONField(name = "net_bandwidth")
        private Integer netBandwidth;

        @JSONField(name = "state")
        private String state;

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

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name
         *            the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the type
         */
        public String getType() {
            return type;
        }

        /**
         * @param type
         *            the type to set
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * @return the cpu
         */
        public Integer getCpu() {
            return cpu;
        }

        /**
         * @param cpu
         *            the cpu to set
         */
        public void setCpu(Integer cpu) {
            this.cpu = cpu;
        }

        /**
         * @return the mem
         */
        public Integer getMemory() {
            return memory;
        }

        /**
         * @param mem
         *            the mem to set
         */
        public void setMemory(Integer memory) {
            this.memory = memory;
        }

        /**
         * @return the netBandwidth
         */
        public Integer getNetBandwidth() {
            return netBandwidth;
        }

        /**
         * @param netBandwidth
         *            the netBandwidth to set
         */
        public void setNetBandwidth(Integer netBandwidth) {
            this.netBandwidth = netBandwidth;
        }

        /**
         * @return the state
         */
        public String getState() {
            return state;
        }

        /**
         * @param state
         *            the state to set
         */
        public void setState(String state) {
            this.state = state;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "Unit [id=" + id + ", name=" + name + ", type=" + type + ", cpu=" + cpu + ", memory=" + memory
                    + ", netBandwidth=" + netBandwidth + ", state=" + state + "]";
        }

    }

    public static class Node {
        @JSONField(name = "ip")
        private String ip;

        @JSONField(name = "name")
        private String name;

        @JSONField(name = "os")
        private String os;

        @JSONField(name = "cpu")
        private Cpu cpu;
        
        @JSONField(name = "pod")
        private Pod pod;

        @JSONField(name = "memory")
        private Memory memory;

        @JSONField(name = "net_bandwidth")
        private NetBandwidth netBandwidth;

        /**
         * @return the ip
         */
        public String getIp() {
            return ip;
        }

        /**
         * @param ip
         *            the ip to set
         */
        public void setIp(String ip) {
            this.ip = ip;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name
         *            the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the os
         */
        public String getOs() {
            return os;
        }

        /**
         * @param os
         *            the os to set
         */
        public void setOs(String os) {
            this.os = os;
        }

        /**
         * @return the cpu
         */
        public Cpu getCpu() {
            return cpu;
        }

        /**
         * @param cpu
         *            the cpu to set
         */
        public void setCpu(Cpu cpu) {
            this.cpu = cpu;
        }

        public Pod getPod() {
			return pod;
		}
		

		public void setPod(Pod pod) {
			this.pod = pod;
		}
		

		/**
         * @return the memory
         */
        public Memory getMemory() {
            return memory;
        }

        /**
         * @param memory
         *            the memory to set
         */
        public void setMemory(Memory memory) {
            this.memory = memory;
        }

        /**
         * @return the netBandwidth
         */
        public NetBandwidth getNetBandwidth() {
            return netBandwidth;
        }

        /**
         * @param netBandwidth
         *            the netBandwidth to set
         */
        public void setNetBandwidth(NetBandwidth netBandwidth) {
            this.netBandwidth = netBandwidth;
        }


        @Override
		public String toString() {
			return "Node [ip=" + ip + ", name=" + name + ", os=" + os + ", cpu=" + cpu + ", pod=" + pod + ", memory="
					+ memory + ", netBandwidth=" + netBandwidth + "]";
		}



		public static class NetBandwidth {
            @JSONField(name = "capacity")
            private Long capacity;

            @JSONField(name = "used")
            private Long used;

            /**
             * @return the capacity
             */
            public Long getCapacity() {
                return capacity;
            }

            /**
             * @param capacity
             *            the capacity to set
             */
            public void setCapacity(Long capacity) {
                this.capacity = capacity;
            }

            /**
             * @return the used
             */
            public Long getUsed() {
                return used;
            }

            /**
             * @param used
             *            the used to set
             */
            public void setUsed(Long used) {
                this.used = used;
            }

            /*
             * (non-Javadoc)
             * 
             * @see java.lang.Object#toString()
             */
            @Override
            public String toString() {
                return "NetBandwidth [capacity=" + capacity + ", used=" + used + "]";
            }

        }

        public static class Memory {
            @JSONField(name = "capacity")
            private BigInteger capacity;

            @JSONField(name = "used")
            private BigInteger used;

            /**
             * @return the capacity
             */
            public BigInteger getCapacity() {
                return capacity;
            }

            /**
             * @param capacity
             *            the capacity to set
             */
            public void setCapacity(BigInteger capacity) {
                this.capacity = capacity;
            }

            /**
             * @return the used
             */
            public BigInteger getUsed() {
                return used;
            }

            /**
             * @param used
             *            the used to set
             */
            public void setUsed(BigInteger used) {
                this.used = used;
            }

            /*
             * (non-Javadoc)
             * 
             * @see java.lang.Object#toString()
             */
            @Override
            public String toString() {
                return "Mem [capacity=" + capacity + ", used=" + used + "]";
            }

        }

        public static class Cpu {
            @JSONField(name = "capacity")
            private Integer capacity;

            @JSONField(name = "used")
            private Integer used;

            /**
             * @return the total
             */
           
            /**
             * @return the used
             */
            public Integer getUsed() {
                return used;
            }

            public Integer getCapacity() {
				return capacity;
			}
			

			public void setCapacity(Integer capacity) {
				this.capacity = capacity;
			}
			

			/**
             * @param used
             *            the used to set
             */
            public void setUsed(Integer used) {
                this.used = used;
            }

            /*
             * (non-Javadoc)
             * 
             * @see java.lang.Object#toString()
             */
            @Override
			public String toString() {
				return "Cpu [capacity=" + capacity + ", used=" + used + "]";
			}

        }
        
        public static class Pod {
            @JSONField(name = "capacity")
            private Integer capacity;

            @JSONField(name = "used")
            private Integer used;

            /**
             * @return the total
             */
           
            /**
             * @return the used
             */
            public Integer getUsed() {
                return used;
            }

            public Integer getCapacity() {
				return capacity;
			}
			

			public void setCapacity(Integer capacity) {
				this.capacity = capacity;
			}
			

			/**
             * @param used
             *            the used to set
             */
            public void setUsed(Integer used) {
                this.used = used;
            }

            /*
             * (non-Javadoc)
             * 
             * @see java.lang.Object#toString()
             */
            @Override
			public String toString() {
				return "Pod [capacity=" + capacity + ", used=" + used + "]";
			}

        }
    }

    public static class StorageRemote {
        @JSONField(name = "id")
        private String id;

        @JSONField(name = "name")
        private String name;

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

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name
         *            the name to set
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
            return "StorageRemote [id=" + id + ", name=" + name + "]";
        }

    }

    public static class StorageNfs {
        @JSONField(name = "id")
        private String id;

        @JSONField(name = "name")
        private String name;

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

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name
         *            the name to set
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
            return "StorageNfs [id=" + id + ", name=" + name + "]";
        }

    }

    public static class StorageHost {

        @JSONField(name = "performance")
        private String performance;

        @JSONField(name = "paths")
        private List<String> paths;

        @JSONField(name = "capacity")
        private BigInteger  capacity;

        @JSONField(name = "used")
        private BigInteger used;

        /**
         * @return the performance
         */
        public String getPerformance() {
            return performance;
        }

        /**
         * @param performance
         *            the performance to set
         */
        public void setPerformance(String performance) {
            this.performance = performance;
        }

        /**
         * @return the paths
         */
        public List<String> getPaths() {
            return paths;
        }

        /**
         * @param paths
         *            the paths to set
         */
        public void setPaths(List<String> paths) {
            this.paths = paths;
        }

        /**
         * @return the capacity
         */
        public BigInteger getCapacity() {
            return capacity;
        }

        /**
         * @param capacity
         *            the capacity to set
         */
        public void setCapacity(BigInteger capacity) {
            this.capacity = capacity;
        }

        /**
         * @return the used
         */
        public BigInteger getUsed() {
            return used;
        }

        /**
         * @param used
         *            the used to set
         */
        public void setUsed(BigInteger used) {
            this.used = used;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "StorageHost [performance=" + performance + ", paths=" + paths + ", capacity=" + capacity + ", used="
                    + used + "]";
        }

    }

    public static class Cluster {

        @JSONField(name = "id")
        private String id;

        @JSONField(name = "name")
        private String name;

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

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name
         *            the name to set
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
            return "Cluster [id=" + id + ", name=" + name + "]";
        }

    }

}
