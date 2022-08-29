package com.bsg.upm.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.BusinessSystemChkRsEnum;
import com.bsg.upm.dao.BusinessSubsystemDAO;
import com.bsg.upm.dao.BusinessSystemDAO;
import com.bsg.upm.domain.BusinessSystemDO;
import com.bsg.upm.form.BusinessSystemForm;

@Service
public class BusinessSystemCheck extends BaseCheck {
	
	@Autowired
    private BusinessSystemDAO businessSystemDAO;
	
	@Autowired
    private BusinessSubsystemDAO businessSubsystemDAO;
	

    /**
     * check for save
     * 
     * @param businessSystemForm
     * @return
     */
    public CheckResult checkSave(BusinessSystemForm businessSystemForm) {
        // Non-logical check for save
        CheckResult checkResult = checkSaveNonLogic(businessSystemForm);
        if (checkResult.getCode() != BusinessSystemChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for save
        checkResult = checkSaveLogic(businessSystemForm);

        return checkResult;
    }

    /**
     * check for updae
     * 
     * @param businessSystemId
     * @param businessSystemForm
     * @return
     */
    public CheckResult checkUpdate(String businessSystemId, BusinessSystemForm businessSystemForm) {
        // Non-logical check for update
        CheckResult checkResult = checkUpdateNonLogic(businessSystemForm);
        if (checkResult.getCode() != BusinessSystemChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for update
        checkResult = checkUpdateLogic(businessSystemId, businessSystemForm);

        return checkResult;
    }

    /**
     * check for remove
     * 
     * @param businessSystemId
     * @return
     */
    public CheckResult checkRemove(String businessSystemId) {
        BusinessSystemDO businessSystemDO = businessSystemDAO.get(businessSystemId);
        if (businessSystemDO == null) {
            return CheckResult.failure(BusinessSystemChkRsEnum.ILLEGAL_ID_NOT_EXIST, businessSystemId);
        }

        int businessSubsystemCnt = businessSubsystemDAO.countByBusinessSystemId(businessSystemId);
        if (businessSubsystemCnt != 0) {
            return CheckResult.failure(BusinessSystemChkRsEnum.REMOVE_ILLEGAL_ASSOCIATED_WITH_BUSINESS_SUBSYSTEM,
                    businessSystemDO.getName());
        }
        return CheckResult.success();
    }

    /**
     * Non-logical check for save
     * 
     * @param businessSystemForm
     * @return
     */
    private CheckResult checkSaveNonLogic(BusinessSystemForm businessSystemForm) {
        if (StringUtils.isBlank(businessSystemForm.getName())) {
            return CheckResult.failure(BusinessSystemChkRsEnum.ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK);
        }

        return CheckResult.success();
    }

    /**
     * logical check for save
     * 
     * @param businessSystemForm
     * @return
     */
    private CheckResult checkSaveLogic(BusinessSystemForm businessSystemForm) {
        BusinessSystemDO businessSystemDO = businessSystemDAO.getByNameAndOwner(businessSystemForm.getName(),
                getUsername());
        if (businessSystemDO != null) {
            return CheckResult.failure(BusinessSystemChkRsEnum.ADD_ILLEGAL_NAME_EXIST, businessSystemForm.getName());
        }

        return CheckResult.success();
    }

    /**
     * Non-logical check for update
     * 
     * @param businessSystemForm
     * @return
     */
    private CheckResult checkUpdateNonLogic(BusinessSystemForm businessSystemForm) {
        if (businessSystemForm.getName() != null && StringUtils.isEmpty(businessSystemForm.getName())) {
            return CheckResult.failure(BusinessSystemChkRsEnum.UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY);
        }

        return CheckResult.success();
    }

    /**
     * logical check for update
     * 
     * @param businessSystemId
     * @param businessSystemForm
     * @return
     */
    private CheckResult checkUpdateLogic(String businessSystemId, BusinessSystemForm businessSystemForm) {
        BusinessSystemDO businessSystemDO = businessSystemDAO.get(businessSystemId);

        if (businessSystemDO == null) {
            return CheckResult.failure(BusinessSystemChkRsEnum.ILLEGAL_ID_NOT_EXIST, businessSystemId);
        }

        if (StringUtils.isNotBlank(businessSystemForm.getName())
                && !businessSystemForm.getName().equals(businessSystemDO.getName())) {
            BusinessSystemDO newBusinessSystemDO = businessSystemDAO.getByNameAndOwner(businessSystemForm.getName(),
                    getUsername());
            if (newBusinessSystemDO != null) {
                return CheckResult.failure(BusinessSystemChkRsEnum.UPDATE_ILLEGAL_NAME_EXIST,
                        businessSystemForm.getName());
            }
        }

        return CheckResult.success();
    }
}
