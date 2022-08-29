package com.bsg.upm.mgm.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.aspectj.weaver.NewMemberClassTypeMunger;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bsg.upm.constant.Consts;
import com.bsg.upm.constant.DictConsts;
import com.bsg.upm.dao.OrderGroupDAO;
import com.bsg.upm.domain.UserDO;
import com.bsg.upm.enums.InterfaceEnum;
import com.bsg.upm.exception.CallingInterfaceException;
import com.bsg.upm.exception.ConnectConsulException;
import com.bsg.upm.exception.ServiceNotFoundException;
import com.bsg.upm.form.ServerGroupChangeUserPwdForm;
import com.bsg.upm.mgm.body.MgmBackupStrategyBody;
import com.bsg.upm.mgm.body.MgmBaseBody;
import com.bsg.upm.mgm.body.MgmClusterBody;
import com.bsg.upm.mgm.body.MgmDatabaseBody;
import com.bsg.upm.mgm.body.MgmHostBody;
import com.bsg.upm.mgm.body.MgmImageBody;
import com.bsg.upm.mgm.body.MgmNetworkingBody;
import com.bsg.upm.mgm.body.MgmRemoteStorageBody;
import com.bsg.upm.mgm.body.MgmRemoteStoragePoolBody;
import com.bsg.upm.mgm.body.MgmServerGroupBackupBody;
import com.bsg.upm.mgm.body.MgmServerGroupBody;
import com.bsg.upm.mgm.body.MgmServerGroupCfgsBody;
import com.bsg.upm.mgm.body.MgmServerGroupImageBody;
import com.bsg.upm.mgm.body.MgmServerGroupResourceRequestsBody;
import com.bsg.upm.mgm.body.MgmServerGroupStateBody;
import com.bsg.upm.mgm.body.MgmServerGroupUnitBackupBody;
import com.bsg.upm.mgm.body.MgmServerGroupUnitRebulidBody;
import com.bsg.upm.mgm.body.MgmServerGroupUnitRebulidBody2;
import com.bsg.upm.mgm.body.MgmServerGroupUnitRoleChangeBody;
import com.bsg.upm.mgm.body.MgmServerGroupUnitRoleChangeSlaveBody;
import com.bsg.upm.mgm.body.MgmServerGroupUpdateUserBody;
import com.bsg.upm.mgm.body.MgmServerGroupUserBody;
import com.bsg.upm.mgm.body.MgmSiteBody;
import com.bsg.upm.mgm.body.MgmUpdateSiteBody;
import com.bsg.upm.mgm.model.MgmBackupFile;
import com.bsg.upm.mgm.model.MgmBackupStrategy;
import com.bsg.upm.mgm.model.MgmCluster;
import com.bsg.upm.mgm.model.MgmDatabase;
import com.bsg.upm.mgm.model.MgmHost;
import com.bsg.upm.mgm.model.MgmImage;
import com.bsg.upm.mgm.model.MgmImageConf;
import com.bsg.upm.mgm.model.MgmNetworking;
import com.bsg.upm.mgm.model.MgmRemoteStorage;
import com.bsg.upm.mgm.model.MgmRemoteStoragePool;
import com.bsg.upm.mgm.model.MgmSaveServerGroup;
import com.bsg.upm.mgm.model.MgmServerGroup;
import com.bsg.upm.mgm.model.MgmServerGroup.Image;
import com.bsg.upm.mgm.model.MgmServerGroup.KeySet;
import com.bsg.upm.mgm.model.MgmService;
import com.bsg.upm.mgm.model.MgmSite;
import com.bsg.upm.mgm.model.MgmTask;
import com.bsg.upm.mgm.model.MgmTopology;
import com.bsg.upm.mgm.model.MgmUser;
import com.bsg.upm.mgm.query.MgmBackupFileQuery;
import com.bsg.upm.mgm.query.MgmBackupRestoreFile;
import com.bsg.upm.mgm.query.MgmClusterQuery;
import com.bsg.upm.mgm.query.MgmDatabaseQuery;
import com.bsg.upm.mgm.query.MgmHostQuery;
import com.bsg.upm.mgm.query.MgmImageQuery;
import com.bsg.upm.mgm.query.MgmNetworkingQuery;
import com.bsg.upm.mgm.query.MgmRemoteStoragePoolQuery;
import com.bsg.upm.mgm.query.MgmRemoteStorageQuery;
import com.bsg.upm.mgm.query.MgmServerGroupDetailQuery;
import com.bsg.upm.mgm.query.MgmServerGroupQuery;
import com.bsg.upm.mgm.query.MgmServiceQuery;
import com.bsg.upm.mgm.query.MgmSiteQuery;
import com.bsg.upm.mgm.query.MgmUserQuery;
import com.bsg.upm.param.HostDeleteParam;
import com.bsg.upm.param.HostMonitorCancelParam;
import com.bsg.upm.param.HostMonitorParam;
import com.bsg.upm.param.HostMonitorQueryParam;
import com.bsg.upm.param.HostMonitorRegisParam;
import com.bsg.upm.param.HostMonitorRegisParam1;
import com.bsg.upm.param.MgmGetUnitMonitorParam;
import com.bsg.upm.param.MgmGetUnitMonitorStringParam;
import com.bsg.upm.param.ServMonitorCancelParam;
import com.bsg.upm.param.ServeUnitRegisParam;
import com.bsg.upm.query.BackupEndpointParam;
import com.bsg.upm.query.HostParam;
import com.bsg.upm.service.HttpRequestService;
import com.bsg.upm.util.http.HttpRequestUtil;
import com.bsg.upm.util.http.HttpResp;

@Service
public class MgmApi extends HttpRequestService {
	@Autowired
    protected OrderGroupDAO orderGroupDAO;

