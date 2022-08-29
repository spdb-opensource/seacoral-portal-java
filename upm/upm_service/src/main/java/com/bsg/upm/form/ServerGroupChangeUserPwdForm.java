package com.bsg.upm.form;

import java.io.Serializable;
import java.util.Arrays;

import com.alibaba.fastjson.annotation.JSONField;

public class ServerGroupChangeUserPwdForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String pwd;
	private String auth_type;
	private String ip;
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getAuth_type() {
		return auth_type;
	}
	
	public void setAuth_type(String auth_type) {
		this.auth_type = auth_type;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "ServerGroupChangeUserPwdForm [name=" + name + ", pwd=" + pwd + ", auth_type=" + auth_type + ", ip=" + ip
				+ "]";
	}
	

	
}
