package com.bsg.upm.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.DictCheck;
import com.bsg.upm.check.resultenum.DictChkRsEnum;
import com.bsg.upm.domain.DictDO;
import com.bsg.upm.dto.DictDTO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.DictForm;
import com.bsg.upm.util.DateUtils;
import com.bsg.upm.util.PinyinUtil;

@Service
public class DictService extends BaseService {

    @Autowired
    private DictCheck dictCheck;

    @Transactional
    public Result list(String dictTypeCode) throws ServiceException {
        try {
            List<DictDTO> dictDTOs = new ArrayList<>();

            List<DictDO> dictDOs = dictDAO.listByDictTypeCode(dictTypeCode);
            for (DictDO dictDO : dictDOs) {
                DictDTO dictDTO = new DictDTO();
                BeanUtils.copyProperties(dictDO, dictDTO);
                dictDTO.setGmtCreate(DateUtils.dateTimeToString(dictDO.getGmtCreate()));
                dictDTO.setGmtModified(DateUtils.dateTimeToString(dictDO.getGmtModified()));
                dictDTOs.add(dictDTO);
            }

            return Result.success(dictDTOs);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    public Result get(String id) throws ServiceException {
        try {
        	if(nullCheck(id)) {
        		return Result.failure(CheckResult.failure(DictChkRsEnum.UPDATE_ILLEGAL_ID_MUST_NOT_BE_EMPTY));
        	}
            DictDTO dictDTO = new DictDTO();

            DictDO dictDO = dictDAO.get(id);
            BeanUtils.copyProperties(dictDO, dictDTO);
            dictDTO.setGmtCreate(DateUtils.dateTimeToString(dictDO.getGmtCreate()));
            dictDTO.setGmtModified(DateUtils.dateTimeToString(dictDO.getGmtModified()));
            return Result.success(dictDTO);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    public Result update(String dictId, DictForm dictForm) throws ServiceException {
        try {
        	if(nullCheck(dictId)) {
        		return Result.failure(CheckResult.failure(DictChkRsEnum.UPDATE_ILLEGAL_ID_MUST_NOT_BE_EMPTY));
        	}
            CheckResult checkResult = dictCheck.checkUpdate(dictId, dictForm);
            if (checkResult.getCode() != DictChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            DictDO dictDO = dictDAO.get(dictId);
            if (StringUtils.isNotBlank(dictForm.getName())) {
                dictDO.setName(dictForm.getName());
            }
            if (dictForm.getSequence() != null) {
                dictDO.setSequence(dictForm.getSequence());
            }
            dictDO.setEditor(getUsername());
            dictDO.setGmtModified(systemDAO.getCurrentSqlDateTime());
            dictDAO.update(dictDO);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    public Result save(DictForm dictForm) throws ServiceException {
        try {
            
            CheckResult checkResult = dictCheck.checkSave(dictForm);
            if (checkResult.getCode() != DictChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            DictDO dictDO = new DictDO();
            dictDO.setId(PinyinUtil.getUuid());
            dictDO.setDictTypeCode(dictForm.getDictTypeCode());
            dictDO.setCode(dictForm.getCode());
            dictDO.setName(dictForm.getName());
            dictDO.setSequence(dictForm.getSequence());
            dictDO.setCreator(getUsername());
            dictDO.setGmtCreate(systemDAO.getCurrentSqlDateTime());
            dictDAO.save(dictDO);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    public DictDO getDomain(String id) {
        return dictDAO.get(id);
    }

}
