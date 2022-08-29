package com.bsg.upm.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.RoleCfgDataScopeCheck;
import com.bsg.upm.check.resultenum.RoleCfgDataScopeChkRsEnum;
import com.bsg.upm.check.resultenum.RoleChkRsEnum;
import com.bsg.upm.constant.DictTypeConsts;
import com.bsg.upm.domain.RoleCfgDataScopeDO;
import com.bsg.upm.domain.RoleDO;
import com.bsg.upm.dto.RoleCfgDataScopeDTO;
import com.bsg.upm.dto.RoleCfgDataScopeDTO.RoleDTO;
import com.bsg.upm.dto.RoleCfgDataScopeDTO.ScopeDTO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.RoleCfgDataScopeForm;
import com.bsg.upm.util.PinyinUtil;

@Service
public class RoleCfgDataScopeService extends BaseService {

    @Autowired
    private RoleCfgDataScopeCheck cfgDataScopeCheck;

    public Result get(String roleId) throws ServiceException {
        RoleCfgDataScopeDTO cfgDataScopeDTO = null;
        try {
        	if(nullCheck(roleId)) {
        		return Result.failure(CheckResult.failure(RoleChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            RoleDO roleDO = roleDAO.get(roleId);

            if (roleDO != null) {
                RoleCfgDataScopeDO cfgDataScopeDO = roleCfgDataScopeDAO.getByRoleId(roleId);
                if (cfgDataScopeDO != null) {
                    cfgDataScopeDTO = getShowDTO(cfgDataScopeDO, roleDO);
                }
            }

            return Result.success(cfgDataScopeDTO);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result update(String roleId, RoleCfgDataScopeForm cfgDataScopeForm) throws ServiceException {
        try {
        	if(nullCheck(roleId)) {
        		return Result.failure(CheckResult.failure(RoleChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = cfgDataScopeCheck.checkUpdate(roleId, cfgDataScopeForm);
            if (checkResult.getCode() != RoleCfgDataScopeChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            RoleCfgDataScopeDO newCfgDataScopeDO = buildRoleCfgDataScope(roleId, cfgDataScopeForm);
            RoleCfgDataScopeDO cfgDataScopeDO = roleCfgDataScopeDAO.getByRoleId(roleId);
            if (cfgDataScopeDO == null) {
                roleCfgDataScopeDAO.save(newCfgDataScopeDO);
            } else {
                roleCfgDataScopeDAO.update(newCfgDataScopeDO);
            }
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    private RoleCfgDataScopeDTO getShowDTO(RoleCfgDataScopeDO cfgDataScopeDO, RoleDO roleDO) {
        RoleCfgDataScopeDTO cfgDataScopeDTO = new RoleCfgDataScopeDTO();
        RoleDTO role = cfgDataScopeDTO.new RoleDTO();
        role.setId(roleDO.getId());
        role.setName(roleDO.getName());

        ScopeDTO ogScope = cfgDataScopeDTO.new ScopeDTO();
        ogScope.setCode(cfgDataScopeDO.getOrderGroup());
        ogScope.setDisplay(
                dictDAO.getNameByCodeAndDictTypeCode(cfgDataScopeDO.getOrderGroup(), DictTypeConsts.DATA_SCOPE));

        ScopeDTO sgScope = cfgDataScopeDTO.new ScopeDTO();
        sgScope.setCode(cfgDataScopeDO.getServGroup());
        sgScope.setDisplay(
                dictDAO.getNameByCodeAndDictTypeCode(cfgDataScopeDO.getServGroup(), DictTypeConsts.DATA_SCOPE));

        ScopeDTO operateLogScope = cfgDataScopeDTO.new ScopeDTO();
        operateLogScope.setCode(cfgDataScopeDO.getOperateLog());
        operateLogScope.setDisplay(
                dictDAO.getNameByCodeAndDictTypeCode(cfgDataScopeDO.getOperateLog(), DictTypeConsts.DATA_SCOPE));

        cfgDataScopeDTO.setRole(role);
        cfgDataScopeDTO.setOgScope(ogScope);
        cfgDataScopeDTO.setSgScope(sgScope);
        cfgDataScopeDTO.setOperateLogScope(operateLogScope);

        return cfgDataScopeDTO;
    }

    private RoleCfgDataScopeDO buildRoleCfgDataScope(String roleId, RoleCfgDataScopeForm cfgDataScopeForm) {
        RoleCfgDataScopeDO cfgDataScopeDO = new RoleCfgDataScopeDO();
        cfgDataScopeDO.setId(PinyinUtil.getUuid());
        cfgDataScopeDO.setRoleId(roleId);
        cfgDataScopeDO.setOrderGroup(cfgDataScopeForm.getOgScopeCode());
        cfgDataScopeDO.setServGroup(cfgDataScopeForm.getSgScopeCode());
        cfgDataScopeDO.setOperateLog(cfgDataScopeForm.getOperateLogScope());
        return cfgDataScopeDO;
    }

}
