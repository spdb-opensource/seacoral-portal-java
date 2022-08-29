package com.bsg.upm.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bsg.upm.annotation.OperateLog;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.DatabaseForm;
import com.bsg.upm.form.KeysetForm;
import com.bsg.upm.form.MgmBaseForm;
import com.bsg.upm.form.NetworkingForm;
import com.bsg.upm.form.ServerGroupChangeUserPwdForm;
import com.bsg.upm.form.ServerGroupForm;
import com.bsg.upm.form.ServerStateForm;
import com.bsg.upm.mgm.body.MgmBaseBody;
import com.bsg.upm.form.ServerGroupUnitForm;
import com.bsg.upm.form.ServerGroupUpdateUserForm;
import com.bsg.upm.form.ServerGroupUserForm;
import com.bsg.upm.param.HostMonitorQueryParam;
import com.bsg.upm.param.HostMonitorRegisParam;
import com.bsg.upm.param.ServeGetUnitMonitorParam;
import com.bsg.upm.param.ServeUnitRegisParam;
import com.bsg.upm.query.BackupFileParam;
import com.bsg.upm.query.UnitRebulidParam;
import com.bsg.upm.query.UnitRoleChangeParam;
import com.bsg.upm.service.DatabaseService;
import com.bsg.upm.service.Result;
import com.bsg.upm.service.ServerGroupService;
import com.bsg.upm.service.ServerGroupUnitService;
import com.bsg.upm.service.ServerGroupUserService;

/**
 * 服务组controller
 * 
 * @author swn
 *
 */

