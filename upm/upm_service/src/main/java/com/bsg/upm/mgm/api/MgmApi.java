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
     * ????????????
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
     * ????????????
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
     * ????????????
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
     * ????????????
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
     * ????????????
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
     * ????????????
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
     * ????????????
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
     * ????????????
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
    
    /** ????????????
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

    /** ????????????agent????????????
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
    
    /** ????????????host????????????
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
     * ????????????
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
    
    /** ????????????agent
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
    
    /** ????????????host
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
    
    /** ????????????agent
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
    
    /** ????????????host
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
     * ????????????
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
     * ??????????????????
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
     * ????????????
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
     * ????????????
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
     * ????????????
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
     * ????????????
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

        logger.info("???????????????");
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
     * ????????????
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

        logger.info("???????????????");
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
     * ????????????
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

        logger.info("???????????????");
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
     * ????????????
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

        logger.info("???????????????");
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
     * ????????????
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

        logger.info("???????????????");
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
     * ????????????
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

        logger.info("???????????????");
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
     * ????????????
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

        logger.info("???????????????");
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
     * ????????????
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

        logger.info("???????????????");
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
     * ??????CM???shell
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

        logger.info("??????CM???shell???");
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
     * ??????????????????
     * 
     * @param id
     *            ????????????ID
     * @param name
     *            ??????????????????
     * @param siteId
     *            ????????????id
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

        logger.info("?????????????????????");
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
     * ??????????????????
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

        logger.info("?????????????????????");
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
     * ??????????????????
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

        logger.info("?????????????????????");
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
     * ??????????????????
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

        logger.info("?????????????????????");
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
     * ?????????????????????
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

        logger.info("????????????????????????");
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
     * ?????????????????????
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

        logger.info("????????????????????????");
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
     * ?????????????????????
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

        logger.info("????????????????????????");
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
     * ?????????????????????
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

        logger.info("????????????????????????");
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
     * ???????????????????????????????????????????????????
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

        logger.info("??????????????????????????????????????????");
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
     * ???????????????????????????????????????????????????????????????
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

        logger.info("??????????????????????????????????????????????????????");
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
     * ????????????????????????
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
            logger.info("??????????????????????????????app_id??????");
            MgmSaveServerGroup mgmSaveServerGroup=new MgmSaveServerGroup();
            mgmSaveServerGroup.setId("11111111111111111111111111111");
            mgmSaveServerGroup.setName("test");
            mgmSaveServerGroup.setTaskId("taskId");
            return mgmSaveServerGroup;
        }
        if (mgmServerGroupImageBody == null) {
            logger.info("????????????????????????????????????????????????");
            MgmSaveServerGroup mgmSaveServerGroup=new MgmSaveServerGroup();
            mgmSaveServerGroup.setId("11111111111111111111111111111");
            mgmSaveServerGroup.setName("test");
            mgmSaveServerGroup.setTaskId("taskId");
            return mgmSaveServerGroup;
        }

        String endpoint = "/{0}/manager/apps/{1}/image";
        endpoint = MessageFormat.format(endpoint, "v1.0", appId);

        logger.info("?????????????????????????????????");
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
     * ??????????????????????????????
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
            logger.info("????????????????????????????????????app_id??????");
            return;
        }
        if (mgmServerGroupStateBody == null) {
            logger.info("????????????????????????????????????state??????");
            return;
        }

        String endpoint = "/{0}/manager/apps/{1}/state";
        endpoint = MessageFormat.format(endpoint, "v1.0", appId);

        logger.info("?????????????????????????????????");
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
     * ????????????????????????????????????
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
            logger.info("??????????????????????????????????????????app_id??????");
            return;
        }
        if (mgmServerGroupStateBody == null) {
            logger.info("??????????????????????????????????????????state??????");
            return;
        }

        String endpoint = "/{0}/manager/apps/{1}/units/{2}/state";
        endpoint = MessageFormat.format(endpoint, "v1.0", appId, unitId);

        logger.info("???????????????????????????????????????");
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
     * ??????????????????????????????
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
            logger.info("????????????????????????????????????app_id??????");
            MgmSaveServerGroup mgmSaveServerGroup=new MgmSaveServerGroup();
            mgmSaveServerGroup.setId("11111111111111111111111111111");
            mgmSaveServerGroup.setName("test");
            mgmSaveServerGroup.setTaskId("taskId");
            return mgmSaveServerGroup;
        }
        if (mgmServerGroupResourceRequestsBody == null) {
            logger.info("???????????????????????????????????????????????????");
            MgmSaveServerGroup mgmSaveServerGroup=new MgmSaveServerGroup();
            mgmSaveServerGroup.setId("11111111111111111111111111111");
            mgmSaveServerGroup.setName("test");
            mgmSaveServerGroup.setTaskId("taskId");
            return mgmSaveServerGroup;
        }

        String endpoint = "/{0}/manager/apps/{1}/resource/requests";
        endpoint = MessageFormat.format(endpoint, "v1.0", appId);

        logger.info("?????????????????????????????????");
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
     * ??????????????????
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
            logger.info("????????????????????????app_id??????");
            throw new CallingInterfaceException("????????????????????????app_id??????");
        }
        if (mgmServerGroupBackupBody == null) {
            logger.info("????????????????????????state??????");
            throw new CallingInterfaceException("????????????????????????app_id??????");
        }

        String endpoint = "/{0}/manager/backup/strategy";
        endpoint = MessageFormat.format(endpoint, "v1.0");

        logger.info("?????????????????????");
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
     * ??????????????????
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
            logger.info("????????????????????????app_id??????");
            throw new CallingInterfaceException("????????????????????????app_id??????");
        }
        if (mgmServerGroupUnitBackupBody == null) {
            logger.info("????????????????????????state??????");
            throw new CallingInterfaceException("????????????????????????app_id??????");
        }

        String endpoint = "/{0}/manager/backup/strategy";
        endpoint = MessageFormat.format(endpoint, "v1.0");

        logger.info("?????????????????????");
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
     * ??????????????????
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
            logger.info("????????????????????????app_id??????");
            throw new CallingInterfaceException("????????????????????????app_id??????");
        }
        if (mgmServerGroupUnitRebulidBody == null) {
            logger.info("????????????????????????state??????");
            throw new CallingInterfaceException("????????????????????????app_id??????");
        }
        String endpoint = "/{0}/manager/apps/{1}/units/{2}/rebuild";
//        String endpoint = "/{0}/manager/backup/strategy";
        endpoint = MessageFormat.format(endpoint, "v1.0",appId,unitId);

        logger.info("?????????????????????");
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
     * ????????????????????????
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
    		logger.info("??????????????????????????????app_id??????");
    		throw new CallingInterfaceException("??????????????????????????????app_id??????");
    	}
    	if (mgmServerGroupUnitRoleChangeBody == null) {
    		logger.info("??????????????????????????????state??????");
    		throw new CallingInterfaceException("??????????????????????????????app_id??????");
    	}
    	String endpoint = "/{0}/manager/apps/{1}/role";
    	endpoint = MessageFormat.format(endpoint, "v1.0",appId);
    	
    	logger.info("???????????????????????????");
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
    		logger.info("??????????????????????????????????????????app_id??????");
    		throw new CallingInterfaceException("??????????????????????????????app_id??????");
    	}
    	if (mgmServerGroupUnitRoleChangeBody == null) {
    		logger.info("??????????????????????????????????????????state??????");
    		throw new CallingInterfaceException("??????????????????????????????????????????app_id??????");
    	}
    	String endpoint = "/{0}/manager/apps/{1}/units/{2}/role";
    	endpoint = MessageFormat.format(endpoint, "v1.0",appId,slaveId);
    	
    	logger.info("???????????????????????????????????????");
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
     * ??????????????????
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
            logger.info("????????????????????????app_id??????");
            throw new CallingInterfaceException("????????????????????????app_id??????");
        }
        if (mgmServerGroupUnitRebulidBody == null) {
            logger.info("????????????????????????state??????");
            throw new CallingInterfaceException("????????????????????????app_id??????");
        }
        String endpoint = "/{0}/manager/apps/{1}/units/{2}/migrate";
//        String endpoint = "/{0}/manager/backup/strategy";
        endpoint = MessageFormat.format(endpoint, "v1.0",appId,unitId);

        logger.info("?????????????????????");
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
     * ??????????????????
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
            logger.info("????????????????????????app_id??????");
            return status;
        }
        String endpoint = "{0}/manager/apps/{1}";
        endpoint = MessageFormat.format(endpoint, "v1.0", appId);

        logger.info("?????????????????????");
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
     * ?????????????????????
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

        logger.info("????????????????????????");
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
     * ???????????????????????????
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

        logger.info("??????????????????????????????");
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
     * ?????????????????????
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

        logger.info("????????????????????????");
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
     * ?????????????????????
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

        logger.info("????????????????????????");
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
     * ????????????
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
     * ????????????
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

        logger.info("???????????????");
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
     * ????????????
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

        logger.info("???????????????");
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

        logger.info("?????????????????????");
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
     * ????????????
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

        logger.info("???????????????");
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
     * ??????????????????
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
        logger.info("?????????????????????");
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
     * ????????????
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
        logger.info("???????????????");
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
     * ?????????????????????????????????
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

        logger.info("??????????????????????????????");
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
     * ??????????????????
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

        logger.info("?????????????????????");
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
     * ?????????????????????????????????
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

        logger.info("??????????????????????????????");
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
     * ?????????????????????????????????
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

        logger.info("????????????????????????");
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
     * ?????????????????????????????????
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

        logger.info("????????????????????????????????????");
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
     * ?????????????????????????????????
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

        logger.info("????????????????????????????????????");
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
     * ?????????????????????????????????
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

        logger.info("????????????????????????????????????");
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
     * ??????????????????
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

        logger.info("????????????????????????????????????");
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
     * ??????????????????
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

        logger.info("?????????????????????");
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
     * ??????????????????
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

        logger.info("?????????????????????");
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
     * ??????json???????????????json???
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
     * ?????????????????????
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

        logger.info("????????????????????????");
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
     * ?????????????????????
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
    	
    	logger.info("??????????????????????????????");
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
     * ????????????????????????
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
    
    /** ????????????????????????
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
        logger.info("?????????????????????");
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
    
    
    /**  ?????????????????????????????????
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
        logger.info("????????????", "1");
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
    
    /** ????????????????????????
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
    
    /** ????????????????????????
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
    
    /**  ??????????????????????????????????????????
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
    
    /**??????????????????????????????????????????
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
         * ????????????
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

            logger.info("???????????????");
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
         * ??????????????????
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
	     * cm??????????????????
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

	        logger.info("??????CM???sites???");
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
