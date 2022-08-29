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
import com.bsg.upm.form.SiteConfigForm;
import com.bsg.upm.form.SiteForm;
import com.bsg.upm.service.Result;
import com.bsg.upm.service.SiteService;

@Controller
@RequestMapping(value = "sites")
@OperateLog(module = "站点")
public class SiteController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SiteService siteService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result list(HttpServletResponse response) {
        Result result = null;
        try {
            result = siteService.list();
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list site exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{site_id}", method = RequestMethod.GET)
    @ResponseBody
    public Result get(@PathVariable("site_id") String siteId, HttpServletResponse response) {
        Result result = null;
        try {
            result = siteService.get(siteId);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get site exception:", e);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @OperateLog(action = "新增")
    public Result save(@RequestBody SiteForm siteForm, HttpServletResponse response) {
        Result result = null;
        try {
            result = siteService.save(siteForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_CREATED);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("save site exception:", e);
        }
        return result;
    }
    
    @RequestMapping(value = "/update/{site_id}",method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "编辑")
    public Result update(@PathVariable("site_id") String siteId,@RequestBody SiteForm siteForm, HttpServletResponse response) {
        Result result = null;
        try {
            result = siteService.update(siteId,siteForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_CREATED);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("save site exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{site_id}", method = RequestMethod.DELETE)
    @ResponseBody
    @OperateLog(action = "删除")
    public Result remove(@PathVariable("site_id") String siteId, HttpServletResponse response) {
        Result result = null;
        try {
            result = siteService.remove(siteId);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_NO_CONTENT);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("remove site exception:", e);
        }
        return result;
    }

}