@Controller
@RequestMapping(value = "/serv_grps/mysql")
@OperateLog(module = "服务组")
public class ServerGroupController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ServerGroupService serverGroupService;

	@Autowired
	private DatabaseService databaseService;

	@Autowired
	private ServerGroupUserService serverGroupUserService;
	
	@Autowired
	private ServerGroupUnitService serverGroupUnitService;

	/**
	 * 服务组查询
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Result list(@RequestHeader(value ="subscription_id" ,required=false) String subscription_id,HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupService.list(subscription_id);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("list server group exception:", e);
		}
		return result;
	}

	/**
	 * 服务组详情、header增加订阅号字段subscription_id
	 * 
	 * @param serverGroupId
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{serv_group_id}", method = RequestMethod.GET)
	@ResponseBody
	public Result get(@PathVariable("serv_group_id") String serverGroupId, 
			@RequestHeader(value ="subscription_id" ,required=false) String subscription_id, HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupService.get(serverGroupId,subscription_id);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("get server group exception:", e);
		}
		return result;
	}
	
	/**
	 * 服务组任务详情
	 * 
	 * @param serverGroupId
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/tasks/{task_id}", method = RequestMethod.GET)
	@ResponseBody
	public Result getTask(@PathVariable("task_id") String taskId, HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupService.getTask(taskId);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("get server group exception:", e);
		}
		return result;
	}

	/**
	 * 服务组启动
	 * 
	 * @param serverGroupId
	 *            服务组编码
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{serv_group_id}/start", method = RequestMethod.PUT)
	@ResponseBody
	@OperateLog(action = "启动")
	public Result start(@PathVariable("serv_group_id") String serverGroupId, HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupService.enabled(serverGroupId, true);
			if (result.getCode() != Result.SUCCESS) {
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				return result;
			}
			response.setStatus(HttpStatus.SC_OK);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("enable server group exception:", e);
		}
		return result;
	}
	
	/**
	 * 服务组状态变更，cmha、proxy、数据库
	 * 
	 * @param serverGroupId
	 *            服务组编码
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{serv_group_id}/state", method = RequestMethod.PUT)
	@ResponseBody
	@OperateLog(action = "变更")
	public Result state(@PathVariable("serv_group_id") String serverGroupId, 
						@RequestBody ServerStateForm serverStateForm,
						HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupService.state(serverGroupId, serverStateForm);
			if (result.getCode() != Result.SUCCESS) {
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				return result;
			}
			response.setStatus(HttpStatus.SC_OK);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("enable server group exception:", e);
		}
		return result;
	}

	/**
	 * 服务组
	 * 
	 * @param serverGroupId
	 *            服务组编码
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{serv_group_id}/stop", method = RequestMethod.PUT)
	@ResponseBody
	@OperateLog(action = "停止")
	public Result stop(@PathVariable("serv_group_id") String serverGroupId, HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupService.enabled(serverGroupId, false);
			if (result.getCode() != Result.SUCCESS) {
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				return result;
			}
			response.setStatus(HttpStatus.SC_OK);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("enable server group exception:", e);
		}
		return result;
	}

	/**
	 * 扩容
	 * 
	 * @param serverGroupId
	 * @param serverGroupForm
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{serv_group_id}/scale/{orderGroupId}", method = RequestMethod.PUT)
	@ResponseBody
	@OperateLog(action = "扩容")
	public Result scale(@PathVariable("serv_group_id") String serverGroupId,@PathVariable("orderGroupId") String orderGroupId,
			@RequestBody ServerGroupForm serverGroupForm,
			@RequestHeader(value ="subscription_id" ,required=false) String subscription_id,
			HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupService.scale(serverGroupId,orderGroupId, serverGroupForm,subscription_id);
			if (result.getCode() != Result.SUCCESS) {
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				return result;
			}
			response.setStatus(HttpStatus.SC_OK);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("scale server group exception:", e);
		}
		return result;
	}

	/**
	 * 升级
	 * 
	 * @param serverGroupId
	 * @param serverGroupForm
	 * @param response
	 * @return
	 * 去掉参数,@PathVariable("orderGroupId") String orderGroupId
	 */
	@RequestMapping(value = "/{serv_group_id}/image", method = RequestMethod.PUT)
	@ResponseBody
	@OperateLog(action = "升级")
	public Result image(@PathVariable("serv_group_id") String serverGroupId,
			@RequestHeader(value ="subscription_id" ,required=false) String subscription_id,
			@RequestBody ServerGroupForm serverGroupForm, HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupService.image(serverGroupId, serverGroupForm,subscription_id);
			if (result.getCode() != Result.SUCCESS) {
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				return result;
			}
			response.setStatus(HttpStatus.SC_OK);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("image server group exception:", e);
		}
		return result;
	}
	
	/*
	 * 升级应该调用reImage
	 */
	
	/**
	 * 备份
	 * @param serverGroupId
	 * @param serverGroupForm
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{serv_group_id}/backup", method = RequestMethod.PUT)
	@ResponseBody
	@OperateLog(action = "备份")
	public Result backup(@PathVariable("serv_group_id") String serverGroupId,
			@RequestBody ServerGroupForm serverGroupForm, HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupService.backup(serverGroupId, serverGroupForm);
			if (result.getCode() != Result.SUCCESS) {
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				return result;
			}
			response.setStatus(HttpStatus.SC_OK);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("backup server group exception:", e);
		}
		return result;
	}

	/**
	 * 服务组删除
	 * 
	 * @param serverGroupId
	 *            服务组编码
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{serv_group_id}/{name}", method = RequestMethod.DELETE)
	@ResponseBody
	@OperateLog(action = "删除")
	public Result remove(@PathVariable("serv_group_id") String serverGroupId,
			@RequestHeader(value ="subscription_id" ,required=false) String subscription_id,
			@PathVariable("name") String name,	 HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupService.remove(serverGroupId,name,subscription_id);
			if (result.getCode() != Result.SUCCESS) {
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				return result;
			}
			response.setStatus(HttpStatus.SC_NO_CONTENT);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("remove server group exception:", e);
		}
		return result;
	}
	
	/**
	 * 单元启动
	 * @param serverGroupId
	 * @param unitId
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{serv_group_id}/units/{unit_id}/start", method = RequestMethod.PUT)
	@ResponseBody
	@OperateLog(action = "单元启动")
	public Result startUnit(@PathVariable("serv_group_id") String serverGroupId, 
			@PathVariable("unit_id") String unitId, HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupUnitService.enabled(serverGroupId, unitId, true);
			if (result.getCode() != Result.SUCCESS) {
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				return result;
			}
			response.setStatus(HttpStatus.SC_OK);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("server group unit start exception:", e);
		}
		return result;
	}
	
	/**
	 * 单元停止
	 * @param serverGroupId
	 * @param unitId
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{serv_group_id}/units/{unit_id}/stop", method = RequestMethod.PUT)
	@ResponseBody
	@OperateLog(action = "单元停止")
	public Result stopUnit(@PathVariable("serv_group_id") String serverGroupId, 
			@PathVariable("unit_id") String unitId, HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupUnitService.enabled(serverGroupId, unitId, false);
			if (result.getCode() != Result.SUCCESS) {
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				return result;
			}
			response.setStatus(HttpStatus.SC_OK);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("server group unit stop exception:", e);
		}
		return result;
	}
	
	/** 单元备份
	 * @param serverGroupId
	 * @param unitId
	 * @param serverGroupUnitForm
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{serv_group_id}/units/{unit_id}/backup", method = RequestMethod.PUT)
	@ResponseBody
	@OperateLog(action = "单元备份")
	public Result unitBackup(@PathVariable("serv_group_id") String serverGroupId,
			@PathVariable("unit_id") String unitId,
			@RequestBody ServerGroupUnitForm serverGroupUnitForm, HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupUnitService.backup(serverGroupId, unitId, serverGroupUnitForm);
			if (result.getCode() != Result.SUCCESS) {
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				return result;
			}
			response.setStatus(HttpStatus.SC_OK);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("server group unit backup exception:", e);
		}
		return result;
	}
	

	/**单元重建
	 * @param unitRebulidParam
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/rebuild", method = RequestMethod.PUT)
	@ResponseBody
	@OperateLog(action = "单元重建")
	public Result unitRebulid(@RequestBody UnitRebulidParam unitRebulidParam,HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupUnitService.rebulid(unitRebulidParam);
			if (result.getCode() != Result.SUCCESS) {
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				return result;
			}
			response.setStatus(HttpStatus.SC_OK);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("server group unit rebuild exception:", e);
		}
		return result;
	}
	
	/** 单元角色变更
	 * @param backupFileParam
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/roleChange", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "单元角色设置为主")
    public Result roleChangeMaster(@RequestBody UnitRoleChangeParam unitRoleParam, HttpServletResponse response) {
        Result result = null;
        try {
            result = serverGroupUnitService.roleChange(unitRoleParam);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("restore backup files exception:", e);
        }
        return result;
    }
	
	@RequestMapping(value = "/roleChangeSlave/{slaveId}", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "单元角色设置为从")
    public Result roleChangeSlave(@PathVariable("slaveId") String slaveId,@RequestBody UnitRoleChangeParam unitRoleParam, HttpServletResponse response) {
        Result result = null;
        try {
            result = serverGroupUnitService.roleChangeSlave(slaveId,unitRoleParam);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("restore backup files exception:", e);
        }
        return result;
    }
	
	/**单元迁移
	 * @param unitRebulidParam
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/migrate", method = RequestMethod.PUT)
	@ResponseBody
	@OperateLog(action = "单元迁移")
	public Result unitMigrate(@RequestBody UnitRebulidParam unitRebulidParam,HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupUnitService.migrate(unitRebulidParam);
			if (result.getCode() != Result.SUCCESS) {
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				return result;
			}
			response.setStatus(HttpStatus.SC_OK);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("server group unit migrate exception:", e);
		}
		return result;
	}
	
	/**
	 * 服务组库查询
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{serv_group_id}/databases", method = RequestMethod.GET)
	@ResponseBody
	public Result listDatabase(@PathVariable("serv_group_id") String serverGroupId, HttpServletResponse response) {
		Result result = null;
		try {
			result = databaseService.list(serverGroupId);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("list database of server group exception:", e);
		}
		return result;
	}

	/**
	 * 服务组库详情查询
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{serv_group_id}/databases/{db_name}", method = RequestMethod.GET)
	@ResponseBody
	public Result getDatabase(@PathVariable("serv_group_id") String serverGroupId,
			@PathVariable("db_name") String databaseName, HttpServletResponse response) {
		Result result = null;
		try {
			result = databaseService.get(serverGroupId, databaseName);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("list database of server group exception:", e);
		}
		return result;
	}

	/**
	 * 服务组库新增
	 * 
	 * @param serverGroupId
	 * @param databaseForm
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{serv_group_id}/databases", method = RequestMethod.POST)
	@ResponseBody
	@OperateLog(action = "库新增")
	public Result saveDatabase(@PathVariable("serv_group_id") String serverGroupId,
			@RequestBody DatabaseForm databaseForm, HttpServletResponse response) {
		Result result = null;
		try {
			result = databaseService.save(serverGroupId, databaseForm);
			if (result.getCode() != Result.SUCCESS) {
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				return result;
			}
			response.setStatus(HttpStatus.SC_CREATED);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("save database of server group exception:", e);
		}
		return result;
	}

	/**
	 * 服务组库删除
	 * 
	 * @param serverGroupId
	 * @param databaseName
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{serv_group_id}/databases/{db_name}", method = RequestMethod.DELETE)
	@ResponseBody
	@OperateLog(action = "库删除")
	public Result removeDatabase(@PathVariable("serv_group_id") String serverGroupId,@PathVariable("db_name") String databaseName, HttpServletResponse response) {
		Result result = null;
		try {
			result = databaseService.remove(serverGroupId, databaseName);
			if (result.getCode() != Result.SUCCESS) {
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				return result;
			}
			response.setStatus(HttpStatus.SC_NO_CONTENT);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("remove database of server group exception:", e);
		}
		return result;
	}

	/**
	 * 服务组用户查询
	 * 
	 * @param serverGroupId
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "{serv_group_id}/users", method = RequestMethod.GET)
	@ResponseBody
	public Result listUsers(@PathVariable("serv_group_id") String serverGroupId, HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupUserService.list(serverGroupId);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("list server group user exception:", e);
		}
		return result;
	}

	/**
	 * 服务组用户详情
	 * 
	 * @param serverGroupId
	 * @param username
	 * @param ip
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{serv_group_id}/users/{username}", method = RequestMethod.GET)
	@ResponseBody
	public Result getUser(@PathVariable("serv_group_id") String serverGroupId,
			@PathVariable("username") String username, @RequestParam("ip") String ip, HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupUserService.get(serverGroupId, username, ip);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("get server group user exception:", e);
		}
		return result;
	}

	/**
	 * 服务组用户保存
	 * 
	 * @param serverGroupId
	 * @param serverGroupUserForm
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{serv_group_id}/users", method = RequestMethod.POST)
	@ResponseBody
	@OperateLog(action = "用户新增")
	public Result saveUser(@PathVariable("serv_group_id") String serverGroupId,
			@RequestBody ServerGroupUserForm serverGroupUserForm, HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupUserService.save(serverGroupId, serverGroupUserForm);
			if (result.getCode() != Result.SUCCESS) {
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				return result;
			}
			response.setStatus(HttpStatus.SC_CREATED);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("save server group user exception:", e);
		}
		return result;
	}

	/** 用户密码重置
	 * @param serverGroupId
	 * @param username
	 * @param serverGroupUserForm
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{serv_group_id}/changeUserPwd/{username}", method = RequestMethod.PUT)
	@ResponseBody
	@OperateLog(action = "用户密码重置")
	public Result changeUserPwd(@PathVariable("serv_group_id") String serverGroupId,
			@PathVariable("username") String username,
			@RequestBody ServerGroupChangeUserPwdForm serverGroupUserForm, HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupUserService.changeUserPwd(serverGroupId, username, serverGroupUserForm);
			if (result.getCode() != Result.SUCCESS) {
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				return result;
			}
			response.setStatus(HttpStatus.SC_OK);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("resetPwd sever group user exception:", e);
		}
		return result;
	}
	/**
	 * 服务组用户编辑
	 * 
	 * @param serverGroupId
	 * @param username
	 * @param ip
	 * @param serverGroupUserForm
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{serv_group_id}/saveUpdateUser/{username}", method = RequestMethod.PUT)
	@ResponseBody
	@OperateLog(action = "用户编辑")
	public Result updateUser(@PathVariable("serv_group_id") String serverGroupId,
			@PathVariable("username") String username,
			@RequestBody ServerGroupUpdateUserForm serverGroupUserForm, HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupUserService.update(serverGroupId, username, serverGroupUserForm);
			if (result.getCode() != Result.SUCCESS) {
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				return result;
			}
			response.setStatus(HttpStatus.SC_OK);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("update sever group user exception:", e);
		}
		return result;
	}

	/**
	 * 服务组用户删除
	 * 
	 * @param serverGroupId
	 * @param username
	 * @param ip
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{serv_group_id}/users/{username}", method = RequestMethod.DELETE)
	@ResponseBody
	@OperateLog(action = "用户删除")
	public Result removeUser(@PathVariable("serv_group_id") String serverGroupId,
			@PathVariable("username") String username, @RequestParam("ip") String ip, HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupUserService.remove(serverGroupId, username, ip);
			if (result.getCode() != Result.SUCCESS) {
				response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
				return result;
			}
			response.setStatus(HttpStatus.SC_NO_CONTENT);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("remove server group user exception:", e);
		}
		return result;
	}
	
	/** 服务组参数查询
	 * @param servGroupId
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/cfgs/{serv_group_id}", method = RequestMethod.GET)
    @ResponseBody
    @OperateLog(action = "配置查询")
    public Result getCfgs(@PathVariable("serv_group_id") String servGroupId, HttpServletResponse response) {
        Result result = null;
        try {
            result = serverGroupUserService.getUnitConf(servGroupId);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error(" software image conf exception:", e);
        }
        return result;
    }
	
	/**  服务配置文件编辑
	 * @param servGroupId
	 * @param keysetForm
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/editCfgs/{serv_group_id}", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "配置文件编辑")
    public Result editCfgs(@PathVariable("serv_group_id") String servGroupId,@RequestBody KeysetForm keysetForm, HttpServletResponse response) {
        Result result = null;
        try {
            result = serverGroupUserService.editCfgs(servGroupId,keysetForm);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error(" software image conf exception:", e);
        }
        return result;
    }
	
 	/** 查询服务单元是否注册
 	 * @param unitId
 	 * @param response
 	 * @return
 	 */
 	@RequestMapping(value = "/instances/{unit_id}/info", method = RequestMethod.GET)
    @ResponseBody
    public Result getAgents(@PathVariable("unit_id") String unitId, HttpServletResponse response) {
        Result result = null;
        try {
            result = serverGroupUnitService.getUnitMonitor(unitId);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get serveUnitRegister exception:", e);
        }
        return result;
    }
 
 	/** 服务单元监控注册
 	 * @param serveUnitRegisParam
 	 * @param response
 	 * @return
 	 */
 	@RequestMapping(value = "/instances", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "单元监控注册")
    public Result monitorRegister(@RequestBody ServeUnitRegisParam serveUnitRegisParam, HttpServletResponse response) {
        Result result = null;
        try {
            result = serverGroupUnitService.monitorRegister(serveUnitRegisParam);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_NO_CONTENT);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("monitorRegister unit exception:", e);
        }
        return result;
    }
	
 	/**服务单元监控注销
 	 * @param unitId
 	 * @param response
 	 * @return
 	 */
 	@RequestMapping(value = "/instances/uninstall/{unit_id}", method = RequestMethod.DELETE)
    @ResponseBody
    @OperateLog(action = "服务单元监控注销")
    public Result uninstall1(@PathVariable("unit_id") String unitId, HttpServletResponse response) {
        Result result = null;
        try {
            result = serverGroupUnitService.monitorCancel(unitId);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_NO_CONTENT);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("monitorRegister host exception:", e);
        }
        return result;
    }

 	/**根据指定实例查询单元监控数据
 	 * @param instanceName
 	 * @param startTime
 	 * @param endTime
 	 * @param response
 	 * @return
 	 */
 	@RequestMapping(value = "/instances/{instance_name}/metrics/{start_time}/{end_time}", method = RequestMethod.PUT)
    @ResponseBody
    public Result getUnitMonitorByInstanceName(@PathVariable("instance_name") String instanceName,@PathVariable("start_time") Long startTime,@PathVariable("end_time") Long endTime, @RequestBody ServeGetUnitMonitorParam serveGetUnitMonitorParam,HttpServletResponse response) {
        Result result = null;
        try {
        	 HostMonitorQueryParam hostMonitorQueryParam=new HostMonitorQueryParam();
        	 hostMonitorQueryParam.setStartTime(startTime);
        	 hostMonitorQueryParam.setEndTime(endTime);
        	 String relateCode=serveGetUnitMonitorParam.getRelateCode();
        	 String description=serveGetUnitMonitorParam.getDescription();
        	 if(description.equals("负载(TPS)")) {
        		 description="mysql insert监控项;mysql select监控项;mysql update监控项;mysql delete监控项";
        	 }else if(description.equals("连接数")) {
        		 description="mysql最大连接数监控项;mysql已存在连接数监控项";
        	 }
        	 
            result = serverGroupUnitService.getUnitMonitorByInstanceName(instanceName,relateCode,description,hostMonitorQueryParam);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get host exception:", e);
        }
        return result;
    }
 	
 	@RequestMapping(value = "/instancesString/{instance_name}/metrics/{start_time}/{end_time}", method = RequestMethod.PUT)
    @ResponseBody
    public Result getUnitMonitorByInstanceNameString(@PathVariable("instance_name") String instanceName,@PathVariable("start_time") Long startTime,@PathVariable("end_time") Long endTime, @RequestBody ServeGetUnitMonitorParam serveGetUnitMonitorParam,HttpServletResponse response) {
        Result result = null;
        try {
        	 HostMonitorQueryParam hostMonitorQueryParam=new HostMonitorQueryParam();
        	 hostMonitorQueryParam.setStartTime(startTime);
        	 hostMonitorQueryParam.setEndTime(endTime);
        	 String relateCode=serveGetUnitMonitorParam.getRelateCode();
        	 String description=serveGetUnitMonitorParam.getDescription();
            result = serverGroupUnitService.getUnitMonitorByInstanceNameString(instanceName,relateCode,description,hostMonitorQueryParam);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get host exception:", e);
        }
        return result;
    }
 	
 	/**根据指定容器查询单元监控数据
 	 * @param containerName
 	 * @param startTime
 	 * @param endTime
 	 * @param response
 	 * @return
 	 */
 	@RequestMapping(value = "/containers/{container_name}/metrics/{start_time}/{end_time}", method = RequestMethod.PUT)
    @ResponseBody
    public Result getUnitMonitorByContainerName(@PathVariable("container_name") String containerName,@PathVariable("start_time") Long startTime,@PathVariable("end_time") Long endTime, @RequestBody ServeGetUnitMonitorParam serveGetUnitMonitorParam,HttpServletResponse response) {
        Result result = null;
        try {
        	 HostMonitorQueryParam hostMonitorQueryParam=new HostMonitorQueryParam();
        	 hostMonitorQueryParam.setStartTime(startTime);
        	 hostMonitorQueryParam.setEndTime(endTime);
        	 String relateCode=serveGetUnitMonitorParam.getRelateCode();
        	 String description=serveGetUnitMonitorParam.getDescription();
        	 if(description.equals("网络吞吐量")) {
        		 description="容器网络接收速度监控项;容器网络发送速度监控项";
        	 }
            result = serverGroupUnitService.getUnitMonitorByContainerName(containerName,relateCode,description,hostMonitorQueryParam);
        } catch (ServiceException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            logger.error("get host exception:", e);
        }
        return result;
    }
 	
 	/**
	 * 拓扑查询
	 * 
	 * @param serverGroupId
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{serv_group_id}/topology", method = RequestMethod.GET)
	@ResponseBody
	public Result getTopology(@PathVariable("serv_group_id") String serverGroupId, 
			@RequestHeader(value ="subscription_id" ,required=false) String subscription_id, HttpServletResponse response) {
		Result result = null;
		try {
			result = serverGroupService.getTopology(serverGroupId,subscription_id);
		} catch (ServiceException e) {
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			logger.error("get server group exception:", e);
		}
		return result;
	}
	
	@RequestMapping(value = "/{serv_group_id}/replication/semi_sync", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "设置半同步")
    public Result updateReplicationMode(@PathVariable("serv_group_id") String serverGroupId, 
    		@RequestHeader(value ="subscription_id" ,required=false) String subscription_id,
    		@RequestBody MgmBaseForm mgmBaseForm,HttpServletResponse response) {
        Result result = null;
        try {
            result = serverGroupService.updateReplicationMode(serverGroupId,subscription_id,mgmBaseForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "系统异常：" + e.getMessage());
            logger.error("设置半同步异常：", e);
        }
        return result;
    }
	
	@RequestMapping(value = "/{serv_group_id}/replication/set_source", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "设置复制源")
    public Result resetMaster(@PathVariable("serv_group_id") String serverGroupId, 
    		@RequestHeader(value ="subscription_id" ,required=false) String subscription_id,
    		@RequestBody MgmBaseForm mgmBaseForm,HttpServletResponse response) {
        Result result = null;
        try {
            result = serverGroupService.resetMaster(serverGroupId,subscription_id,mgmBaseForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "系统异常：" + e.getMessage());
            logger.error("设置复制源：", e);
        }
        return result;
    }
	
	@RequestMapping(value = "/{serv_group_id}/maintenance", method = RequestMethod.PUT)
    @ResponseBody
    @OperateLog(action = "维护状态变更")
    public Result maintenance(@PathVariable("serv_group_id") String serverGroupId, 
    		@RequestHeader(value ="subscription_id" ,required=false) String subscription_id,
    		@RequestBody MgmBaseForm mgmBaseForm,HttpServletResponse response) {
        Result result = null;
        try {
            result = serverGroupService.maintenance(serverGroupId,subscription_id,mgmBaseForm);
            if (result.getCode() != Result.SUCCESS) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                return result;
            }
            response.setStatus(HttpStatus.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result = Result.failure(HttpStatus.SC_INTERNAL_SERVER_ERROR, "系统异常：" + e.getMessage());
            logger.error("维护状态变更：", e);
        }
        return result;
    }
}
