package com.bsg.upm.dao;

import org.apache.ibatis.annotations.Param;

import com.bsg.upm.domain.SanDO;
import com.bsg.upm.param.SanDAOParam;

public interface SanDAO extends CrudDAO<SanDO, SanDAOParam> {

    SanDO getByNameAndSiteId(@Param("name") String name, @Param("siteId") long siteId);
    
    int countBySiteId(long siteId);

}
