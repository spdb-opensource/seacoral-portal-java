package com.bsg.upm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bsg.upm.domain.GroupDO;

public interface GroupDAO {

    List<GroupDO> list();

    GroupDO get(String id);

    GroupDO getByNameAndCreator(@Param("name") String name, @Param("creator") String creator);

    int save(GroupDO groupDO);

    int update(GroupDO groupDO);

    int remove(String id);
}
