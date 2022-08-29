package com.bsg.upm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.RgCheck;
import com.bsg.upm.check.resultenum.ClusterChkRsEnum;
import com.bsg.upm.check.resultenum.RgChkRsEnum;
import com.bsg.upm.check.resultenum.SanChkRsEnum;
import com.bsg.upm.constant.DictTypeConsts;
import com.bsg.upm.dto.BaseDTO.InfoDTO;
import com.bsg.upm.dto.BaseDTO.TaskDTO;
import com.bsg.upm.dto.BaseDTO.TypeDTO;
import com.bsg.upm.dto.RemoteStoragePoolDTO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.RemoteStoragePoolForm;
import com.bsg.upm.mgm.api.MgmApi;
import com.bsg.upm.mgm.body.MgmRemoteStoragePoolBody;
import com.bsg.upm.mgm.model.MgmModel.Info;
import com.bsg.upm.mgm.model.MgmModel.Task;
import com.bsg.upm.mgm.model.MgmRemoteStorage;
import com.bsg.upm.mgm.model.MgmRemoteStoragePool;
import com.bsg.upm.mgm.query.MgmRemoteStoragePoolQuery;
import com.bsg.upm.mgm.query.MgmRemoteStorageQuery;

@Service
public class RemoteStoragePoolService extends BaseService {

    @Autowired
    private RgCheck rgCheck;

    @Autowired
    private MgmApi mgmApi;

