package com.bsg.upm.dao;

import java.util.List;

import com.bsg.upm.domain.RoleCfgOthersDO;

/**
 * 
 * @author HCK
 * @date 2018年5月8日
 */
public interface RoleCfgOthersDAO {

    List<RoleCfgOthersDO> list();

    RoleCfgOthersDO getByRoleId(String roleId);

    int save(RoleCfgOthersDO cfgOthersDO);

    int update(RoleCfgOthersDO cfgOthersDO);

    int removeByRoleId(String roleId);
}
