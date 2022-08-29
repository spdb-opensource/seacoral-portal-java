package com.bsg.upm.query;

import java.io.Serializable;

public class SiteParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String username;

    /**
     * 获取username
     * 
     * @return username username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置username
     * 
     * @param username
     *            username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SiteParam [username=" + username + "]";
    }

}
