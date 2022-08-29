package com.bsg.upm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bsg.upm.domain.SchemaDO;
import com.bsg.upm.param.UserDAOParam;

/**
 * schema接口
 * @author yeht
 * @date 2020年7月8日
 */
public interface SchemaDAO {

    //List<SchemaDO> list(SchemaDAOParam schemaDAOParam);

    //List<SchemaDO> listBase();

    //SchemaDO getByUsername(String username);

    /**
     * 获取schema信息
     * 
     * @param dbname
     *            库名
     */
    SchemaDO get(@Param("dbname") String dbname);


    int save(SchemaDO SchemaDO);

    int update(SchemaDO SchemaDO);


    int removeschemaByDbname(String dbname);

}
