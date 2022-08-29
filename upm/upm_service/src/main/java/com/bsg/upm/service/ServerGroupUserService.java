package com.bsg.upm.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.ServerGroupCfgsCheck;
import com.bsg.upm.check.ServerGroupUserCheck;
import com.bsg.upm.check.resultenum.HostChkRsEnum;
import com.bsg.upm.check.resultenum.ServerGroupChkRsEnum;
import com.bsg.upm.check.resultenum.ServerGroupUserChkRsEnum;
import com.bsg.upm.check.resultenum.SiteChkRsEnum;
import com.bsg.upm.dto.ServerGroupUserDTO;
import com.bsg.upm.dto.ServerGroupUserDTO.Privilege;
import com.bsg.upm.exception.CallingInterfaceException;
import com.bsg.upm.exception.ConnectConsulException;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.exception.ServiceNotFoundException;
import com.bsg.upm.form.KeysetForm;
import com.bsg.upm.form.ServerGroupChangeUserPwdForm;
import com.bsg.upm.form.ServerGroupUpdateUserForm;
import com.bsg.upm.form.ServerGroupUserForm;
import com.bsg.upm.form.ServerGroupUserForm.PrivilegeForm;
import com.bsg.upm.mgm.api.MgmApi;
import com.bsg.upm.mgm.body.MgmServerGroupCfgsBody;
import com.bsg.upm.mgm.body.MgmServerGroupUpdateUserBody;
import com.bsg.upm.mgm.body.MgmServerGroupUserBody;
import com.bsg.upm.mgm.model.MgmImageConf;
import com.bsg.upm.mgm.model.MgmUser;
import com.bsg.upm.mgm.model.MgmServerGroup.Image;
import com.bsg.upm.mgm.model.MgmServerGroup.KeySet;
import com.bsg.upm.mgm.query.MgmUserQuery;
import com.bsg.upm.query.ImageConf;
import com.bsg.upm.query.ImageTemplate;
import com.bsg.upm.query.UnitCfgsConf;

/**
 * 服务组用户service
 * 
 * @author swn
 *
 */
@Service
public class ServerGroupUserService extends BaseService {

	@Autowired
	private MgmApi mgmApi;

	@Autowired
	private ServerGroupUserCheck serverGroupUserCheck;
	
	@Autowired
	private ServerGroupCfgsCheck serverGroupCfgsCheck;

