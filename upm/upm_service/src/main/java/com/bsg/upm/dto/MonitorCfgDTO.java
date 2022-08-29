package com.bsg.upm.dto;

import java.util.List;

public class MonitorCfgDTO {

    /**
     * 对象类型
     */
    private String objType;

    /**
     * 监控项类型
     */
    private String metricType;

    /**
     * 采集间隔时间
     */
    private Integer intervalTime;

    /**
     * 是否可用
     */
    private Boolean enable;

    /**
     * 描述
     */
    private String description;

    /**
     * 监控项
     */
    private List<Metric> metrics;

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
     * 获取监控项类型
     * 
     * @return metricType 监控项类型
     */
    public String getMetricType() {
        return metricType;
    }

    /**
     * 设置监控项类型
     * 
     * @param metricType
     *            监控项类型
     */
    public void setMetricType(String metricType) {
        this.metricType = metricType;
    }

    /**
     * 获取采集间隔时间
     * 
     * @return intervalTime 采集间隔时间
     */
    public Integer getIntervalTime() {
        return intervalTime;
    }

    /**
     * 设置采集间隔时间
     * 
     * @param intervalTime
     *            采集间隔时间
     */
    public void setIntervalTime(Integer intervalTime) {
        this.intervalTime = intervalTime;
    }

    /**
     * 获取是否可用
     * 
     * @return enable 是否可用
     */
    public Boolean getEnable() {
        return enable;
    }

    /**
     * 设置是否可用
     * 
     * @param enable
     *            是否可用
     */
    public void setEnable(Boolean enable) {
        this.enable = enable;
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
     * 获取监控项
     * 
     * @return metrics 监控项
     */
    public List<Metric> getMetrics() {
        return metrics;
    }

    /**
     * 设置监控项
     * 
     * @param metrics
     *            监控项
     */
    public void setMetrics(List<Metric> metrics) {
        this.metrics = metrics;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MonitorCfgDTO [objType=" + objType + ", metricType=" + metricType + ", intervalTime=" + intervalTime
                + ", enable=" + enable + ", description=" + description + ", metrics=" + metrics + "]";
    }

    public static class Metric {

        /**
         * 监控项
         */
        private String name;

        /**
         * 描述
         */
        private String description;

        /**
         * 是否是历史值
         */
        private Boolean history;

        /**
         * 事件
         */
        private Event event;

        /**
         * 获取监控项
         * 
         * @return name 监控项
         */
        public String getName() {
            return name;
        }

        /**
         * 设置监控项
         * 
         * @param name
         *            监控项
         */
        public void setName(String name) {
            this.name = name;
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
         * 获取是否是历史值
         * 
         * @return history 是否是历史值
         */
        public Boolean getHistory() {
            return history;
        }

        /**
         * 设置是否是历史值
         * 
         * @param history
         *            是否是历史值
         */
        public void setHistory(Boolean history) {
            this.history = history;
        }

        /**
         * 获取事件
         * 
         * @return event 事件
         */
        public Event getEvent() {
            return event;
        }

        /**
         * 设置事件
         * 
         * @param event
         *            事件
         */
        public void setEvent(Event event) {
            this.event = event;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "Metric [name=" + name + ", description=" + description + ", history=" + history + ", event=" + event
                    + "]";
        }

        public static class Event {

            /**
             * 事件等级
             */
            private List<Level> levels;

            /**
             * 获取事件等级
             * 
             * @return levels 事件等级
             */
            public List<Level> getLevels() {
                return levels;
            }

            /**
             * 设置事件等级
             * 
             * @param levels
             *            事件等级
             */
            public void setLevels(List<Level> levels) {
                this.levels = levels;
            }

            /*
             * (non-Javadoc)
             * 
             * @see java.lang.Object#toString()
             */
            @Override
            public String toString() {
                return "Event [levels=" + levels + "]";
            }

            public static class Level {

                /**
                 * 等级名称
                 */
                private String name;

                /**
                 * 开始值
                 */
                private String start;

                /**
                 * 结束值
                 */
                private String end;

                /**
                 * 描述
                 */
                private String description;

                /**
                 * 是否可用
                 */
                private Boolean enable;

                /**
                 * 是否告警
                 */
                private Boolean alarm;

                /**
                 * 获取等级名称
                 * 
                 * @return name 等级名称
                 */
                public String getName() {
                    return name;
                }

                /**
                 * 设置等级名称
                 * 
                 * @param name
                 *            等级名称
                 */
                public void setName(String name) {
                    this.name = name;
                }

                /**
                 * 获取开始值
                 * 
                 * @return start 开始值
                 */
                public String getStart() {
                    return start;
                }

                /**
                 * 设置开始值
                 * 
                 * @param start
                 *            开始值
                 */
                public void setStart(String start) {
                    this.start = start;
                }

                /**
                 * 获取结束值
                 * 
                 * @return end 结束值
                 */
                public String getEnd() {
                    return end;
                }

                /**
                 * 设置结束值
                 * 
                 * @param end
                 *            结束值
                 */
                public void setEnd(String end) {
                    this.end = end;
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
                 * 获取是否可用
                 * 
                 * @return enable 是否可用
                 */
                public Boolean getEnable() {
                    return enable;
                }

                /**
                 * 设置是否可用
                 * 
                 * @param enable
                 *            是否可用
                 */
                public void setEnable(Boolean enable) {
                    this.enable = enable;
                }

                /**
                 * 获取是否告警
                 * 
                 * @return alarm 是否告警
                 */
                public Boolean getAlarm() {
                    return alarm;
                }

                /**
                 * 设置是否告警
                 * 
                 * @param alarm
                 *            是否告警
                 */
                public void setAlarm(Boolean alarm) {
                    this.alarm = alarm;
                }

                /*
                 * (non-Javadoc)
                 * 
                 * @see java.lang.Object#toString()
                 */
                @Override
                public String toString() {
                    return "Level [name=" + name + ", start=" + start + ", end=" + end + ", description=" + description
                            + ", enable=" + enable + ", alarm=" + alarm + "]";
                }

            }

        }
    }
}
