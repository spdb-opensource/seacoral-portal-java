package com.bsg.upm.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.ScaleChkRsEnum;
import com.bsg.upm.constant.DictTypeConsts;
import com.bsg.upm.domain.ScaleDO;
import com.bsg.upm.form.ScaleForm;

@Service
public class ScaleCheck extends BaseCheck {

    /**
     * check for save
     * 
     * @param scaleForm
     * @return
     */
    public CheckResult checkSave(ScaleForm scaleForm) {
        // Non-logical check for save
        CheckResult checkResult = checkSaveNonLogic(scaleForm);
        if (checkResult.getCode() != ScaleChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for save
        checkResult = checkSaveLogic(scaleForm);

        return checkResult;
    }

    /**
     * check for updae
     * 
     * @param scaleId
     * @param scaleForm
     * @return
     */
    public CheckResult checkUpdate(String scaleId, ScaleForm scaleForm) {
        // Non-logical check for update
        CheckResult checkResult = checkUpdateNonLogic(scaleForm);
        if (checkResult.getCode() != ScaleChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for update
        checkResult = checkUpdateLogic(scaleId, scaleForm);

        return checkResult;
    }

    /**
     * 
     * @param scaleId
     * @param enbaled
     * @return
     */
    public CheckResult checkEnabled(String scaleId, boolean enable) {
        ScaleDO scaleDO = scaleDAO.get(scaleId);
        if (scaleDO == null) {
            return CheckResult.failure(ScaleChkRsEnum.ILLEGAL_ID_NOT_EXIST, scaleId);
        }
        return CheckResult.success();
    }

    /**
     * check for remove
     * 
     * @param scaleId
     * @return
     */
    public CheckResult checkRemove(String scaleId) {
        ScaleDO scaleDO = scaleDAO.get(scaleId);
        if (scaleDO == null) {
            return CheckResult.failure(ScaleChkRsEnum.ILLEGAL_ID_NOT_EXIST, scaleId);
        }

        return CheckResult.success();
    }

    /**
     * Non-logical check for save
     * 
     * @param scaleForm
     * @return
     */
    private CheckResult checkSaveNonLogic(ScaleForm scaleForm) {
        if (StringUtils.isBlank(scaleForm.getType())) {
            return CheckResult.failure(ScaleChkRsEnum.ADD_ILLEGAL_TYPE_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isBlank(scaleForm.getName())) {
            return CheckResult.failure(ScaleChkRsEnum.ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK);
        }

        if (scaleForm.getCpuCnt() == null) {
            return CheckResult.failure(ScaleChkRsEnum.ADD_ILLEGAL_CPU_CNT_MUST_NOT_BE_NULL);
        }

        if (scaleForm.getMemSize() == null) {
            return CheckResult.failure(ScaleChkRsEnum.ADD_ILLEGAL_MEM_SIZE_MUST_NOT_BE_NULL);
        }

        if (scaleForm.getSequence() == null) {
            return CheckResult.failure(ScaleChkRsEnum.ADD_ILLEGAL_SEQUENCE_MUST_NOT_BE_NULL);
        }

        return CheckResult.success();
    }

    /**
     * logical check for save
     * 
     * @param scaleForm
     * @return
     */
    private CheckResult checkSaveLogic(ScaleForm scaleForm) {
        int servCount = dictDAO.countByCodeAndDictTypeCode(scaleForm.getType(),DictTypeConsts.IMAGE_TYPE);
        if (servCount != 1) {
            return CheckResult.failure(ScaleChkRsEnum.ADD_ILLEGAL_TYPE_NOT_EXIST, scaleForm.getType());
        }

        int nameCnt = scaleDAO.countByTypeAndName(scaleForm.getType(), scaleForm.getName());
        if (nameCnt != 0) {
            return CheckResult.failure(ScaleChkRsEnum.ADD_ILLEGAL_NAME_EXIST, scaleForm.getName());
        }

        ScaleDO scaleDO = scaleDAO.getByTypeAndCpuAndMem(scaleForm.getType(), scaleForm.getCpuCnt(),
                scaleForm.getMemSize());
        if (scaleDO != null) {
            return CheckResult.failure(ScaleChkRsEnum.ADD_ILLEGAL_CPU_MEM_EXIST);
        }

        return CheckResult.success();
    }

    /**
     * Non-logical check for update
     * 
     * @param scaleForm
     * @return
     */
    private CheckResult checkUpdateNonLogic(ScaleForm scaleForm) {
        if (scaleForm.getName() != null && StringUtils.isEmpty(scaleForm.getName())) {
            return CheckResult.failure(ScaleChkRsEnum.UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY);
        }

        return CheckResult.success();
    }

    /**
     * logical check for update
     * 
     * @param scaleId
     * @param scaleForm
     * @return
     */
    private CheckResult checkUpdateLogic(String scaleId, ScaleForm scaleForm) {
        ScaleDO scaleDO = scaleDAO.get(scaleId);

        if (scaleDO == null) {
            return CheckResult.failure(ScaleChkRsEnum.ILLEGAL_ID_NOT_EXIST, scaleId);
        }

        if (scaleDO.getEnabled()) {
            return CheckResult.failure(ScaleChkRsEnum.UPDATE_ILLEGAL_ENABLED, scaleDO.getName());
        }

        if (StringUtils.isNotBlank(scaleForm.getName()) && !scaleForm.getName().equals(scaleDO.getName())) {
            int nameCnt = scaleDAO.countByTypeAndName(scaleDO.getType(), scaleForm.getName());
            if (nameCnt != 0) {
                return CheckResult.failure(ScaleChkRsEnum.UPDATE_ILLEGAL_NAME_EXIST, scaleForm.getName());
            }
        }

        return CheckResult.success();
    }
}
