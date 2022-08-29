package com.bsg.upm.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.NetworkingCheck;
import com.bsg.upm.check.resultenum.ClusterChkRsEnum;
import com.bsg.upm.check.resultenum.ImageChkRsEnum;
import com.bsg.upm.check.resultenum.NetworkingChkRsEnum;
import com.bsg.upm.dto.BaseDTO.InfoDTO;
import com.bsg.upm.dto.NetworkingDTO;
import com.bsg.upm.dto.NetworkingDTO.ClusterDTO;
import com.bsg.upm.dto.NetworkingDTO.IPDTO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.NetworkingForm;
import com.bsg.upm.mgm.api.MgmApi;
import com.bsg.upm.mgm.body.MgmNetworkingBody;
import com.bsg.upm.mgm.model.MgmModel.Info;
import com.bsg.upm.mgm.model.MgmNetworking;
import com.bsg.upm.mgm.model.MgmSite;
import com.bsg.upm.mgm.model.MgmNetworking.Cluster;
import com.bsg.upm.mgm.model.MgmNetworking.IP;
import com.bsg.upm.mgm.model.MgmNetworking.Site;
import com.bsg.upm.mgm.query.MgmNetworkingQuery;
import com.bsg.upm.mgm.query.MgmSiteQuery;
import com.bsg.upm.query.NetworkingParam;

@Service
public class NetworkingService extends BaseService {

	@Autowired
	private NetworkingCheck networkingCheck;

	@Autowired
	private MgmApi mgmApi;

