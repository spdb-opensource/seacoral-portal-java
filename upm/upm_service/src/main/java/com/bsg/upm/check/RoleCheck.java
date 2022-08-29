package com.bsg.upm.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.RoleChkRsEnum;
import com.bsg.upm.domain.RoleDO;
import com.bsg.upm.form.RoleForm;

@Service
public class RoleCheck extends BaseCheck {

    /**
     * check for save
     * 
     * @param roleForm
     * @return
     */
    public CheckResult checkSave(RoleForm roleForm) {
        // Non-logical check for save
        CheckResult checkResult = checkSaveNonLogic(roleForm);
        if (checkResult.getCode() != RoleChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for save
        checkResult = checkSaveLogic(roleForm);

        return checkResult;
    }

    /**
     * check for updae
     * 
     * @param roleId
     * @param roleForm
     * @return
     */
    public CheckResult checkUpdate(String roleId, RoleForm roleForm) {
        // Non-logical check for update
        CheckResult checkResult = checkUpdateNonLogic(roleForm);
        if (checkResult.getCode() != RoleChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for update
        checkResult = checkUpdateLogic(roleId, roleForm);

        return checkResult;
    }

    /**
     * check for remove
     * 
     * @param roleId
     * @return
     */
    public CheckResult checkRemove(String roleId) {
        RoleDO roleDO = roleDAO.get(roleId);
        if (roleDO == null) {
            return CheckResult.failure(RoleChkRsEnum.ILLEGAL_ID_NOT_EXIST, roleId);
        }


        int userCnt = userDAO.countByRoleId(roleId);
        if (userCnt != 0) {
            return CheckResult.failure(RoleChkRsEnum.REMOVE_ILLEGAL_ASSOCIATED_WITH_USER, roleDO.getName());
        }
        return CheckResult.success();
    }

    /**
     * Non-logical check for save
     * 
     * @param roleForm
     * @return
     */
    private CheckResult checkSaveNonLogic(RoleForm roleForm) {
        if (StringUtils.isBlank(roleForm.getName())) {
            return CheckResult.failure(RoleChkRsEnum.ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK);
        }
        return CheckResult.success();
    }

    /**
     * logical check for save
     * 
     * @param roleForm
     * @return
     */
    private CheckResult checkSaveLogic(RoleForm roleForm) {
        int nameCnt = roleDAO.countByName(roleForm.getName());
        if (nameCnt != 0) {
            return CheckResult.failure(RoleChkRsEnum.ADD_ILLEGAL_NAME_EXIST, roleForm.getName());
        }
        return CheckResult.success();
    }

    /**
     * Non-logical check for update
     * 
     * @param roleForm
     * @return
     */
    private CheckResult checkUpdateNonLogic(RoleForm roleForm) {
        if (roleForm.getName() != null && StringUtils.isEmpty(roleForm.getName())) {
            return CheckResult.failure(RoleChkRsEnum.UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY);
        }
        return CheckResult.success();
    }

    /**
     * logical check for update
     * 
     * @param roleId
     * @param roleForm
     * @return
     */
    private CheckResult checkUpdateLogic(String roleId, RoleForm roleForm) {
        RoleDO roleDO = roleDAO.get(roleId);
        if (roleDO == null) {
            return CheckResult.failure(RoleChkRsEnum.ILLEGAL_ID_NOT_EXIST, roleId);
        }


        if (StringUtils.isNotBlank(roleForm.getName()) && !roleForm.getName().equals(roleDO.getName())) {
            int nameCnt = roleDAO.countByName(roleForm.getName());
            if (nameCnt != 0) {
                return CheckResult.failure(RoleChkRsEnum.UPDATE_ILLEGAL_NAME_EXIST, roleForm.getName());
            }
        }
        return CheckResult.success();
    }
}
