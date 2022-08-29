package com.bsg.upm.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.SiteConfigChkRsEnum;
import com.bsg.upm.form.SiteConfigForm;

@Service
public class SiteConfigCheck extends BaseCheck {

    /**
     * check for save
     * 
     * @param siteConfigForm
     * @return
     */
    public CheckResult checkSave(SiteConfigForm siteConfigForm) {
        // Non-logical check for save
        CheckResult checkResult = checkSaveNonLogic(siteConfigForm);
        if (checkResult.getCode() != SiteConfigChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for save
        checkResult = checkSaveLogic(siteConfigForm);

        return checkResult;
    }

    /**
     * check for updae
     * 
     * @param siteConfigForm
     * @return
     */
    public CheckResult checkUpdate(SiteConfigForm siteConfigForm) {
        // Non-logical check for update
        CheckResult checkResult = checkUpdateNonLogic(siteConfigForm);
        if (checkResult.getCode() != SiteConfigChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for update
        checkResult = checkUpdateLogic(siteConfigForm);

        return checkResult;
    }

    /**
     * check for remove
     * 
     * @param siteId
     * @return
     */
    public CheckResult checkRemove(long siteId) {
        
        return CheckResult.success();
    }

    /**
     * Non-logical check for save
     * 
     * @param siteConfigForm
     * @return
     */
    private CheckResult checkSaveNonLogic(SiteConfigForm siteConfigForm) {
        if (siteConfigForm.getAlarmOn() == null) {
            return CheckResult.failure(SiteConfigChkRsEnum.ADD_ILLEGAL_ALARM_ON_MUST_NOT_BE_NULL);
        }

        if (StringUtils.isBlank(siteConfigForm.getAlarmAddr())) {
            return CheckResult.failure(SiteConfigChkRsEnum.ADD_ILLEGAL_ALARM_ADDR_MUST_NOT_BE_BLANK);
        }

        if (siteConfigForm.getMailOn() == null) {
            return CheckResult.failure(SiteConfigChkRsEnum.ADD_ILLEGAL_MAIL_ON_MUST_NOT_BE_NULL);
        }

        if (StringUtils.isBlank(siteConfigForm.getMailHost())) {
            return CheckResult.failure(SiteConfigChkRsEnum.ADD_ILLEGAL_MAIL_HOST_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isBlank(siteConfigForm.getMailProtocol())) {
            return CheckResult.failure(SiteConfigChkRsEnum.ADD_ILLEGAL_MAIL_PROTOCOL_MUST_NOT_BE_BLANK);
        }

        if (siteConfigForm.getMailPort() == null) {
            return CheckResult.failure(SiteConfigChkRsEnum.ADD_ILLEGAL_MAIL_PORT_MUST_NOT_BE_NULL);
        }

        if (StringUtils.isBlank(siteConfigForm.getMailUsername())) {
            return CheckResult.failure(SiteConfigChkRsEnum.ADD_ILLEGAL_MAIL_USERNAME_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isBlank(siteConfigForm.getMailPassword())) {
            return CheckResult.failure(SiteConfigChkRsEnum.ADD_ILLEGAL_MAIL_PASSWORD_MUST_NOT_BE_BLANK);
        }

        return CheckResult.success();
    }

    /**
     * logical check for save
     * 
     * @param siteConfigForm
     * @return
     */
    private CheckResult checkSaveLogic(SiteConfigForm siteConfigForm) {
        
        return CheckResult.success();
    }

    /**
     * Non-logical check for update
     * 
     * @param siteConfigForm
     * @return
     */
    private CheckResult checkUpdateNonLogic(SiteConfigForm siteConfigForm) {
        if (siteConfigForm.getAlarmAddr() != null && StringUtils.isEmpty(siteConfigForm.getAlarmAddr())) {
            return CheckResult.failure(SiteConfigChkRsEnum.UPDATE_ILLEGAL_ALARM_ADDR_MUST_NOT_BE_EMPTY);
        }

        if (siteConfigForm.getMailHost() != null && StringUtils.isEmpty(siteConfigForm.getMailHost())) {
            return CheckResult.failure(SiteConfigChkRsEnum.UPDATE_ILLEGAL_MAIL_HOST_MUST_NOT_BE_EMPTY);
        }

        if (siteConfigForm.getMailProtocol() != null && StringUtils.isEmpty(siteConfigForm.getMailProtocol())) {
            return CheckResult.failure(SiteConfigChkRsEnum.UPDATE_ILLEGAL_MAIL_PROTOCOL_MUST_NOT_BE_EMPTY);
        }

        if (siteConfigForm.getMailUsername() != null && StringUtils.isEmpty(siteConfigForm.getMailUsername())) {
            return CheckResult.failure(SiteConfigChkRsEnum.UPDATE_ILLEGAL_MAIL_USERNAME_MUST_NOT_BE_EMPTY);
        }

        if (siteConfigForm.getMailPassword() != null && StringUtils.isEmpty(siteConfigForm.getMailPassword())) {
            return CheckResult.failure(SiteConfigChkRsEnum.UPDATE_ILLEGAL_MAIL_PASSWORD_MUST_NOT_BE_EMPTY);
        }

        return CheckResult.success();
    }

    /**
     * logical check for update
     * 
     * @param siteConfigForm
     * @return
     */
    private CheckResult checkUpdateLogic(SiteConfigForm siteConfigForm) {
        
        return CheckResult.success();
    }
}
