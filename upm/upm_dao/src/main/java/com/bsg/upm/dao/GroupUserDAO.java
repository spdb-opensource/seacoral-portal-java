package com.bsg.upm.dao;

import com.bsg.upm.domain.GroupUserDO;
import com.bsg.upm.domain.UserDO;

public interface GroupUserDAO {

    int countByGroupId(String groupId);

    int save(UserDO userDO);
    int saveUser(GroupUserDO userDO);

    int removeByGroupId(String groupId);

    int removeByUsername(String username);
}
