package com.bsg.upm.param;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class HostMonitorQueryParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @JSONField(name = "start_time")
    private Long startTime;
    
    @JSONField(name = "end_time")
    private Long endTime;

	public Long getStartTime() {
		return startTime;
	}
	

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	

	public Long getEndTime() {
		return endTime;
	}
	

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}


	@Override
	public String toString() {
		return "HostMonitorQueryParam [startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	

   
}
