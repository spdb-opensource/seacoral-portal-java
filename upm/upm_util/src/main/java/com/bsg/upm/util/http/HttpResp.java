package com.bsg.upm.util.http;

import java.io.Serializable;

public class HttpResp implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int statusCode;
    private String responseContent;

    public HttpResp(int statusCode, String responseContent) {
        this.statusCode = statusCode;
        this.responseContent = responseContent;
    }

    /**
     * 获取statusCode
     * 
     * @return statusCode statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * 设置statusCode
     * 
     * @param statusCode
     *            statusCode
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * 获取responseContent
     * 
     * @return responseContent responseContent
     */
    public String getResponseContent() {
        return responseContent;
    }

    /**
     * 设置responseContent
     * 
     * @param responseContent
     *            responseContent
     */
    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "HttpResp [statusCode=" + statusCode + ", responseContent=" + responseContent + "]";
    }

}
