package com.bsg.upm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bsg.upm.domain.RemoteStorageVendorDO;

public interface RemoteStorageVendorDAO {
    
    List<RemoteStorageVendorDO> list();
    
    RemoteStorageVendorDO get(String id);
    
    RemoteStorageVendorDO getByCodeAndVersion(@Param("code") String code, @Param("version") String version);
    
}
