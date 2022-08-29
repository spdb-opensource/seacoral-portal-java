package com.bsg.upm.service;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.constant.DataCategoryConsts;
import com.bsg.upm.dao.OperateLogDAO;
import com.bsg.upm.domain.OperateLogDO;
import com.bsg.upm.dto.OperateLogDTO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.param.OperateLogDAOParam;
import com.bsg.upm.query.OperateLogParam;
import com.bsg.upm.util.DateUtils;
import com.bsg.upm.util.PinyinUtil;

@Service
public class OperateLogService extends BaseService {

    @Autowired
    private OperateLogDAO operateLogDAO;

    public Result list(OperateLogParam operateLogParam) throws ServiceException {
        try {
            List<OperateLogDTO> operateLogDTOs = new ArrayList<>();

            OperateLogDAOParam operateLogDAOParam = new OperateLogDAOParam();
            BeanUtils.copyProperties(operateLogParam, operateLogDAOParam);
            if (operateLogParam.getStart() != null) {
                operateLogDAOParam.setStartDate(new Date(operateLogParam.getStart()));
            }
            if (operateLogParam.getEnd() != null) {
                operateLogDAOParam.setEndDate(new Date(operateLogParam.getEnd()));
            }
            if(operateLogParam.getObjType()!=null) {
            	operateLogDAOParam.setObjType(URLDecoder.decode(operateLogParam.getObjType(), "UTF-8"));
            }

            List<OperateLogDO> operateLogDOs = operateLogDAO.list(operateLogDAOParam);

            String username = operateLogParam.getOwner();
            if (StringUtils.isBlank(username)) {
                username = getUsername();
            }
            List<String> usernames = listVisiableUserData(username, DataCategoryConsts.OPERATE_LOG);
            if (usernames == null) {
                return Result.success(operateLogDTOs);
            }

            List<OperateLogDO> visibleOperateLogDOs = new ArrayList<>();
            for (OperateLogDO operateLogDO : operateLogDOs) {
                if (usernames.contains(operateLogDO.getCreator())) {
                    visibleOperateLogDOs.add(operateLogDO);
                }
            }

            for (OperateLogDO operateLogDO : visibleOperateLogDOs) {
                OperateLogDTO operateLogDTO = new OperateLogDTO();
                BeanUtils.copyProperties(operateLogDO, operateLogDTO);
                operateLogDTO.setGmtCreate(DateUtils.dateTimeToString(operateLogDO.getGmtCreate()));
                
                operateLogDTOs.add(operateLogDTO);
            }

            return Result.success(operateLogDTOs);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 
     * @param operateLogDO
     * @return
     * @throws ServiceException
     */
    @Transactional(rollbackFor = ServiceException.class)
    public Result save(OperateLogDO operateLogDO) throws ServiceException {
        try {
        	operateLogDO.setId(PinyinUtil.getUuid());
            operateLogDAO.save(operateLogDO);
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
