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
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.BusinessSubsystemForm;
import com.bsg.upm.service.BusinessSubsystemService;
import com.bsg.upm.service.Result;

@Controller
@RequestMapping(value = "business_subsystems")
@OperateLog(module = "业务子系统")
public class BusinessSubsystemController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BusinessSubsystemService businessSubsystemService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam(value = "business_system_id", required = false) String businessSystemId,
            HttpServletResponse response) {
        Result result = null;
        try {
            result = businessSubsystemService.list(businessSystemId);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list business subsystem exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{businessSubsystem_id}", method = RequestMethod.GET)
    @ResponseBody
    public Result get(@PathVariable("businessSubsystem_id") String businessSubsystemId, HttpServletResponse response) {
        Result result = null;
        try {
            result = businessSubsystemService.get(businessSubsystemId);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get business subsystem exception:", e);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @OperateLog(action = "新增")
    public Result save(@RequestBody BusinessSubsystemForm businessSubsystemForm, HttpServletResponse response) {
        Result result = null;
        try {
            result = businessSubsystemService.save(businessSubsystemForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_CREATED);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("save business subsystem exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{businessSubsystem_id}", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "编辑")
    public Result update(@PathVariable("businessSubsystem_id") String businessSubsystemId,
            @RequestBody BusinessSubsystemForm businessSubsystemForm, HttpServletResponse response) {
        Result result = null;
        try {
            result = businessSubsystemService.update(businessSubsystemId, businessSubsystemForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("update business subsystem exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{businessSubsystem_id}", method = RequestMethod.DELETE)
    @ResponseBody
    @OperateLog(action = "删除")
    public Result remove(@PathVariable("businessSubsystem_id") String businessSubsystemId, HttpServletResponse response) {
        Result result = null;
        try {
            result = businessSubsystemService.remove(businessSubsystemId);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_NO_CONTENT);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("remove business subsystem exception:", e);
        }
        return result;
    }
}
