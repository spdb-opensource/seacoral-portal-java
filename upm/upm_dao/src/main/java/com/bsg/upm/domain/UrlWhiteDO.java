package com.bsg.upm.domain;

import java.io.Serializable;

public class UrlWhiteDO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String url;
    private String method;
    private String whiteIps;

    /**
     * 获取url
     * 
     * @return url url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置url
     * 
     * @param url
     *            url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取method
     * 
     * @return method method
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置method
     * 
     * @param method
     *            method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 获取whiteIps
     * 
     * @return whiteIps whiteIps
     */
    public String getWhiteIps() {
        return whiteIps;
    }

    /**
     * 设置whiteIps
     * 
     * @param whiteIps
     *            whiteIps
     */
    public void setWhiteIps(String whiteIps) {
        this.whiteIps = whiteIps;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UrlWhiteDO [url=" + url + ", method=" + method + ", whiteIps=" + whiteIps + "]";
    }

}
