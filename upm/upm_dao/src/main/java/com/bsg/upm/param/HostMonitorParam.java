package com.bsg.upm.param;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class HostMonitorParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @JSONField(name = "history")
    private String history;
    
    @JSONField(name = "metrics")
    private List<Metrics> metrics;
    
    
    @Override
	public String toString() {
		return "HostMonitorParam [history=" + history + ", metrics=" + metrics + "]";
	}





	public String getHistory() {
		return history;
	}
	




	public void setHistory(String history) {
		this.history = history;
	}
	




	public List<Metrics> getMetrics() {
		return metrics;
	}
	




	public void setMetrics(List<Metrics> metrics) {
		this.metrics = metrics;
	}
	




	public class Metrics{
    	private String name;
    	private Long timestamp;
    	private double value;
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public Long getTimestamp() {
			return timestamp;
		}
		
		public void setTimestamp(Long timestamp) {
			this.timestamp = timestamp;
		}
		
		public double getValue() {
			return value;
		}
		
		public void setValue(double value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Metrics [name=" + name + ", timestamp=" + timestamp + ", value=" + value + "]";
		}
		
    	
    }
    
	
}
