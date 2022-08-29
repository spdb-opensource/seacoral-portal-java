package com.bsg.upm.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.SanChkRsEnum;
import com.bsg.upm.domain.SanVendorDO;
import com.bsg.upm.domain.SanDO;
import com.bsg.upm.form.RemoteStorageForm;

@Service
public class SanCheck extends BaseCheck {

    /**
     * check for save
     * 
     * @param sanForm
     * @return
     */
    public CheckResult checkSave(RemoteStorageForm sanForm) {
        // Non-logical check for save
        CheckResult checkResult = checkSaveNonLogic(sanForm);
        if (checkResult.getCode() != SanChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for save
//        checkResult = checkSaveLogic(sanForm);

        return checkResult;
    }

    /**
     * check for remove
     * 
     * @param sanId
     * @return
     */
    public CheckResult checkRemove(String sanId) {
        SanDO sanDO = sanDAO.get(sanId);
        if (sanDO == null) {
            return CheckResult.failure(SanChkRsEnum.ILLEGAL_ID_NOT_EXIST, sanId);
        }
        return CheckResult.success();
    }

    /**
     * Non-logical check for save
     * 
     * @param sanForm
     * @return
     */
    private CheckResult checkSaveNonLogic(RemoteStorageForm sanForm) {
        if (sanForm.getSiteId() == null) {
            return CheckResult.failure(SanChkRsEnum.ADD_ILLEGAL_SITE_ID_MUST_NOT_BE_NULL);
        }

        /*if (sanForm.getVendorId() == null) {
            return CheckResult.failure(SanChkRsEnum.ADD_ILLEGAL_VENDOR_ID_MUST_NOT_BE_NULL);
        }*/

        if (StringUtils.isBlank(sanForm.getName())) {
            return CheckResult.failure(SanChkRsEnum.ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK);
        }

        /*if (StringUtils.isBlank(sanForm.getDomain())) {
            return CheckResult.failure(SanChkRsEnum.ADD_ILLEGAL_DOMAIN_MUST_NOT_BE_BLANK);
        }

        if (sanForm.getLunStart() == null) {
            return CheckResult.failure(SanChkRsEnum.ADD_ILLEGAL_LUN_START_MUST_NOT_BE_NULL);
        }

        if (sanForm.getLunEnd() == null) {
            return CheckResult.failure(SanChkRsEnum.ADD_ILLEGAL_LUN_END_MUST_NOT_BE_NULL);
        }

        if (sanForm.getHostLunStart() == null) {
            return CheckResult.failure(SanChkRsEnum.ADD_ILLEGAL_HOST_LUN_START_MUST_NOT_BE_NULL);
        }

        if (sanForm.getHostLunEnd() == null) {
            return CheckResult.failure(SanChkRsEnum.ADD_ILLEGAL_HOST_LUN_END_MUST_NOT_BE_NULL);
        }*/

        if (sanForm.getDescription() == null) {
            return CheckResult.failure(SanChkRsEnum.ADD_ILLEGAL_DESCRIPTION_MUST_NOT_BE_NULL);
        }

        return CheckResult.success();
    }

    /**
     * logical check for save
     * 
     * @param sanForm
     * @return
     */
    private CheckResult checkSaveLogic(RemoteStorageForm sanForm) {
        

        return CheckResult.success();
    }

}
