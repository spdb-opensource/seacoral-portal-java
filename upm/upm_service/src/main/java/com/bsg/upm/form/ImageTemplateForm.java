package com.bsg.upm.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImageTemplateForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String dataDir;

    private String logDir;

    private String configFilePath;

    private List<KeysetForm> keysets = new ArrayList<>();

    /**
     * 获取dataDir
     * 
     * @return dataDir dataDir
     */
    public String getDataDir() {
        return dataDir;
    }

    /**
     * 设置dataDir
     * 
     * @param dataDir
     *            dataDir
     */
    public void setDataDir(String dataDir) {
        this.dataDir = dataDir;
    }

    /**
     * 获取logDir
     * 
     * @return logDir logDir
     */
    public String getLogDir() {
        return logDir;
    }

    /**
     * 设置logDir
     * 
     * @param logDir
     *            logDir
     */
    public void setLogDir(String logDir) {
        this.logDir = logDir;
    }

    /**
     * 获取configFilePath
     * 
     * @return configFilePath configFilePath
     */
    public String getConfigFilePath() {
        return configFilePath;
    }

    /**
     * 设置configFilePath
     * 
     * @param configFilePath
     *            configFilePath
     */
    public void setConfigFilePath(String configFilePath) {
        this.configFilePath = configFilePath;
    }

    /**
     * 获取keysets
     * 
     * @return keysets keysets
     */
    public List<KeysetForm> getKeysets() {
        return keysets;
    }

    /**
     * 设置keysets
     * 
     * @param keysets
     *            keysets
     */
    public void setKeysets(List<KeysetForm> keysets) {
        this.keysets = keysets;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ImageTemplateForm [dataDir=" + dataDir + ", logDir=" + logDir + ", configFilePath=" + configFilePath
                + ", keysets=" + keysets + "]";
    }

}
