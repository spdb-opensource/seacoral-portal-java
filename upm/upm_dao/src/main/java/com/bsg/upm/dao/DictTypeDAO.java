package com.bsg.upm.dao;

import java.util.List;

import com.bsg.upm.domain.DictTypeDO;

/**
 * 字典类型接口
 * 
 * @author HCK
 * @date 2018年5月8日
 */
public interface DictTypeDAO extends CrudDAO<DictTypeDO, Object> {

    DictTypeDO getByCode(String code);
    
    List<DictTypeDO> listSume();
    
}
