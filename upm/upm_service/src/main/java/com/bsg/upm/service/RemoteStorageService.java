package com.bsg.upm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.SanCheck;
import com.bsg.upm.check.resultenum.OrderGroupChkRsEnum;
import com.bsg.upm.check.resultenum.SanChkRsEnum;
import com.bsg.upm.constant.DictTypeConsts;
import com.bsg.upm.domain.DictDO;
import com.bsg.upm.domain.RemoteStorageVendorDO;
import com.bsg.upm.dto.BaseDTO.InfoDTO;
import com.bsg.upm.dto.BaseDTO.TaskDTO;
import com.bsg.upm.dto.BaseDTO.TypeDTO;
import com.bsg.upm.dto.RemoteStorageDTO;
import com.bsg.upm.dto.RemoteStorageDTO.AuthDTO;
import com.bsg.upm.dto.RemoteStorageDTO.SiteDTO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.RemoteStorageForm;
import com.bsg.upm.mgm.api.MgmApi;
import com.bsg.upm.mgm.body.MgmRemoteStorageBody;
import com.bsg.upm.mgm.body.MgmRemoteStorageBody.Auth;
import com.bsg.upm.mgm.model.MgmModel.Info;
import com.bsg.upm.mgm.model.MgmModel.Task;
import com.bsg.upm.mgm.model.MgmRemoteStorage;
import com.bsg.upm.mgm.query.MgmNetworkingQuery;
import com.bsg.upm.mgm.query.MgmRemoteStorageQuery;
import com.bsg.upm.query.RemoteStorageParam;

@Service
public class RemoteStorageService extends BaseService {

    @Autowired
    private SanCheck sanCheck;

    @Autowired
    private MgmApi mgmApi;

