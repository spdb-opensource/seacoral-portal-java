package com.bsg.upm.dao;

import java.util.Date;

import com.bsg.upm.domain.OperateLogDO;
import com.bsg.upm.param.OperateLogDAOParam;

/**
 * 操作日志接口
 * 
 * @author HCK
 * @date 2018年5月9日
 */
public interface OperateLogDAO extends CrudDAO<OperateLogDO, OperateLogDAOParam> {

    int removeBeforeDatetime(Date datetime);
    
    int removeBySiteId(long siteId);
}
