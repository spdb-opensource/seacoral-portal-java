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
import com.bsg.upm.form.DictForm;
import com.bsg.upm.service.DictService;
import com.bsg.upm.service.Result;

@Controller
@RequestMapping(value = "dicts")
@OperateLog(module = "字典")
public class DictController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DictService dictService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam("dict_type_code") String dictTypeCode, HttpServletResponse response) {
        Result result = null;
        try {
            result = dictService.list(dictTypeCode);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list dict exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{dict_id}", method = RequestMethod.GET)
    @ResponseBody
    public Result get(@PathVariable("dict_id") String dictId, HttpServletResponse response) {
        Result result = null;
        try {
            result = dictService.get(dictId);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get dict exception:", e);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @OperateLog(action = "新增")
    public Result save(@RequestBody DictForm dictForm, HttpServletResponse response) {
        Result result = null;
        try {
            result = dictService.save(dictForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("update dict exception:", e);
        }
        return result;
    }
    
    @RequestMapping(value = "/{dict_id}", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "编辑")
    public Result update(@PathVariable("dict_id") String dictId, @RequestBody DictForm dictForm,
            HttpServletResponse response) {
        Result result = null;
        try {
            result = dictService.update(dictId, dictForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("update dict exception:", e);
        }
        return result;
    }
}
