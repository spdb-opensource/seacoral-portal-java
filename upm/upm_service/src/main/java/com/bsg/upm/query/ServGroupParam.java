package com.bsg.upm.query;

import java.io.Serializable;

public class ServGroupParam implements Serializable {

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
     * 是否发送告警
     */
    private Boolean sendAlarm;

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
     * 获取是否发送告警
     * 
     * @return sendAlarm 是否发送告警
     */
    public Boolean getSendAlarm() {
        return sendAlarm;
    }

    /**
     * 设置是否发送告警
     * 
     * @param sendAlarm
     *            是否发送告警
     */
    public void setSendAlarm(Boolean sendAlarm) {
        this.sendAlarm = sendAlarm;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ServGroupParam [siteId=" + siteId + ", owner=" + owner + ", sendAlarm=" + sendAlarm + "]";
    }

}
