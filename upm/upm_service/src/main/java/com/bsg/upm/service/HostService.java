package com.bsg.upm.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.HostCheck;
import com.bsg.upm.check.resultenum.GroupChkRsEnum;
import com.bsg.upm.check.resultenum.HostChkRsEnum;
import com.bsg.upm.check.resultenum.RgChkRsEnum;
import com.bsg.upm.constant.DictTypeConsts;
import com.bsg.upm.domain.UserDO;
import com.bsg.upm.dto.BaseDTO.InfoDTO;
import com.bsg.upm.dto.BaseDTO.TaskDTO;
import com.bsg.upm.dto.BaseDTO.TypeDTO;
import com.bsg.upm.dto.HostDTO;
import com.bsg.upm.dto.HostDTO.BandwidthDTO;
import com.bsg.upm.dto.HostDTO.ClusterDTO;
import com.bsg.upm.dto.HostDTO.CpuDTO;
import com.bsg.upm.dto.HostDTO.MaxUsageDTO;
import com.bsg.upm.dto.HostDTO.MemDTO;
import com.bsg.upm.dto.HostDTO.PodDTO;
import com.bsg.upm.dto.HostDTO.SiteDTO;
import com.bsg.upm.dto.HostDTO.StorageDTO;
import com.bsg.upm.dto.HostDTO.StorageRemoteDTO;
import com.bsg.upm.dto.ServerGroupDTO.UnitDTO;
import com.bsg.upm.exception.CallingInterfaceException;
import com.bsg.upm.exception.ConnectConsulException;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.exception.ServiceNotFoundException;
import com.bsg.upm.form.HostForm;
import com.bsg.upm.form.StorageForm;
import com.bsg.upm.mgm.body.MgmHostBody;
import com.bsg.upm.mgm.model.MgmCluster;
import com.bsg.upm.mgm.model.MgmHost;
import com.bsg.upm.mgm.model.MgmHost.StorageHost;
import com.bsg.upm.mgm.model.MgmHost.Unit;
import com.bsg.upm.mgm.query.MgmClusterQuery;
import com.bsg.upm.mgm.query.MgmHostQuery;
import com.bsg.upm.param.HostDeleteParam;
import com.bsg.upm.param.HostMonitorCancelParam;
import com.bsg.upm.param.HostMonitorParam;
import com.bsg.upm.param.HostMonitorQueryParam;
import com.bsg.upm.param.HostMonitorRegisParam;
import com.bsg.upm.param.HostMonitorRegisParam1;
import com.bsg.upm.query.HostParam;

@Service
public class HostService extends BaseService {

    @Autowired
    private HostCheck hostCheck;

