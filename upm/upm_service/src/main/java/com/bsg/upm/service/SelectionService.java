package com.bsg.upm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.constant.DictTypeConsts;
import com.bsg.upm.domain.AppDO;
import com.bsg.upm.domain.DictDO;
import com.bsg.upm.domain.RemoteStorageVendorDO;
import com.bsg.upm.domain.SanDO;
import com.bsg.upm.domain.SanVendorDO;
import com.bsg.upm.dto.ClusterDTO;
import com.bsg.upm.dto.DictDTO;
import com.bsg.upm.dto.RemoteStorageDTO;
import com.bsg.upm.dto.SanVendorDTO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.param.SanDAOParam;
import com.bsg.upm.query.ClusterParam;
import com.bsg.upm.query.RemoteStorageParam;
import com.bsg.upm.util.DateUtils;

@Service
public class SelectionService extends BaseService {
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private ClusterService clusterService;

    public List<DictDTO> list(String dictTypeCode) {
        List<DictDTO> dictDTOs = new ArrayList<>();
        try {
            List<DictDO> dictDOs = dictDAO.listByDictTypeCode(dictTypeCode);
            for (DictDO dictDO : dictDOs) {
                DictDTO dictDTO = new DictDTO();
                dictDTOs.add(dictDTO);

                BeanUtils.copyProperties(dictDO, dictDTO);
                dictDTO.setGmtCreate(DateUtils.dateTimeToString(dictDO.getGmtCreate()));
                dictDTO.setGmtModified(DateUtils.dateTimeToString(dictDO.getGmtModified()));
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return dictDTOs;
    }

    @SuppressWarnings("unchecked")
	@Transactional
    public List<com.bsg.upm.dto.SiteDTO> listSite(String username) {
    	
    	try {
    		Result result = siteService.list();
    		if (result.getCode() == Result.SUCCESS) {
    			return (List<com.bsg.upm.dto.SiteDTO>) result.getData();
    		} else {
    			logger.info("站点select结果：" + result);
    			return null;
    		}
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    public Result listArea() {
        List<DictDTO> dictDTOs=new ArrayList<>();
        try {
            List<DictDO> dictDOs=dictDAO.listByDictTypeCode(DictTypeConsts.AREA);
            for (DictDO dictDO : dictDOs) {
                DictDTO dictDTO=new DictDTO();
                dictDTOs.add(dictDTO);
                dictDTO.setCode(dictDO.getCode());
                dictDTO.setName(dictDO.getName());
                dictDTO.setSequence(dictDO.getSequence());
            }
            return Result.success(dictDTOs);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @SuppressWarnings("unchecked")
	@Transactional
    public List<ClusterDTO> listCluster(ClusterParam clusterParam) {
    	
    	try {
    		Result result = clusterService.list(clusterParam);
    		
    		if (result.getCode() == Result.SUCCESS) {
    			return  (List<ClusterDTO>) result.getData();
    		} else {
    			logger.info("集群select结果：" + result);
    			return null;
    		}
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    @Transactional
    public List<SanVendorDTO> listSanVendor() {
        List<SanVendorDTO> sanVendorDTOs = new ArrayList<>();
        try {
            List<RemoteStorageVendorDO> remoteStorageVendorDOs = remoteStorageVendorDAO.list();
            for (RemoteStorageVendorDO remoteStorageVendorDO : remoteStorageVendorDOs) {
                SanVendorDTO sanVendorDTO = new SanVendorDTO();
                sanVendorDTOs.add(sanVendorDTO);

                BeanUtils.copyProperties(remoteStorageVendorDO, sanVendorDTO);
                sanVendorDTO.setName(remoteStorageVendorDO.getName() + "(" + remoteStorageVendorDO.getVersion() + ")");
                sanVendorDTO.setId(remoteStorageVendorDO.getId());
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return sanVendorDTOs;
    }

    @Transactional
    public List<RemoteStorageDTO> listSan(RemoteStorageParam sanParam) {
        List<RemoteStorageDTO> sanDTOs = new ArrayList<>();
        try {
            SanDAOParam sanDAOParam = new SanDAOParam();
            BeanUtils.copyProperties(sanParam, sanDAOParam);

            List<SanDO> sanDOs = sanDAO.listBase(sanDAOParam);
            for (SanDO sanDO : sanDOs) {
                RemoteStorageDTO sanDTO = new RemoteStorageDTO();
                sanDTOs.add(sanDTO);

                BeanUtils.copyProperties(sanDO, sanDTO);
//                sanDTO.setGmtCreate(DateUtils.dateTimeToString(sanDO.getGmtCreate()));
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return sanDTOs;
    }

    public class SiteDTO {
        private Long id;
        private String name;
        private List<AreaDTO> areas = new ArrayList<>();

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<AreaDTO> getAreas() {
            return areas;
        }

        public void setAreas(List<AreaDTO> areas) {
            this.areas = areas;
        }

        @Override
        public String toString() {
            return "SiteDTO [id=" + id + ", name=" + name + ", areas=" + areas + "]";
        }

    }

    public class AreaDTO {
        private Long id;
        private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "AreaDTO [id=" + id + ", name=" + name + "]";
        }

    }

    @Transactional
    public List<AppDTO> listApp() {
        List<AppDTO> appDTOs = new ArrayList<>();
        try {
        	List<AppDO> appDOs= appDAO.list(null);
        	
            for(AppDO appDO:appDOs){
            	if(appDO.getType().equals("menu")){
            		AppDTO appDTO=new AppDTO();
                	appDTOs.add(appDTO);
                	
                	appDTO.setId(appDO.getId());
                	appDTO.setName(appDO.getName());
                	appDTO.setType(appDO.getType());
                	List<AppDTO> subAppDTOs=new ArrayList<>();
                	appDTO.setSubApps(subAppDTOs);
                	for(AppDO subAppDO:appDOs){
                		if(subAppDO.getPid().equals(appDO.getId()) && subAppDO.getType(). equals("page")){
                			AppDTO subAppDTO=new AppDTO();
                			subAppDTOs.add(subAppDTO);
                			
                			subAppDTO.setId(subAppDO.getId());
                			subAppDTO.setName(subAppDO.getName());
                			subAppDTO.setType(subAppDO.getType());
                			List<AppDTO> btnDTOs=new ArrayList<>();
                			subAppDTO.setSubApps(btnDTOs);
                			for(AppDO btnDO:appDOs){
                        		if(btnDO.getPid().equals(subAppDO.getId()) && btnDO.getType().equals("button")){
                        			AppDTO btnDTO=new AppDTO();
                        			btnDTOs.add(btnDTO);
                        			
                        			btnDTO.setId(btnDO.getId());
                        			btnDTO.setName(btnDO.getName());
                        			btnDTO.setType(btnDO.getType());
                        			
                        			List<AppDTO> subBtnDTOs=new ArrayList<>();
                        			btnDTO.setSubApps(subBtnDTOs);
                        			
                        			for(AppDO subBtnDO:appDOs){
                                		if(subBtnDO.getPid().equals(btnDTO.getId()) && subBtnDO.getType().equals("subButton")){
                                			AppDTO subBtnDTO=new AppDTO();
                                			subBtnDTOs.add(subBtnDTO);
                                			
                                			subBtnDTO.setId(subBtnDO.getId());
                                			subBtnDTO.setName(subBtnDO.getName());
                                			subBtnDTO.setType(subBtnDO.getType());
                                			
                                			List<AppDTO> sSubBtnDTOs=new ArrayList<>();
                                			subBtnDTO.setSubApps(sSubBtnDTOs);
                                			
                                			for(AppDO sSubBtnDO:appDOs){
                                        		if(sSubBtnDO.getPid().equals(subBtnDTO.getId()) && sSubBtnDO.getType().equals("sSubButton")){
                                        			AppDTO sSubBtnDTO=new AppDTO();
                                        			sSubBtnDTOs.add(sSubBtnDTO);
                                        			
                                        			sSubBtnDTO.setId(sSubBtnDO.getId());
                                        			sSubBtnDTO.setName(sSubBtnDO.getName());
                                        			sSubBtnDTO.setType(sSubBtnDO.getType());
                                        		}
                                        	}
                                		}
                                	}
                        		}
                        	}
                		}
                	}
            	}
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return appDTOs;
    }
    public class AppDTO {
        private Long id;
        private String name;
        private String type;
        private List<AppDTO> subApps = new ArrayList<>();
        

        public Long getId() {
			return id;
		}
		


		public void setId(Long id) {
			this.id = id;
		}
		


		public String getName() {
			return name;
		}
		


		public void setName(String name) {
			this.name = name;
		}
		


		public String getType() {
			return type;
		}
		


		public void setType(String type) {
			this.type = type;
		}
		


		public List<AppDTO> getSubApps() {
			return subApps;
		}
		


		public void setSubApps(List<AppDTO> subApps) {
			this.subApps = subApps;
		}
		


		@Override
        public String toString() {
            return "AppDTO [id=" + id + ", name=" + name + ", type=" + type + ", subApps=" + subApps + "]";
        }

    }

}
