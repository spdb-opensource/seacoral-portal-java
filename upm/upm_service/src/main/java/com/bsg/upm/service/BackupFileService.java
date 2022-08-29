package com.bsg.upm.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.check.BackupFileCheck;
import com.bsg.upm.check.BackupStrategyCheck;
import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.DatabaseCheck;
import com.bsg.upm.check.resultenum.BackupFileChkRsEnum;
import com.bsg.upm.check.resultenum.BackupStrategyChkRsEnum;
import com.bsg.upm.check.resultenum.ServerGroupUnitChkRsEnum;
import com.bsg.upm.check.resultenum.SiteChkRsEnum;
import com.bsg.upm.constant.DictTypeConsts;
import com.bsg.upm.domain.OrderGroupDO;
import com.bsg.upm.domain.UserDO;
import com.bsg.upm.dto.BackupFileDTO;
import com.bsg.upm.dto.BackupFileDTO.Config;
import com.bsg.upm.dto.BackupFileDTO.IdAndNamDTO;
import com.bsg.upm.dto.BackupFileDTO.ServerGroupDTO;
import com.bsg.upm.dto.ServerGroupDTO.OwnerDTO;
import com.bsg.upm.dto.BackupStrategyDTO;
import com.bsg.upm.exception.CallingInterfaceException;
import com.bsg.upm.exception.ConnectConsulException;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.exception.ServiceNotFoundException;
import com.bsg.upm.form.DatabaseForm;
import com.bsg.upm.form.ServerGroupUserForm;
import com.bsg.upm.mgm.api.MgmApi;
import com.bsg.upm.mgm.body.MgmBackupStrategyBody;
import com.bsg.upm.mgm.body.MgmDatabaseBody;
import com.bsg.upm.mgm.body.MgmServerGroupUserBody;
import com.bsg.upm.mgm.model.MgmBackupFile;
import com.bsg.upm.mgm.model.MgmBackupStrategy;
import com.bsg.upm.mgm.query.MgmBackupFileQuery;
import com.bsg.upm.mgm.query.MgmBackupRestoreFile;
import com.bsg.upm.query.BackupEndpointParam;
import com.bsg.upm.query.BackupFileParam;
import com.bsg.upm.query.BackupStrategyAddParam;

@Service
public class BackupFileService extends BaseService {

    @Autowired
    private BackupFileCheck backupFileCheck;

    @Autowired
    private MgmApi mgmApi;
    
    @Autowired
	private BackupStrategyCheck backupStrategyCheck;

