package com.bsg.upm.check;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.ClusterChkRsEnum;
import com.bsg.upm.constant.DictTypeConsts;
import com.bsg.upm.form.ClusterForm;

@Service
public class ClusterCheck extends BaseCheck {

    /**
     * check for save
     * 
     * @param clusterForm
     * @return
     */
    public CheckResult checkSave(ClusterForm clusterForm) {
        // Non-logical check for save
        CheckResult checkResult = checkSaveNonLogic(clusterForm);
        if (checkResult.getCode() != ClusterChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for save
        checkResult = checkSaveLogic(clusterForm);

        return checkResult;
    }

    /**
     * check for updae
     * 
     * @param clusterId
     * @param clusterForm
     * @return
     */
    public CheckResult checkUpdate(String clusterId, ClusterForm clusterForm) {
        // Non-logical check for update
        CheckResult checkResult = checkUpdateNonLogic(clusterForm);
        if (checkResult.getCode() != ClusterChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for update
        checkResult = checkUpdateLogic(clusterId, clusterForm);

        return checkResult;
    }

    /**
     *
     * @param clusterId
     * @param enbaled
     * @return
     */
    public CheckResult checkEnabled(String clusterId, boolean enable) {

        /*if (StringUtils.isBlank(clusterId)) {
            return CheckResult.failure(ClusterChkRsEnum.UPDATE_ILLEGAL_HA_TAG_MUST_NOT_BE_BLANK);
        }*/
        return CheckResult.success();
    }

    /**
     * check for remove
     * 
     * @param clusterId
     * @return
     */
    public CheckResult checkRemove(String clusterId) {

        return CheckResult.success();
    }

    /**
     * Non-logical check for save
     * 
     * @param clusterForm
     * @return
     */
    private CheckResult checkSaveNonLogic(ClusterForm clusterForm) {

        if (nullCheck(clusterForm.getSiteId())) {
            return CheckResult.failure(ClusterChkRsEnum.ADD_ILLEGAL_SITE_ID_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isBlank(clusterForm.getAreaCode())) {
            return CheckResult.failure(ClusterChkRsEnum.ADD_ILLEGAL_AREA_CODE_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isBlank(clusterForm.getName())) {
            return CheckResult.failure(ClusterChkRsEnum.ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK);
        }

        if (clusterForm.getImageTypes() == null || clusterForm.getImageTypes().size() == 0) {
            return CheckResult.failure(ClusterChkRsEnum.ADD_ILLEGAL_IMAGE_TYPES_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isBlank(clusterForm.getHaTag())) {
            return CheckResult.failure(ClusterChkRsEnum.ADD_ILLEGAL_HA_TAG_MUST_NOT_BE_BLANK);
        }
        return CheckResult.success();
    }

    /**
     * logical check for save
     * 
     * @param clusterForm
     * @return
     */
    private CheckResult checkSaveLogic(ClusterForm clusterForm) {
        String areaName = dictDAO.getNameByCodeAndDictTypeCode(clusterForm.getAreaCode(), DictTypeConsts.AREA);
        if (areaName == null) {
            return CheckResult.failure(ClusterChkRsEnum.ADD_ILLEGAL_AREA_CODE_NOT_EXIST, clusterForm.getAreaCode());
        }

        return CheckResult.success();
    }

    /**
     * Non-logical check for update
     *
     * @param clusterForm
     * @return
     */
    private CheckResult checkUpdateNonLogic(ClusterForm clusterForm) {

        if (StringUtils.isBlank(clusterForm.getAreaCode())) {
            return CheckResult.failure(ClusterChkRsEnum.UPDATE_ILLEGAL_AREA_CODE_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isEmpty(clusterForm.getName())) {
            return CheckResult.failure(ClusterChkRsEnum.UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY);
        }

        if (clusterForm.getImageTypes() == null || clusterForm.getImageTypes().size() == 0) {
            return CheckResult.failure(ClusterChkRsEnum.UPDATE_ILLEGAL_IMAGE_TYPES_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isBlank(clusterForm.getHaTag())) {
            return CheckResult.failure(ClusterChkRsEnum.UPDATE_ILLEGAL_HA_TAG_MUST_NOT_BE_BLANK);
        }
        return CheckResult.success();
    }

    /**
     * logical check for update
     * 
     * @param clusterId
     * @param clusterForm
     * @return
     */
    private CheckResult checkUpdateLogic(String clusterId, ClusterForm clusterForm) {

        String areaName = dictDAO.getNameByCodeAndDictTypeCode(clusterForm.getAreaCode(), DictTypeConsts.AREA);
        if (areaName == null) {
            return CheckResult.failure(ClusterChkRsEnum.UPDATE_ILLEGAL_AREA_CODE_NOT_EXIST, clusterForm.getAreaCode());
        }
        return CheckResult.success();
    }

}
