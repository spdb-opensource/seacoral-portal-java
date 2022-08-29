package com.bsg.upm.param;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsg.upm.param.MgmGetUnitMonitorParam.Metrics;

public class GetUnitMonitorParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @JSONField(name = "metric")
    private String metric;
    
    @JSONField(name = "description")
    private String description;
    
    @JSONField(name = "history")
    private String history;
    
    @JSONField(name = "datas")
    private List<Metrics> datas;
    
    



	@Override
	public String toString() {
		return "HostMonitorParam [metric=" + metric + ", description=" + description + ", histrry=" + history
				+ ", datas=" + datas + "]";
	}




	public String getMetric() {
		return metric;
	}
	











	public void setMetric(String metric) {
		this.metric = metric;
	}
	











	public String getDescription() {
		return description;
	}
	











	public void setDescription(String description) {
		this.description = description;
	}
	











	public String getHistory() {
		return history;
	}
	











	public void setHistory(String history) {
		this.history = history;
	}
	











	public List<Metrics> getDatas() {
		return datas;
	}
	





	public void setDatas(List<Metrics> datas) {
		this.datas = datas;
	}
	





    
	
}