    @Transactional
    public Result list(HostForm hostForm) throws ServiceException {
        try {
            List<HostDTO> hostDTOs = new ArrayList<>();
            MgmHostQuery mgmHostQuery = new MgmHostQuery();
            if(hostForm.getEnabled()!=null) {
            	mgmHostQuery.setEnabled(hostForm.getEnabled());
            }
            if(hostForm.getClusterId()!=null) {
            	mgmHostQuery.setClusterId(hostForm.getClusterId());
            }
            if(hostForm.getSiteId()!=null) {
            	mgmHostQuery.setSiteId(hostForm.getSiteId());
            }
            List<MgmHost> mgmHosts = mgmApi.listHost(mgmHostQuery);
            for (MgmHost mgmHost : mgmHosts) {
                HostDTO hostDTO = getShowBaseDTO(mgmHost);
                hostDTOs.add(hostDTO);
            }
            return Result.success(hostDTOs);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Result get(String hostId) throws ServiceException {
        HostDTO hostDTO = null;
        try {
        	if(nullCheck(hostId)) {
        		return Result.failure(CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            MgmHostQuery mgmHostQuery = new MgmHostQuery();
            mgmHostQuery.setId(hostId);
            MgmHost mgmHosts = mgmApi.getHost(hostId);
            hostDTO = getShowBaseDTO(mgmHosts);
            return Result.success(hostDTO);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    public HostDTO getHost(String hostId) throws ServiceException {
        HostDTO hostDTO = null;
        try {
        	if(nullCheck(hostId)) {
        		return hostDTO;
        	}
            MgmHostQuery mgmHostQuery = new MgmHostQuery();
            mgmHostQuery.setId(hostId);
            MgmHost mgmHosts = mgmApi.getHost(hostId);
            hostDTO = getShowBaseDTO(mgmHosts);
            return hostDTO;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    public Result getAgents(String hostIp) throws ServiceException {
        try {
        	if(nullCheck(hostIp)) {
        		return Result.failure(CheckResult.failure(HostChkRsEnum.MONITORCANCEL_ILLEGAL_IP_MUST_NOT_BE_EMPTY));
        	}
            mgmApi.getAgents(hostIp);
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    public Result getAgentsHost(String hostId) throws ServiceException {
        try {
        	if(nullCheck(hostId)) {
        		return Result.failure(CheckResult.failure(HostChkRsEnum.MONITORCANCEL_ILLEGAL_ID_MUST_NOT_BE_EMPTY));
        	}
            mgmApi.getAgentsHost(hostId);
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result save(HostForm hostForm) throws ServiceException {
        try {
            CheckResult checkResult = hostCheck.checkSave(hostForm);
            if (checkResult.getCode() != HostChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            MgmHostBody mgmHostBody = buildSaveHostBody(hostForm);

            mgmApi.saveHost(mgmHostBody);
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    public Result validation(HostForm hostForm,HostParam hostParam) throws ServiceException {
        try {
            CheckResult checkResult = hostCheck.checkSave(hostForm);
            if (checkResult.getCode() != HostChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            MgmHostBody mgmHostBody = buildSaveHostBody(hostForm);

            mgmApi.validation(mgmHostBody,hostParam);
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result update(String hostId, HostForm hostForm) throws ServiceException {
        try {
        	if(nullCheck(hostId)) {
        		return Result.failure(CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = hostCheck.checkUpdate(hostId, hostForm);
            if (checkResult.getCode() != HostChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            MgmHostBody mgmHostBody = buildHostForUpdate(hostForm);
            mgmApi.updateHost(hostId, mgmHostBody);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result enabled(String hostId, boolean enabled) throws ServiceException {
        try {
        	if(nullCheck(hostId)) {
        		return Result.failure(CheckResult.failure(HostChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            MgmHostBody mgmHostBody = new MgmHostBody();
            mgmHostBody.setEnabled(enabled);

            mgmApi.updateHost(hostId, mgmHostBody);
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result remove(String hostId,HostDeleteParam userForm) throws ServiceException {
        try {
        	if(nullCheck(hostId)) {
        		return Result.failure(CheckResult.failure(HostChkRsEnum.MONITORCANCEL_ILLEGAL_IP_MUST_NOT_BE_EMPTY));
        	}
        	MgmHostQuery mgmHostQuery = new MgmHostQuery();
            mgmHostQuery.setId(hostId);
            List<MgmHost> mgmHosts = mgmApi.listHost(mgmHostQuery);
            if(mgmHosts.size()!=0) {
            	HostDTO hostDTO=getShowBaseDTO(mgmHosts.get(0));
            	if(hostDTO.getEnabled()) {
            		return Result.failure(CheckResult.failure(HostChkRsEnum.REMOVE_ILLEGAL_WITH_ENABLED));
            	}else {
            		 mgmApi.removeHost(hostId,userForm);
                     return Result.success();
            	}
            }else {
            	return Result.failure(CheckResult.failure(HostChkRsEnum.ILLEGAL_ID_NOT_EXIST));
            }
//            return Result.success(hostDTO);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    @Transactional(rollbackFor = ServiceException.class)
    public Result monitorRegister(HostMonitorRegisParam hostMonitorRegisParam) throws ServiceException {
        try {
        	 CheckResult checkResult = hostCheck.checkMonitorRegister(hostMonitorRegisParam);
             if (checkResult.getCode() != HostChkRsEnum.SUCCESS.getCode()) {
                 logger.error(loginfo(checkResult));
                 return Result.failure(checkResult);
             }
             mgmApi.monitorRegister(hostMonitorRegisParam);
             return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    @Transactional(rollbackFor = ServiceException.class)
    public Result monitorRegister1(HostMonitorRegisParam1 hostMonitorRegisParam) throws ServiceException {
        try {
        	 CheckResult checkResult = hostCheck.checkMonitorRegister1(hostMonitorRegisParam);
             if (checkResult.getCode() != HostChkRsEnum.SUCCESS.getCode()) {
                 logger.error(loginfo(checkResult));
                 return Result.failure(checkResult);
             }
             mgmApi.monitorRegister1(hostMonitorRegisParam);
             return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    @Transactional(rollbackFor = ServiceException.class)
    public Result monitorCancel(HostMonitorRegisParam hostMonitorRegisParam) throws ServiceException {
        try {
        	 CheckResult checkResult = hostCheck.checkMonitorCancel(hostMonitorRegisParam);
             if (checkResult.getCode() != HostChkRsEnum.SUCCESS.getCode()) {
                 logger.error(loginfo(checkResult));
                 return Result.failure(checkResult);
             }
             mgmApi.monitorCancel(hostMonitorRegisParam);
             return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    @Transactional(rollbackFor = ServiceException.class)
    public Result monitorCancel1(String hostId) throws ServiceException {
        try {
        	if(nullCheck(hostId)) {
        		return Result.failure(CheckResult.failure(HostChkRsEnum.MONITORCANCEL_ILLEGAL_IP_MUST_NOT_BE_EMPTY));
        	}
        	HostMonitorCancelParam hostMonitorCancelParam=new HostMonitorCancelParam();
        	hostMonitorCancelParam.setForce(true);
             mgmApi.monitorCancel1(hostId,hostMonitorCancelParam);
             return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }


    public HostDTO getShowBaseDTO(MgmHost mgmHost)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
    	HostDTO hostDTO = new HostDTO();
        hostDTO.setId(mgmHost.getId());
        hostDTO.setArch(mgmHost.getArch());
        hostDTO.setRole(mgmHost.getRole());
        hostDTO.setName(mgmHost.getNode().getName());

//        SiteDTO siteDTO = hostDTO.new SiteDTO();
//        MgmClusterQuery mgmClusterQuery = new MgmClusterQuery();
//        mgmClusterQuery.setId(mgmHost.getCluster().getId());
//        List<MgmCluster> mgmClusters = mgmApi.listCluster(mgmClusterQuery);
//        MgmCluster mgmCluster = mgmClusters.get(0);
//        siteDTO.setId(mgmCluster.getSite().getId());
//        siteDTO.setName(mgmCluster.getSite().getName());
//        hostDTO.setSite(siteDTO);

        ClusterDTO clusterDTO = hostDTO.new ClusterDTO();
        clusterDTO.setId(mgmHost.getCluster().getId());
        clusterDTO.setName(mgmHost.getCluster().getName());
        hostDTO.setCluster(clusterDTO);

        hostDTO.setIp(mgmHost.getNode().getIp());
        hostDTO.setRoom(mgmHost.getRoom());
        hostDTO.setSeat(mgmHost.getSeat());
        hostDTO.setNtpServer(mgmHost.getNtpServer());

        List<StorageHost> storageHosts = mgmHost.getStorageHost();
        List<StorageDTO> storageDTOs = new ArrayList<>();
        if(storageHosts!=null) {
	        for (StorageHost storageHost : storageHosts) {
	            StorageDTO storageDTO = hostDTO.new StorageDTO();
	            TypeDTO typeDTO = hostDTO.new TypeDTO();
	            typeDTO.setCode(storageHost.getPerformance());
	            typeDTO.setDisplay(
	                    dictDAO.getNameByCodeAndDictTypeCode(storageHost.getPerformance(), DictTypeConsts.PERFORMANCE));
	            storageDTO.setPerformance(typeDTO);
	            storageDTO.setPath(storageHost.getPaths());
	            storageDTO.setUsedSize(storageHost.getUsed());
	            storageDTO.setTotalSize(storageHost.getCapacity());
	            storageDTOs.add(storageDTO);
	        }
        }
        hostDTO.setStorage(storageDTOs);

        StorageRemoteDTO storageRemoteDTO = hostDTO.new StorageRemoteDTO();
        storageRemoteDTO.setId(mgmHost.getStorageRemote().getId());
        storageRemoteDTO.setName(mgmHost.getStorageRemote().getName());
        hostDTO.setStorageRemote(storageRemoteDTO);

        hostDTO.setMaxUnitCnt(mgmHost.getMaxUnit());
        if(mgmHost.getUnits()!=null) {
        	hostDTO.setUnits(mgmHost.getUnits());
        }
        hostDTO.setExistingUnitCnt(mgmHost.getExistingUnit());

        MaxUsageDTO maxUsageDTO = hostDTO.new MaxUsageDTO();
        maxUsageDTO.setCpu(mgmHost.getMaxUsage().getCpu());
        maxUsageDTO.setMem(mgmHost.getMaxUsage().getMemory());
        maxUsageDTO.setBandwidth(mgmHost.getMaxUsage().getNetBandwidth());
        maxUsageDTO.setStorage(mgmHost.getMaxUsage().getStorageHost());
        hostDTO.setMaxUsage(maxUsageDTO);

        hostDTO.setOs(mgmHost.getNode().getOs());

        CpuDTO cpuDTO = hostDTO.new CpuDTO();
        cpuDTO.setUsedCnt(mgmHost.getNode().getCpu().getUsed());
        cpuDTO.setTotal(mgmHost.getNode().getCpu().getCapacity());
        hostDTO.setCpu(cpuDTO);

        MemDTO memDTO = hostDTO.new MemDTO();
        memDTO.setUsedSize(mgmHost.getNode().getMemory().getUsed());
        memDTO.setTotalSize(mgmHost.getNode().getMemory().getCapacity());
        hostDTO.setMem(memDTO);
        
        PodDTO podDTO=hostDTO.new PodDTO();
        podDTO.setUsedSize(mgmHost.getNode().getPod().getUsed());
        podDTO.setTotalSize(mgmHost.getNode().getPod().getCapacity());
        hostDTO.setPod(podDTO);

        BandwidthDTO bandwidthDTO = hostDTO.new BandwidthDTO();
        bandwidthDTO.setUsedSize(mgmHost.getNode().getNetBandwidth().getUsed());
        bandwidthDTO.setTotalSize(mgmHost.getNode().getNetBandwidth().getCapacity());
        hostDTO.setBandwidth(bandwidthDTO);

        hostDTO.setEnabled(mgmHost.getEnabled());
        hostDTO.setDescription(mgmHost.getDesc());

        InfoDTO createdDTO = hostDTO.new InfoDTO();
        createdDTO.setUsername(mgmHost.getCreated().getUser());
        createdDTO.setTimestamp(mgmHost.getCreated().getTimestamp());
        hostDTO.setCreated(createdDTO);

        InfoDTO modifiedDTO = hostDTO.new InfoDTO();
        modifiedDTO.setUsername(mgmHost.getModified().getUser());
        modifiedDTO.setTimestamp(mgmHost.getModified().getTimestamp());
        hostDTO.setModified(modifiedDTO);

        TaskDTO taskDTO = hostDTO.new TaskDTO();
        taskDTO.setId(mgmHost.getTask().getId());
        TypeDTO actionDTO = hostDTO.new TypeDTO();
        actionDTO.setCode(mgmHost.getTask().getAction());
        actionDTO.setDisplay(
                dictDAO.getNameByCodeAndDictTypeCode(mgmHost.getTask().getAction(), DictTypeConsts.TASK_ACTION));
        taskDTO.setAction(actionDTO);
        TypeDTO statusDTO = hostDTO.new TypeDTO();
        statusDTO.setCode(mgmHost.getTask().getStatus());
        statusDTO.setDisplay(
                dictDAO.getNameByCodeAndDictTypeCode(mgmHost.getTask().getStatus(), DictTypeConsts.TASK_STATUS));
        taskDTO.setStatus(statusDTO);
        hostDTO.setTask(taskDTO);

        return hostDTO;
    }

    public MgmHostBody buildSaveHostBody(HostForm hostForm) {
        MgmHostBody mgmHostBody = new MgmHostBody();

        mgmHostBody.setClusterId(hostForm.getClusterId());
        mgmHostBody.setRole(hostForm.getRole());

        com.bsg.upm.mgm.body.Ssh ssh = new com.bsg.upm.mgm.body.Ssh();
        ssh.setIp(hostForm.getIp());
        ssh.setPort(hostForm.getSshPort());
        ssh.setUsername(hostForm.getSshUsername());
        ssh.setPassword(hostForm.getSshPassword());
        mgmHostBody.setSsh(ssh);

        mgmHostBody.setRoom(hostForm.getRoom());
        mgmHostBody.setSeat(hostForm.getSeat());
        mgmHostBody.setNtpServer(hostForm.getNtpServer());

        List<com.bsg.upm.mgm.body.StorageHost> storageHosts = new ArrayList<>();
        for (StorageForm storageForm : hostForm.getStorage()) {
            com.bsg.upm.mgm.body.StorageHost storageHost = new com.bsg.upm.mgm.body.StorageHost();
            storageHost.setPerformance(storageForm.getPerformance()); 
            storageHost.setPaths(storageForm.getPath());
            storageHosts.add(storageHost);
        }
        mgmHostBody.setStorageHost(storageHosts);

        mgmHostBody.setStorageRemoteId(hostForm.getStorageRemoteId());
        mgmHostBody.setMaxUnit(hostForm.getMaxUnitCnt());

        com.bsg.upm.mgm.body.MaxUsage maxUsage = new com.bsg.upm.mgm.body.MaxUsage();
        maxUsage.setCpu(hostForm.getMaxUsage().getCpu());
        maxUsage.setMem(hostForm.getMaxUsage().getMem());
        maxUsage.setNetBandwidth(hostForm.getMaxUsage().getBandwidth());
        maxUsage.setStorageHost(hostForm.getMaxUsage().getStorage());
        mgmHostBody.setMaxUsage(maxUsage);

        mgmHostBody.setEnabled(hostForm.getEnabled());
        mgmHostBody.setDesc(hostForm.getDescription());
        mgmHostBody.setCreatedUser(getUsername());

        return mgmHostBody;
    }

    public MgmHostBody buildHostForUpdate(HostForm hostForm) {
        MgmHostBody mgmHostBody = new MgmHostBody();

        mgmHostBody.setRoom(hostForm.getRoom());
        mgmHostBody.setRole(hostForm.getRole());
        mgmHostBody.setSeat(hostForm.getSeat());
        mgmHostBody.setMaxUnit(hostForm.getMaxUnitCnt());

        com.bsg.upm.mgm.body.MaxUsage maxUsage = new com.bsg.upm.mgm.body.MaxUsage();
        maxUsage.setCpu(hostForm.getMaxUsage().getCpu());
        maxUsage.setMem(hostForm.getMaxUsage().getMem());
        maxUsage.setNetBandwidth(hostForm.getMaxUsage().getBandwidth());
        maxUsage.setStorageHost(hostForm.getMaxUsage().getStorage());
        mgmHostBody.setMaxUsage(maxUsage);

        mgmHostBody.setDesc(hostForm.getDescription());
        mgmHostBody.setModifiedUser(getUsername());

        return mgmHostBody;
    }
    
    public Result getHostMemMonitor(String hostId,String type,HostMonitorQueryParam hostMonitorQueryParam) throws ServiceException {
        try {
        	
        	if(nullCheck(hostId)) {
        		return Result.failure(CheckResult.failure(HostChkRsEnum.MONITORCANCEL_ILLEGAL_ID_MUST_NOT_BE_EMPTY));
        	}else if(hostMonitorQueryParam==null) {
        		return Result.failure(CheckResult.failure(HostChkRsEnum.MONITOR_ILLEGAL_STARTTIMEORENDTIME_MUST_NOT_BE_EMPTY));
        	}
        	HostMonitorParam hostMonitorParam=mgmApi.getHostMemMonitor(hostId,type,hostMonitorQueryParam);
            return Result.success(hostMonitorParam);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
