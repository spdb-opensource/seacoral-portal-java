package com.bsg.upm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bsg.upm.domain.PrivilegeDO;

public interface PrivilegeDAO {

    List<PrivilegeDO> list(@Param("enabled") Boolean enabled, @Param("global") Boolean global);

}
