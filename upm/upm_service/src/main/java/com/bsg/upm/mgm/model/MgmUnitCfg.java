package com.bsg.upm.mgm.model;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class MgmUnitCfg implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JSONField(name = "config_file")
    private String configFile;

    @JSONField(name = "content")
    private String content;

    @JSONField(name = "data_mount")
    private String dataMount;

    @JSONField(name = "log_mount")
    private String logMount;

    @JSONField(name = "image")
    private String image;

    @JSONField(name = "service")
    private String service;

    @JSONField(name = "timestamp")
    private Long timestamp;

    @JSONField(name = "keysets")
    private List<Keyset> keysets;

    /**
     * 获取configFile
     * 
     * @return configFile configFile
     */
    public String getConfigFile() {
        return configFile;
    }

    /**
     * 设置configFile
     * 
     * @param configFile
     *            configFile
     */
    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    /**
     * 获取content
     * 
     * @return content content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置content
     * 
     * @param content
     *            content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取dataMount
     * 
     * @return dataMount dataMount
     */
    public String getDataMount() {
        return dataMount;
    }

    /**
     * 设置dataMount
     * 
     * @param dataMount
     *            dataMount
     */
    public void setDataMount(String dataMount) {
        this.dataMount = dataMount;
    }

    /**
     * 获取logMount
     * 
     * @return logMount logMount
     */
    public String getLogMount() {
        return logMount;
    }

    /**
     * 设置logMount
     * 
     * @param logMount
     *            logMount
     */
    public void setLogMount(String logMount) {
        this.logMount = logMount;
    }

    /**
     * 获取image
     * 
     * @return image image
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置image
     * 
     * @param image
     *            image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取service
     * 
     * @return service service
     */
    public String getService() {
        return service;
    }

    /**
     * 设置service
     * 
     * @param service
     *            service
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * 获取timestamp
     * 
     * @return timestamp timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * 设置timestamp
     * 
     * @param timestamp
     *            timestamp
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 获取keysets
     * 
     * @return keysets keysets
     */
    public List<Keyset> getKeysets() {
        return keysets;
    }

    /**
     * 设置keysets
     * 
     * @param keysets
     *            keysets
     */
    public void setKeysets(List<Keyset> keysets) {
        this.keysets = keysets;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MgmServCfg [configFile=" + configFile + ", content=" + content + ", dataMount=" + dataMount
                + ", logMount=" + logMount + ", image=" + image + ", service=" + service + ", timestamp=" + timestamp
                + ", keysets=" + keysets + "]";
    }

    public static class Keyset {
        @JSONField(name = "key")
        private String key;

        @JSONField(name = "value")
        private String value;

        @JSONField(name = "desc")
        private String desc;

        @JSONField(name = "can_set")
        private boolean canSet;

        @JSONField(name = "must_restart")
        private boolean mustRestart;

        @JSONField(name = "range")
        private String range;

        @JSONField(name = "default")
        private String _default;

        /**
         * 获取key
         * 
         * @return key key
         */
        public String getKey() {
            return key;
        }

        /**
         * 设置key
         * 
         * @param key
         *            key
         */
        public void setKey(String key) {
            this.key = key;
        }

        /**
         * 获取value
         * 
         * @return value value
         */
        public String getValue() {
            return value;
        }

        /**
         * 设置value
         * 
         * @param value
         *            value
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * 获取desc
         * 
         * @return desc desc
         */
        public String getDesc() {
            return desc;
        }

        /**
         * 设置desc
         * 
         * @param desc
         *            desc
         */
        public void setDesc(String desc) {
            this.desc = desc;
        }

        /**
         * 获取canSet
         * 
         * @return canSet canSet
         */
        public boolean isCanSet() {
            return canSet;
        }

        /**
         * 设置canSet
         * 
         * @param canSet
         *            canSet
         */
        public void setCanSet(boolean canSet) {
            this.canSet = canSet;
        }

        /**
         * 获取mustRestart
         * 
         * @return mustRestart mustRestart
         */
        public boolean isMustRestart() {
            return mustRestart;
        }

        /**
         * 设置mustRestart
         * 
         * @param mustRestart
         *            mustRestart
         */
        public void setMustRestart(boolean mustRestart) {
            this.mustRestart = mustRestart;
        }

        /**
         * 获取range
         * 
         * @return range range
         */
        public String getRange() {
            return range;
        }

        /**
         * 设置range
         * 
         * @param range
         *            range
         */
        public void setRange(String range) {
            this.range = range;
        }

        /**
         * 获取_default
         * 
         * @return _default _default
         */
        public String get_default() {
            return _default;
        }

        /**
         * 设置_default
         * 
         * @param _default
         *            _default
         */
        public void set_default(String _default) {
            this._default = _default;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return "Keyset [key=" + key + ", value=" + value + ", desc=" + desc + ", canSet=" + canSet
                    + ", mustRestart=" + mustRestart + ", range=" + range + ", _default=" + _default + "]";
        }

    }

}
