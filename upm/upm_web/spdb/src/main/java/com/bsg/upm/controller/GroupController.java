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
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.GroupForm;
import com.bsg.upm.service.GroupService;
import com.bsg.upm.service.Result;

@Controller
@RequestMapping(value = "groups")
@OperateLog(module = "组别")
public class GroupController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GroupService groupService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result list(HttpServletResponse response) {
        Result result = null;
        try {
            result = groupService.list();
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list group exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{group_id}", method = RequestMethod.GET)
    @ResponseBody
    public Result get(@PathVariable("group_id") String groupId, HttpServletResponse response) {
        Result result = null;
        try {
            result = groupService.get(groupId);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get group exception:", e);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @OperateLog(action = "新增")
    public Result save(@RequestBody GroupForm groupForm, HttpServletResponse response) {
        Result result = null;
        try {
            result = groupService.save(groupForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_CREATED);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("save group exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{group_id}", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "编辑")
    public Result update(@PathVariable("group_id") String groupId, @RequestBody GroupForm groupForm,
            HttpServletResponse response) {
        Result result = null;
        try {
            result = groupService.update(groupId, groupForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("update group exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{group_id}", method = RequestMethod.DELETE)
    @ResponseBody
    @OperateLog(action = "删除")
    public Result remove(@PathVariable("group_id") String groupId, HttpServletResponse response) {
        Result result = null;
        try {
            result = groupService.remove(groupId);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_NO_CONTENT);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("remove group exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{group_id}/users", method = RequestMethod.POST)
    @ResponseBody
    @OperateLog(action = "配置用户")
    public Result saveUser(@PathVariable("group_id") String groupId, @RequestBody List<String> usernames,
            HttpServletResponse response) {
        Result result = null;
        try {
            result = groupService.saveUsers(groupId, usernames);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_CREATED);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("save group user exception:", e);
        }
        return result;
    }
}
