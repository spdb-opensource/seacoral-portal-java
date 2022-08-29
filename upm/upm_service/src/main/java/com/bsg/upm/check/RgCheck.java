package com.bsg.upm.check;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.RgChkRsEnum;
import com.bsg.upm.form.RemoteStoragePoolForm;

@Service
public class RgCheck extends BaseCheck {

    /**
     * check for save
     * 
     * @param sanId
     * @param rgForms
     * @return
     */
    public CheckResult checkSave(String sanId, RemoteStoragePoolForm rgForms) {
        // Non-logical check for save
        CheckResult checkResult = checkSaveNonLogic(sanId, rgForms);
        if (checkResult.getCode() != RgChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for save
//        checkResult = checkSaveLogic(sanId);

        return checkResult;
    }

    /**
     * 
     * @param sanId
     * @param enbaled
     * @return
     */
    public CheckResult checkEnabled(long sanId, boolean enabled) {
       
        return CheckResult.success();
    }

    /**
     * check for remove
     * 
     * @param sanId
     * @return
     */
    public CheckResult checkRemove(long sanId) {
       
        return CheckResult.success();
    }

    /**
     * Non-logical check for save
     * 
     * @param rgForms
     * @return
     */
    private CheckResult checkSaveNonLogic(String sanId, RemoteStoragePoolForm rgForms) {
        if (StringUtils.isBlank(sanId)) {
            return CheckResult.failure(RgChkRsEnum.ADD_ILLEGAL_SAN_ID_MUST_NOT_BE_NULL);
        }

            if (StringUtils.isBlank(rgForms.getCode())) {
                return CheckResult.failure(RgChkRsEnum.ADD_ILLEGAL_CODE_MUST_NOT_BE_BLANK);
            }
            
            /*if (StringUtils.isBlank(rgForm.getTypeId())) {
            	return CheckResult.failure(RgChkRsEnum.ADD_ILLEGAL_TYPE_MUST_NOT_BE_BLANK);
            }*/

        return CheckResult.success();
    }

    /**
     * logical check for save
     * 
     * @param sanId
     * @return
     */
    private CheckResult checkSaveLogic(Long sanId) {
       
        return CheckResult.success();
    }
}
