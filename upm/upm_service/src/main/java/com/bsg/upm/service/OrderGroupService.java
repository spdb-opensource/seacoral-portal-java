package com.bsg.upm.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.OrderGroupCheck;
import com.bsg.upm.check.resultenum.NetworkingChkRsEnum;
import com.bsg.upm.check.resultenum.OrderGroupChkRsEnum;
import com.bsg.upm.constant.Consts;
import com.bsg.upm.constant.DataCategoryConsts;
import com.bsg.upm.constant.DictConsts;
import com.bsg.upm.constant.DictTypeConsts;
import com.bsg.upm.dao.BusinessSubsystemDAO;
import com.bsg.upm.dao.BusinessSystemDAO;
import com.bsg.upm.domain.BusinessSubsystemDO;
import com.bsg.upm.domain.BusinessSystemDO;
import com.bsg.upm.domain.CmhaProxyDO;
import com.bsg.upm.domain.DbUserDO;
import com.bsg.upm.domain.OrderDO;
import com.bsg.upm.domain.OrderGroupDO;
import com.bsg.upm.domain.RoleCfgOthersDO;
import com.bsg.upm.domain.SchemaDO;
import com.bsg.upm.domain.ServGroupDO;
import com.bsg.upm.domain.UserDO;
import com.bsg.upm.dto.ArchDTO;
import com.bsg.upm.dto.BaseDTO.TypeDTO;
import com.bsg.upm.dto.CmhaProxyDTO;
import com.bsg.upm.dto.DbUserDTO;
import com.bsg.upm.dto.OrderDTO;
import com.bsg.upm.dto.OrderGroupDTO;
import com.bsg.upm.dto.SchemaDTO;
import com.bsg.upm.dto.SiteDTO;
import com.bsg.upm.dto.UserDTO;
import com.bsg.upm.dto.VersionDTO;
import com.bsg.upm.exception.CallingInterfaceException;
import com.bsg.upm.exception.ConnectConsulException;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.exception.ServiceNotFoundException;
import com.bsg.upm.form.AuditForm;
import com.bsg.upm.form.OrderForm;
import com.bsg.upm.form.OrderGroupForm;
import com.bsg.upm.form.ServerGroupForm;
import com.bsg.upm.form.UserForm;
import com.bsg.upm.form.OrderForm.ArchForm;
import com.bsg.upm.form.OrderForm.ProxyForm;
import com.bsg.upm.form.OrderForm.SchemaForm;
import com.bsg.upm.mgm.api.MgmApi;
import com.bsg.upm.mgm.model.MgmImage;
import com.bsg.upm.mgm.model.MgmSaveServerGroup;
import com.bsg.upm.mgm.model.MgmServerGroup;
import com.bsg.upm.mgm.model.MgmSite;
import com.bsg.upm.mgm.query.MgmImageQuery;
import com.bsg.upm.mgm.query.MgmServerGroupDetailQuery;
import com.bsg.upm.mgm.query.MgmServerGroupQuery;
import com.bsg.upm.mgm.query.MgmSiteQuery;
import com.bsg.upm.param.OrderGroupDAOParam;
import com.bsg.upm.query.OrderGroupParam;
import com.bsg.upm.service.ServerGroupService.CreateTask;
import com.bsg.upm.service.ServerGroupService.DeleteTask;
import com.bsg.upm.service.ServerGroupService.ImageTask;
import com.bsg.upm.service.ServerGroupService.ScaleTask;
import com.bsg.upm.util.DateUtils;
import com.bsg.upm.util.PinyinUtil;
import com.ecwid.consul.v1.acl.model.NewAcl;
import com.google.gson.Gson;

@Service
public class OrderGroupService extends BaseService {

    @Autowired
    private OrderGroupCheck orderGroupCheck;

    @Autowired
    private ServerGroupService serverGroupService;
    
    
    @Autowired
    private ServGroupService servGroupService;
    
    @Autowired
    private BusinessSystemDAO businessSystemDAO;
    
    @Autowired
    private BusinessSubsystemDAO businessSubsystemDAO;

