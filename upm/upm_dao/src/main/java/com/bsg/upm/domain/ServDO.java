package com.bsg.upm.domain;

import java.io.Serializable;
import java.util.List;

public class ServDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private String servGroupId;
    private String type;
    private Integer majorVersion;
    private Integer minorVersion;
    private Integer patchVersion;
    private Integer buildVersion;
    private String archMode;
    private Integer unitCnt;
    private Double cpuCnt;
    private Double memSize;
    private String diskType;
    private Integer dataSize;
    private Integer logSize;
    private Integer port;
    private String relateId;
    private List<UnitDO> units;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServGroupId() {
        return servGroupId;
    }

    public void setServGroupId(String servGroupId) {
        this.servGroupId = servGroupId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(Integer majorVersion) {
        this.majorVersion = majorVersion;
    }

    public Integer getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(Integer minorVersion) {
        this.minorVersion = minorVersion;
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

    public String getArchMode() {
        return archMode;
    }

    public void setArchMode(String archMode) {
        this.archMode = archMode;
    }

    public Integer getUnitCnt() {
        return unitCnt;
    }

    public void setUnitCnt(Integer unitCnt) {
        this.unitCnt = unitCnt;
    }

    public Double getCpuCnt() {
        return cpuCnt;
    }

    public void setCpuCnt(Double cpuCnt) {
        this.cpuCnt = cpuCnt;
    }

    public Double getMemSize() {
        return memSize;
    }

    public void setMemSize(Double memSize) {
        this.memSize = memSize;
    }

    public String getDiskType() {
        return diskType;
    }

    public void setDiskType(String diskType) {
        this.diskType = diskType;
    }

    public Integer getDataSize() {
        return dataSize;
    }

    public void setDataSize(Integer dataSize) {
        this.dataSize = dataSize;
    }

    public Integer getLogSize() {
        return logSize;
    }

    public void setLogSize(Integer logSize) {
        this.logSize = logSize;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getRelateId() {
        return relateId;
    }

    public void setRelateId(String relateId) {
        this.relateId = relateId;
    }

    public List<UnitDO> getUnits() {
        return units;
    }

    public void setUnits(List<UnitDO> units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "ServDO [id=" + id + ", servGroupId=" + servGroupId + ", type=" + type + ", majorVersion=" + majorVersion
                + ", minorVersion=" + minorVersion + ", patchVersion=" + patchVersion + ", buildVersion=" + buildVersion
                + ", archMode=" + archMode + ", unitCnt=" + unitCnt + ", cpuCnt=" + cpuCnt + ", memSize=" + memSize
                + ", diskType=" + diskType + ", dataSize=" + dataSize + ", logSize=" + logSize + ", port=" + port
                + ", relateId=" + relateId + ", units=" + units + "]";
    }

}
