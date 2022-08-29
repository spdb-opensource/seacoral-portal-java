package com.bsg.upm.service;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.sql.visitor.functions.If;
import com.alibaba.fastjson.JSONObject;
import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.ServerGroupCheck;
import com.bsg.upm.check.resultenum.HostChkRsEnum;
import com.bsg.upm.check.resultenum.NetworkingChkRsEnum;
import com.bsg.upm.check.resultenum.OrderGroupChkRsEnum;
import com.bsg.upm.check.resultenum.ScaleChkRsEnum;
import com.bsg.upm.check.resultenum.ServerGroupChkRsEnum;
import com.bsg.upm.constant.DictConsts;
import com.bsg.upm.constant.DictTypeConsts;
import com.bsg.upm.dao.ServerGroupDAO;
import com.bsg.upm.domain.BusinessSystemDO;
import com.bsg.upm.domain.CmhaProxyDO;
import com.bsg.upm.domain.DbUserDO;
import com.bsg.upm.domain.OrderDO;
import com.bsg.upm.domain.OrderGroupDO;
import com.bsg.upm.domain.SchemaDO;
import com.bsg.upm.domain.ServGroupDO;
import com.bsg.upm.domain.UserDO;
import com.bsg.upm.dto.BaseDTO.TaskDTO;
import com.bsg.upm.dto.BaseDTO.TypeDTO;
import com.bsg.upm.dto.ServerGroupDTO;
import com.bsg.upm.dto.ServerGroupDTO.ArchDTO;
import com.bsg.upm.dto.ServerGroupDTO.AreaDTO;
import com.bsg.upm.dto.ServerGroupDTO.ClusterDTO;
import com.bsg.upm.dto.ServerGroupDTO.Ha;
import com.bsg.upm.dto.ServerGroupDTO.HostDTO;
import com.bsg.upm.dto.ServerGroupDTO.MysqlArchDTO;
import com.bsg.upm.dto.ServerGroupDTO.MysqlScaleDTO;
import com.bsg.upm.dto.ServerGroupDTO.MysqlVersionDTO;
import com.bsg.upm.dto.ServerGroupDTO.OwnerDTO;
import com.bsg.upm.dto.ServerGroupDTO.ReplicationDTO;
import com.bsg.upm.dto.ServerGroupDTO.ServerDTO;
import com.bsg.upm.dto.ServerGroupDTO.ServerDatabase;
import com.bsg.upm.dto.ServerGroupDTO.SpecArchDTO;
import com.bsg.upm.dto.ServerGroupDTO.SpecDTO;
import com.bsg.upm.dto.ServerGroupDTO.SpecDatabase;
import com.bsg.upm.dto.ServerGroupDTO.SpecVersionDTO;
import com.bsg.upm.dto.ServerGroupDTO.UnitDTO;
import com.bsg.upm.dto.UnitTaskDTO;
import com.bsg.upm.dto.UserDTO;
import com.bsg.upm.exception.CallingInterfaceException;
import com.bsg.upm.exception.ConnectConsulException;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.exception.ServiceNotFoundException;
import com.bsg.upm.form.DatabaseForm;
import com.bsg.upm.form.MgmBaseForm;
import com.bsg.upm.form.ServerGroupForm;
import com.bsg.upm.form.ServerGroupUserForm;
import com.bsg.upm.form.ServerGroupUserForm.PrivilegeForm;
import com.bsg.upm.form.ServerStateForm;
import com.bsg.upm.mgm.api.MgmApi;
import com.bsg.upm.mgm.body.MgmBaseBody;
import com.bsg.upm.mgm.body.MgmServerGroupBackupBody;
import com.bsg.upm.mgm.body.MgmServerGroupBody;
import com.bsg.upm.mgm.body.MgmServerGroupBody.ArchBody;
import com.bsg.upm.mgm.body.MgmServerGroupBody.BackupBody;
import com.bsg.upm.mgm.body.MgmServerGroupBody.BackupStorageBody;
import com.bsg.upm.mgm.body.MgmServerGroupBody.ClusterBody;
import com.bsg.upm.mgm.body.MgmServerGroupBody.ConditionBody;
import com.bsg.upm.mgm.body.MgmServerGroupBody.HostBody;
import com.bsg.upm.mgm.body.MgmServerGroupBody.NetworkBody;
import com.bsg.upm.mgm.body.MgmServerGroupBody.PortBody;
import com.bsg.upm.mgm.body.MgmServerGroupBody.RequestBody;
import com.bsg.upm.mgm.body.MgmServerGroupBody.ResourceBody;
import com.bsg.upm.mgm.body.MgmServerGroupBody.ServiceBody;
import com.bsg.upm.mgm.body.MgmServerGroupBody.StorageRemoteBody;
import com.bsg.upm.mgm.body.MgmServerGroupBody.UnitBody;
import com.bsg.upm.mgm.body.MgmServerGroupBody.VolumeBody;
import com.bsg.upm.mgm.body.MgmServerGroupImageBody;
import com.bsg.upm.mgm.body.MgmServerGroupImageBody.ImageBody;
import com.bsg.upm.mgm.body.MgmServerGroupResourceRequestsBody;
import com.bsg.upm.mgm.body.MgmServerGroupResourceRequestsBody.DatabaseBody;
import com.bsg.upm.mgm.body.MgmServerGroupResourceRequestsBody.RequestsBody;
import com.bsg.upm.mgm.body.MgmServerGroupResourceRequestsBody.ResourcesBody;
import com.bsg.upm.mgm.body.MgmServerGroupResourceRequestsBody.ServicesBody;
import com.bsg.upm.mgm.body.MgmServerGroupResourceRequestsBody.SpecBody;
import com.bsg.upm.mgm.body.MgmServerGroupResourceRequestsBody.StorageBody;
import com.bsg.upm.mgm.body.MgmServerGroupResourceRequestsBody.UnitsBody;
import com.bsg.upm.mgm.body.MgmServerGroupResourceRequestsBody.VolumesBody;
import com.bsg.upm.mgm.body.MgmServerGroupStateBody;
import com.bsg.upm.mgm.body.MgmServerGroupUserBody;
import com.bsg.upm.mgm.model.MgmCluster;
import com.bsg.upm.mgm.model.MgmImage;
import com.bsg.upm.mgm.model.MgmModel.Task;
import com.bsg.upm.mgm.model.MgmSaveServerGroup;
import com.bsg.upm.mgm.model.MgmServerGroup;
import com.bsg.upm.mgm.model.MgmServerGroup.Arch;
import com.bsg.upm.mgm.model.MgmServerGroup.Conditions;
import com.bsg.upm.mgm.model.MgmServerGroup.Image;
import com.bsg.upm.mgm.model.MgmServerGroup.Node;
import com.bsg.upm.mgm.model.MgmServerGroup.Port;
import com.bsg.upm.mgm.model.MgmServerGroup.Replication;
import com.bsg.upm.mgm.model.MgmServerGroup.Requests;
import com.bsg.upm.mgm.model.MgmServerGroup.Spec;
import com.bsg.upm.mgm.model.MgmServerGroup.SpecResources;
import com.bsg.upm.mgm.model.MgmServerGroup.SpecService;
import com.bsg.upm.mgm.model.MgmServerGroup.SpecUnit;
import com.bsg.upm.mgm.model.MgmServerGroup.Status;
import com.bsg.upm.mgm.model.MgmServerGroup.StatusDatabase;
import com.bsg.upm.mgm.model.MgmServerGroup.StatusService;
import com.bsg.upm.mgm.model.MgmServerGroup.Storage;
import com.bsg.upm.mgm.model.MgmServerGroup.Unit;
import com.bsg.upm.mgm.model.MgmServerGroup.Volume;
import com.bsg.upm.mgm.model.MgmTask;
import com.bsg.upm.mgm.model.MgmTopology;
import com.bsg.upm.mgm.query.MgmClusterQuery;
import com.bsg.upm.mgm.query.MgmImageQuery;
import com.bsg.upm.mgm.query.MgmServerGroupDetailQuery;
import com.bsg.upm.mgm.query.MgmServerGroupQuery;
import com.bsg.upm.param.TaskDAOParam;
import com.bsg.upm.util.PinyinUtil;
import com.google.gson.Gson;
//import com.sun.tools.internal.xjc.api.SpecVersion;

/**
 * 服务组service
 * 
 * @author swn
 *
 */
@Service
public class ServerGroupService extends BaseService {

    @Autowired
    private MgmApi mgmApi;

    @Autowired
    private ServerGroupDAO serverGroupDAO;

    @Autowired
    private ServerGroupCheck serverGroupCheck;

    @Autowired
    private OrderGroupService orderGroupService;
    
    @Autowired
    private ServGroupService servGroupService;
    
    @Autowired
	private ServerGroupUserService serverGroupUserService;
    
    @Autowired
	private DatabaseService databaseService;

