package com.bsg.upm.mgm.body;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月6日
 */
public class StorageHost implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @JSONField(name = "performance")
    private String performance;

    @JSONField(name = "paths")
    private List<String> paths;

    /**
     * @return the performance
     */
    public String getPerformance() {
        return performance;
    }

    /**
     * @param performance
     *            the performance to set
     */
    public void setPerformance(String performance) {
        this.performance = performance;
    }

    /**
     * @return the paths
     */
    public List<String> getPaths() {
        return paths;
    }

    /**
     * @param paths
     *            the paths to set
     */
    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "StorageHost [performance=" + performance + ", paths=" + paths + "]";
    }

}
