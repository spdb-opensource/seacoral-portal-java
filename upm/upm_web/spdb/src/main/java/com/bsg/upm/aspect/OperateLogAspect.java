package com.bsg.upm.aspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bsg.upm.annotation.OperateLog;
import com.bsg.upm.constant.Consts;
import com.bsg.upm.domain.DictDO;
import com.bsg.upm.domain.DictTypeDO;
import com.bsg.upm.domain.GroupDO;
import com.bsg.upm.domain.OperateLogDO;
import com.bsg.upm.domain.RoleDO;
import com.bsg.upm.domain.SanDO;
import com.bsg.upm.domain.ScaleDO;
import com.bsg.upm.domain.UserDO;
import com.bsg.upm.dto.BusinessSubsystemDTO;
import com.bsg.upm.dto.BusinessSystemDTO;
import com.bsg.upm.dto.ClusterDTO;
import com.bsg.upm.dto.HostDTO;
import com.bsg.upm.dto.NetworkingDTO;
import com.bsg.upm.dto.OrderGroupDTO;
import com.bsg.upm.dto.RemoteStorageDTO;
import com.bsg.upm.dto.ServerGroupDTO;
import com.bsg.upm.dto.SiteDTO;
import com.bsg.upm.form.BusinessSubsystemForm;
import com.bsg.upm.form.BusinessSystemForm;
import com.bsg.upm.form.ClusterForm;
import com.bsg.upm.form.DatabaseForm;
import com.bsg.upm.form.GroupForm;
import com.bsg.upm.form.HostForm;
import com.bsg.upm.form.LoginForm;
import com.bsg.upm.form.NetworkingForm;
import com.bsg.upm.form.OrderGroupForm;
import com.bsg.upm.form.RemoteStoragePoolForm;
import com.bsg.upm.form.RoleCfgAppForm;
import com.bsg.upm.form.RoleForm;
import com.bsg.upm.form.RemoteStorageForm;
import com.bsg.upm.form.ScaleForm;
import com.bsg.upm.form.ServerGroupUserForm;
import com.bsg.upm.form.SiteForm;
import com.bsg.upm.form.ImageForm;
import com.bsg.upm.form.KeysetForm;
import com.bsg.upm.form.UserForm;
import com.bsg.upm.param.ServeUnitRegisParam;
import com.bsg.upm.query.UnitRebulidParam;
import com.bsg.upm.query.UnitRoleChangeParam;
import com.bsg.upm.service.BusinessSubsystemService;
import com.bsg.upm.service.BusinessSystemService;
import com.bsg.upm.service.ClusterService;
import com.bsg.upm.service.DictService;
import com.bsg.upm.service.DictTypeService;
import com.bsg.upm.service.GroupService;
import com.bsg.upm.service.HostService;
import com.bsg.upm.service.NetworkingService;
import com.bsg.upm.service.OperateLogService;
import com.bsg.upm.service.OrderGroupService;
import com.bsg.upm.service.Result;
import com.bsg.upm.service.RoleService;
import com.bsg.upm.service.RemoteStorageService;
import com.bsg.upm.service.ScaleService;
import com.bsg.upm.service.ServerGroupService;
import com.bsg.upm.service.SiteService;
import com.bsg.upm.service.SystemService;
import com.bsg.upm.service.UserService;
import com.bsg.upm.util.PinyinUtil;

/**
 * 日志切面类
 * 
 * @author HCK
 *
 */
@Aspect
@Component
public class OperateLogAspect {

    protected Logger logger = Logger.getLogger(getClass());

    @Autowired
    private SystemService systemService;

    @Autowired
    private DictTypeService dictTypeService;

    @Autowired
    private DictService dictService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private OperateLogService operateLogService;

    @Autowired
    private NetworkingService networkingService;

    @Autowired
    private RemoteStorageService sanService;

    @Autowired
    private ScaleService scaleService;
    
    @Autowired
    private SiteService siteService;
    
    @Autowired
    private ClusterService clusterService;
    
    @Autowired
    private HostService hostService;
    
    @Autowired
    private OrderGroupService orderGroupService;
    
    @Autowired
	private ServerGroupService serverGroupService;
    
    @Autowired
    private BusinessSystemService businessSystemService;
    
    @Autowired
    private BusinessSubsystemService businessSubsystemService;
    
    @Autowired
    private HttpSession session;

