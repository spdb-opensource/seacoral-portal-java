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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsg.upm.annotation.OperateLog;
import com.bsg.upm.annotation.UnderlineToCamel;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.AuditForm;
import com.bsg.upm.form.OrderGroupForm;
import com.bsg.upm.query.OrderGroupParam;
import com.bsg.upm.service.OrderGroupService;
import com.bsg.upm.service.Result;

@Controller
@RequestMapping(value = "order_groups")
@OperateLog(module = "工单")
public class OrderGroupController {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private OrderGroupService orderGroupService;
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result list(@UnderlineToCamel OrderGroupParam orderGroupParam, HttpServletResponse response) {
        Result result = null;
        try {
            result = orderGroupService.list(orderGroupParam);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list order exception:", e);
        }
        return result;
    }
    
    @RequestMapping(value = "/{order_group_id}", method = RequestMethod.GET)
    @ResponseBody
    public Result get(@PathVariable("order_group_id") String orderGroupId, HttpServletResponse response) {
        Result result = null;
        try {
            result = orderGroupService.get(orderGroupId);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get order exception:", e);
        }
        return result;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @OperateLog(action = "新增")
    public Result save(@RequestBody OrderGroupForm orderGroupForm,@RequestHeader(value ="subscription_id" ,required=false) String subscription_id,
            @RequestParam(value = "cnt", defaultValue = "1") Integer cnt, HttpServletResponse response) {
        Result result = null;
        try {
            result = orderGroupService.save(orderGroupForm, cnt, null,subscription_id);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            logger.info("新增服务ID：",result.getData().toString());
            response.setStatus(HttpStatus.SC_CREATED);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("save order exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{order_group_id}", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "编辑")
    public Result update(@PathVariable("order_group_id") String orderGroupId,
            @RequestBody OrderGroupForm orderGroupForm, HttpServletResponse response) {
        Result result = null;
        try {
            result = orderGroupService.update(orderGroupId, orderGroupForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("update order exception:", e);
        }
        return result;
    }
    
    @RequestMapping(value = "/{order_group_id}/audit", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "审批")
    public Result audit(@PathVariable("order_group_id") String orderGroupId, @RequestBody AuditForm auditForm,
            HttpServletResponse response) {
        Result result = null;
        try {
            result = orderGroupService.audit(orderGroupId, auditForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("audit order exception:", e);
        }
        return result;
    }
    

    /*
     * 去掉参数,@PathVariable("changeVersionImageId") String changeVersionImageId
     */
    @RequestMapping(value = "/{order_group_id}/execute", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "执行")
    public Result execute(@PathVariable("order_group_id") String orderGroupId, HttpServletResponse response) {
        Result result = null;
        try {
            result = orderGroupService.execute(orderGroupId,"");
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("execute order exception:", e);
        }
        return result;
    }
    
    @RequestMapping(value = "/{order_group_id}", method = RequestMethod.DELETE)
    @ResponseBody
    @OperateLog(action = "删除")
    public Result remove(@PathVariable("order_group_id") String orderGroupId, HttpServletResponse response) {
        Result result = null;
        try {
            result = orderGroupService.remove(orderGroupId);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_NO_CONTENT);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("remove order exception:", e);
        }
        return result;
    }
    
 /**
     * 工单执行状态重新查询
     * @param taskId
     * @param response
     * @return
     */
    @RequestMapping(value = "/reSearchTask/{serveId}/{taskId}/{actionType}",method = RequestMethod.GET)
    @ResponseBody
    public Result reSearchTask(@PathVariable("serveId") String serveId,@PathVariable("taskId") String taskId,@PathVariable("actionType") String actionType, HttpServletResponse response) {
        Result result = null;
        try {
            result = orderGroupService.reSearchTask(serveId,taskId,actionType);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list order exception:", e);
        }
        return result;
    }
}
