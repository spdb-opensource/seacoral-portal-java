package com.bsg.upm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.UserCheck;
import com.bsg.upm.check.resultenum.SiteChkRsEnum;
import com.bsg.upm.check.resultenum.UserChkRsEnum;
import com.bsg.upm.constant.DictConsts;
import com.bsg.upm.domain.GroupDO;
import com.bsg.upm.domain.GroupUserDO;
import com.bsg.upm.domain.RoleDO;
import com.bsg.upm.domain.UserDO;
import com.bsg.upm.dto.BaseDTO.InfoDTO;
import com.bsg.upm.dto.UserDTO;
import com.bsg.upm.dto.UserDTO.RoleDTO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.PwdForm;
import com.bsg.upm.form.UserForm;
import com.bsg.upm.param.UserDAOParam;
import com.bsg.upm.query.UserParam;
import com.bsg.upm.util.DateUtils;
import com.bsg.upm.util.PinyinUtil;

@Service
public class UserService extends BaseService {

    @Autowired
    private UserCheck userCheck;

    @Transactional(rollbackFor = ServiceException.class)
    public Result list(UserParam userParam) throws ServiceException {
    	try {
	        List<UserDTO> userDTOs = new ArrayList<>();
	        UserDAOParam userDAOParam = new UserDAOParam();
	        BeanUtils.copyProperties(userParam, userDAOParam);
	
	        List<UserDO> userDOs = userDAO.list(userDAOParam);
	        for (UserDO userDO : userDOs) {
	            UserDTO userDTO = getShowBaseDTO(userDO);
	            userDTOs.add(userDTO);
	        }
	
	        return Result.success(userDTOs);
	    } catch (Exception e) {
	        throw new ServiceException(e);
	    }
    }

