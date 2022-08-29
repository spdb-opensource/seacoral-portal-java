package com.bsg.upm.param;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class HostDeleteParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @JSONField(name = "username")
    private String username;
    @JSONField(name = "pwd")
    private String pwd;
    
    @JSONField(name = "ssh_port")
    private String port;

	public String getUsername() {
		return username;
	}
	

	public void setUsername(String username) {
		this.username = username;
	}
	

	public String getPwd() {
		return pwd;
	}
	

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getPort() {
		return port;
	}
	


	public void setPort(String port) {
		this.port = port;
	}


	@Override
	public String toString() {
		return "HostDeleteParam [username=" + username + ", pwd=" + pwd + ", port=" + port + "]";
	}
	

   
}
