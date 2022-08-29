package com.bsg.upm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.ImageCheck;
import com.bsg.upm.check.resultenum.HostChkRsEnum;
import com.bsg.upm.check.resultenum.ImageChkRsEnum;
import com.bsg.upm.constant.DictTypeConsts;
import com.bsg.upm.dto.BaseDTO.InfoDTO;
import com.bsg.upm.dto.BaseDTO.TaskDTO;
import com.bsg.upm.dto.BaseDTO.TypeDTO;
import com.bsg.upm.dto.ImageDTO;
import com.bsg.upm.dto.SiteDTO;
import com.bsg.upm.dto.VersionDTO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.ImageForm;
import com.bsg.upm.mgm.api.MgmApi;
import com.bsg.upm.mgm.body.MgmImageBody;
import com.bsg.upm.mgm.model.MgmImage;
import com.bsg.upm.mgm.model.MgmImageConf;
import com.bsg.upm.mgm.model.MgmServerGroup.Image;
import com.bsg.upm.mgm.model.MgmServerGroup.KeySet;
import com.bsg.upm.mgm.model.MgmServerGroup.Template;
import com.bsg.upm.mgm.query.MgmImageQuery;
import com.bsg.upm.query.ImageConf;
import com.bsg.upm.query.ImageParam;
import com.bsg.upm.query.ImageTemplate;

@Service
public class ImageService extends BaseService {

    @Autowired
    private ImageCheck imageCheck;

    @Autowired
    private MgmApi mgmApi;