    @Transactional
    public Result list(RemoteStorageParam sanParam) throws ServiceException {
        try {
            List<RemoteStorageDTO> remoteStorageDTOs = new ArrayList<>();

            MgmRemoteStorageQuery mgmRemoteStorageQuery = new MgmRemoteStorageQuery();
            if(sanParam.getSiteId()!=null) {
            	mgmRemoteStorageQuery.setSiteId(sanParam.getSiteId());
            }
            if(sanParam.getEnabled()!=null) {
            	mgmRemoteStorageQuery.setEnabled(sanParam.getEnabled());
            }
            List<MgmRemoteStorage> mgmRemoteStorages = mgmApi.listRemoteStorage(mgmRemoteStorageQuery);
            for (MgmRemoteStorage mgmRemoteStorage : mgmRemoteStorages) {
            	RemoteStorageDTO remoteStorageDTO = getBaseDTO(mgmRemoteStorage);
            	remoteStorageDTOs.add(remoteStorageDTO);
            }
            
            return Result.success(remoteStorageDTOs);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    public Result get(String remoteStorageId) throws ServiceException {
        RemoteStorageDTO remoteStorageDTO = null;
        try {
        	if(nullCheck(remoteStorageId)) {
        		return Result.failure(CheckResult.failure(SanChkRsEnum.ADD_ILLEGAL_San_ID_MUST_NOT_BE_NULL));
        	}
        	MgmRemoteStorageQuery mgmRemoteStorageQuery = new MgmRemoteStorageQuery();
        	mgmRemoteStorageQuery.setId(remoteStorageId);
			
        	List<MgmRemoteStorage> mgmRemoteStorages = mgmApi.listRemoteStorage(mgmRemoteStorageQuery);
        	MgmRemoteStorage mgmRemoteStorage;
            if (mgmRemoteStorages.size() != 0) {
            	mgmRemoteStorage = mgmRemoteStorages.get(0);
            	remoteStorageDTO = getBaseDTO(mgmRemoteStorage);
            }
            return Result.success(remoteStorageDTO);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    @Transactional
    public RemoteStorageDTO getSan(String remoteStorageId) throws ServiceException {
        RemoteStorageDTO remoteStorageDTO = null;
        try {
        	if(nullCheck(remoteStorageId)) {
        		return remoteStorageDTO;
        	}
        	MgmRemoteStorageQuery mgmRemoteStorageQuery = new MgmRemoteStorageQuery();
        	mgmRemoteStorageQuery.setId(remoteStorageId);
			
        	List<MgmRemoteStorage> mgmRemoteStorages = mgmApi.listRemoteStorage(mgmRemoteStorageQuery);
        	MgmRemoteStorage mgmRemoteStorage;
            if (mgmRemoteStorages.size() != 0) {
            	mgmRemoteStorage = mgmRemoteStorages.get(0);
            	remoteStorageDTO = getBaseDTO(mgmRemoteStorage);
            }
            return remoteStorageDTO;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    @Transactional
    public RemoteStorageDTO getRemoteStorage(String remoteStorageId) throws ServiceException {
        RemoteStorageDTO remoteStorageDTO = null;
        try {
        	if(nullCheck(remoteStorageId)) {
        		return remoteStorageDTO;
        	}
        	MgmRemoteStorageQuery mgmRemoteStorageQuery = new MgmRemoteStorageQuery();
        	mgmRemoteStorageQuery.setId(remoteStorageId);
			
        	List<MgmRemoteStorage> mgmRemoteStorages = mgmApi.listRemoteStorage(mgmRemoteStorageQuery);
        	MgmRemoteStorage mgmRemoteStorage;
            if (mgmRemoteStorages.size() != 0) {
            	mgmRemoteStorage = mgmRemoteStorages.get(0);
            	remoteStorageDTO = getBaseDTO(mgmRemoteStorage);
            }
            return remoteStorageDTO;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result save(RemoteStorageForm remoteStorageForm) throws ServiceException {
        try {
            CheckResult checkResult = sanCheck.checkSave(remoteStorageForm);
            if (checkResult.getCode() != SanChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            MgmRemoteStorageBody mgmRemoteStorageBody = buildSaveMgmRemoteStorageBody(remoteStorageForm);

            mgmApi.saveRemoteStorage(mgmRemoteStorageBody);
            
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    @Transactional(rollbackFor = ServiceException.class)
    public Result enabled(String storageId, boolean enabled) throws ServiceException {
        try {
        	if(nullCheck(storageId)) {
        		return Result.failure(CheckResult.failure(SanChkRsEnum.ADD_ILLEGAL_San_ID_MUST_NOT_BE_NULL));
        	}
        	MgmRemoteStorageBody mgmRemoteStorageBody = new MgmRemoteStorageBody();

        	mgmRemoteStorageBody.setEnabled(enabled);
        	mgmRemoteStorageBody.setModifiedUser(getUsername());

            mgmApi.updateRemoteStorage(storageId, mgmRemoteStorageBody);
            
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result remove(String storageId) throws ServiceException {
        try {
        	mgmApi.removeRemoteStorage(storageId);
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    private RemoteStorageDTO getBaseDTO(MgmRemoteStorage mgmRemoteStorage) {
    	if (mgmRemoteStorage == null) {
    		return null;
    	}
    	
        RemoteStorageDTO remoteStorageDTO = new RemoteStorageDTO();
        
        remoteStorageDTO.setId(mgmRemoteStorage.getId());
        remoteStorageDTO.setName(mgmRemoteStorage.getName());
        
        com.bsg.upm.mgm.model.MgmRemoteStorage.Site site = mgmRemoteStorage.getSite();
        if (site != null) {
        	SiteDTO siteDTO = remoteStorageDTO.new SiteDTO();
        	siteDTO.setId(site.getId());
        	siteDTO.setName(site.getName());
        	
        	remoteStorageDTO.setSite(siteDTO);
        }
        
        com.bsg.upm.mgm.model.MgmRemoteStorage.Auth auth = mgmRemoteStorage.getAuth();
        if (auth != null) {
//        	remoteStorageDTO.setIp(auth.getIp());
//        	remoteStorageDTO.setPort(Integer.valueOf(auth.getPort()));
//        	remoteStorageDTO.setUsername(auth.getUsername());
        	
        	//add by yeht for auth
        	AuthDTO authDTO = remoteStorageDTO.new AuthDTO();
        	authDTO.setIp(auth.getIp());
        	authDTO.setPort(auth.getPort());
        	authDTO.setVstorename(auth.getVstorename());
        	remoteStorageDTO.setAuth(authDTO);
        }
        
        if (mgmRemoteStorage.getVendor() != null) {
        	TypeDTO vendorDTO = remoteStorageDTO.new TypeDTO();
            vendorDTO.setCode(mgmRemoteStorage.getVendor());
            if (mgmRemoteStorage.getModel() != null) {
            	RemoteStorageVendorDO remoteStorageVendorDO = remoteStorageVendorDAO.getByCodeAndVersion(mgmRemoteStorage.getVendor(), mgmRemoteStorage.getModel());
            	if (remoteStorageVendorDO != null) {
                	vendorDTO.setDisplay(remoteStorageVendorDO.getName() + "(" + remoteStorageVendorDO.getVersion() + ")");
                }
            }
            remoteStorageDTO.setVendor(vendorDTO);
        }
        
        remoteStorageDTO.setModel(mgmRemoteStorage.getModel());
        
        if (mgmRemoteStorage.getType() != null) {
        	TypeDTO typeDTO = remoteStorageDTO.new TypeDTO();
        	typeDTO.setCode(mgmRemoteStorage.getType());
        	typeDTO.setDisplay(dictDAO.getNameByCodeAndDictTypeCode(mgmRemoteStorage.getType(), DictTypeConsts.REMOTE_STORAGE_TYPE));
        	remoteStorageDTO.setType(typeDTO);
        }
        
        if (mgmRemoteStorage.getCapacity() != null) {
        	remoteStorageDTO.setTotalSize(mgmRemoteStorage.getCapacity());
        }
        if (mgmRemoteStorage.getUsed() != null) {
        	remoteStorageDTO.setUsedSize(mgmRemoteStorage.getUsed());
        }
        remoteStorageDTO.setEnabled(mgmRemoteStorage.getEnabled());
        remoteStorageDTO.setDescription(mgmRemoteStorage.getDesc());
        
        Info createdInfo = mgmRemoteStorage.getCreated();
        if (createdInfo != null) {
        	InfoDTO createdInfoDTO = remoteStorageDTO.new InfoDTO();
        	createdInfoDTO.setUsername(createdInfo.getUser());
        	createdInfoDTO.setTimestamp(createdInfo.getTimestamp());
        	remoteStorageDTO.setCreated(createdInfoDTO);
        }
        
        Info modifiedInfo = mgmRemoteStorage.getModified();
        if (modifiedInfo != null) {
        	InfoDTO modifiedInfoDTO = remoteStorageDTO.new InfoDTO();
        	modifiedInfoDTO.setUsername(modifiedInfo.getUser());
        	modifiedInfoDTO.setTimestamp(modifiedInfo.getTimestamp());
        	remoteStorageDTO.setModified(modifiedInfoDTO);
        }
        
        Task task = mgmRemoteStorage.getTask();
        if (task != null) {
        	TaskDTO taskDTO = remoteStorageDTO.new TaskDTO();
        	taskDTO.setId(task.getId());
        	
        	if (task.getAction() != null) {
        		TypeDTO actionDTO = remoteStorageDTO.new TypeDTO();
        		actionDTO.setCode(task.getAction());
        		actionDTO.setDisplay(dictDAO.getNameByCodeAndDictTypeCode(task.getAction(), DictTypeConsts.TASK_ACTION));
        		taskDTO.setAction(actionDTO);
        	}
        	
        	if (task.getStatus() != null) {
        		TypeDTO statusDTO = remoteStorageDTO.new TypeDTO();
        		statusDTO.setCode(task.getStatus());
        		statusDTO.setDisplay(dictDAO.getNameByCodeAndDictTypeCode(task.getStatus(), DictTypeConsts.TASK_STATUS));
        		taskDTO.setStatus(statusDTO);
        	}
        	
        	remoteStorageDTO.setTask(taskDTO);
        }

        return remoteStorageDTO;
    }

    private void setMgmShow(RemoteStorageDTO sanDTO, MgmRemoteStorage mgmSan) {
        if (mgmSan != null) {
//            sanDTO.setTotalSize(mgmSan.getTotal());
//            sanDTO.setFreeSize(mgmSan.getFree());

//            List<MgmSan.Space> mgmSpaces = mgmSan.getSpaces();
            /*if (mgmSpaces != null) {
                List<RgDTO> rgDTOs = new ArrayList<>(mgmSpaces.size());
//                sanDTO.setRgs(rgDTOs);

                for (MgmSan.Space mgmSpace : mgmSpaces) {
                    RgDTO rgDTO = new RgDTO();
                    rgDTOs.add(rgDTO);

                    rgDTO.setCode(mgmSpace.getId());
                    rgDTO.setTotalSize(mgmSpace.getTotal());
                    rgDTO.setFreeSize(mgmSpace.getFree());
//                    rgDTO.setLunSum(mgmSpace.getLunNum());
                    rgDTO.setEnabled(mgmSpace.isEnable());
                }
            }*/
        }
    }


    private MgmRemoteStorageBody buildSaveMgmRemoteStorageBody(RemoteStorageForm remoteStorageForm) {
    	if (remoteStorageForm == null) {
    		return null;
    	}
    	
        MgmRemoteStorageBody mgmRemoteStorageBody = new MgmRemoteStorageBody();
        
        mgmRemoteStorageBody.setSiteId(remoteStorageForm.getSiteId());
        mgmRemoteStorageBody.setName(remoteStorageForm.getName());
        
        if (remoteStorageForm.getVendorCode() != null) {
        	RemoteStorageVendorDO remoteStorageVendorDO = remoteStorageVendorDAO.get(remoteStorageForm.getVendorCode());
        	
        	if (remoteStorageVendorDO != null) {
        		mgmRemoteStorageBody.setVendor(remoteStorageVendorDO.getCode());
        		mgmRemoteStorageBody.setModel(remoteStorageVendorDO.getVersion());
        	}
        }
        
        if (remoteStorageForm.getType() != null) {
        	DictDO typeDictDO = dictDAO.get(remoteStorageForm.getType());
        	if (typeDictDO != null) {
        		mgmRemoteStorageBody.setType(typeDictDO.getCode());
        	}
        }
        
        Auth auth = new MgmRemoteStorageBody.Auth();
        auth.setIp(remoteStorageForm.getIp());
        auth.setPort(remoteStorageForm.getPort());
        auth.setUsername(remoteStorageForm.getUsername());
        auth.setPassword(remoteStorageForm.getPassword());
        auth.setVstorename(remoteStorageForm.getVstorename());
        mgmRemoteStorageBody.setAuth(auth);
        
        mgmRemoteStorageBody.setEnabled(remoteStorageForm.getEnabled());
        
        mgmRemoteStorageBody.setDesc(remoteStorageForm.getDescription());
        mgmRemoteStorageBody.setCreatedUser(getUsername());
        
        return mgmRemoteStorageBody;
    }

}
