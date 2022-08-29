package com.bsg.upm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bsg.upm.domain.BusinessSystemDO;

public interface BusinessSystemDAO {

    List<BusinessSystemDO> list(@Param("owner") String owner);

    BusinessSystemDO get(String id);

    BusinessSystemDO getByNameAndOwner(@Param("name") String name, @Param("owner") String owner);

    int save(BusinessSystemDO businessSystemDO);

    int update(BusinessSystemDO businessSystemDO);

    int remove(String id);
    
    BusinessSystemDO getBusinessName(String businessSubsystemId);

}