    @Transactional
    public Result list(OrderGroupParam orderGroupParam) throws ServiceException {
        try {
            List<OrderGroupDTO> orderGroupDTOs = new ArrayList<>();
            OrderGroupDAOParam orderGroupDAOParam = new OrderGroupDAOParam();
            BeanUtils.copyProperties(orderGroupParam, orderGroupDAOParam);
            List<OrderGroupDO> orderGroupDOs = orderGroupDAO.list(orderGroupDAOParam);

            String username = orderGroupParam.getOwner();
            if (StringUtils.isBlank(username)) {
                username = getUsername();
            }
            List<String> usernames = listVisiableUserData(username, DataCategoryConsts.ORDER_GROUP);
            if (usernames == null) {
                return Result.success(orderGroupDTOs);
            }

            List<OrderGroupDO> visibleOrderGroupDOs = new ArrayList<>();
            for (OrderGroupDO orderGroupDO : orderGroupDOs) {
                if (usernames.contains(orderGroupDO.getOwner())) {
                    visibleOrderGroupDOs.add(orderGroupDO);
                }
            }

            MgmSiteQuery mgmSiteQuery = new MgmSiteQuery();
            List<MgmSite> mgmSites = mgmApi.listSite(mgmSiteQuery);

            for (OrderGroupDO orderGroupDO : visibleOrderGroupDOs) {
                OrderGroupDTO orderGroupDTO = getShowBaseDTO(orderGroupDO, mgmSites);
                orderGroupDTOs.add(orderGroupDTO);
            }
            return Result.success(orderGroupDTOs);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    public Result get(String orderGroupId) throws ServiceException {
        OrderGroupDTO orderGroupDTO = null;
        try {
        	if(nullCheck(orderGroupId)) {
        		return Result.failure(CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            OrderGroupDO orderGroupDO = orderGroupDAO.get(orderGroupId);

            MgmSiteQuery mgmSiteQuery = new MgmSiteQuery();
            List<MgmSite> mgmSites = mgmApi.listSite(mgmSiteQuery);
            if (orderGroupDO != null) {
                orderGroupDTO = getShowBaseDTO(orderGroupDO, mgmSites);
            }

            return Result.success(orderGroupDTO);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    @Transactional
    public OrderGroupDTO getOrderGroup(String orderGroupId) throws ServiceException {
        OrderGroupDTO orderGroupDTO = null;
        try {
        	if(nullCheck(orderGroupId)) {
        		return orderGroupDTO;
        	}
            OrderGroupDO orderGroupDO = orderGroupDAO.get(orderGroupId);

            MgmSiteQuery mgmSiteQuery = new MgmSiteQuery();
            List<MgmSite> mgmSites = mgmApi.listSite(mgmSiteQuery);
            if (orderGroupDO != null) {
                orderGroupDTO = getShowBaseDTO(orderGroupDO, mgmSites);
            }

            return orderGroupDTO;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result save(OrderGroupForm orderGroupForm, int cnt, String username,String subscription_id) throws ServiceException {
    	ServGroupDO servGroupDO = null;
    	try {
            CheckResult checkResult = orderGroupCheck.checkSave(orderGroupForm, cnt);
            if (checkResult.getCode() != OrderGroupChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            
            Date nowDate = systemDAO.getCurrentSqlDateTime();
            username=getUsername();
            BusinessSystemDO businessSystemDO = businessSystemDAO
                    .getByNameAndOwner(orderGroupForm.getBusinessSystemName(), username);
            if (businessSystemDO == null) {
                businessSystemDO = new BusinessSystemDO();
                businessSystemDO.setId(PinyinUtil.getUuid());
                businessSystemDO.setName(orderGroupForm.getBusinessSystemName());
                businessSystemDO.setDescription("");
                businessSystemDO.setOwner(username);
                businessSystemDO.setCreator(username);
                businessSystemDO.setGmtCreate(nowDate);
                businessSystemDAO.save(businessSystemDO);
            }

            BusinessSubsystemDO businessSubsystemDO = businessSubsystemDAO.getByNameAndBusinessSystemId(
            		orderGroupForm.getBusinessSubsystemName(), businessSystemDO.getId());
            if (businessSubsystemDO == null) {
                businessSubsystemDO = new BusinessSubsystemDO();
                businessSubsystemDO.setId(PinyinUtil.getUuid());
                businessSubsystemDO.setName(orderGroupForm.getBusinessSubsystemName());
                businessSubsystemDO.setDescription("");
                businessSubsystemDO.setBusinessSystemId(businessSystemDO.getId());
                businessSubsystemDO.setBusinessSystem(businessSystemDO);
                businessSubsystemDO.setCreator(username);
                businessSubsystemDO.setGmtCreate(nowDate);
                businessSubsystemDAO.save(businessSubsystemDO);
            }
           
            List<String> servGroupNames = new ArrayList<>();
            for (int i = 0; i < cnt; i++) {
                OrderGroupDO orderGroupDO = buildOrderGroupForSave(orderGroupForm, businessSubsystemDO,i + 1, nowDate, cnt);
                /*ServGroupDO servGroupDO=new ServGroupDO();
                servGroupDO.setBusinessSubsystemId(businessSubsystemDO.getId());
                servGroupDO.setRelateId(orderGroupDO.getRelateAppId());
                servGroupService.save(servGroupDO);*/
                orderGroupDAO.save(orderGroupDO);
                servGroupNames.add(orderGroupDO.getName());

                List<OrderDO> orderDOs = buildOrdersForSave(orderGroupForm, orderGroupDO);
                for (OrderDO orderDO : orderDOs) {
                    orderDAO.save(orderDO);
                    //add by yeht 保存数据库信息、用户信息
                    List<DbUserDO> userDOs = orderDO.getUsers();
                    if(userDOs!=null && !userDOs.isEmpty()) {
                    	for(DbUserDO userDO:userDOs) {
                        	userDO.setOrder_id(orderDO.getId());
                        	userDO.setCreateTime(nowDate);
                        	userDO.setType("user");
                        	dbUserDAO.save(userDO);
                        }
                    }
                    
                    List<DbUserDO> schemaDOs = orderDO.getSchemas();
                    if(schemaDOs!=null && !schemaDOs.isEmpty()) {
                    	for(DbUserDO schemaDO:schemaDOs) {
                        	schemaDO.setOrder_id(orderDO.getId());
                        	schemaDO.setCreateTime(nowDate);
                        	schemaDO.setType("schema");
                        	dbUserDAO.save(schemaDO);
                        }
                    }
                    
                    List<CmhaProxyDO> proxyDOs = orderDO.getProxy();
                    if(proxyDOs!=null && !proxyDOs.isEmpty()) {
                    	for(CmhaProxyDO proxyDO:proxyDOs) {
                        	proxyDO.setOrder_id(orderDO.getId());
                        	proxyDO.setCreateTime(nowDate);
                        	proxyDO.setType("proxy");
                        	cmhaProxyDAO.save(proxyDO);
                        }
                    }
                    
                    List<CmhaProxyDO> cmhaDOs = orderDO.getCmha();
                    if(cmhaDOs!=null && !cmhaDOs.isEmpty()) {
                    	for(CmhaProxyDO cmhaDO:cmhaDOs) {
                        	cmhaDO.setOrder_id(orderDO.getId());
                        	cmhaDO.setCreateTime(nowDate);
                        	cmhaDO.setType("cmha");
                        	cmhaProxyDAO.save(cmhaDO);
                        }
                    }
                    
                }
                
                

                orderGroupDO=orderGroupDAO.get(orderGroupDO.getId());
                String auditName = getUsername();
                //subscription_id订阅号有值，是云发过来的请求，直接自动审批、执行工单
                if(subscription_id!=null && !"".equals(subscription_id)) {
                	servGroupDO =autoExecute(orderGroupDO,"",subscription_id);
                	return Result.success(servGroupDO.getRelateId());
                }else {
                	boolean isAutoAudit = isAutoAudit(auditName);
                    if (isAutoAudit) {
                        autoAudit(orderGroupDO);
                        boolean isAutoExecute = isAutoExecute(auditName);
                        if (isAutoExecute) {
                        	servGroupDO =autoExecute(orderGroupDO,"",subscription_id);
                        	return Result.success(servGroupDO.getRelateId());
                        }else {
                        	return Result.success(orderGroupDO.getId());
                        }
                    }
                    return Result.success(orderGroupDO.getId());
                }
                
                
            }

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result update(String orderGroupId, OrderGroupForm orderGroupForm) throws ServiceException {
        try {
        	if(nullCheck(orderGroupId)) {
        		return Result.failure(CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = orderGroupCheck.checkUpdate(orderGroupId, orderGroupForm);
            if (checkResult.getCode() != OrderGroupChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            String username = getUsername();
            Date nowDate = systemDAO.getCurrentSqlDateTime();

            OrderGroupDO orderGroupDO = buildOrderGroupCoreForUpdate(orderGroupId, orderGroupForm, nowDate);

            orderGroupDAO.update(orderGroupDO);

            orderDAO.removeByOrderGroupId(orderGroupId);
            List<OrderDO> orderDOs = buildOrdersForSave(orderGroupForm, orderGroupDO);
            for (OrderDO orderDO : orderDOs) {
                orderDAO.save(orderDO);
                
              //add by yeht 保存数据库信息、用户信息
                List<DbUserDO> userDOs = orderDO.getUsers();
                if(userDOs!=null && !userDOs.isEmpty()) {
                	for(DbUserDO userDO:userDOs) {
                    	userDO.setOrder_id(orderDO.getId());
                    	userDO.setCreateTime(nowDate);
                    	userDO.setType("user");
                    	dbUserDAO.save(userDO);
                    }
                }
                
                List<DbUserDO> schemaDOs = orderDO.getSchemas();
                if(schemaDOs!=null && !schemaDOs.isEmpty()) {
                	for(DbUserDO schemaDO:schemaDOs) {
                    	schemaDO.setOrder_id(orderDO.getId());
                    	schemaDO.setCreateTime(nowDate);
                    	schemaDO.setType("schema");
                    	dbUserDAO.save(schemaDO);
                    }
                }
                
                List<CmhaProxyDO> proxyDOs = orderDO.getProxy();
                if(proxyDOs!=null && !proxyDOs.isEmpty()) {
                	for(CmhaProxyDO proxyDO:proxyDOs) {
                    	proxyDO.setOrder_id(orderDO.getId());
                    	proxyDO.setCreateTime(nowDate);
                    	proxyDO.setType("proxy");
                    	cmhaProxyDAO.save(proxyDO);
                    }
                }
                
                List<CmhaProxyDO> cmhaDOs = orderDO.getCmha();
                if(cmhaDOs!=null && !cmhaDOs.isEmpty()) {
                	for(CmhaProxyDO cmhaDO:cmhaDOs) {
                    	cmhaDO.setOrder_id(orderDO.getId());
                    	cmhaDO.setCreateTime(nowDate);
                    	cmhaDO.setType("cmha");
                    	cmhaProxyDAO.save(cmhaDO);
                    }
                }
            }
            String auditName = getUsername();
            boolean isAutoAudit = isAutoAudit(auditName);
            if (isAutoAudit) {
                autoAudit(orderGroupDO);
                boolean isAutoExecute = isAutoExecute(auditName);
                if (isAutoExecute) {
                    autoExecute(orderGroupDO,"","");
                }
            }
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result remove(String orderGroupId) throws ServiceException {
        try {
        	if(nullCheck(orderGroupId)) {
        		return Result.failure(CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = orderGroupCheck.checkRemove(orderGroupId);
            if (checkResult.getCode() != OrderGroupChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            orderGroupDAO.remove(orderGroupId);
            orderDAO.removeByOrderGroupId(orderGroupId);
            
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result audit(String orderGroupId, AuditForm auditForm) throws ServiceException {
        try {
        	if(nullCheck(orderGroupId)) {
        		return Result.failure(CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = orderGroupCheck.checkAudit(orderGroupId, auditForm);
            if (checkResult.getCode() != OrderGroupChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }

            OrderGroupDO orderGroupDO = orderGroupDAO.getBase(orderGroupId);
            orderGroupDO.setStatus(auditForm.getStatus());
            orderGroupDO.setMsg(StringUtils.trimToEmpty(auditForm.getMsg()));
            orderGroupDAO.updateStatusAndMsg(orderGroupDO);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public void autoAudit(OrderGroupDO orderGroupDO) throws ServiceException {
        try {
            orderGroupDO.setStatus(DictConsts.ORDER_STATUS_APPROVED);
            orderGroupDAO.updateStatusAndMsg(orderGroupDO);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public Result execute(String orderGroupId,String changeVersionImageId) throws ServiceException {
        try {
        	if(nullCheck(orderGroupId)) {
        		return Result.failure(CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
        	/*if(nullCheck(changeVersionImageId)) {
        		return Result.failure(CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_IMAGE_VERSION_NOT_BE_NULL));
        	}*/
            CheckResult checkResult = orderGroupCheck.checkExecute(orderGroupId);
            if (checkResult.getCode() != OrderGroupChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            OrderGroupDO orderGroupDO = orderGroupDAO.get(orderGroupId);
            
            ServGroupDO servGroupDO =autoExecute(orderGroupDO,changeVersionImageId,null);
            if(null==servGroupDO) {
            	return Result.success();
            }else
            	return Result.success(servGroupDO.getRelateId());
            
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = ServiceException.class)
    public ServGroupDO autoExecute(OrderGroupDO orderGroupDO,String imageId,String subscription_id) throws ServiceException {
    	ServGroupDO	servGroupDO = null;
    	try {
        	
            orderGroupDO.setStatus(DictConsts.STATUS_RUNNING);
            orderGroupDAO.updateStatusAndMsg(orderGroupDO);
            logger.info("服务执行: {}", orderGroupDO.toString());
            if (orderGroupDO.getActionType().equals(DictConsts.ORDER_CREATE_TYPE_NEW)) {
                switch (orderGroupDO.getType()) {
                case Consts.SERV_GROUP_TYPE_MYSQL:
                	if(orderGroupDO.getRelateAppId()!=null) {
                		logger.info("服务创建重新执行: {}", orderGroupDO.toString());
                		serverGroupService.reDeleteI(orderGroupDO.getRelateAppId(),orderGroupDO);
                	}else {
                		logger.info("服务创建执行: {}", orderGroupDO.toString());
                		servGroupDO = serverGroupService.create(orderGroupDO,subscription_id);
                	}
                    break;
                default:
                    break;
                }
            }
            if (orderGroupDO.getActionType().equals(DictConsts.ORDER_CREATE_TYPE_SCALE_UP)) {
                switch (orderGroupDO.getType()) {
                case Consts.SERV_GROUP_TYPE_MYSQL:
                	ServerGroupForm serverGroupForm=new ServerGroupForm();
                	List<OrderDO> orderDOs=orderGroupDO.getOrders();
                	OrderDO orderDO=new OrderDO();
                	if(orderDOs!=null) {
                		orderDO=orderDOs.get(0);
                		serverGroupForm.setCnpCnt(orderDO.getCpuCnt());
                		serverGroupForm.setType(orderDO.getType());
                		serverGroupForm.setMemSize(orderDO.getMemSize());
                		serverGroupForm.setDataDirSize(orderDO.getDataDirSize());
                		serverGroupForm.setLogDirSize(orderDO.getLogDirSize());
                		serverGroupForm.setNetworkBandwidth(orderDO.getNetworkBandwidth());
                		
                		if("true".equals(orderDO.getHamode()) ) {
                			List<CmhaProxyDO> cmhaDOs =  cmhaProxyDAO.getCmhaOrProxy(orderDO.getId(), "cmha");
                    		List<CmhaProxyDO> proxyDOs =  cmhaProxyDAO.getCmhaOrProxy(orderDO.getId(), "proxy");
                    		
                    		if(cmhaDOs !=null && !cmhaDOs.isEmpty()) {
                    			
                    			serverGroupForm.setCmhaCnpCnt(cmhaDOs.get(0).getCpu());
                        		serverGroupForm.setCmhaMemSize(cmhaDOs.get(0).getMemory());
                        		serverGroupForm.setCmhaDataDirSize(cmhaDOs.get(0).getData_capacity());
                        		serverGroupForm.setCmhaLogDirSize(cmhaDOs.get(0).getLog_capacity());
                    		}
                    		
                    		if(proxyDOs !=null && !proxyDOs.isEmpty()) {
	                    		serverGroupForm.setProxyCnpCnt(proxyDOs.get(0).getCpu());
	                    		serverGroupForm.setProxyMemSize(proxyDOs.get(0).getMemory());
	                    		serverGroupForm.setProxyDataDirSize(proxyDOs.get(0).getData_capacity());
	                    		serverGroupForm.setProxyLogDirSize(proxyDOs.get(0).getLog_capacity());
                    		}
                		}
                		
                	}
                	logger.info("服务扩容重新执行: {}", serverGroupForm.toString());
                	serverGroupService.reScale(orderGroupDO.getRelateAppId(),orderGroupDO,serverGroupForm);
                	
                    break;
                default:
                    break;
                }
                
            }
            if (orderGroupDO.getActionType().equals(DictConsts.ORDER_CREATE_TYPE_IMAGE_UPDATE)) {
            	
                switch (orderGroupDO.getType()) {
                case Consts.SERV_GROUP_TYPE_MYSQL:
                	ServerGroupForm serverGroupForm=new ServerGroupForm();
                	List<OrderDO> orderDOs=orderGroupDO.getOrders();
                	OrderDO orderDO=new OrderDO();
                	if(orderDOs!=null) {
                		orderDO=orderDOs.get(0);
                		serverGroupForm.setType(orderDO.getType());
                		serverGroupForm.setMajorVersion(orderDO.getMajorVersion());
                		serverGroupForm.setMinVersion(orderDO.getMinorVersion());
                		serverGroupForm.setPatchVersion(orderDO.getPatchVersion());
                		serverGroupForm.setBuildVersion(orderDO.getBuildVersion());
                		
                		//add by yeht for cmha\proxy
                		serverGroupForm.setCmhaImageId(orderDO.getCmhaImageId());
                		serverGroupForm.setProxyImageId(orderDO.getProxyImageId());
                		serverGroupForm.setImageId(orderDO.getImageId());
                        
//                		MgmImageQuery mgmImageQuery=new MgmImageQuery();
//                		mgmImageQuery.setType(orderDO.getType());
//                		mgmImageQuery.setMajor(orderDO.getMajorVersion());
//                		mgmImageQuery.setMinor(orderDO.getMinorVersion());
//                		mgmImageQuery.setPatch(orderDO.getPatchVersion());
//                		mgmImageQuery.setBuild(orderDO.getBuildVersion());
//                		List<MgmImage> mgmImage=mgmApi.getImage(mgmImageQuery);
//                		if(mgmImage!=null&&mgmImage.size()!=0) {
//                			serverGroupForm.setImageId(mgmImage.get(0).getId());
//                		}
                	}
                	logger.info("服务升级重新执行: {}", serverGroupForm.toString());
                	serverGroupService.reImage(orderGroupDO.getRelateAppId(),orderGroupDO,serverGroupForm);
                    break;
                default:
                    break;
                }
            }
            if (orderGroupDO.getActionType().equals(DictConsts.ORDER_CREATE_TYPE_DELETE)) {
                switch (orderGroupDO.getType()) {
                case Consts.SERV_GROUP_TYPE_MYSQL:
                	logger.info("服务删除重新执行: {}", orderGroupDO.getRelateAppId());
                	serverGroupService.reDelete(orderGroupDO.getRelateAppId(),orderGroupDO);
//                	serverGroupService.remove(orderGroupDO.getRelateAppId());
                    break;
                default:
                    break;
                }
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
		return servGroupDO;
        
    }
    
    private OrderGroupDO buildOrderGroupForSave(OrderGroupForm orderGroupForm, BusinessSubsystemDO businessSubsystemDO,int no, Date nowDate, int cnt) {
        OrderGroupDO orderGroupDO = new OrderGroupDO();
        orderGroupDO.setId(PinyinUtil.getUuid());
        orderGroupDO.setBusinessSubsystemId(businessSubsystemDO.getId());
        orderGroupDO.setType(orderGroupForm.getType());
        orderGroupDO.setSiteId(orderGroupForm.getSiteId());
        orderGroupDO.setAreaCode(orderGroupForm.getAreaCode());
        orderGroupDO.setName(orderGroupForm.getName());
        orderGroupDO.setActionType("new");
        orderGroupDO.setOwner(getUsername());
        orderGroupDO.setStatus(DictConsts.ORDER_STATUS_UNAPPROVED);
        orderGroupDO.setMsg("");
        orderGroupDO.setCreator(getUsername());
        orderGroupDO.setGmtCreate(nowDate);
        return orderGroupDO;
    }

    private List<OrderDO> buildOrdersForSave(OrderGroupForm orderGroupForm, OrderGroupDO orderGroupDO) {
        List<OrderDO> orderDOs = new ArrayList<>();
        List<OrderForm> orderForms = orderGroupForm.getOrders();
        List<OrderDO> preOrderDOs=orderGroupDO.getOrders();
        int index=0;
        for (OrderForm orderForm : orderForms) {
            OrderDO orderDO=new OrderDO();
            if(preOrderDOs!=null){
                orderDO = preOrderDOs.get(index);
            }
            orderDOs.add(orderDO);
            orderDO.setId(PinyinUtil.getUuid());
            orderDO.setOrderGroupId(orderGroupDO.getId());
            orderDO.setType(orderForm.getType());
            orderDO.setMajorVersion(orderForm.getVersion().getMajor());
            orderDO.setMinorVersion(orderForm.getVersion().getMinor());
            orderDO.setPatchVersion(orderForm.getVersion().getPatch());
            orderDO.setBuildVersion(orderForm.getVersion().getBuild());
//            orderDO.setArchId(orderForm.getArchId());
            ArchForm archForm = orderForm.getArch();
            if (archForm != null) {
              orderDO.setArchTypeCode(archForm.getMode());
              if (archForm.getReplicas() != null) {
            	  orderDO.setUnitCnt(archForm.getReplicas());
              }
            }
            orderDO.setCpuCnt(orderForm.getCpuCnt());
            orderDO.setMemSize(orderForm.getMemSize());
            orderDO.setPort(orderForm.getPort());
            orderDO.setCnt(orderForm.getCnt());
            orderDO.setDataDirSize(orderForm.getDataDirSize());
            orderDO.setDataDirType(orderForm.getDataDirType());
            orderDO.setDataDirPerformance(orderForm.getDataDirPerformance());
            orderDO.setLogDirSize(orderForm.getLogDirSize());
            orderDO.setLogDirType(orderForm.getLogDirType());
            orderDO.setLogDirPerformance(orderForm.getLogDirPerformance());
            orderDO.setNetworkBandwidth(orderForm.getNetworkBandwidth());
            orderDO.setHostHA(orderForm.getHostHA());
            orderDO.setClusterHA(orderForm.getClusterHA());
            orderDO.setNetworkHA(orderForm.getNetworkHA());
            orderDO.setStorageHA(orderForm.getStorageHA());
            
            //add by yeht
            orderDO.setHamode(orderForm.getHamode().toString());
            orderDO.setHacontainer(orderForm.getHacontainer());
            orderDO.setSchedule(orderForm.getSchedule());
            orderDO.setArchitecture(orderForm.getArchitecture());
            
            List<SchemaForm> schemaForms = orderForm.getSchemas();
            List<DbUserDO> schemaDOs = new ArrayList<>();
            if(schemaForms!=null && !schemaForms.isEmpty()) {
            	for (SchemaForm schemaForm : schemaForms) {
                	
                	DbUserDO schemaDO = new DbUserDO();
                	schemaDO.setDbname(schemaForm.getDbname()); 
                	//schemaDO.setCharacterSet(schemaForm.getCharacterSet());
                	schemaDOs.add(schemaDO);
                }
            }
            
            
            List<com.bsg.upm.form.OrderForm.UserForm> userForms = orderForm.getUsers();
            List<DbUserDO> userDOs = new ArrayList<>();
            if(userForms!=null && !userForms.isEmpty()) {
            	 for (com.bsg.upm.form.OrderForm.UserForm userForm : userForms) {
                 	
                 	DbUserDO userDO = new DbUserDO();
                 	userDO.setUsername(userForm.getDbuser());
                 	userDO.setUserpwd(userForm.getDbpwd());
                 	userDOs.add(userDO);
                 }
            }
           
            
            List<ProxyForm> cmhaForms = orderForm.getCmha();
            List<ProxyForm> proxyForms = orderForm.getProxy();
            List<CmhaProxyDO> cmhaDOs = new ArrayList<>();
            List<CmhaProxyDO> proxyDOs = new ArrayList<>();
            
            Date nowDate = systemDAO.getCurrentSqlDateTime();
            if(cmhaForms!=null && !cmhaForms.isEmpty()) {
            	for (ProxyForm cmhaForm : cmhaForms) {
                	CmhaProxyDO cmhaProxyDO = new CmhaProxyDO();
                	cmhaProxyDO.setCpu(cmhaForm.getCpu());
                	cmhaProxyDO.setImg_id(cmhaForm.getImg_id());
                	cmhaProxyDO.setCreateTime(nowDate);
                	cmhaProxyDO.setData_capacity(cmhaForm.getData_capacity());
                	cmhaProxyDO.setLog_capacity(cmhaForm.getLog_capacity());
                	cmhaProxyDO.setMode(cmhaForm.getMode());
                	cmhaProxyDO.setMemory(cmhaForm.getMemory());
                	cmhaProxyDO.setPerformance(cmhaForm.getPerformance());
                	cmhaProxyDO.setPort(cmhaForm.getPort());
                	cmhaProxyDO.setReplicas(cmhaForm.getReplicas());
                	cmhaDOs.add(cmhaProxyDO);
                }
            }
            if(proxyForms!=null && !proxyForms.isEmpty()) {
            	for (ProxyForm proxyForm : proxyForms) {
                	CmhaProxyDO cmhaProxyDO = new CmhaProxyDO();
                	cmhaProxyDO.setImg_id(proxyForm.getImg_id());
                	cmhaProxyDO.setCpu(proxyForm.getCpu());
                	cmhaProxyDO.setCreateTime(nowDate);
                	cmhaProxyDO.setData_capacity(proxyForm.getData_capacity());
                	cmhaProxyDO.setLog_capacity(proxyForm.getLog_capacity());
                	cmhaProxyDO.setMode(proxyForm.getMode());
                	cmhaProxyDO.setMemory(proxyForm.getMemory());
                	cmhaProxyDO.setPerformance(proxyForm.getPerformance());
                	cmhaProxyDO.setPort(proxyForm.getPort());
                	cmhaProxyDO.setReplicas(proxyForm.getReplicas());
                	proxyDOs.add(cmhaProxyDO);
                }
            }
            
            
            orderDO.setUsers(userDOs);
            orderDO.setSchemas(schemaDOs);
            orderDO.setCmha(cmhaDOs);
            orderDO.setProxy(proxyDOs);
            
            
            
            /////////////////////
            
            Map<String, String> smallMap = new HashMap<String, String>(); 
            smallMap=orderForm.getCfgs();
            String characterSet=JSON.toJSONString(smallMap);
            
            orderDO.setCfgs(characterSet);
            index++;
        }
        return orderDOs;
    }

    private OrderGroupDTO getShowBaseDTO(OrderGroupDO orderGroupDO, List<MgmSite> mgmSites)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
//    	logger.info("工单: {}", orderGroupDO.toString());
        OrderGroupDTO orderGroupDTO = new OrderGroupDTO();
        orderGroupDTO.setId(orderGroupDO.getId());
        orderGroupDTO.setType(orderGroupDO.getType());
        if(orderGroupDO.getRelateTaskId()!=null) {
        	orderGroupDTO.setTaskId(orderGroupDO.getRelateTaskId());
        }else {
        	orderGroupDTO.setTaskId("0");
        }
        SiteDTO siteDTO = new SiteDTO();
        siteDTO.setId(orderGroupDO.getSiteId());
        for (MgmSite mgmSite : mgmSites) {
            if (mgmSite.getId().equals(orderGroupDO.getSiteId())) {
                siteDTO.setName(mgmSite.getName());
            }
        }
        orderGroupDTO.setSite(siteDTO);

        TypeDTO areaDTO = orderGroupDTO.new TypeDTO();
        areaDTO.setCode(orderGroupDO.getAreaCode());
        areaDTO.setDisplay(dictDAO.getNameByCodeAndDictTypeCode(orderGroupDO.getAreaCode(), DictTypeConsts.AREA));
        orderGroupDTO.setArea(areaDTO);
//        + String.format("%03d")
        orderGroupDTO.setName(orderGroupDO.getName());

        TypeDTO createTypeDTO = orderGroupDTO.new TypeDTO();
        createTypeDTO.setCode(orderGroupDO.getActionType());
        createTypeDTO.setDisplay(
                dictDAO.getNameByCodeAndDictTypeCode(orderGroupDO.getActionType(), DictTypeConsts.ORDER_CREATE_TYPE));
        orderGroupDTO.setActionType(createTypeDTO);

        TypeDTO statusDTO = orderGroupDTO.new TypeDTO();
        statusDTO.setCode(orderGroupDO.getStatus());
        statusDTO.setDisplay(
                dictDAO.getNameByCodeAndDictTypeCode(orderGroupDO.getStatus(), DictTypeConsts.ORDER_STATUS));
        orderGroupDTO.setStatus(statusDTO);

        orderGroupDTO.setMsg(orderGroupDO.getMsg());
        orderGroupDTO.setOwner(orderGroupDO.getOwner());
        orderGroupDTO.setGmtCreate(DateUtils.dateTimeToString(orderGroupDO.getGmtCreate()));
        orderGroupDTO.setCreator(orderGroupDO.getCreator());
        orderGroupDTO.setGmtModified(DateUtils.dateTimeToString(orderGroupDO.getGmtModified()));
        orderGroupDTO.setEditor(orderGroupDO.getEditor());

        List<OrderDTO> orderDTOs = new ArrayList<>();
        

        List<OrderDO> orderDOs = orderGroupDO.getOrders();
        for (OrderDO orderDO : orderDOs) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTOs.add(orderDTO);

            orderDTO.setId(orderDO.getId());
            orderDTO.setType(orderDO.getType());

            VersionDTO versionDTO = new VersionDTO();
            versionDTO.setMajor(orderDO.getMajorVersion());
            versionDTO.setMinor(orderDO.getMinorVersion());
            versionDTO.setPatch(orderDO.getPatchVersion());
            versionDTO.setBuild(orderDO.getBuildVersion());
            orderDTO.setVersion(versionDTO);

            com.bsg.upm.dto.OrderDTO.ArchDTO archDTO = orderDTO.new ArchDTO();
            orderDTO.setArch(archDTO);
            if (orderDO.getArchTypeCode() != null) {
            	archDTO.setMode(orderDO.getArchTypeCode());
            	archDTO.setName(dictDAO.getNameByCodeAndDictTypeCode(orderDO.getArchTypeCode(), DictTypeConsts.ARCH_TYPE));
            	
            }
            if (StringUtils.isNotBlank(orderDO.getUnitCnt().toString())) {
            	archDTO.setReplicas(orderDO.getUnitCnt());
            }

            orderDTO.setCpuCnt(orderDO.getCpuCnt());
            orderDTO.setMemSize(orderDO.getMemSize());
            orderDTO.setPort(orderDO.getPort());
            orderDTO.setDataDirSize(orderDO.getDataDirSize());
            orderDTO.setLogDirSize(orderDO.getLogDirSize());

            TypeDTO dataDirTypeDTO = orderDTO.new TypeDTO();
            dataDirTypeDTO.setCode(orderDO.getDataDirType());
            dataDirTypeDTO.setDisplay(
                    dictDAO.getNameByCodeAndDictTypeCode(orderDO.getDataDirType(), DictTypeConsts.DIR_TYPE));
            orderDTO.setDataDirType(dataDirTypeDTO);
            orderDTO.setNetworkBandwidth(orderDO.getNetworkBandwidth());
            TypeDTO logDirTypeDTO = orderDTO.new TypeDTO();
            logDirTypeDTO.setCode(orderDO.getLogDirType());
            logDirTypeDTO
                    .setDisplay(dictDAO.getNameByCodeAndDictTypeCode(orderDO.getLogDirType(), DictTypeConsts.DIR_TYPE));
            orderDTO.setLogDirType(logDirTypeDTO);



            TypeDTO dataDirPerformanceDTO = orderDTO.new TypeDTO();
            dataDirPerformanceDTO.setCode(orderDO.getDataDirPerformance());
            dataDirPerformanceDTO.setDisplay(
                    dictDAO.getNameByCodeAndDictTypeCode(orderDO.getDataDirPerformance(), DictTypeConsts.PERFORMANCE));
            orderDTO.setDataDirPerformance(dataDirPerformanceDTO);

            TypeDTO logDirPerformanceDTO = orderDTO.new TypeDTO();
            logDirPerformanceDTO.setCode(orderDO.getLogDirPerformance());
            logDirPerformanceDTO.setDisplay(
                    dictDAO.getNameByCodeAndDictTypeCode(orderDO.getLogDirPerformance(), DictTypeConsts.PERFORMANCE));
            orderDTO.setLogDirPerformance(logDirPerformanceDTO);

            orderDTO.setNetworkBandwidth(orderDO.getNetworkBandwidth());
            orderDTO.setHostHA(orderDO.getHostHA());
            orderDTO.setClusterHA(orderDO.getClusterHA());
            orderDTO.setNetworkHA(orderDO.getNetworkHA());
            orderDTO.setStorageHA(orderDO.getStorageHA());
            
            Gson gson = new Gson();
            String cfgs = orderDO.getCfgs();
            Map<String, String> cfgsMap = new HashMap<String, String>();
            cfgsMap = gson.fromJson(cfgs, cfgsMap.getClass());
            
            orderDTO.setCfgs(cfgsMap);
            orderDTO.setCnt(orderDO.getCnt());
            
            VersionDTO preVersionDTO = new VersionDTO();
            preVersionDTO.setMajor(orderDO.getPreMajorVersion());
            preVersionDTO.setMinor(orderDO.getPreMinorVersion());
            preVersionDTO.setPatch(orderDO.getPrePatchVersion());
            preVersionDTO.setBuild(orderDO.getPreBuildVersion());
            orderDTO.setPreVersion(preVersionDTO);

            com.bsg.upm.dto.OrderDTO.ArchDTO preArchDTO = orderDTO.new ArchDTO();
            orderDTO.setPreArch(preArchDTO);
            if (orderDO.getPreArchTypeCode() != null) {
            	preArchDTO.setMode(orderDO.getPreArchTypeCode());
            	preArchDTO.setName(dictDAO.getNameByCodeAndDictTypeCode(orderDO.getPreArchTypeCode(), DictTypeConsts.ARCH_TYPE));
            }
            if (orderDO.getPreUnitCnt()!=null) {
            	preArchDTO.setReplicas(orderDO.getPreUnitCnt());
            }
            
            orderDTO.setPreCpuCnt(orderDO.getPreCpuCnt());
            orderDTO.setPreMemSize(orderDO.getPreMemSize());
            orderDTO.setPreDataDirSize(orderDO.getPreDataDirSize());
            orderDTO.setPreLogDirSize(orderDO.getPreLogDirSize());
            orderDTO.setPreNetworkBandwidth(orderDO.getPreNetworkBandwidth());
            orderDO.setPreCnt(orderDO.getPreCnt());
            
            /////////////////////新增字段  add by yeht
		    orderDTO.setHaMode(orderDO.getHamode());
		    orderDTO.setSchedule(orderDO.getSchedule());
		    orderDTO.setArchitecture(orderDO.getArchitecture());
		    orderDTO.setHacontainer(orderDO.getHacontainer());
		    
		    //待补充，yeht
        	List<DbUserDO> userDOs = dbUserDAO.getDbUsers(orderDO.getId(), "user");
        	List<DbUserDTO> userDTOs = new ArrayList<>(),schemaDTOs = new ArrayList<>();
            List<DbUserDO> schemaDOs = dbUserDAO.getDbUsers(orderDO.getId(), "schema");
            //orderDO.setUsers(schemaDOs);
            for(DbUserDO userDO : userDOs) {
            	DbUserDTO userDTO = new DbUserDTO();
            	BeanUtils.copyProperties(userDO, userDTO);
            	userDTO.setDbuser(userDO.getUsername());
            	userDTO.setDbpwd(userDO.getUserpwd());
            	userDTOs.add(userDTO);
            }
            for(DbUserDO schemaDO : schemaDOs) {
            	DbUserDTO schemaDTO = new DbUserDTO();
            	BeanUtils.copyProperties(schemaDO, schemaDTO);
            	schemaDTOs.add(schemaDTO);
            }
            
            orderDTO.setUsers(userDTOs);
            orderDTO.setSchemas(schemaDTOs);
            
            if("true".equals(orderDO.getHamode())) {
            	List<CmhaProxyDO> proxyDOs = cmhaProxyDAO.getCmhaOrProxy(orderDO.getId(), "proxy");
                List<CmhaProxyDO> cmhaDOs = cmhaProxyDAO.getCmhaOrProxy(orderDO.getId(), "cmha");
                List<CmhaProxyDTO> proxyDTOs = new ArrayList<>() ,cmhaDTOs = new ArrayList<>();
                
                for(CmhaProxyDO cmhaDO : cmhaDOs) {
                	CmhaProxyDTO cmhaDTO = new CmhaProxyDTO();
                	BeanUtils.copyProperties(cmhaDO, cmhaDTO);
                	cmhaDTOs.add(cmhaDTO);
                }
                for(CmhaProxyDO proxyDO : proxyDOs) {
                	CmhaProxyDTO proxyDTO = new CmhaProxyDTO();
                	BeanUtils.copyProperties(proxyDO, proxyDTO);
                	proxyDTOs.add(proxyDTO);
                }
                orderDTO.setProxy(proxyDTOs);
                orderDTO.setCmha(cmhaDTOs);
            }
            
		    
        }
        orderGroupDTO.setOrders(orderDTOs);
        return orderGroupDTO;
    }

    private OrderGroupDO buildOrderGroupCoreForUpdate(String orderGroupId, OrderGroupForm orderGroupForm, Date nowDate) {
        OrderGroupDO newOrderGroupDO = new OrderGroupDO();
        OrderGroupDO orderGroupDO = orderGroupDAO.get(orderGroupId);
        
//        List<OrderDO> orderDOs = orderGroupDO.getOrders();
//        for (OrderDO orderDO : orderDOs) {
//        	List<DbUserDO> userDOs = dbUserDAO.getDbUsers(orderDO.getId(), "user");
//        	orderDO.setUsers(userDOs);
//            List<DbUserDO> schemaDOs = dbUserDAO.getDbUsers(orderDO.getId(), "schema");
//            orderDO.setUsers(schemaDOs);
//            
//            List<CmhaProxyDO> proxyDOs = cmhaProxyDAO.getCmhaOrProxy(orderDO.getId(), "proxy");
//            List<CmhaProxyDO> cmhaDOs = cmhaProxyDAO.getCmhaOrProxy(orderDO.getId(), "cmha");
//            orderDO.setCmha(cmhaDOs);
//            orderDO.setProxy(proxyDOs);
//            
//        }
        
        BeanUtils.copyProperties(orderGroupDO, newOrderGroupDO);

        newOrderGroupDO.setStatus(DictConsts.ORDER_STATUS_UNAPPROVED);
        newOrderGroupDO.setMsg("");
        newOrderGroupDO.setEditor(getUsername());
        newOrderGroupDO.setGmtModified(nowDate);

        newOrderGroupDO.setSiteId(orderGroupForm.getSiteId());
        newOrderGroupDO.setAreaCode(orderGroupForm.getAreaCode());
        newOrderGroupDO.setName(orderGroupForm.getName());

        return newOrderGroupDO;
    }

    // 自动审批
    public boolean isAutoAudit(String username) {
        UserDO userDO = userDAO.getByUsername(username);
        RoleCfgOthersDO cfgOthersDO = roleCfgOthersDAO.getByRoleId(userDO.getRoleId());
        boolean autoAudit = false;
        if (cfgOthersDO != null) {
            autoAudit = cfgOthersDO.getOrderAutoAudit();
        }
        return autoAudit;
    }

    // 自动执行
    public boolean isAutoExecute(String username) {
        UserDO userDO = userDAO.getByUsername(username);
        RoleCfgOthersDO cfgOthersDO = roleCfgOthersDAO.getByRoleId(userDO.getRoleId());
        boolean autoAudit = false;
        if (cfgOthersDO != null) {
            autoAudit = cfgOthersDO.getOrderAutoExecute();
        }
        return autoAudit;
    }
    
    @Transactional(rollbackFor = ServiceException.class)
    public Result reSearchTask(String serveId,String taskId,String actionType) throws ServiceException {
        try {
        	if(nullCheck(serveId)) {
        		return Result.failure(CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
        	if(nullCheck(taskId)) {
        		return Result.failure(CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_TASKID_MUST_NOT_BE_BLANK));
        	}
        	if(nullCheck(actionType)) {
        		return Result.failure(CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_ACTIONTYPE_MUST_NOT_BE_BLANK));
        	}
            OrderGroupDO orderGroupDO=orderGroupDAO.get(serveId);
//            orderGroupDO.setId(serveId);
            orderGroupDO.setRelateTaskId(taskId);
            orderGroupDO.setMsg("");
            orderGroupDO.setStatus(DictConsts.STATUS_RUNNING);
            orderGroupDAO.updateStatusAndMsg(orderGroupDO);
            logger.info("工单状态重新查询: {}", orderGroupDO.toString());
            if (actionType.equals(DictConsts.ORDER_CREATE_TYPE_NEW)) {
                	logger.info("服务创建重新查询: {}", orderGroupDO.toString());
                	CreateTask createTask=new CreateTask(orderGroupDO);
                    Thread thread = new Thread(createTask);
                    thread.start();
            }
            if (actionType.equals(DictConsts.ORDER_CREATE_TYPE_IMAGE_UPDATE)) {
            	logger.info("服务升级重新查询: {}", orderGroupDO.toString());
            	ImageTask imageTask=new ImageTask(orderGroupDO);
                Thread thread = new Thread(imageTask);
                thread.start();
            	
            }
            if (actionType.equals(DictConsts.ORDER_CREATE_TYPE_SCALE_UP)) {
            	logger.info("服务扩容重新查询: {}", orderGroupDO.toString());
            	ScaleTask scaleTask=new ScaleTask(orderGroupDO);
                Thread thread = new Thread(scaleTask);
                thread.start();
            }
            if (actionType.equals(DictConsts.ORDER_CREATE_TYPE_DELETE)) {
            	logger.info("服务删除重新查询: {}", orderGroupDO.toString());
            	DeleteTask deleteTask=new DeleteTask(orderGroupDO);
              	Thread thread = new Thread(deleteTask);
                thread.start();
            }

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    public class CreateTask implements Runnable {
        public OrderGroupDO orderGroupDO;
        public volatile boolean exit = false;
        public int i=0;
        public CreateTask(OrderGroupDO orderGroupDO) {
            this.orderGroupDO=orderGroupDO;
        }
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (!exit) {
                String taskId=orderGroupDO.getRelateTaskId();
                MgmSaveServerGroup mgmSaveServerGroup=new MgmSaveServerGroup();
                mgmSaveServerGroup.setId(taskId);
                String logPrefix = "服务创建重新查询"+orderGroupDO.getId();
                logger.info("{}: start build concurrent subtask queues。", logPrefix);
                try {
                    String flag=mgmApi.listTask(mgmSaveServerGroup);
                    if (flag.equals("5")) {
                        orderGroupDO.setStatus(DictConsts.ORDER_STATUS_SUCCESS);
                        orderGroupDAO.updateStatusAndMsg(orderGroupDO);
                        exit=true;
                    }else if(flag.equals("4")){
                        orderGroupDO.setStatus(DictConsts.ORDER_STATUS_FAILURE);
                        orderGroupDAO.updateStatusAndMsg(orderGroupDO);
                        exit=true;
                    }else{
                        Thread.sleep(10000);
                        i++;
                        if(i==180){
                        	orderGroupDO.setStatus(DictConsts.ORDER_STATUS_OVERTIME);
                            orderGroupDAO.updateStatusAndMsg(orderGroupDO);
                            exit=true;
                        }
                    }
                    
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
            
        }

    }
    
    public class ScaleTask implements Runnable {
        public OrderGroupDO orderGroupDO;
        public volatile boolean exit = false;
        public int i=0;
        public ScaleTask(OrderGroupDO orderGroupDO) {
            this.orderGroupDO=orderGroupDO;
        }
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (!exit) {
                String taskId=orderGroupDO.getRelateTaskId();
                MgmSaveServerGroup mgmSaveServerGroup=new MgmSaveServerGroup();
                mgmSaveServerGroup.setId(taskId);
                String logPrefix = "服务扩容"+orderGroupDO.getName();
                logger.info("{}: start scale concurrent subtask queues。", logPrefix);
                try {
                    String flag=mgmApi.listTask(mgmSaveServerGroup);
                    if (flag.equals("5")) {
                        orderGroupDO.setStatus(DictConsts.ORDER_STATUS_SUCCESS);
                        orderGroupDAO.updateStatusAndMsg(orderGroupDO);
                        exit=true;
                    }else if(flag.equals("4")){
                        orderGroupDO.setStatus(DictConsts.ORDER_STATUS_FAILURE);
                        orderGroupDAO.updateStatusAndMsg(orderGroupDO);
                        exit=true;
                    }else{
                        Thread.sleep(10000);
                        i++;
                        if(i==180){
                        	orderGroupDO.setStatus(DictConsts.ORDER_STATUS_OVERTIME);
                            orderGroupDAO.updateStatusAndMsg(orderGroupDO);
                            exit=true;
                        }
                    }
                    
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
            
        }

    }
    
    public class ImageTask implements Runnable {
        public OrderGroupDO orderGroupDO;
        public volatile boolean exit = false;
        public int i=0;
        public ImageTask(OrderGroupDO orderGroupDO) {
            this.orderGroupDO=orderGroupDO;
        }
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while (!exit) {
                String taskId=orderGroupDO.getRelateTaskId();
                MgmSaveServerGroup mgmSaveServerGroup=new MgmSaveServerGroup();
                mgmSaveServerGroup.setId(taskId);
                String logPrefix = "服务升级"+orderGroupDO.getName();
                logger.info("{}: start scale concurrent subtask queues。", logPrefix);
                try {
                    String flag=mgmApi.listTask(mgmSaveServerGroup);
                    if (flag.equals("5")) {
                        orderGroupDO.setStatus(DictConsts.ORDER_STATUS_SUCCESS);
                        orderGroupDAO.updateStatusAndMsg(orderGroupDO);
                        exit=true;
                    }else if(flag.equals("4")){
                        orderGroupDO.setStatus(DictConsts.ORDER_STATUS_FAILURE);
                        orderGroupDAO.updateStatusAndMsg(orderGroupDO);
                        exit=true;
                    }else{
                        Thread.sleep(10000);
                        i++;
                        if(i==180){
                            exit=true;
                            orderGroupDO.setStatus(DictConsts.ORDER_STATUS_OVERTIME);
                            orderGroupDAO.updateStatusAndMsg(orderGroupDO);
                        }
                    }
                    
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
            
        }

    }
    
    public class DeleteTask implements Runnable{
    	 public OrderGroupDO copyOrderGroup;
         public volatile boolean isDeleted = false;
         public int i=0;
         public DeleteTask(OrderGroupDO copyOrderGroup) {
             this.copyOrderGroup=copyOrderGroup;
         }
         @Override
         public void run() {
             // TODO Auto-generated method stub
        	 copyOrderGroup.setActionType(DictConsts.ORDER_CREATE_TYPE_DELETE);
             String logPrefix = "服务删除"+copyOrderGroup.getName();
             logger.info("{}: start build concurrent subtask queues。", logPrefix);
             while (isDeleted==false) {
                 try {
                	 Thread.sleep(30000);
                	 MgmServerGroupQuery mgmServerGroupQuery = new MgmServerGroupQuery();
                     List<MgmServerGroup> mgmServerGroups;
                     Boolean deletedStatus=false;
    				 mgmServerGroups = mgmApi.listServerGroup(mgmServerGroupQuery);
    				 if(mgmServerGroups==null||mgmServerGroups.size()==0) {
    					 isDeleted=true;
    				 }else {
    					 for (MgmServerGroup mgmServerGroup : mgmServerGroups) {   
     						if(mgmServerGroup.getId().equals(copyOrderGroup.getRelateAppId())) {
     			            	MgmServerGroupDetailQuery mgmServerGroupDetailQuery = new MgmServerGroupDetailQuery();
     			                mgmServerGroupDetailQuery.setId(copyOrderGroup.getRelateAppId());
     			                List<MgmServerGroup> mgmServerGroupss = mgmApi.detailServerGroup(mgmServerGroupDetailQuery);
     			                MgmServerGroup mgmServerGroupp = null;
     			                if (mgmServerGroupss.size() != 0) {
     			                    mgmServerGroupp = mgmServerGroupss.get(0);
     			                    if(mgmServerGroupp.getTask().getAction().equals(DictConsts.MGM_APP_DELETE_TYPE_NEW)&& mgmServerGroupp.getTask().getStatus().equals(DictConsts.COMPARE_ORDER_STATUS_FAILED)) {
     			                    	deletedStatus=true;
     			                    	logger.info("{}: delete info::", mgmServerGroupp.toString());
     			                    	isDeleted=true;
     			                    }else {
     			                    	isDeleted=false;
     			                    }
     			                }
     			                break;
     			            }else {
        			            	isDeleted=true;
     						}
     			        }
    				 }
    					
                     if (isDeleted==true) {
                    	 if(deletedStatus==true) {
                    		 copyOrderGroup.setStatus(DictConsts.ORDER_STATUS_FAILURE);
//                 	 		 orderGroupDAO.removeDeleted(copyOrderGroup.getName());
                          	 orderGroupDAO.updateStatusAndMsg(copyOrderGroup);
                          	logger.info("{}: delete failed。", isDeleted);
                    	 }else {
                    		 copyOrderGroup.setStatus(DictConsts.ORDER_STATUS_SUCCESS);
                 	 		 orderGroupDAO.removeDeleted(copyOrderGroup.getName());
                          	 orderGroupDAO.updateStatusAndMsg(copyOrderGroup);
                          	 logger.info("{}: delete success。", isDeleted);
                    	 }
                             	isDeleted=true;
                     }else{
                         Thread.sleep(30000);
                         i++;
                         isDeleted=false;
                         if(i==59){
                         	isDeleted=true;
                         	
                         	copyOrderGroup.setStatus(DictConsts.ORDER_STATUS_OVERTIME);
                         	orderGroupDAO.updateStatusAndMsg(copyOrderGroup);
//                         	MgmSaveServerGroup mgmSaveServerGroup=new MgmSaveServerGroup();
//                            mgmSaveServerGroup.setId(taskId);
                         	logger.info("{}: delete overtime。", isDeleted);
                         }
                     }
                 } catch (Exception e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                 }
                 
             }
             logger.info("{}: delete status。", isDeleted);
             
         }
    }

}
