package com.bsg.upm.form;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author yeht
 * @date 2019年7月4日
 */
public class MgmBaseForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String unit_id;

    //是否维护状态
    private Boolean maintenance;

	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	public Boolean getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(Boolean maintenance) {
		this.maintenance = maintenance;
	}

	@Override
	public String toString() {
		return "MgmBaseBody [unit_id=" + unit_id + ", maintenance=" + maintenance + "]";
	}


}