    @Transactional
    public Result list(ImageParam imageParam) throws ServiceException {
        try {
            List<ImageDTO> imageDTOs = new ArrayList<>();
            MgmImageQuery mgmImageQuery=new MgmImageQuery();
            if(imageParam.getImageId()!=null) {
            	mgmImageQuery.setId(imageParam.getImageId());
            }
            if(imageParam.getType()!=null) {
            	mgmImageQuery.setType(imageParam.getType());
            }
            if(imageParam.getEnabled()!=null) {
            	mgmImageQuery.setEnabled(String.valueOf(imageParam.getEnabled()));
            }
            BeanUtils.copyProperties(imageParam, mgmImageQuery);
            
            List<MgmImage> mgmImages=mgmApi.listImage(mgmImageQuery);
            for(MgmImage mgmImage:mgmImages){
                ImageDTO softwareImageDTO=getShowBaseDTO(mgmImage);
                imageDTOs.add(softwareImageDTO);
            }
            return Result.success(imageDTOs);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    @Transactional
    public Result get(String imageId) throws ServiceException {
        try {
            MgmImageQuery mgmImageQuery=new MgmImageQuery();
            List<MgmImage> mgmImages=mgmApi.listImage(mgmImageQuery);
            mgmImageQuery.setId(imageId);
            ImageDTO softwareImageDTO=getShowBaseDTO(mgmImages.get(0));
            return Result.success(softwareImageDTO);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    @Transactional
    public Result getImageConf(String imageId) throws ServiceException {
        try {
        	if(nullCheck(imageId)) {
        		return Result.failure(CheckResult.failure(ImageChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
        	ImageTemplate omageTemplate=new ImageTemplate();
        	List<ImageConf> imageConfDTOs = new ArrayList<>();
//            MgmImageQuery mgmImageQuery=new MgmImageQuery();
        	Image image=new Image();
        	image.setId(imageId);
            MgmImageConf mgmImageConf=mgmApi.imageConf(image);
            //MgmImageConf mgmImageConf=new MgmImageConf();
            //if(mgmImageConfList.size()!=0) {
            	//mgmImageConf=mgmImageConfList.get(0);
            if(mgmImageConf.getTemplate()!=null && mgmImageConf.getTemplate().getKeySets()!=null) {
            	for(int i=0;i<mgmImageConf.getTemplate().getKeySets().size();i++) {
                	ImageConf imageConf=new ImageConf();
                	imageConf=getImageConfDTO(mgmImageConf.getTemplate().getKeySets().get(i));
                	imageConfDTOs.add(imageConf);
                	
                }
            	omageTemplate.setConfigFile(mgmImageConf.getTemplate().getConfigFile());
            }
            	
            //}
            
            omageTemplate.setKeysets(imageConfDTOs);
//            mgmImageQuery.setId(imageId);
            return Result.success(omageTemplate);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result save(ImageForm imageForm) throws ServiceException {
        try {
            CheckResult checkResult = imageCheck.checkSave(imageForm);
            if (checkResult.getCode() != ImageChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            MgmImageBody mgmImageBody=buildSaveImageBody(imageForm);
            mgmApi.saveImage(mgmImageBody);
            
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result enabled(String imageId, boolean enabled) throws ServiceException {
        try {
        	if(nullCheck(imageId)) {
        		return Result.failure(CheckResult.failure(ImageChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            MgmImageBody mgmImageBody=new MgmImageBody();
            //modify by yeht 用unschedulable
//            mgmImageBody.setEnabled(enabled);
            mgmImageBody.setUnschedulable(!enabled);
            mgmImageBody.setModifiedUser(getUsername());
            mgmApi.updateImage(imageId, mgmImageBody);


            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result remove(String imageId) throws ServiceException {
    	ImageParam imageParam=new ImageParam();
    	imageParam.setImageId(imageId);
        try {
        	if(nullCheck(imageId)) {
        		return Result.failure(CheckResult.failure(ImageChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
        	MgmImageQuery mgmImageQuery=new MgmImageQuery();
            if(imageParam.getImageId()!=null) {
            	mgmImageQuery.setId(imageParam.getImageId());
            }
            BeanUtils.copyProperties(imageParam, mgmImageQuery);
            
            List<MgmImage> mgmImages=mgmApi.listImage(mgmImageQuery);
            MgmImage mgmImage=mgmImages.get(0);
            ImageDTO softwareImageDTO=getShowBaseDTO(mgmImage);
            
            //"unschedulable": true,未启用,false已启用
            if(!softwareImageDTO.getUnschedulable()) {
            	return Result.failure(CheckResult.failure(ImageChkRsEnum.REMOVE_ILLEGAL_ASSOCIATED_WITH_ENABLED));
            }else {
            	mgmApi.removeImage(imageId);
                return Result.success();
            }
            
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    private ImageDTO getShowBaseDTO(MgmImage mgmImage) {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(mgmImage.getId());
        imageDTO.setType(mgmImage.getType());
        imageDTO.setUnschedulable(mgmImage.getUnschedulable());
        
        VersionDTO versionDTO=new VersionDTO();
        versionDTO.setMajor(mgmImage.getMajor());
        versionDTO.setMinor(mgmImage.getMinor());
        versionDTO.setPatch(mgmImage.getPatch());
        versionDTO.setBuild(mgmImage.getBuild());
        imageDTO.setVersion(versionDTO);
        
        //imageDTO.setEnabled(!mgmImage.getUnschedulable());
        imageDTO.setDescription(mgmImage.getDesc());
        imageDTO.setArchitecture(mgmImage.getArch());
        
        try {
			InfoDTO createdDTO=imageDTO.new InfoDTO();
			createdDTO.setTimestamp(mgmImage.getCreated().getTimestamp());
			createdDTO.setUsername(mgmImage.getCreated().getUser());
			imageDTO.setCreated(createdDTO);
			
			InfoDTO modifiedDTO=imageDTO.new InfoDTO();
			modifiedDTO.setTimestamp(mgmImage.getModified().getTimestamp());
			modifiedDTO.setUsername(mgmImage.getModified().getUser());
			imageDTO.setModified(modifiedDTO);
			
			SiteDTO siteDTO = new SiteDTO();
			siteDTO.setId(mgmImage.getSite().getId());
			siteDTO.setName(mgmImage.getSite().getName());
			imageDTO.setSite(siteDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        TaskDTO taskDTO=imageDTO.new TaskDTO();
        taskDTO.setId(mgmImage.getTask().getId());
        TypeDTO actionDTO=imageDTO.new TypeDTO();
        actionDTO.setCode(mgmImage.getTask().getAction());
        actionDTO.setDisplay(dictDAO.getNameByCodeAndDictTypeCode(mgmImage.getTask().getAction(), DictTypeConsts.TASK_ACTION));
        taskDTO.setAction(actionDTO);
        TypeDTO statusDTO=imageDTO.new TypeDTO();
        statusDTO.setCode(mgmImage.getTask().getStatus());
        statusDTO.setDisplay(dictDAO.getNameByCodeAndDictTypeCode(mgmImage.getTask().getStatus(), DictTypeConsts.TASK_STATUS));
        taskDTO.setStatus(statusDTO);
        imageDTO.setTask(taskDTO);
        
        return imageDTO;
    }
    
    private ImageConf getImageConfDTO(KeySet keySet) {
    	ImageConf imageConf=new ImageConf();
    	imageConf.setCanset(keySet.getCanSet());
    	imageConf.setMustRestart(keySet.getMustRestart());
    	imageConf.setKey(keySet.getKey());
    	imageConf.setValue(keySet.getValue());
    	imageConf.setDefaultValue(keySet.getDefaultValue());
    	imageConf.setDescription(keySet.getDesc());
    	imageConf.setRange(keySet.getRange());
        return imageConf;
    }

    private MgmImageBody buildSaveImageBody(ImageForm softwareImageForm) {
        MgmImageBody mgmImageBody=new MgmImageBody();
        mgmImageBody.setType(softwareImageForm.getType());
        mgmImageBody.setSite_id(softwareImageForm.getSite_id());
        mgmImageBody.setArch(softwareImageForm.getArchitecture());
        mgmImageBody.setMajor(softwareImageForm.getVersion().getMajor());
        mgmImageBody.setMinor(softwareImageForm.getVersion().getMinor());
        mgmImageBody.setPatch(softwareImageForm.getVersion().getPatch());
        mgmImageBody.setBuild(softwareImageForm.getVersion().getBuild());
        mgmImageBody.setEnabled(softwareImageForm.getEnabled());
        mgmImageBody.setDesc(softwareImageForm.getDescription());
        mgmImageBody.setCreatedUser(getUsername());
        return mgmImageBody;
    }


}
