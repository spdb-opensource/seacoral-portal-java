package com.bsg.upm.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.RoleCheck;
import com.bsg.upm.check.resultenum.RoleChkRsEnum;
import com.bsg.upm.check.resultenum.SanChkRsEnum;
import com.bsg.upm.domain.RoleDO;
import com.bsg.upm.domain.UserDO;
import com.bsg.upm.dto.BaseDTO.InfoDTO;
import com.bsg.upm.dto.RoleDTO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.RoleForm;
import com.bsg.upm.util.DateUtils;
import com.bsg.upm.util.PinyinUtil;

@Service
public class RoleService extends BaseService {

    @Autowired
    private RoleCheck roleCheck;

    @Transactional
    public Result list() throws ServiceException {
        try {
            List<RoleDTO> roleDTOs = new ArrayList<>();
            List<RoleDO> roleDOs = roleDAO.list(null);
            List<UserDO> userDOs = userDAO.listBase();
            for (RoleDO roleDO : roleDOs) {
                UserDO userDO = findUserDO(userDOs, roleDO.getCreator());
                RoleDTO roleDTO = getShowDTO(roleDO, userDO);
                roleDTOs.add(roleDTO);
            }

            return Result.success(roleDTOs);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Result get(String roleId) throws ServiceException {
        RoleDTO roleDTO = null;
        try {
        	if(nullCheck(roleId)) {
        		return Result.failure(CheckResult.failure(RoleChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            RoleDO roleDO = roleDAO.get(roleId);
            if (roleDO != null) {
                UserDO userDO = userDAO.getByUsername(roleDO.getCreator());
                roleDTO = getShowDTO(roleDO, userDO);
            }

            return Result.success(roleDTO);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result save(RoleForm roleForm) throws ServiceException {
        try {
            CheckResult checkResult = roleCheck.checkSave(roleForm);
            if (checkResult.getCode() != RoleChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            RoleDO roleDO = buildRoleForSave(roleForm);
            roleDAO.save(roleDO);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result update(String roleId, RoleForm roleForm) throws ServiceException {
        try {
        	if(nullCheck(roleId)) {
        		return Result.failure(CheckResult.failure(RoleChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = roleCheck.checkUpdate(roleId, roleForm);
            if (checkResult.getCode() != RoleChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            RoleDO newRoleDO = buildRoleForUpdate(roleId, roleForm);
            roleDAO.update(newRoleDO);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result remove(String roleId) throws ServiceException {
        try {
        	if(nullCheck(roleId)) {
        		return Result.failure(CheckResult.failure(RoleChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = roleCheck.checkRemove(roleId);
            if (checkResult.getCode() != RoleChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            roleDAO.remove(roleId);
            roleCfgDataScopeDAO.removeByRoleId(roleId);
            roleCfgOthersDAO.removeByRoleId(roleId);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    private RoleDTO getShowDTO(RoleDO roleDO, UserDO userDO) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(roleDO.getId());
        roleDTO.setName(roleDO.getName());
        roleDTO.setSequence(roleDO.getSequence());
        roleDTO.setDescription(roleDO.getDescription());
        InfoDTO createdDTO = roleDTO.new InfoDTO();
        createdDTO.setUsername(roleDO.getCreator());
        createdDTO.setTimestamp(DateUtils.dateTimeToString(roleDO.getGmtCreate()));
        roleDTO.setCreated(createdDTO);

        InfoDTO modifiedDTO = roleDTO.new InfoDTO();
        modifiedDTO.setUsername(roleDO.getEditor());
        modifiedDTO.setTimestamp(DateUtils.dateTimeToString(roleDO.getGmtModified()));
        roleDTO.setModified(modifiedDTO);
        return roleDTO;
    }

    private RoleDO buildRoleForSave(RoleForm roleForm) {
        RoleDO roleDO = new RoleDO();
        roleDO.setId(PinyinUtil.getUuid());
        roleDO.setName(roleForm.getName());
        if (roleForm.getSequence() != null) {
            roleDO.setSequence(roleForm.getSequence());
        }
        roleDO.setDescription(StringUtils.trimToEmpty(roleForm.getDescription()));
        roleDO.setCreator(getUsername());
        roleDO.setGmtCreate(systemDAO.getCurrentSqlDateTime());
        return roleDO;
    }

    private RoleDO buildRoleForUpdate(String roleId, RoleForm roleForm) {
        RoleDO newRoleDO = new RoleDO();
        RoleDO roleDO = roleDAO.get(roleId);
        BeanUtils.copyProperties(roleDO, newRoleDO);

        if (roleForm.getName() != null) {
            newRoleDO.setName(roleForm.getName());
        }
        if (roleForm.getSequence() != null) {
            newRoleDO.setSequence(roleForm.getSequence());
        }
        if (roleForm.getDescription() != null) {
            newRoleDO.setDescription(roleForm.getDescription());
        }
        newRoleDO.setEditor(getUsername());
        newRoleDO.setGmtModified(systemDAO.getCurrentSqlDateTime());
        return newRoleDO;
    }

    public RoleDO getDomain(String roleId) {
        return roleDAO.get(roleId);
    }

}
