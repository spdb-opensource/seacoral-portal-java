package com.bsg.upm.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsg.upm.annotation.OperateLog;
import com.bsg.upm.annotation.UnderlineToCamel;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.NetworkingForm;
import com.bsg.upm.query.NetworkingParam;
import com.bsg.upm.service.NetworkingService;
import com.bsg.upm.service.Result;

@Controller
@RequestMapping(value = "networkings")
@OperateLog(module = "网段")
public class NetworkingController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private NetworkingService networkingService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result list(@UnderlineToCamel NetworkingParam networkingParam, HttpServletResponse response) {
        Result result = null;
        try {
            result = networkingService.list(networkingParam);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list networking exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{networking_id}", method = RequestMethod.GET)
    @ResponseBody
    public Result get(@PathVariable("networking_id") String networkingId, HttpServletResponse response) {
        Result result = null;
        try {
            result = networkingService.get(networkingId);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get networking exception:", e);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @OperateLog(action = "新增")
    public Result save(@RequestBody NetworkingForm networkingForm, HttpServletResponse response) {
        Result result = null;
        try {
            result = networkingService.save(networkingForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_CREATED);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("save networking exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{networking_id}", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "编辑")
    public Result update(@PathVariable("networking_id") String networkingId, @RequestBody NetworkingForm networkingForm,
            HttpServletResponse response) {
        Result result = null;
        try {
            result = networkingService.update(networkingId, networkingForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("update networking exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{networking_id}/enabled", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "启用")
    public Result enable(@PathVariable("networking_id") String networkingId, HttpServletResponse response) {
        Result result = null;
        try {
            result = networkingService.enabled(networkingId, true);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("enable networking exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{networking_id}/disabled", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "停用")
    public Result disable(@PathVariable("networking_id") String networkingId, HttpServletResponse response) {
        Result result = null;
        try {
            result = networkingService.enabled(networkingId, false);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("disable networking exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{networking_id}", method = RequestMethod.DELETE)
    @ResponseBody
    @OperateLog(action = "删除")
    public Result remove(@PathVariable("networking_id") String networkingId, HttpServletResponse response) {
        Result result = null;
        try {
            result = networkingService.remove(networkingId);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_NO_CONTENT);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("remove networking exception:", e);
        }
        return result;
    }
    
    @RequestMapping(value = "/mgmpath", method = RequestMethod.GET)
    @ResponseBody
    public Result getMgmPath(HttpServletResponse response) {
        Result result = null;
        try {
            result = networkingService.getMgmPath();
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get networking exception:", e);
        }
        return result;
    }
    
    /**
     * 调用CM的shell接口
     * @param pod_id
     * @param response
     * @return
     */
    @RequestMapping(value = "/shell/{site_id}/{pod_id}/{type}", method = RequestMethod.GET)
    @ResponseBody
    public Result toShell(@PathVariable("site_id") String site_id,@PathVariable("pod_id") String podId,
    		@PathVariable("type") String type,HttpServletResponse response) {
        Result result = null;
        try {
            result = networkingService.toShell(site_id,podId,type);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get networking exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/grafanaaddr", method = RequestMethod.GET)
    @ResponseBody
    public Result getGrafanaAddr(HttpServletResponse response) {
        Result result = null;
        try {
            result = networkingService.getGrafanaAddr();
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get GrafanaAddr exception:", e);
        }
        return result;
    }
    
    /**
     * java和cm健康检查
     * @return
     */
    @RequestMapping(value = "/statecheck", method = RequestMethod.GET)
    @ResponseBody
    public Result statecheck(@RequestHeader(value ="subscription_id" ,required=false) String subscription_id,
    		HttpServletResponse response) {
        Result result = null;
        try {
            result = networkingService.statecheck(subscription_id);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get statecheck exception:", e);
        }
        return result;
    }
}