	/**
	 * 用户列表查询
	 * 
	 * @param serverGroupId
	 * @return
	 * @throws ServiceException
	 */
	@Transactional
	public Result list(String serverGroupId) throws ServiceException {
		try {
			if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
			final List<ServerGroupUserDTO> serverGroupUserDTOs = new ArrayList<>();

			MgmUserQuery mgmUserQuery = new MgmUserQuery();
			List<MgmUser> mgmUsers = mgmApi.listUser(serverGroupId, mgmUserQuery);
			for (MgmUser mgmUser : mgmUsers) {
				ServerGroupUserDTO serverGroupUserDTO = getShowBaseDTO(mgmUser);
				serverGroupUserDTOs.add(serverGroupUserDTO);
			}

			return Result.success(serverGroupUserDTOs);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 服务组用户详情查询
	 * 
	 * @param serverGroupId
	 * @return
	 * @throws ServiceException
	 */
	@Transactional
	public Result get(String serverGroupId, String username, String ip) throws ServiceException {
		ServerGroupUserDTO serverGroupUserDTO = null;
		try {
			if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
			if(nullCheck(username)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_USERNAME_MUST_NOT_BE_BLANK));
        	}
			if(nullCheck(ip)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_IP_MUST_NOT_BE_BLANK));
        	}
			MgmUser mgmUsers = mgmApi.userDetail(serverGroupId,username, ip);
			if (mgmUsers != null ) {
				serverGroupUserDTO = getShowBaseDTO(mgmUsers);
			}
			return Result.success(serverGroupUserDTO);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 服务组用户保存
	 * 
	 * @param serverGroupId
	 * @param databaseForm
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(rollbackFor = ServiceException.class)
	public Result save(String serverGroupId, ServerGroupUserForm serverGroupUserForm) throws ServiceException {
		try {
			if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
			CheckResult checkResult = serverGroupUserCheck.checkSave(serverGroupUserForm);
			if (checkResult.getCode() != SiteChkRsEnum.SUCCESS.getCode()) {
				logger.error(loginfo(checkResult));
				return Result.failure(checkResult);
			}

			MgmServerGroupUserBody mgmServerGroupUserBody = buildSaveMgmDatabaseBody(serverGroupUserForm);
			mgmApi.saveUser(serverGroupId, mgmServerGroupUserBody);

			return Result.success();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 服务组用户编辑
	 * 
	 * @param serverGroupId
	 * @param username
	 * @param ip
	 * @param serverGroupUserForm
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(rollbackFor = ServiceException.class)
	public Result update(String serverGroupId, String username, ServerGroupUpdateUserForm serverGroupUserForm)
			throws ServiceException {
		try {
			if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
			if(nullCheck(username)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_USERNAME_MUST_NOT_BE_BLANK));
        	}
			// TODO 服务组用户编辑保存调用mgm接口， 该接口暂时不用不提供
			serverGroupUserForm.setUsername(username);
			serverGroupUserForm.setWhiteIps(serverGroupUserForm.getIp());
			CheckResult checkResult = serverGroupUserCheck.checkUpdateSave(serverGroupUserForm);
			if (checkResult.getCode() !=ServerGroupUserChkRsEnum.SUCCESS.getCode()) { 
					logger.error(loginfo(checkResult));
					return Result.failure(checkResult); 
			} 
			MgmServerGroupUpdateUserBody mgmServerGroupUserBody =buildUpdateMgmDatabaseBody(serverGroupUserForm);
			mgmApi.updateUser(serverGroupId, mgmServerGroupUserBody);
			return Result.success();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Transactional(rollbackFor = ServiceException.class)
	public Result changeUserPwd(String serverGroupId, String username, ServerGroupChangeUserPwdForm serverGroupUserForm)
			throws ServiceException {
		try {
			if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
			if(nullCheck(username)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_USERNAME_MUST_NOT_BE_BLANK));
        	}
			// TODO 服务组用户编辑保存调用mgm接口， 该接口暂时不用不提供
			serverGroupUserForm.setName(username);
			CheckResult checkResult = serverGroupUserCheck.checkChangeUserPwd(serverGroupUserForm);
			if (checkResult.getCode() !=ServerGroupUserChkRsEnum.SUCCESS.getCode()) { 
					logger.error(loginfo(checkResult));
					return Result.failure(checkResult); 
			} 
			mgmApi.changeUserPwd(serverGroupId, serverGroupUserForm);
			return Result.success();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 服务组用户删除
	 * 
	 * @param serverGroupId
	 * @param databaseName
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(rollbackFor = ServiceException.class)
	public Result remove(String serverGroupId, String username, String ip) throws ServiceException {
		try {
			if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
			if(nullCheck(username)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_USERNAME_MUST_NOT_BE_BLANK));
        	}
			if(nullCheck(ip)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_IP_MUST_NOT_BE_BLANK));
        	}
			mgmApi.removeUser(serverGroupId, username, ip);
			return Result.success();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * restful接口数据转前端数据
	 * 
	 * @param mgmSite
	 * @return
	 * @throws IOException
	 * @throws CallingInterfaceException
	 * @throws ServiceNotFoundException
	 * @throws ConnectConsulException
	 */
	private ServerGroupUserDTO getShowBaseDTO(MgmUser mgmUser)
			throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
		if (mgmUser == null) {
			return null;
		}

		ServerGroupUserDTO serverGroupUserDTO = new ServerGroupUserDTO();
		serverGroupUserDTO.setUsername(mgmUser.getName());
		serverGroupUserDTO.setWhiteIps(mgmUser.getIp());
		serverGroupUserDTO.setAuth_type(mgmUser.getAuthType());
		com.bsg.upm.mgm.model.MgmUser.Privilege[] privileges = mgmUser.getPrivileges();
		if (privileges != null && privileges.length > 0) {
			List<Privilege> privilegeDTOs = new ArrayList<>();
			for (com.bsg.upm.mgm.model.MgmUser.Privilege privilege : privileges) {
				Privilege privilegeDTO = serverGroupUserDTO.new Privilege();
				privilegeDTO.setDbName(privilege.getDbName());
				privilegeDTO.setPrivileges(privilege.getPrivileges());
				privilegeDTOs.add(privilegeDTO);
			}

			serverGroupUserDTO.setPrivileges(privilegeDTOs.toArray(new Privilege[privileges.length]));
		}

