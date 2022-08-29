package com.bsg.upm.query;

import java.io.Serializable;

public class MonitorParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 监控项
     */
    private String metric;

    /**
     * 压缩类型
     */
    private String compress;

    /**
     * 对象类型
     */
    private String objType;

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
     * 获取压缩类型
     * 
     * @return compress 压缩类型
     */
    public String getCompress() {
        return compress;
    }

    /**
     * 设置压缩类型
     * 
     * @param compress
     *            压缩类型
     */
    public void setCompress(String compress) {
        this.compress = compress;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MonitorParam [metric=" + metric + ", compress=" + compress + ", objType=" + objType + "]";
    }

}