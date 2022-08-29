package com.bsg.upm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bsg.upm.domain.ScaleDO;

public interface ScaleDAO {

    List<ScaleDO> list(@Param("type") String type, @Param("enabled") Boolean enabled);

    ScaleDO get(String id);

    ScaleDO getByTypeAndCpuAndMem(@Param("type") String type, @Param("cpuCnt") int cpuCnt,
            @Param("memSize") long memSize);

    int countByTypeAndName(@Param("type") String type, @Param("name") String name);

    int save(ScaleDO scaleDO);

    int update(ScaleDO scaleDO);
    
    int updateEnabled(ScaleDO scaleDO);

    int remove(String id);
}
