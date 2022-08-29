package com.bsg.upm.dao;

import java.util.List;

import com.bsg.upm.domain.RoleCfgAppDO;

/**
 * 
 * @author ZhuXH
 * @date 2019年5月17日
 */
public interface RoleCfgAppDAO {

	List<RoleCfgAppDO> listByRoleId(String roleId);
	
	int save(RoleCfgAppDO cfgAppDO);
	
	int removeByRoleId(String roleId);
}