    @Transactional
    public Result list(BackupFileParam backupFileParam) throws ServiceException {
        try {
            List<BackupFileDTO> backupFileDTOs = new ArrayList<>();
            
            MgmBackupFileQuery mgmBackupFileQuery=new MgmBackupFileQuery();
            if(StringUtils.isNotBlank(backupFileParam.getServGroupId())){
                mgmBackupFileQuery.setAppId(backupFileParam.getServGroupId());
            }
            mgmBackupFileQuery.setSiteId(backupFileParam.getSiteId());
            mgmBackupFileQuery.setValid(backupFileParam.getValid());
            mgmBackupFileQuery.setStatus(backupFileParam.getStatus());
            List<MgmBackupFile> mgmBackupFiles = mgmApi.listBackupFile(mgmBackupFileQuery);
            for (MgmBackupFile mgmBackupFile : mgmBackupFiles) {
                BackupFileDTO backupFileDTO = getShowBaseDTO(mgmBackupFile);
                backupFileDTOs.add(backupFileDTO);
            }
            
            return Result.success(backupFileDTOs);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    public Result restore(BackupFileParam backupFileParam) throws ServiceException {
        try {
            logger.info("备份还原参数: {}", backupFileParam.toString());
            MgmBackupRestoreFile mgmBackupFileQuery=new MgmBackupRestoreFile();
            mgmBackupFileQuery.setBackupFileId(backupFileParam.getBackupFileId());
            mgmApi.restoreBackupFile(backupFileParam.getAppId(),backupFileParam.getUnitId(),mgmBackupFileQuery);
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    @Transactional(rollbackFor = ServiceException.class)
    public Result remove(String backupFileId) throws ServiceException {
        try {
            CheckResult checkResult = backupFileCheck.checkRemove(backupFileId);
            if (checkResult.getCode() != BackupFileChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            
            mgmApi.removeBackupFile(backupFileId);
            
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    private BackupFileDTO getShowBaseDTO(MgmBackupFile mgmBackupFile) {
    	if (mgmBackupFile == null) {
    		return null;
    	}
    	
    	BackupFileDTO backupFileDTO = new BackupFileDTO();
    	
    	backupFileDTO.setId(mgmBackupFile.getId());
    	backupFileDTO.setName(mgmBackupFile.getName());
    	backupFileDTO.setPath(mgmBackupFile.getPath());
    	backupFileDTO.setType(mgmBackupFile.getType());
//    	backupFileDTO.setType(dictDAO.getNameByCodeAndDictTypeCode(mgmBackupFile.getType(), DictTypeConsts.BACKUP_TYPE));
    	backupFileDTO.setSize(mgmBackupFile.getSize());
    	backupFileDTO.setRetention(mgmBackupFile.getRetention());
    	backupFileDTO.setGmtStart(mgmBackupFile.getCreatedAt());
    	backupFileDTO.setExpire(mgmBackupFile.getExpireAt());
    	backupFileDTO.setGmtEnd(mgmBackupFile.getFinishedAt());
    	backupFileDTO.setValid(mgmBackupFile.getVaild());
    	backupFileDTO.setStatus(mgmBackupFile.getStatus());
    	if (mgmBackupFile.getBackupStorage() != null) {
    		IdAndNamDTO storageRemoteDTO = backupFileDTO.new IdAndNamDTO();
    		storageRemoteDTO.setId(mgmBackupFile.getBackupStorage().getId());
    		storageRemoteDTO.setName(mgmBackupFile.getBackupStorage().getName());
    		backupFileDTO.setStorageRemote(storageRemoteDTO);
    	}

    	if (mgmBackupFile.getUnit() != null) {
    		IdAndNamDTO unitDTO = backupFileDTO.new IdAndNamDTO();
    		unitDTO.setId(mgmBackupFile.getUnit().getId());
    		unitDTO.setName(mgmBackupFile.getUnit().getName());
    		backupFileDTO.setUnit(unitDTO);
    	}
    	
    	if (mgmBackupFile.getApp() != null) {
    		ServerGroupDTO serverGroupDTO = backupFileDTO.new ServerGroupDTO();
    		serverGroupDTO.setId(mgmBackupFile.getApp().getId());
    		serverGroupDTO.setName(mgmBackupFile.getApp().getName());
    		// 服务组用户通过查询服务组工单中任意一条数据的owner设置
    		if (StringUtils.isNotBlank(mgmBackupFile.getApp().getId())) {
    			OrderGroupDO orderGroupDO = orderGroupDAO.getLastOneByServerGroupId(mgmBackupFile.getApp().getId());
    			if (orderGroupDO != null) {
    				serverGroupDTO.setOwner(orderGroupDO.getOwner());
    			}
    		}
    		backupFileDTO.setServGroup(serverGroupDTO);
    	}
    	
    	
    	if (mgmBackupFile.getConfig() != null) {
    		Config configDTO = backupFileDTO.new Config();
    		configDTO.setNfs_ip(mgmBackupFile.getConfig().getNfs_ip());;
    		configDTO.setNfs_opts(mgmBackupFile.getConfig().getNfs_opts());;
    		configDTO.setNfs_source(mgmBackupFile.getConfig().getNfs_source());;
    		configDTO.setS3_url(mgmBackupFile.getConfig().getS3_url());;
    		configDTO.setS3_bucket(mgmBackupFile.getConfig().getS3_bucket());;
    		configDTO.setS3_access_key(mgmBackupFile.getConfig().getS3_access_key());;
    		configDTO.setS3_secret_key(mgmBackupFile.getConfig().getS3_secret_key());;
    		backupFileDTO.setConfig(configDTO);
    	}
    	backupFileDTO.setEnabled(mgmBackupFile.getEnabled());
    	backupFileDTO.setSiteId(mgmBackupFile.getSiteId());
    	backupFileDTO.setCreatedUser(mgmBackupFile.getCreatedUser());
    	
    	if (mgmBackupFile.getEndpoint() != null) {
    		IdAndNamDTO endpintDTO = backupFileDTO.new IdAndNamDTO();
    		endpintDTO.setId(mgmBackupFile.getUnit().getId());
    		endpintDTO.setName(mgmBackupFile.getUnit().getName());
    		backupFileDTO.setEndpoint(endpintDTO);
    	}
    	backupFileDTO.setEndpointType(mgmBackupFile.getEndpointType());
    	
        return backupFileDTO;
    }
    
    /**
	 *备份策略查询
	 * 
	 * @param backupFileParam
	 * @return
	 * @throws ServiceException
	 */
    @Transactional
    public Result listStrategy(BackupFileParam backupFileParam) throws ServiceException {
        try {
            List<BackupStrategyDTO> backupStrategyDTOs = new ArrayList<>();
            
            MgmBackupFileQuery mgmBackupStrategyQuery=new MgmBackupFileQuery();
            mgmBackupStrategyQuery.setAppId(backupFileParam.getServGroupId());
            mgmBackupStrategyQuery.setUnitId(backupFileParam.getUnitId());
            mgmBackupStrategyQuery.setId(backupFileParam.getStrategyId());
            List<MgmBackupStrategy> mgmBackupFiles = mgmApi.listStrategyFile(mgmBackupStrategyQuery);
            for (MgmBackupStrategy mgmBackupFile : mgmBackupFiles) {
            	BackupStrategyDTO backupFileDTO = strategyGetShowBaseDTO(mgmBackupFile);
                backupStrategyDTOs.add(backupFileDTO);
            }
            
            return Result.success(backupStrategyDTOs);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    private BackupStrategyDTO strategyGetShowBaseDTO(MgmBackupStrategy mgmBackupStrategy) {
    	if (mgmBackupStrategy == null) {
    		return null;
    	}
    	BackupStrategyDTO backupStrategyDTO = new BackupStrategyDTO();
    	backupStrategyDTO.setId(mgmBackupStrategy.getId());
    	backupStrategyDTO.setName(mgmBackupStrategy.getName());
    	backupStrategyDTO.setBackupType(dictDAO.getNameByCodeAndDictTypeCode(mgmBackupStrategy.getType(), DictTypeConsts.BACKUP_TYPE));
    	backupStrategyDTO.setDesc(mgmBackupStrategy.getDesc());
    	backupStrategyDTO.setSchedule(mgmBackupStrategy.getSchedule());
    	backupStrategyDTO.setStorage(mgmBackupStrategy.getStorage());
    	backupStrategyDTO.setRole(mgmBackupStrategy.getRole());
    	backupStrategyDTO.setServGroupId(mgmBackupStrategy.getAppId());
    	backupStrategyDTO.setTables(mgmBackupStrategy.getTables());
    	backupStrategyDTO.setUnitId(mgmBackupStrategy.getUnitId());
    	backupStrategyDTO.setEnabled(mgmBackupStrategy.getEnabled());
    	backupStrategyDTO.setRetention(mgmBackupStrategy.getRetention());
    	
    	 if (mgmBackupStrategy.getCreated()!= null) {
             com.bsg.upm.dto.BackupStrategyDTO.OwnerDTO ownerDTO = backupStrategyDTO.new OwnerDTO();
             UserDO ownerUser = userDAO.getByUsername(mgmBackupStrategy.getCreated().getUser());
             if (ownerUser != null) {
             	 ownerDTO.setName(ownerUser.getName());
                 ownerDTO.setUsername(ownerUser.getUsername());
                 ownerDTO.setTimestamp(mgmBackupStrategy.getCreated().getTimestamp());
             }
             backupStrategyDTO.setCreated(ownerDTO);
         }
    	 if (mgmBackupStrategy.getModified()!= null) {
             com.bsg.upm.dto.BackupStrategyDTO.OwnerDTO ownerDTO = backupStrategyDTO.new OwnerDTO();
             UserDO ownerUser = userDAO.getByUsername(mgmBackupStrategy.getModified().getUser());
             if (ownerUser != null) {
             	 ownerDTO.setName(ownerUser.getName());
                 ownerDTO.setUsername(ownerUser.getUsername());
                 ownerDTO.setTimestamp(mgmBackupStrategy.getModified().getTimestamp());
             }
             backupStrategyDTO.setModified(ownerDTO);
         }
        return backupStrategyDTO;
    }
    
    /**
	 *备份策略新增
	 * 
	 * @param serverGroupId
	 * @param backupStrategyAddParam
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(rollbackFor = ServiceException.class)
	public Result save(String serverGroupId, BackupStrategyAddParam backupStrategyAddParam) throws ServiceException {
		try {
			if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(BackupStrategyChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
			CheckResult checkResult = backupStrategyCheck.checkSave(backupStrategyAddParam);
			if (checkResult.getCode() != BackupFileChkRsEnum.SUCCESS.getCode()) {
				logger.error(loginfo(checkResult));
				return Result.failure(checkResult);
			}

			MgmBackupStrategyBody mgmStrategyBody = buildSaveMgmBackupStrategyBody(backupStrategyAddParam);
			mgmStrategyBody.setAppId(serverGroupId);
			mgmApi.saveBackupStrategy(mgmStrategyBody);

			return Result.success();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	private MgmBackupStrategyBody buildSaveMgmBackupStrategyBody(BackupStrategyAddParam backupStrategyAddParam) {
		MgmBackupStrategyBody mgmBackupStrategyBody = new MgmBackupStrategyBody();

		mgmBackupStrategyBody.setName(backupStrategyAddParam.getName());
		mgmBackupStrategyBody.setOnce(false);
		mgmBackupStrategyBody.setRetention(backupStrategyAddParam.getRetention());
		mgmBackupStrategyBody.setType(backupStrategyAddParam.getType());
		mgmBackupStrategyBody.setSchedule(backupStrategyAddParam.getCronExpression());
		mgmBackupStrategyBody.setCreatedUser(getUsername());
		mgmBackupStrategyBody.setDesc(backupStrategyAddParam.getDescription());
		mgmBackupStrategyBody.setEnabled(backupStrategyAddParam.getEnabled());
		mgmBackupStrategyBody.setStorage(backupStrategyAddParam.getStorage());
		mgmBackupStrategyBody.setEndpointId(backupStrategyAddParam.getEndpointId());
		mgmBackupStrategyBody.setRole(backupStrategyAddParam.getRole());
		mgmBackupStrategyBody.setUnit_id(backupStrategyAddParam.getUnit_id());
		return mgmBackupStrategyBody;
	}
	
	/**
	 *备份策略删除
	 * 
	 * @param backupStrategyId
	 * @return
	 * @throws ServiceException
	 */
	@Transactional(rollbackFor = ServiceException.class)
    public Result removeStrategy(String backupStrategyId) throws ServiceException {
        try {
            CheckResult checkResult = backupFileCheck.checkRemoveStrategy(backupStrategyId);
            if (checkResult.getCode() != BackupFileChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            
            mgmApi.removeStrategyFile(backupStrategyId);
            
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

	public Result listEndpoint(BackupFileParam backupFileParam) {
		try {
            List<BackupFileDTO> backupFileDTOs = new ArrayList<>();
            
            MgmBackupFileQuery mgmBackupFileQuery=new MgmBackupFileQuery();
            if(StringUtils.isNotBlank(backupFileParam.getEndpointId())){
                mgmBackupFileQuery.setId(backupFileParam.getEndpointId());
            }
//            mgmBackupFileQuery.setSiteId(backupFileParam.getSiteId());
//            mgmBackupFileQuery.setValid(backupFileParam.getValid());
//            mgmBackupFileQuery.setStatus(backupFileParam.getStatus());
            List<MgmBackupFile> mgmBackupFiles = mgmApi.listBackupEndpoint(mgmBackupFileQuery);
            for (MgmBackupFile mgmBackupFile : mgmBackupFiles) {
                BackupFileDTO backupFileDTO = getShowBaseDTO(mgmBackupFile);
                backupFileDTOs.add(backupFileDTO);
            }
            
            return Result.success(backupFileDTOs);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
	}

	public Result saveEndpoint(BackupEndpointParam backupEndpointParam) {
		try {
			
			
			if (StringUtils.isBlank(backupEndpointParam.getName())) {
				return Result.failure(20421011,"备份存储名不能为空");
			}
//			if (StringUtils.isBlank(backupEndpointParam.getEnabled())) {
//				return Result.failure(20421012,"备份存储开发不能为空");
//			}
			if (backupEndpointParam.getSite_id()==null) {
				return Result.failure(20421013,"备份存储站点id不能为空");
			}
			if (StringUtils.isBlank(backupEndpointParam.getType())) {
				return Result.failure(20421014,"备份存储类型不能为空");
			}
			
			if (backupEndpointParam.getConfig()==null) {
				return Result.failure(20421015,"备份存储配置不能为空");
			}
			
			backupEndpointParam.setCreated_user("admin");
			mgmApi.saveBackupEndpoint(backupEndpointParam);

			return Result.success();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public Result deleteEndpoint(String id) {
		try {
			if (StringUtils.isBlank(id)) {
				return Result.failure(20421016,"备份存储id不能为空");
			}
			mgmApi.removeBackupEndpoint(id);
			return Result.success();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public Result updateEndpoint(String id, BackupEndpointParam backupEndpointParam) {
		try {
			if (StringUtils.isBlank(id)) {
				return Result.failure(20421016,"备份存储id不能为空");
			}
			if (StringUtils.isBlank(backupEndpointParam.getName())) {
				return Result.failure(20421011,"备份存储名不能为空");
			}
			/*if (StringUtils.isAnyBlank(backupEndpointParam.getEnabled())) {
				return Result.failure(20421012,"备份存储开发不能为空");
			}*/
			if (backupEndpointParam.getSite_id()==null) {
				return Result.failure(20421013,"备份存储站点id不能为空");
			}
			if (StringUtils.isBlank(backupEndpointParam.getType())) {
				return Result.failure(20421014,"备份存储类型不能为空");
			}
			
			if (backupEndpointParam.getConfig()==null) {
				return Result.failure(20421015,"备份存储配置不能为空");
			}
			mgmApi.updateBackupEndpoint(id,backupEndpointParam);
			return Result.success();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
