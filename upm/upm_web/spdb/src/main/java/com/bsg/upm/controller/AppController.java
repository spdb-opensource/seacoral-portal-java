package com.bsg.upm.controller;

import com.alibaba.fastjson.JSONObject;
import com.bsg.upm.annotation.OperateLog;
import com.bsg.upm.domain.JwtUser;
import com.bsg.upm.dto.AppDTO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.service.AppService;
import com.bsg.upm.service.LoginService;
import com.bsg.upm.util.JwtUtil;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "app")
@OperateLog(module = "权限")
public class AppController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private LoginService loginService;
    @Autowired
    private AppService appService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView home(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
//    	HttpSession session = request.getSession();
//    	UserDO userDO = (UserDO) session.getAttribute("user");



        JwtUser jwtUser = jwtUtil.getJwtUserFromTokenInCache(request);

        //TODO:后期改成在请求头里边获取token
//        String jwtToken = request.getHeader("JwtToken");
//        JwtUser jwtUser = jwtUtil.getJwtUserFromToken(jwtToken);

        try {
//            mv.addObject("userName", JSONObject.toJSONString(userDO.getName()));
//            mv.addObject("role", JSONObject.toJSONString(userDO.getRole().getName()));
            mv.addObject("userName", JSONObject.toJSONString(jwtUser.getName()));
            mv.addObject("role", JSONObject.toJSONString(jwtUser.getRole().getName()));
            mv.setViewName("home");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /home exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView logout(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            loginService.logout(request);
            mv.setViewName("cup_dba");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /home exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/forbidden", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView forbidden(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("forbidden");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /forbidden exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/site/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView sitesList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));

            mv.setViewName("views/site/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /site/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/site/detail/{site_id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceSiteDetail(@PathVariable("site_id") String siteId, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("siteId", siteId);
            mv.setViewName("views/site/detail");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/networking/update exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/site/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView sitesAdd(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/site/add");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /site/add exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/site/update", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView sitesUpdate(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/site/update");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /site/update exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView index(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<com.bsg.upm.service.AppService.AppDTO> result = new ArrayList<>();
        try {
            result = appService.listPage(request);
            mv.addObject("menuList", JSONObject.toJSONString(result));
            mv.setViewName("index");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /index exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView homepage(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/homepage/homepage");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /homepage exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/upcdb_homepage", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView upcdbHomepage(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/homepage/upcdb_homepage");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /homepage exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/backupStorage/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceBackupStorageList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.setViewName("views/resource/backup_storage/list");
            mv.addObject("btnPer", JSONObject.toJSONString(result));
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/backupStorage/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/backupStorage/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceBackupStorageAdd(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/resource/backup_storage/add");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/backupStorage/add exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/backupStorage/update", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceBackupStorageUpdate(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/resource/backup_storage/update");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/backupStorage/update exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/area/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceAreasList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/resource/area/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/area/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/area/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceAreasAdd(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/resource/area/add");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/area/add exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/area/update", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceAreasUpdate(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/resource/area/update");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/area/update exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/networking/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceNetworkingList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/resource/networking/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/networking/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/networking/detail/{networking_id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceNetworkingDetail(@PathVariable("networking_id") String networkingId, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("networkingId", networkingId);
            mv.setViewName("views/resource/networking/detail");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/networking/update exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/networking/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceNetworkingAdd(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/resource/networking/add");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/networking/add exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/networking/update", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceNetworkingUpdate(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/resource/networking/update");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/networking/update exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/networking/upload", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceNetworkingUpload(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/resource/networking/upload");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/networking/upload exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/port/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourcePortList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/resource/port/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/port/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/port/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourcePortAdd(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/resource/port/add");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/port/add exception:", e);
        }
        return mv;
    }


    @RequestMapping(value = "/resource/cluster/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceClusterList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/resource/cluster/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/cluster/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/cluster/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceClusterAdd(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/resource/cluster/add");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/cluster/add exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/cluster/update", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceClusterUpdate(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/resource/cluster/update");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/cluster/update exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/cluster/upload", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceClusterUpload(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/resource/cluster/upload");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/cluster/upload exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/softwareImage/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceSoftwareImageList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/resource/software_image/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/softwareImage/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/softwareImage/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceSoftwareImageAdd(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/resource/software_image/add");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/softwareImage/add exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/softwareImage/detail/{image_id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceImageDetail(@PathVariable("image_id") String imageId, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("imageId", JSONObject.toJSONString(imageId));
            mv.setViewName("views/resource/software_image/detail");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/software_image/detail exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/softwareImage/paramList", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceSoftwareImageParamList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        try {
            result = appService.listSubBtnApp(request, "paramList");
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/resource/software_image/paramList");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/softwareImage/paramList exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/softwareImage/updateParam", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceSoftwareImageUpdateParam(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/resource/software_image/updateParam");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/softwareImage/updateParam exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/host/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceHostList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/resource/host/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/host/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/host/monitor/{host_id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceHostMonitor(@PathVariable("host_id") String hostId, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("hostId", JSONObject.toJSONString(hostId));
            mv.setViewName("views/resource/host/monitor");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/host/monitor exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/host/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceHostAdd(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/resource/host/add");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/host/add exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/host/detail/{host_id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceHostDetail(@PathVariable("host_id") String hostId, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("hostId", JSONObject.toJSONString(hostId));
            mv.setViewName("views/resource/host/detail");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/host/detail exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/host/mgm", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceHostMgm(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/resource/host/mgm");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/host/mgm exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/host/out", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceHostOut(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/resource/host/out");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/host/out exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/host/update", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceHostUpdate(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/resource/host/update");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/host/update exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/host/upload", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceHostUpload(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/resource/host/upload");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/host/upload exception:", e);
        }
        return mv;
    }


    @RequestMapping(value = "/resource/san/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceSanList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/resource/san/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/san/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/san/detail/{san_id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceSanDetail(@PathVariable("san_id") String sanId, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("sanId", sanId);
            mv.setViewName("views/resource/san/detail");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/san/detail exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/san/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceSanAdd(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/resource/san/add");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/san/add exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/san/rgadd", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceSanRgadd(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/resource/san/rgadd");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/san/rgadd exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/san/rgmanage", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView resourceSanRgmanage(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        try {
            result = appService.listSubBtnApp(request, "rgmanage");
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/resource/san/rgmanage");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/san/rgmanage exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/workorder/mysql/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView workorderMysqlAdd(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/workorder/mysql/add");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /workorder/mysql/add exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/workorder/mysql/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView workorderMysqlList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/workorder/mysql/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /workorder/mysql/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/workorder/mysql/examine", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView workorderMysqlExamine(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/workorder/mysql/examine");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /workorder/mysql/examine exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/workorder/mysql/execute", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView workorderMysqlExecute(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/workorder/mysql/execute");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /workorder/mysql/execute exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/workorder/mysql/detail", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView workorderMysqlDetail(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/workorder/mysql/detail");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /workorder/mysql/detail exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/workorder/mysql/update", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView workorderMysqlUpdate(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/workorder/mysql/update");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /workorder/mysql/update exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/service/mysql/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/manage", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlManageI(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listSubBtnApp(request, "manageUpsql");
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/service/mysql/manage");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/manage exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource/software_image/paramList", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView imageParamListManageI(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listSubBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/resource/software_image/paramList");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /resource/software_image/paramList exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/manage/{mysql_id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlManage(@PathVariable("mysql_id") long mysqlId, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listSubBtnApp(request, "manageUpsql");
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.addObject("mysqlId", mysqlId);
            mv.setViewName("views/service/mysql/manage");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/manage exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/backup", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlBackup(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/backup");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/backup exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/change", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlChange(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/change");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/change exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/changeVersion", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlChangeVersion(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/changeVersion");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/changeVersion exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/transfer", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlTransfer(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/transfer");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/transfer exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/clone", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlClone(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/clone");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/clone exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/enlarge", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlEnlarge(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/enlarge");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/enlarge exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/mysqlTab", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlUpsqlTab(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        try {
            result = appService.listsSubBtnApp(request, "mysqlTab");
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/service/mysql/mysqlTab");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/mysqlTab exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/upproxyTab", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlUpproxyTab(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        try {
            result = appService.listsSubBtnApp(request, "upproxyTab");
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/service/mysql/upproxyTab");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/upproxyTab exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/swmTab", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlSwmTab(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        try {
            result = appService.listsSubBtnApp(request, "swmTab");
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/service/mysql/swmTab");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/swmTab exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/graphTab", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlGraphTab(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/graphTab");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/graphTab exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/paramTab", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlParamTab(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        try {
            result = appService.listsSubBtnApp(request, "paramTab");
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/service/mysql/paramTab");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/paramTab exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/cfgContentsTab", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlCfgContentsTab(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/cfgContentsTab");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/cfgContentsTab exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/databaseTab", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlDatabaseTab(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        try {
            result = appService.listsSubBtnApp(request, "databaseTab");
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/service/mysql/databaseTab");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/databaseTab exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/databaseTab/detail/{name}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView dataBaseDetail(@PathVariable("name") String name, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("name", JSONObject.toJSONString(name));
            mv.setViewName("views/service/mysql/dataBaseDetail");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /views/service/mysql/dataBaseDetail exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/userTab", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlUserTab(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        try {
            result = appService.listsSubBtnApp(request, "userTab");
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/service/mysql/userTab");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/userTab exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/backupStrategyTab", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlBackupStrategyTab(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        try {
            result = appService.listsSubBtnApp(request, "backupStrategyTab");
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/service/mysql/backupStrategyTab");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/backupStrategyTab exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/backupListTab", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlBackupListTab(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        try {
            result = appService.listsSubBtnApp(request, "backupListTab");
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/service/mysql/backupListTab");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/backupListTab exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/monitorTab", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlMonitorTab(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/monitorTab");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/monitorTab exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/userDirectTab", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlUserDirectTab(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        try {
            result = appService.listsSubBtnApp(request, "userDirectTab");
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/service/mysql/userDirectTab");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/userDirectTab exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/unitBackup", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlUnitBackup(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/unitBackup");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/unitBackup exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/restore", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlRestore(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/restore");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/restore exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/roleChangeSlave/{slave_Id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlroleChangeSlave(@PathVariable("slave_Id") String slaveId, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.addObject("slaveId", slaveId);
            mv.setViewName("views/service/mysql/roleChangeSlave");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/roleChangeSlave exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/roleChange", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlRoleChange(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/roleChange");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/roleChange exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/migrate", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlMigrate(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/migrate");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/migrate exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/rebuild", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlRebuild(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/rebuild");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/rebuild exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/changeUpsqlVersion", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlChangeUpsqlVersion(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/changeUpsqlVersion");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/changeUpsqlVersion exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/slowlog", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqSlowlog(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/slowlog");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/slowlog exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/param", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlParam(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/param");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/param exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/addDatabase", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlAddDatabase(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/addDatabase");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/addDatabase exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/addUser", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlAddUser(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/addUser");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/addUser exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/editUser", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlEditUser(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/editUser");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/editUser exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/resetPassword", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlResetPassword(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/resetPassword");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/editUser exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/addUserDirect", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlAddUserDirect(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/addUserDirect");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/addUserDirect exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/addBackupStrategy", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlAddBackupStrategy(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/addBackupStrategy");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/addBackupStrategy exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/mysql/editBackupStrategy", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpsqlEditBackupStrategy(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/mysql/editBackupStrategy");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/mysql/editBackupStrategy exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/redis/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceRedisList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/service/redis/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/redis/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/redis/manage/{upredis_id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceRedisManage(@PathVariable("upredis_id") long upredisId, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listSubBtnApp(request, "manageUpredis");
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("upredisId", upredisId);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/service/redis/manage");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/redis/manage exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/redis/change", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceRedisChange(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/redis/change");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/redis/change exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/redis/changeArchVersion", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceRedisChangeArchVersion(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/redis/changeArchVersion");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/redis/changeArchVersion exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/redis/changeVersion", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceRedisChangeVersion(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/redis/changeVersion");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/redis/changeVersion exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/redis/enlarge", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceRedisEnlarge(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/redis/enlarge");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/redis/enlarge exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/redis/transfer", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceRedisTransfer(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/redis/transfer");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/redis/transfer exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/redis/graphTab", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceRedisGraphTab(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/redis/graphTab");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/redis/graphTab exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/redis/upredisTab", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceRedisUpredisTab(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        try {
            result = appService.listsSubBtnApp(request, "upredisTab");
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/service/redis/upredisTab");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/redis/upredisTab exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/redis/urproxyTab", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceRedisUrproxyTab(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        try {
            result = appService.listsSubBtnApp(request, "urproxyTab");
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/service/redis/urproxyTab");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/redis/urproxyTab exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/redis/sentinelTab", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceRedisSentinelTab(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        try {
            result = appService.listsSubBtnApp(request, "sentinelTab");
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/service/redis/sentinelTab");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/redis/sentinelTab exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/redis/paramTab", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceRedisParamTab(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        try {
            result = appService.listsSubBtnApp(request, "paramTab");
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/service/redis/paramTab");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/redis/paramTab exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/redis/monitorTab", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceRedisMonitorTab(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/redis/monitorTab");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/redis/monitorTab exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/redis/resetPassword", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceRedisResetPassword(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/redis/resetPassword");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/redis/resetPassword exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/redis/rebuild", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceRedisRebuild(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/redis/rebuild");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/redis/rebuild exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/redis/migrate", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceRedisMigrate(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/redis/migrate");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/redis/migrate exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/redis/changeRedisVersion", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceRedisChangeRedisVersion(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/redis/changeRedisVersion");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/redis/changeRedisVersion exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/redis/param", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceRedisParam(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/service/redis/param");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/redis/param exception:", e);
        }
        return mv;
    }


    @RequestMapping(value = "/monitor/site/resource", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView monitorSiteResource(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/monitor/site/resource");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /monitor/site/resource exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/monitor/site/host", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView monitorSiteHost(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/monitor/site/host");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /monitor/site/host exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/monitor/site/unit", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView monitorSiteUnit(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/monitor/site/unit");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /monitor/site/unit exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/monitor/host/resource", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView monitorHostResource(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/monitor/host/resource");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /monitor/host/resource exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/monitor/host/metricclass", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView monitorHostMetricclass(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/monitor/host/metricclass");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /monitor/host/metricclass exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/monitor/host/monitor", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView monitorHostMonitor(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/monitor/host/monitor");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /monitor/host/monitor exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/monitor/component/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView monitorComponentList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/monitor/component/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /monitor/component/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/monitor/event/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView monitorEventList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/monitor/event/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /monitor/event/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/monitor/task/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView monitorTaskList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/monitor/task/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /monitor/task/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/system/user/manager", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView systemUserManager(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/system/user/manager");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /system/user/manager exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/system/user/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView systemUserAdd(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/system/user/add");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /system/user/add exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/system/user/edit", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView systemUserEdit(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/system/user/edit");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /system/user/edit exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/system/user/editPassword", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView systemUserSync(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/system/user/editPassword");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /system/user/editPassword exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/system/role/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView systemRoleList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/system/role/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /system/role/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/system/role/manage", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView systemRoleManage(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/system/role/manage");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /system/role/manage exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/system/role/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView systemRoleAdd(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/system/role/add");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /system/role/add exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/system/role/update", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView systemRoleUpdate(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/system/role/update");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /system/role/update exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/system/group/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView systemGroupList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/system/group/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /system/group/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/system/group/manage", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView systemGroupManage(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/system/group/manage");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /system/group/manage exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/system/group/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView systemGroupAdd(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/system/group/add");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /system/group/add exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/system/group/update", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView systemGroupUpdate(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/system/group/update");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /system/group/update exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/workorder/sys/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView workorderSysList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
//            HttpSession session = request.getSession();
//            UserDO userDO = (UserDO) session.getAttribute("user");
//            mv.addObject("roleId", userDO.getRoleId());
            JwtUser jwtUser = jwtUtil.getJwtUserFromTokenInCache(request);
            mv.addObject("roleId", jwtUser.getRoleId());
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/workorder/sys/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /workorder/sys/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/workorder/sys/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView workorderSysAdd(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/workorder/sys/add");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /workorder/sys/add exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/workorder/sys/update", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView workorderSysUpdate(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/workorder/sys/update");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /workorder/sys/update exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/workorder/subsys/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView workorderSubsysList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/workorder/subsys/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /workorder/subsys/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/workorder/subsys/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView workorderSubsysAdd(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/workorder/subsys/add");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /workorder/subsys/add exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/workorder/subsys/update", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView workorderSubsysUpdate(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/workorder/subsys/update");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /workorder/subsys/update exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/system/dicts/dictionary", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView systemDictsDictionary(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/system/dicts/dictionary");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /system/dicts/dictionary exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/system/dicts/edit", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView systemDictsEdit(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/system/dicts/edit");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /system/dicts/edit exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/system/dicts/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView systemDictsAdd(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/system/dicts/add");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /system/dicts/add exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/system/event/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView systemEventList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/system/event/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /system/event/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/system/event/manager", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView systemEventManager(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/system/event/manager");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /system/event/manager exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/system/event/opelatelog", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView systemEventOpelatelog(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/system/event/opelatelog");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /system/event/opelatelog exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/workorder/scale/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView workorderScaleList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/workorder/scale/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /workorder/scale/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/workorder/scale/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView workorderScaleAdd(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/workorder/scale/add");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /workorder/scale/add exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/workorder/scale/update", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView workorderScaleUpdate(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/workorder/scale/update");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /workorder/scale/update exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/workorder/combogroup/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView workorderCombogroupList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/workorder/combogroup/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /workorder/combogroup/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/workorder/combogroup/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView workorderCombogroupAdd(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/workorder/combogroup/add");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /workorder/combogroup/add exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/workorder/combogroup/update", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView workorderCombogroupUpdate(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("views/workorder/combogroup/update");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /workorder/combogroup/update exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/workorder/ordercfgs/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView workorderOrdercfgsList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/workorder/ordercfgs/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /workorder/ordercfgs/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/workorder/upkafka/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView workorderUpkafkaAdd(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/workorder/upkafka/add");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /workorder/upkafka/add exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/workorder/moray/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView workorderMorayAdd(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/workorder/moray/add");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /workorder/moray/add exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/upkafka/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceUpkafkaList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/service/upkafka/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/upkafka/list exception:", e);
        }
        return mv;
    }

    @RequestMapping(value = "/service/moray/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView serviceMorayList(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        List<AppDTO> result = new ArrayList<>();
        String requestUrl = request.getServletPath();
        try {
            result = appService.listBtnApp(request, requestUrl);
            mv.addObject("requestUrl", requestUrl);
            mv.addObject("btnPer", JSONObject.toJSONString(result));
            mv.setViewName("views/service/moray/list");
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error("get /service/moray/list exception:", e);
        }
        return mv;
    }

}
