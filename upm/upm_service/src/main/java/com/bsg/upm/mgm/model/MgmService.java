package com.bsg.upm.mgm.model;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class MgmService implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JSONField(name = "id")
    private String id;

    @JSONField(name = "tag")
    private String groupName;

    @JSONField(name = "high_available")
    private boolean highAvailable;

    @JSONField(name = "backup_file_sum")
    private Long backupFileSize;

    @JSONField(name = "max_container")
    private Integer maxContainer;

    @JSONField(name = "architecture")
    private Architecture architecture;

    @JSONField(name = "units")
    private List<Unit> units;

    /**
     * 获取id
     * 
     * @return id id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     * 
     * @param id
     *            id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取groupName
     * 
     * @return groupName groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置groupName
     * 
     * @param groupName
     *            groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 获取highAvailable
     * 
     * @return highAvailable highAvailable
     */
    public boolean isHighAvailable() {
        return highAvailable;
    }

    /**
     * 设置highAvailable
     * 
     * @param highAvailable
     *            highAvailable
     */
    public void setHighAvailable(boolean highAvailable) {
        this.highAvailable = highAvailable;
    }

    /**
     * 获取backupFileSize
     * 
     * @return backupFileSize backupFileSize
     */
    public Long getBackupFileSize() {
        return backupFileSize;
    }

    /**
     * 设置backupFileSize
     * 
     * @param backupFileSize
     *            backupFileSize
     */
    public void setBackupFileSize(Long backupFileSize) {
        this.backupFileSize = backupFileSize;
    }

    /**
     * 获取maxContainer
     * 
     * @return maxContainer maxContainer
     */
    public Integer getMaxContainer() {
        return maxContainer;
    }

    /**
     * 设置maxContainer
     * 
     * @param maxContainer
     *            maxContainer
     */
    public void setMaxContainer(Integer maxContainer) {
        this.maxContainer = maxContainer;
    }

    /**
     * 获取architecture
     * 
     * @return architecture architecture
     */
    public Architecture getArchitecture() {
        return architecture;
    }

    /**
     * 设置architecture
     * 
     * @param architecture
     *            architecture
     */
    public void setArchitecture(Architecture architecture) {
        this.architecture = architecture;
    }

    /**
     * 获取units
     * 
     * @return units units
     */
    public List<Unit> getUnits() {
        return units;
    }

    /**
     * 设置units
     * 
     * @param units
     *            units
     */
    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MgmService [id=" + id + ", groupName=" + groupName + ", highAvailable=" + highAvailable
                + ", backupFileSize=" + backupFileSize + ", maxContainer=" + maxContainer + ", architecture="
                + architecture + ", units=" + units + "]";
    }

    public static class Architecture {
        @JSONField(name = "mode")
        private String mode;

        @JSONField(name = "master")
        private Integer master;

        @JSONField(name = "standby")
        private Integer standby;

        @JSONField(name = "slave")
        private Integer slave;

        /**
         * 获取mode
         * 
         * @return mode mode
         */
        public String getMode() {
            return mode;
        }

        /**
         * 设置mode
         * 
         * @param mode
         *            mode
         */
        public void setMode(String mode) {
            this.mode = mode;
        }

        /**
         * 获取master
         * 
         * @return master master
         */
        public Integer getMaster() {
            return master;
        }

        /**
         * 设置master
         * 
         * @param master
         *            master
         */
        public void setMaster(Integer master) {
            this.master = master;
        }

        /**
         * 获取standby
         * 
         * @return standby standby
         */
        public Integer getStandby() {
            return standby;
        }

        /**
         * 设置standby
         * 
         * @param standby
         *            standby
         */
        public void setStandby(Integer standby) {
            this.standby = standby;
        }

        /**
         * 获取slave
         * 
         * @return slave slave
         */
        public Integer getSlave() {
            return slave;
        }

        /**
         * 设置slave
         * 
         * @param slave
         *            slave
         */
        public void setSlave(Integer slave) {
            this.slave = slave;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "Architecture [mode=" + mode + ", master=" + master + ", standby=" + standby + ", slave=" + slave
                    + "]";
        }

    }

    public static class Unit {
        @JSONField(name = "unit")
        private Info info;

        @JSONField(name = "image")
        private Image image;

        @JSONField(name = "engine")
        private Engine engine;

        @JSONField(name = "networkings")
        private List<Networking> networkings;

        @JSONField(name = "volumes")
        private List<Volume> volumes;

        @JSONField(name = "container")
        private Container container;

        @JSONField(name = "container_config")
        private ContainerCfg containerCfg;

        /**
         * 获取info
         * 
         * @return info info
         */
        public Info getInfo() {
            return info;
        }

        /**
         * 设置info
         * 
         * @param info
         *            info
         */
        public void setInfo(Info info) {
            this.info = info;
        }

        /**
         * 获取image
         * 
         * @return image image
         */
        public Image getImage() {
            return image;
        }

        /**
         * 设置image
         * 
         * @param image
         *            image
         */
        public void setImage(Image image) {
            this.image = image;
        }

        /**
         * 获取engine
         * 
         * @return engine engine
         */
        public Engine getEngine() {
            return engine;
        }

        /**
         * 设置engine
         * 
         * @param engine
         *            engine
         */
        public void setEngine(Engine engine) {
            this.engine = engine;
        }

        /**
         * 获取networkings
         * 
         * @return networkings networkings
         */
        public List<Networking> getNetworkings() {
            return networkings;
        }

        /**
         * 设置networkings
         * 
         * @param networkings
         *            networkings
         */
        public void setNetworkings(List<Networking> networkings) {
            this.networkings = networkings;
        }

        /**
         * 获取volumes
         * 
         * @return volumes volumes
         */
        public List<Volume> getVolumes() {
            return volumes;
        }

        /**
         * 设置volumes
         * 
         * @param volumes
         *            volumes
         */
        public void setVolumes(List<Volume> volumes) {
            this.volumes = volumes;
        }

        /**
         * 获取container
         * 
         * @return container container
         */
        public Container getContainer() {
            return container;
        }

        /**
         * 设置container
         * 
         * @param container
         *            container
         */
        public void setContainer(Container container) {
            this.container = container;
        }

        /**
         * 获取containerCfg
         * 
         * @return containerCfg containerCfg
         */
        public ContainerCfg getContainerCfg() {
            return containerCfg;
        }

        /**
         * 设置containerCfg
         * 
         * @param containerCfg
         *            containerCfg
         */
        public void setContainerCfg(ContainerCfg containerCfg) {
            this.containerCfg = containerCfg;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "Unit [info=" + info + ", image=" + image + ", engine=" + engine + ", networkings=" + networkings
                    + ", volumes=" + volumes + ", container=" + container + ", containerCfg=" + containerCfg + "]";
        }

        public static class Info {
            @JSONField(name = "id")
            private String id;

            @JSONField(name = "name")
            private String name;

            @JSONField(name = "type")
            private String type;

            /**
             * 获取id
             * 
             * @return id id
             */
            public String getId() {
                return id;
            }

            /**
             * 设置id
             * 
             * @param id
             *            id
             */
            public void setId(String id) {
                this.id = id;
            }

            /**
             * 获取name
             * 
             * @return name name
             */
            public String getName() {
                return name;
            }

            /**
             * 设置name
             * 
             * @param name
             *            name
             */
            public void setName(String name) {
                this.name = name;
            }

            /**
             * 获取type
             * 
             * @return type type
             */
            public String getType() {
                return type;
            }

            /**
             * 设置type
             * 
             * @param type
             *            type
             */
            public void setType(String type) {
                this.type = type;
            }

            /*
             * (non-Javadoc)
             * 
             * @see java.lang.Object#toString()
             */
            @Override
            public String toString() {
                return "Info [id=" + id + ", name=" + name + ", type=" + type + "]";
            }

        }

        public static class Image {
            @JSONField(name = "id")
            private String id;

            @JSONField(name = "name")
            private String name;

            @JSONField(name = "major")
            private Integer major;

            @JSONField(name = "minor")
            private Integer minor;

            @JSONField(name = "patch")
            private Integer patch;

            @JSONField(name = "build")
            private Integer build;

            /**
             * 获取id
             * 
             * @return id id
             */
            public String getId() {
                return id;
            }

            /**
             * 设置id
             * 
             * @param id
             *            id
             */
            public void setId(String id) {
                this.id = id;
            }

            /**
             * 获取name
             * 
             * @return name name
             */
            public String getName() {
                return name;
            }

            /**
             * 设置name
             * 
             * @param name
             *            name
             */
            public void setName(String name) {
                this.name = name;
            }

            /**
             * 获取major
             * 
             * @return major major
             */
            public Integer getMajor() {
                return major;
            }

            /**
             * 设置major
             * 
             * @param major
             *            major
             */
            public void setMajor(Integer major) {
                this.major = major;
            }

            /**
             * 获取minor
             * 
             * @return minor minor
             */
            public Integer getMinor() {
                return minor;
            }

            /**
             * 设置minor
             * 
             * @param minor
             *            minor
             */
            public void setMinor(Integer minor) {
                this.minor = minor;
            }

            /**
             * 获取patch
             * 
             * @return patch patch
             */
            public Integer getPatch() {
                return patch;
            }

            /**
             * 设置patch
             * 
             * @param patch
             *            patch
             */
            public void setPatch(Integer patch) {
                this.patch = patch;
            }

            /**
             * 获取build
             * 
             * @return build build
             */
            public Integer getBuild() {
                return build;
            }

            /**
             * 设置build
             * 
             * @param build
             *            build
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
                return "Image [id=" + id + ", name=" + name + ", major=" + major + ", minor=" + minor + ", patch="
                        + patch + ", build=" + build + "]";
            }
        }

        public static class Engine {
            @JSONField(name = "id")
            private String id;

            @JSONField(name = "node")
            private String hostId;

            @JSONField(name = "name")
            private String hostName;

            @JSONField(name = "addr")
            private String hostIp;

            /**
             * 获取id
             * 
             * @return id id
             */
            public String getId() {
                return id;
            }

            /**
             * 设置id
             * 
             * @param id
             *            id
             */
            public void setId(String id) {
                this.id = id;
            }

            /**
             * 获取hostId
             * 
             * @return hostId hostId
             */
            public String getHostId() {
                return hostId;
            }

            /**
             * 设置hostId
             * 
             * @param hostId
             *            hostId
             */
            public void setHostId(String hostId) {
                this.hostId = hostId;
            }

            /**
             * 获取hostName
             * 
             * @return hostName hostName
             */
            public String getHostName() {
                return hostName;
            }

            /**
             * 设置hostName
             * 
             * @param hostName
             *            hostName
             */
            public void setHostName(String hostName) {
                this.hostName = hostName;
            }

            /**
             * 获取hostIp
             * 
             * @return hostIp hostIp
             */
            public String getHostIp() {
                return hostIp;
            }

            /**
             * 设置hostIp
             * 
             * @param hostIp
             *            hostIp
             */
            public void setHostIp(String hostIp) {
                this.hostIp = hostIp;
            }

            /*
             * (non-Javadoc)
             * 
             * @see java.lang.Object#toString()
             */
            @Override
            public String toString() {
                return "Engine [id=" + id + ", hostId=" + hostId + ", hostName=" + hostName + ", hostIp=" + hostIp
                        + "]";
            }
        }

        public static class Networking {
            @JSONField(name = "ip_addr")
            private String ip;

            @JSONField(name = "gateway")
            private String gateway;

            @JSONField(name = "prefix")
            private Integer mask;

            @JSONField(name = "bandwidth")
            private Integer bandwidth;

            /**
             * 获取ip
             * 
             * @return ip ip
             */
            public String getIp() {
                return ip;
            }

            /**
             * 设置ip
             * 
             * @param ip
             *            ip
             */
            public void setIp(String ip) {
                this.ip = ip;
            }

            /**
             * 获取gateway
             * 
             * @return gateway gateway
             */
            public String getGateway() {
                return gateway;
            }

            /**
             * 设置gateway
             * 
             * @param gateway
             *            gateway
             */
            public void setGateway(String gateway) {
                this.gateway = gateway;
            }

            /**
             * 获取mask
             * 
             * @return mask mask
             */
            public Integer getMask() {
                return mask;
            }

            /**
             * 设置mask
             * 
             * @param mask
             *            mask
             */
            public void setMask(Integer mask) {
                this.mask = mask;
            }

            /**
             * 获取bandwidth
             * 
             * @return bandwidth bandwidth
             */
            public Integer getBandwidth() {
                return bandwidth;
            }

            /**
             * 设置bandwidth
             * 
             * @param bandwidth
             *            bandwidth
             */
            public void setBandwidth(Integer bandwidth) {
                this.bandwidth = bandwidth;
            }

            /*
             * (non-Javadoc)
             * 
             * @see java.lang.Object#toString()
             */
            @Override
            public String toString() {
                return "Networking [ip=" + ip + ", gateway=" + gateway + ", mask=" + mask + ", bandwidth=" + bandwidth
                        + "]";
            }

        }

        public static class Volume {
            @JSONField(name = "id")
            private String id;

            @JSONField(name = "name")
            private String name;

            @JSONField(name = "type")
            private String type;

            @JSONField(name = "driver")
            private String driver;

            @JSONField(name = "driver_type")
            private String driverType;

            @JSONField(name = "size")
            private Long size;

            /**
             * 获取id
             * 
             * @return id id
             */
            public String getId() {
                return id;
            }

            /**
             * 设置id
             * 
             * @param id
             *            id
             */
            public void setId(String id) {
                this.id = id;
            }

            /**
             * 获取name
             * 
             * @return name name
             */
            public String getName() {
                return name;
            }

            /**
             * 设置name
             * 
             * @param name
             *            name
             */
            public void setName(String name) {
                this.name = name;
            }

            /**
             * 获取type
             * 
             * @return type type
             */
            public String getType() {
                return type;
            }

            /**
             * 设置type
             * 
             * @param type
             *            type
             */
            public void setType(String type) {
                this.type = type;
            }

            /**
             * 获取driver
             * 
             * @return driver driver
             */
            public String getDriver() {
                return driver;
            }

            /**
             * 设置driver
             * 
             * @param driver
             *            driver
             */
            public void setDriver(String driver) {
                this.driver = driver;
            }

            /**
             * 获取driverType
             * 
             * @return driverType driverType
             */
            public String getDriverType() {
                return driverType;
            }

            /**
             * 设置driverType
             * 
             * @param driverType
             *            driverType
             */
            public void setDriverType(String driverType) {
                this.driverType = driverType;
            }

            /**
             * 获取size
             * 
             * @return size size
             */
            public Long getSize() {
                return size;
            }

            /**
             * 设置size
             * 
             * @param size
             *            size
             */
            public void setSize(Long size) {
                this.size = size;
            }

            /*
             * (non-Javadoc)
             * 
             * @see java.lang.Object#toString()
             */
            @Override
            public String toString() {
                return "Volume [id=" + id + ", name=" + name + ", type=" + type + ", driver=" + driver + ", driverType="
                        + driverType + ", size=" + size + "]";
            }

        }

        public static class Container {
            @JSONField(name = "Id")
            private String id;

            @JSONField(name = "State")
            private String state;

            @JSONField(name = "Status")
            private String status;

            /**
             * 获取id
             * 
             * @return id id
             */
            public String getId() {
                return id;
            }

            /**
             * 设置id
             * 
             * @param id
             *            id
             */
            public void setId(String id) {
                this.id = id;
            }

            /**
             * 获取state
             * 
             * @return state state
             */
            public String getState() {
                return state;
            }

            /**
             * 设置state
             * 
             * @param state
             *            state
             */
            public void setState(String state) {
                this.state = state;
            }

            /**
             * 获取status
             * 
             * @return status status
             */
            public String getStatus() {
                return status;
            }

            /**
             * 设置status
             * 
             * @param status
             *            status
             */
            public void setStatus(String status) {
                this.status = status;
            }

            /*
             * (non-Javadoc)
             * 
             * @see java.lang.Object#toString()
             */
            @Override
            public String toString() {
                return "Container [id=" + id + ", state=" + state + ", status=" + status + "]";
            }

        }

        public static class ContainerCfg {

            @JSONField(name = "HostConfig")
            private HostCfg hostCfg;

            /**
             * 获取hostCfg
             * 
             * @return hostCfg hostCfg
             */
            public HostCfg getHostCfg() {
                return hostCfg;
            }

            /**
             * 设置hostCfg
             * 
             * @param hostCfg
             *            hostCfg
             */
            public void setHostCfg(HostCfg hostCfg) {
                this.hostCfg = hostCfg;
            }

            /*
             * (non-Javadoc)
             * 
             * @see java.lang.Object#toString()
             */
            @Override
            public String toString() {
                return "ContainerCfg [hostCfg=" + hostCfg + "]";
            }

            public static class HostCfg {
                @JSONField(name = "Memory")
                private Long memSize;

                @JSONField(name = "ncpu")
                private Integer cpuCnt;

                /**
                 * 获取memSize
                 * 
                 * @return memSize memSize
                 */
                public Long getMemSize() {
                    return memSize;
                }

                /**
                 * 设置memSize
                 * 
                 * @param memSize
                 *            memSize
                 */
                public void setMemSize(Long memSize) {
                    this.memSize = memSize;
                }

                /**
                 * 获取cpuCnt
                 * 
                 * @return cpuCnt cpuCnt
                 */
                public Integer getCpuCnt() {
                    return cpuCnt;
                }

                /**
                 * 设置cpuCnt
                 * 
                 * @param cpuCnt
                 *            cpuCnt
                 */
                public void setCpuCnt(Integer cpuCnt) {
                    this.cpuCnt = cpuCnt;
                }

                /*
                 * (non-Javadoc)
                 * 
                 * @see java.lang.Object#toString()
                 */
                @Override
                public String toString() {
                    return "HostCfg [memSize=" + memSize + ", cpuCnt=" + cpuCnt + "]";
                }
            }
        }
    }
}
