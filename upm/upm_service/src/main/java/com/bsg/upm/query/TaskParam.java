package com.bsg.upm.query;

import java.io.Serializable;

public class TaskParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 站点编码
     */
    private Long siteId;

    /**
     * 所属者
     */
    private String owner;

    /**
     * 对象类型
     */
    private String objType;

    /**
     * 对象编码
     */
    private Long objId;

    /**
     * 起始时间
     */
    private Long start;

    /**
     * 结束时间
     */
    private Long end;

    /**
     * 获取站点编码
     * 
     * @return siteId 站点编码
     */
    public Long getSiteId() {
        return siteId;
    }

    /**
     * 设置站点编码
     * 
     * @param siteId
     *            站点编码
     */
    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    /**
     * 获取所属者
     * 
     * @return owner 所属者
     */
    public String getOwner() {
        return owner;
    }

    /**
     * 设置所属者
     * 
     * @param owner
     *            所属者
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * 获取对象类型
     * 
     * @return objType 对象类型
     */
    public String getObjType() {
        return objType;
    }

    /**
     * 设置对象类型
     * 
     * @param objType
     *            对象类型
     */
    public void setObjType(String objType) {
        this.objType = objType;
    }

    /**
     * 获取对象编码
     * 
     * @return objId 对象编码
     */
    public Long getObjId() {
        return objId;
    }

    /**
     * 设置对象编码
     * 
     * @param objId
     *            对象编码
     */
    public void setObjId(Long objId) {
        this.objId = objId;
    }

    /**
     * 获取起始时间
     * 
     * @return start 起始时间
     */
    public Long getStart() {
        return start;
    }

    /**
     * 设置起始时间
     * 
     * @param start
     *            起始时间
     */
    public void setStart(Long start) {
        this.start = start;
    }

    /**
     * 获取结束时间
     * 
     * @return end 结束时间
     */
    public Long getEnd() {
        return end;
    }

    /**
     * 设置结束时间
     * 
     * @param end
     *            结束时间
     */
    public void setEnd(Long end) {
        this.end = end;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "TaskParam [siteId=" + siteId + ", owner=" + owner + ", objType=" + objType + ", objId=" + objId
                + ", start=" + start + ", end=" + end + "]";
    }

}
