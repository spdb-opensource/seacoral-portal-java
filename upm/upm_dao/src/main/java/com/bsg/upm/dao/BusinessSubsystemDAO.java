package com.bsg.upm.dao;

import org.apache.ibatis.annotations.Param;

import com.bsg.upm.domain.BusinessSubsystemDO;
import com.bsg.upm.param.BusinessSubsystemDAOParam;

public interface BusinessSubsystemDAO extends CrudDAO<BusinessSubsystemDO, BusinessSubsystemDAOParam> {

    BusinessSubsystemDO getByNameAndBusinessSystemId(@Param("name") String name,
            @Param("businessSystemId") String businessSystemId);

    int countByBusinessSystemId(String businessSystemId);
}
