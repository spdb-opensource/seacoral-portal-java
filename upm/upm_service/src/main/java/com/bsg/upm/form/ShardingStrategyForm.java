package com.bsg.upm.form;

import java.io.Serializable;

public class ShardingStrategyForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 内容
     */
    private String content;

    /**
     * 获取内容
     * 
     * @return content 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     * 
     * @param content
     *            内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ShardingStrategyForm [content=" + content + "]";
    }

}
