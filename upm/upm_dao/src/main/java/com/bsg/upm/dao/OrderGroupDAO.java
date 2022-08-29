package com.bsg.upm.dao;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.bsg.upm.domain.OrderGroupDO;
import com.bsg.upm.param.OrderGroupDAOParam;

public interface OrderGroupDAO extends CrudDAO<OrderGroupDO, OrderGroupDAOParam> {

    OrderGroupDO getBase(String id);
    
    int updateStatusAndMsg(OrderGroupDO orderGroupDO);
    
    int updateStatus(OrderGroupDO orderGroupDO);
    
    int updateServGroupId(OrderGroupDO orderGroupDO);
    
    OrderGroupDO getLastOneByServerGroupId(String serverGroupId);
    
    List<OrderGroupDO> checkName(String name);
    
    OrderGroupDO checkNameAndActionType(OrderGroupDO orderGroupDO);
    
    int countByBusinessSubsystemId(String businessSubsystemId);
    
    int updateBusinessSubstemIdAndOwner(OrderGroupDO orderGroupDO);
}
