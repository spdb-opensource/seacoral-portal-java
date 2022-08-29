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
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.RoleCfgAppForm;
import com.bsg.upm.form.RoleCfgDataScopeForm;
import com.bsg.upm.form.RoleCfgOthersForm;
import com.bsg.upm.form.RoleForm;
import com.bsg.upm.service.Result;
import com.bsg.upm.service.RoleCfgAppService;
import com.bsg.upm.service.RoleCfgDataScopeService;
import com.bsg.upm.service.RoleCfgOthersService;
import com.bsg.upm.service.RoleService;

@Controller
@RequestMapping(value = "roles")
@OperateLog(module = "角色")
public class RoleController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleCfgDataScopeService cfgDataScopeService;

    @Autowired
    private RoleCfgOthersService cfgOthersService;

    @Autowired
    private RoleCfgAppService cfgAppService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Result list(HttpServletResponse response) {
        Result result = null;
        try {
            result = roleService.list();
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("list role exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{role_id}", method = RequestMethod.GET)
    @ResponseBody
    public Result get(@PathVariable("role_id") String roleId, HttpServletResponse response) {
        Result result = null;
        try {
            result = roleService.get(roleId);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get role exception:", e);
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @OperateLog(action = "新增")
    public Result save(@RequestBody RoleForm roleForm, HttpServletResponse response) {
        Result result = null;
        try {
            result = roleService.save(roleForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_CREATED);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("save role exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{role_id}", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "编辑")
    public Result update(@PathVariable("role_id") String roleId, @RequestBody RoleForm roleForm,
            HttpServletResponse response) {
        Result result = null;
        try {
            result = roleService.update(roleId, roleForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("update role exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{role_id}", method = RequestMethod.DELETE)
    @ResponseBody
    @OperateLog(action = "删除")
    public Result remove(@PathVariable("role_id") String roleId, HttpServletResponse response) {
        Result result = null;
        try {
            result = roleService.remove(roleId);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_NO_CONTENT);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("remove role exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{role_id}/cfgs/data_scopes", method = RequestMethod.GET)
    @ResponseBody
    public Result getCfgDataScope(@PathVariable("role_id") String roleId, HttpServletResponse response) {
        Result result = null;
        try {
            result = cfgDataScopeService.get(roleId);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get role data scope config exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{role_id}/cfgs/data_scopes", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(module = "角色权限配置-数据可见范围配置", action = "编辑")
    public Result updateCfgDataScope(@PathVariable("role_id") String roleId,
            @RequestBody RoleCfgDataScopeForm cfgDataScopeForm, HttpServletResponse response) {
        Result result = null;
        try {
            result = cfgDataScopeService.update(roleId, cfgDataScopeForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("update role data scope config  exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{role_id}/cfgs/others", method = RequestMethod.GET)
    @ResponseBody
    public Result getCfgOthers(@PathVariable("role_id") String roleId, HttpServletResponse response) {
        Result result = null;
        try {
            result = cfgOthersService.get(roleId);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get role other config exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{role_id}/cfgs/others", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(module = "角色权限配置-其他配置", action = "编辑")
    public Result updateCfgOthers(@PathVariable("role_id") String roleId, @RequestBody RoleCfgOthersForm cfgOthersForm,
            HttpServletResponse response) {
        Result result = null;
        try {
            result = cfgOthersService.update(roleId, cfgOthersForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("update role other config exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{role_id}/cfgs/apps", method = RequestMethod.GET)
    @ResponseBody
    public Result getCfgApp(@PathVariable("role_id") String roleId, HttpServletResponse response) {
        Result result = null;
        try {
            result = cfgAppService.get(roleId);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get role app config exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{role_id}/cfgs/apps", method = RequestMethod.POST)
    @ResponseBody
    @OperateLog(module = "角色权限配置-功能配置", action = "新增")
    public Result saveCfgApp(@PathVariable("role_id") String roleId, @RequestBody RoleCfgAppForm cfgAppForm,
            HttpServletResponse response) {
        Result result = null;
        try {
            result = cfgAppService.save(roleId, cfgAppForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_CREATED);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("save role app config  exception:", e);
        }
        return result;
    }

    @RequestMapping(value = "/{role_id}/cfgs/apps", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(module = "角色权限配置-功能配置", action = "编辑")
    public Result updateCfgApp(@PathVariable("role_id") String roleId, @RequestBody RoleCfgAppForm cfgAppForm,
            HttpServletResponse response) {
        Result result = null;
        try {
            result = cfgAppService.update(roleId, cfgAppForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("update role app config  exception:", e);
        }
        return result;
    }

}
