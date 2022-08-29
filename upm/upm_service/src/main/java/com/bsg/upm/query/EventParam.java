package com.bsg.upm.query;

import java.io.Serializable;

public class EventParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 站点编码
     */
    private Long siteId;

    /**
     * 起始时间
     */
    private Long start;

    /**
     * 结束时间
     */
    private Long end;

    /**
     * 类型
     */
    private String type;

    /**
     * 所属者
     */
    private String owner;

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

    /**
     * 获取类型
     * 
     * @return type 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     * 
     * @param type
     *            类型
     */
    public void setType(String type) {
        this.type = type;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EventParam [siteId=" + siteId + ", start=" + start + ", end=" + end + ", type=" + type + ", owner="
                + owner + "]";
    }

}