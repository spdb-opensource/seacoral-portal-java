package com.bsg.upm.param;

import java.io.Serializable;

public class ServGroupDAOParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 站点编码
     */
    private Long siteId;

    /**
     * 类型
     */
    private String type;

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
        return "ServGroupDAOParam [siteId=" + siteId + ", type=" + type + ", sendAlarm=" + sendAlarm + "]";
    }

}
