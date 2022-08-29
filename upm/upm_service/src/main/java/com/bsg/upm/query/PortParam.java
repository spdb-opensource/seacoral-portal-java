package com.bsg.upm.query;

import java.io.Serializable;

public class PortParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 站点编码
     */
    private Long siteId;

    /**
     * 是否可用
     */
    private Boolean enabled;

    /**
     * 是否被使用
     */
    private Boolean used;

    /**
     * 起始端口号
     */
    private Integer start;

    /**
     * 结束端口号
     */
    private Integer end;

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
     * 获取是否可用
     * 
     * @return enabled 是否可用
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置是否可用
     * 
     * @param enabled
     *            是否可用
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取是否被使用
     * 
     * @return used 是否被使用
     */
    public Boolean getUsed() {
        return used;
    }

    /**
     * 设置是否被使用
     * 
     * @param used
     *            是否被使用
     */
    public void setUsed(Boolean used) {
        this.used = used;
    }

    /**
     * 获取起始端口号
     * 
     * @return start 起始端口号
     */
    public Integer getStart() {
        return start;
    }

    /**
     * 设置起始端口号
     * 
     * @param start
     *            起始端口号
     */
    public void setStart(Integer start) {
        this.start = start;
    }

    /**
     * 获取结束端口号
     * 
     * @return end 结束端口号
     */
    public Integer getEnd() {
        return end;
    }

    /**
     * 设置结束端口号
     * 
     * @param end
     *            结束端口号
     */
    public void setEnd(Integer end) {
        this.end = end;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PortDAOParam [siteId=" + siteId + ", enabled=" + enabled + ", used=" + used + ", start=" + start
                + ", end=" + end + "]";
    }

}
