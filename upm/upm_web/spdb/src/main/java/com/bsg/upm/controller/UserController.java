package com.bsg.upm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsg.upm.domain.JwtUser;
import com.bsg.upm.util.JwtUtil;
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
import com.bsg.upm.form.PwdForm;
import com.bsg.upm.form.UserForm;
import com.bsg.upm.query.UserParam;
import com.bsg.upm.service.Result;
import com.bsg.upm.service.UserService;

@Controller
@RequestMapping(value = "users")
@OperateLog(module = "用户")
public class UserController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "info", method = RequestMethod.GET)
    @ResponseBody
    public JwtUser userInfo(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("X-Token");
        JwtUser user = jwtUtil.getJwtUserFromToken(token);
        return user;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result list(@UnderlineToCamel UserParam userParam, HttpServletResponse response) {
        Result result = null;
        try {
            result = userService.list(userParam);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list user exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @ResponseBody
    public Result get(@PathVariable("username") String username, HttpServletResponse response) {
        Result result = null;
        try {
            result = userService.get(username);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get user exception:", e);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @OperateLog(action = "新增")
    public Result save(@RequestBody UserForm userForm, HttpServletResponse response) {
        Result result = null;
        try {
            result = userService.save(userForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_CREATED);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("save user exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "编辑")
    public Result update(@PathVariable("username") String username, @RequestBody UserForm userForm,
                         HttpServletResponse response) {
        Result result = null;
        try {
            result = userService.update(username, userForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("update user exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{username}/enabled", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "启用")
    public Result enable(@PathVariable("username") String username, HttpServletResponse response) {
        Result result = null;
        try {
            result = userService.enabled(username, true);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("enable user exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{username}/disabled", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "停用")
    public Result disable(@PathVariable("username") String username, HttpServletResponse response) {
        Result result = null;
        try {
            result = userService.enabled(username, false);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("disable user exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{username}/pwd", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "修改密码")
    public Result updatePwd(@PathVariable("username") String username, @RequestBody PwdForm pwdForm,
                            HttpServletResponse response) {
        Result result = null;
        try {
            result = userService.updatePwd(username, pwdForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("update password exception:", e);
        }
        return result;
    }

}
