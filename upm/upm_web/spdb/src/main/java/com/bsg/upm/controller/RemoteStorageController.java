package com.bsg.upm.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsg.upm.annotation.OperateLog;
import com.bsg.upm.annotation.UnderlineToCamel;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.RemoteStoragePoolForm;
import com.bsg.upm.form.RemoteStorageForm;
import com.bsg.upm.query.RemoteStorageParam;
import com.bsg.upm.service.RemoteStoragePoolService;
import com.bsg.upm.service.RemoteStorageService;
import com.bsg.upm.service.Result;

@Controller
@RequestMapping(value = "/storages/remotes")
@OperateLog(module = "SAN")
public class RemoteStorageController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RemoteStorageService remoteStorageService;

    @Autowired
    private RemoteStoragePoolService remoteStoragePoolService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result list(@UnderlineToCamel RemoteStorageParam remoteStorageParam, HttpServletResponse response) {
        Result result = null;
        try {
            result = remoteStorageService.list(remoteStorageParam);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list san exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{storage_remote_id}", method = RequestMethod.GET)
    @ResponseBody
    public Result get(@PathVariable("storage_remote_id") String storageRemoteId, HttpServletResponse response) {
        Result result = null;
        try {
            result = remoteStorageService.get(storageRemoteId);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get san exception:", e);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @OperateLog(action = "新增")
    public Result save(@RequestBody RemoteStorageForm remoteStorageForm, HttpServletResponse response) {
        Result result = null;
        try {
            result = remoteStorageService.save(remoteStorageForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_CREATED);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("save san exception:", e);
        }
        return result;
    }
    
    @RequestMapping(value = "/{storage_remote_id}/enabled", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "启用")
    public Result enable(@PathVariable("storage_remote_id") String storageRemoteId, HttpServletResponse response) {
        Result result = null;
        try {
            result = remoteStorageService.enabled(storageRemoteId, true);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("enable remote storage exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{storage_remote_id}/disabled", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "停用")
    public Result disable(@PathVariable("storage_remote_id") String storageRemoteId, HttpServletResponse response) {
        Result result = null;
        try {
            result = remoteStorageService.enabled(storageRemoteId, false);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("disable san exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{storage_remote_id}", method = RequestMethod.DELETE)
    @ResponseBody
    @OperateLog(action = "删除")
    public Result remove(@PathVariable("storage_remote_id") String storageRemoteId, HttpServletResponse response) {
        Result result = null;
        try {
            result = remoteStorageService.remove(storageRemoteId);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_NO_CONTENT);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("remove san exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{storage_remote_id}/pools", method = RequestMethod.GET)
    @ResponseBody
    public Result listRg(@PathVariable("storage_remote_id") String storageRemoteId, HttpServletResponse response) {
        Result result = null;
        try {
            result = remoteStoragePoolService.list(storageRemoteId);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list san exception:", e);
        }
        return result;
    }
    
    @RequestMapping(value = "/{storage_remote_id}/pools", method = RequestMethod.POST)
    @ResponseBody
    @OperateLog(module = "RG", action = "新增")
    public Result saveRg(@PathVariable("storage_remote_id") String storageRemoteId, @RequestBody RemoteStoragePoolForm remoteStoragePoolForms, HttpServletResponse response) {
        Result result = null;
        try {
            result = remoteStoragePoolService.save(storageRemoteId, remoteStoragePoolForms);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_CREATED);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("save rg exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{storage_remote_id}/pools/{pool_code}/enabled", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(module = "RG", action = "启用")
    public Result enableRG(@PathVariable("storage_remote_id") String storageRemoteId, @PathVariable("pool_code") String poolCode,
            HttpServletResponse response) {
        Result result = null;
        try {
            result = remoteStoragePoolService.enabled(storageRemoteId, poolCode, true);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("enable rg exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{storage_remote_id}/pools/{pool_code}/disabled", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(module = "RG", action = "停用")
    public Result disableRG(@PathVariable("storage_remote_id") String storageRemoteId, @PathVariable("pool_code") String poolCode,
            HttpServletResponse response) {
        Result result = null;
        try {
            result = remoteStoragePoolService.enabled(storageRemoteId, poolCode, false);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("disable rg exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{storage_remote_id}/pools/{pool_code}", method = RequestMethod.DELETE)
    @ResponseBody
    @OperateLog(module = "RG", action = "删除")
    public Result removeRG(@PathVariable("storage_remote_id") String storageRemoteId, @PathVariable("pool_code") String poolCode,
            HttpServletResponse response) {
        Result result = null;
        try {
            result = remoteStoragePoolService.remove(storageRemoteId, poolCode);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_NO_CONTENT);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("remove rg exception:", e);
        }
        return result;
    }
}
