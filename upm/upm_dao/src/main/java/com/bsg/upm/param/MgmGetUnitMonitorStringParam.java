package com.bsg.upm.param;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class MgmGetUnitMonitorStringParam implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @JSONField(name = "history")
    private String history;
    
    @JSONField(name = "metrics")
    private List<Metricss> metrics;

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	
	


	public List<Metricss> getMetrics() {
		return metrics;
	}
	

	public void setMetrics(List<Metricss> metrics) {
		this.metrics = metrics;
	}
	

	@Override
	public String toString() {
		return "MgmGetUnitMonitorParam [history=" + history + ", metrics=" + metrics + "]";
	}

	public class Metricss{
    	private String name;
    	private Long timestamp;
    	private String value;
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
		
		public String getValue() {
			return value;
		}
		
		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Metricss [name=" + name + ", timestamp=" + timestamp + ", value=" + value + "]";
		}
		
    	
    }
    
	
}
