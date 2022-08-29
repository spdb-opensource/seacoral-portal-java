package com.bsg.upm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bsg.upm.domain.DbUserDO;
import com.bsg.upm.domain.SchemaDO;

/**
 * 数据库用户接口
 * @author yeht
 * @date 2020年8月1日
 */
public interface DbUserDAO {


    List<DbUserDO> listBase();


    int save(DbUserDO dbUserDO);

    int update(DbUserDO dbUserDO);


	void saveSchema(SchemaDO schemaDO);


	List<DbUserDO> getDbUsers( @Param("order_id") String order_id,@Param("type")  String type);
	
	int remove(String order_id);
	

}
