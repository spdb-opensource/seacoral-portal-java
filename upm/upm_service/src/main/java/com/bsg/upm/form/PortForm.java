package com.bsg.upm.form;

import java.io.Serializable;

public class PortForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 站点编码
     */
    private Long siteId;

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
        return "PortForm [siteId=" + siteId + ", start=" + start + ", end=" + end + "]";
    }

}
