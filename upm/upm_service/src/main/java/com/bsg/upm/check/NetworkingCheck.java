package com.bsg.upm.check;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.NetworkingChkRsEnum;
import com.bsg.upm.constant.DictConsts;
import com.bsg.upm.form.NetworkingForm;
import com.bsg.upm.util.IpV4Utils;

@Service
public class NetworkingCheck extends BaseCheck {

    /**
     * check for save
     * 
     * @param networkingForm
     * @return
     */
    public CheckResult checkSave(NetworkingForm networkingForm) {
        // Non-logical check for save
        CheckResult checkResult = checkSaveNonLogic(networkingForm);
        if (checkResult.getCode() != NetworkingChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for save
//        checkResult = checkSaveLogic(networkingForm);

        return checkResult;
    }

    /**
     * check for updae
     * 
     * @param networkingId
     * @param networkingForm
     * @return
     */
    public CheckResult checkUpdate(String networkingId, NetworkingForm networkingForm) {
        // Non-logical check for update
        CheckResult checkResult = checkUpdateNonLogic(networkingForm);
        if (checkResult.getCode() != NetworkingChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for update
//        checkResult = checkUpdateLogic(networkingId, networkingForm);

        return checkResult;
    }

    /**
     * 
     * @param networkingId
     * @return
     */
    public CheckResult checkIn(long networkingId) {
        
        return CheckResult.success();
    }

    /**
     * 
     * @param networkingId
     * @return
     */
    public CheckResult checkOut(long networkingId) {
       
        return CheckResult.success();
    }

    /**
     * 
     * @param networkingId
     * @param enbaled
     * @return
     */
    public CheckResult checkEnabled(long networkingId, boolean enable) {
        
        return CheckResult.success();
    }

    /**
     * check for remove
     * 
     * @param networkingId
     * @return
     */
    public CheckResult checkRemove(long networkingId) {
        
        return CheckResult.success();
    }

    /**
     * Non-logical check for save
     * 
     * @param networkingForm
     * @return
     */
    private CheckResult checkSaveNonLogic(NetworkingForm networkingForm) {
        if (nullCheck(networkingForm.getSiteId())) {
            return CheckResult.failure(NetworkingChkRsEnum.ADD_ILLEGAL_SITE_ID_MUST_NOT_BE_NULL);
        }

        /*if (StringUtils.isBlank(networkingForm.getName())) {
            return CheckResult.failure(NetworkingChkRsEnum.ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK);
        }*/

        if (StringUtils.isBlank(networkingForm.getStartIp())) {
            return CheckResult.failure(NetworkingChkRsEnum.ADD_ILLEGAL_START_IP_MUST_NOT_BE_BLANK);
        }
        if (!IpV4Utils.ipV4Validate(networkingForm.getStartIp())) {
            return CheckResult.failure(NetworkingChkRsEnum.ADD_ILLEGAL_START_IP_FORMAT_ERROR,
                    networkingForm.getStartIp());
        }

        if (StringUtils.isBlank(networkingForm.getEndIp())) {
            return CheckResult.failure(NetworkingChkRsEnum.ADD_ILLEGAL_END_IP_MUST_NOT_BE_BLANK);
        }
        if (!IpV4Utils.ipV4Validate(networkingForm.getEndIp())) {
            return CheckResult.failure(NetworkingChkRsEnum.ADD_ILLEGAL_END_IP_FORMAT_ERROR, networkingForm.getEndIp());
        }
        if (!IpV4Utils.isSameSegment(networkingForm.getStartIp(), networkingForm.getEndIp())) {
            return CheckResult.failure(NetworkingChkRsEnum.ADD_ILLEGAL_END_IP_NOT_SAME_SEGMENT,
                    networkingForm.getStartIp(), networkingForm.getEndIp());
        }
        if (IpV4Utils.compareIpV4s(networkingForm.getStartIp(), networkingForm.getEndIp()) == 1) {
            return CheckResult.failure(NetworkingChkRsEnum.ADD_ILLEGAL_END_IP_MUST_NOT_BE_LESS_THAN_START_IP,
                    networkingForm.getEndIp(), networkingForm.getStartIp());
        }

        if (StringUtils.isBlank(networkingForm.getGateway())) {
            return CheckResult.failure(NetworkingChkRsEnum.ADD_ILLEGAL_GATEWAY_MUST_NOT_BE_BLANK);
        }
        if (!IpV4Utils.ipV4Validate(networkingForm.getGateway())) {
            return CheckResult.failure(NetworkingChkRsEnum.ADD_ILLEGAL_GATEWAY_FORMAT_ERROR,
                    networkingForm.getGateway());
        }

        if (networkingForm.getMask() == null) {
            return CheckResult.failure(NetworkingChkRsEnum.ADD_ILLEGAL_MASK_MUST_NOT_BE_NULL);
        }

        if (networkingForm.getVlanId() == null) {
            return CheckResult.failure(NetworkingChkRsEnum.ADD_ILLEGAL_VLAN_ID_MUST_NOT_BE_NULL);
        }

        return CheckResult.success();
    }

    /**
     * logical check for save
     * 
     * @param networkingForm
     * @return
     */
    private CheckResult checkSaveLogic(NetworkingForm networkingForm) {
        
        return CheckResult.success();
    }

    /**
     * Non-logical check for update
     * 
     * @param networkingForm
     * @return
     */
    private CheckResult checkUpdateNonLogic(NetworkingForm networkingForm) {
        /*if (networkingForm.getName() != null && StringUtils.isEmpty(networkingForm.getName())) {
            return CheckResult.failure(NetworkingChkRsEnum.UPDATE_ILLEGAL_NAME_MUST_NOT_BE_EMPTY);
        }*/

        if (networkingForm.getStartIp() != null) {
            if (StringUtils.isBlank(networkingForm.getStartIp())) {
                return CheckResult.failure(NetworkingChkRsEnum.UPDATE_ILLEGAL_START_IP_MUST_NOT_BE_EMPTY);
            }
            if (!IpV4Utils.ipV4Validate(networkingForm.getStartIp())) {
                return CheckResult.failure(NetworkingChkRsEnum.UPDATE_ILLEGAL_START_IP_FORMAT_ERROR,
                        networkingForm.getStartIp());
            }
        }

        if (networkingForm.getEndIp() != null) {
            if (StringUtils.isBlank(networkingForm.getEndIp())) {
                return CheckResult.failure(NetworkingChkRsEnum.UPDATE_ILLEGAL_END_IP_MUST_NOT_BE_EMPTY);
            }
            if (!IpV4Utils.ipV4Validate(networkingForm.getEndIp())) {
                return CheckResult.failure(NetworkingChkRsEnum.UPDATE_ILLEGAL_END_IP_FORMAT_ERROR,
                        networkingForm.getEndIp());
            }
        }

        if (networkingForm.getGateway() != null) {
            if (StringUtils.isBlank(networkingForm.getGateway())) {
                return CheckResult.failure(NetworkingChkRsEnum.UPDATE_ILLEGAL_GATEWAY_MUST_NOT_BE_EMPTY);
            }
            if (!IpV4Utils.ipV4Validate(networkingForm.getGateway())) {
                return CheckResult.failure(NetworkingChkRsEnum.UPDATE_ILLEGAL_GATEWAY_FORMAT_ERROR,
                        networkingForm.getGateway());
            }
        }

        return CheckResult.success();
    }

    /**
     * logical check for update
     * 
     * @param networkingId
     * @param networkingForm
     * @return
     */
    private CheckResult checkUpdateLogic(long networkingId, NetworkingForm networkingForm) {
        

        return CheckResult.success();
    }
}
