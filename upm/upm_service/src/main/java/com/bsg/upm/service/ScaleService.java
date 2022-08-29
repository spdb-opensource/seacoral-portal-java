package com.bsg.upm.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.ScaleCheck;
import com.bsg.upm.check.resultenum.RoleChkRsEnum;
import com.bsg.upm.check.resultenum.ScaleChkRsEnum;
import com.bsg.upm.constant.DictTypeConsts;
import com.bsg.upm.domain.ScaleDO;
import com.bsg.upm.dto.BaseDTO.InfoDTO;
import com.bsg.upm.dto.BaseDTO.TypeDTO;
import com.bsg.upm.dto.ScaleDTO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.ScaleForm;
import com.bsg.upm.util.DateUtils;
import com.bsg.upm.util.PinyinUtil;

@Service
public class ScaleService extends BaseService {

    @Autowired
    private ScaleCheck scaleCheck;

    @Transactional
    public Result list(String type, Boolean enabled) throws ServiceException {
        try {
            List<ScaleDTO> scaleDTOs = new ArrayList<>();

            List<ScaleDO> scaleDOs = scaleDAO.list(type, enabled);
            for (ScaleDO scaleDO : scaleDOs) {
                ScaleDTO scaleDTO = getShowBaseDTO(scaleDO);
                scaleDTOs.add(scaleDTO);
            }

            return Result.success(scaleDTOs);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Result get(String scaleId) throws ServiceException {
        ScaleDTO scaleDTO = null;
        try {
        	if(nullCheck(scaleId)) {
        		return Result.failure(CheckResult.failure(ScaleChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            ScaleDO scaleDO = scaleDAO.get(scaleId);

            if (scaleDO != null) {
                scaleDTO = getShowBaseDTO(scaleDO);
            }

            return Result.success(scaleDTO);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result save(ScaleForm scaleForm) throws ServiceException {
        try {
            CheckResult checkResult = scaleCheck.checkSave(scaleForm);
            if (checkResult.getCode() != ScaleChkRsEnum.SUCCESS.getCode()) {
                logger.error("[{}] " + checkResult.getMsg(), checkResult.getCode());
                return Result.failure(checkResult.getCode(), checkResult.getMsg());
            }

            ScaleDO scaleDO = buildscaleForSave(scaleForm);
            scaleDAO.save(scaleDO);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result update(String scaleId, ScaleForm scaleForm) throws ServiceException {
        try {
        	if(nullCheck(scaleId)) {
        		return Result.failure(CheckResult.failure(ScaleChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = scaleCheck.checkUpdate(scaleId, scaleForm);
            if (checkResult.getCode() != ScaleChkRsEnum.SUCCESS.getCode()) {
                logger.error("[{}] " + checkResult.getMsg(), checkResult.getCode());
                return Result.failure(checkResult.getCode(), checkResult.getMsg());
            }

            ScaleDO newscaleDO = buildscaleForUpdate(scaleId, scaleForm);
            scaleDAO.update(newscaleDO);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result enabled(String scaleId, boolean enabled) throws ServiceException {
        try {
        	if(nullCheck(scaleId)) {
        		return Result.failure(CheckResult.failure(ScaleChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = scaleCheck.checkEnabled(scaleId, enabled);
            if (checkResult.getCode() != ScaleChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            ScaleDO scaleDO = scaleDAO.get(scaleId);
            scaleDO.setEnabled(enabled);
            scaleDO.setEditor(getUsername());
            scaleDO.setGmtModified(systemDAO.getCurrentSqlDateTime());
            scaleDAO.updateEnabled(scaleDO);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result remove(String scaleId) throws ServiceException {
        try {
        	if(nullCheck(scaleId)) {
        		return Result.failure(CheckResult.failure(ScaleChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = scaleCheck.checkRemove(scaleId);
            if (checkResult.getCode() != ScaleChkRsEnum.SUCCESS.getCode()) {
                logger.error("[{}] " + checkResult.getMsg(), checkResult.getCode());
                return Result.failure(checkResult.getCode(), checkResult.getMsg());
            }

            scaleDAO.remove(scaleId);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    private ScaleDTO getShowBaseDTO(ScaleDO scaleDO) {
        ScaleDTO scaleDTO = new ScaleDTO();
        scaleDTO.setId(scaleDO.getId());
        scaleDTO.setName(scaleDO.getName());
        scaleDTO.setCpuCnt(scaleDO.getCpuCnt());
        scaleDTO.setMemSize(scaleDO.getMemSize());
        scaleDTO.setEnabled(scaleDO.getEnabled());
        scaleDTO.setDescription(scaleDO.getDescription());
        scaleDTO.setSequence(scaleDO.getSequence());
        TypeDTO typeDTO=scaleDTO.new TypeDTO();
        typeDTO.setCode(scaleDO.getType());
        typeDTO.setDisplay(dictDAO.getNameByCodeAndDictTypeCode(scaleDO.getType(), DictTypeConsts.IMAGE_TYPE));
        scaleDTO.setType(typeDTO);
        InfoDTO createdDTO=scaleDTO.new InfoDTO();
        createdDTO.setTimestamp(DateUtils.dateTimeToString(scaleDO.getGmtCreate()));
        createdDTO.setUsername(scaleDO.getCreator());
        scaleDTO.setCreated(createdDTO);
        
        InfoDTO modifiedDTO=scaleDTO.new InfoDTO();
        modifiedDTO.setTimestamp(DateUtils.dateTimeToString(scaleDO.getGmtModified()));
        modifiedDTO.setUsername(scaleDO.getEditor());
        scaleDTO.setModified(modifiedDTO);
        
        return scaleDTO;
    }

    private ScaleDO buildscaleForSave(ScaleForm scaleForm) {
        ScaleDO scaleDO = new ScaleDO();
        scaleDO.setId(PinyinUtil.getUuid());
        scaleDO.setType(scaleForm.getType());
        scaleDO.setName(scaleForm.getName());
        scaleDO.setCpuCnt(scaleForm.getCpuCnt());
        scaleDO.setMemSize(scaleForm.getMemSize());
        scaleDO.setSequence(scaleForm.getSequence());
        scaleDO.setDescription(StringUtils.trimToEmpty(scaleForm.getDescription()));
        scaleDO.setEnabled(true);
        scaleDO.setCreator(getUsername());
        scaleDO.setGmtCreate(systemDAO.getCurrentSqlDateTime());
        return scaleDO;
    }

    private ScaleDO buildscaleForUpdate(String scaleId, ScaleForm scaleForm) {
        ScaleDO scaleDO = scaleDAO.get(scaleId);
        if (scaleForm.getName() != null) {
            scaleDO.setName(scaleForm.getName());
        }
        if (scaleForm.getSequence() != null) {
            scaleDO.setSequence(scaleForm.getSequence());
        }
        if (scaleForm.getDescription() != null) {
            scaleDO.setDescription(scaleForm.getDescription());
        }
        scaleDO.setEditor(getUsername());
        scaleDO.setGmtModified(systemDAO.getCurrentSqlDateTime());
        return scaleDO;
    }

    public ScaleDO getDomain(String scaleId) {
        return scaleDAO.get(scaleId);
    }

}
