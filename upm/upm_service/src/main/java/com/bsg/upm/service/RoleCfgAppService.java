package com.bsg.upm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.RoleCfgAppCheck;
import com.bsg.upm.check.resultenum.RoleCfgAppChkRsEnum;
import com.bsg.upm.check.resultenum.RoleChkRsEnum;
import com.bsg.upm.domain.RoleCfgAppDO;
import com.bsg.upm.domain.RoleDO;
import com.bsg.upm.dto.RoleCfgAppDTO;
import com.bsg.upm.dto.RoleCfgAppDTO.AppDTO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.RoleCfgAppForm;
import com.bsg.upm.util.PinyinUtil;

@Service
public class RoleCfgAppService  extends BaseService {

	@Autowired
    private RoleCfgAppCheck cfgAppCheck;
	
	public Result get(String roleId) throws ServiceException {
        RoleCfgAppDTO cfgAppDTO = null;
        try {
        	if(nullCheck(roleId)) {
        		return Result.failure(CheckResult.failure(RoleChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            RoleDO roleDO = roleDAO.get(roleId);

            if (roleDO != null) {
                cfgAppDTO=new RoleCfgAppDTO();
                cfgAppDTO.setRoleId(roleDO.getId());
                cfgAppDTO.setRoleName(roleDO.getName());
                List<AppDTO> appDTOs=new ArrayList<>();
                cfgAppDTO.setApps(appDTOs);
                
                List<RoleCfgAppDO> cfgAppDOs=roleCfgAppDAO.listByRoleId(roleId);
                for(RoleCfgAppDO cfgAppDO : cfgAppDOs){
                	AppDTO appDTO=cfgAppDTO.new AppDTO();
                	appDTOs.add(appDTO);
                	
                	appDTO.setId(cfgAppDO.getAppId());
                	
                }
            }

            return Result.success(cfgAppDTO);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
	
	@Transactional(rollbackFor = ServiceException.class)
	public Result save(String roleId,RoleCfgAppForm cfgAppForm)throws ServiceException {
		 try {
			 	if(nullCheck(roleId)) {
	        		return Result.failure(CheckResult.failure(RoleChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
	        	}
		        CheckResult checkResult = cfgAppCheck.checkSave(roleId, cfgAppForm);
		        if (checkResult.getCode() != RoleCfgAppChkRsEnum.SUCCESS.getCode()) {
		            logger.error(loginfo(checkResult));
		            return Result.failure(checkResult);
		        }
		        saveNonCheck(roleId, cfgAppForm);
		        return Result.success();
		    } catch (Exception e) {
		        throw new ServiceException(e);
		    }
	}
	
	@Transactional(rollbackFor = ServiceException.class)
    public Result update(String roleId, RoleCfgAppForm cfgAppForm) throws ServiceException {
        try {
        	if(nullCheck(roleId)) {
        		return Result.failure(CheckResult.failure(RoleChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = cfgAppCheck.checkUpdate(roleId, cfgAppForm);
            if (checkResult.getCode() != RoleCfgAppChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            roleCfgAppDAO.removeByRoleId(roleId);
            saveNonCheck(roleId, cfgAppForm);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
	
	private void saveNonCheck(String roleId, RoleCfgAppForm cfgAppForm) {
        for (Long appId : cfgAppForm.getAppIds()) {
            RoleCfgAppDO cfgAppDO=new RoleCfgAppDO();
            cfgAppDO.setId(PinyinUtil.getUuid());
            cfgAppDO.setRoleId(roleId);
            cfgAppDO.setAppId(appId);
            
            roleCfgAppDAO.save(cfgAppDO);
        }
    }
	
}
