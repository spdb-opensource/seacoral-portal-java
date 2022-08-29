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
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsg.upm.annotation.OperateLog;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.BusinessSystemForm;
import com.bsg.upm.service.BusinessSystemService;
import com.bsg.upm.service.Result;

@Controller
@RequestMapping(value = "business_systems")
@OperateLog(module = "业务系统")
public class BusinessSystemController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BusinessSystemService businessSystemService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result list(HttpServletResponse response) {
        Result result = null;
        try {
            result = businessSystemService.list();
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list business system exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{businessSystem_id}", method = RequestMethod.GET)
    @ResponseBody
    public Result get(@PathVariable("businessSystem_id") String businessSystemId, HttpServletResponse response) {
        Result result = null;
        try {
            result = businessSystemService.get(businessSystemId);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get business system exception:", e);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @OperateLog(action = "新增")
    public Result save(@RequestBody BusinessSystemForm businessSystemForm, HttpServletResponse response) {
        Result result = null;
        try {
            result = businessSystemService.save(businessSystemForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_CREATED);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("save business system exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{businessSystem_id}", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "编辑")
    public Result update(@PathVariable("businessSystem_id") String businessSystemId, @RequestBody BusinessSystemForm businessSystemForm,
            HttpServletResponse response) {
        Result result = null;
        try {
            result = businessSystemService.update(businessSystemId, businessSystemForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("update business system exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{businessSystem_id}", method = RequestMethod.DELETE)
    @ResponseBody
    @OperateLog(action = "删除")
    public Result remove(@PathVariable("businessSystem_id") String businessSystemId, HttpServletResponse response) {
        Result result = null;
        try {
            result = businessSystemService.remove(businessSystemId);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_NO_CONTENT);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("remove business system exception:", e);
        }
        return result;
    }
}
