package com.bsg.upm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.SiteCheck;
import com.bsg.upm.check.resultenum.ServerGroupChkRsEnum;
import com.bsg.upm.check.resultenum.SiteChkRsEnum;
import com.bsg.upm.constant.DictTypeConsts;
import com.bsg.upm.domain.DictDO;
import com.bsg.upm.dto.BaseDTO.InfoDTO;
import com.bsg.upm.dto.BaseDTO.TypeDTO;
import com.bsg.upm.dto.SiteDTO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.SiteForm;
import com.bsg.upm.mgm.api.MgmApi;
import com.bsg.upm.mgm.body.MgmSiteBody;
import com.bsg.upm.mgm.body.MgmUpdateSiteBody;
import com.bsg.upm.mgm.model.MgmModel.Info;
import com.bsg.upm.mgm.query.MgmSiteQuery;
import com.bsg.upm.mgm.model.MgmSite;

@Service
public class SiteService extends BaseService {

    @Autowired
    private SiteCheck siteCheck;

    @Autowired
    private MgmApi mgmApi;

    @Autowired
    private HostService hostService;

    @Autowired
    private ConsulService consulService;

    /**
     * site list
     * 
     * @param siteForm
     * @return Result
     * @throws ServiceException
     */
    @Transactional
    public Result list() throws ServiceException {
        try {
            final List<SiteDTO> siteDTOs = new ArrayList<>();
            
            MgmSiteQuery mgmSiteQuery = new MgmSiteQuery();
            List<MgmSite> mgmSites = mgmApi.listSite(mgmSiteQuery);
            for (MgmSite mgmSite : mgmSites) {
            	SiteDTO siteDTO = getShowBaseDTO(mgmSite);
            	siteDTOs.add(siteDTO);
            }
            
            return Result.success(siteDTOs);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * site detail
     * 
     * @param siteId
     * @return Result
     * @throws ServiceException
     */
    public Result get(String siteId) throws ServiceException {
        SiteDTO siteDTO = null;
        try {
        	if(nullCheck(siteId)) {
        		return Result.failure(CheckResult.failure(SiteChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
        	MgmSiteQuery mgmSiteQuery = new MgmSiteQuery();
        	mgmSiteQuery.setId(siteId);
        	List<MgmSite> mgmSites = mgmApi.listSite(mgmSiteQuery);
        	MgmSite mgmSite;
            if (mgmSites.size() != 0) {
            	mgmSite = mgmSites.get(0);
            	siteDTO = getShowBaseDTO(mgmSite);
            }
        	
            return Result.success(siteDTO);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    public SiteDTO getSite(String siteId) throws ServiceException {
        SiteDTO siteDTO = null;
        try {
        	MgmSiteQuery mgmSiteQuery = new MgmSiteQuery();
        	mgmSiteQuery.setId(siteId);
        	List<MgmSite> mgmSites = mgmApi.listSite(mgmSiteQuery);
        	MgmSite mgmSite;
            if (mgmSites.size() != 0) {
            	mgmSite = mgmSites.get(0);
            	siteDTO = getShowBaseDTO(mgmSite);
            }
        	
            return siteDTO;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * save site
     * 
     * @param siteForm
     * @return
     * @throws ServiceException
     */
    @Transactional(rollbackFor = ServiceException.class)
    public Result save(SiteForm siteForm) throws ServiceException {
        try {
            CheckResult checkResult = siteCheck.checkSave(siteForm);
            if (checkResult.getCode() != SiteChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            
            MgmSiteBody mgmSiteBody = buildSaveMgmSiteBody(siteForm);
            mgmApi.saveSite(mgmSiteBody);
            
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    @Transactional(rollbackFor = ServiceException.class)
    public Result update(String siteId,SiteForm siteForm) throws ServiceException {
        try {
        	if(nullCheck(siteId)) {
        		return Result.failure(CheckResult.failure(SiteChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = siteCheck.checkSave(siteForm);
            if (checkResult.getCode() != SiteChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            
            MgmUpdateSiteBody mgmSiteBody = buildUpdateMgmSiteBody(siteForm);
            mgmApi.updateSite(siteId,mgmSiteBody);
            
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 
     * @param siteId
     * @return
     * @throws ServiceException
     */
    @Transactional(rollbackFor = ServiceException.class)
    public Result remove(String siteId) throws ServiceException {
        try {
        	if(nullCheck(siteId)) {
        		return Result.failure(CheckResult.failure(SiteChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = siteCheck.checkRemove(siteId);
            if (checkResult.getCode() != SiteChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            
            mgmApi.removeSite(siteId);
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 
     * @param siteDO
     * @return
     */
    private SiteDTO getShowBaseDTO(MgmSite mgmSite) {
        SiteDTO siteDTO = new SiteDTO();
        siteDTO.setId(mgmSite.getId());
        siteDTO.setName(mgmSite.getName());
        siteDTO.setImage_registry(mgmSite.getImage_registry());
        
        if (mgmSite.getRegion() != null) {
        	TypeDTO regionDTO = siteDTO.new TypeDTO();
            regionDTO.setCode(mgmSite.getRegion());
            regionDTO.setDisplay(dictDAO.getNameByCodeAndDictTypeCode(mgmSite.getRegion(), DictTypeConsts.REGION));
            siteDTO.setRegion(regionDTO);
        }
        
        if (mgmSite.getType() != null) {
        	TypeDTO typeDTO = siteDTO.new TypeDTO();
            typeDTO.setCode(mgmSite.getType());
            typeDTO.setDisplay(dictDAO.getNameByCodeAndDictTypeCode(mgmSite.getType(), DictTypeConsts.SITE_TYPE));
            siteDTO.setType(typeDTO);
        }
        
        siteDTO.setDomain(mgmSite.getDomain());
        siteDTO.setPort(mgmSite.getPort());
        siteDTO.setVersion(mgmSite.getVersion());
        
        if (mgmSite.getState() != null) {
        	siteDTO.setStatus(mgmSite.getState().toLowerCase());
        }
        
        siteDTO.setDescription(mgmSite.getDesc());
        
        Info createdInfo = mgmSite.getCreated();
        if (createdInfo != null) {
        	InfoDTO createdInfoDTO = siteDTO.new InfoDTO();
        	createdInfoDTO.setUsername(createdInfo.getUser());
        	createdInfoDTO.setTimestamp(createdInfo.getTimestamp());
        	siteDTO.setCreated(createdInfoDTO);
        }
        
        Info modifiedInfo = mgmSite.getModified();
        if (modifiedInfo != null) {
        	InfoDTO modifiedInfoDTO = siteDTO.new InfoDTO();
        	modifiedInfoDTO.setUsername(modifiedInfo.getUser());
        	modifiedInfoDTO.setTimestamp(modifiedInfo.getTimestamp());
        	siteDTO.setModified(modifiedInfoDTO);
        }

        return siteDTO;
    }

    /**
     * 
     * @param siteForm
     * @return
     */
    private MgmSiteBody buildSaveMgmSiteBody(SiteForm siteForm) {
    	MgmSiteBody mgmSiteBody = new MgmSiteBody();
    	
    	mgmSiteBody.setName(siteForm.getName());
    	
    	if (siteForm.getType() != null) {
    		DictDO typeDictDO = dictDAO.get(siteForm.getType());
    		if (typeDictDO != null) {
    			mgmSiteBody.setType(typeDictDO.getCode());
    		}
    		
    	}
    	
    	mgmSiteBody.setDomain(siteForm.getDomain());
    	mgmSiteBody.setPort(siteForm.getPort());
    	mgmSiteBody.setDesc(siteForm.getDescription());
    	
    	if (siteForm.getRegion() != null) {
    		DictDO regionDictDO = dictDAO.get(siteForm.getRegion());
    		if (regionDictDO != null) {
    			mgmSiteBody.setRegion(regionDictDO.getCode());
    		}
    	}
    	
    	mgmSiteBody.setCreatedUser(getUsername());
    	
        return mgmSiteBody;
    }
    
    private MgmUpdateSiteBody buildUpdateMgmSiteBody(SiteForm siteForm) {
    	MgmUpdateSiteBody mgmSiteBody = new MgmUpdateSiteBody();
    	
    	mgmSiteBody.setName(siteForm.getName());
    	mgmSiteBody.setDomain(siteForm.getDomain());
    	mgmSiteBody.setPort(siteForm.getPort());
    	mgmSiteBody.setDesc(siteForm.getDescription());
    	mgmSiteBody.setRegion(siteForm.getRegion());
    	mgmSiteBody.setModifiedUser(getUsername());
    	
        return mgmSiteBody;
    }

}
