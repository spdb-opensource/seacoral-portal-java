package com.bsg.upm.check;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.HostChkRsEnum;
import com.bsg.upm.constant.DictConsts;
import com.bsg.upm.constant.TaskDictConsts;
import com.bsg.upm.form.HostForm;
import com.bsg.upm.param.HostMonitorRegisParam;
import com.bsg.upm.param.HostMonitorRegisParam1;
import com.bsg.upm.param.TaskDAOParam;
import com.bsg.upm.util.IpV4Utils;

@Service
public class HostCheck extends BaseCheck {

    /**
     * check for save
     * 
     * @param hostForm
     * @return
     */
    public CheckResult checkSave(HostForm hostForm) {
        // Non-logical check for save
        CheckResult checkResult = checkSaveNonLogic(hostForm);
        if (checkResult.getCode() != HostChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for save
        checkResult = checkSaveLogic(hostForm);

        return checkResult;
    }

    /**
     * check for updae
     * 
     * @param hostId
     * @param hostForm
     * @return
     */
    public CheckResult checkUpdate(String hostId, HostForm hostForm) {
        // Non-logical check for update
        CheckResult checkResult = checkUpdateNonLogic(hostForm);
        if (checkResult.getCode() != HostChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for update
        checkResult = checkUpdateLogic(hostId, hostForm);

        return checkResult;
    }


    /**
     * Non-logical check for save
     * 
     * @param hostForm
     * @return
     */
    private CheckResult checkSaveNonLogic(HostForm hostForm) {
        if (hostForm.getClusterId() == null) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_CLUSTER_ID_MUST_NOT_BE_NULL);
        }

        if (StringUtils.isBlank(hostForm.getIp())) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_IP_MUST_NOT_BE_BLANK);
        }
        if (!IpV4Utils.ipV4Validate(hostForm.getIp())) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_IP_MUST_NOT_BE_BLANK, hostForm.getIp());
        }
        if (hostForm.getSshPort() == null) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_PORT_MUST_NOT_BE_NULL);
        }

        if (StringUtils.isBlank(hostForm.getSshUsername())) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isBlank(hostForm.getSshPassword())) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_PASSWORD_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isBlank(hostForm.getRoom())) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_ROOM_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isBlank(hostForm.getSeat())) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_SEAT_MUST_NOT_BE_BLANK);
        }

        
        if (hostForm.getStorage().size()==0 && StringUtils.isBlank(hostForm.getStorageRemoteId())) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_HDD_SSD_SAN_NOT_ALL_BLANK);
        }

        if (hostForm.getMaxUnitCnt() == null) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_MAX_UNIT_CNT_MUST_NOT_BE_NULL);
        }
        if (hostForm.getMaxUnitCnt() < 1) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_MAX_UNIT_CNT_BELOW_LIMIT, 1);
        }

        
        if (hostForm.getMaxUsage().getCpu() == null) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_MAX_CPU_USAGE_MUST_NOT_BE_NULL);
        }
        if (hostForm.getMaxUsage().getCpu() < 1) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_MAX_CPU_USAGE_BELOW_LIMIT, 1);
        }
        if (hostForm.getMaxUsage().getCpu() > 100) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_MAX_CPU_USAGE_OVER_LIMIT, 100);
        }

        if (hostForm.getMaxUsage().getMem() == null) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_MAX_MEM_USAGE_MUST_NOT_BE_NULL);
        }
        if (hostForm.getMaxUsage().getMem() < 1) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_MAX_MEM_USAGE_BELOW_LIMIT, 1);
        }
        if (hostForm.getMaxUsage().getMem() > 100) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_MAX_MEM_USAGE_OVER_LIMIT, 100);
        }
        
        if (hostForm.getMaxUsage().getBandwidth() == null) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_MAX_BANDWIDTH_USAGE_MUST_NOT_BE_NULL);
        }
        if (hostForm.getMaxUsage().getBandwidth() < 1) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_MAX_BANDWIDTH_USAGE_BELOW_LIMIT, 1);
        }
        if (hostForm.getMaxUsage().getBandwidth() > 100) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_MAX_BANDWIDTH_USAGE_OVER_LIMIT, 100);
        }
        
        if (hostForm.getMaxUsage().getStorage() == null) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_MAX_STORAGE_USAGE_MUST_NOT_BE_NULL);
        }
        if (hostForm.getMaxUsage().getStorage() < 1) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_MAX_STORAGE_USAGE_BELOW_LIMIT, 1);
        }
        if (hostForm.getMaxUsage().getStorage() > 100) {
            return CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_MAX_STORAGE_USAGE_OVER_LIMIT, 100);
        }
        return CheckResult.success();
    }

    /**
     * logical check for save
     * 
     * @param hostForm
     * @return
     */
    private CheckResult checkSaveLogic(HostForm hostForm) {
        
        return CheckResult.success();
    }

    /**
     * Non-logical check for update
     * 
     * @param hostForm
     * @return
     */
    private CheckResult checkUpdateNonLogic(HostForm hostForm) {
        if (hostForm.getIp() != null) {
            if (StringUtils.isBlank(hostForm.getIp())) {
                return CheckResult.failure(HostChkRsEnum.UPDATE_ILLEGAL_IP_MUST_NOT_BE_EMPTY);
            }
            if (!IpV4Utils.ipV4Validate(hostForm.getIp())) {
                return CheckResult.failure(HostChkRsEnum.UPDATE_ILLEGAL_IP_FORMAT_ERROR, hostForm.getIp());
            }
        }

        if (hostForm.getSshUsername() != null && StringUtils.isEmpty(hostForm.getSshUsername())) {
            return CheckResult.failure(HostChkRsEnum.UPDATE_ILLEGAL_USERNAME_MUST_NOT_BE_EMPTY);
        }

        if (hostForm.getSshPassword() != null && StringUtils.isEmpty(hostForm.getSshPassword())) {
            return CheckResult.failure(HostChkRsEnum.UPDATE_ILLEGAL_PASSWORD_MUST_NOT_BE_EMPTY);
        }

        if (hostForm.getRoom() != null && StringUtils.isEmpty(hostForm.getRoom())) {
            return CheckResult.failure(HostChkRsEnum.UPDATE_ILLEGAL_ROOM_MUST_NOT_BE_EMPTY);
        }

        if (hostForm.getSeat() != null && StringUtils.isEmpty(hostForm.getSeat())) {
            return CheckResult.failure(HostChkRsEnum.UPDATE_ILLEGAL_SEAT_MUST_NOT_BE_EMPTY);
        }

        if (hostForm.getMaxUnitCnt() != null && hostForm.getMaxUnitCnt() < 1) {
            return CheckResult.failure(HostChkRsEnum.UPDATE_ILLEGAL_MAX_CONTAINER_CNT_BELOW_LIMIT, 1);
        }

        return CheckResult.success();
    }

    /**
     * logical check for update
     * 
     * @param hostId
     * @param hostForm
     * @return
     */
    private CheckResult checkUpdateLogic(String hostId, HostForm hostForm) {
        
        return CheckResult.success();
    }
    
    public CheckResult checkMonitorRegister(HostMonitorRegisParam hostMonitorRegisParam) {
        // Non-logical check for update
        CheckResult checkResult = checkMonitorRegisterNonLogic(hostMonitorRegisParam);
        if (checkResult.getCode() != HostChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }
        return checkResult;
    }
    
    public CheckResult checkMonitorRegister1(HostMonitorRegisParam1 hostMonitorRegisParam) {
        // Non-logical check for update
        CheckResult checkResult = checkMonitorRegisterNonLogic1(hostMonitorRegisParam);
        if (checkResult.getCode() != HostChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }
        return checkResult;
    }
    
    public CheckResult checkMonitorCancel(HostMonitorRegisParam hostMonitorRegisParam) {
        // Non-logical check for update
        CheckResult checkResult = checkMonitorCancelNonLogic(hostMonitorRegisParam);
        if (checkResult.getCode() != HostChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }
        return checkResult;
    }
    
    private CheckResult checkMonitorRegisterNonLogic(HostMonitorRegisParam hostMonitorRegisParam) {
        if (hostMonitorRegisParam.getIpAddr() != null) {
            if (StringUtils.isBlank(hostMonitorRegisParam.getIpAddr() )) {
                return CheckResult.failure(HostChkRsEnum.MONITORREGISTER_ILLEGAL_IP_MUST_NOT_BE_EMPTY);
            }
            if (!IpV4Utils.ipV4Validate(hostMonitorRegisParam.getIpAddr() )) {
                return CheckResult.failure(HostChkRsEnum.MONITORREGISTER_ILLEGAL_IP_FORMAT_ERROR, hostMonitorRegisParam.getIpAddr() );
            }
        }

        if (hostMonitorRegisParam.getName() != null && StringUtils.isEmpty(hostMonitorRegisParam.getName())) {
            return CheckResult.failure(HostChkRsEnum.MONITORREGISTER_ILLEGAL_NAME_MUST_NOT_BE_EMPTY);
        }

        if (hostMonitorRegisParam.getSshPort()==null) {
            return CheckResult.failure(HostChkRsEnum.MONITORREGISTER_ILLEGAL_SSHPORT_MUST_NOT_BE_EMPTY);
        }

        if (hostMonitorRegisParam.getOsUser() != null && StringUtils.isEmpty(hostMonitorRegisParam.getOsUser())) {
            return CheckResult.failure(HostChkRsEnum.MONITORREGISTER_ILLEGAL_OSUSER_MUST_NOT_BE_EMPTY);
        }

        if (hostMonitorRegisParam.getOsPwd() != null && StringUtils.isEmpty(hostMonitorRegisParam.getOsPwd())) {
            return CheckResult.failure(HostChkRsEnum.MONITORREGISTER_ILLEGAL_OSPWD_MUST_NOT_BE_EMPTY);
        }

        if (hostMonitorRegisParam.getCheckType() != null && StringUtils.isEmpty( hostMonitorRegisParam.getCheckType())) {
            return CheckResult.failure(HostChkRsEnum.MONITORREGISTER_ILLEGAL_CHECKTYPE_MUST_NOT_BE_EMPTY);
        }
        
        return CheckResult.success();
    }
    
    private CheckResult checkMonitorCancelNonLogic(HostMonitorRegisParam hostMonitorRegisParam) {
        if (hostMonitorRegisParam.getIpAddr() != null) {
            if (StringUtils.isBlank(hostMonitorRegisParam.getIpAddr() )) {
                return CheckResult.failure(HostChkRsEnum.MONITORREGISTER_ILLEGAL_IP_MUST_NOT_BE_EMPTY);
            }
            if (!IpV4Utils.ipV4Validate(hostMonitorRegisParam.getIpAddr() )) {
                return CheckResult.failure(HostChkRsEnum.MONITORREGISTER_ILLEGAL_IP_FORMAT_ERROR, hostMonitorRegisParam.getIpAddr() );
            }
        }


        if (hostMonitorRegisParam.getSshPort() == null ) {
            return CheckResult.failure(HostChkRsEnum.MONITORREGISTER_ILLEGAL_SSHPORT_MUST_NOT_BE_EMPTY);
        }

        if (hostMonitorRegisParam.getOsUser() != null && StringUtils.isEmpty(hostMonitorRegisParam.getOsUser())) {
            return CheckResult.failure(HostChkRsEnum.MONITORREGISTER_ILLEGAL_OSUSER_MUST_NOT_BE_EMPTY);
        }

        if (hostMonitorRegisParam.getOsPwd() != null && StringUtils.isEmpty(hostMonitorRegisParam.getOsPwd())) {
            return CheckResult.failure(HostChkRsEnum.MONITORREGISTER_ILLEGAL_OSPWD_MUST_NOT_BE_EMPTY);
        }

        return CheckResult.success();
    }
    
    private CheckResult checkMonitorRegisterNonLogic1(HostMonitorRegisParam1 hostMonitorRegisParam) {
        if (hostMonitorRegisParam.getIpAddr() != null) {
            if (StringUtils.isBlank(hostMonitorRegisParam.getIpAddr() )) {
                return CheckResult.failure(HostChkRsEnum.MONITORREGISTER_ILLEGAL_IP_MUST_NOT_BE_EMPTY);
            }
            if (!IpV4Utils.ipV4Validate(hostMonitorRegisParam.getIpAddr() )) {
                return CheckResult.failure(HostChkRsEnum.MONITORREGISTER_ILLEGAL_IP_FORMAT_ERROR, hostMonitorRegisParam.getIpAddr() );
            }
        }

        if (hostMonitorRegisParam.getName() != null && StringUtils.isEmpty(hostMonitorRegisParam.getName())) {
            return CheckResult.failure(HostChkRsEnum.MONITORREGISTER_ILLEGAL_NAME_MUST_NOT_BE_EMPTY);
        }

        
        /*if (hostMonitorRegisParam.getTag() != null && StringUtils.isEmpty( hostMonitorRegisParam.getTag())) {
            return CheckResult.failure(HostChkRsEnum.MONITORREGISTER_ILLEGAL_TAG_MUST_NOT_BE_EMPTY);
        }*/

        return CheckResult.success();
    }

}
