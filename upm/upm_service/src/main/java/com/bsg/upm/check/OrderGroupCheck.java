package com.bsg.upm.check;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.bsg.upm.check.resultenum.NetworkingChkRsEnum;
import com.bsg.upm.check.resultenum.OrderGroupChkRsEnum;
import com.bsg.upm.constant.DictConsts;
import com.bsg.upm.domain.OrderGroupDO;
import com.bsg.upm.form.AuditForm;
import com.bsg.upm.form.OrderForm;
import com.bsg.upm.form.OrderGroupForm;

/**
 * 
 * @author ZhuXH
 * @date 2019年7月15日
 */
@Service
public class OrderGroupCheck extends BaseCheck {

    
    /**
    * @param orderGroupForm
    * @return
    * @return CheckResult
    */
    public CheckResult checkSave(OrderGroupForm orderGroupForm,int cnt){
     // Non-logical check for save
        CheckResult checkResult = checkSaveNonLogic(orderGroupForm,cnt);
        if (checkResult.getCode() != NetworkingChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        return checkResult;
    }
    
    /**
     * check for updae
     *
     * @param orderGroupId
     * @param orderGroupForm
     * @return
     */
    public CheckResult checkUpdate(String orderGroupId, OrderGroupForm orderGroupForm) {
        // Non-logical check for update
        CheckResult checkResult = checkUpdateNonLogic(orderGroupForm);
        if (checkResult.getCode() != OrderGroupChkRsEnum.SUCCESS.getCode()) {
            return checkResult;
        }

        // logical check for update
        checkResult = checkUpdateLogic(orderGroupId, orderGroupForm);

        return checkResult;
    }
    
    /**
     * check for remove
     *
     * @param orderGroupId
     * @return
     */
    public CheckResult checkRemove(String orderGroupId) {
        OrderGroupDO orderGroupDO = orderGroupDAO.getBase(orderGroupId);
        if (orderGroupDO == null) {
            return CheckResult.failure(OrderGroupChkRsEnum.ILLEGAL_ID_NOT_EXIST, orderGroupId);
        }
        if (orderGroupDO.getStatus().equals(DictConsts.ORDER_STATUS_EXECUTING)
                || orderGroupDO.getStatus().equals(DictConsts.ORDER_STATUS_FAILURE)) {
            return CheckResult.failure(OrderGroupChkRsEnum.REMOVE_ILLEGAL_EXECUTEING_OR_FAILURE);
        }
        return CheckResult.success();
    }
    
    /**
     * check for execute
     *
     * @param orderGroupId
     * @param orderGroupForm
     * @return
     */
    public CheckResult checkExecute(String orderGroupId) {
        OrderGroupDO orderGroupDO = orderGroupDAO.get(orderGroupId);
        if (orderGroupDO == null) {
            return CheckResult.failure(OrderGroupChkRsEnum.ILLEGAL_ID_NOT_EXIST, orderGroupId);
        }

        if (!orderGroupDO.getStatus().equals(DictConsts.ORDER_STATUS_APPROVED)
                && !orderGroupDO.getStatus().equals(DictConsts.ORDER_STATUS_FAILURE)) {
            return CheckResult.failure(OrderGroupChkRsEnum.EXECUTE_ILLEGAL_BECAUSE_NOT_APPROVED_OR_FAILURE,
                    orderGroupId);
        }

        return CheckResult.success();
    }
    
    private CheckResult checkSaveNonLogic(OrderGroupForm orderGroupForm,int cnt) {
        if (cnt > 99) {
            return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_CNT_OVER_LIMIT, 99);
        }
        if(StringUtils.isBlank(orderGroupForm.getSiteId())){
            return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_SITE_NAME_MUST_NOT_BE_BLANK);
        }
        if (StringUtils.isBlank(orderGroupForm.getBusinessSystemName())) {
            return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_BUSINESS_SYSTEM_NAME_MUST_NOT_BE_BLANK);
        }

        if (StringUtils.isBlank(orderGroupForm.getBusinessSubsystemName())) {
            return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_BUSINESS_SUBSYSTEM_NAME_MUST_NOT_BE_BLANK);
        }
        if(StringUtils.isBlank(orderGroupForm.getAreaCode())){
            return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_AREA_CODE_MUST_NOT_BE_BLANK);
        }
        if(StringUtils.isBlank(orderGroupForm.getType())){
            return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_TYPE_MUST_NOT_BE_BLANK);
        }
        
        if (StringUtils.isBlank(orderGroupForm.getName())) {
            return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK);
        }
        List<OrderGroupDO> orderGroupDO=orderGroupDAO.checkName(orderGroupForm.getName());
        if (orderGroupDO.size()!=0) {
            return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_NAME_EXIST);
        }

        if (!orderGroupForm.getName().matches("[a-zA-Z0-9]+")) {
            return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_NAME_FORMAT_ERROR);
        }
        
        List<OrderForm> orderForms=orderGroupForm.getOrders();
        for(OrderForm orderForm:orderForms){
            if(StringUtils.isBlank(orderForm.getType())){
                return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_ORDERS_FORMAT_ERROR);
            }
            if (orderForm.getVersion().getMajor()==null || orderForm.getVersion().getMinor()==null || orderForm.getVersion().getPatch()==null || orderForm.getVersion().getBuild()==null) {
                return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_IMAGE_VERSION_NOT_BE_NULL);
            }
            /*if (orderForm.getArchId()==null) {
                return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_ARCH_ID_MUST_NOT_BE_BLANK);
            }*/
            if (orderForm.getArch() == null) {
            	return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_ARCH_MUST_NOT_BE_BLANK);
            }
            if (StringUtils.isBlank(orderForm.getArch().getMode())) {
            	return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_ARCH_MODEL_MUST_NOT_BE_BLANK);
            }
            if (orderForm.getArch().getReplicas() == null) {
            	return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_ARCH_UNIT_MUST_NOT_BE_BLANK);
            }
            if(orderForm.getCpuCnt()==null){
                return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_CPU_CNT_MUST_NOT_BE_NULL);
            }
            if(orderForm.getMemSize()==null){
                return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_MEM_SIZE_MUST_NOT_BE_NULL);
            }
            if(orderForm.getPort()==null){
                return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_PORT_MUST_NOT_BE_NULL);
            }
            if(orderForm.getDataDirSize()==null){
                return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_DATA_DIR_SIZE_MUST_NOT_BE_NULL);
            }
            if (StringUtils.isBlank(orderForm.getDataDirType())) {
                return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_DATA_DIR_PERFORMANCE_MUST_NOT_BE_BLANK);
            }
            if (orderForm.getLogDirSize()==null) {
                return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_LOG_DIR_SIZE_MUST_NOT_BE_NULL);
            }
            if (StringUtils.isBlank(orderForm.getLogDirType())) {
                return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_LOG_DIR_TYPE_MUST_NOT_BE_BLANK);
            }
            if(orderForm.getNetworkBandwidth()==null){
                return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_NETWORK_BANDWIDTH_MUST_NOT_BE_NULL);
            }
            if (orderForm.getHostHA()==null || orderForm.getClusterHA()==null || orderForm.getNetworkHA()==null || orderForm.getStorageHA()==null) {
                return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_HA_MUST_NOT_BE_BLANK);
            }
            if (orderForm.getCnt()==null) {
                return CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_CNT_MUST_NOT_BE_BLANK);
            }
        }
        return CheckResult.success();
    }

    /**
     * check for audit
     *
     * @param orderGroupId
     * @param auditForm
     * @return
     */
    public CheckResult checkAudit(String orderGroupId, AuditForm auditForm) {
        OrderGroupDO orderGroupDO = orderGroupDAO.getBase(orderGroupId);
        if (orderGroupDO == null) {
            return CheckResult.failure(OrderGroupChkRsEnum.ILLEGAL_ID_NOT_EXIST, orderGroupId);
        }
        if (!orderGroupDO.getStatus().equals(DictConsts.ORDER_STATUS_UNAPPROVED)) {
            return CheckResult.failure(OrderGroupChkRsEnum.AUDIT_ILLEGAL_APPROVED_REPEATEDLY,
                    orderGroupDO.getName());
        }

        if (StringUtils.isBlank(auditForm.getStatus())) {
            return CheckResult.failure(OrderGroupChkRsEnum.AUDIT_ILLEGAL_STATUS_FORMAT_ERROR);
        }
        if (!auditForm.getStatus().equals(DictConsts.ORDER_STATUS_APPROVED)
                && !auditForm.getStatus().equals(DictConsts.ORDER_STATUS_UNAPPROVE)) {
            return CheckResult.failure(OrderGroupChkRsEnum.AUDIT_ILLEGAL_STATUS_FORMAT_ERROR,
                    auditForm.getStatus());
        }
        if (auditForm.getStatus().equals(DictConsts.ORDER_STATUS_UNAPPROVE)) {
            if (StringUtils.isBlank(auditForm.getMsg())) {
                return CheckResult.failure(OrderGroupChkRsEnum.AUDIT_ILLEGAL_MSG_MUST_NOT_BE_NULL);
            }
        }
        return CheckResult.success();
    }
    
    /**
     * Non-logical check for update
     *
     * @param orderGroupForm
     * @return
     */
    private CheckResult checkUpdateNonLogic(OrderGroupForm orderGroupForm) {
        List<OrderForm> orderForms = orderGroupForm.getOrders();
        if (orderForms != null) {
            if(StringUtils.isBlank(orderGroupForm.getAreaCode())){
                return CheckResult.failure(OrderGroupChkRsEnum.UPDATE_ILLEGAL_AREA_NOT_BE_EMPTY);
            }
            if(StringUtils.isBlank(orderGroupForm.getSiteId())){
                return CheckResult.failure(OrderGroupChkRsEnum.UPDATE_ILLEGAL_SITE_NOT_BE_EMPTY);
            }
            for (OrderForm orderForm : orderForms) {
                if (StringUtils.isBlank(orderForm.getType())) {
                    return CheckResult.failure(OrderGroupChkRsEnum.UPDATE_ILLEGAL_ORDERS_FORMAT_ERROR);
                }

                if (orderForm.getVersion().getMajor() == null || orderForm.getVersion().getMinor() == null
                        || orderForm.getVersion().getPatch() == null || orderForm.getVersion().getBuild() == null) {
                    return CheckResult.failure(OrderGroupChkRsEnum.UPDATE_ILLEGAL_IMAGE_VERSION_FORMAT_ERROR,
                            orderForm.getType());
                }

                if (orderForm.getCnt() != null) {
                    if (orderForm.getCnt() <= 0) {
                        return CheckResult.failure(OrderGroupChkRsEnum.UPDATE_ILLEGAL_CNT_BELOW_LIMIT,
                                orderForm.getType());
                    }
                }
            }
        }
        return CheckResult.success();
    }

    /**
     * logical check for update
     *
     * @param orderGroupId
     * @param orderGroupForm
     * @return
     */
    private CheckResult checkUpdateLogic(String orderGroupId, OrderGroupForm orderGroupForm) {
        OrderGroupDO orderGroupDO = orderGroupDAO.get(orderGroupId);
        if (orderGroupDO == null) {
            return CheckResult.failure(OrderGroupChkRsEnum.ILLEGAL_ID_NOT_EXIST, orderGroupId);
        }

        if (orderGroupDO.getStatus().equals(DictConsts.ORDER_STATUS_EXECUTING)
                || orderGroupDO.getStatus().equals(DictConsts.ORDER_STATUS_SUCCESS)
                || orderGroupDO.getStatus().equals(DictConsts.ORDER_STATUS_UNAPPROVE)) {
            return CheckResult.failure(OrderGroupChkRsEnum.UPDATE_ILLEGAL_FORBIDDEN);
        }

        return CheckResult.success();
    }
}
