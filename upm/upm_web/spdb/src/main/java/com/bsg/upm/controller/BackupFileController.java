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
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.SiteConfigForm;
import com.bsg.upm.form.SiteForm;
import com.bsg.upm.query.BackupEndpointParam;
import com.bsg.upm.query.BackupFileParam;
import com.bsg.upm.query.BackupStrategyAddParam;
import com.bsg.upm.query.ClusterParam;
import com.bsg.upm.service.BackupFileService;
import com.bsg.upm.service.Result;
import com.bsg.upm.service.SiteService;

@Controller
@RequestMapping(value = "backupfiles")
@OperateLog(module = "数据库服务备份文件")
public class BackupFileController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BackupFileService backupFileService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result list(@UnderlineToCamel BackupFileParam backupFileParam, HttpServletResponse response) {
        Result result = null;
        try {
            result = backupFileService.list(backupFileParam);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list backup files exception:", e);
        }
        return result;
    }
    
    @RequestMapping(value = "/restore", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "备份还原")
    public Result restore(@RequestBody BackupFileParam backupFileParam, HttpServletResponse response) {
        Result result = null;
        try {
            result = backupFileService.restore(backupFileParam);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("restore backup files exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{backupfile_id}", method = RequestMethod.DELETE)
    @ResponseBody
    @OperateLog(action = "删除")
    public Result remove(@PathVariable("backupfile_id") String backupFileId, HttpServletResponse response) {
        Result result = null;
        try {
            result = backupFileService.remove(backupFileId);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_NO_CONTENT);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("remove backup files exception:", e);
        }
        return result;
    }
    
    @RequestMapping(value = "/strategy",method = RequestMethod.GET)
    @ResponseBody
    public Result listStrategy(@UnderlineToCamel BackupFileParam backupFileParam, HttpServletResponse response) {
        Result result = null;
        try {
            result = backupFileService.listStrategy(backupFileParam);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list backup files exception:", e);
        }
        return result;
    }
    
    @RequestMapping(value = "/strategy/{serv_group_id}",method = RequestMethod.POST)
    @ResponseBody
    @OperateLog(action = "备份策略新增")
    public Result addStrategy(@PathVariable("serv_group_id") String serverGroupId,@RequestBody BackupStrategyAddParam backupStrategyAddParam, HttpServletResponse response) {
        Result result = null;
        try {
            result = backupFileService.save(serverGroupId,backupStrategyAddParam);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list backupStrategy files exception:", e);
        }
        return result;
    }
    
    @RequestMapping(value = "/deleteStrategy/{backup_strategy_id}", method = RequestMethod.DELETE)
    @ResponseBody
    @OperateLog(action = "备份策略删除")
    public Result removeStrategy(@PathVariable("backup_strategy_id") String backupStrategyId, HttpServletResponse response) {
        Result result = null;
        try {
            result = backupFileService.removeStrategy(backupStrategyId);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_NO_CONTENT);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("remove backupStrategy files exception:", e);
        }
        return result;
    }
    
    //备份存储查询
    @RequestMapping(value = "/endpoint",method = RequestMethod.GET)
    @ResponseBody
    public Result listEndpoint(@UnderlineToCamel BackupFileParam backupFileParam, HttpServletResponse response) {
        Result result = null;
        try {
            result = backupFileService.listEndpoint(backupFileParam);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list backup files exception:", e);
        }
        return result;
    }
    
    //备份存储新增
    @RequestMapping(value = "/endpoint", method = RequestMethod.POST)
    @ResponseBody
    public Result saveEndpoint(@RequestBody BackupEndpointParam backupEndpointParam, HttpServletResponse response) {
        Result result = null;
        try {
            result = backupFileService.saveEndpoint(backupEndpointParam);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("restore backup files exception:", e);
        }
        return result;
    }
    
    //备份存储删除
    @RequestMapping(value = "/endpoint/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteEndpoint(@PathVariable("id") String id, HttpServletResponse response) {
        Result result = null;
        try {
            result = backupFileService.deleteEndpoint(id);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("restore backup files exception:", e);
        }
        return result;
    }
    //备份存储修改
    @RequestMapping(value = "/endpoint/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Result updateEndpoint(@PathVariable("id") String id, @RequestBody BackupEndpointParam backupEndpointParam, HttpServletResponse response) {
        Result result = null;
        try {
            result = backupFileService.updateEndpoint(id,backupEndpointParam);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("restore backup files exception:", e);
        }
        return result;
    }

}