		return serverGroupUserDTO;
	}

	private MgmServerGroupUserBody buildSaveMgmDatabaseBody(ServerGroupUserForm serverGroupUserForm) {
		MgmServerGroupUserBody mgmServerGroupUserBody = new MgmServerGroupUserBody();

		mgmServerGroupUserBody.setName(serverGroupUserForm.getUsername());
		mgmServerGroupUserBody.setIp(serverGroupUserForm.getWhiteIps());
		// 数据库用户类型写死
		mgmServerGroupUserBody.setAuthType("mysql_native_password");
		mgmServerGroupUserBody.setPwd(serverGroupUserForm.getPassword());

		PrivilegeForm[] privilegeForms = serverGroupUserForm.getDb_privileges();
		if (privilegeForms != null && privilegeForms.length > 0) {
			MgmServerGroupUserBody.Privilege[] mgmPrivileges = new MgmServerGroupUserBody.Privilege[privilegeForms.length];

			for (int i = 0; i < privilegeForms.length; i++) {
				MgmServerGroupUserBody.Privilege mgmPrivilege = new MgmServerGroupUserBody.Privilege();
				mgmPrivilege.setDbName(privilegeForms[i].getDbName());
				mgmPrivilege.setPrivileges(privilegeForms[i].getPrivileges());

				mgmPrivileges[i] = mgmPrivilege;
			}

			mgmServerGroupUserBody.setDb_privileges(mgmPrivileges);
		}

		return mgmServerGroupUserBody;
	}
	
	private MgmServerGroupUpdateUserBody buildUpdateMgmDatabaseBody(ServerGroupUpdateUserForm serverGroupUserForm) {
		MgmServerGroupUpdateUserBody mgmServerGroupUserBody = new MgmServerGroupUpdateUserBody();

		mgmServerGroupUserBody.setName(serverGroupUserForm.getUsername());
		mgmServerGroupUserBody.setIp(serverGroupUserForm.getWhiteIps());
		mgmServerGroupUserBody.setDb_privileges(serverGroupUserForm.getDb_privileges());
		// 数据库用户类型写死
		return mgmServerGroupUserBody;
	}
	
	
	
	@Transactional
    public Result getUnitConf(String servGroupId) throws ServiceException {
        try {
        	if(nullCheck(servGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
        	List<UnitCfgsConf> list=new ArrayList<>();
            List<KeySet> mgmImageConfList=mgmApi.UnitCfgsConf(servGroupId);
            if(mgmImageConfList.size()!=0) {
            	for(KeySet keySet:mgmImageConfList) {
            		UnitCfgsConf mgmImageConf=buildUnitCfgs(keySet);
            		list.add(mgmImageConf);
                }
            }
            return Result.success(list);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
	
	private ImageConf getImageConfDTO(KeySet keySet) {
    	ImageConf imageConf=new ImageConf();
    	imageConf.setCanset(keySet.getCanSet());
    	imageConf.setMustRestart(keySet.getMustRestart());
    	imageConf.setKey(keySet.getKey());
    	imageConf.setValue(keySet.getValue());
    	imageConf.setDefaultValue(keySet.getDefaultValue());
    	imageConf.setDescription(keySet.getDesc());
    	imageConf.setRange(keySet.getRange());
        return imageConf;
    }
	
	@Transactional
    public Result editCfgs(String servGroupId,KeysetForm keysetForm) throws ServiceException {
        try {
        	if(nullCheck(servGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
			CheckResult checkResult = serverGroupCfgsCheck.checkSave(keysetForm);
			if (checkResult.getCode() !=ServerGroupUserChkRsEnum.SUCCESS.getCode()) { 
					logger.error(loginfo(checkResult));
					return Result.failure(checkResult); 
			} 
			MgmServerGroupCfgsBody mgmServerGroupCfgsBody =buildEditCfgsMgmDatabaseBody(keysetForm);
			mgmApi.updateCfgs(servGroupId, mgmServerGroupCfgsBody);
			return Result.success();
        	
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
	
	private MgmServerGroupCfgsBody buildEditCfgsMgmDatabaseBody(KeysetForm keysetForm) {
		MgmServerGroupCfgsBody mgmServerGroupCfgsBody = new MgmServerGroupCfgsBody();

		mgmServerGroupCfgsBody.setKey(keysetForm.getKey());
		mgmServerGroupCfgsBody.setValue(keysetForm.getValue());
		// 数据库用户类型写死
		return mgmServerGroupCfgsBody;
	}
	
	private UnitCfgsConf  buildUnitCfgs(KeySet keySet) {
		UnitCfgsConf mgmImageConf=new UnitCfgsConf();
		mgmImageConf.setCanset(keySet.getCanSet());
		mgmImageConf.setDefaultValue(keySet.getDefaultValue());
		mgmImageConf.setDescription(keySet.getDesc());
		mgmImageConf.setKey(keySet.getKey());
		mgmImageConf.setMustRestart(keySet.getMustRestart());
		mgmImageConf.setValue(keySet.getValue());
		mgmImageConf.setRange(keySet.getRange());
		return mgmImageConf;
	}
	
	

}
