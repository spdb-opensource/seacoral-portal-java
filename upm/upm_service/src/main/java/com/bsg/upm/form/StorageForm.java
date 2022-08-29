package com.bsg.upm.form;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月6日
 */
public class StorageForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 存储设备类型
     */
    private String performance;
    /**
     * 存储设备路径
     */
    private List<String> path;

    /**
     * @return the 存储设备类型
     */
    public String getPerformance() {
        return performance;
    }

    /**
     * @param 存储设备类型
     *            the performance to set
     */
    public void setPerformance(String performance) {
        this.performance = performance;
    }

    /**
     * @return the 存储设备路径
     */
    public List<String> getPath() {
        return path;
    }

    /**
     * @param 存储设备路径
     *            the path to set
     */
    public void setPath(List<String> path) {
        this.path = path;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "StorageForm [performance=" + performance + ", path=" + path + "]";
    }

}
