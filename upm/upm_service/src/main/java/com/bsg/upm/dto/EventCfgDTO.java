package com.bsg.upm.dto;

import java.io.Serializable;

public class EventCfgDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 对象类型
     */
    private String objType;

    /**
     * 监控项
     */
    private String metric;

    /**
     * 是否开启监控项
     */
    private Boolean metricOn;

    /**
     * 事件级别
     */
    private String level;

    /**
     * 开始阀值
     */
    private String startValue;

    /**
     * 结束阀值
     */
    private String endValue;

    /**
     * 是否可用
     */
    private Boolean enabled;

    /**
     * 是否告警
     */
    private Boolean alarmEnabled;

    /**
     * 描述
     */
    private String description;

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
     * 获取监控项
     * 
     * @return metric 监控项
     */
    public String getMetric() {
        return metric;
    }

    /**
     * 设置监控项
     * 
     * @param metric
     *            监控项
     */
    public void setMetric(String metric) {
        this.metric = metric;
    }

    /**
     * 获取是否开启监控项
     * 
     * @return metricOn 是否开启监控项
     */
    public Boolean getMetricOn() {
        return metricOn;
    }

    /**
     * 设置是否开启监控项
     * 
     * @param metricOn
     *            是否开启监控项
     */
    public void setMetricOn(Boolean metricOn) {
        this.metricOn = metricOn;
    }

    /**
     * 获取事件级别
     * 
     * @return level 事件级别
     */
    public String getLevel() {
        return level;
    }

    /**
     * 设置事件级别
     * 
     * @param level
     *            事件级别
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 获取开始阀值
     * 
     * @return startValue 开始阀值
     */
    public String getStartValue() {
        return startValue;
    }

    /**
     * 设置开始阀值
     * 
     * @param startValue
     *            开始阀值
     */
    public void setStartValue(String startValue) {
        this.startValue = startValue;
    }

    /**
     * 获取结束阀值
     * 
     * @return endValue 结束阀值
     */
    public String getEndValue() {
        return endValue;
    }

    /**
     * 设置结束阀值
     * 
     * @param endValue
     *            结束阀值
     */
    public void setEndValue(String endValue) {
        this.endValue = endValue;
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
     * 获取是否告警
     * 
     * @return alarmEnabled 是否告警
     */
    public Boolean getAlarmEnabled() {
        return alarmEnabled;
    }

    /**
     * 设置是否告警
     * 
     * @param alarmEnabled
     *            是否告警
     */
    public void setAlarmEnabled(Boolean alarmEnabled) {
        this.alarmEnabled = alarmEnabled;
    }

    /**
     * 获取描述
     * 
     * @return description 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     * 
     * @param description
     *            描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EventCfgDTO [objType=" + objType + ", metric=" + metric + ", metricOn=" + metricOn + ", level=" + level
                + ", startValue=" + startValue + ", endValue=" + endValue + ", enabled=" + enabled + ", alarmEnabled="
                + alarmEnabled + ", description=" + description + "]";
    }

}
