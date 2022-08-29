package com.bsg.upm.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.ServerGroupChkRsEnum;
import com.bsg.upm.check.resultenum.ServerGroupUnitChkRsEnum;
import com.bsg.upm.form.ServerGroupUnitForm;
import com.bsg.upm.param.ServeUnitRegisParam;
import com.bsg.upm.query.UnitRebulidParam;

/**
 * 服务组单元处理检查
 * 
 * @author HCK
 *
 */
@Service
public class ServerGroupUnitCheck extends BaseCheck {
	
	public CheckResult checkBackup(String serverGroupId, String unitId, ServerGroupUnitForm serverGroupUnitForm) {
		if (StringUtils.isBlank(serverGroupId)) {
            return CheckResult.failure(ServerGroupUnitChkRsEnum.ADD_ILLEGAL_SERVER_GROUP_ID_MUST_NOT_BE_BLANK);
        }
		
		if (StringUtils.isBlank(unitId)) {
            return CheckResult.failure(ServerGroupUnitChkRsEnum.ILLEGAL_ID_NOT_EXIST);
        }

        if (StringUtils.isBlank(serverGroupUnitForm.getStorageType())) {
            return CheckResult.failure(ServerGroupUnitChkRsEnum.ADD_ILLEGAL_STORAGE_TYPE_MUST_NOT_BE_BLANK);
        }
		
        if (StringUtils.isBlank(serverGroupUnitForm.getType())) {
            return CheckResult.failure(ServerGroupUnitChkRsEnum.ADD_ILLEGAL_TYPE_MUST_NOT_BE_BLANK);
        }
        
        if (serverGroupUnitForm.getRetention() == null) {
            return CheckResult.failure(ServerGroupUnitChkRsEnum.ADD_ILLEGAL_RETENTION_MUST_NOT_BE_BLANK);
        }

        return CheckResult.success();
	}
	
	public CheckResult checkRebuild(UnitRebulidParam unitRebulidParam) {
		if (StringUtils.isBlank(unitRebulidParam.getAppId())) {
            return CheckResult.failure(ServerGroupUnitChkRsEnum.ADD_ILLEGAL_SERVER_GROUP_ID_MUST_NOT_BE_BLANK);
        }
		
		if (StringUtils.isBlank(unitRebulidParam.getUnitId())) {
            return CheckResult.failure(ServerGroupUnitChkRsEnum.ILLEGAL_ID_NOT_EXIST);
        }

        return CheckResult.success();
	}
	
	public CheckResult checkUnitMonitor(ServeUnitRegisParam serveUnitRegisParam) {
		if (StringUtils.isBlank(serveUnitRegisParam.getName())) {
            return CheckResult.failure(ServerGroupUnitChkRsEnum.MONITORREGISTER_ILLEGAL_NAME_MUST_NOT_BE_EMPTY);
        }
		
		if (StringUtils.isBlank(serveUnitRegisParam.getCategory())) {
            return CheckResult.failure(ServerGroupUnitChkRsEnum.MONITORREGISTER_ILLEGAL_CATEGRRY_MUST_NOT_BE_EMPTY);
        }
		
		if(serveUnitRegisParam.getContainer()==null) {
			return CheckResult.failure(ServerGroupUnitChkRsEnum.MONITORREGISTER_ILLEGAL_CONTAINER_MUST_NOT_BE_EMPTY);
		}else {
			if(StringUtils.isBlank(serveUnitRegisParam.getContainer().getName())) {
				return CheckResult.failure(ServerGroupUnitChkRsEnum.MONITORREGISTER_ILLEGAL_CONTAINER_NAME_MUST_NOT_BE_EMPTY);
			}
			
			if(StringUtils.isBlank(serveUnitRegisParam.getContainer().getHostName())) {
				return CheckResult.failure(ServerGroupUnitChkRsEnum.MONITORREGISTER_ILLEGAL_HOST_NAME_MUST_NOT_BE_EMPTY);
			}
			
			if(StringUtils.isBlank(serveUnitRegisParam.getContainer().getCategory())) {
				return CheckResult.failure(ServerGroupUnitChkRsEnum.MONITORREGISTER_ILLEGAL_CONTAINER_CATEGRRY_MUST_NOT_BE_EMPTY);
			}
			
		}
		
		if (StringUtils.isBlank(serveUnitRegisParam.getTag())) {
            return CheckResult.failure(ServerGroupUnitChkRsEnum.MONITORREGISTER_ILLEGAL_TAG_MUST_NOT_BE_EMPTY);
        }

        return CheckResult.success();
	}
	

}
