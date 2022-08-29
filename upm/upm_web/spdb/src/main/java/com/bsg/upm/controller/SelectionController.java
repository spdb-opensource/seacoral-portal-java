package com.bsg.upm.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsg.upm.annotation.UnderlineToCamel;
import com.bsg.upm.constant.DictTypeConsts;
import com.bsg.upm.dto.ClusterDTO;
import com.bsg.upm.dto.DictDTO;
import com.bsg.upm.dto.HostDTO;
import com.bsg.upm.dto.RemoteStorageDTO;
import com.bsg.upm.dto.SanVendorDTO;
import com.bsg.upm.dto.SiteDTO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.HostForm;
import com.bsg.upm.query.ClusterParam;
import com.bsg.upm.query.OrderGroupParam;
import com.bsg.upm.query.RemoteStorageParam;
import com.bsg.upm.service.HostService;
import com.bsg.upm.service.Result;
import com.bsg.upm.service.SelectionService;

@Controller
@RequestMapping(value = "selections")
public class SelectionController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SelectionService selectionService;
    
    @Autowired
    private HostService hostService;


    @RequestMapping(value = "areas", method = RequestMethod.GET)
    @ResponseBody
    public Result listArea(HttpServletResponse response) {
        Result result = null;
        try {
            result = selectionService.listArea();
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list cluster exception:", e);
        }
        return result;
    }
    
    
    @RequestMapping(value = "data_scopes", method = RequestMethod.GET)
    @ResponseBody
    public List<DictDTO> listDataScope() {
        return selectionService.list(DictTypeConsts.DATA_SCOPE);
    }

    @RequestMapping(value = "auth_types", method = RequestMethod.GET)
    @ResponseBody
    public List<DictDTO> listAuthType() {
        return selectionService.list(DictTypeConsts.AUTH_TYPE);
    }

    @RequestMapping(value = "regions", method = RequestMethod.GET)
    @ResponseBody
    public List<DictDTO> listRegion() {
        return selectionService.list(DictTypeConsts.REGION);
    }

    @RequestMapping(value = "backup_storage_types", method = RequestMethod.GET)
    @ResponseBody
    public List<DictDTO> listBackupStorageType() {
        return selectionService.list(DictTypeConsts.BACKUP_STORAGE_TYPE);
    }

    @RequestMapping(value = "dir_types", method = RequestMethod.GET)
    @ResponseBody
    public List<DictDTO> listDirType() {
        return selectionService.list(DictTypeConsts.DIR_TYPE);
    }

    @RequestMapping(value = "character_sets", method = RequestMethod.GET)
    @ResponseBody
    public List<DictDTO> listCharacterSets() {
        return selectionService.list(DictTypeConsts.CHARACTER_SETS);
    }

    @RequestMapping(value = "serv_user_models", method = RequestMethod.GET)
    @ResponseBody
    public List<DictDTO> listServUserModel() {
        return selectionService.list(DictTypeConsts.SERV_USER_MODEL);
    }

    @RequestMapping(value = "serv_user_auth_types", method = RequestMethod.GET)
    @ResponseBody
    public List<DictDTO> listServUserAuthTyps() {
        return selectionService.list(DictTypeConsts.SERV_USER_AUTH_TYPE);
    }

    @RequestMapping(value = "backup_types", method = RequestMethod.GET)
    @ResponseBody
    public List<DictDTO> listBackupType() {
        return selectionService.list(DictTypeConsts.BACKUP_TYPE);
    }

    @RequestMapping(value = "mail_protocols", method = RequestMethod.GET)
    @ResponseBody
    public List<DictDTO> listMailProtocol() {
        return selectionService.list(DictTypeConsts.MAIL_PROTOCOL);
    }

    @RequestMapping(value = "migrate_strategys", method = RequestMethod.GET)
    @ResponseBody
    public List<DictDTO> listMigrateStrategy() {
        return selectionService.list(DictTypeConsts.MIGRATE_STRATEGY);
    }

    @RequestMapping(value = "hosts/{cluster_id}/{enabled}", method = RequestMethod.GET)
    @ResponseBody
    public Result listMigrateStrategy(@PathVariable("cluster_id") String clusterId,@PathVariable("enabled") String enabled) {
    	HostForm hostForm=new HostForm();
    	if(enabled.equals("enabled")) {
    		hostForm.setEnabled(true);
    	}else {
    		hostForm.setEnabled(false);
    	}
    	hostForm.setClusterId(clusterId);
    	return hostService.list(hostForm);
    }
    
    @RequestMapping(value = "rebuild_strategys", method = RequestMethod.GET)
    @ResponseBody
    public List<DictDTO> listRebuildStrategy() {
        return selectionService.list(DictTypeConsts.REBUILD_STRATEGY);
    }
    
    @RequestMapping(value = "san_types", method = RequestMethod.GET)
    @ResponseBody
    public List<DictDTO> listSanType() {
        return selectionService.list(DictTypeConsts.REMOTE_STORAGE_TYPE);
    }
    
    @RequestMapping(value = "san_rg_types", method = RequestMethod.GET)
    @ResponseBody
    public List<DictDTO> listSanRgType() {
        return selectionService.list(DictTypeConsts.REMOTE_STORAGE_POOL_TYPE);
    }
    
    @RequestMapping(value = "performance_types", method = RequestMethod.GET)
    @ResponseBody
    public List<DictDTO> listPerformanceType() {
        return selectionService.list(DictTypeConsts.PERFORMANCE);
    }
    
    @RequestMapping(value = "topologie_types", method = RequestMethod.GET)
    @ResponseBody
    public List<DictDTO> listTopologieType() {
        return selectionService.list(DictTypeConsts.NETWORKING_TOPOLOGY_TYPE);
    }
    
    @RequestMapping(value = "site_types", method = RequestMethod.GET)
    @ResponseBody
    public List<DictDTO> listSiteType() {
        return selectionService.list(DictTypeConsts.SITE_TYPE);
    }

    @RequestMapping(value = "sites", method = RequestMethod.GET)
    @ResponseBody
    public List<SiteDTO> listSite() {
        return selectionService.listSite(selectionService.getUsername());
    }
    
    @RequestMapping(value = "clusters", method = RequestMethod.GET)
    @ResponseBody
    public List<ClusterDTO> listCluster(@UnderlineToCamel ClusterParam clusterParam) {
        return selectionService.listCluster(clusterParam);
    }
    
    @RequestMapping(value = "san_vendors", method = RequestMethod.GET)
    @ResponseBody
    public List<SanVendorDTO> listSanVendor() {
        return selectionService.listSanVendor();
    }

    @RequestMapping(value = "sans", method = RequestMethod.GET)
    @ResponseBody
    public List<RemoteStorageDTO> listCluster(@UnderlineToCamel RemoteStorageParam sanParam) {
        return selectionService.listSan(sanParam);
    }

    @RequestMapping(value = "apps", method = RequestMethod.GET)
    @ResponseBody
    public List<com.bsg.upm.service.SelectionService.AppDTO> listApp() {
        return selectionService.listApp();
    }
    
    @RequestMapping(value = "image_types", method = RequestMethod.GET)
    @ResponseBody
    public List<DictDTO> listImageType() {
        return selectionService.list(DictTypeConsts.IMAGE_TYPE);
    }
    
    @RequestMapping(value = "performance", method = RequestMethod.GET)
    @ResponseBody
    public List<DictDTO> listPerformance() {
        return selectionService.list(DictTypeConsts.PERFORMANCE);
    }
    
    @RequestMapping(value = "ntp_server", method = RequestMethod.GET)
    @ResponseBody
    public List<DictDTO> listNtpServer() {
        return selectionService.list(DictTypeConsts.NTP_SERVER);
    }
    
    @RequestMapping(value = "enabled", method = RequestMethod.GET)
    @ResponseBody
    public List<DictDTO> listEnabled() {
        return selectionService.list(DictTypeConsts.ENABLED);
    }
}
