package com.bsg.upm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bsg.upm.domain.OrderDO;

public interface OrderDAO {

    int save(OrderDO orderDO);

    int update(OrderDO orderDO);

    int updateServId(OrderDO orderDO);

    int removeByOrderGroupId(String orderGroupId);

    int countByOrderGroupIdAndType(@Param("orderGroupId") String orderGroupId,@Param("type")  String type);
    
    List<OrderDO> selectByOrderGroupId(String orderGroupId);
}
