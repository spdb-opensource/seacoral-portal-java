package com.bsg.upm.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.DatabaseChkRsEnum;
import com.bsg.upm.check.resultenum.ServerGroupUserChkRsEnum;
import com.bsg.upm.form.DatabaseForm;
import com.bsg.upm.form.ServerGroupChangeUserPwdForm;
import com.bsg.upm.form.ServerGroupUpdateUserForm;
import com.bsg.upm.form.ServerGroupUserForm;

/**
 * 服务组用户处理检查
 * 
 * @author swn
 *
 */
@Service
public class ServerGroupUserCheck extends BaseCheck {

	/**
	 * 检查用户保存
	 * 
	 * @param serverGroupId
	 * @param serverGroupForm
	 * @return
	 */
	public CheckResult checkSave(ServerGroupUserForm serverGroupUserForm) {
		if (StringUtils.isBlank(serverGroupUserForm.getUsername())) {
			return CheckResult.failure(ServerGroupUserChkRsEnum.ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK);
		}
		
		if (StringUtils.isBlank(serverGroupUserForm.getPassword())) {
			return CheckResult.failure(ServerGroupUserChkRsEnum.ADD_ILLEGAL_PASSWORD_MUST_NOT_BE_BLANK);
		}
		
		if (StringUtils.isBlank(serverGroupUserForm.getWhiteIps())) {
			return CheckResult.failure(ServerGroupUserChkRsEnum.ADD_ILLEGAL_WHITEIP_MUST_NOT_BE_BLANK);
		}

		return CheckResult.success();
	}
	
	public CheckResult checkUpdateSave(ServerGroupUpdateUserForm serverGroupUserForm) {
		if (StringUtils.isBlank(serverGroupUserForm.getUsername())) {
			return CheckResult.failure(ServerGroupUserChkRsEnum.ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK);
		}
		
		
		if (StringUtils.isBlank(serverGroupUserForm.getWhiteIps())) {
			return CheckResult.failure(ServerGroupUserChkRsEnum.ADD_ILLEGAL_WHITEIP_MUST_NOT_BE_BLANK);
		}

		return CheckResult.success();
	}
	
	public CheckResult checkChangeUserPwd(ServerGroupChangeUserPwdForm serverGroupUserForm) {
		if (StringUtils.isBlank(serverGroupUserForm.getName())) {
			return CheckResult.failure(ServerGroupUserChkRsEnum.ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK);
		}
		
		
		if (StringUtils.isBlank(serverGroupUserForm.getPwd())) {
			return CheckResult.failure(ServerGroupUserChkRsEnum.ADD_ILLEGAL_PASSWORD_MUST_NOT_BE_BLANK);
		}

		return CheckResult.success();
	}

}
