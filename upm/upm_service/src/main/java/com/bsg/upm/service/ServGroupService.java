package com.bsg.upm.service;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.resultenum.UserChkRsEnum;
import com.bsg.upm.constant.DictConsts;
import com.bsg.upm.dao.ServGroupDAO;
import com.bsg.upm.domain.GroupUserDO;
import com.bsg.upm.domain.ServGroupDO;
import com.bsg.upm.domain.ServerGroupDO;
import com.bsg.upm.domain.UserDO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.UserForm;
import com.bsg.upm.util.PinyinUtil;

@Service
public class ServGroupService extends BaseService{
	
	@Autowired
	private ServGroupDAO servGroupDAO;

	@Transactional(rollbackFor = ServiceException.class)
    public Result save(ServGroupDO serverGroupDO) throws ServiceException {
    	try {
//	        CheckResult checkResult = userCheck.checkSave(userForm);
//	        if (checkResult.getCode() != UserChkRsEnum.SUCCESS.getCode()) {
//	            logger.error(loginfo(checkResult));
//	            return Result.failure(checkResult);
//	        }
	
//	        Date date = systemDAO.getCurrentSqlDateTime();
//	        String username = getUsername();
    		serverGroupDO.setId(PinyinUtil.getUuid());
	        servGroupDAO.save(serverGroupDO);
	        return Result.success();
    	} catch (Exception e) {
	        throw new ServiceException(e);
	    }
    }
	
	@Transactional(rollbackFor = ServiceException.class)
    public Result remove(String relateId) throws ServiceException {
    	try {
	        servGroupDAO.remove(relateId);
	        return Result.success();
    	} catch (Exception e) {
	        throw new ServiceException(e);
	    }
    }
}
