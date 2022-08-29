package com.bsg.upm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bsg.upm.domain.AppDO;
import com.bsg.upm.param.AppDAOParam;

/**
 * app interface
 * 
 * @author ZhuXH
 * @date 2019-05-17
 */
public interface AppDAO extends CrudDAO<AppDO, AppDAOParam> {
	
	List<AppDO> listBtnApp(@Param("roleId") String roleId,@Param("path") String path);
	
	List<AppDO> listSubBtnApp(@Param("roleId") String roleId,@Param("path") String path);
	
	List<AppDO> listsSubBtnApp(@Param("roleId") String roleId,@Param("path") String path);
	
	List<AppDO> listMenu(@Param("roleId") String roleId);
	
	List<AppDO> listPage(@Param("roleId") String roleId);
}
