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
import com.bsg.upm.form.ClusterForm;
import com.bsg.upm.query.ClusterParam;
import com.bsg.upm.service.ClusterService;
import com.bsg.upm.service.Result;

@Controller
@RequestMapping(value = "clusters")
@OperateLog(module = "集群")
public class ClusterController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ClusterService clusterService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result list(@UnderlineToCamel ClusterParam cluserParam, HttpServletResponse response) {
        Result result = null;
        try {
            result = clusterService.list(cluserParam);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list cluster exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{cluster_id}", method = RequestMethod.GET)
    @ResponseBody
    public Result get(@PathVariable("cluster_id") String clusterId, HttpServletResponse response) {
        Result result = null;
        try {
            result = clusterService.get(clusterId);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get cluster exception:", e);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @OperateLog(action = "新增")
    public Result save(@RequestBody ClusterForm clusterForm, HttpServletResponse response) {
        Result result = null;
        try {
            result = clusterService.save(clusterForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_CREATED);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("save cluster exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{cluster_id}", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "编辑")
    public Result update(@PathVariable("cluster_id") String clusterId, @RequestBody ClusterForm clusterForm,
            HttpServletResponse response) {
        Result result = null;
        try {
            result = clusterService.update(clusterId, clusterForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("update cluster exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{cluster_id}/enabled", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "启用")
    public Result enable(@PathVariable("cluster_id") String clusterId, HttpServletResponse response) {
        Result result = null;
        try {
            result = clusterService.enabled(clusterId, true);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("enable cluster exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{cluster_id}/disabled", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "停用")
    public Result disable(@PathVariable("cluster_id") String clusterId, HttpServletResponse response) {
        Result result = null;
        try {
            result = clusterService.enabled(clusterId, false);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("disable cluster exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{cluster_id}", method = RequestMethod.DELETE)
    @ResponseBody
    @OperateLog(action = "删除")
    public Result remove(@PathVariable("cluster_id") String clusterId, HttpServletResponse response) {
        Result result = null;
        try {
            result = clusterService.remove(clusterId);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_NO_CONTENT);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("remove cluster exception:", e);
        }
        return result;
    }
}
