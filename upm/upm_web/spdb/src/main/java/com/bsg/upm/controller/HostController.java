package com.bsg.upm.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsg.upm.annotation.OperateLog;
import com.bsg.upm.annotation.UnderlineToCamel;
import com.bsg.upm.domain.UserDO;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.EventTemplateForm;
import com.bsg.upm.form.HostForm;
import com.bsg.upm.param.HostDeleteParam;
import com.bsg.upm.param.HostMonitorQueryParam;
import com.bsg.upm.param.HostMonitorRegisParam;
import com.bsg.upm.param.HostMonitorRegisParam1;
import com.bsg.upm.param.UserDAOParam;
import com.bsg.upm.query.HostParam;
import com.bsg.upm.query.ImageParam;
import com.bsg.upm.query.MonitorParam;
import com.bsg.upm.query.RemoteStorageParam;
import com.bsg.upm.service.HostService;
import com.bsg.upm.service.Result;

@Controller
@RequestMapping(value = "hosts")
@OperateLog(module = "主机")
public class HostController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HostService hostService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result list(@UnderlineToCamel HostForm hostForm,HttpServletResponse response) {
        Result result = null;
        try {
            result = hostService.list(hostForm);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list host exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{host_id}", method = RequestMethod.GET)
    @ResponseBody
    public Result get(@PathVariable("host_id") String hostId, HttpServletResponse response) {
        Result result = null;
        try {
            result = hostService.get(hostId);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get host exception:", e);
        }
        return result;
    }
    
    /** 查询agent是否注册
     * @param hostIp
     * @param response
     * @return
     */
    @RequestMapping(value = "/agents/{host_ip}/info", method = RequestMethod.GET)
    @ResponseBody
    public Result getAgents(@PathVariable("host_ip") String hostIp, HttpServletResponse response) {
        Result result = null;
        try {
            result = hostService.getAgents(hostIp);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get host exception:", e);
        }
        return result;
    }
    
    /** 查询host是否注册
     * @param hostId
     * @param response
     * @return
     */
    @RequestMapping(value = "/{host_id}/info", method = RequestMethod.GET)
    @ResponseBody
    public Result getAgentsHost(@PathVariable("host_id") String hostId, HttpServletResponse response) {
        Result result = null;
        try {
            result = hostService.getAgentsHost(hostId);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get host exception:", e);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @OperateLog(action = "注册")
    public Result save(@RequestBody HostForm hostForm, HttpServletResponse response) {
        Result result = null;
        try {
            result = hostService.save(hostForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_CREATED);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("save host exception:", e);
        }
        return result;
    }
    
    @RequestMapping(value = "/validation",method = RequestMethod.POST)
    @ResponseBody
    @OperateLog(action = "检查")
    public Result validation(@RequestBody HostForm hostForm,@UnderlineToCamel HostParam hostParam, HttpServletResponse response) {
        Result result = null;
        try {
            result = hostService.validation(hostForm,hostParam);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_CREATED);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("save host exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{host_id}", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "编辑")
    public Result update(@PathVariable("host_id") String hostId, @RequestBody HostForm hostForm,
            HttpServletResponse response) {
        Result result = null;
        try {
            result = hostService.update(hostId, hostForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("update host exception:", e);
        }
        return result;
    }


    @RequestMapping(value = "/{host_id}/enabled", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "启用")
    public Result enable(@PathVariable("host_id") String hostId, HttpServletResponse response) {
        Result result = null;
        try {
            result = hostService.enabled(hostId, true);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("enable host exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{host_id}/disabled", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "停用")
    public Result disable(@PathVariable("host_id") String hostId, HttpServletResponse response) {
        Result result = null;
        try {
            result = hostService.enabled(hostId, false);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("disable host exception:", e);
        }
        return result;
    }
    

    @RequestMapping(value = "/{host_id}/delete", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "删除")
    public Result remove(@PathVariable("host_id") String hostId, @RequestBody HostDeleteParam userForm, HttpServletResponse response) {
        Result result = null;
        try {
            result = hostService.remove(hostId,userForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_NO_CONTENT);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("remove host exception:", e);
        }
        return result;
    }
    
    /** 监控注册
     * @param hostMonitorRegisParam
     * @param response
     * @return
     */
    @RequestMapping(value = "/agents/install", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "监控注册")
    public Result monitorRegister(@RequestBody HostMonitorRegisParam hostMonitorRegisParam, HttpServletResponse response) {
        Result result = null;
        try {
            result = hostService.monitorRegister(hostMonitorRegisParam);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_NO_CONTENT);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("monitorRegister host exception:", e);
        }
        return result;
    }
    
    @RequestMapping(value = "/agents", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "监控注册")
    public Result monitorRegister1(@RequestBody HostMonitorRegisParam1 hostMonitorRegisParam, HttpServletResponse response) {
        Result result = null;
        try {
            result = hostService.monitorRegister1(hostMonitorRegisParam);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_NO_CONTENT);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("monitorRegister1 host exception:", e);
        }
        return result;
    }
    
    /** 监控注销
     * @param hostMonitorRegisParam
     * @param response
     * @return
     */
    @RequestMapping(value = "/agents/uninstall", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "监控注销")
    public Result uninstall(@RequestBody HostMonitorRegisParam hostMonitorRegisParam, HttpServletResponse response) {
        Result result = null;
        try {
            result = hostService.monitorCancel(hostMonitorRegisParam);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_NO_CONTENT);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("monitorRegister host exception:", e);
        }
        return result;
    }
    
    @RequestMapping(value = "/{host_id}/monitorCancel", method = RequestMethod.DELETE)
    @ResponseBody
    @OperateLog(action = "监控注销")
    public Result uninstall1(@PathVariable("host_id") String hostId, HttpServletResponse response) {
        Result result = null;
        try {
            result = hostService.monitorCancel1(hostId);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_NO_CONTENT);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("monitorRegister host exception:", e);
        }
        return result;
    }
    
    /** 主机监控查询
     * @param hostId
     * @param response
     * @return
     */
    @RequestMapping(value = "/{host_id}/memMonitor/{type}/{start_time}/{end_time}", method = RequestMethod.GET)
    @ResponseBody
    public Result getHostMemMonitor(@PathVariable("host_id") String hostId,@PathVariable("type") String type,@PathVariable("start_time") Long startTime,@PathVariable("end_time") Long endTime, HttpServletResponse response) {
        Result result = null;
        try {
        	 HostMonitorQueryParam hostMonitorQueryParam=new HostMonitorQueryParam();
        	 hostMonitorQueryParam.setStartTime(startTime);
        	 hostMonitorQueryParam.setEndTime(endTime);
            result = hostService.getHostMemMonitor(hostId,type,hostMonitorQueryParam);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get host exception:", e);
        }
        return result;
    }

}
