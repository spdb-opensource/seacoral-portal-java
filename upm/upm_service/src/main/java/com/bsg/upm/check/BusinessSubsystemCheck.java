package com.bsg.upm.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.BusinessSubsystemChkRsEnum;
import com.bsg.upm.dao.BusinessSubsystemDAO;
import com.bsg.upm.dao.BusinessSystemDAO;
import com.bsg.upm.domain.BusinessSubsystemDO;
import com.bsg.upm.domain.BusinessSystemDO;
import com.bsg.upm.form.BusinessSubsystemForm;

@Service
public class BusinessSubsystemCheck extends BaseCheck {
	
	@Autowired
    private BusinessSystemDAO businessSystemDAO;
	
	@Autowired
    private BusinessSubsystemDAO businessSubsystemDAO;

    /**
     * check for save
     * 
     * @param businessSubsystemForm
     * @return
     */
    public CheckResult checkSave(BusinessSubsystemForm businessSubsystemForm) {
        // Non-logical check for save
        CheckResult checkResult = checkSaveNonLogic(businessSubsystemForm);
        if (checkResult.getCode() != BusinessSubsystemChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for save
        checkResult = checkSaveLogic(businessSubsystemForm);

        return checkResult;
    }

    /**
     * check for updae
     * 
     * @param businessSubsystemId
     * @param businessSubsystemForm
     * @return
     */
    public CheckResult checkUpdate(String businessSubsystemId, BusinessSubsystemForm businessSubsystemForm) {
        // Non-logical check for update
        CheckResult checkResult = checkUpdateNonLogic(businessSubsystemForm);
        if (checkResult.getCode() != BusinessSubsystemChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for update
        checkResult = checkUpdateLogic(businessSubsystemId, businessSubsystemForm);

        return checkResult;
    }

    /**
     * check for remove
     * 
     * @param businessSubsystemId
     * @return
     */
    public CheckResult checkRemove(String businessSubsystemId) {
        BusinessSubsystemDO businessSubsystemDO = businessSubsystemDAO.get(businessSubsystemId);
        if (businessSubsystemDO == null) {
            return CheckResult.failure(BusinessSubsystemChkRsEnum.ILLEGAL_ID_NOT_EXIST, businessSubsystemId);
        }

        int businessSubsystemCnt = orderGroupDAO.countByBusinessSubsystemId(businessSubsystemId);
        if (businessSubsystemCnt != 0) {
            return CheckResult.failure(BusinessSubsystemChkRsEnum.REMOVE_ILLEGAL_ASSOCIATED_WITH_ORDER_GROUP,
                    businessSubsystemDO.getName());
        }
        return CheckResult.success();
    }

    /**
     * Non-logical check for save
     * 
     * @param businessSubsystemForm
     * @return
     */
    private CheckResult checkSaveNonLogic(BusinessSubsystemForm businessSubsystemForm) {
        if (businessSubsystemForm.getBusinessSystemId() == null) {
            return CheckResult.failure(BusinessSubsystemChkRsEnum.ADD_ILLEGAL_BUSINESS_SYSTEM_ID_MUST_NOT_BE_NULL);
        }

        if (StringUtils.isBlank(businessSubsystemForm.getName())) {
            return CheckResult.failure(BusinessSubsystemChkRsEnum.ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK);
        }

        return CheckResult.success();
    }

    /**
     * logical check for save
     * 
     * @param businessSubsystemForm
     * @return
     */
    private CheckResult checkSaveLogic(BusinessSubsystemForm businessSubsystemForm) {
        BusinessSystemDO businessSystemDO = businessSystemDAO.get(businessSubsystemForm.getBusinessSystemId());
        if (businessSystemDO == null) {
            return CheckResult.failure(BusinessSubsystemChkRsEnum.ADD_ILLEGAL_BUSINESS_SYSTEM_ID_NOT_EXIST,
                    businessSubsystemForm.getBusinessSystemId());
        }

        BusinessSubsystemDO businessSubsystemDO = businessSubsystemDAO.getByNameAndBusinessSystemId(
                businessSubsystemForm.getName(), businessSubsystemForm.getBusinessSystemId());
        if (businessSubsystemDO != null) {
            return CheckResult.failure(BusinessSubsystemChkRsEnum.ADD_ILLEGAL_NAME_EXIST,
                    businessSubsystemForm.getName());
        }

        return CheckResult.success();
    }

    /**
     * Non-logical check for update
     * 
     * @param businessSubsystemForm
     * @return
     */
    private CheckResult checkUpdateNonLogic(BusinessSubsystemForm businessSubsystemForm) {
        if (businessSubsystemForm.getName() != null && StringUtils.isEmpty(businessSubsystemForm.getName())) {
            return CheckResult.failure(BusinessSubsystemChkRsEnum.UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY);
        }
        return CheckResult.success();
    }

    /**
     * logical check for update
     * 
     * @param businessSubsystemId
     * @param businessSubsystemForm
     * @return
     */
    private CheckResult checkUpdateLogic(String businessSubsystemId, BusinessSubsystemForm businessSubsystemForm) {
        BusinessSubsystemDO businessSubsystemDO = businessSubsystemDAO.get(businessSubsystemId);

        if (businessSubsystemDO == null) {
            return CheckResult.failure(BusinessSubsystemChkRsEnum.ILLEGAL_ID_NOT_EXIST, businessSubsystemId);
        }

        if (StringUtils.isNotBlank(businessSubsystemForm.getName())
                && !businessSubsystemForm.getName().equals(businessSubsystemDO.getName())) {
            BusinessSubsystemDO newBusinessSubsystemDO = businessSubsystemDAO.getByNameAndBusinessSystemId(
                    businessSubsystemForm.getName(), businessSubsystemDO.getBusinessSystem().getId());
            if (newBusinessSubsystemDO != null) {
                return CheckResult.failure(BusinessSubsystemChkRsEnum.UPDATE_ILLEGAL_NAME_EXIST,
                        businessSubsystemForm.getName());
            }
        }

        return CheckResult.success();
    }
}
