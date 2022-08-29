package com.bsg.upm.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.check.BusinessSubsystemCheck;
import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.resultenum.BusinessSubsystemChkRsEnum;
import com.bsg.upm.dao.BusinessSubsystemDAO;
import com.bsg.upm.domain.BusinessSubsystemDO;
import com.bsg.upm.domain.BusinessSystemDO;
import com.bsg.upm.domain.UserDO;
import com.bsg.upm.dto.BusinessSubsystemDTO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.BusinessSubsystemForm;
import com.bsg.upm.param.BusinessSubsystemDAOParam;
import com.bsg.upm.util.DateUtils;
import com.bsg.upm.util.PinyinUtil;

@Service
public class BusinessSubsystemService extends BaseService {

    @Autowired
    private BusinessSubsystemCheck businessSubsystemCheck;
    
    @Autowired
    private BusinessSubsystemDAO businessSubsystemDAO;

    @Transactional
    public Result list(String businessSystemId) throws ServiceException {
        try {
            List<BusinessSubsystemDTO> businessSubsystemDTOs = new ArrayList<>();

            String owner = getUsername();
            BusinessSubsystemDAOParam subsysDAOParam = new BusinessSubsystemDAOParam();
            subsysDAOParam.setBusinessSystemId(businessSystemId);
            subsysDAOParam.setOwner(owner);

            List<BusinessSubsystemDO> businessSubsystemDOs = businessSubsystemDAO.list(subsysDAOParam);
            if (businessSubsystemDOs.size() == 0) {
                return Result.success(businessSubsystemDTOs);
            }
            List<UserDO> userDOs = userDAO.list(null);
            for (BusinessSubsystemDO businessSubsystemDO : businessSubsystemDOs) {
                BusinessSubsystemDTO businessSubsystemDTO = getShowBaseDTO(businessSubsystemDO, userDOs);
                businessSubsystemDTOs.add(businessSubsystemDTO);
            }

            return Result.success(businessSubsystemDTOs);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Result get(String businessSubsystemId) throws ServiceException {
        BusinessSubsystemDTO businessSubsystemDTO = null;
        try {
            BusinessSubsystemDO businessSubsystemDO = businessSubsystemDAO.get(businessSubsystemId);

            if (businessSubsystemDO != null) {
                List<UserDO> userDOs = userDAO.list(null);
                businessSubsystemDTO = getShowBaseDTO(businessSubsystemDO, userDOs);
            }

            return Result.success(businessSubsystemDTO);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    public BusinessSubsystemDTO getBusinessSubsystem(String businessSubsystemId) throws ServiceException {
        BusinessSubsystemDTO businessSubsystemDTO = null;
        try {
            BusinessSubsystemDO businessSubsystemDO = businessSubsystemDAO.get(businessSubsystemId);

            if (businessSubsystemDO != null) {
                List<UserDO> userDOs = userDAO.list(null);
                businessSubsystemDTO = getShowBaseDTO(businessSubsystemDO, userDOs);
            }

            return businessSubsystemDTO;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result save(BusinessSubsystemForm businessSubsystemForm) throws ServiceException {
        try {
            CheckResult checkResult = businessSubsystemCheck.checkSave(businessSubsystemForm);
            if (checkResult.getCode() != BusinessSubsystemChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            BusinessSubsystemDO businessSubsystemDO = buildSaveBusinessSubsystemDO(businessSubsystemForm);
            businessSubsystemDAO.save(businessSubsystemDO);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result update(String businessSubsystemId, BusinessSubsystemForm businessSubsystemForm)
            throws ServiceException {
        try {
            CheckResult checkResult = businessSubsystemCheck.checkUpdate(businessSubsystemId, businessSubsystemForm);
            if (checkResult.getCode() != BusinessSubsystemChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            BusinessSubsystemDO newBusinessSubsystemDO = buildUpdateBusinessSubsystemDO(businessSubsystemId,
                    businessSubsystemForm);
            businessSubsystemDAO.update(newBusinessSubsystemDO);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result remove(String businessSubsystemId) throws ServiceException {
        try {
            CheckResult checkResult = businessSubsystemCheck.checkRemove(businessSubsystemId);
            if (checkResult.getCode() != BusinessSubsystemChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            businessSubsystemDAO.remove(businessSubsystemId);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    private BusinessSubsystemDTO getShowBaseDTO(BusinessSubsystemDO businessSubsystemDO, List<UserDO> userDOs) {
        BusinessSubsystemDTO businessSubsystemDTO = new BusinessSubsystemDTO();
        businessSubsystemDTO.setId(businessSubsystemDO.getId());
        businessSubsystemDTO.setName(businessSubsystemDO.getName());
        businessSubsystemDTO.setDescription(businessSubsystemDO.getDescription());
        businessSubsystemDTO.setGmtCreate(DateUtils.dateTimeToString(businessSubsystemDO.getGmtCreate()));
        businessSubsystemDTO.setCreator(businessSubsystemDO.getCreator());
        UserDO creatorUserDO = findUserDO(userDOs, businessSubsystemDO.getCreator());
        if (creatorUserDO != null) {
            businessSubsystemDTO.setCreatorName(creatorUserDO.getName());
        }

        businessSubsystemDTO.setGmtModified(DateUtils.dateTimeToString(businessSubsystemDO.getGmtModified()));
        businessSubsystemDTO.setEditor(businessSubsystemDO.getEditor());
        UserDO editorUserDO = findUserDO(userDOs, businessSubsystemDO.getEditor());
        if (editorUserDO != null) {
            businessSubsystemDTO.setEditorName(editorUserDO.getName());
        }

        BusinessSystemDO businessSystemDO = businessSubsystemDO.getBusinessSystem();
        businessSubsystemDTO.setBusinessSystemId(businessSystemDO.getId());
        businessSubsystemDTO.setBusinessSystemName(businessSystemDO.getName());
        businessSubsystemDTO.setOwner(businessSystemDO.getOwner());
        UserDO ownerUserDO = findUserDO(userDOs, businessSystemDO.getOwner());
        if (ownerUserDO != null) {
            businessSubsystemDTO.setOwnerName(ownerUserDO.getName());
        }
        return businessSubsystemDTO;
    }

    private BusinessSubsystemDO buildSaveBusinessSubsystemDO(BusinessSubsystemForm businessSubsystemForm) {
    	
        BusinessSubsystemDO businessSubsystemDO = new BusinessSubsystemDO();
        businessSubsystemDO.setId(PinyinUtil.getUuid());
        businessSubsystemDO.setName(businessSubsystemForm.getName());
        businessSubsystemDO.setDescription(StringUtils.trimToEmpty(businessSubsystemForm.getDescription()));
        businessSubsystemDO.setCreator(getUsername());
        businessSubsystemDO.setGmtCreate(systemDAO.getCurrentSqlDateTime());
        businessSubsystemDO.setBusinessSystemId(businessSubsystemForm.getBusinessSystemId());
        return businessSubsystemDO;
    }

    private BusinessSubsystemDO buildUpdateBusinessSubsystemDO(String businessSubsystemId,
            BusinessSubsystemForm businessSubsystemForm) {
        BusinessSubsystemDO businessSubsystemDO = businessSubsystemDAO.get(businessSubsystemId);
        if (businessSubsystemForm.getName() != null) {
            businessSubsystemDO.setName(businessSubsystemForm.getName());
        }
        if (businessSubsystemForm.getDescription() != null) {
            businessSubsystemDO.setDescription(businessSubsystemForm.getDescription());
        }
        businessSubsystemDO.setEditor(getUsername());
        businessSubsystemDO.setGmtModified(systemDAO.getCurrentSqlDateTime());

        return businessSubsystemDO;
    }

    public BusinessSubsystemDO getDomain(String businessSubsystemId) {
        return businessSubsystemDAO.get(businessSubsystemId);
    }
}