    public List<MgmSite> listSite()
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/sites";
        endpoint = MessageFormat.format(endpoint, "v1.0");
        HttpResp httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
        if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
            throw new CallingInterfaceException(httpResp.getResponseContent());
        }
        return JSONArray.parseArray(httpResp.getResponseContent(), MgmSite.class);
    }

    /**
     * 
     * 集群新增
     * 
     * @param mgmClusterBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     * @return void
     */
    public void saveCluster(MgmClusterBody mgmClusterBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/clusters";
        endpoint = MessageFormat.format(endpoint, "v1.0");
        if (Consts.TEST_FLAG) {
            logger.info("Http Request URL: {}", endpoint);
            logger.info("Http Request Method: {}", "POST");
            if (mgmClusterBody != null) {
                logger.info("Http Request Data: {}", JSON.toJSON(mgmClusterBody));
            }
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmClusterBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPostRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_CREATED) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }

    /**
     * 
     * 集群编辑
     * 
     * @param clusterId
     * @param mgmClusterBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     * @return void
     */
    public void updateCluster(String clusterId, MgmClusterBody mgmClusterBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/clusters/{1}";
        endpoint = MessageFormat.format(endpoint, "v1.0", clusterId);
        if (Consts.TEST_FLAG) {
            logger.info("Http Request URL: {}", endpoint);
            logger.info("Http Request Method: {}", "PUT");
            if (mgmClusterBody != null) {
                logger.info("Http Request Data: {}", JSON.toJSON(mgmClusterBody));
            }
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmClusterBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }

    /**
     * 
     * 集群查询
     * 
     * @param mgmClusterQuery
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     * @return List<MgmCluster>
     */
    public List<MgmCluster> listCluster(MgmClusterQuery mgmClusterQuery)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {

        String path = processPath(mgmClusterQuery);
        String endpoint = "{0}/manager/clusters" + path;
        endpoint = MessageFormat.format(endpoint, "v1.0");
        HttpResp httpResp;
        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("clusterList");
            httpResp = new HttpResp(200, jsonStr);
        } else {
            httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
        }
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (mgmClusterQuery != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmClusterQuery));
        }
        if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
            throw new CallingInterfaceException(httpResp.getResponseContent());
        }
        return JSONArray.parseArray(httpResp.getResponseContent(), MgmCluster.class);
    }

    /**
     * 
     * 集群删除
     * 
     * @param clusterId
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     * @return void
     */
    public void removeCluster(String clusterId)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/clusters/{1}";
        endpoint = MessageFormat.format(endpoint, "v1.0", clusterId);
        if (Consts.TEST_FLAG) {
            logger.info("Http Request URL: {}", endpoint);
            logger.info("Http Request Method: {}", "DELETE");
            if (clusterId != null) {
                logger.info("Http Request Data: {}", clusterId);
            }
            return;
        } else {
            HttpResp httpResp = sendDeleteRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_NO_CONTENT) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }

    }

    /**
     * 
     * 主机新增
     * 
     * @param mgmHostBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     * @return void
     */
    public void saveHost(MgmHostBody mgmHostBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/hosts";
        endpoint = MessageFormat.format(endpoint, "v1.0");
        if (Consts.TEST_FLAG) {
            logger.info("Http Request URL: {}", endpoint);
            logger.info("Http Request Method: {}", "POST");
            if (mgmHostBody != null) {
                logger.info("Http Request Data: {}", JSON.toJSON(mgmHostBody));
            }
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmHostBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPostRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_CREATED) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    /**
     * 
     * 主机校验
     * 
     * @param mgmHostBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     * @return void
     */
    public void validation(MgmHostBody mgmHostBody,HostParam hostParam)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
    	String path = processPath(hostParam);
    	String endpoint = "{0}/manager/hosts"+path;
        endpoint = MessageFormat.format(endpoint, "v1.0");
        if (Consts.TEST_FLAG) {
            logger.info("Http Request URL: {}", endpoint);
            logger.info("Http Request Method: {}", "POST");
            if (mgmHostBody != null) {
                logger.info("Http Request Data: {}", JSON.toJSON(mgmHostBody));
            }
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmHostBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPostRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_CREATED) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }

    /**
     * 
     * 主机编辑
     * 
     * @param hostId
     * @param mgmHostBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     * @return void
     */
    public void updateHost(String hostId, MgmHostBody mgmHostBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/hosts/{1}";
        endpoint = MessageFormat.format(endpoint, "v1.0", hostId);
        if (Consts.TEST_FLAG) {
            logger.info("Http Request URL: {}", endpoint);
            logger.info("Http Request Method: {}", "PUT");
            if (mgmHostBody != null) {
                logger.info("Http Request Data: {}", JSON.toJSON(mgmHostBody));
            }
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmHostBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }

    /**
     * 
     * 主机查询
     * 
     * @param mgmHostQuery
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     * @return List<MgmHost>
     */
    public List<MgmHost> listHost(MgmHostQuery mgmHostQuery)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String path = processPath(mgmHostQuery);
        String endpoint = "{0}/manager/hosts" + path;
        endpoint = MessageFormat.format(endpoint, "v1.0");
        HttpResp httpResp;
        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("hostList");
            httpResp = new HttpResp(200, jsonStr);
        } else {
            httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
        }
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (mgmHostQuery != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmHostQuery));
        }
        if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
            throw new CallingInterfaceException(httpResp.getResponseContent());
        }
        return JSONArray.parseArray(httpResp.getResponseContent(), MgmHost.class);
    }
    
    /** 主机详情
     * @param mgmHostQuery
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public MgmHost getHost(String hostId)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/hosts/{1}/detail" ;
        endpoint = MessageFormat.format(endpoint, "v1.0",hostId);
        HttpResp httpResp;
        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("hostList");
            httpResp = new HttpResp(200, jsonStr);
        } else {
            httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
        }
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (hostId != null) {
            logger.info("Http Request Data: {}", hostId);
        }
        if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
            throw new CallingInterfaceException(httpResp.getResponseContent());
        }
        return  JSONArray.parseObject(httpResp.getResponseContent(), MgmHost.class);
    }

    /** 查询主机agent是否注册
     * @param hostIp
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void getAgents(String hostIp)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "v1/agents/{0}/info";
        endpoint = MessageFormat.format(endpoint, hostIp);
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (hostIp != null) {
            logger.info("Http Request Data: {}", hostIp);
        }
        HttpResp httpResp;
        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("hostAgents");
            httpResp = new HttpResp(200, jsonStr);
        } else {
            httpResp = sendMonitorGetRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
       
    }
    
    /** 查询主机host是否注册
     * @param hostId
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void getAgentsHost(String hostId)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "v1/hosts/{0}/info";
        endpoint = MessageFormat.format(endpoint, hostId);
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (hostId != null) {
            logger.info("Http Request Data: {}", hostId);
        }
        HttpResp httpResp;
        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("hostAgentsHost");
            httpResp = new HttpResp(200, jsonStr);
        } else {
            httpResp = sendMonitorGetRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
        
    }
    
    /**
     * 
     * 主机删除
     * 
     * @param hostId
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     * @return void
     */
    public void removeHost(String hostId,HostDeleteParam userForm)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
    	userForm.setPwd(URLEncoder.encode(userForm.getPwd(),"UTF-8"));
    	String path = processPath(userForm);
        String endpoint = "{0}/manager/hosts/{1}"+ path;
        endpoint = MessageFormat.format(endpoint, "v1.0", hostId);
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "DELETE");
        if (hostId != null) {
            logger.info("Http Request Data: {}", hostId);
        }
        if (Consts.TEST_FLAG) {
            return;
        } else {
            HttpResp httpResp = sendDeleteRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_NO_CONTENT) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    /** 监控注册agent
     * @param hostMonitorRegisParam
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void monitorRegister(HostMonitorRegisParam hostMonitorRegisParam)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "v1/agents/install";
//        endpoint = MessageFormat.format(endpoint, "v1.0", hostId);
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "POST");
        if (hostMonitorRegisParam != null) {
        	logger.info("Http Request Data: {}", JSON.toJSON(hostMonitorRegisParam));
        }
        if (Consts.TEST_FLAG) {
        	return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(hostMonitorRegisParam, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendMonitorPostRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_CREATED) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    /** 监控注册host
     * @param hostMonitorRegisParam
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void monitorRegister1(HostMonitorRegisParam1 hostMonitorRegisParam)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "v1/hosts";
//        endpoint = MessageFormat.format(endpoint, "v1.0", hostId);
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "POST");
        if (hostMonitorRegisParam != null) {
        	logger.info("Http Request Data: {}", JSON.toJSON(hostMonitorRegisParam));
        }
        if (Consts.TEST_FLAG) {
        	return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(hostMonitorRegisParam, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendMonitorPostRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_CREATED) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    /** 监控注销agent
     * @param hostMonitorRegisParam
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void monitorCancel(HostMonitorRegisParam hostMonitorRegisParam)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
    	
    	String path = processPath(hostMonitorRegisParam);
        String endpoint = "v1/agents/uninstall" + path;
//        endpoint = MessageFormat.format(endpoint, "v1.0");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "DELETE");
        if (hostMonitorRegisParam != null) {
        	logger.info("Http Request Data: {}", JSON.toJSON(hostMonitorRegisParam));
        }
        if (Consts.TEST_FLAG) {
        	return;
        } else {
            HttpResp httpResp = sendMonitorDeleteRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_NO_CONTENT) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    /** 监控注销host
     * @param hostId
     * @param hostMonitorCancelParam
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void monitorCancel1(String hostId,HostMonitorCancelParam hostMonitorCancelParam)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
//    	DELETE http://<ClusterManager>:20154/v1/hosts/<host_id>?force=true
    	String path = processPath(hostMonitorCancelParam);
        String endpoint = "v1/hosts/{0}" + path;
        endpoint = MessageFormat.format(endpoint, hostId);
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "DELETE");
        if (hostMonitorCancelParam != null) {
        	logger.info("Http Request Data: {}", JSON.toJSON(hostMonitorCancelParam));
        }
        if (Consts.TEST_FLAG) {
        	return;
        } else {
            HttpResp httpResp = sendMonitorDeleteRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_NO_CONTENT) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    /**
     * 
     * 镜像查询
     * 
     * @param mgmImageQuery
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     * @return List<MgmImage>
     */
    public List<MgmImage> listImage(MgmImageQuery mgmImageQuery)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String path = processPath(mgmImageQuery);
        String endpoint = "{0}/manager/images" + path;
        endpoint = MessageFormat.format(endpoint, "v1.0");
        HttpResp httpResp;
        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("imageList");
            httpResp = new HttpResp(200, jsonStr);
        } else {
            httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
        }
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (mgmImageQuery != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmImageQuery));
        }
        if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
            throw new CallingInterfaceException(httpResp.getResponseContent());
        }
        return JSONArray.parseArray(httpResp.getResponseContent(), MgmImage.class);
    }
    
    public List<MgmImage> getImage(MgmImageQuery mgmImageQuery)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String path = processPath(mgmImageQuery);
        String endpoint = "{0}/manager/images" + path;
        endpoint = MessageFormat.format(endpoint, "v1.0");
        HttpResp httpResp;
        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("image");
            httpResp = new HttpResp(200, jsonStr);
        } else {
            httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
        }
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (mgmImageQuery != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmImageQuery));
        }
        if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
            throw new CallingInterfaceException(httpResp.getResponseContent());
        }
        return JSONArray.parseArray(httpResp.getResponseContent(), MgmImage.class);
    }
    
    /**
     * 
     * 镜像配置查询
     * @param imageId
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public MgmImageConf imageConf(Image image)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
    	//String path = processPath(image);
        String endpoint = "{0}/manager/images/{1}/templates" ;
        endpoint = MessageFormat.format(endpoint, "v1.0",image.getId());
        
        /*
    	String endpoint = "{0}/manager/images/templates/{1}";
        endpoint = MessageFormat.format(endpoint, "v1.0", imageId);*/
        HttpResp httpResp;
        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("imageConf");
            httpResp = new HttpResp(200, jsonStr);
        } else {
            httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
        }
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (image != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(image));
        }
        if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
            throw new CallingInterfaceException(httpResp.getResponseContent());
        }
        return JSONArray.parseObject(httpResp.getResponseContent(), MgmImageConf.class);
    }

    /**
     * 
     * 镜像新增
     * 
     * @param mgmImageBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     * @return void
     */
    public void saveImage(MgmImageBody mgmImageBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/images";
        endpoint = MessageFormat.format(endpoint, "v1.0");
        if (Consts.TEST_FLAG) {
            logger.info("Http Request URL: {}", endpoint);
            logger.info("Http Request Method: {}", "POST");
            if (mgmImageBody != null) {
                logger.info("Http Request Data: {}", JSON.toJSON(mgmImageBody));
            }
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmImageBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPostRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_CREATED) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }

    /**
     * 
     * 镜像编辑
     * 
     * @param imageId
     * @param mgmImageBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     * @return void
     */
    public void updateImage(String imageId, MgmImageBody mgmImageBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/images/{1}";
        endpoint = MessageFormat.format(endpoint, "v1.0", imageId);
        if (Consts.TEST_FLAG) {
            logger.info("Http Request URL: {}", endpoint);
            logger.info("Http Request Method: {}", "PUT");
            if (mgmImageBody != null) {
                logger.info("Http Request Data: {}", JSON.toJSON(mgmImageBody));
            }
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmImageBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }

    /**
     * 
     * 镜像删除
     * 
     * @param imageId
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     * @return void
     */
    public void removeImage(String imageId)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/images/{1}";
        endpoint = MessageFormat.format(endpoint, "v1.0", imageId);
        if (Consts.TEST_FLAG) {
            logger.info("Http Request URL: {}", endpoint);
            logger.info("Http Request Method: {}", "DELETE");
            if (imageId != null) {
                logger.info("Http Request Data: {}", imageId);
            }
            return;
        } else {
            HttpResp httpResp = sendDeleteRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_NO_CONTENT) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }

    /**
     * 站点查询
     * 
     * @param mgmSiteQuery
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public List<MgmSite> listSite(MgmSiteQuery mgmSiteQuery)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String path = processPath(mgmSiteQuery);
        String endpoint = "/{0}/manager/sites" + path;
        endpoint = MessageFormat.format(endpoint, "v1.0");

        logger.info("站点查询：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (mgmSiteQuery != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmSiteQuery));
        }

        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("site");
            HttpResp httpResp = new HttpResp(200, jsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            List<MgmSite> result = JSONArray.parseArray(httpResp.getResponseContent(), MgmSite.class);

            String id = mgmSiteQuery.getId();
            if (id != null) {
                List<MgmSite> temp = new ArrayList<>();
                for (MgmSite mgmSite : result) {
                    if (id.equals(mgmSite.getId())) {
                        temp.add(mgmSite);
                        break;
                    }
                }
                result = temp;
            }
            return result;
        } else {
            HttpResp httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            return JSONArray.parseArray(httpResp.getResponseContent(), MgmSite.class);
        }
    }

    /**
     * 站点保存
     * 
     * @param mgmSiteBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void saveSite(MgmSiteBody mgmSiteBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/sites";
        endpoint = MessageFormat.format(endpoint, "v1.0");

        logger.info("站点保存：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "POST");
        if (mgmSiteBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmSiteBody));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmSiteBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPostRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_CREATED) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    

    /**
     * 站点编辑
     * 
     * @param siteId
     * @param mgmSiteBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void updateSite(String siteId, MgmUpdateSiteBody mgmSiteBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/sites/{1}";
        endpoint = MessageFormat.format(endpoint, "v1.0", siteId);

        logger.info("站点编辑：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "PUT");
        if (mgmSiteBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmSiteBody));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmSiteBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }

    /**
     * 站点删除
     * 
     * @param siteId
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void removeSite(String siteId)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/sites/{1}";
        endpoint = MessageFormat.format(endpoint, "v1.0", siteId);

        logger.info("站点删除：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "DELETE");

        if (Consts.TEST_FLAG) {
            return;
        } else {
            HttpResp httpResp = sendDeleteRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_NO_CONTENT) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }

    /**
     * 网段查询
     * 
     * @param mgmNetworkingQuery
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public List<MgmNetworking> listNetworking(MgmNetworkingQuery mgmNetworkingQuery)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String path = processPath(mgmNetworkingQuery);
        String endpoint = "/{0}/manager/networks" + path;
        endpoint = MessageFormat.format(endpoint, "v1.0");

        logger.info("网段查询：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (mgmNetworkingQuery != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmNetworkingQuery));
        }

        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("network");
            HttpResp httpResp = new HttpResp(200, jsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }

            List<MgmNetworking> result = JSONArray.parseArray(httpResp.getResponseContent(), MgmNetworking.class);
            String id = mgmNetworkingQuery.getId();
            if (id != null) {
                List<MgmNetworking> temp = new ArrayList<>();
                for (MgmNetworking mgmNetworking : result) {
                    if (id.equals(mgmNetworking.getId())) {
                        temp.add(mgmNetworking);
                        break;
                    }
                }
                result = temp;
            }

            return result;
        } else {
            HttpResp httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            return JSONArray.parseArray(httpResp.getResponseContent(), MgmNetworking.class);
        }
    }

    /**
     * 网段保存
     * 
     * @param mgmNetworkingBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void saveNetworking(MgmNetworkingBody mgmNetworkingBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/networks";
        endpoint = MessageFormat.format(endpoint, "v1.0");

        logger.info("网段保存：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "POST");
        if (mgmNetworkingBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmNetworkingBody));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmNetworkingBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPostRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_CREATED) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }

    /**
     * 网段编辑
     * 
     * @param networkId
     * @param mgmNetworkingBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void updateNetworking(String networkId, MgmNetworkingBody mgmNetworkingBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/networks/{1}";
        endpoint = MessageFormat.format(endpoint, "v1.0", networkId);

        logger.info("网段编辑：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "PUT");
        if (mgmNetworkingBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmNetworkingBody));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmNetworkingBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }

    /**
     * 网段删除
     * 
     * @param networksId
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void removeNetworking(String networksId)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/networks/{1}";
        endpoint = MessageFormat.format(endpoint, "v1.0", networksId);

        logger.info("网段删除：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "DELETE");

        if (Consts.TEST_FLAG) {
            return;
        } else {
            HttpResp httpResp = sendDeleteRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_NO_CONTENT) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    /**
     * 转发CM的shell
     * 
     * @param podId
     * @return 
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public String toShell(String site_id,String podId,String type)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "/{0}/api/v1/pod/default/{1}/shell/{2}";
        endpoint = MessageFormat.format(endpoint, site_id, podId,type);

        logger.info("转发CM的shell：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");

        if (Consts.TEST_FLAG) {
            return null;
        } else {
            HttpResp httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }else
            	
            	return JSONObject.parse(httpResp.getResponseContent()).toString();
        }
    }

    /**
     * 外置存储查询
     * 
     * @param id
     *            外置存储ID
     * @param name
     *            外置存储名称
     * @param siteId
     *            所属站点id
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public List<MgmRemoteStorage> listRemoteStorage(MgmRemoteStorageQuery mgmRemoteStorageQuery)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String path = processPath(mgmRemoteStorageQuery);
        String endpoint = "/{0}/manager/storages/remote" + path;
        endpoint = MessageFormat.format(endpoint, "v1.0");

        logger.info("外置存储查询：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (mgmRemoteStorageQuery != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmRemoteStorageQuery));
        }

        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("remote-storage");
            HttpResp httpResp = new HttpResp(200, jsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }

            List<MgmRemoteStorage> result = JSONArray.parseArray(httpResp.getResponseContent(), MgmRemoteStorage.class);
            String id = mgmRemoteStorageQuery.getId();
            if (id != null) {
                List<MgmRemoteStorage> temp = new ArrayList<>();
                for (MgmRemoteStorage mgmRemoteStorage : result) {
                    if (id.equals(mgmRemoteStorage.getId())) {
                        temp.add(mgmRemoteStorage);
                        break;
                    }
                }
                result = temp;
            }

            return result;
        } else {
            HttpResp httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            return JSONArray.parseArray(httpResp.getResponseContent(), MgmRemoteStorage.class);
        }
    }

    /**
     * 外置存储保存
     * 
     * @param mgmRemoteStorageBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void saveRemoteStorage(MgmRemoteStorageBody mgmRemoteStorageBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/storages/remote";
        endpoint = MessageFormat.format(endpoint, "v1.0");

        logger.info("外置存储保存：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "POST");
        if (mgmRemoteStorageBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmRemoteStorageBody));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmRemoteStorageBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPostRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_CREATED) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }

    /**
     * 外置存储编辑
     * 
     * @param storageId
     * @param mgmRemoteStorageBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void updateRemoteStorage(String storageId, MgmRemoteStorageBody mgmRemoteStorageBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/storages/remote/{1}";
        endpoint = MessageFormat.format(endpoint, "v1.0", storageId);

        logger.info("外置存储修改：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "PUT");
        if (mgmRemoteStorageBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmRemoteStorageBody));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmRemoteStorageBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }

    /**
     * 外置存储删除
     * 
     * @param storageId
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void removeRemoteStorage(String storageId)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/storages/remote/{1}";
        endpoint = MessageFormat.format(endpoint, "v1.0", storageId);

        logger.info("外置存储删除：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "DELETE");

        if (Consts.TEST_FLAG) {
            return;
        } else {
            HttpResp httpResp = sendDeleteRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_NO_CONTENT) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }

    /**
     * 外置存储池查询
     * 
     * @param sanId
     * @param id
     * @param name
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public List<MgmRemoteStoragePool> listRemoteStoragePool(String sanId,
            MgmRemoteStoragePoolQuery mgmRemoteStoragePoolQuery)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String path = processPath(mgmRemoteStoragePoolQuery);
        String endpoint = "/{0}/manager/storages/remote/{1}/pools" + path;
        endpoint = MessageFormat.format(endpoint, "v1.0", sanId);

        logger.info("外置存储池查询：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (mgmRemoteStoragePoolQuery != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmRemoteStoragePoolQuery));
        }

        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("remote-storage-pool");
            HttpResp httpResp = new HttpResp(200, jsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }

            List<MgmRemoteStoragePool> result = JSONArray.parseArray(httpResp.getResponseContent(),
                    MgmRemoteStoragePool.class);
            String id = mgmRemoteStoragePoolQuery.getId();
            if (id != null) {
                List<MgmRemoteStoragePool> temp = new ArrayList<>();
                for (MgmRemoteStoragePool mgmRemoteStoragePool : result) {
                    if (id.equals(mgmRemoteStoragePool.getId())) {
                        temp.add(mgmRemoteStoragePool);
                        break;
                    }
                }
                result = temp;
            }

            return result;
        } else {
            HttpResp httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            return JSONArray.parseArray(httpResp.getResponseContent(), MgmRemoteStoragePool.class);
        }
    }

    /**
     * 外置存储池保存
     * 
     * @param mgmRemoteStorageBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void saveRemoteStoragePool(String storageId, MgmRemoteStoragePoolBody mgmRemoteStoragePoolBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/storages/remote/{1}/pools";
        endpoint = MessageFormat.format(endpoint, "v1.0", storageId);

        logger.info("外置存储池保存：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "POST");
        if (mgmRemoteStoragePoolBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmRemoteStoragePoolBody));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmRemoteStoragePoolBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPostRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_CREATED) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }

    /**
     * 外置存储池编辑
     * 
     * @param storageId
     * @param mgmRemoteStorageBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void updateRemoteStoragePool(String storageId, String poolId,
            MgmRemoteStoragePoolBody mgmRemoteStoragePoolBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/storages/remote/{1}/pools/{2}";
        endpoint = MessageFormat.format(endpoint, "v1.0", storageId, poolId);

        logger.info("外置存储池编辑：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "PUT");
        if (mgmRemoteStoragePoolBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmRemoteStoragePoolBody));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmRemoteStoragePoolBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }

    /**
     * 外置存储池删除
     * 
     * @param storageId
     * @param poolId
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void removeRemoteStoragePool(String storageId, String poolId)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/storages/remote/{1}/pools/{2}";
        endpoint = MessageFormat.format(endpoint, "v1.0", storageId, poolId);

        logger.info("外置存储池删除：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "DELETE");

        if (Consts.TEST_FLAG) {
            return;
        } else {
            HttpResp httpResp = sendDeleteRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_NO_CONTENT) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }

    /**
     * 服务应用查询，提供给服务组查询调用
     * 
     * @param mgmServerGroupQuery
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public List<MgmServerGroup> listServerGroup(MgmServerGroupQuery mgmServerGroupQuery)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String path = processPath(mgmServerGroupQuery);
        String endpoint = "/{0}/manager/apps" + path;
        endpoint = MessageFormat.format(endpoint, "v1.0");

        logger.info("服务应用查询（服务组查询）：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (mgmServerGroupQuery != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmServerGroupQuery));
        }

        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("servergroup");
            HttpResp httpResp = new HttpResp(200, jsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }

            List<MgmServerGroup> result = JSONArray.parseArray(httpResp.getResponseContent(), MgmServerGroup.class);
            return result;
        } else {
            HttpResp httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            return JSONArray.parseArray(httpResp.getResponseContent(), MgmServerGroup.class);
        }
    }
    
    /**
     * 服务应用详细查询，提供给服务组详细查询使用
     * @param mgmServerGroupDetailQuery
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public List<MgmServerGroup> detailServerGroup(MgmServerGroupDetailQuery mgmServerGroupDetailQuery)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String path = processPath(mgmServerGroupDetailQuery);
        String endpoint = "/{0}/manager/apps/detail" + path;
        endpoint = MessageFormat.format(endpoint, "v1.0");

        logger.info("服务应用详细查询（服务组详细查询）：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (mgmServerGroupDetailQuery != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmServerGroupDetailQuery));
        }

        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("servergroupdetail");
            HttpResp httpResp = new HttpResp(200, jsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }

            List<MgmServerGroup> result = JSONArray.parseArray(httpResp.getResponseContent(), MgmServerGroup.class);
            return result;
        } else {
            HttpResp httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            return JSONArray.parseArray(httpResp.getResponseContent(), MgmServerGroup.class);
        }
    }

    /**
     * 服务应用版本变更
     * 
     * @param appId
     * @param mgmServerGroupImageBody
     * @throws IOException
     * @throws CallingInterfaceException
     * @throws ServiceNotFoundException
     * @throws ConnectConsulException
     */
    public MgmSaveServerGroup imageServerGroup(String appId, MgmServerGroupImageBody mgmServerGroupImageBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        if (appId == null || appId.length() == 0) {
            logger.info("版本变更（服务应用）app_id值空");
            MgmSaveServerGroup mgmSaveServerGroup=new MgmSaveServerGroup();
            mgmSaveServerGroup.setId("11111111111111111111111111111");
            mgmSaveServerGroup.setName("test");
            mgmSaveServerGroup.setTaskId("taskId");
            return mgmSaveServerGroup;
        }
        if (mgmServerGroupImageBody == null) {
            logger.info("版本变更（服务应用）请求内容值空");
            MgmSaveServerGroup mgmSaveServerGroup=new MgmSaveServerGroup();
            mgmSaveServerGroup.setId("11111111111111111111111111111");
            mgmSaveServerGroup.setName("test");
            mgmSaveServerGroup.setTaskId("taskId");
            return mgmSaveServerGroup;
        }

        String endpoint = "/{0}/manager/apps/{1}/image";
        endpoint = MessageFormat.format(endpoint, "v1.0", appId);

        logger.info("服务应用运行版本变更：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "PUT");
        if (mgmServerGroupImageBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmServerGroupImageBody));
        }

        if (Consts.TEST_FLAG) {
        	MgmSaveServerGroup mgmSaveServerGroup=new MgmSaveServerGroup();
            mgmSaveServerGroup.setId("11111111111111111111111111111");
            mgmSaveServerGroup.setName("test");
            mgmSaveServerGroup.setTaskId("taskId");
            return mgmSaveServerGroup;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmServerGroupImageBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            return JSONObject.parseObject(httpResp.getResponseContent(), MgmSaveServerGroup.class);
        }

    }

    /**
     * 服务应用运行状态变更
     * 
     * @param appId
     * @param state
     * @throws IOException
     * @throws CallingInterfaceException
     * @throws ServiceNotFoundException
     * @throws ConnectConsulException
     */
    public void stateServerGroup(String appId, MgmServerGroupStateBody mgmServerGroupStateBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        if (appId == null || appId.length() == 0) {
            logger.info("运行状态变更（服务应用）app_id值空");
            return;
        }
        if (mgmServerGroupStateBody == null) {
            logger.info("运行状态变更（服务应用）state值空");
            return;
        }

        String endpoint = "/{0}/manager/apps/{1}/state";
        endpoint = MessageFormat.format(endpoint, "v1.0", appId);

        logger.info("服务应用运行状态变更：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "PUT");
        if (mgmServerGroupStateBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmServerGroupStateBody));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmServerGroupStateBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }

    }
    
    /**
     * 服务应用单元运行状态变更
     * @param appId
     * @param unitId
     * @param mgmServerGroupStateBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void stateServerGroupUnit(String appId, String unitId, MgmServerGroupStateBody mgmServerGroupStateBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        if (appId == null || appId.length() == 0) {
            logger.info("运行状态变更（服务应用单元）app_id值空");
            return;
        }
        if (mgmServerGroupStateBody == null) {
            logger.info("运行状态变更（服务应用单元）state值空");
            return;
        }

        String endpoint = "/{0}/manager/apps/{1}/units/{2}/state";
        endpoint = MessageFormat.format(endpoint, "v1.0", appId, unitId);

        logger.info("服务应用单元运行状态变更：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "PUT");
        if (mgmServerGroupStateBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmServerGroupStateBody));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmServerGroupStateBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }

    }

    /**
     * 服务应用资源请求变更
     * 
     * @param appId
     * @param mgmServerGroupResourceRequestsBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public MgmSaveServerGroup resourceRequestsServerGroup(String appId,
            MgmServerGroupResourceRequestsBody mgmServerGroupResourceRequestsBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        if (appId == null || appId.length() == 0) {
            logger.info("资源请求变更（服务应用）app_id值空");
            MgmSaveServerGroup mgmSaveServerGroup=new MgmSaveServerGroup();
            mgmSaveServerGroup.setId("11111111111111111111111111111");
            mgmSaveServerGroup.setName("test");
            mgmSaveServerGroup.setTaskId("taskId");
            return mgmSaveServerGroup;
        }
        if (mgmServerGroupResourceRequestsBody == null) {
            logger.info("资源请求变更（服务应用）请求内容空");
            MgmSaveServerGroup mgmSaveServerGroup=new MgmSaveServerGroup();
            mgmSaveServerGroup.setId("11111111111111111111111111111");
            mgmSaveServerGroup.setName("test");
            mgmSaveServerGroup.setTaskId("taskId");
            return mgmSaveServerGroup;
        }

        String endpoint = "/{0}/manager/apps/{1}/resource/requests";
        endpoint = MessageFormat.format(endpoint, "v1.0", appId);

        logger.info("服务应用资源请求变更：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "PUT");
        if (mgmServerGroupResourceRequestsBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmServerGroupResourceRequestsBody));
        }

        if (Consts.TEST_FLAG) {
        	MgmSaveServerGroup mgmSaveServerGroup=new MgmSaveServerGroup();
            mgmSaveServerGroup.setId("11111111111111111111111111111");
            mgmSaveServerGroup.setName("test");
            mgmSaveServerGroup.setTaskId("taskId");
            return mgmSaveServerGroup;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmServerGroupResourceRequestsBody,
                    SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            return JSONObject.parseObject(httpResp.getResponseContent(), MgmSaveServerGroup.class);
        }
    }
    
    /**
     * 服务应用备份
     * @param appId
     * @param mgmServerGroupBackupBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void backupServerGroup(String appId,
    		MgmServerGroupBackupBody mgmServerGroupBackupBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
    	if (appId == null || appId.length() == 0) {
            logger.info("备份（服务应用）app_id值空");
            throw new CallingInterfaceException("备份（服务应用）app_id值空");
        }
        if (mgmServerGroupBackupBody == null) {
            logger.info("备份（服务应用）state值空");
            throw new CallingInterfaceException("备份（服务应用）app_id值空");
        }

        String endpoint = "/{0}/manager/backup/strategy";
        endpoint = MessageFormat.format(endpoint, "v1.0");

        logger.info("服务应用备份：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "POST");
        if (mgmServerGroupBackupBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmServerGroupBackupBody));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmServerGroupBackupBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPostRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_CREATED) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }

    /**
     * 服务单元备份
     * @param appId
     * @param mgmServerGroupBackupBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void backupServerGroupUnit(String appId,
    		MgmServerGroupUnitBackupBody mgmServerGroupUnitBackupBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
    	if (appId == null || appId.length() == 0) {
            logger.info("备份（服务单元）app_id值空");
            throw new CallingInterfaceException("备份（服务单元）app_id值空");
        }
        if (mgmServerGroupUnitBackupBody == null) {
            logger.info("备份（服务单元）state值空");
            throw new CallingInterfaceException("备份（服务单元）app_id值空");
        }

        String endpoint = "/{0}/manager/backup/strategy";
        endpoint = MessageFormat.format(endpoint, "v1.0");

        logger.info("服务单元备份：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "POST");
        if (mgmServerGroupUnitBackupBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmServerGroupUnitBackupBody));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmServerGroupUnitBackupBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPostRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_CREATED) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    /**
     * 服务单元重建
     * @param appId
     * @param mgmServerGroupBackupBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void rebulidServerGroupUnit(String appId,String unitId,
    		MgmServerGroupUnitRebulidBody mgmServerGroupUnitRebulidBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
    	if (appId == null || appId.length() == 0) {
            logger.info("重建（服务单元）app_id值空");
            throw new CallingInterfaceException("重建（服务单元）app_id值空");
        }
        if (mgmServerGroupUnitRebulidBody == null) {
            logger.info("重建（服务单元）state值空");
            throw new CallingInterfaceException("重建（服务单元）app_id值空");
        }
        String endpoint = "/{0}/manager/apps/{1}/units/{2}/rebuild";
//        String endpoint = "/{0}/manager/backup/strategy";
        endpoint = MessageFormat.format(endpoint, "v1.0",appId,unitId);

        logger.info("服务单元重建：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "PUT");
        if (mgmServerGroupUnitRebulidBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmServerGroupUnitRebulidBody));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
        	String bodyJsonStr;
        	if(mgmServerGroupUnitRebulidBody.getNode()!=null) {
        		bodyJsonStr = JSONObject.toJSONString(mgmServerGroupUnitRebulidBody, SerializerFeature.WriteMapNullValue);
        	}else {
        		MgmServerGroupUnitRebulidBody2 mgmServerGroupUnitRebulidBody2=new MgmServerGroupUnitRebulidBody2();
        		bodyJsonStr = JSONObject.toJSONString(mgmServerGroupUnitRebulidBody2, SerializerFeature.WriteMapNullValue);
        	}
            HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    /**
     * 服务单元角色变更
     * @param appId
     * @param mgmServerGroupUnitRoleChangeBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void roleChangeServerGroupUnit(String appId,
    		MgmServerGroupUnitRoleChangeBody mgmServerGroupUnitRoleChangeBody)
    				throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
    	if (appId == null ) {
    		logger.info("角色变更（服务单元）app_id值空");
    		throw new CallingInterfaceException("角色变更（服务单元）app_id值空");
    	}
    	if (mgmServerGroupUnitRoleChangeBody == null) {
    		logger.info("角色变更（服务单元）state值空");
    		throw new CallingInterfaceException("角色变更（服务单元）app_id值空");
    	}
    	String endpoint = "/{0}/manager/apps/{1}/role";
    	endpoint = MessageFormat.format(endpoint, "v1.0",appId);
    	
    	logger.info("服务单元角色变更：");
    	logger.info("Http Request URL: {}", endpoint);
    	logger.info("Http Request Method: {}", "PUT");
    	if (mgmServerGroupUnitRoleChangeBody != null) {
    		logger.info("Http Request Data: {}", JSON.toJSON(mgmServerGroupUnitRoleChangeBody));
    	}
    	
    	if (Consts.TEST_FLAG) {
    		return;
    	} else {
    		String bodyJsonStr;
    		bodyJsonStr = JSONObject.toJSONString(mgmServerGroupUnitRoleChangeBody, SerializerFeature.WriteMapNullValue);
    		HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
    		if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
    			throw new CallingInterfaceException(httpResp.getResponseContent());
    		}
//    		return;
    	}
    }
    
    public void roleChangeSlaveServerGroupUnit(String appId,String slaveId,
    		MgmServerGroupUnitRoleChangeSlaveBody mgmServerGroupUnitRoleChangeBody)
    				throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
    	if (appId == null ) {
    		logger.info("角色变更切换为从（服务单元）app_id值空");
    		throw new CallingInterfaceException("角色变更（服务单元）app_id值空");
    	}
    	if (mgmServerGroupUnitRoleChangeBody == null) {
    		logger.info("角色变更切换为从（服务单元）state值空");
    		throw new CallingInterfaceException("角色变更切换为从（服务单元）app_id值空");
    	}
    	String endpoint = "/{0}/manager/apps/{1}/units/{2}/role";
    	endpoint = MessageFormat.format(endpoint, "v1.0",appId,slaveId);
    	
    	logger.info("服务单元角色变更切换为从：");
    	logger.info("Http Request URL: {}", endpoint);
    	logger.info("Http Request Method: {}", "PUT");
    	if (mgmServerGroupUnitRoleChangeBody != null) {
    		logger.info("Http Request Data: {}", JSON.toJSON(mgmServerGroupUnitRoleChangeBody));
    	}
    	
    	if (Consts.TEST_FLAG) {
    		return;
    	} else {
    		String bodyJsonStr;
    		bodyJsonStr = JSONObject.toJSONString(mgmServerGroupUnitRoleChangeBody, SerializerFeature.WriteMapNullValue);
    		HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
    		if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
    			throw new CallingInterfaceException(httpResp.getResponseContent());
    		}
    	}
    }
    
    /**
     * 服务单元迁移
     * @param appId
     * @param mgmServerGroupBackupBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void migrateServerGroupUnit(String appId,String unitId,
    		MgmServerGroupUnitRebulidBody mgmServerGroupUnitRebulidBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
    	if (appId == null || appId.length() == 0) {
            logger.info("迁移（服务单元）app_id值空");
            throw new CallingInterfaceException("备份（服务单元）app_id值空");
        }
        if (mgmServerGroupUnitRebulidBody == null) {
            logger.info("迁移（服务单元）state值空");
            throw new CallingInterfaceException("迁移（服务单元）app_id值空");
        }
        String endpoint = "/{0}/manager/apps/{1}/units/{2}/migrate";
//        String endpoint = "/{0}/manager/backup/strategy";
        endpoint = MessageFormat.format(endpoint, "v1.0",appId,unitId);

        logger.info("服务单元迁移：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "PUT");
        if (mgmServerGroupUnitRebulidBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmServerGroupUnitRebulidBody));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
        	String bodyJsonStr;
        	if(mgmServerGroupUnitRebulidBody.getNode()!=null) {
        		bodyJsonStr = JSONObject.toJSONString(mgmServerGroupUnitRebulidBody, SerializerFeature.WriteMapNullValue);
        	}else {
        		MgmServerGroupUnitRebulidBody2 mgmServerGroupUnitRebulidBody2=new MgmServerGroupUnitRebulidBody2();
        		bodyJsonStr = JSONObject.toJSONString(mgmServerGroupUnitRebulidBody2, SerializerFeature.WriteMapNullValue);
        	}
            HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    
    /**
     * 服务应用删除
     * 
     * @param appId
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public int removeServerGroup(String appId)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
    	int status=0;
        if (appId == null || appId.length() == 0) {
            logger.info("删除（服务应用）app_id值空");
            return status;
        }
        String endpoint = "{0}/manager/apps/{1}";
        endpoint = MessageFormat.format(endpoint, "v1.0", appId);

        logger.info("服务应用删除：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "DELETE");
        
        
        
        if (Consts.TEST_FLAG) {
            return status;
        } else {
            HttpResp httpResp = sendDeleteRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_NO_CONTENT) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }else {
            	status=1;
            }
            return status;
        }
    }

    /**
     * 数据库服务查询
     * 
     * @param appId
     * @param mgmDatabaseQuery
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public List<MgmDatabase> listDatabase(String appId, MgmDatabaseQuery mgmDatabaseQuery)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String path = processPath(mgmDatabaseQuery);
        String endpoint = "/{0}/manager/apps/{1}/database/schemas" + path;
        endpoint = MessageFormat.format(endpoint, "v1.0", appId);

        logger.info("数据库服务查询：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (mgmDatabaseQuery != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmDatabaseQuery));
        }

        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("database");
            HttpResp httpResp = new HttpResp(200, jsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            List<MgmDatabase> result = JSONArray.parseArray(httpResp.getResponseContent(), MgmDatabase.class);

            return result;
        } else {
            HttpResp httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            return JSONArray.parseArray(httpResp.getResponseContent(), MgmDatabase.class);
        }
    }

    /**
     * 数据库服务详细查询
     * 
     * @param appId
     * @param schemaName
     * @param mgmDatabaseQuery
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public MgmDatabase detailDatabase(String appId, String schemaName, MgmDatabaseQuery mgmDatabaseQuery)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String path = processPath(mgmDatabaseQuery);
        String endpoint = "/{0}/manager/apps/{1}/database/schemas/{2}" + path;
        endpoint = MessageFormat.format(endpoint, "v1.0", appId, schemaName);

        logger.info("数据库服务详细查询：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (mgmDatabaseQuery != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmDatabaseQuery));
        }

        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("database");
            HttpResp httpResp = new HttpResp(200, jsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            List<MgmDatabase> result = JSONArray.parseArray(httpResp.getResponseContent(), MgmDatabase.class);
            if (schemaName != null) {
                for (MgmDatabase mgmDatabase : result) {
                    if (schemaName.equals(mgmDatabase.getName())) {
                        return mgmDatabase;
                    }
                }
            }

            return null;
        } else {
            HttpResp httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            return JSONArray.parseObject(httpResp.getResponseContent(), MgmDatabase.class);
        }
    }

    /**
     * 数据库服务新增
     * 
     * @param appId
     * @param mgmDatabaseBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void saveDatabase(String appId, MgmDatabaseBody mgmDatabaseBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/apps/{1}/database/schemas";
        endpoint = MessageFormat.format(endpoint, "v1.0", appId);

        logger.info("数据库服务保存：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "POST");
        if (mgmDatabaseBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmDatabaseBody));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmDatabaseBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPostRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_CREATED) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }

    /**
     * 数据库服务删除
     * 
     * @param appId
     * @param schemaName
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void removeDatabase(String appId, String schemaName)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/apps/{1}/database/schemas/{2}";
        endpoint = MessageFormat.format(endpoint, "v1.0", appId, schemaName);

        logger.info("数据库服务删除：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "DELETE");

        if (Consts.TEST_FLAG) {
            return;
        } else {
            HttpResp httpResp = sendDeleteRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_NO_CONTENT) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }

    /**
     * 
     * 服务新增
     * 
     * @param mgmServerGroupBody
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     * @return MgmSaveServerGroup
     */
    public MgmSaveServerGroup saveServerGroup(MgmServerGroupBody mgmServerGroupBody,String subscription_id)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/apps";
        if(subscription_id!=null && !"".equals(subscription_id)) {
        	endpoint = endpoint + "?subscription_id=" + subscription_id;
        }
        endpoint = MessageFormat.format(endpoint, "v1.0");
        if (Consts.TEST_FLAG) {
            logger.info("Http Request URL: {}", endpoint);
            logger.info("Http Request Method: {}", "POST");
            if (mgmServerGroupBody != null) {
                logger.info("Http Request Data: {}", JSON.toJSON(mgmServerGroupBody));
            }
            MgmSaveServerGroup saveServerGroup = new MgmSaveServerGroup();
            saveServerGroup.setId("11111111111111111111111111111");
            saveServerGroup.setName("test");
            saveServerGroup.setTaskId("taskId");
            return saveServerGroup;
        } else {
        	logger.info("Http Request URL: {}", endpoint);
            logger.info("Http Request Method: {}", "POST");
            if (mgmServerGroupBody != null) {
                logger.info("Http Request Data: {}", JSON.toJSON(mgmServerGroupBody));
            }
            String bodyJsonStr = JSONObject.toJSONString(mgmServerGroupBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPostRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_CREATED) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            return JSONObject.parseObject(httpResp.getResponseContent(), MgmSaveServerGroup.class);
        	/*logger.info("Http Request URL: {}", endpoint);
            logger.info("Http Request Method: {}", "POST");
            if (mgmServerGroupBody != null) {
                logger.info("Http Request Data: {}", JSON.toJSON(mgmServerGroupBody));
            }
        	MgmSaveServerGroup saveServerGroup = new MgmSaveServerGroup();
            saveServerGroup.setId("11111111111111111111111111111");
            saveServerGroup.setName("test");
            saveServerGroup.setTaskId("taskId");
            return saveServerGroup;*/
        }
    }
    
    /**
     * 用户保存
     * @param mgmUserBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void saveUser(String appId, MgmServerGroupUserBody mgmUserBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/apps/{1}/database/users";
        endpoint = MessageFormat.format(endpoint, "v1.0", appId);

        logger.info("用户保存：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "POST");
        if (mgmUserBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmUserBody));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmUserBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPostRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_CREATED) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    /**
     * 用户编辑
     * @param userId
     * @param mgmUserBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void updateUser(String appId, MgmServerGroupUpdateUserBody mgmUserBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/apps/{1}/database/users/privileges";
        endpoint = MessageFormat.format(endpoint, "v1.0", appId);

        logger.info("用户编辑：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "PUT");
        if (mgmUserBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmUserBody));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmUserBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
//        	return;
        }
    }
    
    public void changeUserPwd(String appId, ServerGroupChangeUserPwdForm mgmUserBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/apps/{1}/database/users/pwd";
        endpoint = MessageFormat.format(endpoint, "v1.0", appId);

        logger.info("用户密码重置：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "PUT");
        if (mgmUserBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmUserBody));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmUserBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
//        	return;
        }
    }

    /**
     * 用户查询
     * @param mgmUserQuery
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public List<MgmUser> listUser(String appId, MgmUserQuery mgmUserQuery)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String path = processPath(mgmUserQuery);
        String endpoint = "{0}/manager/apps/{1}/database/users" + path;
        endpoint = MessageFormat.format(endpoint, "v1.0", appId);

        logger.info("用户查询：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (mgmUserQuery != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmUserQuery));
        }

        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("users");
            HttpResp httpResp = new HttpResp(200, jsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            List<MgmUser> result = JSONArray.parseArray(httpResp.getResponseContent(), MgmUser.class);

            /*String id = mgmUserQuery.getId();
            if (id != null) {
                List<MgmSite> temp = new ArrayList<>();
                for (MgmSite mgmSite : result) {
                    if (id.equals(mgmSite.getId())) {
                        temp.add(mgmSite);
                        break;
                    }
                }
                result = temp;
            }*/
            return result;
        } else {
            HttpResp httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            return JSONArray.parseArray(httpResp.getResponseContent(), MgmUser.class);
        }
    }
    
    /**
     * 用户详情查询
     * @param mgmUserQuery
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public MgmUser userDetail(String appId, String username,String ip)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/apps/{1}/database/users/{2}?ip={3}";
        String username1=URLEncoder.encode(username,"UTF-8");
        String ip1=URLEncoder.encode(ip,"UTF-8");
        endpoint = MessageFormat.format(endpoint, "v1.0", appId,username1,ip1);
        logger.info("用户详情查询：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (ip != null) {
            logger.info("Http Request Data: {}", ip);
        }
        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("users");
            HttpResp httpResp = new HttpResp(200, jsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            MgmUser result = (MgmUser) JSONArray.parseArray(httpResp.getResponseContent(), MgmUser.class);
            return result;
        } else {
            HttpResp httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            return  JSONArray.parseObject(httpResp.getResponseContent(), MgmUser.class);
        }
    }
    
    /**
     * 用户删除
     * @param userId
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void removeUser(String appId, String userName, String ip)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/apps/{1}/database/users/{2}?ip={3}";
        String username=URLEncoder.encode(userName,"UTF-8");
        String ipp=URLEncoder.encode(ip,"UTF-8");
        endpoint = MessageFormat.format(endpoint, "v1.0", appId, username, ipp);
        logger.info("用户删除：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "DELETE");

        if (Consts.TEST_FLAG) {
            return;
        } else {
            HttpResp httpResp = sendDeleteRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_NO_CONTENT) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }

    /**
     * 数据库服务备份文件查询
     * @param mgmBackupFileQuery
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public List<MgmBackupFile> listBackupFile(MgmBackupFileQuery mgmBackupFileQuery)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String path = processPath(mgmBackupFileQuery);
        String endpoint = "/{0}/manager/backup/files" + path;
        endpoint = MessageFormat.format(endpoint, "v1.0");

        logger.info("数据库备份文件查询：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (mgmBackupFileQuery != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmBackupFileQuery));
        }

        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("backupfile");
            HttpResp httpResp = new HttpResp(200, jsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            List<MgmBackupFile> result = JSONArray.parseArray(httpResp.getResponseContent(), MgmBackupFile.class);

            String id = mgmBackupFileQuery.getId();
            if (id != null) {
                List<MgmBackupFile> temp = new ArrayList<>();
                for (MgmBackupFile mgmBackupFile : result) {
                    if (id.equals(mgmBackupFile.getId())) {
                        temp.add(mgmBackupFile);
                        break;
                    }
                }
                result = temp;
            }
            return result;
        } else {
            HttpResp httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            return JSONArray.parseArray(httpResp.getResponseContent(), MgmBackupFile.class);
        }
    }
    
    /**
     * 备份存储查询
     * @param mgmBackupFileQuery
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public List<MgmBackupFile> listBackupEndpoint(MgmBackupFileQuery mgmBackupFileQuery)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String path = processPath(mgmBackupFileQuery);
        String endpoint = "/{0}/manager/backup/endpoint" + path;
        endpoint = MessageFormat.format(endpoint, "v1.0");

        logger.info("备份存储查询：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (mgmBackupFileQuery != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmBackupFileQuery));
        }

        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("backupfile");
            HttpResp httpResp = new HttpResp(200, jsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            List<MgmBackupFile> result = JSONArray.parseArray(httpResp.getResponseContent(), MgmBackupFile.class);

            String id = mgmBackupFileQuery.getId();
            if (id != null) {
                List<MgmBackupFile> temp = new ArrayList<>();
                for (MgmBackupFile mgmBackupFile : result) {
                    if (id.equals(mgmBackupFile.getId())) {
                        temp.add(mgmBackupFile);
                        break;
                    }
                }
                result = temp;
            }
            return result;
        } else {
            HttpResp httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            return JSONArray.parseArray(httpResp.getResponseContent(), MgmBackupFile.class);
        }
    }
    
    /**
     * 数据库服务备份策略查询
     * @param mgmBackupFileQuery
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public List<MgmBackupStrategy> listStrategyFile(MgmBackupFileQuery mgmBackupFileQuery)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String path = processPath(mgmBackupFileQuery);
        String endpoint = "/{0}/manager/backup/strategy" + path;
        endpoint = MessageFormat.format(endpoint, "v1.0");

        logger.info("数据库备份策略查询：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (mgmBackupFileQuery != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmBackupFileQuery));
        }

        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("backupfile");
            HttpResp httpResp = new HttpResp(200, jsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            List<MgmBackupStrategy> result = JSONArray.parseArray(httpResp.getResponseContent(), MgmBackupStrategy.class);

            String id = mgmBackupFileQuery.getId();
            if (id != null) {
                List<MgmBackupStrategy> temp = new ArrayList<>();
                for (MgmBackupStrategy mgmBackupFile : result) {
                    if (id.equals(mgmBackupFile.getId())) {
                        temp.add(mgmBackupFile);
                        break;
                    }
                }
                result = temp;
            }
            return result;
        } else {
            HttpResp httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            return JSONArray.parseArray(httpResp.getResponseContent(), MgmBackupStrategy.class);
        }
    }
    
    /**
     * 数据库服务备份文件还原
     * @param mgmBackupFileQuery
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void restoreBackupFile(String appId,String unitId,MgmBackupRestoreFile mgmBackupFileQuery)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "/{0}/manager/apps/{1}/units/{2}/restore";
        endpoint = MessageFormat.format(endpoint, "v1.0",appId,unitId);

        logger.info("数据库备份还原：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "PUT");
        if (mgmBackupFileQuery != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmBackupFileQuery));
        }

        if (Consts.TEST_FLAG) {
        	
        } else {
        	String bodyJsonStr = JSONObject.toJSONString(mgmBackupFileQuery, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    /**
     * 数据库服务备份文件删除
     * @param backupFileId
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void removeBackupFile(String backupFileId)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/backup/files?id={1}";
        endpoint = MessageFormat.format(endpoint, "v1.0", backupFileId);

        logger.info("数据库服务备份文件删除：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "DELETE");

        if (Consts.TEST_FLAG) {
            return;
        } else {
            HttpResp httpResp = sendDeleteRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_NO_CONTENT) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    /**
     * 数据库服务备份策略新增
     * 
     * @param appId
     * @param mgmDatabaseBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void saveBackupStrategy(MgmBackupStrategyBody mgmStrategyBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/backup/strategy";
        endpoint = MessageFormat.format(endpoint, "v1.0");

        logger.info("数据库服务备份策略新增：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "POST");
        if (mgmStrategyBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmStrategyBody));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmStrategyBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPostRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_CREATED) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    /**
     * 数据库服务备份策略删除
     * @param backupFileId
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void removeStrategyFile(String backupStrategyId)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/backup/strategy?id={1}";
        endpoint = MessageFormat.format(endpoint, "v1.0", backupStrategyId);

        logger.info("数据库服务备份策略删除：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "DELETE");

        if (Consts.TEST_FLAG) {
            return;
        } else {
            HttpResp httpResp = sendDeleteRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_NO_CONTENT) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    
    /**
     * 备份存储新增
     * 
     * @param backupEndpointParam
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void saveBackupEndpoint(BackupEndpointParam backupEndpointParam)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/backup/endpoint";
        endpoint = MessageFormat.format(endpoint, "v1.0");

        logger.info("数据库服务备份策略新增：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "POST");
        if (backupEndpointParam != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(backupEndpointParam));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(backupEndpointParam, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPostRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_CREATED) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    /**
     * 备份存储修改
     * 
     * @param backupEndpointParam
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void updateBackupEndpoint(String id ,BackupEndpointParam backupEndpointParam)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/backup/endpoint/{1}";
        endpoint = MessageFormat.format(endpoint, "v1.0", id);

        logger.info("备份存储修改：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "PUT");
        if (backupEndpointParam != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(backupEndpointParam));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(backupEndpointParam, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    /**
     * 备份存储删除
     * @param backupFileId
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void removeBackupEndpoint(String id)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/backup/endpoint/{1}";
        endpoint = MessageFormat.format(endpoint, "v1.0", id);

        logger.info("备份存储删除：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "DELETE");

        if (Consts.TEST_FLAG) {
            return;
        } else {
            HttpResp httpResp = sendDeleteRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_NO_CONTENT) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    /**
     * 读取json文件，返回json串
     * 
     * @param fileName
     * @return
     */
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        Reader reader = null;
        ClassPathResource rs = new ClassPathResource("com\\bsg\\upm\\mgm\\api\\test\\json\\" + fileName + ".json");
        try {
        	InputStream inputStream = rs.getInputStream();
            reader = new InputStreamReader(inputStream, "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally{
        	try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    public static String processPath(Object object) {
        String path = "?";
        for (Field field : object.getClass().getDeclaredFields()) {
            JSONField jsonField = field.getAnnotation(JSONField.class);
            if (jsonField != null) {
                field.setAccessible(true);
                Object fieldValue = getFieldValueByName(field.getName(), object);
                if (fieldValue != null) {
                    path += jsonField.name() + "=" + fieldValue + "&";
                }
            }
        }
        if (path == "?") {
            path = "";
        } else {
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }

    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            if (fieldName.equals("serialVersionUID")) {
                return null;
            }
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            if (value == null) {
                return null;
            }
            return value;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 数据库服务查询
     * 
     * @param appId
     * @param mgmDatabaseQuery
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public String listTask(MgmSaveServerGroup MgmSaveServerGroup)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String path = processPath(MgmSaveServerGroup);
        String endpoint = "/{0}/manager/tasks" + path;
        endpoint = MessageFormat.format(endpoint, "v1.0", path);

        logger.info("数据库服务查询：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
       /* if (mgmDatabaseQuery != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmDatabaseQuery));
        }*/

        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("database");
            HttpResp httpResp = new HttpResp(200, jsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            List<MgmDatabase> result = JSONArray.parseArray(httpResp.getResponseContent(), MgmDatabase.class);
            System.out.println(result.toString());
            return "1";
        } else {
        	String status="999";
            HttpResp httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
            List<MgmTask> result = JSONArray.parseArray(httpResp.getResponseContent(), MgmTask.class);
            if(result!=null&&result.size()!=0) {
            	MgmTask mgmTask=result.get(0);
                if(mgmTask.getStatus().equals(DictConsts.ORDER_STATUS_SUCCESS)) {
                	status= "5";
                }
                if(mgmTask.getStatus().equals(DictConsts.COMPARE_ORDER_STATUS_FAILED)) {
                	status= "4";
                }
                if(mgmTask.getStatus().equals(DictConsts.STATUS_RUNNING)) {
                	status= "3";
                }
            }
            
            return status;
        }
    }
    /**
     * 数据库服务查询
     * 
     * @param appId
     * @param mgmDatabaseQuery
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public List<MgmTask> listTasks(MgmTask mgmTask)
    		throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
    	String path = processPath(mgmTask);
    	String endpoint = "/{0}/manager/tasks" + path;
    	endpoint = MessageFormat.format(endpoint, "v1.0", path);
    	
    	logger.info("数据库服务任务查询：");
    	logger.info("Http Request URL: {}", endpoint);
    	logger.info("Http Request Method: {}", "GET");
    	/* if (mgmDatabaseQuery != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmDatabaseQuery));
        }*/
    	
    	if (Consts.TEST_FLAG) {
    		String jsonStr = readJsonFile("database");
    		HttpResp httpResp = new HttpResp(200, jsonStr);
    		if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
    			throw new CallingInterfaceException(httpResp.getResponseContent());
    		}
    		return JSONArray.parseArray(httpResp.getResponseContent(), MgmTask.class);
    	} else {
    		HttpResp httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
    		if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
    			throw new CallingInterfaceException(httpResp.getResponseContent());
    		}
    		return JSONArray.parseArray(httpResp.getResponseContent(), MgmTask.class);
    	}
		
    }
    
    /**
     * 
     * 单元参数配置查询
     * @param imageId
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public List<KeySet> UnitCfgsConf(String servGroupId)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
//    	String path = processPath(image);
        String endpoint = "{0}/manager/apps/{1}/config";
        endpoint = MessageFormat.format(endpoint, "v1.0",servGroupId);
        /*
    	String endpoint = "{0}/manager/images/templates/{1}";
        endpoint = MessageFormat.format(endpoint, "v1.0", imageId);*/
        HttpResp httpResp;
        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("imageConf");
            httpResp = new HttpResp(200, jsonStr);
        } else {
            httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
        }
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (servGroupId != null) {
            logger.info("Http Request Data: {}", servGroupId);
        }
        if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
            throw new CallingInterfaceException(httpResp.getResponseContent());
        }
        return JSONArray.parseArray(httpResp.getResponseContent(), KeySet.class);
    }
    
    /** 服务配置文件编辑
     * @param appId
     * @param mgmServerGroupCfgsBody
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void updateCfgs(String appId, MgmServerGroupCfgsBody mgmServerGroupCfgsBody)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "{0}/manager/apps/{1}/config";
        endpoint = MessageFormat.format(endpoint, "v1.0", appId);
        logger.info("配置文件编辑：");
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "PUT");
        if (mgmServerGroupCfgsBody != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(mgmServerGroupCfgsBody));
        }

        if (Consts.TEST_FLAG) {
            return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(mgmServerGroupCfgsBody, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    public HostMonitorParam getHostMemMonitor(String hostId,String type,HostMonitorQueryParam hostMonitorQueryParam)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
//    	v1/hosts/{host_id}/metrics/host.linux.mem.usage?start_time=1571287938&end_time=1571291811
    	String path = processPath(hostMonitorQueryParam);
        String endpoint = "v1/hosts/{0}/metrics/{1}" +path;
        if(type.equals("cpu_avg_usage")) {
        	type=DictConsts.HOST_MONITOR_TYPE_CPU;
        }else if(type.equals("mem_usage")) {
        	type=DictConsts.HOST_MONITOR_TYPE_MEM;
        }
        endpoint = MessageFormat.format(endpoint, hostId,type);
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (hostId != null||type!=null) {
            logger.info("Http Request Data: {}","hostId:"+ hostId+";type:"+type);
        }
        HttpResp httpResp;
        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("getHostMemMonitor");
            httpResp = new HttpResp(200, jsonStr);
        } else {
        	httpResp = sendMonitorGetRequest(InterfaceEnum.MGM, endpoint);
        }
        if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
            throw new CallingInterfaceException(httpResp.getResponseContent());
        }
        return  JSONArray.parseObject(httpResp.getResponseContent(), HostMonitorParam.class);
    }
    
    
    /**  查询服务单元是否注册过
     * @param hostIp
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void getUnitMonitor(String unitId)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "v1/instances/{0}/info";
        endpoint = MessageFormat.format(endpoint, unitId);
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        logger.info("请求次数", "1");
        if (unitId != null) {
            logger.info("Http Request Data: {}", unitId);
        }
        HttpResp httpResp;
        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("getUnitMonitor");
            httpResp = new HttpResp(200, jsonStr);
        } else {
            httpResp = sendMonitorGetRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    /** 服务单元监控注册
     * @param serveUnitRegisParam
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void unitMonitorRegister(ServeUnitRegisParam serveUnitRegisParam)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        String endpoint = "v1/instances";
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "POST");
        if (serveUnitRegisParam != null) {
        	logger.info("Http Request Data: {}", JSON.toJSON(serveUnitRegisParam));
        }
        if (Consts.TEST_FLAG) {
        	return;
        } else {
            String bodyJsonStr = JSONObject.toJSONString(serveUnitRegisParam, SerializerFeature.WriteMapNullValue);
            HttpResp httpResp = sendMonitorPostRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
            if (httpResp.getStatusCode() != HttpStatus.SC_CREATED) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    /** 服务单元监控注销
     * @param hostId
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public void unitMonitorCancel(String hostId)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        
        ServMonitorCancelParam servMonitorCancelParam=new ServMonitorCancelParam();
        servMonitorCancelParam.setDelContainer(true);
        String path = processPath(servMonitorCancelParam);
        String endpoint = "v1/instances/{0}" +path;
        endpoint = MessageFormat.format(endpoint, hostId);
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "DELETE");
        if (hostId != null) {
        	logger.info("Http Request Data: {}", hostId);
        }
        if (Consts.TEST_FLAG) {
        	return;
        } else {
            HttpResp httpResp = sendMonitorDeleteRequest(InterfaceEnum.MGM, endpoint);
            if (httpResp.getStatusCode() != HttpStatus.SC_NO_CONTENT) {
                throw new CallingInterfaceException(httpResp.getResponseContent());
            }
        }
    }
    
    /**  根据指定实例查询单元监控数据
     * @param instanceName
     * @param hostMonitorQueryParam
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public MgmGetUnitMonitorParam getUnitMonitorByInstanceName(String instanceName,String relateCode,HostMonitorQueryParam hostMonitorQueryParam)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
//    	relateCode=URLEncoder.encode(relateCode,"UTF-8");
    	String path = processPath(hostMonitorQueryParam);
//    	instances/{instance_name}/metrics/instance.mysql.buffer_pool.hit?start_time=1571287938&end_time=1571291811
        String endpoint = "v1/instances/{0}/metrics/{1}" +path;
        endpoint = MessageFormat.format(endpoint, instanceName,relateCode);
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (instanceName != null) {
            logger.info("Http Request Data: {}","instanceName:"+ instanceName);
        }
        HttpResp httpResp;
        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("getUnitMonitorByInstanceName");
            httpResp = new HttpResp(200, jsonStr);
        } else {
        	httpResp = sendMonitorGetRequest(InterfaceEnum.MGM, endpoint);
        }
        if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
            throw new CallingInterfaceException(httpResp.getResponseContent());
        }
        return  JSONArray.parseObject(httpResp.getResponseContent(), MgmGetUnitMonitorParam.class);
    }
    
    public MgmGetUnitMonitorStringParam getUnitMonitorByInstanceNameString(String instanceName,String relateCode,HostMonitorQueryParam hostMonitorQueryParam)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
//    	relateCode=URLEncoder.encode(relateCode,"UTF-8");
    	String path = processPath(hostMonitorQueryParam);
//    	instances/{instance_name}/metrics/instance.mysql.buffer_pool.hit?start_time=1571287938&end_time=1571291811
        String endpoint = "v1/instances/{0}/metrics/{1}" +path;
        endpoint = MessageFormat.format(endpoint, instanceName,relateCode);
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (instanceName != null) {
            logger.info("Http Request Data: {}","instanceName:"+ instanceName);
        }
        HttpResp httpResp;
        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("getUnitMonitorByInstanceName");
            httpResp = new HttpResp(200, jsonStr);
        } else {
        	httpResp = sendMonitorGetRequest(InterfaceEnum.MGM, endpoint);
        }
        if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
            throw new CallingInterfaceException(httpResp.getResponseContent());
        }
        return  JSONArray.parseObject(httpResp.getResponseContent(), MgmGetUnitMonitorStringParam.class);
    }
    
    /**根据指定容器查询单元监控数据
     * @param instanceName
     * @param hostMonitorQueryParam
     * @return
     * @throws ConnectConsulException
     * @throws ServiceNotFoundException
     * @throws CallingInterfaceException
     * @throws IOException
     */
    public MgmGetUnitMonitorParam getUnitMonitorByContainerName(String containerName,String relateCode,HostMonitorQueryParam hostMonitorQueryParam)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
