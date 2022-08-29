package com.bsg.upm.check;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.ImageChkRsEnum;
import com.bsg.upm.constant.DictTypeConsts;
import com.bsg.upm.form.ImageForm;

@Service
public class ImageCheck extends BaseCheck {

    /**
     * check for save
     * 
     * @param imageForm
     * @return
     */
    public CheckResult checkSave(ImageForm imageForm) {
        // Non-logical check for save
        CheckResult checkResult = checkSaveNonLogic(imageForm);
        if (checkResult.getCode() != ImageChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for save
        checkResult = checkSaveLogic(imageForm);

        return checkResult;
    }

    /**
     * 
     * @param imageId
     * @return
     */
    public CheckResult checkRelease(long imageId) {

        return CheckResult.success();
    }

    /**
     * Non-logical check for save
     * 
     * @param imageForm
     * @return
     */
    private CheckResult checkSaveNonLogic(ImageForm imageForm) {

        if (StringUtils.isBlank(imageForm.getType())) {
            return CheckResult.failure(ImageChkRsEnum.ADD_ILLEGAL_TYPE_MUST_NOT_BE_BLANK);
        }

        if (imageForm.getVersion().getMajor() == null) {
            return CheckResult.failure(ImageChkRsEnum.ADD_ILLEGAL_MAJOR_VERSION_MUST_NOT_BE_NULL);
        }

        if (imageForm.getVersion().getMinor() == null) {
            return CheckResult.failure(ImageChkRsEnum.ADD_ILLEGAL_MINOR_VERSION_MUST_NOT_BE_NULL);
        }

        if (imageForm.getVersion().getPatch() == null) {
            return CheckResult.failure(ImageChkRsEnum.ADD_ILLEGAL_PATCH_VERSION_MUST_NOT_BE_NULL);
        }

        if (imageForm.getVersion().getBuild() == null) {
            return CheckResult.failure(ImageChkRsEnum.ADD_ILLEGAL_BUILD_VERSION_MUST_NOT_BE_NULL);
        }

        return CheckResult.success();
    }

    /**
     * logical check for save
     * 
     * @param imageForm
     * @return
     */
    private CheckResult checkSaveLogic(ImageForm imageForm) {

        int servCount = dictDAO.countByCodeAndDictTypeCode(imageForm.getType(),DictTypeConsts.IMAGE_TYPE);
        if (servCount != 1) {
            return CheckResult.failure(ImageChkRsEnum.ADD_ILLEGAL_TYPE_NOT_EXIST, imageForm.getType());
        }
        
        return CheckResult.success();
    }
}
