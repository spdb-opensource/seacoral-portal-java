package com.bsg.upm.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.service.PrivilegeService;
import com.bsg.upm.service.Result;

@Controller
@RequestMapping(value = "privileges")
public class PrivilegeController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PrivilegeService privilegeService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam(value = "enabled", required = false) Boolean enabled,
            @RequestParam(value = "global", required = false) Boolean global, HttpServletResponse response) {
        Result result = null;
        try {
            result = privilegeService.list(enabled, global);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list privilege exception:", e);
        }
        return result;
    }

}