    /**
     * 服务组列表
     * 
     * @return
     * @throws ServiceException
     */
    @Transactional
    public Result list(String subscription_id) throws ServiceException {
        try {
            final List<ServerGroupDTO> serverGroupDTOs = new ArrayList<>();

            MgmServerGroupQuery mgmServerGroupQuery = new MgmServerGroupQuery();
            mgmServerGroupQuery.setSubscription_id(subscription_id);
            List<MgmServerGroup> mgmServerGroups = mgmApi.listServerGroup(mgmServerGroupQuery);
            for (MgmServerGroup mgmServerGroup : mgmServerGroups) {
                ServerGroupDTO serverGroupDTO = getShowBaseDTO(mgmServerGroup);
                serverGroupDTOs.add(serverGroupDTO);
            }

            return Result.success(serverGroupDTOs);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 获取服务组通过id
     * 
     * @param serverGroupId
     * @return
     * @throws ServiceException
     */
    @Transactional
    public Result get(String serverGroupId,String subscription_id) throws ServiceException {
        ServerGroupDTO serverGroupDTO = null;
        try {
        	if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            MgmServerGroupDetailQuery mgmServerGroupDetailQuery = new MgmServerGroupDetailQuery();
            mgmServerGroupDetailQuery.setId(serverGroupId);
            //add for 订阅号
            mgmServerGroupDetailQuery.setSubscription_id(subscription_id);
            List<MgmServerGroup> mgmServerGroups = mgmApi.detailServerGroup(mgmServerGroupDetailQuery);
            MgmServerGroup mgmServerGroup = null;
            if (mgmServerGroups.size() != 0) {
                mgmServerGroup = mgmServerGroups.get(0);
                serverGroupDTO = getShowBaseDTO(mgmServerGroup);
            }

            return Result.success(serverGroupDTO);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    @Transactional
    public ServerGroupDTO getServerGroupDTO(String serverGroupId) throws ServiceException {
        ServerGroupDTO serverGroupDTO = null;
        try {
        	if(nullCheck(serverGroupId)) {
        		return serverGroupDTO;
        	}
            MgmServerGroupDetailQuery mgmServerGroupDetailQuery = new MgmServerGroupDetailQuery();
            mgmServerGroupDetailQuery.setId(serverGroupId);
            List<MgmServerGroup> mgmServerGroups = mgmApi.detailServerGroup(mgmServerGroupDetailQuery);
            MgmServerGroup mgmServerGroup = null;
            if (mgmServerGroups.size() != 0) {
                mgmServerGroup = mgmServerGroups.get(0);
                serverGroupDTO = getShowBaseDTO(mgmServerGroup);
            }

            return serverGroupDTO;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    /**
     *任务详情
     * 
     * @param serverGroupId
     * @return
     * @throws ServiceException
     */
    @Transactional
    public Result getTask(String taskId) throws ServiceException {
    	List<UnitTaskDTO> taskDaos=new ArrayList<>();
    	try {
    		if(nullCheck(taskId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_TASK_ID_MUST_NOT_BE_BLANK));
        	}
    		MgmTask mgmTask = new MgmTask();
    		mgmTask.setId(taskId);
    		List<MgmTask> mgmTasks = mgmApi.listTasks(mgmTask);
    		for(MgmTask mgmTask1:mgmTasks) {
    			UnitTaskDTO unitTaskDTO=new UnitTaskDTO();
    			unitTaskDTO=getShowBaseDTOTask(mgmTask1);
    			taskDaos.add(unitTaskDTO);
    		}
//    		if (mgmTasks.size() != 0) {
//    			mgmTask1 = mgmTasks.get(0);
//    			taskDao = getShowBaseDTOTask(mgmTask1);
//    		}
    		
    		return Result.success(taskDaos);
    	} catch (Exception e) {
    		throw new ServiceException(e);
    	}
    }

    /**
     * 服务组启动/停止
     * 
     * @param serverGroupId
     * @param enabled
     * @return
     * @throws ServiceException
     */
    @Transactional(rollbackFor = ServiceException.class)
    public Result enabled(String serverGroupId, boolean enabled) throws ServiceException {
        try {
        	if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            MgmServerGroupStateBody mgmServerGroupStateBody = new MgmServerGroupStateBody();
            if (enabled) {
                mgmServerGroupStateBody.setState("passing");
            } else {
                mgmServerGroupStateBody.setState("critical");
            }

            mgmApi.stateServerGroup(serverGroupId, mgmServerGroupStateBody);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    /**
     * 服务组状态变更
     * 
     * @param serverGroupId
     * @param serverStateForm
     * @return
     * @throws ServiceException
     */
    @Transactional(rollbackFor = ServiceException.class)
    public Result state(String serverGroupId, ServerStateForm serverStateForm) throws ServiceException {
        try {
        	if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
        	
            MgmServerGroupStateBody mgmServerGroupStateBody = new MgmServerGroupStateBody();
            
            mgmServerGroupStateBody.setState(serverStateForm.getState());
            MgmServerGroupStateBody.Spec spec = new MgmServerGroupStateBody.Spec();
            MgmServerGroupStateBody.Statebase database = new MgmServerGroupStateBody.Statebase();
            MgmServerGroupStateBody.Statebase cmha = new MgmServerGroupStateBody.Statebase();
            MgmServerGroupStateBody.Statebase proxy = new MgmServerGroupStateBody.Statebase();
            MgmServerGroupStateBody.Services dataservices = new MgmServerGroupStateBody.Services();
            MgmServerGroupStateBody.Services cmhaservices = new MgmServerGroupStateBody.Services();
            MgmServerGroupStateBody.Services proxyservices = new MgmServerGroupStateBody.Services();
            
            dataservices.setState(serverStateForm.getDatabaseState());
            cmhaservices.setState(serverStateForm.getCmhaState());
            proxyservices.setState(serverStateForm.getProxyState());
            database.setServices(dataservices);
            cmha.setServices(cmhaservices);
            proxy.setServices(proxyservices);
            
            spec.setCmha(cmha);
            spec.setProxy(proxy);
            spec.setDatabase(database);
            
            mgmServerGroupStateBody.setSpec(spec);
            mgmApi.stateServerGroup(serverGroupId, mgmServerGroupStateBody);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 扩容
     * 
     * @param serverGroupId
     * @param serverGroupForm
     * @return
     * @throws ServiceException
     */
    @Transactional(rollbackFor = ServiceException.class)
    public Result scale(String serverGroupId,String orderGroupIdI, ServerGroupForm serverGroupForm,String subscription_id) throws ServiceException {
        try {
        	if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
        	//orderGroupIdI没用到
//        	if(nullCheck(orderGroupIdI)) {
//        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ORDER_ID_MUST_NOT_BE_BLANK));
//        	}
            CheckResult checkResult = serverGroupCheck.checkScale(serverGroupId, serverGroupForm);
            if (checkResult.getCode() != NetworkingChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            OrderGroupDO orderGroupDO2=new OrderGroupDO();
            orderGroupDO2.setName(serverGroupForm.getName());
            orderGroupDO2.setActionType(DictConsts.ORDER_CREATE_TYPE_SCALE_UP);
            OrderGroupDO orderGroupDO1=orderGroupDAO.checkNameAndActionType(orderGroupDO2);
            if(orderGroupDO1!=null) {
            	if(!orderGroupDO1.getStatus().equals(DictConsts.ORDER_STATUS_SUCCESS)&&orderGroupDO1.getActionType().equals(DictConsts.ORDER_CREATE_TYPE_SCALE_UP)) {
            		throw new ServiceException("该申请单已经存在，请前往申请单页面查看");
            	}
            }

            // TODO 服务组，扩容时，往数据库中工单组和工单表插入数据
            // 1.查询工单组
            OrderGroupDO existedOrderGroup = orderGroupDAO.getLastOneByServerGroupId(serverGroupId);
            // 2.复制插入工单组
            if (existedOrderGroup != null) {
                OrderGroupDO copyOrderGroup = new OrderGroupDO();
                copyOrderGroup.setId(PinyinUtil.getUuid());
                copyOrderGroup.setType(existedOrderGroup.getType());
                copyOrderGroup.setSiteId(existedOrderGroup.getSiteId());
                copyOrderGroup.setAreaCode(existedOrderGroup.getAreaCode());
                copyOrderGroup.setName(existedOrderGroup.getName());
                copyOrderGroup.setActionType(DictConsts.ORDER_CREATE_TYPE_SCALE_UP);
                copyOrderGroup.setStatus(DictConsts.ORDER_STATUS_UNAPPROVED);
                copyOrderGroup.setMsg(existedOrderGroup.getMsg());
                copyOrderGroup.setOwner(getUsername());
                // extend_json_str是null
                copyOrderGroup.setRelateAppId(serverGroupId);
                // relate_servg_task_id是null
                copyOrderGroup.setGmtCreate(systemDAO.getCurrentSqlDateTime());
                copyOrderGroup.setCreator(getUsername());
                copyOrderGroup.setBusinessSubsystemId(existedOrderGroup.getBusinessSubsystemId());
                // gmt_modified是null
                // editor是null

                int insertOrderGroupCount = orderGroupDAO.save(copyOrderGroup);
                String orderGroupId=copyOrderGroup.getId();
                
                Date nowDate = systemDAO.getCurrentSqlDateTime();

                if (insertOrderGroupCount == 1) {
                    // 3.查询工单组下工单
                    List<OrderDO> existedOrders = orderDAO.selectByOrderGroupId(existedOrderGroup.getId());

                    // 4.复制工单
                    for (OrderDO orderDO : existedOrders) {
                        OrderDO copyOrder = new OrderDO();
                        // 复制工单关联工单组
                        copyOrder.setId(PinyinUtil.getUuid());
                        copyOrder.setOrderGroupId(copyOrderGroup.getId());
                        copyOrder.setType(orderDO.getType());
                        // major_version,minor_version,patch_version,build_version都是升级相关字段
                        copyOrder.setMajorVersion(orderDO.getMajorVersion());
                        copyOrder.setMinorVersion(orderDO.getMinorVersion());
                        copyOrder.setPatchVersion(orderDO.getPatchVersion());
                        copyOrder.setBuildVersion(orderDO.getBuildVersion());
//                        copyOrder.setArchId(orderDO.getArchId());
                        copyOrder.setArchTypeCode(orderDO.getArchTypeCode());
                        copyOrder.setUnitCnt(orderDO.getUnitCnt());
                        // 扩容数据cpu
                        copyOrder.setCpuCnt(serverGroupForm.getCnpCnt());
                        // 扩容数据 memSize
                        copyOrder.setMemSize(serverGroupForm.getMemSize());
                        // 扩容数据 dataDirSize
                        copyOrder.setDataDirSize(serverGroupForm.getDataDirSize());
                        copyOrder.setDataDirType(orderDO.getDataDirType());
                        copyOrder.setDataDirPerformance(orderDO.getDataDirPerformance());
                        // 扩容数据 logDirSize
                        copyOrder.setLogDirSize(serverGroupForm.getLogDirSize());
                        copyOrder.setLogDirType(orderDO.getLogDirType());
                        copyOrder.setLogDirPerformance(orderDO.getLogDirPerformance());
                        // 扩容数据 networkBandwidth
                        copyOrder.setNetworkBandwidth(serverGroupForm.getNetworkBandwidth());
                        copyOrder.setHostHA(orderDO.getHostHA());
                        copyOrder.setNetworkHA(orderDO.getNetworkHA());
                        copyOrder.setClusterHA(orderDO.getClusterHA());
                        copyOrder.setStorageHA(orderDO.getStorageHA());
                        copyOrder.setCfgs(orderDO.getCfgs());
                        copyOrder.setPort(orderDO.getPort());
                        copyOrder.setCnt(orderDO.getCnt());
                        // pre_major_version,pre_minor_version,pre_patch_version,pre_build_version升级相关字段
                        copyOrder.setPreMajorVersion(orderDO.getPreMajorVersion());
                        copyOrder.setPreMinorVersion(orderDO.getPreMinorVersion());
                        copyOrder.setPrePatchVersion(orderDO.getPrePatchVersion());
                        copyOrder.setPreBuildVersion(orderDO.getPreBuildVersion());
//                        copyOrder.setPreArchId(orderDO.getPreArchId());
                        copyOrder.setPreArchTypeCode(orderDO.getPreArchTypeCode());
                        copyOrder.setPreUnitCnt(orderDO.getPreUnitCnt());
                        // pre_cpu_cnt,pre_mem_size,pre_data_dir_size,pre_log_dir_size是扩容前数据
                        copyOrder.setPreCpuCnt(orderDO.getCpuCnt());
                        copyOrder.setPreMemSize(orderDO.getMemSize());
                        copyOrder.setPreDataDirSize(orderDO.getDataDirSize());
                        copyOrder.setPreLogDirSize(orderDO.getLogDirSize());
                        copyOrder.setPreNetworkBandwidth(orderDO.getPreNetworkBandwidth());
                        copyOrder.setPreCnt(orderDO.getPreCnt());
                        
                        //新增hamode等字段
                        copyOrder.setHacontainer(orderDO.getHacontainer());
                        copyOrder.setHamode(orderDO.getHamode());
                        copyOrder.setArchitecture(orderDO.getArchitecture());
                        copyOrder.setImageId(orderDO.getImageId());
                        copyOrder.setProxyImageId(orderDO.getProxyImageId());
                        copyOrder.setCmhaImageId(orderDO.getCmhaImageId());
                        
                        int insertOrderCount = orderDAO.save(copyOrder);
                        
                        //hamode为true时，并且参数不全为null才附带cmha和proxy的信息
                        if("true".equals(orderDO.getHamode())) {
                        	List<CmhaProxyDO> proxyDOs = cmhaProxyDAO.getCmhaOrProxy(orderDO.getId(),"proxy");
                            List<CmhaProxyDO> cmhaDOs = cmhaProxyDAO.getCmhaOrProxy(orderDO.getId(),"cmha");
                            logger.info("服务扩容proxy信息 {}", proxyDOs.toString());
                            if(proxyDOs == null || proxyDOs.isEmpty()) {
                            	if(serverGroupForm.getProxyCnpCnt()!=null || serverGroupForm.getProxyMemSize()!=null
                            			||serverGroupForm.getProxyDataDirSize()!=null || serverGroupForm.getProxyLogDirSize()!=null) {
                            		CmhaProxyDO proxyDO = new CmhaProxyDO();
                                	proxyDO.setOrder_id(copyOrder.getId());
                                	proxyDO.setCreateTime(nowDate);
                                	proxyDO.setType("proxy");
                                	proxyDO.setCpu(serverGroupForm.getProxyCnpCnt());
                                	proxyDO.setData_capacity(serverGroupForm.getProxyDataDirSize());
                                	proxyDO.setLog_capacity(serverGroupForm.getProxyLogDirSize());
                                	proxyDO.setMemory(serverGroupForm.getProxyMemSize());
                                	cmhaProxyDAO.save(proxyDO);
                                	logger.info("服务扩容proxyDo信息 {}", proxyDO.toString());
                            	}
                            	
                            }else {
                            	for (CmhaProxyDO proxyDO : proxyDOs){
                                	proxyDO.setOrder_id(copyOrder.getId());
                                	proxyDO.setCreateTime(nowDate);
                                	proxyDO.setType("proxy");
                                	proxyDO.setCpu(serverGroupForm.getProxyCnpCnt());
                                	proxyDO.setData_capacity(serverGroupForm.getProxyDataDirSize());
                                	proxyDO.setLog_capacity(serverGroupForm.getProxyLogDirSize());
                                	proxyDO.setMemory(serverGroupForm.getProxyMemSize());
                                	cmhaProxyDAO.save(proxyDO);
                                }
                            }
                            if(cmhaDOs == null || cmhaDOs.isEmpty()) {
                            	if(serverGroupForm.getCmhaCnpCnt()!=null || serverGroupForm.getCmhaMemSize()!=null
                            			||serverGroupForm.getCmhaDataDirSize()!=null || serverGroupForm.getCmhaLogDirSize()!=null) {
                            		CmhaProxyDO cmhaDO = new CmhaProxyDO();
                                	cmhaDO.setOrder_id(copyOrder.getId());
                                	cmhaDO.setCreateTime(nowDate);
                                	cmhaDO.setType("cmha");
                                	cmhaDO.setCpu(serverGroupForm.getCmhaCnpCnt());
                                	cmhaDO.setData_capacity(serverGroupForm.getCmhaDataDirSize());
                                	cmhaDO.setLog_capacity(serverGroupForm.getCmhaLogDirSize());
                                	cmhaDO.setMemory(serverGroupForm.getCmhaMemSize());
                                	cmhaProxyDAO.save(cmhaDO);
                            	}
                            	
                            }else {
                            	for (CmhaProxyDO cmhaDO : cmhaDOs){
                                	cmhaDO.setOrder_id(copyOrder.getId());
                                	cmhaDO.setCreateTime(nowDate);
                                	cmhaDO.setType("cmha");
                                	cmhaDO.setCpu(serverGroupForm.getCmhaCnpCnt());
                                	cmhaDO.setData_capacity(serverGroupForm.getCmhaDataDirSize());
                                	cmhaDO.setLog_capacity(serverGroupForm.getCmhaLogDirSize());
                                	cmhaDO.setMemory(serverGroupForm.getCmhaMemSize());
                                	cmhaProxyDAO.save(cmhaDO);
//                                }
                            }
                        }
                        
                        }
                        	
                        /*
                         * 扩容时，加上user和schema
                         */
                    	List <DbUserDO> userDOs = dbUserDAO.getDbUsers(orderDO.getId(),"user");
                    	List <DbUserDO> schemaDOs = dbUserDAO.getDbUsers(orderDO.getId(),"schema");
                        if(userDOs!=null && !userDOs.isEmpty()) {
                        	for(DbUserDO userDO:userDOs) {
                            	userDO.setOrder_id(copyOrder.getId());
                            	userDO.setCreateTime(nowDate);
                            	userDO.setType("user");
                            	dbUserDAO.save(userDO);
                            }
                        }
                        
                        if(schemaDOs!=null && !schemaDOs.isEmpty()) {
                        	for(DbUserDO schemaDO:schemaDOs) {
                            	schemaDO.setOrder_id(copyOrder.getId());
                            	schemaDO.setCreateTime(nowDate);
                            	schemaDO.setType("schema");
                            	dbUserDAO.save(schemaDO);
                            }
                        }
                        
                        

                        if (insertOrderCount != 1) {
                            throw new ServiceException("扩容时工单(tbl_order)复制插入失败");
                        }
                    

                    
                    //subscription_id订阅号有值，是云发过来的请求，直接自动审批、执行工单
                    if(subscription_id!=null && !"".equals(subscription_id)) {
                    	orderGroupService.autoExecute(orderGroupDAO.get(orderGroupId),"",subscription_id);
                    }else {
                    	// 5.获取用户角色，并判断是否自动审批，自动执行
                        String auditName = getUsername();
                        boolean isAutoAudit = orderGroupService.isAutoAudit(auditName);
                        if (isAutoAudit) {
                            orderGroupService.autoAudit(copyOrderGroup);

                            boolean isAutoExecute = orderGroupService.isAutoExecute(auditName);
                            if (isAutoExecute) {
                            	OrderGroupDO orderGroupDOo=orderGroupDAO.get(orderGroupId);
                            	if(orderGroupDOo==null) {
                            		throw new ServiceException("扩容时工单组(tbl_order_group)查询失败");
                            	}else {
                            		orderGroupService.autoExecute(orderGroupDAO.get(orderGroupId),"","");
                            		
                            	}
                                // 6.执行调用mgm扩容接口
                                /*MgmServerGroupResourceRequestsBody mgmServerGroupResourceRequestsBody = buildScaleMgmServerGroupResourceRequestsBody(
                                        serverGroupForm);
                                MgmSaveServerGroup mgmSaveServerGroup = mgmApi.resourceRequestsServerGroup(serverGroupId, mgmServerGroupResourceRequestsBody);
                                copyOrderGroup.setRelateAppId(mgmSaveServerGroup.getId());
                                copyOrderGroup.setRelateTaskId(mgmSaveServerGroup.getTaskId());
                                	ScaleTask scaleTask=new ScaleTask(copyOrderGroup);
                                    Thread thread = new Thread(scaleTask);
                                    thread.start();*/
                                // 7.获取执行成功后task_id，保存到tbl_order_group表relate_servg_id字段中

                                // 8.创建线程，执行调用mgm任务接口
                            }
                        }
                    }
                    
                    }
                } else {
                    throw new ServiceException("扩容时工单组(tbl_order_group)复制插入失败");
                }

            }else {
            	throw new ServiceException("扩容时工单组(tbl_order_group)查询失败");
            }

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 升级
     * 
     * @param serverGroupId
     * @param serverGroupForm
     * @return
     * @throws ServiceException
     */
    @Transactional(rollbackFor = ServiceException.class)
    public Result image(String serverGroupId, ServerGroupForm serverGroupForm,String subscription_id) throws ServiceException {
        try {
        	if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
//        	if(nullCheck(orderGroupIdI)) {
//        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ORDER_ID_MUST_NOT_BE_BLANK));
//        	}
//            CheckResult checkResult = serverGroupCheck.checkImage(serverGroupId, serverGroupForm);
//            if (checkResult.getCode() != NetworkingChkRsEnum.SUCCESS.getCode()) {
//                logger.error(loginfo(checkResult));
//                return Result.failure(checkResult);
//            }
            OrderGroupDO orderGroupDO2=new OrderGroupDO();
            orderGroupDO2.setName(serverGroupForm.getName());
            orderGroupDO2.setActionType(DictConsts.ORDER_CREATE_TYPE_IMAGE_UPDATE);
            OrderGroupDO orderGroupDO1=orderGroupDAO.checkNameAndActionType(orderGroupDO2);
            if(orderGroupDO1!=null) {
            	if(!orderGroupDO1.getStatus().equals(DictConsts.ORDER_STATUS_SUCCESS)&&orderGroupDO1.getActionType().equals(DictConsts.ORDER_CREATE_TYPE_IMAGE_UPDATE)) {
            		throw new ServiceException("该申请单已经存在，请前往申请单页面查看");
            	}
            }
            // TODO 服务组，升级时，往数据库中工单组和工单表插入数据
            // 1.查询工单组
            OrderGroupDO existedOrderGroup = orderGroupDAO.getLastOneByServerGroupId(serverGroupId);

            // 2.复制插入工单组
            if (existedOrderGroup != null) {
                OrderGroupDO copyOrderGroup = new OrderGroupDO();
                copyOrderGroup.setId(PinyinUtil.getUuid());
                copyOrderGroup.setType(existedOrderGroup.getType());
                copyOrderGroup.setSiteId(existedOrderGroup.getSiteId());
                copyOrderGroup.setAreaCode(existedOrderGroup.getAreaCode());
                copyOrderGroup.setName(existedOrderGroup.getName());
                copyOrderGroup.setActionType(DictConsts.ORDER_CREATE_TYPE_IMAGE_UPDATE);
                copyOrderGroup.setStatus(DictConsts.ORDER_STATUS_UNAPPROVED);
                copyOrderGroup.setMsg(existedOrderGroup.getMsg());
                copyOrderGroup.setOwner(getUsername());
                // extend_json_str是null
                copyOrderGroup.setRelateAppId(serverGroupId);
                // relate_servg_task_id是null
                copyOrderGroup.setGmtCreate(systemDAO.getCurrentSqlDateTime());
                copyOrderGroup.setCreator(getUsername());
                copyOrderGroup.setBusinessSubsystemId(existedOrderGroup.getBusinessSubsystemId());
                // gmt_modified是null
                // editor是null

                int insertOrderGroupCount = orderGroupDAO.save(copyOrderGroup);
                String orderGroupId=copyOrderGroup.getId();

                if (insertOrderGroupCount == 1) {
                    // 3.查询工单组下工单
                    List<OrderDO> existedOrders = orderDAO.selectByOrderGroupId(existedOrderGroup.getId());

                    // 4.复制工单
                    for (OrderDO orderDO : existedOrders) {
                        OrderDO copyOrder = new OrderDO();
                        // 复制工单关联工单组
                        copyOrder.setId(PinyinUtil.getUuid());
                        copyOrder.setOrderGroupId(copyOrderGroup.getId());
                        copyOrder.setType(orderDO.getType());
                        // major_version,minor_version,patch_version,build_version都是升级相关字段
                        copyOrder.setMajorVersion(serverGroupForm.getMajorVersion());
                        copyOrder.setMinorVersion(serverGroupForm.getMinVersion());
                        copyOrder.setPatchVersion(serverGroupForm.getPatchVersion());
                        copyOrder.setBuildVersion(serverGroupForm.getBuildVersion());
                        //add by yeht for cmha\proxy
                        copyOrder.setCmhaImageId(serverGroupForm.getCmhaImageId());
                        copyOrder.setProxyImageId(serverGroupForm.getProxyImageId());
                        copyOrder.setImageId(serverGroupForm.getImageId());
                        
//                        copyOrder.setArchId(orderDO.getArchId());
                        copyOrder.setArchTypeCode(orderDO.getArchTypeCode());
                        copyOrder.setUnitCnt(orderDO.getUnitCnt());
                        // 扩容数据cpu
                        copyOrder.setCpuCnt(orderDO.getCpuCnt());
                        // 扩容数据 memSize
                        copyOrder.setMemSize(orderDO.getMemSize());
                        // 扩容数据 dataDirSize
                        copyOrder.setDataDirSize(orderDO.getDataDirSize());
                        copyOrder.setDataDirType(orderDO.getDataDirType());
                        copyOrder.setDataDirPerformance(orderDO.getDataDirPerformance());
                        // 扩容数据 logDirSize
                        copyOrder.setLogDirSize(orderDO.getLogDirSize());
                        copyOrder.setLogDirType(orderDO.getLogDirType());
                        copyOrder.setLogDirPerformance(orderDO.getLogDirPerformance());
                        // 扩容数据 networkBandwidth
                        copyOrder.setNetworkBandwidth(orderDO.getNetworkBandwidth());
                        copyOrder.setHostHA(orderDO.getHostHA());
                        copyOrder.setNetworkHA(orderDO.getNetworkHA());
                        copyOrder.setClusterHA(orderDO.getClusterHA());
                        copyOrder.setStorageHA(orderDO.getStorageHA());
                        copyOrder.setCfgs(orderDO.getCfgs());
                        copyOrder.setPort(orderDO.getPort());
                        copyOrder.setCnt(orderDO.getCnt());
                        // pre_major_version,pre_minor_version,pre_patch_version,pre_build_version升级相关字段
                        copyOrder.setPreMajorVersion(orderDO.getMajorVersion());
                        copyOrder.setPreMinorVersion(orderDO.getMinorVersion());
                        copyOrder.setPrePatchVersion(orderDO.getPatchVersion());
                        copyOrder.setPreBuildVersion(orderDO.getBuildVersion());
//                        copyOrder.setPreArchId(orderDO.getPreArchId());
                        copyOrder.setPreArchTypeCode(orderDO.getPreArchTypeCode());
                        copyOrder.setPreUnitCnt(orderDO.getPreUnitCnt());
                        copyOrder.setPreCpuCnt(orderDO.getPreCpuCnt());
                        copyOrder.setPreMemSize(orderDO.getPreMemSize());
                        copyOrder.setPreDataDirSize(orderDO.getPreDataDirSize());
                        copyOrder.setPreLogDirSize(orderDO.getPreLogDirSize());
                        copyOrder.setPreNetworkBandwidth(orderDO.getPreNetworkBandwidth());
                        copyOrder.setPreCnt(orderDO.getPreCnt());
                        
                        
                        Date nowDate = systemDAO.getCurrentSqlDateTime();
                        //hamode为true时，才附带cmha和proxy的信息
                        if("true".equals(orderDO.getHamode())) {
                        	
                            List<CmhaProxyDO> proxyDOs = cmhaProxyDAO.getCmhaOrProxy(orderDO.getId(),"proxy");
                            List<CmhaProxyDO> cmhaDOs = cmhaProxyDAO.getCmhaOrProxy(orderDO.getId(),"cmha");
                            if(proxyDOs.isEmpty() ) {
                            	CmhaProxyDO proxyDO = new CmhaProxyDO();
                            	proxyDO.setOrder_id(copyOrder.getId());
                            	proxyDO.setCreateTime(nowDate);
                            	proxyDO.setType("proxy");
                            	proxyDO.setCpu(serverGroupForm.getProxyCnpCnt());
                            	proxyDO.setData_capacity(serverGroupForm.getProxyDataDirSize());
                            	proxyDO.setLog_capacity(serverGroupForm.getProxyLogDirSize());
                            	proxyDO.setMemory(serverGroupForm.getProxyMemSize());
                            	cmhaProxyDAO.save(proxyDO);
                            }else {
                            	for (CmhaProxyDO proxyDO : proxyDOs){
                                	proxyDO.setOrder_id(copyOrder.getId());
                                	proxyDO.setCreateTime(nowDate);
                                	proxyDO.setType("proxy");
                                	proxyDO.setCpu(serverGroupForm.getProxyCnpCnt());
                                	proxyDO.setData_capacity(serverGroupForm.getProxyDataDirSize());
                                	proxyDO.setLog_capacity(serverGroupForm.getProxyLogDirSize());
                                	proxyDO.setMemory(serverGroupForm.getProxyMemSize());
                                	cmhaProxyDAO.save(proxyDO);
                                }
                            }
                            if(cmhaDOs.isEmpty()) {
                            	CmhaProxyDO cmhaDO = new CmhaProxyDO();
                            	cmhaDO.setOrder_id(copyOrder.getId());
                            	cmhaDO.setCreateTime(nowDate);
                            	cmhaDO.setType("cmha");
                            	cmhaDO.setCpu(serverGroupForm.getCmhaCnpCnt());
                            	cmhaDO.setData_capacity(serverGroupForm.getCmhaDataDirSize());
                            	cmhaDO.setLog_capacity(serverGroupForm.getCmhaLogDirSize());
                            	cmhaDO.setMemory(serverGroupForm.getCmhaMemSize());
                            	cmhaProxyDAO.save(cmhaDO);
                            }else {
                            	for (CmhaProxyDO cmhaDO : cmhaDOs){
                                	cmhaDO.setOrder_id(copyOrder.getId());
                                	cmhaDO.setCreateTime(nowDate);
                                	cmhaDO.setType("cmha");
                                	cmhaDO.setCpu(serverGroupForm.getCmhaCnpCnt());
                                	cmhaDO.setData_capacity(serverGroupForm.getCmhaDataDirSize());
                                	cmhaDO.setLog_capacity(serverGroupForm.getCmhaLogDirSize());
                                	cmhaDO.setMemory(serverGroupForm.getCmhaMemSize());
                                	cmhaProxyDAO.save(cmhaDO);
                                }
                            }
                        }
                        
                        	
                        /*
                         * 升级时，加上user和schema
                         */
                    	List <DbUserDO> userDOs = dbUserDAO.getDbUsers(orderDO.getId(),"user");
                    	List <DbUserDO> schemaDOs = dbUserDAO.getDbUsers(orderDO.getId(),"schema");
                        if(userDOs!=null && !userDOs.isEmpty()) {
                        	for(DbUserDO userDO:userDOs) {
                            	userDO.setOrder_id(copyOrder.getId());
                            	userDO.setCreateTime(nowDate);
                            	userDO.setType("user");
                            	dbUserDAO.save(userDO);
                            }
                        }
                        
                        if(schemaDOs!=null && !schemaDOs.isEmpty()) {
                        	for(DbUserDO schemaDO:schemaDOs) {
                            	schemaDO.setOrder_id(copyOrder.getId());
                            	schemaDO.setCreateTime(nowDate);
                            	schemaDO.setType("schema");
                            	dbUserDAO.save(schemaDO);
                            }
                        }
                        

                        int insertOrderCount = orderDAO.save(copyOrder);

                        if (insertOrderCount != 1) {
                            throw new ServiceException("升级时工单(tbl_order)复制插入失败");
                        }
                    }
                    String imageId=serverGroupForm.getImageId();
                    //subscription_id订阅号有值，是云发过来的请求，直接自动审批、执行工单
                    if(subscription_id!=null && !"".equals(subscription_id)) {
                    	orderGroupService.autoExecute(orderGroupDAO.get(orderGroupId),imageId,subscription_id);
                    }else {
                    	// 5.获取用户角色，并判断是否自动审批，自动执行
                        String auditName = getUsername();
                        boolean isAutoAudit = orderGroupService.isAutoAudit(auditName);
                        if (isAutoAudit) {
                            orderGroupService.autoAudit(copyOrderGroup);

                            boolean isAutoExecute = orderGroupService.isAutoExecute(auditName);
                            if (isAutoExecute) {
                            	OrderGroupDO orderGroupDOo=orderGroupDAO.get(orderGroupId);
                            	if(orderGroupDOo==null) {
                            		throw new ServiceException("升级时工单组(tbl_order_group)查询失败");
                            	}else {
                            		
                            		orderGroupService.autoExecute(orderGroupDAO.get(orderGroupId),imageId,"");
                            		
                            	}

                                // 6.执行调用mgm升级接口
                                /*MgmServerGroupImageBody mgmServerGroupImageBody = buildScaleMgmServerGroupImageBody(
                                        serverGroupForm);
                                MgmSaveServerGroup mgmSaveServerGroup = mgmApi.imageServerGroup(serverGroupId, mgmServerGroupImageBody);
                                copyOrderGroup.setRelateAppId(mgmSaveServerGroup.getId());
                                copyOrderGroup.setRelateTaskId(mgmSaveServerGroup.getTaskId());
//                                int status=mgmApi.imageServerGroup(serverGroupId, mgmServerGroupImageBody);
//                                if(status==1) {
                                	ImageTask imageTask=new ImageTask(copyOrderGroup);
                                    Thread thread = new Thread(imageTask);
                                    thread.start();*/
//                                }
                                // 7.获取执行成功后task_id，保存到tbl_order_group表relate_servg_id字段中

                                // 8.创建线程，执行调用mgm任务接口
                            }
                        }
                    }

                    

                } else {
                    throw new ServiceException("升级时工单组(tbl_order_group)复制插入失败");
                }

            }else {
            	throw new ServiceException("升级时工单组(tbl_order_group)查询失败");
            }

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    /**
     * 备份
     * @param serverGroupId
     * @param serverGroupForm
     * @return
     * @throws ServiceException
     */
    @Transactional(rollbackFor = ServiceException.class)
    public Result backup(String serverGroupId, ServerGroupForm serverGroupForm) throws ServiceException {
        try {
        	if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = serverGroupCheck.checkBackup(serverGroupId, serverGroupForm);
            if (checkResult.getCode() != NetworkingChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            
            MgmServerGroupBackupBody mgmServerGroupBackupBody = buildMgmServerGroupBackupBody(serverGroupForm);
            mgmServerGroupBackupBody.setAppId(serverGroupId);
            mgmApi.backupServerGroup(serverGroupId, mgmServerGroupBackupBody);
			
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 服务组删除
     * 
     * @param serverGroupId
     * @return
     * @throws ServiceException
     */
    @Transactional(rollbackFor = ServiceException.class)
    public Result remove(String serverGroupId,String name,String subscription_id) throws ServiceException {
        try {
        	if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
        	if(nullCheck(name)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_NAME_MUST_NOT_BE_BLANK));
        	}
        	OrderGroupDO orderGroupDO2=new OrderGroupDO();
            orderGroupDO2.setName(name);
            orderGroupDO2.setActionType(DictConsts.ORDER_CREATE_TYPE_DELETE);
        	OrderGroupDO orderGroupDO1=orderGroupDAO.checkNameAndActionType(orderGroupDO2);
            if(orderGroupDO1!=null) {
            	return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.REMOVE_ILLEGAL_WITH_ORDERGROUP_EXIST));
            }
        	 MgmServerGroupDetailQuery mgmServerGroupDetailQuery = new MgmServerGroupDetailQuery();
             mgmServerGroupDetailQuery.setId(serverGroupId);
             List<MgmServerGroup> mgmServerGroups = mgmApi.detailServerGroup(mgmServerGroupDetailQuery);
             MgmServerGroup mgmServerGroup = null;
             if (mgmServerGroups.size() != 0) {
                 mgmServerGroup = mgmServerGroups.get(0);
                 if("passing".equals(mgmServerGroup.getState())) {
                	 return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.REMOVE_ILLEGAL_WITH_ENABLED));
                 }else {
        	 			// TODO 服务组，删除时，往数据库中工单组和工单表插入数据
			            // 1.查询工单组
			            OrderGroupDO existedOrderGroup = orderGroupDAO.getLastOneByServerGroupId(serverGroupId);
			
			            // 2.复制插入工单组
			            if (existedOrderGroup != null) {
			                OrderGroupDO copyOrderGroup = new OrderGroupDO();
			                copyOrderGroup.setId(PinyinUtil.getUuid());
			                copyOrderGroup.setType(existedOrderGroup.getType());
			                copyOrderGroup.setSiteId(existedOrderGroup.getSiteId());
			                copyOrderGroup.setAreaCode(existedOrderGroup.getAreaCode());
			                copyOrderGroup.setName(existedOrderGroup.getName());
			                copyOrderGroup.setActionType(DictConsts.ORDER_CREATE_TYPE_DELETE);
			                copyOrderGroup.setStatus(DictConsts.ORDER_STATUS_UNAPPROVED);
			                copyOrderGroup.setMsg(existedOrderGroup.getMsg());
			                copyOrderGroup.setOwner(getUsername());
			                // extend_json_str是null
			                copyOrderGroup.setRelateAppId(serverGroupId);
			                // relate_servg_task_id是null
			                copyOrderGroup.setGmtCreate(systemDAO.getCurrentSqlDateTime());
			                copyOrderGroup.setCreator(getUsername());
			                copyOrderGroup.setBusinessSubsystemId(existedOrderGroup.getBusinessSubsystemId());
			                // gmt_modified是null
			                // editor是null
			
			                int insertOrderGroupCount = orderGroupDAO.save(copyOrderGroup);
			                String orderGroupId=copyOrderGroup.getId();
			
			                if (insertOrderGroupCount == 1) {
			                    // 3.查询工单组下工单
			                    List<OrderDO> existedOrders = orderDAO.selectByOrderGroupId(existedOrderGroup.getId());
			
			                    // 4.复制工单
			                    for (OrderDO orderDO : existedOrders) {
			                        OrderDO copyOrder = new OrderDO();
			                        // 复制工单关联工单组
			                        copyOrder.setId(PinyinUtil.getUuid());
			                        copyOrder.setOrderGroupId(copyOrderGroup.getId());
			                        copyOrder.setType(orderDO.getType());
			                        // major_version,minor_version,patch_version,build_version都是升级相关字段
			                        copyOrder.setMajorVersion(orderDO.getMajorVersion());
			                        copyOrder.setMinorVersion(orderDO.getMinorVersion());
			                        copyOrder.setPatchVersion(orderDO.getPatchVersion());
			                        copyOrder.setBuildVersion(orderDO.getBuildVersion());
			//                        copyOrder.setArchId(orderDO.getArchId());
			                        copyOrder.setArchTypeCode(orderDO.getArchTypeCode());
			                        copyOrder.setUnitCnt(orderDO.getUnitCnt());
			                        // 扩容数据cpu
			                        copyOrder.setCpuCnt(orderDO.getCpuCnt());
			                        // 扩容数据 memSize
			                        copyOrder.setMemSize(orderDO.getMemSize());
			                        // 扩容数据 dataDirSize
			                        copyOrder.setDataDirSize(orderDO.getDataDirSize());
			                        copyOrder.setDataDirType(orderDO.getDataDirType());
			                        copyOrder.setDataDirPerformance(orderDO.getDataDirPerformance());
			                        // 扩容数据 logDirSize
			                        copyOrder.setLogDirSize(orderDO.getLogDirSize());
			                        copyOrder.setLogDirType(orderDO.getLogDirType());
			                        copyOrder.setLogDirPerformance(orderDO.getLogDirPerformance());
			                        // 扩容数据 networkBandwidth
			                        copyOrder.setNetworkBandwidth(orderDO.getNetworkBandwidth());
			                        copyOrder.setHostHA(orderDO.getHostHA());
			                        copyOrder.setNetworkHA(orderDO.getNetworkHA());
			                        copyOrder.setClusterHA(orderDO.getClusterHA());
			                        copyOrder.setStorageHA(orderDO.getStorageHA());
			                        copyOrder.setCfgs(orderDO.getCfgs());
			                        copyOrder.setPort(orderDO.getPort());
			                        copyOrder.setCnt(orderDO.getCnt());
			                        // pre_major_version,pre_minor_version,pre_patch_version,pre_build_version升级相关字段
			                        copyOrder.setPreMajorVersion(orderDO.getMajorVersion());
			                        copyOrder.setPreMinorVersion(orderDO.getMinorVersion());
			                        copyOrder.setPrePatchVersion(orderDO.getPatchVersion());
			                        copyOrder.setPreBuildVersion(orderDO.getBuildVersion());
			//                        copyOrder.setPreArchId(orderDO.getPreArchId());
			                        copyOrder.setPreArchTypeCode(orderDO.getPreArchTypeCode());
			                        copyOrder.setPreUnitCnt(orderDO.getPreUnitCnt());
			                        copyOrder.setPreCpuCnt(orderDO.getPreCpuCnt());
			                        copyOrder.setPreMemSize(orderDO.getPreMemSize());
			                        copyOrder.setPreDataDirSize(orderDO.getPreDataDirSize());
			                        copyOrder.setPreLogDirSize(orderDO.getPreLogDirSize());
			                        copyOrder.setPreNetworkBandwidth(orderDO.getPreNetworkBandwidth());
			                        copyOrder.setPreCnt(orderDO.getPreCnt());
			
			                        int insertOrderCount = orderDAO.save(copyOrder);
			
			                        if (insertOrderCount != 1) {
			                            throw new ServiceException("删除时工单(tbl_order)复制插入失败");
			                        }
			                    }
			
			                    // 5.获取用户角色，并判断是否自动审批，自动执行
			                    String auditName = getUsername();
			                    boolean isAutoAudit = orderGroupService.isAutoAudit(auditName);
			                    
			                    //subscription_id订阅号有值，是云发过来的请求，直接自动审批、执行工单
			                    if(subscription_id!=null && !"".equals(subscription_id)) {
			                    	isAutoAudit = true;
			                    }
			                    if (isAutoAudit) {
			                        orderGroupService.autoAudit(copyOrderGroup);
			
			                        boolean isAutoExecute = orderGroupService.isAutoExecute(auditName);
			                      //subscription_id订阅号有值，是云发过来的请求，直接自动审批、执行工单
				                    if(subscription_id!=null && !"".equals(subscription_id)) {
				                    	isAutoExecute = true;
				                    }
			                        if (isAutoExecute) {
			                        	OrderGroupDO orderGroupDOo=orderGroupDAO.get(orderGroupId);
			                        	logger.info("删除服务qqqqqqqqqqqq: {}", orderGroupDOo.toString());
			                        	if(orderGroupDOo==null) {
			                        		throw new ServiceException("删除时工单组(tbl_order_group)查询失败");
			                        	}else {
			                        		orderGroupService.autoExecute(orderGroupDAO.get(orderGroupId),"","");
			                        		
			                        	}
			
			                            // 6.执行调用mgm删除接口
			                            /*int status=mgmApi.removeServerGroup(serverGroupId);
			                          //更改审批的状态
			                            if(status==1) {
			                            	DeleteTask deleteTask=new DeleteTask(copyOrderGroup);
			                            	Thread thread = new Thread(deleteTask);
			                                thread.start();
			                            }*/
			                            // 7.获取执行成功后task_id，保存到tbl_order_group表relate_servg_id字段中
			
			                            // 8.创建线程，执行调用mgm任务接口
			                        }
			                    }
			
			                } else {
			                    throw new ServiceException("删除时工单组(tbl_order_group)复制插入失败");
			                }
			
			            }else {
			            	throw new ServiceException("删除时工单组(tbl_order_group)查询失败");
			            }
			
			            return Result.success();
                 }
             }else {
            	 return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ILLEGAL_ID_NOT_EXIST));
             }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 服务组创建
     * 
     * @param orderGroupDO
     *            服务组对象
     * @return Result对象
     * @throws ServiceException
     *             异常处理类
     */
    @Transactional(rollbackFor = ServiceException.class)
    public ServGroupDO create(OrderGroupDO orderGroupDO,String subscription_id) throws ServiceException {
        try {
            MgmServerGroupBody mgmServerGroupBody = buildMgmServerGroupBodyForSave(orderGroupDO);
            MgmSaveServerGroup mgmSaveServerGroup = mgmApi.saveServerGroup(mgmServerGroupBody,subscription_id);
            ServGroupDO servGroupDO=new ServGroupDO();
            servGroupDO.setBusinessSubsystemId(orderGroupDO.getBusinessSubsystemId());
            servGroupDO.setRelateId(mgmSaveServerGroup.getId());
            servGroupService.save(servGroupDO);
            orderGroupDO.setRelateAppId(mgmSaveServerGroup.getId());
            orderGroupDO.setRelateTaskId(mgmSaveServerGroup.getTaskId());
            orderGroupDAO.updateServGroupId(orderGroupDO);
            
            CreateTask createTask=new CreateTask(orderGroupDO);
            Thread thread = new Thread(createTask);
            thread.start();
            
            //return Result.success(servGroupDO);
            return servGroupDO;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    /**
     * 服务组重新创建
     * 
     * @param orderGroupDO
     *            服务组对象
     * @return Result对象
     * @throws ServiceException
     *             异常处理类
     */
    @Transactional(rollbackFor = ServiceException.class)
    public Result reCreate(OrderGroupDO orderGroupDO) throws ServiceException {
        try {
        	logger.info("{}: 创建失败后删除成功，开始重新执行创建", orderGroupDO.toString());
//            MgmServerGroupBody mgmServerGroupBody = reBuildMgmServerGroupBodyForSave(orderGroupDO);
            MgmServerGroupBody mgmServerGroupBody = buildMgmServerGroupBodyForSave(orderGroupDO);
            MgmSaveServerGroup mgmSaveServerGroup = mgmApi.saveServerGroup(mgmServerGroupBody,"");
            ServGroupDO servGroupDO=new ServGroupDO();
            servGroupDO.setBusinessSubsystemId(orderGroupDO.getBusinessSubsystemId());
            servGroupDO.setRelateId(mgmSaveServerGroup.getId());
            servGroupService.save(servGroupDO);
            orderGroupDO.setRelateAppId(mgmSaveServerGroup.getId());
            orderGroupDO.setRelateTaskId(mgmSaveServerGroup.getTaskId());
            orderGroupDAO.updateServGroupId(orderGroupDO);
            
            CreateTask createTask=new CreateTask(orderGroupDO);
            Thread thread = new Thread(createTask);
            thread.start();
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 服务组扩容
     * 
     * @param orderGroupDO
     *            服务组对象
     * @return Result对象
     * @throws ServiceException
     *             异常处理类
     */
    @Transactional(rollbackFor = ServiceException.class)
    public Result reScale(String serverGroupId,OrderGroupDO orderGroupDO,ServerGroupForm serverGroupForm) throws ServiceException {
        try {
        	
        	String haMode = orderGroupDO.getOrders().get(0).getHamode();
        	MgmServerGroupResourceRequestsBody mgmServerGroupResourceRequestsBody = buildScaleMgmServerGroupResourceRequestsBody(
                    serverGroupForm,haMode);
        	
            MgmSaveServerGroup mgmSaveServerGroup = mgmApi.resourceRequestsServerGroup(serverGroupId, mgmServerGroupResourceRequestsBody);
            orderGroupDO.setRelateAppId(mgmSaveServerGroup.getId());
            orderGroupDO.setRelateTaskId(mgmSaveServerGroup.getTaskId());
            	ScaleTask scaleTask=new ScaleTask(orderGroupDO);
                Thread thread = new Thread(scaleTask);
                thread.start();
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    /**
     * 服务组升级
     * 
     * @param orderGroupDO
     *            服务组对象
     * @return Result对象
     * @throws ServiceException
     *             异常处理类
     */
    @Transactional(rollbackFor = ServiceException.class)
    public Result reImage(String serverGroupId,OrderGroupDO orderGroupDO,ServerGroupForm serverGroupForm) throws ServiceException {
        try {
        	if(nullCheck(serverGroupForm.getImageId())) {
        		return Result.failure(CheckResult.failure(OrderGroupChkRsEnum.ADD_ILLEGAL_IMAGE_VERSION_NOT_BE_NULL));
        	}
        	MgmServerGroupImageBody mgmServerGroupImageBody = buildScaleMgmServerGroupImageBody(
                    serverGroupForm);
            MgmSaveServerGroup mgmSaveServerGroup = mgmApi.imageServerGroup(serverGroupId, mgmServerGroupImageBody);
            orderGroupDO.setRelateAppId(mgmSaveServerGroup.getId());
            orderGroupDO.setRelateTaskId(mgmSaveServerGroup.getTaskId());
            ImageTask imageTask=new ImageTask(orderGroupDO);
            Thread thread = new Thread(imageTask);
            thread.start();
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    /**
     * 服务组删除
     * 
     * @param orderGroupDO
     *            服务组对象
     * @return Result对象
     * @throws ServiceException
     *             异常处理类
     */
    @Transactional(rollbackFor = ServiceException.class)
    public Result reDelete(String serverGroupId,OrderGroupDO orderGroupDO) throws ServiceException {
        try {
        	int status=mgmApi.removeServerGroup(serverGroupId);
            //更改审批的状态
              if(status==1) {
              	DeleteTask deleteTask=new DeleteTask(orderGroupDO);
              	Thread thread = new Thread(deleteTask);
                thread.start();
              }
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    /**
     * 服务组删除
     * 
     * @param orderGroupDO
     *            服务组对象
     * @return Result对象
     * @throws ServiceException
     *             异常处理类
     */
    @Transactional(rollbackFor = ServiceException.class)
    public Result reDeleteI(String serverGroupId,OrderGroupDO orderGroupDO) throws ServiceException {
        try {
        	orderGroupDO = orderGroupDAO.get(orderGroupDO.getId());
        	int status=mgmApi.removeServerGroup(serverGroupId);
            //更改审批的状态
              if(status==1) {
              	ReDeleteTask deleteTask=new ReDeleteTask(orderGroupDO);
              	Thread thread = new Thread(deleteTask);
                thread.start();
              }
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    @Transactional(rollbackFor = ServiceException.class)
    private UnitTaskDTO getShowBaseDTOTask(MgmTask mgmTask)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
    	UnitTaskDTO unitTaskDTO=new UnitTaskDTO();
    	unitTaskDTO.setId(mgmTask.getId());
    	unitTaskDTO.setAction(dictDAO.getNameByCodeAndDictTypeCode(mgmTask.getAction(), DictTypeConsts.TASK_ACTION));
    	unitTaskDTO.setStartDateTime(mgmTask.getCreatedAt());
    	unitTaskDTO.setEndDateTime(mgmTask.getFinishedAt());
    	unitTaskDTO.setStatus(dictDAO.getNameByCodeAndDictTypeCode(mgmTask.getStatus(), DictTypeConsts.TASK_STATUS));
    	UserDO userDTO=userDAO.getByUsername(getUsername());
    	UserDO userDO=new UserDO();
    	if(userDTO!=null) {
    		userDO.setName(userDTO.getName());
    		userDO.setUsername(userDTO.getUsername());
    	}
    	unitTaskDTO.setUserDTO(userDO);
    	unitTaskDTO.setError(mgmTask.getError());
    	return unitTaskDTO;
    }
    
    /**
     * restful接口数据转前端数据
     * 
     * @param mgmSite
     * @return
     * @throws IOException
     * @throws CallingInterfaceException
     * @throws ServiceNotFoundException
     * @throws ConnectConsulException
     */
    @Transactional(rollbackFor = ServiceException.class)
    private ServerGroupDTO getShowBaseDTO(MgmServerGroup mgmServerGroup)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        ServerGroupDTO serverGroupDTO = new ServerGroupDTO();
        BeanUtils.copyProperties(mgmServerGroup, serverGroupDTO);

//        serverGroupDTO.setId(mgmServerGroup.getId());
//        serverGroupDTO.setName(mgmServerGroup.getName());
//        //add by yeht for  arch 
//        serverGroupDTO.setArch(mgmServerGroup.getArch());
//        // deprecated 通过node（status）里的集群查询出对应站点信息
//        // 通过conditions（spec）里的集群查询出对应站点信息
        com.bsg.upm.dto.ServerGroupDTO.SiteDTO siteDTO = null;

        // 区域和所属者
        // tbl_order_group库中对应的是area_code和owner，area.name从字典中对应，owner.name从用户表中对应
        if (mgmServerGroup.getId() != null) {
            OrderGroupDO orderGroupDO = orderGroupDAO.getLastOneByServerGroupId(mgmServerGroup.getId());
            if (orderGroupDO != null) {
            	serverGroupDTO.setOrderGroupId(orderGroupDO.getId());
                if (orderGroupDO.getAreaCode() != null) {
                    AreaDTO areaDTO = serverGroupDTO.new AreaDTO();
                    areaDTO.setCode(orderGroupDO.getAreaCode());
                    areaDTO.setName(dictDAO.getNameByCodeAndDictTypeCode(orderGroupDO.getAreaCode(), DictTypeConsts.AREA));
                    serverGroupDTO.setArea(areaDTO);
                }
                if (orderGroupDO.getOwner() != null) {
                    OwnerDTO ownerDTO = serverGroupDTO.new OwnerDTO();
                    ownerDTO.setUsername(orderGroupDO.getOwner());
                    UserDO ownerUser = userDAO.getByUsername(orderGroupDO.getOwner());
                    if (ownerUser != null) {
                    	ownerDTO.setName(ownerUser.getName());
                        ownerDTO.setUsername(ownerUser.getUsername());
                    }
                    serverGroupDTO.setOwner(ownerDTO);
                }
                if(orderGroupDO.getBusinessSubsystemId() != null) {
                	 BusinessSystemDO businessSystemDO = businessSystemDAO.getBusinessName(orderGroupDO.getBusinessSubsystemId());
                     serverGroupDTO.setBusinessSubSystemName(businessSystemDO.getBusinessSubSystemName());
                     serverGroupDTO.setBusinessSystemName(businessSystemDO.getName());
                }
            }
        }

        // flag字段有下面4个比较得到，全满足1是true，否则0是false
        // 1.arch
        // 2.image.id
        // 3.resources
        Boolean flag = true;
        String specImageId = null;
        Arch specArch = null;
        Spec serverGroupSpec = serverGroupDTO.getSpec();
        Status serverGroupStatus = serverGroupDTO.getStatus();
        Conditions conditions=new Conditions();
        SpecResources specResources = null;
        if (mgmServerGroup.getSpec() != null && mgmServerGroup.getSpec().getDatabase() != null
                && mgmServerGroup.getSpec().getDatabase().getImage() != null) {
            specImageId = mgmServerGroup.getSpec().getDatabase().getImage().getId();
        }

        if (mgmServerGroup.getSpec() != null && mgmServerGroup.getSpec().getDatabase() != null
                && mgmServerGroup.getSpec().getDatabase().getServices() != null
                && mgmServerGroup.getSpec().getDatabase().getServices().getUnits() != null
                && mgmServerGroup.getSpec().getDatabase().getServices().getUnits().getResources() != null) {
            specResources = mgmServerGroup.getSpec().getDatabase().getServices().getUnits().getResources();
        }

        if (mgmServerGroup.getCreated() != null) {
            serverGroupDTO.setGmtCreate(mgmServerGroup.getCreated().getTimestamp());
        }

        //serverGroupDTO.setStatus(mgmServerGroup.getState());

        Task task = mgmServerGroup.getTask();
        TaskDTO taskDTO = serverGroupDTO.new TaskDTO();
        if (task != null) {
            taskDTO.setId(task.getId());

            if (task.getAction() != null) {
                TypeDTO actionDTO = serverGroupDTO.new TypeDTO();
                actionDTO.setCode(task.getAction());
                actionDTO
                        .setDisplay(dictDAO.getNameByCodeAndDictTypeCode(task.getAction(), DictTypeConsts.TASK_ACTION));
                taskDTO.setAction(actionDTO);

            }
            if (task.getStatus() != null) {
                TypeDTO statusDTO = serverGroupDTO.new TypeDTO();
                statusDTO.setCode(task.getStatus());
                statusDTO
                        .setDisplay(dictDAO.getNameByCodeAndDictTypeCode(task.getStatus(), DictTypeConsts.TASK_STATUS));
                taskDTO.setStatus(statusDTO);
            }
            serverGroupDTO.setTask(taskDTO);
        }

        // spec下数据操作
        if (mgmServerGroup.getSpec() != null && mgmServerGroup.getSpec().getDatabase() != null
        		&& mgmServerGroup.getSpec().getDatabase().getServices() != null) {
        	specArch = mgmServerGroup.getSpec().getDatabase().getServices().getArch();
        	com.bsg.upm.mgm.model.MgmServerGroup.SpecDatabase  sevGroDabase = serverGroupSpec.getDatabase();
        	SpecService  sevGroService = sevGroDabase.getServices();
        	Arch specArchDTO = sevGroService.getArch();
        	SpecUnit   specUnit  = sevGroService.getUnits();
        	SpecResources   specResource  = specUnit.getResources();
        	Requests    specRequest  = specResource.getRequests();
        	Storage specStorage = specRequest.getStorage();
        	//dataBaseDTO.setArch(specArchDTO);
        	specArchDTO.setMode(specArch.getMode());
        	specArchDTO.setReplicas(specArch.getReplicas());
        	specArchDTO.setName(dictDAO.getNameByCodeAndDictTypeCode(specArch.getMode(), DictTypeConsts.ARCH_TYPE));
        	specArchDTO.setCntName(dictDAO.getNameByCodeAndDictTypeCode(String.valueOf(specArch.getReplicas()), DictTypeConsts.ARCH_CNT));
        	serverGroupSpec.setDatabase(sevGroDabase);
        	sevGroDabase.setServices(sevGroService);
        	sevGroService.setArch(specArchDTO);
        	
        	String specPerformance = specStorage.getPerformance();
        	if (StringUtils.isNotBlank(specPerformance)) {
        		specStorage.setDisplay(dictDAO.getNameByCodeAndDictTypeCode(specPerformance, DictTypeConsts.PERFORMANCE));
        		specStorage.setCode(specStorage.getType());
        	}

        	Volume[] specVolumes = specStorage.getVolumes();
        	String specDirTypeCodeDisplay = dictDAO.getNameByCodeAndDictTypeCode(specStorage.getType(),
                    DictTypeConsts.DIR_TYPE);
        	specStorage.setTypeDisplay(specDirTypeCodeDisplay);
        	for (Volume volume : specVolumes) {
        		
        		volume.setDisplay(specDirTypeCodeDisplay);
        	}
        	 
        	
        }
        if (mgmServerGroup.getSpec() != null && mgmServerGroup.getSpec().getCmha() != null
        		&& mgmServerGroup.getSpec().getCmha().getServices() != null) {
        	specArch = mgmServerGroup.getSpec().getDatabase().getServices().getArch();
        	com.bsg.upm.mgm.model.MgmServerGroup.SpecDatabase  sevGroDabase = serverGroupSpec.getCmha();
        	SpecService  sevGroService = sevGroDabase.getServices();
        	Arch specArchDTO = sevGroService.getArch();
        	SpecUnit   specUnit  = sevGroService.getUnits();
        	SpecResources   specResource  = specUnit.getResources();
        	Requests    specRequest  = specResource.getRequests();
        	Storage specStorage = specRequest.getStorage();
        	//dataBaseDTO.setArch(specArchDTO);
        	specArchDTO.setMode(specArch.getMode());
        	specArchDTO.setReplicas(specArch.getReplicas());
        	specArchDTO.setName(dictDAO.getNameByCodeAndDictTypeCode(specArch.getMode(), DictTypeConsts.ARCH_TYPE));
        	specArchDTO.setCntName(dictDAO.getNameByCodeAndDictTypeCode(String.valueOf(specArch.getReplicas()), DictTypeConsts.ARCH_CNT));
        	serverGroupSpec.setCmha(sevGroDabase);
        	sevGroDabase.setServices(sevGroService);
        	sevGroService.setArch(specArchDTO);
        	
        	String specPerformance = specStorage.getPerformance();
        	if (StringUtils.isNotBlank(specPerformance)) {
        		specStorage.setDisplay(dictDAO.getNameByCodeAndDictTypeCode(specPerformance, DictTypeConsts.PERFORMANCE));
        		specStorage.setCode(specStorage.getType());
        	}

        	Volume[] specVolumes = specStorage.getVolumes();
        	String specDirTypeCodeDisplay = dictDAO.getNameByCodeAndDictTypeCode(specStorage.getType(),
                    DictTypeConsts.DIR_TYPE);
        	specStorage.setTypeDisplay(specDirTypeCodeDisplay);
        	for (Volume volume : specVolumes) {
        		
        		volume.setDisplay(specDirTypeCodeDisplay);
        	}
        	
        }
        if (mgmServerGroup.getSpec() != null && mgmServerGroup.getSpec().getProxy() != null
        		&& mgmServerGroup.getSpec().getProxy().getServices() != null) {
        	com.bsg.upm.mgm.model.MgmServerGroup.SpecDatabase  sevGroDabase = serverGroupSpec.getProxy();
        	SpecService  sevGroService = sevGroDabase.getServices();
        	Arch specArchDTO = sevGroService.getArch();
        	SpecUnit   specUnit  = sevGroService.getUnits();
        	SpecResources   specResource  = specUnit.getResources();
        	Requests    specRequest  = specResource.getRequests();
        	Storage specStorage = specRequest.getStorage();
        	//dataBaseDTO.setArch(specArchDTO);
        	specArchDTO.setMode(specArch.getMode());
        	specArchDTO.setReplicas(specArch.getReplicas());
        	specArchDTO.setName(dictDAO.getNameByCodeAndDictTypeCode(specArch.getMode(), DictTypeConsts.ARCH_TYPE));
        	specArchDTO.setCntName(dictDAO.getNameByCodeAndDictTypeCode(String.valueOf(specArch.getReplicas()), DictTypeConsts.ARCH_CNT));
        	serverGroupSpec.setProxy(sevGroDabase);
        	sevGroDabase.setServices(sevGroService);
        	sevGroService.setArch(specArchDTO);
        	
        	String specPerformance = specStorage.getPerformance();
        	if (StringUtils.isNotBlank(specPerformance)) {
        		specStorage.setDisplay(dictDAO.getNameByCodeAndDictTypeCode(specPerformance, DictTypeConsts.PERFORMANCE));
        		specStorage.setCode(specStorage.getType());
        	}

        	Volume[] specVolumes = specStorage.getVolumes();
        	String specDirTypeCodeDisplay = dictDAO.getNameByCodeAndDictTypeCode(specStorage.getType(),
                    DictTypeConsts.DIR_TYPE);
        	specStorage.setTypeDisplay(specDirTypeCodeDisplay);
        	for (Volume volume : specVolumes) {
        		
        		volume.setDisplay(specDirTypeCodeDisplay);
        	}
        	
        }
        
        
        if (mgmServerGroup.getStatus() != null && mgmServerGroup.getStatus().getProxy() != null
        		&& mgmServerGroup.getStatus().getProxy().getServices() != null) {
        	StatusDatabase  sevGroDabase = serverGroupStatus.getProxy();
        	StatusService[]  sevGroService = sevGroDabase.getServices();
        	Arch specArchDTO = sevGroService[0].getArch();
        	//Unit[]   specUnit  = sevGroService[0].getUnits();
        	//SpecResources   specResource  = specUnit.getResources();
        	//Requests    specRequest  = specResource.getRequests();
        	//Storage specStorage = specRequest.getStorage();
        	//dataBaseDTO.setArch(specArchDTO);
        	specArchDTO.setMode(specArch.getMode());
        	specArchDTO.setReplicas(specArch.getReplicas());
        	specArchDTO.setName(dictDAO.getNameByCodeAndDictTypeCode(specArch.getMode(), DictTypeConsts.ARCH_TYPE));
        	specArchDTO.setCntName(dictDAO.getNameByCodeAndDictTypeCode(String.valueOf(specArch.getReplicas()), DictTypeConsts.ARCH_CNT));
        	serverGroupStatus.setProxy(sevGroDabase);
        	sevGroDabase.setServices(sevGroService);
        	//sevGroService.setArch(specArchDTO);
        	
//        	String specPerformance = specStorage.getPerformance();
//        	if (StringUtils.isNotBlank(specPerformance)) {
//        		specStorage.setDisplay(dictDAO.getNameByCodeAndDictTypeCode(specPerformance, DictTypeConsts.PERFORMANCE));
//        		specStorage.setCode(specStorage.getType());
//        	}

//        	Volume[] specVolumes = specStorage.getVolumes();
//        	for (Volume volume : specVolumes) {
//        		String specDirTypeCodeDisplay = dictDAO.getNameByCodeAndDictTypeCode(specStorage.getType(),
//                        DictTypeConsts.DIR_TYPE);
//        		volume.setDisplay(specDirTypeCodeDisplay);
//        	}
        	
        }
        
        if (mgmServerGroup.getStatus() != null && mgmServerGroup.getStatus().getCmha() != null 
        		&& mgmServerGroup.getStatus().getCmha().getServices() != null) {
        	StatusDatabase  sevGroDabase = serverGroupStatus.getCmha();
        	StatusService[]  sevGroService = sevGroDabase.getServices();
        	Arch specArchDTO = sevGroService[0].getArch();
        	//Unit[]   specUnit  = sevGroService[0].getUnits();
        	//SpecResources   specResource  = specUnit.getResources();
        	//Requests    specRequest  = specResource.getRequests();
        	//Storage specStorage = specRequest.getStorage();
        	//dataBaseDTO.setArch(specArchDTO);
        	specArchDTO.setMode(specArch.getMode());
        	specArchDTO.setReplicas(specArch.getReplicas());
        	specArchDTO.setName(dictDAO.getNameByCodeAndDictTypeCode(specArch.getMode(), DictTypeConsts.ARCH_TYPE));
        	specArchDTO.setCntName(dictDAO.getNameByCodeAndDictTypeCode(String.valueOf(specArch.getReplicas()), DictTypeConsts.ARCH_CNT));
        	serverGroupStatus.setCmha(sevGroDabase);
        	sevGroDabase.setServices(sevGroService);
        	//sevGroService.setArch(specArchDTO);
        	
//        	String specPerformance = specStorage.getPerformance();
//        	if (StringUtils.isNotBlank(specPerformance)) {
//        		specStorage.setDisplay(dictDAO.getNameByCodeAndDictTypeCode(specPerformance, DictTypeConsts.PERFORMANCE));
//        		specStorage.setCode(specStorage.getType());
//        	}

//        	Volume[] specVolumes = specStorage.getVolumes();
//        	for (Volume volume : specVolumes) {
//        		String specDirTypeCodeDisplay = dictDAO.getNameByCodeAndDictTypeCode(specStorage.getType(),
//                        DictTypeConsts.DIR_TYPE);
//        		volume.setDisplay(specDirTypeCodeDisplay);
//        	}
        	
        }
        
        
        if (mgmServerGroup.getStatus() != null && mgmServerGroup.getStatus().getDatabase() != null
        		&& mgmServerGroup.getStatus().getDatabase().getServices() != null) {
        	StatusDatabase  sevGroDabase = serverGroupStatus.getDatabase();
        	StatusService[]  sevGroService = sevGroDabase.getServices();
        	Arch specArchDTO = sevGroService[0].getArch();
        	//Unit[]   specUnit  = sevGroService[0].getUnits();
        	//SpecResources   specResource  = specUnit.getResources();
        	//Requests    specRequest  = specResource.getRequests();
        	//Storage specStorage = specRequest.getStorage();
        	//dataBaseDTO.setArch(specArchDTO);
        	specArchDTO.setMode(specArch.getMode());
        	specArchDTO.setReplicas(specArch.getReplicas());
        	specArchDTO.setName(dictDAO.getNameByCodeAndDictTypeCode(specArch.getMode(), DictTypeConsts.ARCH_TYPE));
        	specArchDTO.setCntName(dictDAO.getNameByCodeAndDictTypeCode(String.valueOf(specArch.getReplicas()), DictTypeConsts.ARCH_CNT));
        	serverGroupStatus.setDatabase(sevGroDabase);
        	sevGroDabase.setServices(sevGroService);
        	//sevGroService.setArch(specArchDTO);
        	
//        	String specPerformance = specStorage.getPerformance();
//        	if (StringUtils.isNotBlank(specPerformance)) {
//        		specStorage.setDisplay(dictDAO.getNameByCodeAndDictTypeCode(specPerformance, DictTypeConsts.PERFORMANCE));
//        		specStorage.setCode(specStorage.getType());
//        	}

//        	Volume[] specVolumes = specStorage.getVolumes();
//        	for (Volume volume : specVolumes) {
//        		String specDirTypeCodeDisplay = dictDAO.getNameByCodeAndDictTypeCode(specStorage.getType(),
//                        DictTypeConsts.DIR_TYPE);
//        		volume.setDisplay(specDirTypeCodeDisplay);
//        	}
        	
        }
        
//        if (mgmServerGroup.getSpec() != null && mgmServerGroup.getSpec().getDatabase() != null) {
//        	
//        	SpecDTO specDTO = serverGroupDTO.new SpecDTO();
//        	SpecDatabase dataBaseDTO = serverGroupDTO.new SpecDatabase();
//        	specDTO.setDatabase(dataBaseDTO);
//        	serverGroupDTO.setSpecs(specDTO);
//        	// 现在固定都是mysql
//        	dataBaseDTO.setType("mysql");
//
//            // mysql架构到tbl_arch表中查询
//            if (mgmServerGroup.getSpec().getDatabase().getServices() != null) {
//            	
//            	Conditions specConditions = mgmServerGroup.getSpec().getDatabase().getServices().getConditions();
//            	/*if (specConditions != null && specConditions.getCluster() != null) {
//            		String[] specClusterCandidatesId = specConditions.getCluster().getCandidatesId();
//            		if (siteDTO == null && specClusterCandidatesId != null && specClusterCandidatesId.length > 0) {
//            			// 通过查询spec下集群信息去获取站点
//                        siteDTO = serverGroupDTO.new SiteDTO();
//                        serverGroupDTO.setSite(siteDTO);
//                        MgmClusterQuery tMgmClusterQuery = new MgmClusterQuery();
//                        tMgmClusterQuery.setId(specClusterCandidatesId[0]);
//                        List<MgmCluster> tMgmClusters = mgmApi.listCluster(tMgmClusterQuery);
//                        if (tMgmClusters != null && tMgmClusters.size() > 0
//                                && tMgmClusters.get(0).getSite() != null) {
//                            siteDTO.setId(tMgmClusters.get(0).getSite().getId());
//                            siteDTO.setName(tMgmClusters.get(0).getSite().getName());
//                        }
//            		}
//            	}*/
//            	
//            	dataBaseDTO.setState(mgmServerGroup.getSpec().getDatabase().getServices().getState());
//                // arch通过查询tbl_arch获取
//                specArch = mgmServerGroup.getSpec().getDatabase().getServices().getArch();
//                conditions=mgmServerGroup.getSpec().getDatabase().getServices().getConditions();
//                
//                if(conditions != null &&conditions.getCluster()!=null&&conditions.getHost()!=null&&conditions.getNetwork()!=null&&conditions.getStorageRemote()!=null) {
//                	Ha ha=serverGroupDTO.new Ha();
//                	ha.setCluster(conditions.getCluster().getHighAvailability());
//                	ha.setHost(conditions.getHost().getHighAvailability());
//                	ha.setNetwoking(conditions.getNetwork().getHighAvailability());
//                	ha.setStorage(conditions.getStorageRemote().getHighAvailability());
//                	dataBaseDTO.setHa(ha);
//                }
////                SpecDTO specDTO = serverGroupDTO.new SpecDTO();
//                if (specArch != null && specArch.getMode() != null && specArch.getReplicas() != null) {
//                	SpecArchDTO specArchDTO = serverGroupDTO.new SpecArchDTO();
//                	dataBaseDTO.setArch(specArchDTO);
//                	specArchDTO.setMode(specArch.getMode());
//                	specArchDTO.setReplicas(specArch.getReplicas());
//                	specArchDTO.setName(dictDAO.getNameByCodeAndDictTypeCode(specArch.getMode(), DictTypeConsts.ARCH_TYPE));
//                	
//                    MysqlArchDTO mysqlArchDTO = serverGroupDTO.new MysqlArchDTO();
//                    /*ArchDO specArchDO = archDAO.getByTypeAndTotalCnt(specArch.getMode(), specArch.getReplicas());
//
//                    if (specArchDO != null) {
//                        mysqlArchDTO.setId(specArchDO.getId());
//                        mysqlArchDTO.setName(specArchDO.getName());
//                    }*/
//                    mysqlArchDTO.setTypeCode(specArch.getMode());
//                    mysqlArchDTO.setTypeName(dictDAO.getNameByCodeAndDictTypeCode(specArch.getMode(), DictTypeConsts.ARCH_TYPE));
//                    mysqlArchDTO.setCntCode(String.valueOf(specArch.getReplicas()));
//                    mysqlArchDTO.setCntName(dictDAO.getNameByCodeAndDictTypeCode(String.valueOf(specArch.getReplicas()), DictTypeConsts.ARCH_CNT));
//                    serverGroupDTO.setMysqlArchs(mysqlArchDTO);
//                }
//
//                if (mgmServerGroup.getSpec().getDatabase().getServices().getUnits() != null
//                        && mgmServerGroup.getSpec().getDatabase().getServices().getUnits().getResources() != null
//                        && mgmServerGroup.getSpec().getDatabase().getServices().getUnits().getResources()
//                                .getRequests() != null) {
//                    Requests specRequests = mgmServerGroup.getSpec().getDatabase().getServices().getUnits()
//                            .getResources().getRequests();
//                    
//                    dataBaseDTO.setCpuCnt(specRequests.getCpu());
//                    //add for Hacontainer 容器漂移方式
//                    dataBaseDTO.setHacontainer(mgmServerGroup.getSpec().getDatabase().getServices().getUnits().getHa());
//                    
//                    MysqlScaleDTO mysqlScaleDTO = serverGroupDTO.new MysqlScaleDTO();
//                    mysqlScaleDTO.setCpu(specRequests.getCpu());
//                    if (specRequests.getMemory() != null) {
//                    	dataBaseDTO.setMemSize( specRequests.getMemory());
//                    	
//                        mysqlScaleDTO.setMemSize(specRequests.getMemory());
//                    }
//                    serverGroupDTO.setMysqlScales(mysqlScaleDTO);
//                    
//                    Storage specStorage = specRequests.getStorage();
//                    if (specStorage != null) {
//                    	
//                    	String specPerformance = specStorage.getPerformance();
//                    	TypeDTO specPerformanceDTO = serverGroupDTO.new TypeDTO();
//                    	if (StringUtils.isNotBlank(specPerformance)) {
//                    		dataBaseDTO.setDataDirPerformance(specPerformanceDTO);
//                    		dataBaseDTO.setLogDirPerformance(specPerformanceDTO);
//                    		
//                    		specPerformanceDTO.setCode(specPerformance);
//                    		specPerformanceDTO.setDisplay(dictDAO.getNameByCodeAndDictTypeCode(specPerformance, DictTypeConsts.PERFORMANCE));
//                    		
//                    	}
//                    	
//                    	Volume[] specVolumes = specStorage.getVolumes();
//                        String specDirTypeCodeDisplay = dictDAO.getNameByCodeAndDictTypeCode(specStorage.getType(),
//                                DictTypeConsts.DIR_TYPE);
//                        
//                        for (Volume volume : specVolumes) {
//                            if ("data".equalsIgnoreCase(volume.getType())) {
//                                if (volume.getCapacity() != null) {
//                                	dataBaseDTO.setDataDirSize( volume.getCapacity());
//                                }
//                                TypeDTO specDataDirType = serverGroupDTO.new TypeDTO();
//                                specDataDirType.setCode(specStorage.getType());
//                                specDataDirType.setDisplay(specDirTypeCodeDisplay);
//                                dataBaseDTO.setDataDirType(specDataDirType);
//                            }
//                            if ("log".equalsIgnoreCase(volume.getType())) {
//                                if (volume.getCapacity() != null) {
//                                	dataBaseDTO.setLogDirSize( volume.getCapacity());
//                                }
//                                TypeDTO specLogDirType = serverGroupDTO.new TypeDTO();
//                                specLogDirType.setCode(specStorage.getType());
//                                specLogDirType.setDisplay(specDirTypeCodeDisplay);
//                                dataBaseDTO.setLogDirType(specLogDirType);
//                            }
//                        }
//                    }
//                    
//                }
//
//            }
//            
//            // mysql镜像通过调用mgm接口查询
//            Image specImage = mgmServerGroup.getSpec().getDatabase().getImage();
//            if (specImage != null && specImage.getId() != null) {
//            	SpecVersionDTO specVersionDTO = serverGroupDTO.new SpecVersionDTO();
//            	dataBaseDTO.setVersion(specVersionDTO);
//            	specVersionDTO.setMajor(specImage.getMajor());
//            	specVersionDTO.setMinor(specImage.getMinor());
//            	specVersionDTO.setPatch(specImage.getPatch());
//            	specVersionDTO.setBuild(specImage.getBuild());
//            	
//                MysqlVersionDTO mysqlVersionDTO = serverGroupDTO.new MysqlVersionDTO();
//
//               /* MgmImageQuery mgmImageQuery = new MgmImageQuery();
//                mgmImageQuery.setId(specImage.getId());
//                List<MgmImage> specMgmImages = mgmApi.listImage(mgmImageQuery);
//                if (specMgmImages != null && specMgmImages.size() > 0) {
//                    mysqlVersionDTO.setId(specImage.getId());*/
//
////                    MgmImage tSpecMgmImage = specMgmImages.get(0);
//                	mysqlVersionDTO.setId(specImage.getId());
//                    StringBuilder tVersionName = new StringBuilder();
//                    tVersionName.append(specImage.getType());
//                    tVersionName.append("-").append(specImage.getMajor());
//                    tVersionName.append(".").append(specImage.getMinor());
//                    tVersionName.append(".").append(specImage.getPatch());
//                    tVersionName.append(".").append(specImage.getBuild());
//                    /*if (tSpecMgmImage.getType() != null) {
//                        tVersionName.append(tSpecMgmImage.getType());
//                    }
//                    if (tSpecMgmImage.getMajor() != null) {
//                        tVersionName.append(".").append(tSpecMgmImage.getMajor());
//                    }
//                    if (tSpecMgmImage.getMinor() != null) {
//                        tVersionName.append(".").append(tSpecMgmImage.getMinor());
//                    }
//                    if (tSpecMgmImage.getPatch() != null) {
//                        tVersionName.append(".").append(tSpecMgmImage.getPatch());
//                    }
//                    if (tSpecMgmImage.getBuild() != null) {
//                        tVersionName.append(".").append(tSpecMgmImage.getBuild());
//                    }*/
//                    mysqlVersionDTO.setName(tVersionName.toString());
//                    serverGroupDTO.setMysqlVersions(mysqlVersionDTO);
//                }
////            }
//        }
//
//        // status下数据操作
//        ServerDTO serverDTOs = serverGroupDTO.new ServerDTO(); 
//        ServerDatabase databaseDTOs = serverGroupDTO.new ServerDatabase();
//        ServerDatabase cmhaDTOs = serverGroupDTO.new ServerDatabase();
//        ServerDatabase proxyDTOs = serverGroupDTO.new ServerDatabase();
//        //ServerDatabase cmhaDTOs = new serverGroupDTO.ServerDatabase();
//        //List<ServerDTO> proxyDTOs = new ArrayList<>();
//        if (mgmServerGroup.getStatus() != null && mgmServerGroup.getStatus().getDatabase() != null
//                && mgmServerGroup.getStatus().getDatabase().getServices() != null) {
//            com.bsg.upm.mgm.model.MgmServerGroup.StatusService[] services = mgmServerGroup.getStatus().getDatabase()
//                    .getServices();
//
//            for (com.bsg.upm.mgm.model.MgmServerGroup.StatusService service : services) {
//                ServerDTO currentServer = serverGroupDTO.new ServerDTO();
//                //ServerDatabase currentServerDatabase = serverGroupDTO.new ServerDatabase();
//                //serverDTOs.add(currentServer);
//                currentServer.setDatabse(databaseDTOs);
//
//                // servs[i].type字段写死mysql
//                databaseDTOs.setType("mysql");
//                // port是services下ports数组中name=server
//                Port[] tPorts = service.getPorts();
//                if (tPorts != null) {
//                    for (Port port : tPorts) {
//                        if ("server".equalsIgnoreCase(port.getName())) {
//                        	databaseDTOs.setPort(Integer.valueOf(port.getPort()));
//                            break;
//                        }
//                    }
//                }
//
//                // arch通过查询tbl_arch获取
//                Arch statusArch = service.getArch();
//                if (statusArch != null && statusArch.getMode() != null && statusArch.getReplicas() != null) {
//                    /*ArchDTO currentArch = serverGroupDTO.new ArchDTO();
//                    currentServer.setArch(currentArch);
//                    ArchDO archDO = archDAO.getByTypeAndTotalCnt(statusArch.getMode(), statusArch.getReplicas());
//                    if (archDO != null) {
//                        currentArch.setId(archDO.getId());
//                        currentArch.setName(archDO.getName());
//                    }*/
//                	currentServerDatabase.setArchTypeCode(statusArch.getMode());
//                	currentServerDatabase.setArchTypeName(dictDAO.getNameByCodeAndDictTypeCode(statusArch.getMode(), DictTypeConsts.ARCH_TYPE));
//                	currentServerDatabase.setUnitCnt(String.valueOf(statusArch.getReplicas()));
//                	currentServerDatabase.setArchCntName(dictDAO.getNameByCodeAndDictTypeCode(String.valueOf(statusArch.getReplicas()), DictTypeConsts.ARCH_CNT));
//                } 
//
//                if (flag) {
//                    if (specArch != null && statusArch != null) {
//                        if (statusArch.getMode() != null && specArch.getMode() != null) {
//                            if (!statusArch.getMode().equalsIgnoreCase(specArch.getMode())) {
//                                flag = false;
//                            }
//                        } else if (statusArch.getMode() == null && specArch.getMode() == null) {
//                        } else {
//                            flag = false;
//                        }
//                    } else if (specArch == null && statusArch == null) {
//                    } else {
//                        flag = false;
//                    }
//                }
//
//                MgmServerGroup.Unit[] units = service.getUnits();
//                List<UnitDTO> unitDTOs = new ArrayList<>();
//                if(units!=null) {
//	                for (Unit unit : units) {
//	                    UnitDTO currentUnit = serverGroupDTO.new UnitDTO();
//	                    unitDTOs.add(currentUnit);
//	                    currentUnit.setTask(taskDTO);
//	                    currentUnit.setId(unit.getId());
//	                    currentUnit.setSequence(unit.getId().split("-")[3]);
//	                    // unit的type字段，写死mysql
//	                    currentUnit.setType("mysql");
//	                    // unit的name就是id值
//	                    currentUnit.setName(unit.getId());
//	
//	                    Node tNode = unit.getNode();
//	                    if (tNode != null) {
//	                        HostDTO currentHost = serverGroupDTO.new HostDTO();
//	                        currentUnit.setHost(currentHost);
//	                        currentHost.setId(tNode.getId());
//	                        currentHost.setIp(tNode.getHostIp());
//	                        ClusterDTO currentCluster = serverGroupDTO.new ClusterDTO();
//	                        currentHost.setCluster(currentCluster);
//	
//	                        if (tNode.getCluster() != null) {
//	                            currentCluster.setId(tNode.getCluster().getId());
//	                            currentCluster.setName(tNode.getCluster().getName());
//	
//	                            // 站点设置，非空时只查一次
//	                            // TODO 这个地方可以优化，可以一次查询所有集群信息，在获取的信息中遍历，不用每次都调用mgm接口
//	                            /*if (siteDTO == null && tNode.getCluster() != null) {
//	                                siteDTO = serverGroupDTO.new SiteDTO();
//	                                MgmClusterQuery tMgmClusterQuery = new MgmClusterQuery();
//	                                tMgmClusterQuery.setId(tNode.getCluster().getId());
//	                                List<MgmCluster> tMgmClusters = mgmApi.listCluster(tMgmClusterQuery);
//	                                if (tMgmClusters != null && tMgmClusters.size() > 0
//	                                        && tMgmClusters.get(0).getSite() != null) {
//	                                    siteDTO.setId(tMgmClusters.get(0).getSite().getId());
//	                                    siteDTO.setName(tMgmClusters.get(0).getSite().getName());
//	                                }
//	                            }*/
//	                        }
//	
//	                    }
//	                    
//	                    Replication tReplication = unit.getReplication();
//	                    if (tReplication != null) {
//	                    	ReplicationDTO currentReplicationDTO = serverGroupDTO.new ReplicationDTO();
//	                    	currentUnit.setReplication(currentReplicationDTO);
//	                    	
//	                    	currentReplicationDTO.setSelfIp(tReplication.getSelfIp());
//	                    	currentReplicationDTO.setSelfPort(tReplication.getSelfPort());
//	                    	// 角色在字典表中
//	                    	if (StringUtils.isNotBlank(tReplication.getRole())) {
//	                    		currentReplicationDTO.setRole(tReplication.getRole());
//	                    		currentReplicationDTO.setRoleName(dictDAO.getNameByCodeAndDictTypeCode(tReplication.getRole(), DictTypeConsts.REPLICATION_ROLE));
//	                    	}
//	                    	currentReplicationDTO.setMasterIp(tReplication.getMasterIp());
//	                    	currentReplicationDTO.setMasterPort(tReplication.getMasterPort());
//	                    	currentReplicationDTO.setSlaveIoRunning(tReplication.getSlaveIoRunning());
//	                    	currentReplicationDTO.setSlaveSqlRunning(tReplication.getSlaveSqlRunning());
//	                    	// 根据slave_io_running和slave_sql_running值判断
//	                    	// All yes - passing
//	                    	// All no - critical
//	                    	// Exist no - warning
//	                    	// Else - unknown
//	                    	String tSlaveRuning = "unknown";
//	                    	if (StringUtils.isNotBlank(tReplication.getSlaveIoRunning()) && StringUtils.isNoneBlank(tReplication.getSlaveSqlRunning())) {
//	                    		if ("yes".equalsIgnoreCase(tReplication.getSlaveIoRunning()) && "yes".equalsIgnoreCase(tReplication.getSlaveSqlRunning())) {
//	                    			tSlaveRuning = "passing";
//	                    		}
//	                    		if ("no".equalsIgnoreCase(tReplication.getSlaveIoRunning()) && "no".equalsIgnoreCase(tReplication.getSlaveSqlRunning())) {
//	                    			tSlaveRuning = "critical";
//	                    		}
//	                    		if ("no".equalsIgnoreCase(tReplication.getSlaveIoRunning()) || "no".equalsIgnoreCase(tReplication.getSlaveSqlRunning())) {
//	                    			tSlaveRuning = "warning";
//	                    		}
//	                    	}
//	                    	currentReplicationDTO.setSlaveRunning(tSlaveRuning);
//	                    	
//	                    	currentReplicationDTO.setSlaveIoState(tReplication.getSlaveIoState());
//	                    	currentReplicationDTO.setSlaveSqlRunningState(tReplication.getSlaveSqlRunningState());
//	                    	currentReplicationDTO.setSecondsBehindMaster(tReplication.getSecondsBehindMaster());
//	                    	currentReplicationDTO.setMasterLogFile(tReplication.getMasterLogFile());
//	                    	currentReplicationDTO.setRelayMasterLogFile(tReplication.getRelayMasterLogFile());
//	                    	currentReplicationDTO.setReadMasterLogPos(tReplication.getReadMasterLogPos());
//	                    	currentReplicationDTO.setExecMasterLogPos(tReplication.getExecMasterLogPos());
//	                    	currentReplicationDTO.setRelayLogFile(tReplication.getRelayLogFile());
//	                    	currentReplicationDTO.setRelayLogPos(tReplication.getRelayLogPos());
//	                    	currentReplicationDTO.setLastIoError(tReplication.getLastIoError());
//	                    	currentReplicationDTO.setLastSqlError(tReplication.getLastSqlError());
//	                    }
//	
//	                    currentUnit.setServIp(unit.getIp());
//	
//	                    if (unit.getResources() != null) {
//	                        currentUnit.setCpuCnt(unit.getResources().getCpu());
//	                        if (unit.getResources().getMemory() != null) {
//	                            currentUnit.setMemSize( unit.getResources().getMemory());
//	                        }
//	
//	                        currentUnit.setNetworkBandwidth(unit.getResources().getNetBandwidth());
//	
//	                        Storage storage = unit.getResources().getStorage();
//	                        if (storage != null && storage.getVolumes() != null) {
//	                            Volume[] volumes = storage.getVolumes();
//	                            String dirTypeCodeDisplay = dictDAO.getNameByCodeAndDictTypeCode(storage.getType(),
//	                                    DictTypeConsts.DIR_TYPE);
//	                            for (Volume volume : volumes) {
//	                                if ("data".equalsIgnoreCase(volume.getType())) {
//	                                    if (volume.getCapacity() != null) {
//	                                        currentUnit.setDataDirSize( volume.getCapacity());
//	                                    }
//	                                    TypeDTO dataDirType = serverGroupDTO.new TypeDTO();
//	                                    dataDirType.setCode(storage.getType());
//	                                    dataDirType.setDisplay(dirTypeCodeDisplay);
//	                                    currentUnit.setDataDirType(dataDirType);
//	                                }
//	                                if ("log".equalsIgnoreCase(volume.getType())) {
//	                                    if (volume.getCapacity() != null) {
//	                                        currentUnit.setLogDirSize( volume.getCapacity());
//	                                    }
//	                                    TypeDTO logDirType = serverGroupDTO.new TypeDTO();
//	                                    logDirType.setCode(storage.getType());
//	                                    logDirType.setDisplay(dirTypeCodeDisplay);
//	                                    currentUnit.setLogDirType(logDirType);
//	                                }
//	                            }
//	
//	                        }
//	                    }
//	
//	                    currentUnit.setStatus(unit.getState());
//	                    currentUnit.setContainerStatus(unit.getPodState());
//	
//	                    // TODO unit.task没有明确，不设置
//	                    // data.serv[i].units[j].task.id
//	                    // data.serv[i].units[j].task.action.code
//	                    // data.serv[i].units[j].task.action.display
//	                    // data.serv[i].units[j].task.status.code
//	                    // data.serv[i].units[j].task.status.display
//	
//	                    Image image = unit.getImage();
//	                    if (flag) {
//	                        if (specImageId != null && unit.getImage().getId() != null) {
//	                            if (!specImageId.equalsIgnoreCase(unit.getImage().getId())) {
//	                                flag = false;
//	                            }
//	                        } else if (specImageId == null && unit.getImage().getId() == null) {
//	
//	                        } else {
//	                            flag = false;
//	                        }
//	
//	                        Requests cTSpecRequests = specResources.getRequests();
//	                        if (cTSpecRequests != null && unit.getResources() != null) {
//	                        	
//	                        	boolean tResourceFlag = Objects.equals(cTSpecRequests.getCpu(), unit.getResources().getCpu())
//	                                    && Objects.equals(cTSpecRequests.getMemory(), unit.getResources().getMemory())
//	                                    && Objects.equals(cTSpecRequests.getNetBandwidth(), unit.getResources().getNetBandwidth());
//	                            
//	                            if (tResourceFlag) {
//	                                Volume[] tSpecVolumes = null;
//	                                if (cTSpecRequests.getStorage() != null
//	                                        && cTSpecRequests.getStorage().getVolumes() != null) {
//	                                    tSpecVolumes = cTSpecRequests.getStorage().getVolumes();
//	                                }
//	
//	                                Volume[] tStatusVolumes = null;
//	                                if (unit.getResources().getStorage() != null
//	                                        && unit.getResources().getStorage().getVolumes() != null) {
//	                                    tStatusVolumes = unit.getResources().getStorage().getVolumes();
//	                                }
//	                                if (tSpecVolumes != null && tStatusVolumes != null) {
//	                                    if (tSpecVolumes.length != tStatusVolumes.length) {
//	                                        flag = false;
//	                                    } else {
//	                                        for (int i = 0; i < tStatusVolumes.length; i++) {
//	                                            if (tStatusVolumes[i].getType() != null) {
//	                                                boolean statusVolumeFlag = false;
//	
//	                                                for (int j = 0; j < tSpecVolumes.length; j++) {
//	                                                    if (tStatusVolumes[i].getType()
//	                                                            .equalsIgnoreCase(tSpecVolumes[j].getType())
//	                                                            && tStatusVolumes[i].getCapacity().compareTo(
//	                                                                    tSpecVolumes[j].getCapacity()) == 0
//	                                                            ) {
//	                                                        statusVolumeFlag = true;
//	                                                        break;
//	                                                    }
//	                                                }
//	
//	                                                if (!statusVolumeFlag) {
//	                                                    flag = false;
//	                                                    break;
//	                                                }
//	                                            }
//	
//	                                        }
//	                                    }
//	
//	                                } else if (tSpecVolumes == null && tStatusVolumes == null) {
//	                                } else {
//	                                    flag = false;
//	                                }
//	
//	                            } else {
//	                                flag = false;
//	                            }
//	
//	                        } else if (cTSpecRequests == null && unit.getResources() == null) {
//	                        } else {
//	                            flag = false;
//	                        }
//	                    }
//	
//	                    if (image != null) {
//	                        com.bsg.upm.dto.ServerGroupDTO.VersionDTO currentVersion = serverGroupDTO.new VersionDTO();
//	                        if(image.getMajor()!=null&&image.getMinor()!=null&&image.getPatch()!=null&&image.getBuild()!=null) {
//	                        	currentVersion.setMajor(unit.getImage().getMajor());
//	                            currentVersion.setMinor(unit.getImage().getMinor());
//	                            currentVersion.setPatch(unit.getImage().getPatch());
//	                            currentVersion.setBuild(unit.getImage().getBuild());
//	                        }
//	                        currentUnit.setVersion(currentVersion);
//	                    }
//	
//	                }
//                }
//                if (unitDTOs.size() > 0) {
//                	currentServerDatabase.setUnits(unitDTOs.toArray(new UnitDTO[unitDTOs.size()]));
//                }
//            }
//
//        } else {
//        	flag = false;
//        }
//        cmhaDTOs = buildCmha(serverGroupDTO,mgmServerGroup,cmhaDTOs,"cmha");
//        proxyDTOs = buildCmha(serverGroupDTO,mgmServerGroup,proxyDTOs,"proxy");
//        
//       // serverGroupDTO.setServs(servs);
//        
//        if (serverDTOs.size() > 0) {
//            serverGroupDTO.setServs(serverDTOs.get(0));
//            serverDTOs.get(0).setCmha(cmhaDTOs);
//            serverDTOs.get(0).setProxy(proxyDTOs);
//            
//        }
//        if (cmhaDTOs.size() > 0) {
//        	serverGroupDTO.setCmha(cmhaDTOs.toArray(new ServerDTO[cmhaDTOs.size()]));
//        }
//        if (proxyDTOs.size() > 0) {
//        	serverGroupDTO.setProxy(proxyDTOs.toArray(new ServerDTO[proxyDTOs.size()]));
//        }

        serverGroupDTO.setExpect(flag);

        return serverGroupDTO;
    }

    private MgmServerGroupResourceRequestsBody buildScaleMgmServerGroupResourceRequestsBody(
            ServerGroupForm serverGroupForm,String haMode) {
        MgmServerGroupResourceRequestsBody mgmServerGroupResourceRequestsBody = new MgmServerGroupResourceRequestsBody();

        SpecBody spec = new MgmServerGroupResourceRequestsBody.SpecBody();
        mgmServerGroupResourceRequestsBody.setSpec(spec);

        DatabaseBody database = new MgmServerGroupResourceRequestsBody.DatabaseBody();
        DatabaseBody proxy = new MgmServerGroupResourceRequestsBody.DatabaseBody();
        DatabaseBody cmha = new MgmServerGroupResourceRequestsBody.DatabaseBody();
        //haMode为true并且参数不全为null时，才带proxy和cmha的信息
        if("true".equals(haMode)) {
        	logger.info("服务扩容serverGroupForm信息: {}", serverGroupForm.toString());
        	if(serverGroupForm.getCmhaCnpCnt() != null ||serverGroupForm.getCmhaMemSize() != null 
        			|| serverGroupForm.getCmhaDataDirSize() != null || serverGroupForm.getCmhaLogDirSize() != null) {
        		spec.setCmha(cmha);
        	}
        	if(serverGroupForm.getProxyCnpCnt() != null ||serverGroupForm.getProxyMemSize() != null 
        			|| serverGroupForm.getProxyDataDirSize() != null || serverGroupForm.getProxyLogDirSize() != null) {
        		spec.setProxy(proxy);
        	}
        }
        
        spec.setDatabase(database);

        ServicesBody services = new MgmServerGroupResourceRequestsBody.ServicesBody();
        ServicesBody proxyServices = new MgmServerGroupResourceRequestsBody.ServicesBody();
        ServicesBody cmhaServices = new MgmServerGroupResourceRequestsBody.ServicesBody();
        database.setServices(services);
        proxy.setServices(proxyServices);
        cmha.setServices(cmhaServices);

        UnitsBody units = new MgmServerGroupResourceRequestsBody.UnitsBody();
        UnitsBody proxyunits = new MgmServerGroupResourceRequestsBody.UnitsBody();
        UnitsBody cmhaunits = new MgmServerGroupResourceRequestsBody.UnitsBody();
        services.setUnits(units);
        proxyServices.setUnits(proxyunits);
        cmhaServices.setUnits(cmhaunits);

        ResourcesBody resources = new MgmServerGroupResourceRequestsBody.ResourcesBody();
        ResourcesBody proxyresources = new MgmServerGroupResourceRequestsBody.ResourcesBody();
        ResourcesBody cmharesources = new MgmServerGroupResourceRequestsBody.ResourcesBody();
        units.setResources(resources);
        proxyunits.setResources(proxyresources);
        cmhaunits.setResources(cmharesources);

        RequestsBody requests = new MgmServerGroupResourceRequestsBody.RequestsBody();
        RequestsBody cmharequests = new MgmServerGroupResourceRequestsBody.RequestsBody();
        RequestsBody proxyrequests = new MgmServerGroupResourceRequestsBody.RequestsBody();
        resources.setRequests(requests);
        cmharesources.setRequests(cmharequests);
        proxyresources.setRequests(proxyrequests);
        
        if (serverGroupForm.getCnpCnt() != null) {
            requests.setCpu(serverGroupForm.getCnpCnt());
        }
        if (serverGroupForm.getMemSize() != null) {
            requests.setMemory(serverGroupForm.getMemSize());
        }
        if (serverGroupForm.getNetworkBandwidth() != null) {
            requests.setNetBandwidth(serverGroupForm.getNetworkBandwidth());
        }
        
        if (serverGroupForm.getProxyCnpCnt() != null) {
        	proxyrequests.setCpu(serverGroupForm.getCnpCnt());
        }
        if (serverGroupForm.getProxyMemSize() != null) {
        	proxyrequests.setMemory(serverGroupForm.getMemSize());
        }
        if (serverGroupForm.getProxyNetworkBandwidth() != null) {
        	proxyrequests.setNetBandwidth(serverGroupForm.getNetworkBandwidth());
        }
        
        if (serverGroupForm.getCmhaCnpCnt() != null) {
        	cmharequests.setCpu(serverGroupForm.getCnpCnt());
        }
        if (serverGroupForm.getCmhaMemSize() != null) {
        	cmharequests.setMemory(serverGroupForm.getMemSize());
        }
        if (serverGroupForm.getCmhaNetworkBandwidth() != null) {
        	cmharequests.setNetBandwidth(serverGroupForm.getNetworkBandwidth());
        }

        StorageBody storage = new MgmServerGroupResourceRequestsBody.StorageBody();
        StorageBody cmhastorage = new MgmServerGroupResourceRequestsBody.StorageBody();
        StorageBody proxystorage = new MgmServerGroupResourceRequestsBody.StorageBody();
        requests.setStorage(storage);
        cmharequests.setStorage(cmhastorage);
        proxyrequests.setStorage(proxystorage);
        
        List<VolumesBody> storageVolumes = new ArrayList<>();
        if (serverGroupForm.getDataDirSize() != null) {
            VolumesBody dataVolumes = new MgmServerGroupResourceRequestsBody.VolumesBody();
            dataVolumes.setType("data");
            dataVolumes.setCapacity(serverGroupForm.getDataDirSize());
            storageVolumes.add(dataVolumes);
        }
        if (serverGroupForm.getLogDirSize() != null) {
            VolumesBody logVolumes = new MgmServerGroupResourceRequestsBody.VolumesBody();
            logVolumes.setType("log");
            logVolumes.setCapacity(serverGroupForm.getLogDirSize());
            storageVolumes.add(logVolumes);
        }
        if (storageVolumes.size() > 0) {
            storage.setVolumes(storageVolumes.toArray(new VolumesBody[storageVolumes.size()]));
        }
        
        //cmha
        List<VolumesBody> cmhastorageVolumes = new ArrayList<>();
        if (serverGroupForm.getCmhaDataDirSize() != null) {
            VolumesBody cmhadataVolumes = new MgmServerGroupResourceRequestsBody.VolumesBody();
            cmhadataVolumes.setType("data");
            cmhadataVolumes.setCapacity(serverGroupForm.getCmhaDataDirSize());
            cmhastorageVolumes.add(cmhadataVolumes);
        }
        if (serverGroupForm.getCmhaLogDirSize() != null) {
            VolumesBody cmhalogVolumes = new MgmServerGroupResourceRequestsBody.VolumesBody();
            cmhalogVolumes.setType("log");
            cmhalogVolumes.setCapacity(serverGroupForm.getCmhaLogDirSize());
            cmhastorageVolumes.add(cmhalogVolumes);
        }
        if (cmhastorageVolumes.size() > 0) {
        	cmhastorage.setVolumes(cmhastorageVolumes.toArray(new VolumesBody[cmhastorageVolumes.size()]));
        }
        
        //proxy
        List<VolumesBody> proxystorageVolumes = new ArrayList<>();
        if (serverGroupForm.getProxyDataDirSize() != null) {
            VolumesBody proxydataVolumes = new MgmServerGroupResourceRequestsBody.VolumesBody();
            proxydataVolumes.setType("data");
            proxydataVolumes.setCapacity(serverGroupForm.getProxyDataDirSize());
            proxystorageVolumes.add(proxydataVolumes);
        }
        if (serverGroupForm.getProxyLogDirSize() != null) {
            VolumesBody proxylogVolumes = new MgmServerGroupResourceRequestsBody.VolumesBody();
            proxylogVolumes.setType("log");
            proxylogVolumes.setCapacity(serverGroupForm.getProxyLogDirSize());
            proxystorageVolumes.add(proxylogVolumes);
        }
        if (proxystorageVolumes.size() > 0) {
            proxystorage.setVolumes(proxystorageVolumes.toArray(new VolumesBody[proxystorageVolumes.size()]));
        }

        return mgmServerGroupResourceRequestsBody;
    }

    private MgmServerGroupImageBody buildScaleMgmServerGroupImageBody(ServerGroupForm serverGroupForm) {
        MgmServerGroupImageBody mgmServerGroupImageBody = new MgmServerGroupImageBody();

        com.bsg.upm.mgm.body.MgmServerGroupImageBody.SpecBody spec = new MgmServerGroupImageBody.SpecBody();
        mgmServerGroupImageBody.setSpec(spec);

        com.bsg.upm.mgm.body.MgmServerGroupImageBody.DatabaseBody database = new MgmServerGroupImageBody.DatabaseBody();
        com.bsg.upm.mgm.body.MgmServerGroupImageBody.DatabaseBody cmha = new MgmServerGroupImageBody.DatabaseBody();
        com.bsg.upm.mgm.body.MgmServerGroupImageBody.DatabaseBody proxy = new MgmServerGroupImageBody.DatabaseBody();
        
        ImageBody image = new MgmServerGroupImageBody.ImageBody();
        ImageBody cmhaimage = new MgmServerGroupImageBody.ImageBody();
        ImageBody proxyimage = new MgmServerGroupImageBody.ImageBody();
        
        if (serverGroupForm.getType() != null) {
            image.setType(serverGroupForm.getType());
        }
        if (serverGroupForm.getMajorVersion() != null) {
            image.setMajor(serverGroupForm.getMajorVersion());
        }
        if (serverGroupForm.getMinVersion() != null) {
            image.setMinor(serverGroupForm.getMinVersion());
        }
        if (serverGroupForm.getPatchVersion() != null) {
            image.setPatch(serverGroupForm.getPatchVersion());
        }
        if (serverGroupForm.getBuildVersion() != null) {
            image.setBuild(serverGroupForm.getBuildVersion());
        }
        
        if(serverGroupForm.getImageId()!=null) {
        	spec.setDatabase(database);
        	database.setImage(image);
        	image.setId(serverGroupForm.getImageId());
        }
        if(serverGroupForm.getCmhaImageId()!=null) {
        	spec.setCmha(cmha);
        	cmha.setImage(cmhaimage);
        	cmhaimage.setId(serverGroupForm.getCmhaImageId());
        }
        if(serverGroupForm.getProxyImageId()!=null) {
        	spec.setProxy(proxy);
        	proxy.setImage(proxyimage);
        	proxyimage.setId(serverGroupForm.getProxyImageId());
        }

        return mgmServerGroupImageBody;
    }

    private MgmServerGroupBody buildMgmServerGroupBodyForSave(OrderGroupDO orderGroupDO)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        MgmServerGroupBody mgmServerGroupBody = new MgmServerGroupBody();
        mgmServerGroupBody.setName(orderGroupDO.getName());
        mgmServerGroupBody.setDesc("");
        
        if(session.getAttribute("user") != null) {
        	mgmServerGroupBody.setCreatedUser(getUsername());
        }else {
        	mgmServerGroupBody.setCreatedUser(orderGroupDO.getCreator());
        }
        
        com.bsg.upm.mgm.body.MgmServerGroupBody.SpecBody specBody = new MgmServerGroupBody.SpecBody();
        mgmServerGroupBody.setSpec(specBody);
        
        /*
         * modify by yeht
         * 增加cmha、proxy
         */
        OrderDO orderDO = orderGroupDO.getOrders().get(0);
        String haMode = orderDO.getHamode();
        mgmServerGroupBody.setArch(orderDO.getArchitecture());
        
        com.bsg.upm.mgm.body.MgmServerGroupBody.DatabaseBody databaseBody = new MgmServerGroupBody.DatabaseBody();
        com.bsg.upm.mgm.body.MgmServerGroupBody.ImageBody imageBody = new MgmServerGroupBody.ImageBody();
        com.bsg.upm.mgm.body.MgmServerGroupBody.ImageBody imageBodyCmha = new MgmServerGroupBody.ImageBody();
        com.bsg.upm.mgm.body.MgmServerGroupBody.ImageBody imageBodyProxy = new MgmServerGroupBody.ImageBody();
        ServiceBody serviceBody = new MgmServerGroupBody.ServiceBody();
        ServiceBody cmhaServiceBody = new MgmServerGroupBody.ServiceBody();
        ServiceBody proxyServiceBody = new MgmServerGroupBody.ServiceBody();
        com.bsg.upm.mgm.body.MgmServerGroupBody.DatabaseBody cmhaBody = new MgmServerGroupBody.DatabaseBody();
        com.bsg.upm.mgm.body.MgmServerGroupBody.DatabaseBody proxyBody = new MgmServerGroupBody.DatabaseBody();
        
        specBody.setDatabase(databaseBody);
        databaseBody.setImage(imageBody);
        databaseBody.setServices(serviceBody);
        
        if(null != haMode && "true".equals(haMode)) {
            specBody.setCmha(cmhaBody);
            specBody.setProxy(proxyBody);
            cmhaBody.setImage(imageBodyCmha);
            proxyBody.setImage(imageBodyProxy);
            cmhaBody.setServices(cmhaServiceBody);
            proxyBody.setServices(proxyServiceBody);
        }
        
        MgmImageQuery mgmImageQuery = new MgmImageQuery();
        mgmImageQuery.setType(orderDO.getType());
        mgmImageQuery.setMajor(orderDO.getMajorVersion());
        mgmImageQuery.setMinor(orderDO.getMinorVersion());
        mgmImageQuery.setPatch(orderDO.getPatchVersion());
        mgmImageQuery.setBuild(orderDO.getBuildVersion());
        List<MgmImage> mgmImage = mgmApi.listImage(mgmImageQuery);
        
        try {
			imageBody.setId(mgmImage.get(0).getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			e.printStackTrace();
		}
       
        serviceBody.setNum(orderDO.getCnt());
        ArchBody archBody = new MgmServerGroupBody.ArchBody();
        
        //add cmha proxy
        ArchBody cmhaArchBody = new MgmServerGroupBody.ArchBody();
        ArchBody proxyArchBody = new MgmServerGroupBody.ArchBody();
        serviceBody.setArch(archBody);
        cmhaServiceBody.setArch(cmhaArchBody);
        proxyServiceBody.setArch(proxyArchBody);
//        archBody.setMode(orderDO.getArch().getShardingType());
        archBody.setMode(orderDO.getArchTypeCode());
        
        /*Integer replicas = orderDO.getArch().getMasterNodeCnt() + orderDO.getArch().getSlaveNodeCnt()
                + orderDO.getArch().getStandbyNodeCnt();*/
        if (StringUtils.isNotBlank(orderDO.getUnitCnt().toString())) {
        	archBody.setReplicas(orderDO.getUnitCnt());
        }

        List<PortBody> portBodies = new ArrayList<>();
        PortBody portBody = new MgmServerGroupBody.PortBody();
        serviceBody.setPorts(portBodies);
        portBody.setName("server");
        portBody.setPort(orderDO.getPort());
        portBodies.add(portBody);
        
        
        Gson gson = new Gson();
        Map<String, String> map = new HashMap<String, String>();
        map = gson.fromJson(orderDO.getCfgs(), map.getClass());
        String  char_set = map.get("character_set_server");
        map.remove("character_set_server");
        map.put("mysqld::character_set_server", char_set);
        serviceBody.setOptions(map);
        ConditionBody conditionBody = new MgmServerGroupBody.ConditionBody();
        serviceBody.setConditions(conditionBody);

        ClusterBody clusterBody = new MgmServerGroupBody.ClusterBody();
        conditionBody.setCluster(clusterBody);
//        String characterSet=orderDO.getCharacterSet();
       
//        optionBody.setKey(orderDO.getCharacterSet());
        //add fro cmha proxy
        if(null != haMode && "true".equals(haMode)) {
        	
            try {
				CmhaProxyDO cmhaDO = cmhaProxyDAO.getCmhaOrProxy(orderDO.getId(), "cmha").get(0);//orderDO.getCmha().get(0);
				CmhaProxyDO proxyDO = cmhaProxyDAO.getCmhaOrProxy(orderDO.getId(), "proxy").get(0);
				cmhaArchBody.setMode(cmhaDO.getMode());
				proxyArchBody.setMode(proxyDO.getMode());
				cmhaArchBody.setReplicas(cmhaDO.getReplicas());
				proxyArchBody.setReplicas(proxyDO.getReplicas());
				
				if(proxyDO.getImg_id()!=null && !"".equals(proxyDO.getImg_id())) {
					
					imageBodyProxy.setId(proxyDO.getImg_id());//latest
				}else 
					imageBodyProxy.setId("latest");
				if(cmhaDO.getImg_id()!=null && !"".equals(cmhaDO.getImg_id())) {
					imageBodyCmha.setId(cmhaDO.getImg_id());//latest
				}else
					imageBodyCmha.setId("latest");
				
				List<PortBody> cmhaportBodies = new ArrayList<>();
				List<PortBody> proxyportBodies = new ArrayList<>();
         
				PortBody cmhaportBody = new MgmServerGroupBody.PortBody();
				PortBody proxyportBody = new MgmServerGroupBody.PortBody();
         
				cmhaServiceBody.setPorts(cmhaportBodies);
				proxyServiceBody.setPorts(proxyportBodies);
				cmhaportBody.setName("server");
				cmhaportBody.setPort(cmhaDO.getPort());
				cmhaportBodies.add(cmhaportBody);
				proxyportBody.setName("server");
				proxyportBody.setPort(proxyDO.getPort());
				proxyportBodies.add(proxyportBody);
				
				ConditionBody cmhaconditionBody = new MgmServerGroupBody.ConditionBody();
				ConditionBody proxyconditionBody = new MgmServerGroupBody.ConditionBody();
         
				cmhaServiceBody.setConditions(cmhaconditionBody);
				proxyServiceBody.setConditions(proxyconditionBody);
				
				ClusterBody cmhaclusterBody = new MgmServerGroupBody.ClusterBody();
				ClusterBody proxyclusterBody = new MgmServerGroupBody.ClusterBody();
				cmhaconditionBody.setCluster(cmhaclusterBody);
				proxyconditionBody.setCluster(proxyclusterBody);
				proxyclusterBody.setHighAvailability(true);
				cmhaclusterBody.setHighAvailability(true);
				
				UnitBody cmhaunitBody = new MgmServerGroupBody.UnitBody();
				UnitBody proxyunitBody = new MgmServerGroupBody.UnitBody();
				
				cmhaunitBody.setHa( Boolean.parseBoolean(orderDO.getHamode()));
				proxyunitBody.setHa( Boolean.parseBoolean(orderDO.getHamode()));
				cmhaServiceBody.setUnits(cmhaunitBody);
				proxyServiceBody.setUnits(proxyunitBody);

				ResourceBody cmharesourceBody = new MgmServerGroupBody.ResourceBody();
				ResourceBody proxyresourceBody = new MgmServerGroupBody.ResourceBody();
				cmhaunitBody.setResources(cmharesourceBody);
				proxyunitBody.setResources(proxyresourceBody);

				RequestBody cmharequestBody = new MgmServerGroupBody.RequestBody();
				RequestBody proxyrequestBody = new MgmServerGroupBody.RequestBody();
				cmharesourceBody.setRequests(cmharequestBody);
				proxyresourceBody.setRequests(proxyrequestBody);
				cmharequestBody.setCpu(cmhaDO.getCpu());
				cmharequestBody.setMemory(cmhaDO.getMemory());
				//cmharequestBody.setNetBandwidth(orderDO.getNetworkBandwidth());
				
				proxyrequestBody.setCpu(proxyDO.getCpu());
				proxyrequestBody.setMemory(cmhaDO.getMemory());
				//proxyrequestBody.setNetBandwidth(orderDO.getNetworkBandwidth());
				
				com.bsg.upm.mgm.body.MgmServerGroupBody.StorageBody cmhastorageBody = new MgmServerGroupBody.StorageBody();
				com.bsg.upm.mgm.body.MgmServerGroupBody.StorageBody proxystorageBody = new MgmServerGroupBody.StorageBody();
				cmharequestBody.setStorage(cmhastorageBody);
				proxyrequestBody.setStorage(proxystorageBody);
				cmhastorageBody.setType(orderDO.getDataDirType());
				cmhastorageBody.setPerformance(cmhaDO.getPerformance());
				proxystorageBody.setType(orderDO.getDataDirType());
				proxystorageBody.setPerformance(proxyDO.getPerformance());

				List<VolumeBody> cmhavolumeBodies = new ArrayList<>();
				List<VolumeBody> proxyvolumeBodies = new ArrayList<>();
				cmhastorageBody.setVolumes(cmhavolumeBodies);
				proxystorageBody.setVolumes(proxyvolumeBodies);
				VolumeBody cmhadataVolumeBody = new MgmServerGroupBody.VolumeBody();
				VolumeBody proxydataVolumeBody = new MgmServerGroupBody.VolumeBody();
				cmhadataVolumeBody.setType("data");
				cmhadataVolumeBody.setCapacity(cmhaDO.getData_capacity());
				proxydataVolumeBody.setType("data");
				proxydataVolumeBody.setCapacity(proxyDO.getData_capacity());
				cmhavolumeBodies.add(cmhadataVolumeBody);
				proxyvolumeBodies.add(proxydataVolumeBody);
				VolumeBody cmhalogVolumeBody = new MgmServerGroupBody.VolumeBody();
				VolumeBody proxylogVolumeBody = new MgmServerGroupBody.VolumeBody();
				cmhalogVolumeBody.setType("log");
				cmhalogVolumeBody.setCapacity(cmhaDO.getLog_capacity());
				proxylogVolumeBody.setType("log");
				proxylogVolumeBody.setCapacity(proxyDO.getLog_capacity());
				cmhavolumeBodies.add(cmhalogVolumeBody);
				proxyvolumeBodies.add(proxylogVolumeBody);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        MgmClusterQuery mgmClusterQuery = new MgmClusterQuery();
        mgmClusterQuery.setSiteId(orderGroupDO.getSiteId());
        mgmClusterQuery.setZone(orderGroupDO.getAreaCode());
        List<MgmCluster> mgmClusters = mgmApi.listCluster(mgmClusterQuery);
        List<String> clusterIds = new ArrayList<>();
        for (MgmCluster mgmCluster : mgmClusters) {
            clusterIds.add(mgmCluster.getId());
        }
        clusterBody.setCandidatesId(clusterIds);
        clusterBody.setHighAvailability(orderDO.getClusterHA());

        HostBody hostBody = new MgmServerGroupBody.HostBody();
        conditionBody.setHost(hostBody);
        hostBody.setHighAvailability(orderDO.getHostHA());

        StorageRemoteBody storageRemoteBody = new MgmServerGroupBody.StorageRemoteBody();
        conditionBody.setStorageRemote(storageRemoteBody);
        storageRemoteBody.setHighAvailability(orderDO.getStorageHA());

        NetworkBody networkBody = new MgmServerGroupBody.NetworkBody();
        conditionBody.setNetwork(networkBody);
        networkBody.setHighAvailability(orderDO.getNetworkHA());

        BackupBody backupBody = new MgmServerGroupBody.BackupBody();
        serviceBody.setBackup(backupBody);

        BackupStorageBody backupStorageBody = new MgmServerGroupBody.BackupStorageBody();
        backupBody.setStorage(backupStorageBody);

        UnitBody unitBody = new MgmServerGroupBody.UnitBody();
        
        unitBody.setHa( Boolean.parseBoolean(orderDO.getHamode()));
        serviceBody.setUnits(unitBody);

        ResourceBody resourceBody = new MgmServerGroupBody.ResourceBody();
        unitBody.setResources(resourceBody);

        RequestBody requestBody = new MgmServerGroupBody.RequestBody();
        resourceBody.setRequests(requestBody);
        requestBody.setCpu(orderDO.getCpuCnt());
        requestBody.setMemory(orderDO.getMemSize());
        requestBody.setNetBandwidth(orderDO.getNetworkBandwidth());
        com.bsg.upm.mgm.body.MgmServerGroupBody.StorageBody storageBody = new MgmServerGroupBody.StorageBody();
        requestBody.setStorage(storageBody);
        storageBody.setType(orderDO.getDataDirType());
        storageBody.setPerformance(orderDO.getDataDirPerformance());

        List<VolumeBody> volumeBodies = new ArrayList<>();
        storageBody.setVolumes(volumeBodies);
        VolumeBody dataVolumeBody = new MgmServerGroupBody.VolumeBody();
        dataVolumeBody.setType("data");
        dataVolumeBody.setCapacity(orderDO.getDataDirSize());
        volumeBodies.add(dataVolumeBody);
        VolumeBody logVolumeBody = new MgmServerGroupBody.VolumeBody();
        logVolumeBody.setType("log");
        logVolumeBody.setCapacity(orderDO.getLogDirSize());
        volumeBodies.add(logVolumeBody);
        
        
        
        

        return mgmServerGroupBody;
    }
    
    private MgmServerGroupBody reBuildMgmServerGroupBodyForSave(OrderGroupDO orderGroupDO)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        MgmServerGroupBody mgmServerGroupBody = new MgmServerGroupBody();
        mgmServerGroupBody.setName(orderGroupDO.getName());
        mgmServerGroupBody.setDesc("");
    	mgmServerGroupBody.setCreatedUser(orderGroupDO.getCreator());
        OrderDO orderDO = orderGroupDO.getOrders().get(0);
        com.bsg.upm.mgm.body.MgmServerGroupBody.SpecBody specBody = new MgmServerGroupBody.SpecBody();
        mgmServerGroupBody.setSpec(specBody);
        com.bsg.upm.mgm.body.MgmServerGroupBody.DatabaseBody databaseBody = new MgmServerGroupBody.DatabaseBody();
        specBody.setDatabase(databaseBody);

        com.bsg.upm.mgm.body.MgmServerGroupBody.ImageBody imageBody = new MgmServerGroupBody.ImageBody();
        databaseBody.setImage(imageBody);
        MgmImageQuery mgmImageQuery = new MgmImageQuery();
        mgmImageQuery.setType(orderDO.getType());
        mgmImageQuery.setMajor(orderDO.getMajorVersion());
        mgmImageQuery.setMinor(orderDO.getMinorVersion());
        mgmImageQuery.setPatch(orderDO.getPatchVersion());
        mgmImageQuery.setBuild(orderDO.getBuildVersion());
        List<MgmImage> mgmImage = mgmApi.listImage(mgmImageQuery);
        imageBody.setId(mgmImage.get(0).getId());

        ServiceBody serviceBody = new MgmServerGroupBody.ServiceBody();
        databaseBody.setServices(serviceBody);
        serviceBody.setNum(orderDO.getCnt());
        ArchBody archBody = new MgmServerGroupBody.ArchBody();
        serviceBody.setArch(archBody);
//        archBody.setMode(orderDO.getArch().getShardingType());
        archBody.setMode(orderDO.getArchTypeCode());
        /*Integer replicas = orderDO.getArch().getMasterNodeCnt() + orderDO.getArch().getSlaveNodeCnt()
                + orderDO.getArch().getStandbyNodeCnt();*/
        if (StringUtils.isNotBlank(orderDO.getUnitCnt().toString())) {
        	archBody.setReplicas(orderDO.getUnitCnt());
        }

        List<PortBody> portBodies = new ArrayList<>();
        PortBody portBody = new MgmServerGroupBody.PortBody();
        serviceBody.setPorts(portBodies);
        portBody.setName("server");
        portBody.setPort(orderDO.getPort());
        portBodies.add(portBody);
        Gson gson = new Gson();
        Map<String, String> map = new HashMap<String, String>();
        map = gson.fromJson(orderDO.getCfgs(), map.getClass());
        serviceBody.setOptions(map);
//        String characterSet=orderDO.getCharacterSet();
       
//        optionBody.setKey(orderDO.getCharacterSet());

        ConditionBody conditionBody = new MgmServerGroupBody.ConditionBody();
        serviceBody.setConditions(conditionBody);

        ClusterBody clusterBody = new MgmServerGroupBody.ClusterBody();
        conditionBody.setCluster(clusterBody);
        MgmClusterQuery mgmClusterQuery = new MgmClusterQuery();
        mgmClusterQuery.setSiteId(orderGroupDO.getSiteId());
        mgmClusterQuery.setZone(orderGroupDO.getAreaCode());
        List<MgmCluster> mgmClusters = mgmApi.listCluster(mgmClusterQuery);
        List<String> clusterIds = new ArrayList<>();
        for (MgmCluster mgmCluster : mgmClusters) {
            clusterIds.add(mgmCluster.getId());
        }
        clusterBody.setCandidatesId(clusterIds);
        clusterBody.setHighAvailability(orderDO.getClusterHA());

        HostBody hostBody = new MgmServerGroupBody.HostBody();
        conditionBody.setHost(hostBody);
        hostBody.setHighAvailability(orderDO.getHostHA());

        StorageRemoteBody storageRemoteBody = new MgmServerGroupBody.StorageRemoteBody();
        conditionBody.setStorageRemote(storageRemoteBody);
        storageRemoteBody.setHighAvailability(orderDO.getStorageHA());

        NetworkBody networkBody = new MgmServerGroupBody.NetworkBody();
        conditionBody.setNetwork(networkBody);
        networkBody.setHighAvailability(orderDO.getNetworkHA());

        BackupBody backupBody = new MgmServerGroupBody.BackupBody();
        serviceBody.setBackup(backupBody);

        BackupStorageBody backupStorageBody = new MgmServerGroupBody.BackupStorageBody();
        backupBody.setStorage(backupStorageBody);

        UnitBody unitBody = new MgmServerGroupBody.UnitBody();
        serviceBody.setUnits(unitBody);

        ResourceBody resourceBody = new MgmServerGroupBody.ResourceBody();
        unitBody.setResources(resourceBody);

        RequestBody requestBody = new MgmServerGroupBody.RequestBody();
        resourceBody.setRequests(requestBody);
        requestBody.setCpu(orderDO.getCpuCnt());
        requestBody.setMemory(orderDO.getMemSize());
        requestBody.setNetBandwidth(orderDO.getNetworkBandwidth());
        com.bsg.upm.mgm.body.MgmServerGroupBody.StorageBody storageBody = new MgmServerGroupBody.StorageBody();
        requestBody.setStorage(storageBody);
        storageBody.setType(orderDO.getDataDirType());
        storageBody.setPerformance(orderDO.getDataDirPerformance());

        List<VolumeBody> volumeBodies = new ArrayList<>();
        storageBody.setVolumes(volumeBodies);
        VolumeBody dataVolumeBody = new MgmServerGroupBody.VolumeBody();
        dataVolumeBody.setType("data");
        dataVolumeBody.setCapacity(orderDO.getDataDirSize());
        volumeBodies.add(dataVolumeBody);
        VolumeBody logVolumeBody = new MgmServerGroupBody.VolumeBody();
        logVolumeBody.setType("log");
        logVolumeBody.setCapacity(orderDO.getLogDirSize());
        volumeBodies.add(logVolumeBody);

        return mgmServerGroupBody;
    }
    
    private MgmServerGroupBackupBody buildMgmServerGroupBackupBody(ServerGroupForm serverGroupForm) {
        MgmServerGroupBackupBody mgmServerGroupBackupBody = new MgmServerGroupBackupBody();
        
        // 服务组备份是不需要unit_id
//        mgmServerGroupBackupBody.setBackupStorageType(serverGroupForm.getStorageType());
        mgmServerGroupBackupBody.setOnce(true);
        mgmServerGroupBackupBody.setEnabled(true);
        mgmServerGroupBackupBody.setName("server");
        mgmServerGroupBackupBody.setRole("master");
        mgmServerGroupBackupBody.setSchedule(serverGroupForm.getSchedule());
        mgmServerGroupBackupBody.setType(serverGroupForm.getType());
        mgmServerGroupBackupBody.setRetention(serverGroupForm.getRetention());
        mgmServerGroupBackupBody.setStorage("nfs");
        mgmServerGroupBackupBody.setCreatedUser("admin");
        mgmServerGroupBackupBody.setEndpointId(serverGroupForm.getEndpointId());

        return mgmServerGroupBackupBody;
    }
    
    public static <T> T parseMap2Object(Map<String, Object> paramMap, Class<T> cls) {
		return JSONObject.parseObject(JSONObject.toJSONString(paramMap), cls);
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
                String logPrefix = "服务创建"+orderGroupDO.getName();
                logger.info("{}: start build concurrent subtask queues。", logPrefix);
                try {
                    String flag=mgmApi.listTask(mgmSaveServerGroup);
                    if (flag.equals("5")) {
                        orderGroupDO.setStatus(DictConsts.ORDER_STATUS_SUCCESS);
                        orderGroupDAO.updateStatusAndMsg(orderGroupDO);
                      //add by yeht 创建完app后，并发创建用户、schema、备份
                        CreateUserTask createUserTask = new CreateUserTask(orderGroupDO);
                        CreateBackupTask createBackupTask = new CreateBackupTask(orderGroupDO);
                        CreateSchemaTask createschemaTask = new CreateSchemaTask(orderGroupDO);
                        Thread thread1 = new Thread(createUserTask);
                        Thread thread2 = new Thread(createBackupTask);
                        Thread thread3 = new Thread(createschemaTask);
                        thread1.start();
                        thread2.start();
                        thread3.start();
                        
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
    
    /*
     * add by yeht
     * CreateUserTask 创建数据库用户
     */
    public class CreateUserTask implements Runnable {
        public OrderGroupDO orderGroupDO;
        public int i=0;
        public CreateUserTask(OrderGroupDO orderGroupDO) {
            this.orderGroupDO=orderGroupDO;
        }
		@Override
        public void run() {
        	String serverGroupId = orderGroupDO.getRelateAppId();
        	
        	OrderDO orderDO = orderGroupDO.getOrders().get(0);
			//serverGroupUserForm.setPassword(orderDO.getUsers().get(0));
        	//serverGroupUserForm.setUsername(orderDO.getUser().getUsername());
        	
        	String oderId = orderDO.getId();
        	List <DbUserDO> userDOs = dbUserDAO.getDbUsers(oderId,"user");
        	List <DbUserDO> schemaDOs = dbUserDAO.getDbUsers(oderId,"schema");
        	if(userDOs == null || userDOs.isEmpty()) {
        		return;
        	}else {
        		PrivilegeForm[] db_privileges = new PrivilegeForm[userDOs.size()];
            	int index = 0;
            	for (DbUserDO userDO:userDOs) {
            		ServerGroupUserForm serverGroupUserForm = new ServerGroupUserForm();
            		serverGroupUserForm.setPassword(userDO.getUserpwd());
            		serverGroupUserForm.setWhiteIps("%");
                	serverGroupUserForm.setUsername(userDO.getUsername());
                	db_privileges[index] = new PrivilegeForm();
            		db_privileges[index].setDbName(schemaDOs.get(index).getDbname());
            		
            		String[] privileges = {"SELECT","UPDATE", "INSERT", "DELETE", "CREATE","DROP","ALTER",
            						"INDEX","EXECUTE","TRIGGER","CREATE VIEW","SHOW VIEW","REFERENCES","EVENT",
            						"CREATE TEMPORARY TABLES","LOCK TABLES","CREATE ROUTINE","ALTER ROUTINE"};
        			db_privileges[0].setPrivileges(privileges);
        			serverGroupUserForm.setDb_privileges(db_privileges);
        			logger.info("{}: 创建用户user。", serverGroupUserForm.toString());
                	
                        try {
                        	//serverGroupId, serverGroupUserForm
                        	serverGroupUserService.save(serverGroupId, serverGroupUserForm);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
            	}
        	}
        }

    }
    
    /*
     * add by yeht
     * CreateSchemaTask 创建数据库schema
     */
    public class CreateSchemaTask implements Runnable {
        public OrderGroupDO orderGroupDO;
        public int i=0;
        public CreateSchemaTask(OrderGroupDO orderGroupDO) {
            this.orderGroupDO=orderGroupDO;
        }
        @Override
        public void run() {
            // TODO Auto-generated method stub
                String serverGroupId=orderGroupDO.getRelateAppId();
                DatabaseForm databaseForm = new DatabaseForm();
            	OrderDO orderDO = orderGroupDO.getOrders().get(0);
            	String oderId = orderDO.getId();
            	List <DbUserDO> schemaDOs = dbUserDAO.getDbUsers(oderId,"schema");
            	if(schemaDOs == null || schemaDOs.isEmpty()) {
            		return;
            	}else {
            		for(DbUserDO schemaDO :schemaDOs) {
                		databaseForm.setCharacterSet("utf8mb4");
                    	databaseForm.setName(schemaDO.getDbname());
                        String logPrefix = "服务创建"+orderGroupDO.getName();
                        logger.info("{}: start build concurrent subtask queues。", logPrefix);
                        logger.info("{}: 创建数据库schema。", databaseForm.toString());
                        try {
                        	 //serverGroupId, databaseForm
                        	 databaseService.save(serverGroupId, databaseForm);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                	}
            	}
        }

    }
    
    /*
     * add by yeht
     * CreateBackupTask 创建备份策略
     */
    public class CreateBackupTask implements Runnable {
        public OrderGroupDO orderGroupDO;
        public int i=0;
        public CreateBackupTask(OrderGroupDO orderGroupDO) {
            this.orderGroupDO=orderGroupDO;
        }
        @Override
        public void run() {
            // TODO Auto-generated method stub
	        	String serverGroupId=orderGroupDO.getRelateAppId();
	        	ServerGroupForm serverGroupForm = new ServerGroupForm();
	        	OrderDO orderDO = orderGroupDO.getOrders().get(0);
	        	String schedule = orderDO.getSchedule();
	        	if(schedule==null ||"".equals(schedule)) {
	        		return;
	        	}else {
	        		//serverGroupForm.setType(serverGroupForm.getType());
		        	serverGroupForm.setSchedule(schedule);//
		        	serverGroupForm.setRetention(2);
		        	serverGroupForm.setType("full");
		            String logPrefix = "服务创建"+orderGroupDO.getName();
		            logger.info("{}: start build concurrent subtask queues。", logPrefix);
	                try {
	                	//serverGroupId, serverGroupForm
	                	backup(serverGroupId, serverGroupForm);
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
    public class ReDeleteTask implements Runnable {
   	 public OrderGroupDO copyOrderGroup;
        public volatile boolean isDeleted = false;
        public int i=0;
        public  ReDeleteTask(OrderGroupDO copyOrderGroup) {
            this.copyOrderGroup=copyOrderGroup;
        }
        @Override
        public void run() {
            // TODO Auto-generated method stub
       	 copyOrderGroup.setActionType(DictConsts.ORDER_CREATE_TYPE_DELETE);
            String logPrefix = "服务创建失败后删除"+copyOrderGroup.getName();
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
				                    	logger.info("{}: delete info::", mgmServerGroupp.toString());
				                    	deletedStatus=true;
				                    	logger.info("{}: 创建失败后删除失败1", isDeleted);
				                    	isDeleted=true;
				                    }else {
				                    	isDeleted=false;
				                    }
				                }
				                break;
	   			            }else {
	   			            	isDeleted=true;
	   			            	logger.info("{}: 创建失败后删除成功1", isDeleted);
							}
	   			        }
				 }
                    if (isDeleted==true) {
                    	if(deletedStatus==true) {
                   		 	copyOrderGroup.setStatus(DictConsts.ORDER_STATUS_FAILURE);
                         	orderGroupDAO.updateStatusAndMsg(copyOrderGroup);
                         	logger.info("{}: 创建失败后删除失败2", isDeleted);
	                   	 }else {
                         	orderGroupDAO.updateStatusAndMsg(copyOrderGroup);
                         	logger.info("{}: 创建失败后删除成功2", isDeleted);
                         	servGroupDAO.remove(copyOrderGroup.getRelateAppId());
                         	reCreate(copyOrderGroup);
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
                        	logger.info("{}: redelete overtime。", isDeleted);
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
    
    public class DeleteTask implements Runnable{
    	 public OrderGroupDO copyOrderGroup;
         public volatile boolean isDeleted = false;
         public int i=0;
         public  DeleteTask(OrderGroupDO copyOrderGroup) {
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
                          	 servGroupDAO.remove(copyOrderGroup.getRelateAppId());
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

	public Result getTopology(String serverGroupId, String subscription_id) {
        try {
        	if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
            MgmServerGroupDetailQuery mgmServerGroupDetailQuery = new MgmServerGroupDetailQuery();
            //mgmServerGroupDetailQuery.setId(serverGroupId);
            //add for 订阅号
            mgmServerGroupDetailQuery.setSubscription_id(subscription_id);
            MgmTopology mgmTopology = null;
            mgmTopology = mgmApi.getTopology(serverGroupId,mgmServerGroupDetailQuery);

            return Result.success(mgmTopology);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
	}

	public Result updateReplicationMode(String serverGroupId, String subscription_id,MgmBaseForm mgmBaseForm) throws Exception {
		try {
        	if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
	
			MgmServerGroupDetailQuery mgmServerGroupDetailQuery = new MgmServerGroupDetailQuery();
	        //mgmServerGroupDetailQuery.setId(serverGroupId);
	        //add for 订阅号
	        mgmServerGroupDetailQuery.setSubscription_id(subscription_id);
	        MgmBaseBody mgmBaseBody = new MgmBaseBody();
	        mgmBaseBody.setUnit_id(mgmBaseForm.getUnit_id());
	        mgmApi.updateReplicationMode(serverGroupId,mgmServerGroupDetailQuery,mgmBaseBody);
		} catch (Exception e) {
            throw new ServiceException(e);
        }

        return Result.success();
    }

	public Result resetMaster(String serverGroupId, String subscription_id, MgmBaseForm mgmBaseForm) {
		try {
        	if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
	
			MgmServerGroupDetailQuery mgmServerGroupDetailQuery = new MgmServerGroupDetailQuery();
	        //mgmServerGroupDetailQuery.setId(serverGroupId);
	        //add for 订阅号
	        mgmServerGroupDetailQuery.setSubscription_id(subscription_id);
	        MgmBaseBody mgmBaseBody = new MgmBaseBody();
	        mgmBaseBody.setUnit_id(mgmBaseForm.getUnit_id());
	        mgmApi.setSource(serverGroupId,mgmServerGroupDetailQuery,mgmBaseBody);
		} catch (Exception e) {
            throw new ServiceException(e);
        }
		
		return Result.success();
	}
	
	public Result maintenance(String serverGroupId, String subscription_id, MgmBaseForm mgmBaseForm) {
		try {
			if(nullCheck(serverGroupId)) {
				return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
			}
			
			MgmServerGroupDetailQuery mgmServerGroupDetailQuery = new MgmServerGroupDetailQuery();
			//mgmServerGroupDetailQuery.setId(serverGroupId);
			//add for 订阅号
			mgmServerGroupDetailQuery.setSubscription_id(subscription_id);
			MgmBaseBody mgmBaseBody = new MgmBaseBody();
			mgmBaseBody.setUnit_id(mgmBaseForm.getUnit_id());
			mgmBaseBody.setMaintenance(mgmBaseForm.getMaintenance());
			mgmApi.maintenance(serverGroupId,mgmServerGroupDetailQuery,mgmBaseBody);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		return Result.success();
	}
	
}
