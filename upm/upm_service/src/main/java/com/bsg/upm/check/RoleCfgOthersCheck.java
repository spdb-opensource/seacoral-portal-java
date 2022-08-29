package com.bsg.upm.check;

import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.RoleCfgOthersChkRsEnum;
import com.bsg.upm.domain.RoleCfgOthersDO;
import com.bsg.upm.domain.RoleDO;
import com.bsg.upm.form.RoleCfgOthersForm;

@Service
public class RoleCfgOthersCheck extends BaseCheck {

    /**
     * check for save
     * 
     * @param roleId
     * @param cfgOthersForm
     * @return
     */
    public CheckResult checkSave(String roleId, RoleCfgOthersForm cfgOthersForm) {
        // Non-logical check for save
        CheckResult checkResult = checkSaveNonLogic(cfgOthersForm);
        if (checkResult.getCode() != RoleCfgOthersChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for save
        checkResult = checkSaveLogic(roleId, cfgOthersForm);

        return checkResult;
    }

    /**
     * check for updae
     * 
     * @param roleId
     * @param cfgOthersForm
     * @return
     */
    public CheckResult checkUpdate(String roleId, RoleCfgOthersForm cfgOthersForm) {
        // Non-logical check for update
        CheckResult checkResult = checkUpdateNonLogic(cfgOthersForm);
        if (checkResult.getCode() != RoleCfgOthersChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for update
        checkResult = checkUpdateLogic(roleId, cfgOthersForm);

        return checkResult;
    }

    /**
     * Non-logical check for save
     * 
     * @param cfgOthersForm
     * @return
     */
    private CheckResult checkSaveNonLogic(RoleCfgOthersForm cfgOthersForm) {
        if (cfgOthersForm.getOgAutoAudit() == null) {
            return CheckResult.failure(RoleCfgOthersChkRsEnum.ADD_ILLEGAL_ORDER_AUTO_AUDIT_MUST_NOT_BE_NULL);
        }
        if (cfgOthersForm.getOgAutoExecute() == null) {
            return CheckResult.failure(RoleCfgOthersChkRsEnum.ADD_ILLEGAL_ORDER_AUTO_AUDIT_MUST_NOT_BE_NULL);
        }
        return CheckResult.success();
    }

    /**
     * logical check for save
     * 
     * @param roleId
     * @param cfgOthersForm
     * @return
     */
    private CheckResult checkSaveLogic(String roleId, RoleCfgOthersForm cfgOthersForm) {
        RoleDO roleDO = roleDAO.get(roleId);
        if (roleDO == null) {
            return CheckResult.failure(RoleCfgOthersChkRsEnum.ILLEGAL_ROLE_ID_NOT_EXIST, roleId);
        }

        RoleCfgOthersDO cfgOthersDO = roleCfgOthersDAO.getByRoleId(roleId);
        if (cfgOthersDO != null) {
            return CheckResult.failure(RoleCfgOthersChkRsEnum.ILLEGAL_ROLE_CFG_EXIST, roleDO.getName());
        }
        return CheckResult.success();
    }

    /**
     * Non-logical check for update
     * 
     * @param cfgOthersForm
     * @return
     */
    private CheckResult checkUpdateNonLogic(RoleCfgOthersForm cfgOthersForm) {
        return CheckResult.success();
    }

    /**
     * logical check for update
     * 
     * @param roleId
     * @param cfgOthersForm
     * @return
     */
    private CheckResult checkUpdateLogic(String roleId, RoleCfgOthersForm cfgOthersForm) {
        RoleDO roleDO = roleDAO.get(roleId);
        if (roleDO == null) {
            return CheckResult.failure(RoleCfgOthersChkRsEnum.ILLEGAL_ROLE_ID_NOT_EXIST, roleId);
        }
        if (cfgOthersForm.getOgAutoAudit() == null) {
            return CheckResult.failure(RoleCfgOthersChkRsEnum.ADD_ILLEGAL_ORDER_AUTO_AUDIT_MUST_NOT_BE_NULL);
        }
        if (cfgOthersForm.getOgAutoExecute() == null) {
            return CheckResult.failure(RoleCfgOthersChkRsEnum.ADD_ILLEGAL_ORDER_AUTO_AUDIT_MUST_NOT_BE_NULL);
        }

        return CheckResult.success();
    }
}
