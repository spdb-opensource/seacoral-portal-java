package com.bsg.upm.service;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.ServerGroupUnitCheck;
import com.bsg.upm.check.resultenum.HostChkRsEnum;
import com.bsg.upm.check.resultenum.NetworkingChkRsEnum;
import com.bsg.upm.check.resultenum.ServerGroupChkRsEnum;
import com.bsg.upm.check.resultenum.ServerGroupUnitChkRsEnum;
import com.bsg.upm.exception.ServiceException;
import com.bsg.upm.form.ServerGroupUnitForm;
import com.bsg.upm.mgm.api.MgmApi;
import com.bsg.upm.mgm.body.MgmServerGroupBackupBody;
import com.bsg.upm.mgm.body.MgmServerGroupStateBody;
import com.bsg.upm.mgm.body.MgmServerGroupUnitBackupBody;
import com.bsg.upm.mgm.body.MgmServerGroupUnitRebulidBody;
import com.bsg.upm.mgm.body.MgmServerGroupUnitRoleChangeBody;
import com.bsg.upm.mgm.body.MgmServerGroupUnitRoleChangeSlaveBody;
import com.bsg.upm.param.GetUnitMonitorParam;
import com.bsg.upm.param.GetUnitMonitorStringParam;
import com.bsg.upm.param.HostMonitorCancelParam;
import com.bsg.upm.param.HostMonitorParam;
import com.bsg.upm.param.HostMonitorQueryParam;
import com.bsg.upm.param.HostMonitorRegisParam;
import com.bsg.upm.param.MgmGetUnitMonitorParam;
import com.bsg.upm.param.MgmGetUnitMonitorStringParam;
import com.bsg.upm.param.ServeUnitRegisParam;
import com.bsg.upm.query.UnitRebulidParam;
import com.bsg.upm.query.UnitRoleChangeParam;
import com.bsg.upm.query.UnitRoleChangeParam.UnitRole;

/**
 * 服务组单元service
 * 
 * @author swn
 *
 */
@Service
public class ServerGroupUnitService extends BaseService {

	@Autowired
	private MgmApi mgmApi;

	@Autowired
	private ServerGroupUnitCheck serverGroupUnitCheck;
	
