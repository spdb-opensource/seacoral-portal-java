package com.bsg.upm.util;

public class JavaMetric {

    /**
     * 监控项
     */
    private String value;

    /**
     * 起始时间(秒)
     */
    private Long start;

    /**
     * 结束时间(秒)
     */
    private Long end;

    /**
     * 压缩类型
     */
    private String compressType;

    public JavaMetric(String value) {
        this.value = value;
    }

    public JavaMetric(String value, Long start, Long end, String compressType) {
        this.value = value;
        this.start = start;
        this.end = end;
        this.compressType = compressType;
    }

    /**
     * 获取监控项
     * 
     * @return value 监控项
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置监控项
     * 
     * @param value
     *            监控项
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取起始时间(秒)
     * 
     * @return start 起始时间(秒)
     */
    public Long getStart() {
        return start;
    }

    /**
     * 设置起始时间(秒)
     * 
     * @param start
     *            起始时间(秒)
     */
    public void setStart(Long start) {
        this.start = start;
    }

    /**
     * 获取结束时间(秒)
     * 
     * @return end 结束时间(秒)
     */
    public Long getEnd() {
        return end;
    }

    /**
     * 设置结束时间(秒)
     * 
     * @param end
     *            结束时间(秒)
     */
    public void setEnd(Long end) {
        this.end = end;
    }

    /**
     * 获取压缩类型
     * 
     * @return compressType 压缩类型
     */
    public String getCompressType() {
        return compressType;
    }

    /**
     * 设置压缩类型
     * 
     * @param compressType
     *            压缩类型
     */
    public void setCompressType(String compressType) {
        this.compressType = compressType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "JavaMetric [value=" + value + ", start=" + start + ", end=" + end + ", compressType=" + compressType
                + "]";
    }

}
