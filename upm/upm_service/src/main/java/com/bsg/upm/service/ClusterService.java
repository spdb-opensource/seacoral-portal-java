package com.bsg.upm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.ClusterCheck;
import com.bsg.upm.check.resultenum.BackupStrategyChkRsEnum;
import com.bsg.upm.check.resultenum.ClusterChkRsEnum;
import com.bsg.upm.check.resultenum.ImageChkRsEnum;
import com.bsg.upm.constant.DictTypeConsts;
import com.bsg.upm.dto.BaseDTO.InfoDTO;
import com.bsg.upm.dto.BaseDTO.TypeDTO;
import com.bsg.upm.dto.ClusterDTO;
import com.bsg.upm.dto.ClusterDTO.NetworkingDTO;
import com.bsg.upm.dto.ClusterDTO.SiteDTO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.ClusterForm;
import com.bsg.upm.mgm.body.MgmClusterBody;
import com.bsg.upm.mgm.model.MgmCluster;
import com.bsg.upm.mgm.model.MgmCluster.Network;
import com.bsg.upm.mgm.query.MgmClusterQuery;
import com.bsg.upm.query.ClusterParam;

@Service
public class ClusterService extends BaseService {

    @Autowired
    private ClusterCheck clusterCheck;

    @Transactional
    public Result list(ClusterParam clusterParam) throws ServiceException {
        try {
            List<ClusterDTO> clusterDTOs = new ArrayList<>();
            MgmClusterQuery mgmClusterQuery=new MgmClusterQuery();
            if(clusterParam.getEnabled()!=null) {
            	mgmClusterQuery.setEnabled(clusterParam.getEnabled());
            }
            if(clusterParam.getSiteId()!=null) {
            	mgmClusterQuery.setSiteId(clusterParam.getSiteId());
            }
            List<MgmCluster> mgmClusters = mgmApi.listCluster(mgmClusterQuery);
            for (MgmCluster mgmCluster : mgmClusters) {
                ClusterDTO clusterDTO = getShowBaseDTO(mgmCluster);
                clusterDTOs.add(clusterDTO);
            }
            return Result.success(clusterDTOs);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Result get(String clusterId) throws ServiceException {
        ClusterDTO clusterDTO = null;
        try {
        	if(nullCheck(clusterId)) {
        		return Result.failure(CheckResult.failure(ClusterChkRsEnum.ADD_ILLEGAL_SITE_ID_MUST_NOT_BE_BLANK));
        	}
            MgmClusterQuery mgmClusterQuery=new MgmClusterQuery();
            mgmClusterQuery.setId(clusterId);
            List<MgmCluster> mgmClusters = mgmApi.listCluster(mgmClusterQuery);
            MgmCluster mgmCluster;
            if (mgmClusters.size() != 0) {
                mgmCluster = mgmClusters.get(0);
                clusterDTO = getShowBaseDTO(mgmCluster);
            }
            return Result.success(clusterDTO);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    public ClusterDTO getCluster(String clusterId) throws ServiceException {
        ClusterDTO clusterDTO = null;
        try {
            MgmClusterQuery mgmClusterQuery=new MgmClusterQuery();
            mgmClusterQuery.setId(clusterId);
            List<MgmCluster> mgmClusters = mgmApi.listCluster(mgmClusterQuery);
            MgmCluster mgmCluster;
            if (mgmClusters.size() != 0) {
                mgmCluster = mgmClusters.get(0);
                clusterDTO = getShowBaseDTO(mgmCluster);
            }
            return clusterDTO;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result save(ClusterForm clusterForm) throws ServiceException {
        try {
            CheckResult checkResult = clusterCheck.checkSave(clusterForm);
            if (checkResult.getCode() != ClusterChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            MgmClusterBody mgmClusterBody = buildSaveClusterBody(clusterForm);

            mgmApi.saveCluster(mgmClusterBody);
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result update(String clusterId, ClusterForm clusterForm) throws ServiceException {
        try {
        	if(nullCheck(clusterId)) {
        		return Result.failure(CheckResult.failure(ClusterChkRsEnum.ADD_ILLEGAL_SITE_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = clusterCheck.checkUpdate(clusterId, clusterForm);
            if (checkResult.getCode() != ClusterChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            MgmClusterBody mgmClusterBody = buildUpdateClusterBody(clusterForm);
            mgmApi.updateCluster(clusterId, mgmClusterBody);
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result enabled(String clusterId, boolean enabled) throws ServiceException {
        try {
        	if(nullCheck(clusterId)) {
        		return Result.failure(CheckResult.failure(ClusterChkRsEnum.ADD_ILLEGAL_SITE_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = clusterCheck.checkEnabled(clusterId, enabled);
            if (checkResult.getCode() != ClusterChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            MgmClusterBody mgmClusterBody = new MgmClusterBody();

            mgmClusterBody.setEnabled(enabled);
            mgmClusterBody.setModifiedUser(getUsername());

            mgmApi.updateCluster(clusterId, mgmClusterBody);
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result remove(String clusterId) throws ServiceException {
        try {
        	if(nullCheck(clusterId)) {
        		return Result.failure(CheckResult.failure(ClusterChkRsEnum.ADD_ILLEGAL_SITE_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = clusterCheck.checkRemove(clusterId);
            if (checkResult.getCode() != ClusterChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            MgmClusterQuery mgmClusterQuery=new MgmClusterQuery();
            mgmClusterQuery.setId(clusterId);
            List<MgmCluster> mgmClusters = mgmApi.listCluster(mgmClusterQuery);
            MgmCluster mgmCluster;
            if (mgmClusters.size() != 0) {
                mgmCluster = mgmClusters.get(0);
                if(mgmCluster.getEnabled()) {
                	return Result.failure(CheckResult.failure(ClusterChkRsEnum.REMOVE_ILLEGAL_ASSOCIATED_WITH_ENABLED));
                }else {
                	 mgmApi.removeCluster(clusterId);
                     return Result.success();
                }
            }else {
            	return Result.failure(CheckResult.failure(ClusterChkRsEnum.ILLEGAL_ID_NOT_EXIST));
            }
           
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    private ClusterDTO getShowBaseDTO(MgmCluster mgmCluster) {
        ClusterDTO clusterDTO = new ClusterDTO();

        clusterDTO.setId(mgmCluster.getId());
        clusterDTO.setName(mgmCluster.getName());
        
        //add by yeht for nfs
//        clusterDTO.setNfs_ip(mgmCluster.getNfs_ip());
//        clusterDTO.setNfs_source(mgmCluster.getNfs_source());
//        clusterDTO.setNfs_opts(mgmCluster.getNfs_opts());

        SiteDTO siteDTO = clusterDTO.new SiteDTO();
        siteDTO.setId(mgmCluster.getSite().getId());
        siteDTO.setName(mgmCluster.getSite().getName());
        clusterDTO.setSite(siteDTO);

        TypeDTO areaDTO = clusterDTO.new TypeDTO();
        areaDTO.setCode(mgmCluster.getZone());
        areaDTO.setDisplay(dictDAO.getNameByCodeAndDictTypeCode(mgmCluster.getZone(), DictTypeConsts.AREA));
        clusterDTO.setArea(areaDTO);

        clusterDTO.setImageTypes(mgmCluster.getImageType());
        clusterDTO.setHaTag(mgmCluster.getHaTag());

        List<NetworkingDTO> networkingDTOs = new ArrayList<>();
        List<Network> networks = mgmCluster.getNetwork();
        for (Network network : networks) {
            NetworkingDTO networkingDTO = clusterDTO.new NetworkingDTO();
            networkingDTOs.add(networkingDTO);
            networkingDTO.setId(network.getId());
            networkingDTO.setName(network.getName());
        }
        clusterDTO.setNetworkings(networkingDTOs);

        clusterDTO.setEnabled(mgmCluster.getEnabled());
        clusterDTO.setDescription(mgmCluster.getDesc());

        InfoDTO createdDTO = clusterDTO.new InfoDTO();
        createdDTO.setUsername(mgmCluster.getCreated().getUser());
        createdDTO.setTimestamp(mgmCluster.getCreated().getTimestamp());
        clusterDTO.setCreated(createdDTO);

        InfoDTO modifiedDTO = clusterDTO.new InfoDTO();
        modifiedDTO.setUsername(mgmCluster.getModified().getUser());
        modifiedDTO.setTimestamp(mgmCluster.getModified().getTimestamp());
        clusterDTO.setModified(modifiedDTO);
        return clusterDTO;
    }

    private MgmClusterBody buildSaveClusterBody(ClusterForm clusterForm) {
        MgmClusterBody mgmClusterSaveBody = new MgmClusterBody();
        mgmClusterSaveBody.setSiteId(clusterForm.getSiteId());
        mgmClusterSaveBody.setName(clusterForm.getName());
        mgmClusterSaveBody.setZone(clusterForm.getAreaCode());
        mgmClusterSaveBody.setImageType(clusterForm.getImageTypes());
        mgmClusterSaveBody.setHaTag(clusterForm.getHaTag());
        mgmClusterSaveBody.setEnabled(clusterForm.getEnabled());
        mgmClusterSaveBody.setDesc(clusterForm.getDescription());
        mgmClusterSaveBody.setCreatedUser(getUsername());
        
//        mgmClusterSaveBody.setNfs_source(clusterForm.getNfs_source());
//        mgmClusterSaveBody.setNfs_opts(clusterForm.getNfs_opts());
//        mgmClusterSaveBody.setNfs_ip(clusterForm.getNfs_ip());
        return mgmClusterSaveBody;
    }

    private MgmClusterBody buildUpdateClusterBody(ClusterForm clusterForm) {
        MgmClusterBody mgmClusterBody = new MgmClusterBody();
        mgmClusterBody.setName(clusterForm.getName());
        mgmClusterBody.setZone(clusterForm.getAreaCode());
        mgmClusterBody.setImageType(clusterForm.getImageTypes());
        mgmClusterBody.setDesc(clusterForm.getDescription());
        mgmClusterBody.setHaTag(clusterForm.getHaTag());
        mgmClusterBody.setModifiedUser(getUsername());
        
//        mgmClusterBody.setNfs_source(clusterForm.getNfs_source());
//        mgmClusterBody.setNfs_opts(clusterForm.getNfs_opts());
//        mgmClusterBody.setNfs_ip(clusterForm.getNfs_ip());

        return mgmClusterBody;
    }

}
