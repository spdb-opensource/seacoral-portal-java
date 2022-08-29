package com.bsg.upm.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.ServerGroupChkRsEnum;
import com.bsg.upm.check.resultenum.SiteChkRsEnum;
import com.bsg.upm.constant.DictTypeConsts;
import com.bsg.upm.form.ServerGroupForm;
import com.bsg.upm.form.SiteForm;
import com.bsg.upm.util.IpV4Utils;

/**
 * 服务组处理检查
 * 
 * @author HCK
 *
 */
@Service
public class ServerGroupCheck extends BaseCheck {
	
	/**
	 * 检查扩容
	 * @param serverGroupId
	 * @param serverGroupForm
	 * @return
	 */
	public CheckResult checkScale(String serverGroupId, ServerGroupForm serverGroupForm) {
		if (StringUtils.isBlank(serverGroupId)) {
            return CheckResult.failure(ServerGroupChkRsEnum.ILLEGAL_ID_NOT_EXIST);
        }

        if (StringUtils.isBlank(serverGroupForm.getType())) {
            return CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_TYPE_MUST_NOT_BE_BLANK);
        }

        return CheckResult.success();
	}
	
	public CheckResult checkImage(String serverGroupId, ServerGroupForm serverGroupForm) {
		if (StringUtils.isBlank(serverGroupId)) {
            return CheckResult.failure(ServerGroupChkRsEnum.ILLEGAL_ID_NOT_EXIST);
        }
		
		if (serverGroupForm.getImageId() == null) {
            return CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_IMAGEID_MUST_NOT_BE_BLANK);
        }

        if (serverGroupForm.getMajorVersion() == null) {
            return CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_MAJOR_VERSION_MUST_NOT_BE_BLANK);
        }
		
		if (serverGroupForm.getMinVersion() == null) {
            return CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_MIN_VERSION_MUST_NOT_BE_BLANK);
        }
		
		if (serverGroupForm.getPatchVersion() == null) {
            return CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_PATCH_VERSION_MUST_NOT_BE_BLANK);
        }
		
		if (serverGroupForm.getBuildVersion() == null) {
            return CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_BUILD_VERSION_MUST_NOT_BE_BLANK);
        }
		
		

        return CheckResult.success();
	}
	
	public CheckResult checkBackup(String serverGroupId, ServerGroupForm serverGroupForm) {
		if (StringUtils.isBlank(serverGroupId)) {
            return CheckResult.failure(ServerGroupChkRsEnum.ILLEGAL_ID_NOT_EXIST);
        }

//        if (StringUtils.isBlank(serverGroupForm.getStorageType())) {
//            return CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_STORAGE_REMOTE_ID_MUST_NOT_BE_BLANK);
//        }
		
		if (StringUtils.isBlank(serverGroupForm.getType())) {
            return CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_BACKUP_TYPE_MUST_NOT_BE_BLANK);
        }
		
		if (serverGroupForm.getRetention() == null) {
            return CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_RETENTION_MUST_NOT_BE_BLANK);
        }

        return CheckResult.success();
	}
	

}
