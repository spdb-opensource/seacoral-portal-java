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
import com.bsg.upm.annotation.UnderlineToCamel;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.ImageTemplateForm;
import com.bsg.upm.form.ImageForm;
import com.bsg.upm.query.ImageParam;
import com.bsg.upm.service.Result;
import com.bsg.upm.service.ImageService;

@Controller
@RequestMapping(value = "images")
@OperateLog(module = "软件镜像")
public class ImageController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ImageService imageService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result list(@UnderlineToCamel ImageParam imageParam, HttpServletResponse response) {
        Result result = null;
        try {
            result = imageService.list(imageParam);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list software image exception:", e);
        }
        return result;
    }

    /*@RequestMapping(value = "/{image_id}", method = RequestMethod.GET)
    @ResponseBody
    @OperateLog(action = "详情")
    public Result get(@PathVariable("image_id") String imageId, HttpServletResponse response) {
        Result result = null;
        try {
            result = imageService.get(imageId);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list software image exception:", e);
        }
        return result;
    }*/
    
    @RequestMapping(value = "/{image_id}/cfgs", method = RequestMethod.GET)
    @ResponseBody
    @OperateLog(action = "配置详情")
    public Result get(@PathVariable("image_id") String imageId, HttpServletResponse response) {
        Result result = null;
        try {
            result = imageService.getImageConf(imageId);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error(" software image conf exception:", e);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @OperateLog(action = "新增")
    public Result save(@RequestBody ImageForm imageForm, HttpServletResponse response) {
        Result result = null;
        try {
            result = imageService.save(imageForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_CREATED);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("save software image exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{image_id}/enabled", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "启用")
    public Result enable(@PathVariable("image_id") String imageId, HttpServletResponse response) {
        Result result = null;
        try {
            result = imageService.enabled(imageId, true);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("enable software image exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{image_id}/disabled", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "停用")
    public Result disable(@PathVariable("image_id") String imageId, HttpServletResponse response) {
        Result result = null;
        try {
            result = imageService.enabled(imageId, false);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("disable software image exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{image_id:[a-zA-Z0-9\\:\\.\\-]+}", method = RequestMethod.DELETE)
    @ResponseBody
    @OperateLog(action = "删除")
    public Result remove(@PathVariable("image_id") String imageId, HttpServletResponse response) {
        Result result = null;
        try {
            result = imageService.remove(imageId);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_NO_CONTENT);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("remove software image exception:", e);
        }
        return result;
    }

}
