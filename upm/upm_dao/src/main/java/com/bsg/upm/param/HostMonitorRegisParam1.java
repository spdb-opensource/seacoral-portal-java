package com.bsg.upm.param;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class HostMonitorRegisParam1 implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @JSONField(name = "name")
    private String name;
    
    @JSONField(name = "ip_addr")
    private String ipAddr;
    
    @JSONField(name = "tag")
    private String tag;

	public String getName() {
		return name;
	}

	public String getIpAddr() {
		return ipAddr;
	}
	

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	

	public String getTag() {
		return tag;
	}
	

	public void setTag(String tag) {
		this.tag = tag;
	}
	

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "HostMonitorRegisParam1 [name=" + name + ", ipAddr=" + ipAddr + ", tag=" + tag + "]";
	}
	
	
}