    @Transactional
    public Result list(String sanId) throws ServiceException {
        try {
        	if(nullCheck(sanId)) {
        		return Result.failure(CheckResult.failure(SanChkRsEnum.ADD_ILLEGAL_San_ID_MUST_NOT_BE_NULL));
        	}
            List<RemoteStoragePoolDTO> remoteStoragePoolDTOs = new ArrayList<>();
            
            MgmRemoteStoragePoolQuery mgmRemoteStoragePoolQuery = new MgmRemoteStoragePoolQuery();
            List<MgmRemoteStoragePool> mgmRemoteStoragePools = mgmApi.listRemoteStoragePool(sanId, mgmRemoteStoragePoolQuery);
            for (MgmRemoteStoragePool mgmRemoteStoragePool : mgmRemoteStoragePools) {
            	RemoteStoragePoolDTO remoteStoragePoolDTO = getBaseDTO(mgmRemoteStoragePool);
            	remoteStoragePoolDTOs.add(remoteStoragePoolDTO);
            }
            return Result.success(remoteStoragePoolDTOs);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    @Transactional(rollbackFor = ServiceException.class)
    public Result save(String storageId, RemoteStoragePoolForm remoteStoragePoolForms) throws ServiceException {
        try {
        	if(nullCheck(storageId)) {
        		return Result.failure(CheckResult.failure(SanChkRsEnum.ADD_ILLEGAL_San_ID_MUST_NOT_BE_NULL));
        	}
            CheckResult checkResult = rgCheck.checkSave(storageId, remoteStoragePoolForms);
            if (checkResult.getCode() != RgChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            
//            for (RemoteStoragePoolForm remoteStoragePoolForm : remoteStoragePoolForms) {
            	MgmRemoteStoragePoolBody mgmRemoteStoragePoolBody = buildSaveMgmRemoteStoragePoolBody(storageId, remoteStoragePoolForms);

                mgmApi.saveRemoteStoragePool(String.valueOf(storageId), mgmRemoteStoragePoolBody);
//			}
            
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result enabled(String storageId, String poolId, boolean enabled) throws ServiceException {
        try {
        	if(nullCheck(storageId)) {
        		return Result.failure(CheckResult.failure(SanChkRsEnum.ADD_ILLEGAL_San_ID_MUST_NOT_BE_NULL));
        	}
        	MgmRemoteStoragePoolBody mgmRemoteStoragePoolBody = new MgmRemoteStoragePoolBody();

        	mgmRemoteStoragePoolBody.setEnabled(enabled);
        	mgmRemoteStoragePoolBody.setModifiedUser(getUsername());

            mgmApi.updateRemoteStoragePool(storageId, poolId, mgmRemoteStoragePoolBody);
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result remove(String storageId, String poolId) throws ServiceException {
        try {
        	if(nullCheck(storageId)) {
        		return Result.failure(CheckResult.failure(SanChkRsEnum.ADD_ILLEGAL_San_ID_MUST_NOT_BE_NULL));
        	}
        	MgmRemoteStorageQuery mgmRemoteStorageQuery = new MgmRemoteStorageQuery();
        	mgmRemoteStorageQuery.setId(storageId);
			
        	List<MgmRemoteStorage> mgmRemoteStorages = mgmApi.listRemoteStorage(mgmRemoteStorageQuery);
        	MgmRemoteStorage mgmRemoteStorage;
            if (mgmRemoteStorages.size() != 0) {
            	mgmRemoteStorage = mgmRemoteStorages.get(0);
            	if(mgmRemoteStorage.getEnabled()) {
            		return Result.failure(CheckResult.failure(RgChkRsEnum.REMOVE_ILLEGAL_ASSOCIATED_WITH_ENABLED));
            	}else {
            		mgmApi.removeRemoteStoragePool(storageId, poolId);
                    return Result.success();
            	}
            }else {
            	return Result.failure(CheckResult.failure(RgChkRsEnum.REMOVE_ILLEGAL_SAN_ID_NOT_EXIST));
            }
        	
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    

    private RemoteStoragePoolDTO getBaseDTO(MgmRemoteStoragePool mgmRemoteStoragePool) {
    	if (mgmRemoteStoragePool == null) {
    		return null;
    	}
    	
    	RemoteStoragePoolDTO remoteStoragePoolDTO = new RemoteStoragePoolDTO();
    	remoteStoragePoolDTO.setId(mgmRemoteStoragePool.getId());
    	remoteStoragePoolDTO.setCode(mgmRemoteStoragePool.getName());
    	
    	/*if (mgmRemoteStoragePool.getType() != null) {
    		TypeDTO typeDTO = remoteStoragePoolDTO.new TypeDTO();
    		typeDTO.setCode(mgmRemoteStoragePool.getType());
    		typeDTO.setDisplay(dictDAO.getNameByCodeAndDictTypeCode(mgmRemoteStoragePool.getType(), DictTypeConsts.REMOTE_STORAGE_POOL_TYPE));
    		
    		remoteStoragePoolDTO.setType(typeDTO);
    	}*/
    	
    	if (mgmRemoteStoragePool.getPerformance() != null) {
    		TypeDTO performanceTypeDTO = remoteStoragePoolDTO.new TypeDTO();
    		performanceTypeDTO.setCode(mgmRemoteStoragePool.getPerformance());
    		performanceTypeDTO.setDisplay(dictDAO.getNameByCodeAndDictTypeCode(mgmRemoteStoragePool.getPerformance(), DictTypeConsts.PERFORMANCE));
    		
    		remoteStoragePoolDTO.setPerformance(performanceTypeDTO);
    	}
    	
    	if (mgmRemoteStoragePool.getCapacity() != null) {
    		remoteStoragePoolDTO.setTotalSize((long)mgmRemoteStoragePool.getCapacity());
    	}
    	
    	if (mgmRemoteStoragePool.getUsed() != null) {
    		remoteStoragePoolDTO.setUsedSize((long)mgmRemoteStoragePool.getUsed());
    	}
    	remoteStoragePoolDTO.setEnabled(mgmRemoteStoragePool.getEnabled());
    	remoteStoragePoolDTO.setDescription(mgmRemoteStoragePool.getDesc());
    	
        Info createdInfo = mgmRemoteStoragePool.getCreated();
        if (createdInfo != null) {
        	InfoDTO createdInfoDTO = remoteStoragePoolDTO.new InfoDTO();
        	createdInfoDTO.setUsername(createdInfo.getUser());
        	createdInfoDTO.setTimestamp(createdInfo.getTimestamp());
        	remoteStoragePoolDTO.setCreated(createdInfoDTO);
        }
        
        Info modifiedInfo = mgmRemoteStoragePool.getModified();
        if (modifiedInfo != null) {
        	InfoDTO modifiedInfoDTO = remoteStoragePoolDTO.new InfoDTO();
        	modifiedInfoDTO.setUsername(modifiedInfo.getUser());
        	modifiedInfoDTO.setTimestamp(modifiedInfo.getTimestamp());
        	remoteStoragePoolDTO.setModified(modifiedInfoDTO);
        }
        Task task = mgmRemoteStoragePool.getTask();
        if (task != null) {
        	TaskDTO taskDTO = remoteStoragePoolDTO.new TaskDTO();
        	taskDTO.setId(task.getId());
        	
        	if (task.getAction() != null) {
        		TypeDTO actionDTO = remoteStoragePoolDTO.new TypeDTO();
        		actionDTO.setCode(task.getAction());
        		actionDTO.setDisplay(dictDAO.getNameByCodeAndDictTypeCode(task.getAction(), DictTypeConsts.TASK_ACTION));
        		taskDTO.setAction(actionDTO);
        	}
        	
        	if (task.getStatus() != null) {
        		TypeDTO statusDTO = remoteStoragePoolDTO.new TypeDTO();
        		statusDTO.setCode(task.getStatus());
        		statusDTO.setDisplay(dictDAO.getNameByCodeAndDictTypeCode(task.getStatus(), DictTypeConsts.TASK_STATUS));
        		taskDTO.setStatus(statusDTO);
        	}
        	
        	remoteStoragePoolDTO.setTask(taskDTO);
        }
        
        return remoteStoragePoolDTO;
    }

    private MgmRemoteStoragePoolBody buildSaveMgmRemoteStoragePoolBody(String storageId, RemoteStoragePoolForm remoteStoragePoolForm) {
    	MgmRemoteStoragePoolBody mgmRemoteStoragePoolBody = new MgmRemoteStoragePoolBody();
        
    	mgmRemoteStoragePoolBody.setName(remoteStoragePoolForm.getCode());
    	mgmRemoteStoragePoolBody.setNativeId(remoteStoragePoolForm.getCode());
    	mgmRemoteStoragePoolBody.setEnabled(remoteStoragePoolForm.getEnabled());
    	mgmRemoteStoragePoolBody.setPerformance(remoteStoragePoolForm.getPerformance());
    	mgmRemoteStoragePoolBody.setDesc(remoteStoragePoolForm.getDescription());
    	
    	mgmRemoteStoragePoolBody.setCreatedUser(getUsername());
        return mgmRemoteStoragePoolBody;
    }

}
