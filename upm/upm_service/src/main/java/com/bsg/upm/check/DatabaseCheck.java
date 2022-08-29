package com.bsg.upm.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.DatabaseChkRsEnum;
import com.bsg.upm.form.DatabaseForm;

/**
 * 服务组处理检查
 * 
 * @author HCK
 *
 */
@Service
public class DatabaseCheck extends BaseCheck {

	/**
	 * 检查扩容
	 * 
	 * @param serverGroupId
	 * @param serverGroupForm
	 * @return
	 */
	public CheckResult checkSave(DatabaseForm databaseForm) {
		if (StringUtils.isBlank(databaseForm.getName())) {
			return CheckResult.failure(DatabaseChkRsEnum.ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK);
		}
		
		if (StringUtils.isBlank(databaseForm.getCharacterSet())) {
			return CheckResult.failure(DatabaseChkRsEnum.ADD_ILLEGAL_CHARACTERSET_MUST_NOT_BE_BLANK);
		}

		return CheckResult.success();
	}

}
