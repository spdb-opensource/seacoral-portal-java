package com.bsg.upm.dao;

import com.bsg.upm.domain.ServGroupDO;
import com.bsg.upm.domain.ServerGroupDO;

public interface ServGroupDAO {
	
	int save(ServGroupDO serverGroupDO);
	
	int remove(String relateId);

}
