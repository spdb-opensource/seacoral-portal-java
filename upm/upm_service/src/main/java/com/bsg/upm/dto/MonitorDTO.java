package com.bsg.upm.dto;

import java.io.Serializable;
import java.util.List;

public class MonitorDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 监控项
     */
    private String metric;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否存在历史值
     */
    private boolean history;

    /**
     * 监控数值
     */
    private List<DataDTO> datas;

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

    /**
     * 获取是否存在历史值
     * 
     * @return history 是否存在历史值
     */
    public boolean isHistory() {
        return history;
    }

    /**
     * 设置是否存在历史值
     * 
     * @param history
     *            是否存在历史值
     */
    public void setHistory(boolean history) {
        this.history = history;
    }

    /**
     * 获取监控数值
     * 
     * @return datas 监控数值
     */
    public List<DataDTO> getDatas() {
        return datas;
    }

    /**
     * 设置监控数值
     * 
     * @param datas
     *            监控数值
     */
    public void setDatas(List<DataDTO> datas) {
        this.datas = datas;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MonitorDTO [metric=" + metric + ", description=" + description + ", history=" + history + ", datas="
                + datas + "]";
    }

    public static class DataDTO {
        /**
         * 时间戳(秒)
         */
        private Long timestamp;

        /**
         * 值
         */
        private Object value;

        /**
         * 获取时间戳(秒)
         * 
         * @return timestamp 时间戳(秒)
         */
        public Long getTimestamp() {
            return timestamp;
        }

        /**
         * 设置时间戳(秒)
         * 
         * @param timestamp
         *            时间戳(秒)
         */
        public void setTimestamp(Long timestamp) {
            this.timestamp = timestamp;
        }

        /**
         * 获取值
         * 
         * @return value 值
         */
        public Object getValue() {
            return value;
        }

        /**
         * 设置值
         * 
         * @param value
         *            值
         */
        public void setValue(Object value) {
            this.value = value;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "DataDTO [timestamp=" + timestamp + ", value=" + value + "]";
        }

    }

}
