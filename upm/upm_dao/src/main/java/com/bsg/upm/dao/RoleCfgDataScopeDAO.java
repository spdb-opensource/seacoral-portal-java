package com.bsg.upm.dao;

import java.util.List;

import com.bsg.upm.domain.RoleCfgDataScopeDO;

/**
 * 
 * @author HCK
 * @date 2018年5月8日
 */
public interface RoleCfgDataScopeDAO {

    List<RoleCfgDataScopeDO> list();

    RoleCfgDataScopeDO getByRoleId(String roleId);

    int save(RoleCfgDataScopeDO cfgDataScopeDO);

    int update(RoleCfgDataScopeDO cfgDataScopeDO);

    int removeByRoleId(String roleId);
}
