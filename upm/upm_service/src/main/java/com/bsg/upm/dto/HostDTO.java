package com.bsg.upm.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import com.bsg.upm.mgm.model.MgmHost.Node;
import com.bsg.upm.mgm.model.MgmHost.Node.Pod;
import com.bsg.upm.mgm.model.MgmHost.Unit;

/**
 * @author ZhuXH
 * @date 2019年7月4日
 */
public class HostDTO extends BaseDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主机编码
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 站点
     */
    private SiteDTO site;

    /**
     * 集群
     */
    private ClusterDTO cluster;

    /**
     * 主机IP
     */
    private String ip;

    /**
     * 机房
     */
    private String room;

    /**
     * 机位
     */
    private String seat;

    /**
     * NTP服务器地址
     */
    private String ntpServer;

    /**
     * 存储设备
     */
    private List<StorageDTO> storage;

    /**
     * 外置存储
     */
    private StorageRemoteDTO storageRemote;

    /**
     * 最大单元数量
     */
    private Integer maxUnitCnt;

    /**
     * 已存在单元数量
     */
    private Integer existingUnitCnt;

    /**
     * 单元数量
     */
    private Integer unitCnt;

    /**
     * 最大使用率
     */
    private MaxUsageDTO maxUsage;

    /**
     * 单元列表
     */
    private List<Unit> units;
    /**
     * 操作系统
     */
    private String os;

    /**
     * cpu
     */
    private CpuDTO cpu;

    private PodDTO pod;

    /**
     * 内存
     */
    private MemDTO mem;

    /**
     * 带宽
     */
    private BandwidthDTO bandwidth;

    /**
     * 是否可用
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
     * 任务
     */
    private TaskDTO task;


    /**
     * 硬件架构
     */
    private String arch;
    
    /**
     * 主机角色(是否可被迁移）
     * dbscale-spare  dbscale-node
     */
    private String role;


    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/**
     * 获取 主机编码
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主机编码
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取 名称
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 站点
     *
     * @return site
     */
    public SiteDTO getSite() {
        return site;
    }

    /**
     * 设置站点
     *
     * @param site
     */
    public void setSite(SiteDTO site) {
        this.site = site;
    }

    /**
     * 获取 集群
     *
     * @return cluster
     */
    public ClusterDTO getCluster() {
        return cluster;
    }

    /**
     * 设置集群
     *
     * @param cluster
     */
    public void setCluster(ClusterDTO cluster) {
        this.cluster = cluster;
    }


    public PodDTO getPod() {
        return pod;
    }


    public void setPod(PodDTO pod) {
        this.pod = pod;
    }


    /**
     * 获取 主机IP
     *
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置主机IP
     *
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<Unit> getUnits() {
        return units;
    }


    public void setUnits(List<Unit> units) {
        this.units = units;
    }


    public String getArch() {
		return arch;
	}

	public void setArch(String arch) {
		this.arch = arch;
	}

	/**
     * 获取 机房
     *
     * @return room
     */
    public String getRoom() {
        return room;
    }

    /**
     * 设置机房
     *
     * @param room
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * 获取 机位
     *
     * @return seat
     */
    public String getSeat() {
        return seat;
    }

    /**
     * 设置机位
     *
     * @param seat
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
    public List<StorageDTO> getStorage() {
        return storage;
    }

    /**
     * @param 存储设备 the storage to set
     */
    public void setStorage(List<StorageDTO> storage) {
        this.storage = storage;
    }

    /**
     * 获取 外置存储
     *
     * @return storageRemote
     */
    public StorageRemoteDTO getStorageRemote() {
        return storageRemote;
    }

    /**
     * 设置外置存储
     *
     * @param storageRemote
     */
    public void setStorageRemote(StorageRemoteDTO storageRemote) {
        this.storageRemote = storageRemote;
    }

    /**
     * 获取 最大单元数量
     *
     * @return maxUIntegerCnt
     */
    public Integer getMaxUnitCnt() {
        return maxUnitCnt;
    }

    /**
     * 设置最大单元数量
     *
     * @param maxUIntegerCnt
     */
    public void setMaxUnitCnt(Integer maxUnitCnt) {
        this.maxUnitCnt = maxUnitCnt;
    }


    public Integer getExistingUnitCnt() {
        return existingUnitCnt;
    }


    public void setExistingUnitCnt(Integer existingUnitCnt) {
        this.existingUnitCnt = existingUnitCnt;
    }


    /**
     * 获取 单元数量
     *
     * @return unitCnt
     */
    public Integer getUnitCnt() {
        return unitCnt;
    }

    /**
     * 设置单元数量
     *
     * @param unitCnt
     */
    public void setUnitCnt(Integer unitCnt) {
        this.unitCnt = unitCnt;
    }

    /**
     * 获取 最大使用率
     *
     * @return maxUsage
     */
    public MaxUsageDTO getMaxUsage() {
        return maxUsage;
    }

    /**
     * 设置最大使用率
     *
     * @param maxUsage
     */
    public void setMaxUsage(MaxUsageDTO maxUsage) {
        this.maxUsage = maxUsage;
    }

    /**
     * 获取 操作系统
     *
     * @return os
     */
    public String getOs() {
        return os;
    }

    /**
     * 设置操作系统
     *
     * @param os
     */
    public void setOs(String os) {
        this.os = os;
    }

    /**
     * 获取 cpu
     *
     * @return cpu
     */
    public CpuDTO getCpu() {
        return cpu;
    }

    /**
     * 设置cpu
     *
     * @param cpu
     */
    public void setCpu(CpuDTO cpu) {
        this.cpu = cpu;
    }

    /**
     * 获取 内存
     *
     * @return mem
     */
    public MemDTO getMem() {
        return mem;
    }

    /**
     * 设置内存
     *
     * @param mem
     */
    public void setMem(MemDTO mem) {
        this.mem = mem;
    }

    /**
     * 获取 带宽
     *
     * @return bandwidth
     */
    public BandwidthDTO getBandwidth() {
        return bandwidth;
    }

    /**
     * 设置带宽
     *
     * @param bandwidth
     */
    public void setBandwidth(BandwidthDTO bandwidth) {
        this.bandwidth = bandwidth;
    }

    /**
     * 获取 是否可用
     *
     * @return enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置是否可用
     *
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取 描述
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取 创建
     *
     * @return created
     */
    public InfoDTO getCreated() {
        return created;
    }

    /**
     * 设置创建
     *
     * @param created
     */
    public void setCreated(InfoDTO created) {
        this.created = created;
    }

    /**
     * 获取 变更
     *
     * @return modified
     */
    public InfoDTO getModified() {
        return modified;
    }

    /**
     * 设置变更
     *
     * @param modified
     */
    public void setModified(InfoDTO modified) {
        this.modified = modified;
    }

    /**
     * 获取 任务
     *
     * @return task
     */
    public TaskDTO getTask() {
        return task;
    }

    /**
     * 设置任务
     *
     * @param task
     */
    public void setTask(TaskDTO task) {
        this.task = task;
    }


    @Override
	public String toString() {
		return "HostDTO [id=" + id + ", name=" + name + ", site=" + site + ", cluster=" + cluster + ", ip=" + ip
				+ ", room=" + room + ", seat=" + seat + ", ntpServer=" + ntpServer + ", storage=" + storage
				+ ", storageRemote=" + storageRemote + ", maxUnitCnt=" + maxUnitCnt + ", existingUnitCnt="
				+ existingUnitCnt + ", unitCnt=" + unitCnt + ", maxUsage=" + maxUsage + ", units=" + units + ", os="
				+ os + ", cpu=" + cpu + ", pod=" + pod + ", mem=" + mem + ", bandwidth=" + bandwidth + ", enabled="
				+ enabled + ", description=" + description + ", created=" + created + ", modified=" + modified
				+ ", task=" + task + ", arch=" + arch + ", role=" + role + "]";
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

    public class ClusterDTO {

        /**
         * 集群编码
         */
        private String id;

        /**
         * 集群名称
         */
        private String name;

        /**
         * 获取 集群编码
         *
         * @return id
         */
        public String getId() {
            return id;
        }

        /**
         * 设置集群编码
         *
         * @param id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * 获取 集群名称
         *
         * @return name
         */
        public String getName() {
            return name;
        }

        /**
         * 设置集群名称
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
            return "ClusterDTO [id=" + id + ", name=" + name + "]";
        }

    }

    public class StorageDTO {

        /**
         * 类型
         */
        private TypeDTO performance;

        /**
         * 存储设备路径
         */
        private List<String> path;

        /**
         * 存储设备总量
         */
        private BigInteger totalSize;

        /**
         * 存储设备使用量
         */
        private BigInteger usedSize;

        /**
         * @return the 类型
         */
        public TypeDTO getPerformance() {
            return performance;
        }

        /**
         * @param 类型 the performance to set
         */
        public void setPerformance(TypeDTO performance) {
            this.performance = performance;
        }

        /**
         * @return the 存储设备路径
         */
        public List<String> getPath() {
            return path;
        }

        /**
         * @param 存储设备路径 the path to set
         */
        public void setPath(List<String> path) {
            this.path = path;
        }

        /**
         * 获取 存储设备总量
         *
         * @return totalSize
         */
        public BigInteger getTotalSize() {
            return totalSize;
        }

        /**
         * 设置存储设备总量
         *
         * @param totalSize
         */
        public void setTotalSize(BigInteger totalSize) {
            this.totalSize = totalSize;
        }

        /**
         * 获取 存储设备使用量
         *
         * @return usedSize
         */
        public BigInteger getUsedSize() {
            return usedSize;
        }

        /**
         * 设置存储设备使用量
         *
         * @param usedSize
         */
        public void setUsedSize(BigInteger usedSize) {
            this.usedSize = usedSize;
        }

        /*
         * (non-Javadoc)
         *
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "StorageDTO [performance=" + performance + ", path=" + path + ", totalSize=" + totalSize
                    + ", usedSize=" + usedSize + "]";
        }

    }

    public class StorageRemoteDTO {

        /**
         * 外置存储编码
         */
        private String id;

        /**
         * 外置存储名称
         */
        private String name;

        /**
         * 获取 外置存储编码
         *
         * @return id
         */
        public String getId() {
            return id;
        }

        /**
         * 设置外置存储编码
         *
         * @param id
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * 获取 外置存储名称
         *
         * @return name
         */
        public String getName() {
            return name;
        }

        /**
         * 设置外置存储名称
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
            return "StorageRemoteDTO [id=" + id + ", name=" + name + "]";
        }
    }

    public class MaxUsageDTO {

        /**
         * cpu最大使用率
         */
        private Integer cpu;

        /**
         * 内存最大使用率
         */
        private Integer mem;

        /**
         * 带宽最大使用率
         */
        private Integer bandwidth;

        /**
         * 存储最大使用率
         */
        private Integer storage;

        /**
         * 获取 cpu最大使用率
         *
         * @return cpu最大使用率
         */
        public Integer getCpu() {
            return cpu;
        }

        /**
         * 设置cpu最大使用率
         *
         * @param cpu最大使用率
         */
        public void setCpu(Integer cpu) {
            this.cpu = cpu;
        }

        /**
         * 获取 内存最大使用率
         *
         * @return mem
         */
        public Integer getMem() {
            return mem;
        }

        /**
         * 设置内存最大使用率
         *
         * @param mem
         */
        public void setMem(Integer mem) {
            this.mem = mem;
        }

        /**
         * 获取 带宽最大使用率
         *
         * @return bandwidth
         */
        public Integer getBandwidth() {
            return bandwidth;
        }

        /**
         * 设置带宽最大使用率
         *
         * @param bandwidth
         */
        public void setBandwidth(Integer bandwidth) {
            this.bandwidth = bandwidth;
        }

        /**
         * 获取 存储最大使用率
         *
         * @return storage
         */
        public Integer getStorage() {
            return storage;
        }

        /**
         * 设置存储最大使用率
         *
         * @param storage
         */
        public void setStorage(Integer storage) {
            this.storage = storage;
        }

        /*
         * (non-Javadoc)
         *
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "MaxUsage [cpu=" + cpu + ", mem=" + mem + ", bandwidth=" + bandwidth + ", storage=" + storage + "]";
        }

    }

    public class CpuDTO {

        /**
         * cpu数量
         */
        private Integer total;

        /**
         * cpu使用量
         */
        private Integer usedCnt;

        /**
         * 获取 cpu数量
         *
         * @return total
         */
        public Integer getTotal() {
            return total;
        }

        /**
         * 设置cpu数量
         *
         * @param total
         */
        public void setTotal(Integer total) {
            this.total = total;
        }

        /**
         * 获取 cpu使用量
         *
         * @return usedCnt
         */
        public Integer getUsedCnt() {
            return usedCnt;
        }

        /**
         * 设置cpu使用量
         *
         * @param usedCnt
         */
        public void setUsedCnt(Integer usedCnt) {
            this.usedCnt = usedCnt;
        }

        /*
         * (non-Javadoc)
         *
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "CpuDTO [total=" + total + ", usedCnt=" + usedCnt + "]";
        }

    }

    public class MemDTO {

        /**
         * 内存总量
         */
        private BigInteger totalSize;

        /**
         * 内存使用量
         */
        private BigInteger usedSize;

        /**
         * 获取 内存总量
         *
         * @return totalSize
         */
        public BigInteger getTotalSize() {
            return totalSize;
        }

        /**
         * 设置内存总量
         *
         * @param totalSize
         */
        public void setTotalSize(BigInteger totalSize) {
            this.totalSize = totalSize;
        }

        /**
         * 获取 内存使用量
         *
         * @return usedSize
         */
        public BigInteger getUsedSize() {
            return usedSize;
        }

        /**
         * 设置内存使用量
         *
         * @param usedSize
         */
        public void setUsedSize(BigInteger usedSize) {
            this.usedSize = usedSize;
        }

        /*
         * (non-Javadoc)
         *
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "MemDTO [totalSize=" + totalSize + ", usedSize=" + usedSize + "]";
        }

    }

    public class PodDTO {

        /**
         * 容器总量
         */
        private Integer totalSize;

        /**
         * 容器使用量
         */
        private Integer usedSize;

        /**
         * 获取 容器总量
         *
         * @return totalSize
         */
        public Integer getTotalSize() {
            return totalSize;
        }

        /**
         * 设置容器总量
         *
         * @param totalSize
         */
        public void setTotalSize(Integer totalSize) {
            this.totalSize = totalSize;
        }

        /**
         * 获取 容器使用量
         *
         * @return usedSize
         */
        public Integer getUsedSize() {
            return usedSize;
        }

        /**
         * 设置容器使用量
         *
         * @param usedSize
         */
        public void setUsedSize(Integer usedSize) {
            this.usedSize = usedSize;
        }

        /*
         * (non-Javadoc)
         *
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "PodDTO [totalSize=" + totalSize + ", usedSize=" + usedSize + "]";
        }

    }

    public class BandwidthDTO {

        /**
         * 带宽总量
         */
        private Long totalSize;

        /**
         * 带宽使用量
         */
        private Long usedSize;

        /**
         * 获取 带宽总量
         *
         * @return totalSize
         */
        public Long getTotalSize() {
            return totalSize;
        }

        /**
         * 设置带宽总量
         *
         * @param totalSize
         */
        public void setTotalSize(Long totalSize) {
            this.totalSize = totalSize;
        }

        /**
         * 获取 带宽使用量
         *
         * @return usedSize
         */
        public Long getUsedSize() {
            return usedSize;
        }

        /**
         * 设置带宽使用量
         *
         * @param usedSize
         */
        public void setUsedSize(Long usedSize) {
            this.usedSize = usedSize;
        }

        /*
         * (non-Javadoc)
         *
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "BandwidthDTO [totalSize=" + totalSize + ", usedSize=" + usedSize + "]";
        }

    }

}
