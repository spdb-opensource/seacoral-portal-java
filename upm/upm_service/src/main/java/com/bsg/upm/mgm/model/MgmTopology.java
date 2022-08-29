package com.bsg.upm.mgm.model;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class MgmTopology implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JSONField(name = "Service")
    private Service service;

    @JSONField(name = "Nodes")
    private List<Node> nodes;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "CmTopology [service=" + service + ", nodes=" + nodes + "]";
    }

    public class Service {
        @JSONField(name = "name")
        private String name;

        @JSONField(name = "version")
        private String version;

        @JSONField(name = "checkid")
        private Long checkid;

        @JSONField(name = "swm_address")
        private String swmAddress;

        @JSONField(name = "create_index")
        private String createIndex;

        @JSONField(name = "modify_index")
        private String modifyIndex;

        @JSONField(name = "ensure_availability")
        private Boolean ensureAvailability;

        @JSONField(name = "ensure_consistency")
        private Boolean ensureConsistency;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public Long getCheckid() {
            return checkid;
        }

        public void setCheckid(Long checkid) {
            this.checkid = checkid;
        }

        public String getSwmAddress() {
            return swmAddress;
        }

        public void setSwmAddress(String swmAddress) {
            this.swmAddress = swmAddress;
        }

        public String getCreateIndex() {
            return createIndex;
        }

        public void setCreateIndex(String createIndex) {
            this.createIndex = createIndex;
        }

        public String getModifyIndex() {
            return modifyIndex;
        }

        public void setModifyIndex(String modifyIndex) {
            this.modifyIndex = modifyIndex;
        }

        public Boolean getEnsureAvailability() {
            return ensureAvailability;
        }

        public void setEnsureAvailability(Boolean ensureAvailability) {
            this.ensureAvailability = ensureAvailability;
        }

        public Boolean getEnsureConsistency() {
            return ensureConsistency;
        }

        public void setEnsureConsistency(Boolean ensureConsistency) {
            this.ensureConsistency = ensureConsistency;
        }

        @Override
        public String toString() {
            return "Service [name=" + name + ", version=" + version + ", checkid=" + checkid + ", swmAddress="
                    + swmAddress + ", createIndex=" + createIndex + ", modifyIndex=" + modifyIndex
                    + ", ensureAvailability=" + ensureAvailability + ", ensureConsistency=" + ensureConsistency + "]";
        }

    }

    public class Node {
        @JSONField(name = "node")
        private String node;

        @JSONField(name = "address")
        private String address;

        @JSONField(name = "port")
        private Integer port;

        @JSONField(name = "status")
        private String status;

        @JSONField(name = "replication")
        private Replication replication;

        @JSONField(name = "candidate")
        private Boolean candidate;

        @JSONField(name = "role")
        private String role;

        @JSONField(name = "isolate")
        private Boolean isolate;

        @JSONField(name = "read_write_mode")
        private String readWriteMode;

        @JSONField(name = "master_host")
        private String masterHost;

        @JSONField(name = "consul")
        private Consul consul;

        @JSONField(name = "maintain")
        private Boolean maintain;

        @JSONField(name = "version")
        private String version;

        public String getNode() {
            return node;
        }

        public void setNode(String node) {
            this.node = node;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Replication getReplication() {
            return replication;
        }

        public void setReplication(Replication replication) {
            this.replication = replication;
        }

        public Boolean getCandidate() {
            return candidate;
        }

        public void setCandidate(Boolean candidate) {
            this.candidate = candidate;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public Boolean getIsolate() {
            return isolate;
        }

        public void setIsolate(Boolean isolate) {
            this.isolate = isolate;
        }

        public String getReadWriteMode() {
            return readWriteMode;
        }

        public void setReadWriteMode(String readWriteMode) {
            this.readWriteMode = readWriteMode;
        }

        public String getMasterHost() {
            return masterHost;
        }

        public void setMasterHost(String masterHost) {
            this.masterHost = masterHost;
        }

        public Consul getConsul() {
            return consul;
        }

        public void setConsul(Consul consul) {
            this.consul = consul;
        }

        public Boolean getMaintain() {
            return maintain;
        }

        public void setMaintain(Boolean maintain) {
            this.maintain = maintain;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        @Override
        public String toString() {
            return "Node [node=" + node + ", address=" + address + ", port=" + port + ", status=" + status
                    + ", replication=" + replication + ", candidate=" + candidate + ", role=" + role + ", isolate="
                    + isolate + ", readWriteMode=" + readWriteMode + ", masterHost=" + masterHost + ", consul=" + consul
                    + ", maintain=" + maintain + ", version=" + version + "]";
        }

        public class Replication {

            @JSONField(name = "io_thread")
            private String ioThread;

            @JSONField(name = "sql_thread")
            private String sqlThread;

            @JSONField(name = "repl_mode")
            private String replMode;

            @JSONField(name = "rpel_err_counter")
            private Long rpelErrCounter;

            public String getIoThread() {
                return ioThread;
            }

            public void setIoThread(String ioThread) {
                this.ioThread = ioThread;
            }

            public String getSqlThread() {
                return sqlThread;
            }

            public void setSqlThread(String sqlThread) {
                this.sqlThread = sqlThread;
            }

            public String getReplMode() {
                return replMode;
            }

            public void setReplMode(String replMode) {
                this.replMode = replMode;
            }

            public Long getRpelErrCounter() {
                return rpelErrCounter;
            }

            public void setRpelErrCounter(Long rpelErrCounter) {
                this.rpelErrCounter = rpelErrCounter;
            }

            @Override
            public String toString() {
                return "Replication [ioThread=" + ioThread + ", sqlThread=" + sqlThread + ", replMode=" + replMode
                        + ", rpelErrCounter=" + rpelErrCounter + "]";
            }

        }

        public class Consul {
            @JSONField(name = "sstatus")
            private String sstatus;

            @JSONField(name = "astatus")
            private String astatus;

            public String getSstatus() {
                return sstatus;
            }

            public void setSstatus(String sstatus) {
                this.sstatus = sstatus;
            }

            public String getAstatus() {
                return astatus;
            }

            public void setAstatus(String astatus) {
                this.astatus = astatus;
            }

            @Override
            public String toString() {
                return "Consul [sstatus=" + sstatus + ", astatus=" + astatus + "]";
            }

        }

    }

}
