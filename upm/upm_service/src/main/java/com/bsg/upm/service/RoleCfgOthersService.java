package com.bsg.upm.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.RoleCfgOthersCheck;
import com.bsg.upm.check.resultenum.RoleCfgOthersChkRsEnum;
import com.bsg.upm.check.resultenum.RoleChkRsEnum;
import com.bsg.upm.domain.RoleCfgOthersDO;
import com.bsg.upm.domain.RoleDO;
import com.bsg.upm.dto.RoleCfgOthersDTO;
import com.bsg.upm.dto.RoleCfgOthersDTO.RoleDTO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.RoleCfgOthersForm;
import com.bsg.upm.util.PinyinUtil;

@Service
public class RoleCfgOthersService extends BaseService {

    @Autowired
    private RoleCfgOthersCheck cfgOthersCheck;

    public Result get(String roleId) throws ServiceException {
        RoleCfgOthersDTO cfgOthersDTO = null;
        try {
        	if(nullCheck(roleId)) {
        		return Result.failure(CheckResult.failure(RoleChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            RoleDO roleDO = roleDAO.get(roleId);

            if (roleDO != null) {
                RoleCfgOthersDO cfgOthersDO = roleCfgOthersDAO.getByRoleId(roleId);
                if (cfgOthersDO != null) {
                    cfgOthersDTO = getShowDTO(cfgOthersDO, roleDO);
                }
            }

            return Result.success(cfgOthersDTO);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result update(String roleId, RoleCfgOthersForm cfgOthersForm) throws ServiceException {
        try {
        	if(nullCheck(roleId)) {
        		return Result.failure(CheckResult.failure(RoleChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = cfgOthersCheck.checkUpdate(roleId, cfgOthersForm);
            if (checkResult.getCode() != RoleCfgOthersChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            RoleCfgOthersDO newCfgOthersDO = buildRoleCfgOthers(roleId, cfgOthersForm);
            RoleCfgOthersDO cfgOthersDO = roleCfgOthersDAO.getByRoleId(roleId);
            if (cfgOthersDO == null) {
                roleCfgOthersDAO.save(newCfgOthersDO);
            }else{
                roleCfgOthersDAO.update(newCfgOthersDO);
            }
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    private RoleCfgOthersDTO getShowDTO(RoleCfgOthersDO cfgOthersDO, RoleDO roleDO) {
        RoleCfgOthersDTO cfgOthersDTO = new RoleCfgOthersDTO();
        RoleDTO role=cfgOthersDTO.new RoleDTO();
        role.setId(roleDO.getId());
        role.setName(roleDO.getName());
        cfgOthersDTO.setRole(role);
        cfgOthersDTO.setOgAutoAudit(cfgOthersDO.getOrderAutoAudit());
        cfgOthersDTO.setOgAutoExecute(cfgOthersDO.getOrderAutoExecute());
        return cfgOthersDTO;
    }

    private RoleCfgOthersDO buildRoleCfgOthers(String roleId, RoleCfgOthersForm cfgOthersForm) {
        RoleCfgOthersDO cfgOthersDO = new RoleCfgOthersDO();
        cfgOthersDO.setId(PinyinUtil.getUuid());
        cfgOthersDO.setRoleId(roleId);
        cfgOthersDO.setOrderAutoAudit(cfgOthersForm.getOgAutoAudit());
        cfgOthersDO.setOrderAutoExecute(cfgOthersForm.getOgAutoExecute());
        return cfgOthersDO;
    }

}
