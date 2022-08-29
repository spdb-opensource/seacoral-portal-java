package com.bsg.upm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bsg.upm.domain.UserDO;
import com.bsg.upm.param.UserDAOParam;

/**
 * 用户接口
 * @author ZhuXH
 * @date 2019年7月1日
 */
public interface UserDAO {

    List<UserDO> list(UserDAOParam userDAOParam);

    List<UserDO> listBase();

    UserDO getByUsername(String username);

    /**
     * 获取用户信息
     * 
     * @param username
     *            用户名
     * @param password
     *            密码
     * @return 用户信息
     */
    UserDO get(@Param("username") String username, @Param("password") String password);

    int countByTelephone(String telephone);

    int countByEmail(String email);

    int countByRoleId(String roleId);

    int save(UserDO userDO);

    int update(UserDO userDO);

    int updateEnabled(UserDO userDO);

    int updatePwd(UserDO userDO);

    int removeCascadeByUsername(String username);

}
