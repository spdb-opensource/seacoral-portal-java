package com.bsg.upm.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsg.upm.annotation.UnderlineToCamel;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.query.OperateLogParam;
import com.bsg.upm.service.OperateLogService;
import com.bsg.upm.service.Result;

@Controller
@RequestMapping(value = "operate_logs")
public class OperateLogController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OperateLogService operateLogService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result listOperateLog(@UnderlineToCamel OperateLogParam operateLogParam, HttpServletResponse response) {
        Result result = null;
        try {
            result = operateLogService.list(operateLogParam);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list operate log exception:", e);
        }
        return result;
    }
}
