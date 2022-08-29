package com.bsg.upm.form;

import java.io.Serializable;

public class MetricTypeCfgForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 采集间隔时间(秒)
     */
    private Integer interval;

    /**
     * 获取采集间隔时间(秒)
     * 
     * @return interval 采集间隔时间(秒)
     */
    public Integer getInterval() {
        return interval;
    }

    /**
     * 设置采集间隔时间(秒)
     * 
     * @param interval
     *            采集间隔时间(秒)
     */
    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MetricTypeCfgForm [interval=" + interval + "]";
    }

}