	@Transactional(rollbackFor = ServiceException.class)
    public Result enabled(String serverGroupId, String unitId, boolean enabled) throws ServiceException {
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
            mgmApi.stateServerGroupUnit(serverGroupId, unitId, mgmServerGroupStateBody);

            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
	
	@Transactional(rollbackFor = ServiceException.class)
    public Result backup(String serverGroupId, String unitId, ServerGroupUnitForm serverGroupUnitForm) throws ServiceException {
        try {
        	if(nullCheck(serverGroupId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_ID_MUST_NOT_BE_BLANK));
        	}
        	if(nullCheck(unitId)) {
        		return Result.failure(CheckResult.failure(ServerGroupChkRsEnum.ADD_ILLEGAL_UNIT_ID_MUST_NOT_BE_BLANK));
        	}
            CheckResult checkResult = serverGroupUnitCheck.checkBackup(serverGroupId, unitId, serverGroupUnitForm);
            if (checkResult.getCode() != NetworkingChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            
            MgmServerGroupUnitBackupBody mgmServerGroupUnitBackupBody = buildMgmServerGroupUnitBackupBody(serverGroupId,unitId, serverGroupUnitForm);
            
            mgmApi.backupServerGroupUnit(serverGroupId, mgmServerGroupUnitBackupBody);
			
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
	
	@Transactional(rollbackFor = ServiceException.class)
    public Result rebulid(UnitRebulidParam unitRebulidParam) throws ServiceException {
        try {

            CheckResult checkResult = serverGroupUnitCheck.checkRebuild(unitRebulidParam);
            if (checkResult.getCode() != NetworkingChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            MgmServerGroupUnitRebulidBody mgmServerGroupUnitRebulidBody=new MgmServerGroupUnitRebulidBody();
            if(unitRebulidParam.getHostId().equals("")||unitRebulidParam.getHostId()==null||unitRebulidParam.getHostId().equals("000")) {
            	
            }else {
            	mgmServerGroupUnitRebulidBody = buildMgmServerGroupUnitRebulidBody(unitRebulidParam.getHostId());
            }
            
            mgmApi.rebulidServerGroupUnit(unitRebulidParam.getAppId(), unitRebulidParam.getUnitId(),mgmServerGroupUnitRebulidBody);
			
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
	
	@Transactional(rollbackFor = ServiceException.class)
    public Result roleChange(UnitRoleChangeParam unitRoleChangeParam) throws ServiceException {
        try {
//            CheckResult checkResult = serverGroupUnitCheck.checkRebuild(unitRebulidParam);
//            if (checkResult.getCode() != NetworkingChkRsEnum.SUCCESS.getCode()) {
//                logger.error(loginfo(checkResult));
//                return Result.failure(checkResult);
//            }
        	MgmServerGroupUnitRoleChangeBody mgmServerGroupUnitRoleChangeBody=new MgmServerGroupUnitRoleChangeBody();
        	mgmServerGroupUnitRoleChangeBody=buildMgmServerGroupUnitRoleChangeBody(unitRoleChangeParam.getUnits());
            mgmApi.roleChangeServerGroupUnit(unitRoleChangeParam.getAppId(), mgmServerGroupUnitRoleChangeBody);
			
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
	
	@Transactional(rollbackFor = ServiceException.class)
    public Result roleChangeSlave(String slaveId,UnitRoleChangeParam unitRoleChangeParam) throws ServiceException {
        try {
//            CheckResult checkResult = serverGroupUnitCheck.checkRebuild(unitRebulidParam);
//            if (checkResult.getCode() != NetworkingChkRsEnum.SUCCESS.getCode()) {
//                logger.error(loginfo(checkResult));
//                return Result.failure(checkResult);
//            }
        	if(nullCheck(slaveId)) {
        		return Result.failure(CheckResult.failure(ServerGroupUnitChkRsEnum.MONITORREGISTER_ILLEGAL_ID_MUST_NOT_BE_EMPTY));
        	}
        	MgmServerGroupUnitRoleChangeSlaveBody mgmServerGroupUnitRoleChangeBody=new MgmServerGroupUnitRoleChangeSlaveBody();
        	mgmServerGroupUnitRoleChangeBody=buildMgmServerGroupUnitRoleChangeSlaveBody(unitRoleChangeParam.getUnits());
            mgmApi.roleChangeSlaveServerGroupUnit(unitRoleChangeParam.getAppId(),slaveId, mgmServerGroupUnitRoleChangeBody);
			
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

	@Transactional(rollbackFor = ServiceException.class)
    public Result migrate(UnitRebulidParam unitRebulidParam) throws ServiceException {
        try {

            CheckResult checkResult = serverGroupUnitCheck.checkRebuild(unitRebulidParam);
            if (checkResult.getCode() != NetworkingChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
            MgmServerGroupUnitRebulidBody mgmServerGroupUnitRebulidBody=new MgmServerGroupUnitRebulidBody();
            if(unitRebulidParam.getHostId().equals("")||unitRebulidParam.getHostId()==null||unitRebulidParam.getHostId().equals("000")) {
            	
            }else {
            	mgmServerGroupUnitRebulidBody = buildMgmServerGroupUnitRebulidBody(unitRebulidParam.getHostId());
            }
            
            mgmApi.migrateServerGroupUnit(unitRebulidParam.getAppId(), unitRebulidParam.getUnitId(),mgmServerGroupUnitRebulidBody);
			
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
	
	private MgmServerGroupUnitBackupBody buildMgmServerGroupUnitBackupBody(String appId,String unitId, ServerGroupUnitForm serverGroupUnitForm) {
		if (serverGroupUnitForm == null) {
			return null;
		}
		
		MgmServerGroupUnitBackupBody mgmServerGroupUnitBackupBody = new MgmServerGroupUnitBackupBody();

		mgmServerGroupUnitBackupBody.setAppId(appId);
		mgmServerGroupUnitBackupBody.setUnitId(unitId);
//		mgmServerGroupUnitBackupBody.setBackupStorageType(serverGroupUnitForm.getStorageType());
		mgmServerGroupUnitBackupBody.setType(serverGroupUnitForm.getType());
		mgmServerGroupUnitBackupBody.setRetention(serverGroupUnitForm.getRetention());
		mgmServerGroupUnitBackupBody.setCreatedUser(getUsername());
		Boolean once = serverGroupUnitForm.getOnce();
		if(once == null) {
			once = true;
		}
		mgmServerGroupUnitBackupBody.setOnce(once);
		mgmServerGroupUnitBackupBody.setSchedule(serverGroupUnitForm.getSchedule());
		mgmServerGroupUnitBackupBody.setStorage(serverGroupUnitForm.getStorage());
		mgmServerGroupUnitBackupBody.setName(serverGroupUnitForm.getName());
		mgmServerGroupUnitBackupBody.setDesc(serverGroupUnitForm.getDesc());
		mgmServerGroupUnitBackupBody.setEndpointId(serverGroupUnitForm.getEndpointId());

		return mgmServerGroupUnitBackupBody;
	}
	
	private MgmServerGroupUnitRebulidBody buildMgmServerGroupUnitRebulidBody(String hostId) {
		MgmServerGroupUnitRebulidBody mgmServerGroupUnitBackupBody=new MgmServerGroupUnitRebulidBody();
		mgmServerGroupUnitBackupBody.setNode(hostId);
		return mgmServerGroupUnitBackupBody;
	}
	
	private MgmServerGroupUnitRoleChangeBody buildMgmServerGroupUnitRoleChangeBody(List<UnitRole> units) {
		MgmServerGroupUnitRoleChangeBody mgmServerGroupUnitBackupBody=new MgmServerGroupUnitRoleChangeBody();
		mgmServerGroupUnitBackupBody.setUnits(units);;
		return mgmServerGroupUnitBackupBody;
	}
	private MgmServerGroupUnitRoleChangeSlaveBody buildMgmServerGroupUnitRoleChangeSlaveBody(List<UnitRole> units) {
		MgmServerGroupUnitRoleChangeSlaveBody mgmServerGroupUnitBackupBody=new MgmServerGroupUnitRoleChangeSlaveBody();
//		mgmServerGroupUnitBackupBody.setUnits(units);;
		UnitRole role=units.get(0);
		String masterId=role.getId();
		String roleName=role.getRole();
		mgmServerGroupUnitBackupBody.setMasetrId(masterId);
		mgmServerGroupUnitBackupBody.setRole(roleName);
		return mgmServerGroupUnitBackupBody;
	}
	
	public Result getUnitMonitor(String unitId) throws ServiceException {
        try {
        	if(nullCheck(unitId)) {
        		return Result.failure(CheckResult.failure(ServerGroupUnitChkRsEnum.MONITORREGISTER_ILLEGAL_ID_MUST_NOT_BE_EMPTY));
        	}
            mgmApi.getUnitMonitor(unitId);
            return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
	
	@Transactional(rollbackFor = ServiceException.class)
    public Result monitorRegister(ServeUnitRegisParam serveUnitRegisParam) throws ServiceException {
        try {
        	CheckResult checkResult = serverGroupUnitCheck.checkUnitMonitor(serveUnitRegisParam);
            if (checkResult.getCode() != NetworkingChkRsEnum.SUCCESS.getCode()) {
                logger.error(loginfo(checkResult));
                return Result.failure(checkResult);
            }
             mgmApi.unitMonitorRegister(serveUnitRegisParam);
             return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
	
	@Transactional(rollbackFor = ServiceException.class)
    public Result monitorCancel(String unitId) throws ServiceException {
        try {
        	if(nullCheck(unitId)) {
        		return Result.failure(CheckResult.failure(ServerGroupUnitChkRsEnum.MONITORREGISTER_ILLEGAL_ID_MUST_NOT_BE_EMPTY));
        	}
		     mgmApi.unitMonitorCancel(unitId);
		     return Result.success();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
	
	public Result getUnitMonitorByInstanceName(String instanceName,String relateCode,String description,HostMonitorQueryParam hostMonitorQueryParam) throws ServiceException {
        try {
        	List<GetUnitMonitorParam> hostMonitorParams=new ArrayList<>();
        	if(nullCheck(instanceName)) {
        		return Result.failure(CheckResult.failure(ServerGroupUnitChkRsEnum.MONITORREGISTER_ILLEGAL_NAME_MUST_NOT_BE_EMPTY));
        	}else if(hostMonitorQueryParam==null) {
        		
        		return Result.failure(CheckResult.failure(HostChkRsEnum.MONITOR_ILLEGAL_STARTTIMEORENDTIME_MUST_NOT_BE_EMPTY));
        	}else if(nullCheck(relateCode)) {
        		return Result.failure(CheckResult.failure(ServerGroupUnitChkRsEnum.MONITORREGISTER_ILLEGAL_CODE_MUST_NOT_BE_EMPTY));
        	}
        	String[] relateCodeList=relateCode.split(";");
        	String[] descriptionList=description.split(";");
        	int ind=0;
        	for(String relatecode:relateCodeList) {
        		MgmGetUnitMonitorParam mgmGetUnitMonitorParam=mgmApi.getUnitMonitorByInstanceName(instanceName,relatecode,hostMonitorQueryParam);
        		GetUnitMonitorParam hostMonitorParam=buildMgmGetUnitMonitorParamRebulidBody(mgmGetUnitMonitorParam);
        		hostMonitorParam.setDescription(descriptionList[ind]);
        		hostMonitorParam.setMetric(relateCode);
        		hostMonitorParams.add(hostMonitorParam);
        		ind++;
        	}
        	
            return Result.success(hostMonitorParams);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
	
	public Result getUnitMonitorByInstanceNameString(String instanceName,String relateCode,String description,HostMonitorQueryParam hostMonitorQueryParam) throws ServiceException {
        try {
        	List<GetUnitMonitorStringParam> hostMonitorParams=new ArrayList<>();
        	if(nullCheck(instanceName)) {
        		return Result.failure(CheckResult.failure(ServerGroupUnitChkRsEnum.MONITORREGISTER_ILLEGAL_NAME_MUST_NOT_BE_EMPTY));
        	}else if(hostMonitorQueryParam==null) {
        		
        		return Result.failure(CheckResult.failure(HostChkRsEnum.MONITOR_ILLEGAL_STARTTIMEORENDTIME_MUST_NOT_BE_EMPTY));
        	}else if(nullCheck(relateCode)) {
        		return Result.failure(CheckResult.failure(ServerGroupUnitChkRsEnum.MONITORREGISTER_ILLEGAL_CODE_MUST_NOT_BE_EMPTY));
        	}
        	String[] relateCodeList=relateCode.split(";");
        	String[] descriptionList=description.split(";");
        	int ind=0;
        	for(String relatecode:relateCodeList) {
        		MgmGetUnitMonitorStringParam mgmGetUnitMonitorParam=mgmApi.getUnitMonitorByInstanceNameString(instanceName,relatecode,hostMonitorQueryParam);
        		GetUnitMonitorStringParam hostMonitorParam=buildMgmGetUnitMonitorParamRebulidBodyString(mgmGetUnitMonitorParam);
        		hostMonitorParam.setDescription(descriptionList[ind]);
        		hostMonitorParam.setMetric(relateCode);
        		hostMonitorParams.add(hostMonitorParam);
        		ind++;
        	}
        	
            return Result.success(hostMonitorParams);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
	
	public Result getUnitMonitorByContainerName(String containerName,String relateCode,String description,HostMonitorQueryParam hostMonitorQueryParam) throws ServiceException {
        try {
        	List<GetUnitMonitorParam> hostMonitorParams=new ArrayList<>();
        	if(nullCheck(containerName)) {
        		return Result.failure(CheckResult.failure(ServerGroupUnitChkRsEnum.MONITORREGISTER_ILLEGAL_NAME_MUST_NOT_BE_EMPTY));
        	}else if(hostMonitorQueryParam==null) {
        		return Result.failure(CheckResult.failure(HostChkRsEnum.MONITOR_ILLEGAL_STARTTIMEORENDTIME_MUST_NOT_BE_EMPTY));
        	}else if(nullCheck(relateCode)) {
        		return Result.failure(CheckResult.failure(ServerGroupUnitChkRsEnum.MONITORREGISTER_ILLEGAL_CODE_MUST_NOT_BE_EMPTY));
        	}
        	containerName="k8s_mysql_"+containerName+"-0";
        	String[] relateCodeList=relateCode.split(";");
        	String[] descriptionList=description.split(";");
        	int ind=0;
        	for(String relatecode:relateCodeList) {
        		MgmGetUnitMonitorParam mgmGetUnitMonitorParam=mgmApi.getUnitMonitorByContainerName(containerName,relatecode,hostMonitorQueryParam);
        		GetUnitMonitorParam hostMonitorParam=buildMgmGetUnitMonitorParamRebulidBody(mgmGetUnitMonitorParam);
        		hostMonitorParam.setDescription(descriptionList[ind]);
        		hostMonitorParam.setMetric(relateCode);
        		hostMonitorParams.add(hostMonitorParam);
        		ind++;
        	}
//        	HostMonitorParam hostMonitorParam=mgmApi.getUnitMonitorByContainerName(containerName,relateCode,hostMonitorQueryParam);
            return Result.success(hostMonitorParams);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
	private GetUnitMonitorParam buildMgmGetUnitMonitorParamRebulidBody(MgmGetUnitMonitorParam mgmGetUnitMonitorParam) {
		GetUnitMonitorParam hostMonitorParam=new GetUnitMonitorParam();
		hostMonitorParam.setHistory(mgmGetUnitMonitorParam.getHistory());
		hostMonitorParam.setDatas(mgmGetUnitMonitorParam.getMetrics());
		return hostMonitorParam;
	}
	
	private GetUnitMonitorStringParam buildMgmGetUnitMonitorParamRebulidBodyString(MgmGetUnitMonitorStringParam mgmGetUnitMonitorParam) {
		GetUnitMonitorStringParam hostMonitorParam=new GetUnitMonitorStringParam();
		hostMonitorParam.setHistory(mgmGetUnitMonitorParam.getHistory());
		hostMonitorParam.setDatas(mgmGetUnitMonitorParam.getMetrics());
		return hostMonitorParam;
	}
}
