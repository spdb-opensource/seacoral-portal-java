package com.bsg.upm.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.DatabaseChkRsEnum;
import com.bsg.upm.check.resultenum.ServerGroupUserChkRsEnum;
import com.bsg.upm.check.resultenum.ServParameterCfgChkRsEnum;
import com.bsg.upm.form.DatabaseForm;
import com.bsg.upm.form.KeysetForm;
import com.bsg.upm.form.ServerGroupUserForm;

/**
 * 服务组用户处理检查
 * 
 * @author swn
 *
 */
@Service
public class ServerGroupCfgsCheck extends BaseCheck {

	/**
	 * 检查服务参数编辑的参数
	 * 
	 * @param serverGroupId
	 * @param serverGroupForm
	 * @return
	 */
	public CheckResult checkSave(KeysetForm keysetForm) {
		if (StringUtils.isBlank(keysetForm.getKey())) {
			return CheckResult.failure(ServParameterCfgChkRsEnum.ILLEGAL_SERV_GROUP_PARAM_EDIT_KEY_NOT_BE_BLANK);
		}
		
		if (StringUtils.isBlank(keysetForm.getValue())) {
			return CheckResult.failure(ServParameterCfgChkRsEnum.ILLEGAL_SERV_GROUP_PARAM_EDIT_VALUE_NOT_BE_BLANK);
		}

		return CheckResult.success();
	}

}
