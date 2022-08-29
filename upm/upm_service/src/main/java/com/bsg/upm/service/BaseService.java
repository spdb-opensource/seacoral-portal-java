package com.bsg.upm.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.alibaba.fastjson.JSONObject;
import com.bsg.upm.check.CheckResult;
import com.bsg.upm.check.resultenum.ChkRsInterface;
import com.bsg.upm.constant.DataCategoryConsts;
import com.bsg.upm.constant.DictConsts;
import com.bsg.upm.dao.AppDAO;
import com.bsg.upm.dao.BusinessSystemDAO;
import com.bsg.upm.dao.CmhaProxyDAO;
import com.bsg.upm.dao.DbUserDAO;
import com.bsg.upm.dao.DictDAO;
import com.bsg.upm.dao.DictTypeDAO;
import com.bsg.upm.dao.GroupDAO;
import com.bsg.upm.dao.GroupUserDAO;
import com.bsg.upm.dao.OperateLogDAO;
import com.bsg.upm.dao.OrderDAO;
import com.bsg.upm.dao.OrderGroupDAO;
import com.bsg.upm.dao.PrivilegeDAO;
import com.bsg.upm.dao.RemoteStorageVendorDAO;
import com.bsg.upm.dao.RoleCfgAppDAO;
import com.bsg.upm.dao.RoleCfgDataScopeDAO;
import com.bsg.upm.dao.RoleCfgOthersDAO;
import com.bsg.upm.dao.RoleDAO;
import com.bsg.upm.dao.SanDAO;
import com.bsg.upm.dao.ScaleDAO;
import com.bsg.upm.dao.SchemaDAO;
import com.bsg.upm.dao.ServGroupDAO;
import com.bsg.upm.dao.SystemDAO;
import com.bsg.upm.dao.UserDAO;
import com.bsg.upm.domain.AppDO;
import com.bsg.upm.domain.DictDO;
import com.bsg.upm.domain.DictTypeDO;
import com.bsg.upm.domain.GroupDO;
import com.bsg.upm.domain.RoleCfgDataScopeDO;
import com.bsg.upm.domain.UserDO;
import com.bsg.upm.exception.UserNotFoundException;
import com.bsg.upm.mgm.api.MgmApi;
import com.bsg.upm.mgm.model.MgmHost;
import com.bsg.upm.mgm.model.MgmRemoteStorage;
import com.bsg.upm.mgm.model.MgmService;

public class BaseService {

    @Autowired
    protected SystemDAO systemDAO;

    @Autowired
    protected DictTypeDAO dictTypeDAO;

    @Autowired
    protected DictDAO dictDAO;

    @Autowired
    protected GroupDAO groupDAO;

    @Autowired
    protected RoleDAO roleDAO;

    @Autowired
    protected RoleCfgDataScopeDAO roleCfgDataScopeDAO;

    @Autowired
    protected RoleCfgOthersDAO roleCfgOthersDAO;

    @Autowired
    protected UserDAO userDAO;
    
    @Autowired
    protected SchemaDAO schemaDAO;

    @Autowired
    protected GroupUserDAO groupUserDAO;

    @Autowired
    protected SanDAO sanDAO;
    
    @Autowired
    protected RemoteStorageVendorDAO remoteStorageVendorDAO;

    /*@Autowired
    protected ArchDAO archDAO;*/

    @Autowired
    protected ScaleDAO scaleDAO;

    @Autowired
    protected AppDAO appDAO;

    @Autowired
    protected OperateLogDAO operateLogDAO;

    @Autowired
    protected ThreadPoolTaskExecutor executor;

    @Autowired
    protected HttpSession session;

    @Autowired
    protected HttpRequestService httpRequestService;

    @Autowired
    protected RoleCfgAppDAO roleCfgAppDAO;

    @Autowired
    protected MgmApi mgmApi;

    @Autowired
    protected OrderGroupDAO orderGroupDAO;
    
    @Autowired
    protected ServGroupDAO servGroupDAO;
    
    @Autowired
    protected OrderDAO orderDAO;
    
    @Autowired
    protected PrivilegeDAO privilegeDAO;
    
    @Autowired
    protected DbUserDAO dbUserDAO;
    
    @Autowired
    protected CmhaProxyDAO cmhaProxyDAO;
    
    @Autowired
    protected BusinessSystemDAO businessSystemDAO;
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public static final String SYSPATH = File.separator + "etc" + File.separator + "spdb";

    public static String loginfo(ChkRsInterface checkResult, Object... arguments) {
        Object[] temp = { checkResult.getCode() };
        FormattingTuple ft = MessageFormatter.arrayFormat("[{}] " + checkResult.getMsg(),
                ArrayUtils.addAll(temp, arguments));
        return ft.getMessage();
    }

    public static String loginfo(String format, Object... arguments) {
        FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
        return ft.getMessage();
    }

    public static String loginfo(CheckResult checkResult) {
        FormattingTuple ft = MessageFormatter.format("[{}] " + checkResult.getMsg(), checkResult.getCode());
        return ft.getMessage();
    }

    public String getUsername() {
        if (session.getAttribute("user") != null) {
            UserDO userDO = (UserDO) session.getAttribute("user");
            return userDO.getUsername();
        }
        return "admin";
    }
    
