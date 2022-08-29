package com.bsg.upm.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.SiteChkRsEnum;
import com.bsg.upm.constant.DictTypeConsts;
import com.bsg.upm.form.SiteForm;
import com.bsg.upm.util.IpV4Utils;

/**
 * 站点业务处理检查
 * 
 * @author HCK
 *
 */
@Service
public class SiteCheck extends BaseCheck {

    /**
     * check for save
     * 
     * @param siteForm
     * @return
     */
    public CheckResult checkSave(SiteForm siteForm) {
        // Non-logical check for save
        CheckResult checkResult = checkSaveNonLogic(siteForm);
        if (checkResult.getCode() != SiteChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for save
        checkResult = checkSaveLogic(siteForm);

        return checkResult;
    }

    /**
     * check for remove
     * 
     * @param siteId
     * @return
     */
    public CheckResult checkRemove(String siteId) {
        
        return CheckResult.success();
    }

    /**
     * Non-logical check for save
     * 
     * @param siteForm
     * @return
     */
    private CheckResult checkSaveNonLogic(SiteForm siteForm) {
        if (StringUtils.isBlank(siteForm.getName())) {
            return CheckResult.failure(SiteChkRsEnum.ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isBlank(siteForm.getRegion())) {
            return CheckResult.failure(SiteChkRsEnum.ADD_ILLEGAL_REGION_MUST_NOT_BE_BLANK);
        }
        
       /* if (StringUtils.isBlank(siteForm.getDomain())) {
            return CheckResult.failure(SiteChkRsEnum.ADD_ILLEGAL_DOMAIN_MUST_NOT_BE_BLANK);
        }*/
        
        if (StringUtils.isBlank(siteForm.getType())) {
            return CheckResult.failure(SiteChkRsEnum.ADD_ILLEGAL_TYPE_MUST_NOT_BE_BLANK);
        }
        
       /* if (siteForm.getPort() == null) {
            return CheckResult.failure(SiteChkRsEnum.ADD_ILLEGAL_PORT_MUST_NOT_BE_BLANK);
        }*/

        return CheckResult.success();
    }

    /**
     * logical check for save
     * 
     * @param siteForm
     * @return
     */
    private CheckResult checkSaveLogic(SiteForm siteForm) {
        

        return CheckResult.success();
    }

}
