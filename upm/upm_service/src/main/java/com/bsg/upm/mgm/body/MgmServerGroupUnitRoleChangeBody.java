package com.bsg.upm.mgm.body;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.bsg.upm.query.UnitRoleChangeParam.UnitRole;

/**
 * 
 * @author swn
 * @date 2019年8月12日
 */
public class MgmServerGroupUnitRoleChangeBody implements Serializable {

	private static final long serialVersionUID = 1L;

	@JSONField(name = "units")
	private List<UnitRole> units;

	public List<UnitRole> getUnits() {
		return units;
	}
	

	public void setUnits(List<UnitRole> units) {
		this.units = units;
	}


	@Override
	public String toString() {
		return "MgmServerGroupUnitRoleChangeBody [units=" + units + "]";
	}
	
	
}