    public Boolean nullCheck(String str) {
        if(str==null||str==""||str.equals("undefined")) {
        	return true;
        }else {
        	return false;
        }
    }

    public UserDO findUserDO(List<UserDO> userDOs, String username) {
        if (userDOs != null) {
            for (UserDO userDO : userDOs) {
                if (userDO.getUsername().equals(username)) {
                    return userDO;
                }
            }
        }
        return null;
    }

    public DictDO findDictDO(List<DictTypeDO> dictTypeDOs, String dictTypeCode, String dictCode) {
        if (dictTypeDOs != null) {
            for (DictTypeDO dictTypeDO : dictTypeDOs) {
                if (dictTypeDO.getCode().equals(dictTypeCode)) {
                    List<DictDO> dictDOs = dictTypeDO.getDicts();
                    for (DictDO dictDO : dictDOs) {
                        if (dictDO.getCode().equals(dictCode)) {
                            return dictDO;
                        }
                    }
                    break;
                }
            }
        }
        return null;
    }

    public MgmRemoteStorage findMgmSan(List<MgmRemoteStorage> mgmSans, String mgmSanId) {
        if (mgmSans == null || StringUtils.isBlank(mgmSanId)) {
            return null;
        }
        for (MgmRemoteStorage mgmSan : mgmSans) {
            if (mgmSan.getId().equals(mgmSanId)) {
                return mgmSan;
            }
        }
        return null;
    }

    public MgmHost findMgmHost(List<MgmHost> mgmHosts, String mgmHostId) {
        if (mgmHosts == null || StringUtils.isBlank(mgmHostId)) {
            return null;
        }
        for (MgmHost mgmHost : mgmHosts) {
            if (mgmHost.getId().equals(mgmHostId)) {
                return mgmHost;
            }
        }
        return null;
    }

    public MgmService findMgmService(List<MgmService> mgmServices, String mgmServiceId) {
        if (mgmServices == null || StringUtils.isBlank(mgmServiceId)) {
            return null;
        }
        for (MgmService mgmService : mgmServices) {
            if (mgmService.getId().equals(mgmServiceId)) {
                return mgmService;
            }
        }
        return null;
    }

    public MgmService.Unit findMgmUnit(MgmService mgmService, String mgmUnitId) {
        if (mgmService == null || StringUtils.isBlank(mgmUnitId)) {
            return null;
        }
        List<MgmService.Unit> mgmUnits = mgmService.getUnits();
        if (mgmUnits != null) {
            for (MgmService.Unit mgmUnit : mgmUnits) {
                MgmService.Unit.Info mgmUnitInfo = mgmUnit.getInfo();
                if (mgmUnitInfo != null) {
                    if (mgmUnitInfo.getId().equals(mgmUnitId)) {
                        return mgmUnit;
                    }
                }
            }
        }
        return null;
    }

    public List<Long> listVisiableSiteId(String username) {
        List<Long> visiableSiteIds = new ArrayList<>();
        UserDO userDO = userDAO.getByUsername(username);
        if (userDO == null) {
            throw new UserNotFoundException(username + " not found.");
        }
        return visiableSiteIds;
    }


    public List<String> listVisiableUserData(String username, String type) {
        List<String> usernames = new ArrayList<>();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(type)) {
            return usernames;
        }
        List<UserDO> userDOs = userDAO.list(null);
        UserDO userDO = findUserDO(userDOs, username);
        if (userDO == null) {
            return usernames;
        }
        List<GroupDO> groupDOs = userDO.getGroups();
        List<String> groupIds = new ArrayList<>();
        for (GroupDO groupDO : groupDOs) {
            groupIds.add(groupDO.getId());
        }
        RoleCfgDataScopeDO roleCfgDataScopeDO = roleCfgDataScopeDAO.getByRoleId(userDO.getRoleId());
        if (roleCfgDataScopeDO == null) {
            return usernames;
        }
        String dataScope = null;
        if (DataCategoryConsts.SERV_GROUP.equals(type)) {
            dataScope = roleCfgDataScopeDO.getServGroup();
        } else if (DataCategoryConsts.ORDER_GROUP.equals(type)) {
            dataScope = roleCfgDataScopeDO.getOrderGroup();
        } else if (DataCategoryConsts.OPERATE_LOG.equals(type)) {
            dataScope = roleCfgDataScopeDO.getOperateLog();
        }

        if (DictConsts.DATA_SCOPE_ONESELF.equals(dataScope)) {
            usernames.add(username);
        } else if (DictConsts.DATA_SCOPE_GROUP.equals(dataScope)) {
            for (UserDO u : userDOs) {
                List<GroupDO> gs = u.getGroups();
                for (GroupDO g : gs) {
                    if (groupIds.contains(g.getId())) {
                        usernames.add(u.getUsername());
                        break;
                    }
                }
            }
        } else if (DictConsts.DATA_SCOPE_ALL.equals(dataScope)) {
            for (UserDO u : userDOs) {
                usernames.add(u.getUsername());
            }
        }
        return usernames;
    }

    public AppDO findAppDO(List<AppDO> appDOs, Long appId) {
        if (appDOs != null) {
            for (AppDO appDO : appDOs) {
                if (appDO.getId().equals(appId)) {
                    return appDO;
                }
            }
        }
        return null;
    }
}