//    	relateCode=URLEncoder.encode(relateCode,"UTF-8");
    	String path = processPath(hostMonitorQueryParam);
//    	instances/{instance_name}/metrics/instance.mysql.buffer_pool.hit?start_time=1571287938&end_time=1571291811
        String endpoint = "v1/containers/{0}/metrics/{1}" +path;
        endpoint = MessageFormat.format(endpoint, containerName,relateCode);
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (containerName != null) {
            logger.info("Http Request Data: {}","containerName:"+ containerName);
        }
        HttpResp httpResp;
        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("getUnitMonitorByContainerName");
            httpResp = new HttpResp(200, jsonStr);
        } else {
        	httpResp = sendMonitorGetRequest(InterfaceEnum.MGM, endpoint);
        }
        if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
            throw new CallingInterfaceException(httpResp.getResponseContent());
        }
        return  JSONArray.parseObject(httpResp.getResponseContent(), MgmGetUnitMonitorParam.class);
    }
    
    public  List<MgmService> getService(String MgmServiceId) throws Exception {
        MgmServiceQuery serviceQuery = new MgmServiceQuery();
        serviceQuery.setId(MgmServiceId);
//        List<MgmService> MgmServices = listService(serviceQuery);
        String path = processPath(MgmServiceId);
        String endpoint = "/services" + path;
        endpoint = MessageFormat.format(endpoint, "v1.0");
        
        /*
    	String endpoint = "{0}/manager/images/templates/{1}";
        endpoint = MessageFormat.format(endpoint, "v1.0", imageId);*/
        HttpResp httpResp;
        if (Consts.TEST_FLAG) {
            String jsonStr = readJsonFile("imageConf");
            httpResp = new HttpResp(200, jsonStr);
        } else {
            httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
        }
        logger.info("Http Request URL: {}", endpoint);
        logger.info("Http Request Method: {}", "GET");
        if (MgmServiceId != null) {
            logger.info("Http Request Data: {}", JSON.toJSON(MgmServiceId));
        }
        if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
            throw new CallingInterfaceException(httpResp.getResponseContent());
        }
		return null;
    }
        
        /**
         * 拓扑查询
         * @param mgmServerGroupDetailQuery
         * @return
         * @throws ConnectConsulException
         * @throws ServiceNotFoundException
         * @throws CallingInterfaceException
         * @throws IOException
         */
        public MgmTopology getTopology(String appId,MgmServerGroupDetailQuery mgmServerGroupDetailQuery)
                throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
            String path = processPath(mgmServerGroupDetailQuery);
            String endpoint = "{0}/manager/apps/{1}/topology" + path;
            endpoint = MessageFormat.format(endpoint, "v1.0",appId);

            logger.info("拓扑查询：");
            logger.info("Http Request URL: {}", endpoint);
            logger.info("Http Request Method: {}", "GET");
            if (mgmServerGroupDetailQuery != null) {
                logger.info("Http Request Data: {}", JSON.toJSON(mgmServerGroupDetailQuery));
            }

            if (Consts.TEST_FLAG) {
                String jsonStr = readJsonFile("servergroupdetail");
                HttpResp httpResp = new HttpResp(200, jsonStr);
                if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                    throw new CallingInterfaceException(httpResp.getResponseContent());
                }

                MgmTopology result = JSONObject.parseObject(httpResp.getResponseContent(), MgmTopology.class);
                return result;
            } else {
                HttpResp httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
                if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                    throw new CallingInterfaceException(httpResp.getResponseContent());
                }
                return JSONObject.parseObject(httpResp.getResponseContent(), MgmTopology.class);
            }
        }
        
        /**
         * 
         * 复制模式设置
         * 
         * @param clusterId
         * @param mgmClusterBody
         * @throws ConnectConsulException
         * @throws ServiceNotFoundException
         * @throws CallingInterfaceException
         * @throws IOException
         * @return void
         */
        public void updateReplicationMode(String appId,MgmServerGroupDetailQuery mgmServerGroupDetailQuery,MgmBaseBody mgmBaseBody)
                throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        	String path = processPath(mgmServerGroupDetailQuery);
            String endpoint = "{0}/manager/apps/{1}/replication/semi_sync";
            endpoint = MessageFormat.format(endpoint, "v1.0",appId);
            if (Consts.TEST_FLAG) {
                logger.info("Http Request URL: {}", endpoint);
                logger.info("Http Request Method: {}", "PUT");
                if (mgmBaseBody != null) {
                    logger.info("Http Request Data: {}", JSON.toJSON(mgmBaseBody));
                }
                return;
            } else {
                String bodyJsonStr = JSONObject.toJSONString(mgmBaseBody, SerializerFeature.WriteMapNullValue);
                HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
                if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                    throw new CallingInterfaceException(httpResp.getResponseContent());
                }
            }
        }

		public void setSource(String appId,MgmServerGroupDetailQuery mgmServerGroupDetailQuery,MgmBaseBody mgmBaseBody)
                throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        	String path = processPath(mgmServerGroupDetailQuery);
            String endpoint = "{0}/manager/apps/{1}/replication/set_source" + path;
            endpoint = MessageFormat.format(endpoint, "v1.0",appId);
            if (Consts.TEST_FLAG) {
                logger.info("Http Request URL: {}", endpoint);
                logger.info("Http Request Method: {}", "PUT");
                if (mgmBaseBody != null) {
                    logger.info("Http Request Data: {}", JSON.toJSON(mgmBaseBody));
                }
                return;
            } else {
                String bodyJsonStr = JSONObject.toJSONString(mgmBaseBody, SerializerFeature.WriteMapNullValue);
                HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
                if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
                    throw new CallingInterfaceException(httpResp.getResponseContent());
                }
            }
        }
		public void maintenance(String appId,MgmServerGroupDetailQuery mgmServerGroupDetailQuery,MgmBaseBody mgmBaseBody)
				throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
			String path = processPath(mgmServerGroupDetailQuery);
			String endpoint = "{0}/manager/apps/{1}/maintenance" + path;
			endpoint = MessageFormat.format(endpoint, "v1.0",appId);
			if (Consts.TEST_FLAG) {
				logger.info("Http Request URL: {}", endpoint);
				logger.info("Http Request Method: {}", "PUT");
				if (mgmBaseBody != null) {
					logger.info("Http Request Data: {}", JSON.toJSON(mgmBaseBody));
				}
				return;
			} else {
				String bodyJsonStr = JSONObject.toJSONString(mgmBaseBody, SerializerFeature.WriteMapNullValue);
				HttpResp httpResp = sendPutRequest(InterfaceEnum.MGM, endpoint, bodyJsonStr);
				if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
					throw new CallingInterfaceException(httpResp.getResponseContent());
				}
			}
		}
		
		/**
	     * cm健康状态检查
	     * 
	     * @param podId
	     * @return 
	     * @throws ConnectConsulException
	     * @throws ServiceNotFoundException
	     * @throws CallingInterfaceException
	     * @throws IOException
	     */
	    public void getSites()
	            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
	        String endpoint = "/{0}/manager/sites";
	        endpoint = MessageFormat.format(endpoint,"v1.0");

	        logger.info("转发CM的sites：");
	        logger.info("Http Request URL: {}", endpoint);
	        logger.info("Http Request Method: {}", "GET");

	        if (Consts.TEST_FLAG) {
	            return ;
	        } else {
	            HttpResp httpResp = sendGetRequest(InterfaceEnum.MGM, endpoint);
	            if (httpResp.getStatusCode() != HttpStatus.SC_OK) {
	                throw new CallingInterfaceException(httpResp.getResponseContent());
	            }else
	            	
	            	return ;
	        }
	    }
}
