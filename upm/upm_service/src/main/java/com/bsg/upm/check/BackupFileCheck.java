package com.bsg.upm.check;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.BackupFileChkRsEnum;

/**
 * 备份文件处理检查
 * 
 * @author swm
 *
 */
@Service
public class BackupFileCheck extends BaseCheck {


    /**
     * check for remove
     * 
     * @param siteId
     * @return
     */
    public CheckResult checkRemove(String backupFileId) {
        
    	if (nullCheck(backupFileId)) {
    		return CheckResult.failure(BackupFileChkRsEnum.REMOVE_ILLEGAL_ID_MUST_NOT_BE_BLANK);
    	}
    	
        return CheckResult.success();
    }
    
    public CheckResult checkRemoveStrategy(String backupStrategyId) {
        
    	if (nullCheck(backupStrategyId)) {
    		return CheckResult.failure(BackupFileChkRsEnum.REMOVE_ILLEGAL_StrategyID_MUST_NOT_BE_BLANK);
    	}
    	
        return CheckResult.success();
    }

    

}
