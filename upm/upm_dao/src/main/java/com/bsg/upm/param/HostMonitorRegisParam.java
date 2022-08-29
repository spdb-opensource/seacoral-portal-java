package com.bsg.upm.param;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class HostMonitorRegisParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @JSONField(name = "name")
    private String name;
    
    @JSONField(name = "ip_addr")
    private String ipAddr;
    
    @JSONField(name = "ssh_port")
    private Integer sshPort;
    
    @JSONField(name = "os_user")
    private String osUser;
    
    @JSONField(name = "os_pwd")
    private String osPwd;
    
    @JSONField(name = "check_type")
    private String checkType;
    

	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}
	

	public String getIpAddr() {
		return ipAddr;
	}
	

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	

	

	public Integer getSshPort() {
		return sshPort;
	}
	


	public void setSshPort(Integer sshPort) {
		this.sshPort = sshPort;
	}
	


	public String getOsUser() {
		return osUser;
	}
	

	public void setOsUser(String osUser) {
		this.osUser = osUser;
	}
	

	public String getOsPwd() {
		return osPwd;
	}
	

	public void setOsPwd(String osPwd) {
		this.osPwd = osPwd;
	}
	

	public String getCheckType() {
		return checkType;
	}
	

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}


	@Override
	public String toString() {
		return "HostMonitorRegisParam [name=" + name + ", ipAddr=" + ipAddr + ", sshPort=" + sshPort + ", osUser="
				+ osUser + ", osPwd=" + osPwd + ", checkType=" + checkType + "]";
	}
	

   
}
