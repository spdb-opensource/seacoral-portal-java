package com.bsg.upm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.domain.DictTypeDO;
import com.bsg.upm.dto.DictTypeDTO;
import com.bsg.upm.exception.ServiceException;

@Service
public class DictTypeService extends BaseService {

    @Transactional
    public Result list() throws ServiceException {
        try {
            List<DictTypeDTO> dictTypeDTOs = new ArrayList<>();

            List<DictTypeDO> dictTypeDOs = dictTypeDAO.listSume();
            for (DictTypeDO dictTypeDO : dictTypeDOs) {
                DictTypeDTO dictTypeDTO = new DictTypeDTO();
                BeanUtils.copyProperties(dictTypeDO, dictTypeDTO);
                dictTypeDTOs.add(dictTypeDTO);
            }

            return Result.success(dictTypeDTOs);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public DictTypeDO getDomain(String code) {
        return dictTypeDAO.getByCode(code);
    }

}
