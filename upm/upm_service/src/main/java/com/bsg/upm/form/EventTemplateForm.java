package com.bsg.upm.form;

import java.io.Serializable;

public class EventTemplateForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 开始阀值
     */
    private String start;

    /**
     * 结束阀值
     */
    private String end;

    /**
     * 获取开始阀值
     * 
     * @return start 开始阀值
     */
    public String getStart() {
        return start;
    }

    /**
     * 设置开始阀值
     * 
     * @param start
     *            开始阀值
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * 获取结束阀值
     * 
     * @return end 结束阀值
     */
    public String getEnd() {
        return end;
    }

    /**
     * 设置结束阀值
     * 
     * @param end
     *            结束阀值
     */
    public void setEnd(String end) {
        this.end = end;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "EventTemplateForm [start=" + start + ", end=" + end + "]";
    }

}
