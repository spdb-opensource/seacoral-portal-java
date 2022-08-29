package com.bsg.upm.check;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.RoleCfgAppChkRsEnum;
import com.bsg.upm.domain.AppDO;
import com.bsg.upm.domain.RoleCfgAppDO;
import com.bsg.upm.domain.RoleDO;
import com.bsg.upm.form.RoleCfgAppForm;

@Service
public class RoleCfgAppCheck extends BaseCheck {
	
	/**
     * check for save
     * 
     * @param roleId
     * @param cfgAppForm
     * @return
     */
	public CheckResult checkSave(String roleId,RoleCfgAppForm cfgResourceForms) {
        // Non-logical check for save
        CheckResult checkResult = checkSaveNonLogic(cfgResourceForms);
        if (checkResult.getCode() != RoleCfgAppChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for save
        checkResult = checkSaveLogic(roleId, cfgResourceForms);

        return checkResult;
    }
	
	/**
     * check for updae
     * 
     * @param roleId
     * @param cfgAppForm
     * @return
     */
    public CheckResult checkUpdate(String roleId, RoleCfgAppForm cfgAppForm) {
        // Non-logical check for update
        CheckResult checkResult = checkUpdateNonLogic(cfgAppForm);
        if (checkResult.getCode() != RoleCfgAppChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for update
        checkResult = checkUpdateLogic(roleId, cfgAppForm);

        return checkResult;
    }
    
	/**
     * Non-logical check for save
     * 
     * @param cfgResourceForms
     * @return
     */
    private CheckResult checkSaveNonLogic(RoleCfgAppForm cfgAppForm) {
        if (cfgAppForm == null) {
            return CheckResult.failure(RoleCfgAppChkRsEnum.ILLEGAL_FORMAT);
        }
        if(cfgAppForm.getAppIds()==null || cfgAppForm.getAppIds().size() == 0){
        	return CheckResult.failure(RoleCfgAppChkRsEnum.ADD_ILLEGAL_APP_IDS_MUST_NOT_BE_NULL);
        }
        return CheckResult.success();
    }
    
    /**
     * logical check for save
     * 
     * @param roleId
     * @param cfgAppForm
     * @return
     */
    private CheckResult checkSaveLogic(String roleId, RoleCfgAppForm cfgAppForm) {
        RoleDO roleDO = roleDAO.get(roleId);
        if (roleDO == null) {
            return CheckResult.failure(RoleCfgAppChkRsEnum.ILLEGAL_ROLE_ID_NOT_EXIST, roleId);
        }

        List<RoleCfgAppDO> cfgAppDOs = roleCfgAppDAO.listByRoleId(roleId);
        if (cfgAppDOs.size() != 0) {
            return CheckResult.failure(RoleCfgAppChkRsEnum.ILLEGAL_ROLE_CFG_EXIST, roleId);
        }
        List<AppDO> appDOs=appDAO.list(null);
        
        for (Long appid : cfgAppForm.getAppIds()) {
        	AppDO appDO = findAppDO(appDOs,appid);
            if (appDO == null) {
                return CheckResult.failure(RoleCfgAppChkRsEnum.ADD_ILLEGAL_APP_ID_NOT_EXIST,
                        cfgAppForm.getAppIds());
            }

        }
        return CheckResult.success();
    }
    /**
     * Non-logical check for update
     * 
     * @param cfgResourceForm
     * @return
     */
    private CheckResult checkUpdateNonLogic(RoleCfgAppForm cfgAppForm) {
        if (cfgAppForm == null) {
            return CheckResult.failure(RoleCfgAppChkRsEnum.ILLEGAL_FORMAT);
        }
        if(cfgAppForm.getAppIds()==null || cfgAppForm.getAppIds().size() == 0){
        	return CheckResult.failure(RoleCfgAppChkRsEnum.UPDATE_ILLEGAL_APP_IDS_MUST_NOT_BE_NULL);
        }
        return CheckResult.success();
    }

    /**
     * logical check for update
     * 
     * @param roleId
     * @param cfgAppForm
     * @return
     */
    private CheckResult checkUpdateLogic(String roleId, RoleCfgAppForm cfgAppForm) {
        RoleDO roleDO = roleDAO.get(roleId);
        if (roleDO == null) {
            return CheckResult.failure(RoleCfgAppChkRsEnum.ILLEGAL_ROLE_ID_NOT_EXIST, roleId);
        }

       /* List<RoleCfgAppDO> cfgAppDOs = roleCfgAppDAO.listByRoleId(roleId);
        if (cfgAppDOs.size() != 0) {
            return CheckResult.failure(RoleCfgAppChkRsEnum.ILLEGAL_ROLE_CFG_EXIST, roleId);
        }*/
        List<AppDO> appDOs=appDAO.list(null);
        
        for (Long appid : cfgAppForm.getAppIds()) {
        	AppDO appDO = findAppDO(appDOs,appid);
            if (appDO == null) {
                return CheckResult.failure(RoleCfgAppChkRsEnum.UPDATE_ILLEGAL_APP_ID_NOT_EXIST,
                        cfgAppForm.getAppIds());
            }

        }
        return CheckResult.success();
    }
}
