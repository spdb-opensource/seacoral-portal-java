package com.bsg.upm.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.BackupStrategyChkRsEnum;
import com.bsg.upm.check.resultenum.DatabaseChkRsEnum;
import com.bsg.upm.form.DatabaseForm;
import com.bsg.upm.query.BackupStrategyAddParam;

/**
 * 服务组处理检查
 * 
 * @author HCK
 *
 */
@Service
public class BackupStrategyCheck extends BaseCheck {

	/**
	 * 检查扩容
	 * 
	 * @param serverGroupId
	 * @param serverGroupForm
	 * @return
	 */
	public CheckResult checkSave(BackupStrategyAddParam backupStrategyAddParam) {
		if (StringUtils.isBlank(backupStrategyAddParam.getName())) {
			return CheckResult.failure(BackupStrategyChkRsEnum.ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK);
		}
		if (StringUtils.isBlank(backupStrategyAddParam.getCronExpression())) {
			return CheckResult.failure(BackupStrategyChkRsEnum.ADD_ILLEGAL_CRONEXPRESSION_MUST_NOT_BE_BLANK);
		}
		if (backupStrategyAddParam.getRetention()==null) {
			return CheckResult.failure(BackupStrategyChkRsEnum.ADD_ILLEGAL_RETENTION_MUST_NOT_BE_BLANK);
		}
		if (StringUtils.isBlank(backupStrategyAddParam.getType())) {
			return CheckResult.failure(BackupStrategyChkRsEnum.ADD_ILLEGAL_TYPE_MUST_NOT_BE_BLANK);
		}
		return CheckResult.success();
	}

}