	@Transactional
	public Result list(NetworkingParam networkingParam) throws ServiceException {

		try {
			List<NetworkingDTO> networkingDTOs = new ArrayList<>();

			MgmNetworkingQuery mgmNetworkingQuery = new MgmNetworkingQuery();
			if(networkingParam.getEnabled()!=null) {
				mgmNetworkingQuery.setEnabled(networkingParam.getEnabled());
			}
			if(networkingParam.getClusterId()!=null) {
				mgmNetworkingQuery.setClusterId(networkingParam.getClusterId());
			}
			if(networkingParam.getSiteId()!=null) {
				mgmNetworkingQuery.setSiteId(networkingParam.getSiteId());
			}
			List<MgmNetworking> mgmNetworkings = mgmApi.listNetworking(mgmNetworkingQuery);
			for (MgmNetworking mgmNetworking : mgmNetworkings) {
				NetworkingDTO networkingDTO = getBaseDTO(mgmNetworking);
				networkingDTOs.add(networkingDTO);
			}

			return Result.success(networkingDTOs);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

	}

	public Result get(String networkingId) throws ServiceException {
		NetworkingDTO networkingDTO = null;
		try {
			if(nullCheck(networkingId)) {
        		return Result.failure(CheckResult.failure(NetworkingChkRsEnum.ADD_ILLEGAL_SITE_ID_MUST_NOT_BE_NULL));
        	}
			MgmNetworkingQuery mgmNetworkingQuery = new MgmNetworkingQuery();
			mgmNetworkingQuery.setId(networkingId);
        	List<MgmNetworking> mgmNetworkings = mgmApi.listNetworking(mgmNetworkingQuery);
        	MgmNetworking mgmNetworking;
            if (mgmNetworkings.size() != 0) {
            	mgmNetworking = mgmNetworkings.get(0);
            	networkingDTO = getBaseDTO(mgmNetworking);
            }
            
			return Result.success(networkingDTO);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public NetworkingDTO getNetWorking(String networkingId) throws ServiceException {
		NetworkingDTO networkingDTO = null;
		try {
			/*if(nullCheck(networkingId)) {
        		return Result.failure(CheckResult.failure(NetworkingChkRsEnum.ADD_ILLEGAL_SITE_ID_MUST_NOT_BE_NULL));
        	}*/
			MgmNetworkingQuery mgmNetworkingQuery = new MgmNetworkingQuery();
			mgmNetworkingQuery.setId(networkingId);
        	List<MgmNetworking> mgmNetworkings = mgmApi.listNetworking(mgmNetworkingQuery);
        	MgmNetworking mgmNetworking;
            if (mgmNetworkings.size() != 0) {
            	mgmNetworking = mgmNetworkings.get(0);
            	networkingDTO = getBaseDTO(mgmNetworking);
            }
            
			return networkingDTO;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Transactional(rollbackFor = ServiceException.class)
	public Result save(NetworkingForm networkingForm) throws ServiceException {
		try {
			CheckResult checkResult = networkingCheck.checkSave(networkingForm);
			if (checkResult.getCode() != NetworkingChkRsEnum.SUCCESS.getCode()) {
				logger.error(loginfo(checkResult));
				return Result.failure(checkResult);
			}

			MgmNetworkingBody mgmNetworkingBody = buildSaveMgmNetworkingBody(networkingForm);

			mgmApi.saveNetworking(mgmNetworkingBody);

			return Result.success();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Transactional(rollbackFor = ServiceException.class)
	public Result update(String networkingId, NetworkingForm networkingForm) throws ServiceException {
		try {
			if(nullCheck(networkingId)) {
        		return Result.failure(CheckResult.failure(NetworkingChkRsEnum.ADD_ILLEGAL_SITE_ID_MUST_NOT_BE_NULL));
        	}
			CheckResult checkResult = networkingCheck.checkUpdate(networkingId, networkingForm);
			if (checkResult.getCode() != NetworkingChkRsEnum.SUCCESS.getCode()) {
				logger.error(loginfo(checkResult));
				return Result.failure(checkResult);
			}
			MgmNetworkingBody mgmNetworkingBodydy = buildUpdateMgmNetworkingBody(networkingForm);
			mgmApi.updateNetworking(networkingId, mgmNetworkingBodydy);

			return Result.success();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Transactional(rollbackFor = ServiceException.class)
	public Result enabled(String networkingId, boolean enabled) throws ServiceException {
		try {
			if(nullCheck(networkingId)) {
        		return Result.failure(CheckResult.failure(NetworkingChkRsEnum.ADD_ILLEGAL_SITE_ID_MUST_NOT_BE_NULL));
        	}
			MgmNetworkingBody mgmNetworkingBody = new MgmNetworkingBody();

			mgmNetworkingBody.setEnabled(enabled);
			mgmNetworkingBody.setModifiedUser(getUsername());

			mgmApi.updateNetworking(String.valueOf(networkingId), mgmNetworkingBody);

			return Result.success();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Transactional(rollbackFor = ServiceException.class)
	public Result remove(String networkingId) throws ServiceException {
		try {
			if(nullCheck(networkingId)) {
        		return Result.failure(CheckResult.failure(NetworkingChkRsEnum.ADD_ILLEGAL_SITE_ID_MUST_NOT_BE_NULL));
        	}
			MgmNetworkingQuery mgmNetworkingQuery = new MgmNetworkingQuery();
			mgmNetworkingQuery.setId(networkingId);
        	List<MgmNetworking> mgmNetworkings = mgmApi.listNetworking(mgmNetworkingQuery);
        	MgmNetworking mgmNetworking;
            if (mgmNetworkings.size() != 0) {
            	mgmNetworking = mgmNetworkings.get(0);
            	if(mgmNetworking.getEnabled()) {
            		return Result.failure(CheckResult.failure(NetworkingChkRsEnum.REMOVE_ILLEGAL_WITH_ENABLED));
            	}else {
            		mgmApi.removeNetworking(String.valueOf(networkingId));
        			return Result.success();
            	}
            }else {
            	return Result.failure(CheckResult.failure(NetworkingChkRsEnum.ADD_ILLEGAL_CLUSTER_ID_NOT_EXIST));
            }
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	private NetworkingDTO getBaseDTO(MgmNetworking mgmNetworking) {
		NetworkingDTO networkingDTO = new NetworkingDTO();

		networkingDTO.setId(mgmNetworking.getId());

		Site site = mgmNetworking.getSite();
		if (site != null) {
			com.bsg.upm.dto.NetworkingDTO.SiteDTO siteDTO = networkingDTO.new SiteDTO();
			siteDTO.setId(site.getId());
			siteDTO.setName(site.getName());

			networkingDTO.setSite(siteDTO);
		}

		IP ipSummary = mgmNetworking.getIpSummary();
		if (ipSummary != null) {
			networkingDTO.setStartIp(ipSummary.getStartIp());
			networkingDTO.setEndIp(ipSummary.getEndIp());
			networkingDTO.setGateway(ipSummary.getGateway());
			networkingDTO.setMask(ipSummary.getPrefix());
			networkingDTO.setVlanId(ipSummary.getVlan());

			IPDTO ipDTO = networkingDTO.new IPDTO();
			ipDTO.setTotal(ipSummary.getTotal());
			ipDTO.setUsedCnt(ipSummary.getUsed());
			networkingDTO.setIp(ipDTO);
		}

		networkingDTO.setTopologies(mgmNetworking.getTopology());
		Cluster cluster = mgmNetworking.getCluster();
		if (cluster != null) {
			ClusterDTO clusterDTO = networkingDTO.new ClusterDTO();
			clusterDTO.setId(cluster.getId());
			clusterDTO.setName(cluster.getName());
			networkingDTO.setCluster(clusterDTO);
		}

		networkingDTO.setEnabled(mgmNetworking.getEnabled());
		networkingDTO.setDescription(mgmNetworking.getDesc());

		Info createdInfo = mgmNetworking.getCreated();
		if (createdInfo != null) {
			InfoDTO createdInfoDTO = networkingDTO.new InfoDTO();
			createdInfoDTO.setUsername(createdInfo.getUser());
			createdInfoDTO.setTimestamp(createdInfo.getTimestamp());
			networkingDTO.setCreated(createdInfoDTO);
		}

		Info modifiedInfo = mgmNetworking.getModified();
		if (modifiedInfo != null) {
			InfoDTO modifiedInfoDTO = networkingDTO.new InfoDTO();
			modifiedInfoDTO.setUsername(modifiedInfo.getUser());
			modifiedInfoDTO.setTimestamp(modifiedInfo.getTimestamp());
			networkingDTO.setModified(modifiedInfoDTO);
		}

		return networkingDTO;
	}

	private MgmNetworkingBody buildSaveMgmNetworkingBody(NetworkingForm networkingForm) {
		MgmNetworkingBody mgmNetworkingBody = new MgmNetworkingBody();

		mgmNetworkingBody.setSiteId(networkingForm.getSiteId());
		mgmNetworkingBody.setClusterId(networkingForm.getClusterId());
//		mgmNetworkingBody.setName(networkingForm.getStartIp() + "-" + networkingForm.getEndIp());

		if (networkingForm.getTopologies() != null) {
			mgmNetworkingBody.setTopology(networkingForm.getTopologies());
		}

		com.bsg.upm.mgm.body.MgmNetworkingBody.IP ipSummary = new MgmNetworkingBody.IP();
		ipSummary.setStartIp(networkingForm.getStartIp());
		ipSummary.setEndIp(networkingForm.getEndIp());
		ipSummary.setGateway(networkingForm.getGateway());
		ipSummary.setPrefix(networkingForm.getMask());
		ipSummary.setVlan(networkingForm.getVlanId());
		mgmNetworkingBody.setIpSummary(ipSummary);

		mgmNetworkingBody.setEnabled(networkingForm.getEnabled());
		mgmNetworkingBody.setDesc(networkingForm.getDescription());
		mgmNetworkingBody.setCreatedUser(getUsername());

		return mgmNetworkingBody;
	}

	private MgmNetworkingBody buildUpdateMgmNetworkingBody(NetworkingForm networkingForm) {
		MgmNetworkingBody mgmNetworkingBody = new MgmNetworkingBody();

		mgmNetworkingBody.setClusterId(String.valueOf(networkingForm.getClusterId()));
		if (networkingForm.getTopologies() != null) {
			mgmNetworkingBody.setTopology(networkingForm.getTopologies());
		}

		mgmNetworkingBody.setDesc(networkingForm.getDescription());
		mgmNetworkingBody.setModifiedUser(getUsername());

		return mgmNetworkingBody;
	}
	
	public Result getMgmPath() throws ServiceException {
		NetworkingDTO networkingDTO = new NetworkingDTO();
			String sysPropertiesFilePath = SYSPATH + File.separator + "sys.properties";
	        FileSystemResource file = new FileSystemResource(sysPropertiesFilePath);
	        try {
	            if (!file.exists()) {
	                logger.error(sysPropertiesFilePath + " not found.");
	            }
	            Properties prop = PropertiesLoaderUtils.loadProperties(file);
	            String path = prop.getProperty("connect.mgm.path");
	            if (StringUtils.isBlank(path)) {
	                logger.error("connect.mgm.path is blank.");
	            }
	            networkingDTO.setPath(path);
            
			return Result.success(networkingDTO);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public Result toShell(String site_id,String podId,String type) {
		try {
			if(nullCheck(podId)) {
        		return Result.failure(CheckResult.failure(NetworkingChkRsEnum.ADD_ILLEGAL_POD_ID_MUST_NOT_BE_NULL));
        	}
			if(nullCheck(type)) {
				type = "mysql";
        	}
    		String resp = mgmApi.toShell(String.valueOf(site_id),String.valueOf(podId),String.valueOf(type));
			return Result.success(resp);
		
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		}
	
	public Result getGrafanaAddr() throws ServiceException {
		NetworkingDTO networkingDTO = new NetworkingDTO();
			String sysPropertiesFilePath = SYSPATH + File.separator + "sys.properties";
	        FileSystemResource file = new FileSystemResource(sysPropertiesFilePath);
	        try {
	            if (!file.exists()) {
	                logger.error(sysPropertiesFilePath + " not found.");
	            }
	            Properties prop = PropertiesLoaderUtils.loadProperties(file);
	            String path = prop.getProperty("grafana.addr");
	            if (StringUtils.isBlank(path)) {
	                logger.error("grafana.addr is blank.");
	            }
	            networkingDTO.setPath(path);
            
			return Result.success(networkingDTO);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public Result statecheck(String subscription_id) throws ServiceException {
	        try {
	        	 mgmApi.getSites();
				return Result.success();
            
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
