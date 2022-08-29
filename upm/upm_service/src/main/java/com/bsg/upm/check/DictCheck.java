package com.bsg.upm.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.DictChkRsEnum;
import com.bsg.upm.domain.DictDO;
import com.bsg.upm.domain.DictTypeDO;
import com.bsg.upm.form.DictForm;

@Service
public class DictCheck extends BaseCheck {

    /**
     * check for updae
     * 
     * @param dictId
     * @param dictForm
     * @return
     */
    public CheckResult checkUpdate(String dictId, DictForm dictForm) {
        // Non-logical check for update
        CheckResult checkResult = checkUpdateNonLogic(dictForm);
        if (checkResult.getCode() != DictChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for update
        checkResult = checkUpdateLogic(dictId, dictForm);

        return checkResult;
    }

    /**
     * Non-logical check for update
     * 
     * @param archForm
     * @return
     */
    private CheckResult checkUpdateNonLogic(DictForm dictForm) {
        if (dictForm.getName() != null && StringUtils.isBlank(dictForm.getName())) {
            return CheckResult.failure(DictChkRsEnum.UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY);
        }

        return CheckResult.success();
    }

    /**
     * logical check for update
     * 
     * @param dictId
     * @param dictForm
     * @return
     */
    private CheckResult checkUpdateLogic(String dictId, DictForm dictForm) {
        DictDO dictDO = dictDAO.get(dictId);

        if (dictDO == null) {
            return CheckResult.failure(DictChkRsEnum.ILLEGAL_ID_NOT_EXIST, dictId);
        }

        if (StringUtils.isNotBlank(dictForm.getName()) && !dictForm.getName().equals(dictDO.getName())) {
            int nameCnt = dictDAO.countByNameAndDictTypeCode(dictForm.getName(), dictDO.getDictTypeCode());
            if (nameCnt != 0) {
                return CheckResult.failure(DictChkRsEnum.UPDATE_ILLEGAL_NAME_EXIST, dictForm.getName());
            }
        }

        return CheckResult.success();
    }
    
    /**
     * check for save
     * 
     * @param dictForm
     * @return
     */
    public CheckResult checkSave(DictForm dictForm) {
        // Non-logical check for update
        CheckResult checkResult = checkSaveNonLogic(dictForm);
        if (checkResult.getCode() != DictChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for update
        checkResult = checkSaveLogic(dictForm);

        return checkResult;
    }
    /**
     * Non-logical check for update
     * 
     * @param archForm
     * @return
     */
    private CheckResult checkSaveNonLogic(DictForm dictForm) {
        if (dictForm.getName() == null && StringUtils.isBlank(dictForm.getName())) {
            return CheckResult.failure(DictChkRsEnum.ADD_ILLEGAL_NAME_MUST_NOT_BE_EMPTY);
        }
        if (dictForm.getSequence() == null) {
            return CheckResult.failure(DictChkRsEnum.ADD_ILLEGAL_SEQUENCE_MUST_NOT_BE_EMPTY);
        }
        
        if(dictForm.getCode()== null || StringUtils.isBlank(dictForm.getCode())){
            return CheckResult.failure(DictChkRsEnum.ADD_ILLEGAL_CODE_MUST_NOT_BE_EMPTY);
        }
        if(dictForm.getDictTypeCode()==null && StringUtils.isBlank(dictForm.getDictTypeCode())){
            return CheckResult.failure(DictChkRsEnum.ADD_ILLEGAL_DICT_CODE_MUST_NOT_BE_EMPTY);
        }
        return CheckResult.success();
    }

    /**
     * logical check for update
     * 
     * @param dictId
     * @param dictForm
     * @return
     */
    private CheckResult checkSaveLogic(DictForm dictForm) {
        DictTypeDO dictTypeDO=dictTypeDAO.getByCode(dictForm.getDictTypeCode());

        if(dictTypeDO==null){
            return CheckResult.failure(DictChkRsEnum.ADD_ILLEGAL_DICT_TYPE_NOT_EXIST,dictForm.getDictTypeCode());
        }
        
        String dictName=dictDAO.getNameByCodeAndDictTypeCode(dictForm.getCode(), dictForm.getDictTypeCode());
        if(StringUtils.isNotBlank(dictName)){
            return CheckResult.failure(DictChkRsEnum.ADD_ILLEGAL_CODE_EXIST,dictForm.getCode());
        }
        
        int dictCount=dictDAO.countByNameAndDictTypeCode(dictForm.getName(), dictForm.getDictTypeCode());
        if(dictCount!=0){
            return CheckResult.failure(DictChkRsEnum.ADD_ILLEGAL_NAME_EXIST,dictForm.getName());
        }
        return CheckResult.success();
    }
}
