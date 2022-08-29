package com.bsg.upm.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.RoleCfgDataScopeChkRsEnum;
import com.bsg.upm.constant.DictTypeConsts;
import com.bsg.upm.domain.RoleCfgDataScopeDO;
import com.bsg.upm.domain.RoleDO;
import com.bsg.upm.form.RoleCfgDataScopeForm;

@Service
public class RoleCfgDataScopeCheck extends BaseCheck {

    /**
     * check for save
     * 
     * @param roleId
     * @param cfgDataScopeForm
     * @return
     */
    public CheckResult checkSave(String roleId, RoleCfgDataScopeForm cfgDataScopeForm) {
        // Non-logical check for save
        CheckResult checkResult = checkSaveNonLogic(cfgDataScopeForm);
        if (checkResult.getCode() != RoleCfgDataScopeChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for save
        checkResult = checkSaveLogic(roleId, cfgDataScopeForm);

        return checkResult;
    }

    /**
     * check for updae
     * 
     * @param roleId
     * @param cfgDataScopeForm
     * @return
     */
    public CheckResult checkUpdate(String roleId, RoleCfgDataScopeForm cfgDataScopeForm) {
        // Non-logical check for update
        CheckResult checkResult = checkUpdateNonLogic(cfgDataScopeForm);
        if (checkResult.getCode() != RoleCfgDataScopeChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for update
        checkResult = checkUpdateLogic(roleId, cfgDataScopeForm);

        return checkResult;
    }

    /**
     * Non-logical check for save
     * 
     * @param cfgDataScopeForm
     * @return
     */
    private CheckResult checkSaveNonLogic(RoleCfgDataScopeForm cfgDataScopeForm) {
        if (StringUtils.isBlank(cfgDataScopeForm.getOgScopeCode())) {
            return CheckResult.failure(RoleCfgDataScopeChkRsEnum.ADD_ILLEGAL_ORDER_GROUP_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isBlank(cfgDataScopeForm.getSgScopeCode())) {
            return CheckResult.failure(RoleCfgDataScopeChkRsEnum.ADD_ILLEGAL_SERV_GROUP_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isBlank(cfgDataScopeForm.getOperateLogScope())) {
            return CheckResult.failure(RoleCfgDataScopeChkRsEnum.ADD_ILLEGAL_OPERATE_LOG_MUST_NOT_BE_BLANK);
        }

        return CheckResult.success();
    }

    /**
     * logical check for save
     * 
     * @param roleId
     * @param cfgDataScopeForm
     * @return
     */
    private CheckResult checkSaveLogic(String roleId, RoleCfgDataScopeForm cfgDataScopeForm) {
        RoleDO roleDO = roleDAO.get(roleId);
        if (roleDO == null) {
            return CheckResult.failure(RoleCfgDataScopeChkRsEnum.ILLEGAL_ROLE_ID_NOT_EXIST, roleId);
        }

        RoleCfgDataScopeDO cfgDataScopeDO = roleCfgDataScopeDAO.getByRoleId(roleId);
        if (cfgDataScopeDO != null) {
            return CheckResult.failure(RoleCfgDataScopeChkRsEnum.ILLEGAL_ROLE_CFG_EXIST, roleDO.getName());
        }

        int orderGroupCnt = dictDAO.countByCodeAndDictTypeCode(cfgDataScopeForm.getOgScopeCode(),
                DictTypeConsts.DATA_SCOPE);
        if (orderGroupCnt == 0) {
            return CheckResult.failure(RoleCfgDataScopeChkRsEnum.ADD_ILLEGAL_ORDER_GROUP_NOT_EXIST,
                    cfgDataScopeForm.getOgScopeCode());
        }

        int servGroupCnt = dictDAO.countByCodeAndDictTypeCode(cfgDataScopeForm.getSgScopeCode(),
                DictTypeConsts.DATA_SCOPE);
        if (servGroupCnt == 0) {
            return CheckResult.failure(RoleCfgDataScopeChkRsEnum.ADD_ILLEGAL_SERV_GROUP_NOT_EXIST,
                    cfgDataScopeForm.getSgScopeCode());
        }


        int operateLogCnt = dictDAO.countByCodeAndDictTypeCode(cfgDataScopeForm.getOperateLogScope(),
                DictTypeConsts.DATA_SCOPE);
        if (operateLogCnt == 0) {
            return CheckResult.failure(RoleCfgDataScopeChkRsEnum.ADD_ILLEGAL_OPERATE_LOG_NOT_EXIST,
                    cfgDataScopeForm.getOperateLogScope());
        }
        return CheckResult.success();
    }

    /**
     * Non-logical check for update
     * 
     * @param cfgDataScopeForm
     * @return
     */
    private CheckResult checkUpdateNonLogic(RoleCfgDataScopeForm cfgDataScopeForm) {
        return CheckResult.success();
    }

    /**
     * logical check for update
     * 
     * @param roleId
     * @param cfgDataScopeForm
     * @return
     */
    private CheckResult checkUpdateLogic(String roleId, RoleCfgDataScopeForm cfgDataScopeForm) {
        RoleDO roleDO = roleDAO.get(roleId);
        if (roleDO == null) {
            return CheckResult.failure(RoleCfgDataScopeChkRsEnum.ILLEGAL_ROLE_ID_NOT_EXIST, roleId);
        }

        if (StringUtils.isNotBlank(cfgDataScopeForm.getOgScopeCode())) {
            int orderGroupCnt = dictDAO.countByCodeAndDictTypeCode(cfgDataScopeForm.getOgScopeCode(),
                    DictTypeConsts.DATA_SCOPE);
            if (orderGroupCnt == 0) {
                return CheckResult.failure(RoleCfgDataScopeChkRsEnum.UPDATE_ILLEGAL_ORDER_GROUP_NOT_EXIST,
                        cfgDataScopeForm.getOgScopeCode());
            }
        }

        if (StringUtils.isNotBlank(cfgDataScopeForm.getSgScopeCode())) {
            int servGroupCnt = dictDAO.countByCodeAndDictTypeCode(cfgDataScopeForm.getSgScopeCode(),
                    DictTypeConsts.DATA_SCOPE);
            if (servGroupCnt == 0) {
                return CheckResult.failure(RoleCfgDataScopeChkRsEnum.UPDATE_ILLEGAL_SERV_GROUP_NOT_EXIST,
                        cfgDataScopeForm.getSgScopeCode());
            }
        }

        if (StringUtils.isNotBlank(cfgDataScopeForm.getOperateLogScope())) {
            int operateLogCnt = dictDAO.countByCodeAndDictTypeCode(cfgDataScopeForm.getOperateLogScope(),
                    DictTypeConsts.DATA_SCOPE);
            if (operateLogCnt == 0) {
                return CheckResult.failure(RoleCfgDataScopeChkRsEnum.UPDATE_ILLEGAL_OPERATE_LOG_NOT_EXIST,
                        cfgDataScopeForm.getOperateLogScope());
            }
        }
        return CheckResult.success();
    }
}
