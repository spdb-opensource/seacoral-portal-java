package com.bsg.upm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.domain.PrivilegeDO;
import com.bsg.upm.dto.PrivilegeDTO;
import com.bsg.upm.exception.ServiceException;

@Service
public class PrivilegeService extends BaseService {

    @Transactional
    public Result list(Boolean enbaled, Boolean gloabl) throws ServiceException {
        try {
            List<PrivilegeDTO> privilegeDTOs = new ArrayList<>();

            List<PrivilegeDO> privilegeDOs = privilegeDAO.list(enbaled, gloabl);
            for (PrivilegeDO privilegeDO : privilegeDOs) {
                PrivilegeDTO privilegeDTO = new PrivilegeDTO();
                BeanUtils.copyProperties(privilegeDO, privilegeDTO);
                privilegeDTOs.add(privilegeDTO);
            }

            return Result.success(privilegeDTOs);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

}
