package com.bsg.upm.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.check.BusinessSystemCheck;
import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.resultenum.BusinessSystemChkRsEnum;
import com.bsg.upm.dao.BusinessSystemDAO;
import com.bsg.upm.domain.BusinessSystemDO;
import com.bsg.upm.domain.UserDO;
import com.bsg.upm.dto.BusinessSystemDTO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.BusinessSystemForm;
import com.bsg.upm.util.DateUtils;
import com.bsg.upm.util.PinyinUtil;

@Service
public class BusinessSystemService extends BaseService {

    @Autowired
    private BusinessSystemCheck businessSystemCheck;
    
    @Autowired
    private BusinessSystemDAO businessSystemDAO;

    @Transactional
    public Result list() throws ServiceException {
        try {
            List<BusinessSystemDTO> businessSystemDTOs = new ArrayList<>();

            String owner = getUsername();
            List<BusinessSystemDO> businessSystemDOs = businessSystemDAO.list(owner);
            if (businessSystemDOs.size() == 0) {
                return Result.success(businessSystemDTOs);
            }

            List<UserDO> userDOs = userDAO.list(null);
            for (BusinessSystemDO businessSystemDO : businessSystemDOs) {
                BusinessSystemDTO businessSystemDTO = getShowBaseDTO(businessSystemDO, userDOs);
                businessSystemDTOs.add(businessSystemDTO);
            }

            return Result.success(businessSystemDTOs);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Result get(String businessSystemId) throws ServiceException {
        BusinessSystemDTO businessSystemDTO = null;
        try {
            BusinessSystemDO businessSystemDO = businessSystemDAO.get(businessSystemId);

            if (businessSystemDO != null) {
                List<UserDO> userDOs = userDAO.list(null);
                businessSystemDTO = getShowBaseDTO(businessSystemDO, userDOs);
            }

            return Result.success(businessSystemDTO);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    public BusinessSystemDTO getBusinessSystem(String businessSystemId) throws ServiceException {
        BusinessSystemDTO businessSystemDTO = null;
        try {
            BusinessSystemDO businessSystemDO = businessSystemDAO.get(businessSystemId);

            if (businessSystemDO != null) {
                List<UserDO> userDOs = userDAO.list(null);
                businessSystemDTO = getShowBaseDTO(businessSystemDO, userDOs);
            }

            return businessSystemDTO;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result save(BusinessSystemForm businessSystemForm) throws ServiceException {
        try {
            CheckResult checkResult = businessSystemCheck.checkSave(businessSystemForm);
            if (checkResult.getCode() != BusinessSystemChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            BusinessSystemDO businessSystemDO = buildSaveBusinessSystemDO(businessSystemForm);
            businessSystemDAO.save(businessSystemDO);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result update(String businessSystemId, BusinessSystemForm businessSystemForm) throws ServiceException {
        try {
            CheckResult checkResult = businessSystemCheck.checkUpdate(businessSystemId, businessSystemForm);
            if (checkResult.getCode() != BusinessSystemChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            BusinessSystemDO newBusinessSystemDO = buildUpdateBusinessSystemDO(businessSystemId, businessSystemForm);
            businessSystemDAO.update(newBusinessSystemDO);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result remove(String businessSystemId) throws ServiceException {
        try {
            CheckResult checkResult = businessSystemCheck.checkRemove(businessSystemId);
            if (checkResult.getCode() != BusinessSystemChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            businessSystemDAO.remove(businessSystemId);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    private BusinessSystemDTO getShowBaseDTO(BusinessSystemDO businessSystemDO, List<UserDO> userDOs) {
        BusinessSystemDTO businessSystemDTO = new BusinessSystemDTO();
        businessSystemDTO.setId(businessSystemDO.getId());
        businessSystemDTO.setName(businessSystemDO.getName());
        businessSystemDTO.setOwner(businessSystemDO.getOwner());
        UserDO ownerUserDO = findUserDO(userDOs, businessSystemDO.getOwner());
        if (ownerUserDO != null) {
            businessSystemDTO.setOwnerName(ownerUserDO.getName());
        }

        businessSystemDTO.setDescription(businessSystemDO.getDescription());
        businessSystemDTO.setGmtCreate(DateUtils.dateTimeToString(businessSystemDO.getGmtCreate()));
        businessSystemDTO.setCreator(businessSystemDO.getCreator());

        UserDO creatorUserDO = findUserDO(userDOs, businessSystemDO.getCreator());
        if (creatorUserDO != null) {
            businessSystemDTO.setCreatorName(creatorUserDO.getName());
        }

        businessSystemDTO.setGmtModified(DateUtils.dateTimeToString(businessSystemDO.getGmtModified()));
        businessSystemDTO.setEditor(businessSystemDO.getEditor());

        UserDO editorUserDO = findUserDO(userDOs, businessSystemDO.getEditor());
        if (editorUserDO != null) {
            businessSystemDTO.setEditorName(editorUserDO.getName());
        }
        return businessSystemDTO;
    }

    private BusinessSystemDO buildSaveBusinessSystemDO(BusinessSystemForm businessSystemForm) {
        BusinessSystemDO businessSystemDO = new BusinessSystemDO();
        businessSystemDO.setId(PinyinUtil.getUuid());
        businessSystemDO.setName(businessSystemForm.getName());
        businessSystemDO.setOwner(getUsername());
        businessSystemDO.setDescription(StringUtils.trimToEmpty(businessSystemForm.getDescription()));
        businessSystemDO.setCreator(getUsername());
        businessSystemDO.setGmtCreate(systemDAO.getCurrentSqlDateTime());
        return businessSystemDO;
    }

    private BusinessSystemDO buildUpdateBusinessSystemDO(String businessSystemId, BusinessSystemForm businessSystemForm) {
        BusinessSystemDO businessSystemDO = businessSystemDAO.get(businessSystemId);
        if (businessSystemForm.getName() != null) {
            businessSystemDO.setName(businessSystemForm.getName());
        }
        if (businessSystemForm.getDescription() != null) {
            businessSystemDO.setDescription(businessSystemForm.getDescription());
        }
        businessSystemDO.setOwner(getUsername());
        businessSystemDO.setEditor(getUsername());
        businessSystemDO.setGmtModified(systemDAO.getCurrentSqlDateTime());

        return businessSystemDO;
    }

    public BusinessSystemDO getDomain(String businessSystemId) {
        return businessSystemDAO.get(businessSystemId);
    }
}