    @Pointcut("execution(public * com.bsg.upm.controller.*.*(..)) ")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint pjp) {
        String module = null;
        String action = null;
        Date nowDate = systemService.getCurrentSqlDateTime();
        long start = System.currentTimeMillis();
        Class<? extends Object> c = pjp.getTarget().getClass();
        OperateLog operateLog = c.getAnnotation(OperateLog.class);
        if (operateLog != null) {
            module = operateLog.module();
        }

        Object[] args = pjp.getArgs();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();

        operateLog = method.getAnnotation(OperateLog.class);
        if (operateLog != null) {
            if (!operateLog.module().equals("")) {
                module = operateLog.module();
            }
            action = operateLog.action();
        }

        String objName = null;
        Long siteId = null;
        String description = null;
        if (StringUtils.isNotBlank(module) && StringUtils.isNotBlank(action)) {
            try {
                switch (module) {
                case "登录":
                    switch (action) {
                    case "登录":
                        LoginForm loginForm = (LoginForm) args[0];
                        objName = loginForm.getUsername();
                        description = "登录";
                        break;
                    default:
                        break;
                    }
                    break;
                case "字典":
                    switch (action) {
                    case "编辑":
                    	String dictId =  (String) args[0];
                        if (dictId != null) {
                            DictDO dictDO = dictService.getDomain(dictId);
                            if (dictDO != null) {
                                DictTypeDO dictTypeDO = dictTypeService.getDomain(dictDO.getDictTypeCode());
                                objName = dictTypeDO.getName() + ":" + dictDO.getName();
                                description = action + "字典[" + objName + "]";
                            }
                        }
                        break;
                    default:
                        break;
                    }
                    break;
                case "业务系统":
                    switch (action) {
                    case "新增":
                    	BusinessSystemForm businessSystemForm = (BusinessSystemForm) args[0];
                        objName = businessSystemForm.getName();
                        description = action + "业务系统[" + objName + "]";
                        break;
                    case "编辑":
                    	String businessSystemId = (String) args[0];
                        if (businessSystemId != null) {
                        	BusinessSystemDTO businessSystemDTO = businessSystemService.getBusinessSystem(businessSystemId);
                            if (businessSystemDTO != null) {
                                objName = businessSystemDTO.getName();
                                description = action + "业务系统[" + objName + "]";
                            }
                        }
                        break;
                    case "删除":
                    	String businessSystemId1 = (String) args[0];
                        if (businessSystemId1 != null) {
                        	BusinessSystemDTO businessSystemDTO1 = businessSystemService.getBusinessSystem(businessSystemId1);
                            if (businessSystemDTO1 != null) {
                                objName = businessSystemDTO1.getName();
                                description = action + "业务系统[" + objName + "]";
                            }
                        }
                        break;
                    default:
                        break;
                    }
                    break;
                case "业务子系统":
                    switch (action) {
                    case "新增":
                    	BusinessSubsystemForm businessSystemForm = (BusinessSubsystemForm) args[0];
                        objName = businessSystemForm.getName();
                        description = action + "业务子系统[" + objName + "]";
                        break;
                    case "编辑":
                    	String businessSystemId = (String) args[0];
                        if (businessSystemId != null) {
                        	BusinessSubsystemDTO businessSystemDTO = businessSubsystemService.getBusinessSubsystem(businessSystemId);
                            if (businessSystemDTO != null) {
                                objName = businessSystemDTO.getName();
                                description = action + "业务子系统[" + objName + "]";
                            }
                        }
                        break;
                    case "删除":
                    	String businessSystemId1 = (String) args[0];
                        if (businessSystemId1 != null) {
                        	BusinessSubsystemDTO businessSystemDTO1 = businessSubsystemService.getBusinessSubsystem(businessSystemId1);
                            if (businessSystemDTO1 != null) {
                                objName = businessSystemDTO1.getName();
                                description = action + "业务子系统[" + objName + "]";
                            }
                        }
                        break;
                    default:
                        break;
                    }
                    break;
                case "组别":
                    switch (action) {
                    case "新增":
                        GroupForm form = (GroupForm) args[0];
                        objName = form.getName();
                        description = action + "组别[" + objName + "]";
                        break;
                    case "编辑":
                    	String groupId1 = (String) args[0];
                        if (groupId1 != null) {
                            GroupDO groupDO1 = groupService.getDomain(groupId1);
                            if (groupDO1 != null) {
                                objName = groupDO1.getName();
                                description = action + "组别[" + objName + "]";
                            }
                        }
                        break;
                    case "删除":
                    	String groupId = (String) args[0];
                        if (groupId != null) {
                            GroupDO groupDO = groupService.getDomain(groupId);
                            if (groupDO != null) {
                                objName = groupDO.getName();
                                description = action + "组别[" + objName + "]";
                            }
                        }
                        break;
                
                    case "配置用户":
                        groupId = (String) args[0];
                        if (groupId != null) {
                            GroupDO groupDO = groupService.getDomain(groupId);
                            if (groupDO != null) {
                                objName = groupDO.getName();
                                description = "配置组别用户[" + objName + "]";
                            }
                        }
                        break;
                    default:
                        break;
                    }
                    break;
                case "角色":
                    switch (action) {
                    case "新增":
                        RoleForm form = (RoleForm) args[0];
                        objName = form.getName();
                        description = action + "角色[" + objName + "]";
                        break;
                    case "编辑":
                    	String roleId1 = (String) args[0];
                        if (roleId1 != null) {
                            RoleDO roleDO1 = roleService.getDomain(roleId1);
                            if (roleDO1 != null) {
                                objName = roleDO1.getName();
                                description = action + "角色[" + objName + "]";
                            }
                        }
                        break;
                    case "删除":
                    	String roleId = (String) args[0];
                        if (roleId != null) {
                            RoleDO roleDO = roleService.getDomain(roleId);
                            if (roleDO != null) {
                                objName = roleDO.getName();
                                description = action + "角色[" + objName + "]";
                            }
                        }
                        break;
                    default:
                        break;
                    }
                    break;
                case "角色权限配置-功能配置":
                    switch (action) {
                    case "新增":
                    	/*RoleCfgAppForm cfgAppForm=(RoleCfgAppForm) args[0];
                    	objName = cfgAppForm.getAppIds();*/
                    case "编辑":
                    	String roleId = (String) args[0];
                        RoleDO roleDO = roleService.getDomain(roleId);
                        if (roleDO != null) {
                            objName = roleDO.getName();
                            description = "配置角色[" + objName + "]权限-功能";
                        }
                        break;
                    default:
                        break;
                    }
                    break;
                case "角色权限配置-数据可见范围配置":
                    switch (action) {
                    case "新增":
                    case "编辑":
                    	String roleId = (String) args[0];
                        if (roleId != null) {
                            RoleDO roleDO = roleService.getDomain(roleId);
                            if (roleDO != null) {
                                objName = roleDO.getName();
                                description = "配置角色[" + objName + "]权限-数据可见范围";
                            }
                        }
                        break;
                    default:
                        break;
                    }
                    break;
                case "角色权限配置-资源配置":
                    switch (action) {
                    case "新增":
                    case "编辑":
                    	String roleId = (String) args[0];
                        if (roleId != null) {
                            RoleDO roleDO = roleService.getDomain(roleId);
                            if (roleDO != null) {
                                objName = roleDO.getName();
                                description = "配置角色[" + objName + "]权限-资源";
                            }
                        }
                        break;
                    default:
                        break;
                    }
                    break;
                case "角色权限配置-报表配置":
                    switch (action) {
                    case "新增":
                    case "编辑":
                    	String roleId = (String) args[0];
                        if (roleId != null) {
                            RoleDO roleDO = roleService.getDomain(roleId);
                            if (roleDO != null) {
                                objName = roleDO.getName();
                                description = "配置角色[" + objName + "]权限-报表";
                            }
                        }
                        break;
                    default:
                        break;
                    }
                    break;
                case "角色权限配置-其他配置":
                    switch (action) {
                    case "新增":
                    case "编辑":
                    	String roleId = (String) args[0];
                        if (roleId != null) {
                            RoleDO roleDO = roleService.getDomain(roleId);
                            if (roleDO != null) {
                                objName = roleDO.getName();
                                description = "配置角色[" + objName + "]权限-其他";
                            }
                        }
                        break;
                    default:
                        break;
                    }
                    break;
                case "用户":
                    switch (action) {
                    case "新增":
                        UserForm form = (UserForm) args[0];
                        objName = form.getUsername();
                        description = action + "用户[" + objName + "]";
                        break;
                    case "编辑":
                    	String username1 = (String) args[0];
                        if (username1 != null) {
                            UserDO userDO1 = userService.getDomain(username1);
                            if (userDO1 != null) {
                                objName = userDO1.getUsername();
                                description = action + "用户[" + objName + "]";
                            }
                        }
                        break;
                    case "启用":
                    	String username2 = (String) args[0];
                        if (username2 != null) {
                            UserDO userDO1 = userService.getDomain(username2);
                            if (userDO1 != null) {
                                objName = userDO1.getUsername();
                                description = action + "用户[" + objName + "]";
                            }
                        }
                        break;
                    case "停用":
                        String username = (String) args[0];
                        if (username != null) {
                            UserDO userDO = userService.getDomain(username);
                            if (userDO != null) {
                                objName = userDO.getUsername();
                                description = action + "用户[" + objName + "]";
                            }
                        }
                        break;
                    case "修改密码":
                        username = (String) args[0];
                        if (username != null) {
                            UserDO userDO2 = userService.getDomain(username);
                            if (userDO2 != null) {
                                objName = userDO2.getUsername();
                                description = "用户[" + objName + "]修改密码";
                            }
                        }
                        break;
                    default:
                        break;
                    }
                    break;
                case "规模":
                    switch (action) {
                    case "新增":
                        ScaleForm form = (ScaleForm) args[0];
                        objName = form.getName();
                        description = action + "规模[" + objName + "]";
                        break;
                    case "编辑":
                    	String scaleId1 = (String) args[0];
                        if (scaleId1 != null) {
                            ScaleDO scaleDO1 = scaleService.getDomain(scaleId1);
                            if (scaleDO1 != null) {
                                objName = scaleDO1.getName();
                                description = action + "规模[" + objName + "]";
                            }
                        }
                        break;
                    case "启用":
                    	String scaleId2 = (String) args[0];
                        if (scaleId2 != null) {
                            ScaleDO scaleDO2 = scaleService.getDomain(scaleId2);
                            if (scaleDO2 != null) {
                                objName = scaleDO2.getName();
                                description = action + "规模[" + objName + "]";
                            }
                        }
                        break;
                    case "停用":
                    	String scaleId3 = (String) args[0];
                        if (scaleId3 != null) {
                            ScaleDO scaleDO3 = scaleService.getDomain(scaleId3);
                            if (scaleDO3 != null) {
                                objName = scaleDO3.getName();
                                description = action + "规模[" + objName + "]";
                            }
                        }
                        break;
                    case "删除":
                    	String scaleId = (String) args[0];
                        if (scaleId != null) {
                            ScaleDO scaleDO = scaleService.getDomain(scaleId);
                            if (scaleDO != null) {
                                objName = scaleDO.getName();
                                description = action + "规模[" + objName + "]";
                            }
                        }
                        break;
                    default:
                        break;
                    }
                    break;
                case "站点":
                    switch (action) {
                    case "编辑":
                    	SiteForm form = (SiteForm) args[1];
                        objName = form.getName();
                        description = action + "站点[" + objName + "]";
                        break;
                    case "删除":
                    	String siteId1 = (String) args[0];
                        if (siteId1 != null) {
                        	SiteDTO siteDTO = siteService.getSite(siteId1);
                            if (siteDTO != null) {
                                objName = siteDTO.getName();
                                description = action + "站点[" + objName + "]";
                            }
                        }
                        break;
                    default:
                        break;
                    }
                    break;
                case "集群":
                    switch (action) {
                    case "新增":
                        ClusterForm form = (ClusterForm) args[0];
                        objName = form.getName();
                        description = action + "集群[" + objName + "]";
                        break;
                    case "编辑":
                    	String clusterId = (String) args[0];
                    	if (clusterId != null) {
                        	ClusterDTO clusterDTO = clusterService.getCluster(clusterId);
                            if (clusterDTO != null) {
                                objName = clusterDTO.getName();
                                description = action +"集群[" + objName + "]";
                            }

                    	}
                        break;
                    case "启用":
                    	String clusterId2 = (String) args[0];
                    	if (clusterId2 != null) {
                        	ClusterDTO clusterDTO2 = clusterService.getCluster(clusterId2);
                            if (clusterDTO2 != null) {
                                objName = clusterDTO2.getName();
                                description = action +"集群[" + objName + "]";
                            }

                    	}
                        break;
                    case "停用":
                    	String clusterId3 = (String) args[0];
                    	if (clusterId3 != null) {
                        	ClusterDTO clusterDTO3 = clusterService.getCluster(clusterId3);
                            if (clusterDTO3 != null) {
                                objName = clusterDTO3.getName();
                                description = action +"集群[" + objName + "]";
                            }

                    	}
                        break;
                    case "删除":
                    	String clusterId1 = (String) args[0];
                        if (clusterId1 != null) {
                        	ClusterDTO clusterDTO1 = clusterService.getCluster(clusterId1);
                            if (clusterDTO1 != null) {
                                objName = clusterDTO1.getName();
                                description = action + "集群[" + objName + "]";
                            }
                        }
                        break;
                    case "上传":
                        siteId = (Long) args[0];
                        objName = "EXCEL";
                        description = "上传集群模板";
                        break;
                    default:
                        break;
                    }
                    break;
                case "网段":
                    switch (action) {
                    case "新增":
                        NetworkingForm form = (NetworkingForm) args[0];
                        objName = form.getStartIp()+"--"+form.getEndIp();
                        description = action + "网段[" + objName + "]";
//                        siteId = form.getSiteId();
                        break;
                    case "编辑":
                    	String networkingId = (String) args[0];
                    	if(networkingId!=null) {
                    		NetworkingDTO networkingDTO = networkingService.getNetWorking(networkingId);
                            if (networkingDTO != null) {
                                objName = networkingDTO.getStartIp()+"--"+networkingDTO.getEndIp();
                                description = action +"网段[" + objName + "]";
                            }
                    	}
                        break;
                    case "入库":
                    case "出库":
                    case "启用":
                    	String networkingId2 = (String) args[0];
                    	if(networkingId2!=null) {
                    		NetworkingDTO networkingDTO2 = networkingService.getNetWorking(networkingId2);
                            if (networkingDTO2 != null) {
                                objName = networkingDTO2.getStartIp()+"--"+networkingDTO2.getEndIp();
                                description = action +"网段[" + objName + "]";
                            }
                    	}
                        break;
                    case "停用":
                    	String networkingId3 = (String) args[0];
                    	if(networkingId3!=null) {
                    		NetworkingDTO networkingDTO3 = networkingService.getNetWorking(networkingId3);
                            if (networkingDTO3 != null) {
                                objName = networkingDTO3.getStartIp()+"--"+networkingDTO3.getEndIp();
                                description = action +"网段[" + objName + "]";
                            }
                    	}
                        break;
                    case "删除":
                       String networkingId1=(String) args[0];
                       if(networkingId1!=null) {
                    	   NetworkingDTO networkingDTO1 = networkingService.getNetWorking(networkingId1);
                           if (networkingDTO1 != null) {
                               objName = networkingDTO1.getStartIp()+"--"+networkingDTO1.getEndIp();
                               description = action +"网段[" + objName + "]";
                           }
                       }
                        break;
                    case "上传":
                        siteId = (Long) args[0];
                        objName = "EXCEL";
                        description = "上传网段模板";
                        break;
                    default:
                        break;
                    }
                    break;
                case "软件镜像":
                    switch (action) {
                    case "新增":
                        ImageForm form = (ImageForm) args[0];
                        objName = form.getType() + form.getVersion().getMajor() + "." + form.getVersion().getMinor() + "."
                                + form.getVersion().getPatch() + "." + form.getVersion().getBuild();
                        description = action + "软件镜像[" + objName + "]";
                        break;
                    case "启用":
                    case "停用":
                    case "删除":
                    default:
                        break;
                    }
                    break;
                case "SAN":
                    switch (action) {
                    case "新增":
                        RemoteStorageForm form = (RemoteStorageForm) args[0];
                        objName = form.getName();
                        description = action + "SAN[" + objName + "]";
//                        siteId = Long.valueOf(form.getSiteId());
                        break;
                    case "启用":
                    	String sanId2 = (String) args[0];
                        if (sanId2 != null) {
                        	RemoteStorageDTO clusterDTO2 = sanService.getRemoteStorage(sanId2);
                            if (clusterDTO2 != null) {
                                objName = clusterDTO2.getName();
                                description = action + "SAN[" + objName + "]";
                            }
                        }
                        break;
                    case "停用":
                    	String sanId3 = (String) args[0];
                        if (sanId3 != null) {
                        	RemoteStorageDTO clusterDTO3 = sanService.getRemoteStorage(sanId3);
                            if (clusterDTO3 != null) {
                                objName = clusterDTO3.getName();
                                description = action + "SAN[" + objName + "]";
                            }
                        }
                        break;
                    case "删除":
                    	String sanId = (String) args[0];
                        if (sanId != null) {
                        	RemoteStorageDTO clusterDTO1 = sanService.getRemoteStorage(sanId);
                            if (clusterDTO1 != null) {
                                objName = clusterDTO1.getName();
                                description = action + "SAN[" + objName + "]";
                            }
                        }
                        break;
                    default:
                        break;
                    }
                    break;
                case "RG":
                    switch (action) {
                    case "新增":
                    	RemoteStoragePoolForm remoteStoragePoolForm=(RemoteStoragePoolForm) args[0];
                    	objName = remoteStoragePoolForm.getCode();
                        description = action + "RG[" + objName + "]";
                        break;
                    case "启用":
                    case "停用":
                    case "删除":
                    	/*String poolCode = (String) args[1];
                        if (poolCode != null) {
                        	ClusterDTO clusterDTO1 = clusterService.getCluster(poolCode);
                            if (clusterDTO1 != null) {
                                objName = clusterDTO1.getName();
                                description = action + "集群[" + objName + "]";
                            }
                        }*/
                        break;
                    default:
                        break;
                    }
                    break;
                case "主机":
                    switch (action) {
                    case "注册":
                    	HostForm hostForm=(HostForm) args[0];
                    	objName = hostForm.getIp();
                        description = action + "主机[" + objName + "]";
                        break;
                    case "编辑":
                    	String hostId = (String) args[0];
                    	HostDTO hostDTO = hostService.getHost(hostId);
                        if (hostId != null) {
                            objName = hostDTO.getName();
                            description = action +"主机[" + objName + "]";
                        }
                        break;
                    case "入库":
                    case "出库":
                    case "启用":
                    	String hostId2 = (String) args[0];
                    	HostDTO hostDTO2 = hostService.getHost(hostId2);
                        if (hostId2 != null) {
                            objName = hostDTO2.getName();
                            description = action +"主机[" + objName + "]";
                        }
                        break;
                    case "停用":
                    	String hostId3 = (String) args[0];
                    	HostDTO hostDTO3 = hostService.getHost(hostId3);
                        if (hostId3 != null) {
                            objName = hostDTO3.getName();
                            description = action +"主机[" + objName + "]";
                        }
                        break;
                    case "删除":
                    	String hostId1 = (String) args[0];
                        if (hostId1 != null) {
                        	HostDTO hostDTO1 = hostService.getHost(hostId1);
                        	if(hostDTO1!=null) {
                        		objName = hostDTO1.getName();
                                description = action +"主机[" + objName + "]";
                        	}
                        }
                        break;
                    case "上传":
                        siteId = (Long) args[0];
                        objName = "EXCEL";
                        description = "上传主机模板";
                        break;
                    default:
                        break;
                    }
                    break;
                case "工单":
                    switch (action) {
                    case "新增":
                        OrderGroupForm form = (OrderGroupForm) args[0];
                        Integer cnt = (Integer) args[1];
                        if (Consts.SERV_TYPE_MYSQL.equals(form.getType())) {
                            module = "MYSQL申请单";
                            objName = form.getName();
                            description = action + "MYSQL申请单[" + objName + "]";
                        }
                        break;
                    case "编辑":
                    	String orderGroupId = (String) args[0];
                    	OrderGroupDTO orderGroupDTO = orderGroupService.getOrderGroup(orderGroupId);
                        if (orderGroupDTO != null) {
                            objName = orderGroupDTO.getName();
                            description = action +"MYSQL申请单[" + objName + "]";
                        }
                        break;
                    case "克隆":
                    case "审批":
                    	String orderGroupId2 = (String) args[0];
                    	OrderGroupDTO orderGroupDTO2 = orderGroupService.getOrderGroup(orderGroupId2);
                        if (orderGroupDTO2 != null) {
                            objName = orderGroupDTO2.getName();
                            description = action +"MYSQL申请单[" + objName + "]";
                        }
                        break;
                    case "执行":
                    	String orderGroupId3 = (String) args[0];
                    	OrderGroupDTO orderGroupDTO3 = orderGroupService.getOrderGroup(orderGroupId3);
                        if (orderGroupDTO3 != null) {
                            objName = orderGroupDTO3.getName();
                            description = action +"MYSQL申请单[" + objName + "]";
                        }
                        break;
                    case "删除":
                    	String orderGroupId1 = (String) args[0];
                    	OrderGroupDTO orderGroupDTO1 = orderGroupService.getOrderGroup(orderGroupId1);
                        if (orderGroupDTO1 != null) {
                            objName = orderGroupDTO1.getName();
                            description = action +"MYSQL申请单[" + objName + "]";
                        }
                        break;
                    default:
                        break;
                    }
                    break;
                case "工单(云运行平台)":
                    switch (action) {
                    case "新增":
                        OrderGroupForm form = (OrderGroupForm) args[0];
                        Integer cnt = (Integer) args[1];
                        /*if (Consts.SERV_GROUP_TYPE_UPSQL.equals(form.getType())) {
                            module = "UPSQL申请单(云运行平台)";
                            objName = form.getName();
                            if (cnt == 1) {
                                objName = objName + "001";
                                description = action + "UPSQL申请单(云运行平台)[" + objName + "]";
                            } else {
                                description = "批量新增UPSQL工单(云运行平台)[" + objName + "]";
                            }
                        }*/
                        break;
                    case "编辑":
                    case "克隆":
                    case "审批":
                    case "执行":
                    case "重做":
                    case "删除":
                        
                        break;
                    default:
                        break;
                    }
                    break;
                case "服务组":
                    switch (action) {
                    case "启动":
                        String serverGroupId=(String) args[0];
                        if(serverGroupId!=null) {
                        	 ServerGroupDTO serverGroupDTO=serverGroupService.getServerGroupDTO(serverGroupId);
                        	 if(serverGroupDTO!=null) {
                        		 objName = serverGroupDTO.getName();
                                 description = action +"服务组[" + objName + "]"; 
                        	 }
                        }
                        break;
                    case "扩容":
                    	String serverGroupId1=(String) args[0];
                        if(serverGroupId1!=null) {
                        	 ServerGroupDTO serverGroupDTO1=serverGroupService.getServerGroupDTO(serverGroupId1);
                        	 if(serverGroupDTO1!=null) {
                        		 objName = serverGroupDTO1.getName();
                                 description = action +"服务组[" + objName + "]"; 
                        	 }
                        }
                        break;
                    case "停止":
                    	String serverGroupId2=(String) args[0];
                        if(serverGroupId2!=null) {
                        	 ServerGroupDTO serverGroupDTO2=serverGroupService.getServerGroupDTO(serverGroupId2);
                        	 if(serverGroupDTO2!=null) {
                        		 objName = serverGroupDTO2.getName();
                                 description = action +"服务组[" + objName + "]"; 
                        	 }
                        }
                        break;
                    case "升级":
                    	String serverGroupId3=(String) args[0];
                        if(serverGroupId3!=null) {
                        	 ServerGroupDTO serverGroupDTO3=serverGroupService.getServerGroupDTO(serverGroupId3);
                        	 if(serverGroupDTO3!=null) {
                        		 objName = serverGroupDTO3.getName();
                                 description = action +"服务组[" + objName + "]"; 
                        	 }
                        }
                        break;
                    case "备份":
                    	String serverGroupId4=(String) args[0];
                        if(serverGroupId4!=null) {
                        	 ServerGroupDTO serverGroupDTO4=serverGroupService.getServerGroupDTO(serverGroupId4);
                        	 if(serverGroupDTO4!=null) {
                        		 objName = serverGroupDTO4.getName();
                                 description = action +"服务组[" + objName + "]"; 
                        	 }
                        }
                        break;
                    case "删除":
                    	String serverGroupId5=(String) args[0];
                        if(serverGroupId5!=null) {
                        	 ServerGroupDTO serverGroupDTO5=serverGroupService.getServerGroupDTO(serverGroupId5);
                        	 if(serverGroupDTO5!=null) {
                        		 objName = serverGroupDTO5.getName();
                                 description = action +"服务组[" + objName + "]"; 
                        	 }
                        }
                        break;
                    case "单元启动":
                    	String serverGroupIda=(String) args[0];
                    	String serverGroupId6=(String) args[1];
                    	String description1="";
                    	if(serverGroupIda!=null) {
                       	 	ServerGroupDTO serverGroupDTOa=serverGroupService.getServerGroupDTO(serverGroupIda);
	                       	if(serverGroupDTOa!=null) {
	                       		objName = serverGroupDTOa.getName();
	                            description1 = "服务组[" + objName + "]"; 
	                       	}
	                       	if(serverGroupId6!=null) {
	                   		 objName = serverGroupId6.split("-")[2];
	                            description =description1+ "单元[" + objName + "]" +"启动"; 
	                       	}
                        }
                        
                        break;
                    case "单元停止":
                    	String serverGroupIdb=(String) args[0];
                    	String serverGroupId7=(String) args[1];
                    	String description2="";
                    	if(serverGroupIdb!=null) {
                       	 	ServerGroupDTO serverGroupDTOb=serverGroupService.getServerGroupDTO(serverGroupIdb);
	                       	if(serverGroupDTOb!=null) {
	                       		objName = serverGroupDTOb.getName();
	                       		description2 = "服务组[" + objName + "]"; 
	                       	}
	                       	if(serverGroupId7!=null) {
	                   		 objName = serverGroupId7.split("-")[2];
	                            description =description2+ "单元[" + objName + "]"+"停止"; 
	                       	}
                        }
                        break;
                    case "单元备份":
                    	String serverGroupIdc=(String) args[0];
                    	String serverGroupId8=(String) args[1];
                    	String description3="";
                    	if(serverGroupIdc!=null) {
                       	 	ServerGroupDTO serverGroupDTOc=serverGroupService.getServerGroupDTO(serverGroupIdc);
	                       	if(serverGroupDTOc!=null) {
	                       		objName = serverGroupDTOc.getName();
	                       		description3 ="服务组[" + objName + "]"; 
	                       	}
	                       	if(serverGroupId8!=null) {
	                   		 objName = serverGroupId8.split("-")[2];
	                            description =description3+ "单元[" + objName + "]"+"备份"; 
	                       	}
                        }
                        break;
                    case "单元重建":
                    	UnitRebulidParam unitRebulidParam=(UnitRebulidParam) args[0];
                    	String serverGroupId9=unitRebulidParam.getUnitId();
                    	String serverGroupIdd=unitRebulidParam.getAppId();
                    	String description4="";
                    	if(serverGroupIdd!=null) {
                       	 	ServerGroupDTO serverGroupDTOd=serverGroupService.getServerGroupDTO(serverGroupIdd);
	                       	if(serverGroupDTOd!=null) {
	                       		objName = serverGroupDTOd.getName();
	                       		description4 ="服务组[" + objName + "]"; 
	                       	}
	                       	if(serverGroupId9!=null) {
	                   		 objName = serverGroupId9.split("-")[2];
	                            description =description4+ "单元[" + objName + "]重建"; 
	                       	}
                        }
                        break;
                    case "单元角色设置为主":
                    	UnitRoleChangeParam unitRoleChangeParam=(UnitRoleChangeParam) args[0];
                    	String serverGroupId10=unitRoleChangeParam.getAppId();
                        if(serverGroupId10!=null) {
                        	 ServerGroupDTO serverGroupDTO10=serverGroupService.getServerGroupDTO(serverGroupId10);
                        	 if(serverGroupDTO10!=null) {
                        		 objName = serverGroupDTO10.getName();
                                 description = "服务组[" + objName + "]"+action; 
                        	 }
                        }
                        break;
                    case "单元角色设置为从":
                    	String serverGroupId20=(String) args[0];
                    	UnitRoleChangeParam unitRoleParam1=(UnitRoleChangeParam) args[1];
                    	if(unitRoleParam1!=null) {
                    		ServerGroupDTO serverGroupDTO20=serverGroupService.getServerGroupDTO(unitRoleParam1.getAppId());
                    		if(serverGroupId20!=null) {
                           	 if(serverGroupDTO20!=null) {
                           		 objName = serverGroupDTO20.getName();
                                    description ="服务组[" + objName + "]单元["+serverGroupId20.split("-")[2]+"]角色设置为从"; 
                           	 }
                           }
                    	}
                        
                        break;
                    case "单元迁移":
                    	UnitRebulidParam unitRebulidParam1=(UnitRebulidParam) args[0];
                    	String serverGroupId11=unitRebulidParam1.getUnitId();
                    	String serverGroupIde=unitRebulidParam1.getAppId();
                    	String description5="";
                    	if(serverGroupIde!=null) {
                       	 	ServerGroupDTO serverGroupDTOe=serverGroupService.getServerGroupDTO(serverGroupIde);
	                       	if(serverGroupDTOe!=null) {
	                       		objName = serverGroupDTOe.getName();
	                       		description5 = "服务组[" + objName + "]"; 
	                       	}
	                       	if(serverGroupId11!=null) {
	                   		 objName = serverGroupId11.split("-")[2];
	                            description =description5+ "单元[" + objName + "]迁移"; 
	                       	}
                        }
                        break;
                    case "库新增":
                    	String serverGroupId12=(String) args[0];
                    	DatabaseForm databaseForm=(DatabaseForm) args[1];
                        if(serverGroupId12!=null) {
                        	 ServerGroupDTO serverGroupDTO12=serverGroupService.getServerGroupDTO(serverGroupId12);
                        	 if(serverGroupDTO12!=null) {
                        		 objName = serverGroupDTO12.getName();
                                 description ="服务组[" + objName + "]新增库["+databaseForm.getName()+"]"; 
                        	 }
                        }
                        break;
                    case "库删除":
                    	String serverGroupId13=(String) args[0];
                    	String databaseName=(String) args[1];
                        if(serverGroupId13!=null) {
                        	 ServerGroupDTO serverGroupDTO13=serverGroupService.getServerGroupDTO(serverGroupId13);
                        	 if(serverGroupDTO13!=null) {
                        		 objName = serverGroupDTO13.getName();
                                 description ="服务组[" + objName + "]库["+databaseName+"]删除"; 
                        	 }
                        }
                        break;
                    case "用户新增":
                    	String serverGroupId14=(String) args[0];
                    	ServerGroupUserForm serverGroupUserForm=(ServerGroupUserForm) args[1];
                        if(serverGroupId14!=null) {
                        	 ServerGroupDTO serverGroupDTO14=serverGroupService.getServerGroupDTO(serverGroupId14);
                        	 if(serverGroupDTO14!=null) {
                        		 objName = serverGroupDTO14.getName();
                                 description = "服务组[" + objName + "]新增用户["+serverGroupUserForm.getUsername()+"]"; 
                        	 }
                        }
                        break;
                    case "用户编辑":
                    	String serverGroupId15=(String) args[0];
                    	String uName=(String) args[1];
                        if(serverGroupId15!=null) {
                        	 ServerGroupDTO serverGroupDTO15=serverGroupService.getServerGroupDTO(serverGroupId15);
                        	 if(serverGroupDTO15!=null) {
                        		 objName = serverGroupDTO15.getName();
                                 description = "服务组[" + objName + "]编辑用户["+uName+"]"; 
                        	 }
                        }
                        break;
                    case "用户删除":
                    	String serverGroupId16=(String) args[0];
                    	String uName1=(String) args[1];
                        if(serverGroupId16!=null) {
                        	 ServerGroupDTO serverGroupDTO16=serverGroupService.getServerGroupDTO(serverGroupId16);
                        	 if(serverGroupDTO16!=null) {
                        		 objName = serverGroupDTO16.getName();
                                 description = "服务组[" + objName + "]删除用户["+uName1+"]"; 
                        	 }
                        }
                        break;
                    case "配置文件编辑":
                    	String serverGroupId17=(String) args[0];
                    	KeysetForm keysetForm=(KeysetForm) args[1];
                        if(serverGroupId17!=null) {
                        	 ServerGroupDTO serverGroupDTO17=serverGroupService.getServerGroupDTO(serverGroupId17);
                        	 if(serverGroupDTO17!=null) {
                        		 objName = serverGroupDTO17.getName();
                                 description = "服务组[" + objName + "]配置文件["+keysetForm.getKey()+"]编辑"; 
                        	 }
                        }
                        break;
                    case "单元监控注册":
                    	ServeUnitRegisParam serveUnitRegisParam=(ServeUnitRegisParam) args[0];
                    	String serverGroupId18=serveUnitRegisParam.getTag();
                    	if(serverGroupId18!=null) {
                       	 	ServerGroupDTO serverGroupDTOf=serverGroupService.getServerGroupDTO(serverGroupId18);
	                       	if(serverGroupDTOf!=null) {
	                       		objName = serverGroupDTOf.getName();
	                       		description = "服务组[" + objName + "]"+action; 
	                       	}
                        }
                        break;
                   
                    case "变更":
                    	String serverGroupId19=(String) args[0];
                        if(serverGroupId19!=null) {
                        	 ServerGroupDTO serverGroupDTO19=serverGroupService.getServerGroupDTO(serverGroupId19);
                        	 if(serverGroupDTO19!=null) {
                        		 objName = serverGroupDTO19.getName();
                                 description = action +"服务组[" + objName + "]"; 
                        	 }
                        }
                        break;
                        
                    default:
                        break;
                    }
                    break;
                }
            } catch (Exception e) {
                logger.error("get operate info exception:", e);
            }
        }

        Object object = null;
        boolean success = true;
        String errorMsg = null;
        try {
            object = pjp.proceed(args);
        } catch (Throwable e) {
            success = false;
        }
        if (object != null && object instanceof Result) {
            if (Result.SUCCESS != ((Result) object).getCode()) {
                success = false;
                errorMsg = ((Result) object).getMsg();
            }
        }

        if (StringUtils.isNotBlank(objName)) {
            try {
                OperateLogDO operateLogDO = new OperateLogDO();
//                operateLogDO.setId(PinyinUtil.getUuid());
                operateLogDO.setGmtCreate(nowDate);
                operateLogDO.setCreator(getUsername());
                operateLogDO.setObjType(module);
                operateLogDO.setAction(action);
                operateLogDO.setObjName(objName);
                operateLogDO.setDescription(description);
                if (siteId != null) {
                    operateLogDO.setSiteId(siteId);
                }
                operateLogDO.setSuccess(success);
                operateLogDO.setErrorMsg(errorMsg);
                long end = System.currentTimeMillis();
                operateLogDO.setUptime(end - start);
                operateLogService.save(operateLogDO);
            } catch (Exception e) {
                logger.error("save operate log exception:", e);
            }
        }
        return object;
    }

    private String getUsername() {
        if (session.getAttribute("user") != null) {
            UserDO userDO = (UserDO) session.getAttribute("user");
            return userDO.getUsername();
        }
        return "admin";
    }
}
