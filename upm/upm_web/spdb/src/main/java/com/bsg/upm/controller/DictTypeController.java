package com.bsg.upm.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.service.DictService;
import com.bsg.upm.service.DictTypeService;
import com.bsg.upm.service.Result;

@Controller
@RequestMapping(value = "dict_types")
public class DictTypeController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DictTypeService dictTypeService;

    @Autowired
    private DictService dictService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result list(HttpServletResponse response) {
        Result result = null;
        try {
            result = dictTypeService.list();
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list dict type exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{dict_type_code}", method = RequestMethod.GET)
    @ResponseBody
    public Result listDict(@PathVariable("dict_type_code") String dictTypeCode, HttpServletResponse response) {
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
}
