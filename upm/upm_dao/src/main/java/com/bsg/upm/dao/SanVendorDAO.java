package com.bsg.upm.dao;

import java.util.List;

import com.bsg.upm.domain.SanVendorDO;

public interface SanVendorDAO {
    
    List<SanVendorDO> list();
    
    SanVendorDO get(long id);

}