    public Result get(String username) throws ServiceException {
    	try {
	        UserDTO userDTO = null;
	        if(nullCheck(username)) {
	    		return Result.failure(CheckResult.failure(UserChkRsEnum.UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY));
	    	}
	        UserDO userDO = userDAO.getByUsername(username);
	
	        if (userDO != null) {
	            userDTO = getShowBaseDTO(userDO);
	        }
	
	        return Result.success(userDTO);
	    } catch (Exception e) {
	        throw new ServiceException(e);
	    }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result save(UserForm userForm) throws ServiceException {
    	try {
	        CheckResult checkResult = userCheck.checkSave(userForm);
	        if (checkResult.getCode() != UserChkRsEnum.SUCCESS.getCode()) {
	            logger.error(loginfo(checkResult));
	            return Result.failure(checkResult);
	        }
	
	        Date date = systemDAO.getCurrentSqlDateTime();
	        String username = getUsername();
	
	        UserDO userDO = new UserDO();
	        BeanUtils.copyProperties(userForm, userDO);
	        userDO.setId(PinyinUtil.getUuid());
	        userDO.setPassword(DigestUtils.md5DigestAsHex(userForm.getPassword().getBytes()));
	        userDO.setAuthType(DictConsts.AUTH_TYPE_NATIVE);
	        userDO.setEnabled(true);
	        userDO.setCreator(username);
	        userDO.setGmtCreate(date);
	        userDAO.save(userDO);
	        for(String groupUserId:userForm.getGroupIds()) {
	        	int index=0;
            	String aa=""+index;
	        	GroupUserDO groupUserDO=new GroupUserDO();
	        	groupUserDO.setGroupId(groupUserId);
	        	groupUserDO.setUsername(username);
	        	groupUserDO.setId(PinyinUtil.getUuid()+aa);
	        	groupUserDAO.saveUser(groupUserDO);
	        	index+=1;
	        }
	
	        return Result.success();
    	} catch (Exception e) {
	        throw new ServiceException(e);
	    }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result update(String username, UserForm userForm) throws ServiceException {
	    try {
	    	if(nullCheck(username)) {
	    		return Result.failure(CheckResult.failure(UserChkRsEnum.UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY));
	    	}
	        CheckResult checkResult = userCheck.checkUpdate(username, userForm);
	        if (checkResult.getCode() != UserChkRsEnum.SUCCESS.getCode()) {
	            logger.error(loginfo(checkResult));
	            return Result.failure(checkResult);
	        }
	
	        UserDO userDO = userDAO.getByUsername(username);
	        if (StringUtils.isNotBlank(userForm.getName())) {
	            userDO.setName(userForm.getName());
	        }
	        if (StringUtils.isNotBlank(userForm.getTelephone())) {
	            userDO.setTelephone(userForm.getTelephone());
	        }
	        if (StringUtils.isNotBlank(userForm.getEmail())) {
	            userDO.setEmail(userForm.getEmail());
	        }
	        if (StringUtils.isNotBlank(userForm.getCompany())) {
	            userDO.setCompany(userForm.getCompany());
	        }
	        if (StringUtils.isNotBlank(userForm.getEmerContact())) {
	            userDO.setEmerContact(userForm.getEmerContact());
	        }
	        if (StringUtils.isNotBlank(userForm.getEmerTel())) {
	            userDO.setEmerTel(userForm.getEmerTel());
	        }
	        if (userForm.getRoleId() != null && !userDO.getRole().getId().equals(userForm.getRoleId())) {
	            userDO.setRoleId(userForm.getRoleId());
	        }
	        if (userForm.getGroupIds() != null) {
	            userDO.setGroupIds(userForm.getGroupIds());
	        }
	        userDO.setEditor(getUsername());
	        userDO.setGmtModified(systemDAO.getCurrentSqlDateTime());
	        userDAO.update(userDO);
	        groupUserDAO.removeByUsername(username);
	        for(String groupUserId:userForm.getGroupIds()) {
	        	int index=0;
            	String aa=""+index;
	        	GroupUserDO groupUserDO=new GroupUserDO();
	        	groupUserDO.setGroupId(groupUserId);
	        	groupUserDO.setUsername(username);
	        	groupUserDO.setId(PinyinUtil.getUuid()+aa);
	        	groupUserDAO.saveUser(groupUserDO);
	        	index+=1;
	        }
//	        groupUserDAO.save(userDO);
	
	        return Result.success();
	    } catch (Exception e) {
	        throw new ServiceException(e);
	    }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result enabled(String username, boolean enabled) throws ServiceException {
        try {
        	if(nullCheck(username)) {
        		return Result.failure(CheckResult.failure(UserChkRsEnum.UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY));
        	}
            CheckResult checkResult = userCheck.checkEnabled(username, enabled);
            if (checkResult.getCode() != UserChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            UserDO userDO = userDAO.getByUsername(username);
            userDO.setEnabled(enabled);
            userDO.setEditor(getUsername());
            userDO.setGmtModified(systemDAO.getCurrentSqlDateTime());
            userDAO.updateEnabled(userDO);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result updatePwd(String username, PwdForm pwdForm) throws ServiceException {
        try {
        	if(nullCheck(username)) {
        		return Result.failure(CheckResult.failure(UserChkRsEnum.UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY));
        	}
            CheckResult checkResult = userCheck.checkUpdatePwd(username, pwdForm);
            if (checkResult.getCode() != UserChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            UserDO userDO = userDAO.getByUsername(username);
            userDO.setPassword(DigestUtils.md5DigestAsHex(pwdForm.getNewPwd().getBytes()));
            userDO.setEditor(getUsername());
            userDO.setGmtModified(systemDAO.getCurrentSqlDateTime());
            userDAO.updatePwd(userDO);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    private UserDTO getShowBaseDTO(UserDO userDO) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDO, userDTO);

        RoleDO roleDO = userDO.getRole();
        RoleDTO roleDTO= userDTO.new RoleDTO();
        roleDTO.setId(roleDO.getId());
        roleDTO.setName(roleDO.getName());

        List<GroupDO> groupDOs = userDO.getGroups();
        List<String> groupNames = new ArrayList<>();
        for (GroupDO groupDO : groupDOs) {
            groupNames.add(groupDO.getName());
        }
        userDTO.setGroupNames(groupNames);
        userDTO.setRole(roleDTO);
        InfoDTO createdDTO=userDTO.new InfoDTO();
        createdDTO.setTimestamp(DateUtils.dateTimeToString(userDO.getGmtCreate()));
        createdDTO.setUsername(userDO.getCreator());
        userDTO.setCreated(createdDTO);
        
        InfoDTO modifiedDTO=userDTO.new InfoDTO();
        modifiedDTO.setTimestamp(DateUtils.dateTimeToString(userDO.getGmtModified()));
        modifiedDTO.setUsername(userDO.getEditor());
        userDTO.setModified(modifiedDTO);
        return userDTO;
    }

    public UserDO getDomain(String username) {
        return userDAO.getByUsername(username);
    }

}
