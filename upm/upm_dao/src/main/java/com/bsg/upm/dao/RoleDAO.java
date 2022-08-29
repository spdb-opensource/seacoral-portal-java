package com.bsg.upm.dao;

import com.bsg.upm.domain.RoleDO;

/**
 * 用户角色接口
 * 
 * @author HCK
 * @date 2018年5月8日
 */
public interface RoleDAO extends CrudDAO<RoleDO, Object> {

    /**
     * 获取指定角色名称的角色个数
     * 
     * @param name
     *            角色名称
     * @return 符合条件的角色个数
     */
    int countByName(String name);
    
}
