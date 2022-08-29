package com.bsg.upm.service;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Properties;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.conn.ConnectTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bsg.upm.check.resultenum.ChkRsInterface;
import com.bsg.upm.enums.InterfaceEnum;
import com.bsg.upm.exception.CallingInterfaceException;
import com.bsg.upm.exception.ConnectConsulException;
import com.bsg.upm.exception.ServiceNotFoundException;
import com.bsg.upm.util.http.HttpClientUtil;
import com.bsg.upm.util.http.HttpMethodEnum;
import com.bsg.upm.util.http.HttpResp;

@Service
public class HttpRequestService {

	public static final String SYSPATH = File.separator + "etc" + File.separator + "spdb";
//    public static final String SYSPATH = "D:";
    
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    
    public HttpResp sendGetRequest(InterfaceEnum interfaceEnum, String endpoint)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        return sendRequest(interfaceEnum, endpoint, null, HttpMethodEnum.GET, null);
    }
    
    public HttpResp sendGetRequest(InterfaceEnum interfaceEnum, String endpoint, Integer socketTimeout)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        return sendRequest(interfaceEnum, endpoint, null, HttpMethodEnum.GET, socketTimeout);
    }
    
    public HttpResp sendMonitorGetRequest(InterfaceEnum interfaceEnum, String endpoint)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        return sendMonitorRequest(interfaceEnum, endpoint, null, HttpMethodEnum.GET, null);
    }
    
    public HttpResp sendMonitorGetRequest(InterfaceEnum interfaceEnum, String endpoint, Integer socketTimeout)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        return sendMonitorRequest(interfaceEnum, endpoint, null, HttpMethodEnum.GET, socketTimeout);
    }

    public HttpResp sendPostRequest(InterfaceEnum interfaceEnum, String endpoint, String content)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        return sendRequest(interfaceEnum, endpoint, content, HttpMethodEnum.POST, null);
    }
    
    public HttpResp sendMonitorPostRequest(InterfaceEnum interfaceEnum, String endpoint, String content)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        return sendMonitorRequest(interfaceEnum, endpoint, content, HttpMethodEnum.POST, null);
    }

    public HttpResp sendPutRequest(InterfaceEnum interfaceEnum, String endpoint, String content)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        return sendRequest(interfaceEnum, endpoint, content, HttpMethodEnum.PUT, null);
    }
    
    public HttpResp sendMonitorPutRequest(InterfaceEnum interfaceEnum, String endpoint, String content)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        return sendMonitorRequest(interfaceEnum, endpoint, content, HttpMethodEnum.PUT, null);
    }

    public HttpResp sendDeleteRequest(InterfaceEnum interfaceEnum, String endpoint)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        return sendRequest(interfaceEnum, endpoint, null, HttpMethodEnum.DELETE, 180000);
    }

    public HttpResp sendDeleteRequest(InterfaceEnum interfaceEnum, String endpoint,
            Integer socketTimeout) throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        return sendMonitorRequest(interfaceEnum, endpoint, null, HttpMethodEnum.DELETE, socketTimeout);
    }
    
    public HttpResp sendMonitorDeleteRequest(InterfaceEnum interfaceEnum, String endpoint)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        return sendMonitorRequest(interfaceEnum, endpoint, null, HttpMethodEnum.DELETE, 180000);
    }

    public HttpResp sendMonitorDeleteRequest(InterfaceEnum interfaceEnum, String endpoint,
            Integer socketTimeout) throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {
        return sendMonitorRequest(interfaceEnum, endpoint, null, HttpMethodEnum.DELETE, socketTimeout);
    }
    

    public HttpResp sendRequest(InterfaceEnum interfaceEnum, String endpoint, String content,
            HttpMethodEnum httpMethodEnum, Integer socketTimeout)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {

        String sysPropertiesFilePath = SYSPATH + File.separator + "sys.properties";
        FileSystemResource file = new FileSystemResource(sysPropertiesFilePath);
        String addr = null;
        try {
            if (!file.exists()) {
                logger.error(sysPropertiesFilePath + " not found.");
            }
            Properties prop = PropertiesLoaderUtils.loadProperties(file);
            String path = prop.getProperty("connect.mgm.path");
            if (StringUtils.isBlank(path)) {
                logger.error("connect.mgm.path is blank.");
            }
            addr = path;
            return sendRequest(addr, endpoint, content, httpMethodEnum, socketTimeout);
        } catch (Exception e) {
            if (e instanceof ConnectTimeoutException || e instanceof ConnectException) {
                logger.error("Connect to {} failed", addr);
                throw new CallingInterfaceException(e);
            } else if (e instanceof IOException) {
                logger.error("Load properties failed:", e);
                throw new IOException(e);
            } else if (e instanceof SocketTimeoutException) {
                logger.error("Connect to {} response timeout.", addr);
                throw new CallingInterfaceException(e);
            } else {
                throw new CallingInterfaceException(e);
            }
        }
    }
    
    public HttpResp sendMonitorRequest(InterfaceEnum interfaceEnum, String endpoint, String content,
            HttpMethodEnum httpMethodEnum, Integer socketTimeout)
            throws ConnectConsulException, ServiceNotFoundException, CallingInterfaceException, IOException {

        String sysPropertiesFilePath = SYSPATH + File.separator + "sys.properties";
        FileSystemResource file = new FileSystemResource(sysPropertiesFilePath);
        String addr = null;
        try {
            if (!file.exists()) {
                logger.error(sysPropertiesFilePath + " not found.");
            }
            Properties prop = PropertiesLoaderUtils.loadProperties(file);
            String path = prop.getProperty("connect.horus.path");
            if (StringUtils.isBlank(path)) {
                logger.error("connect.horus.path is blank.");
            }
            addr = path;
            return sendRequest(addr, endpoint, content, httpMethodEnum, socketTimeout);
        } catch (Exception e) {
            if (e instanceof ConnectTimeoutException || e instanceof ConnectException) {
                logger.error("Connect to {} failed", addr);
                throw new CallingInterfaceException(e);
            } else if (e instanceof IOException) {
                logger.error("Load properties failed:", e);
                throw new IOException(e);
            } else if (e instanceof SocketTimeoutException) {
                logger.error("Connect to {} response timeout.", addr);
                throw new CallingInterfaceException(e);
            } else {
                throw new CallingInterfaceException(e);
            }
        }
    }
    
    private HttpResp sendRequest(String addr, String endpoint, String content, HttpMethodEnum httpMethodEnum,
            Integer socketTimeout) throws IOException {
        String url = "http://" + addr + "/" + endpoint;
        logger.info("Http Request URL: {}", url);
        logger.info("Http Request Method: {}", httpMethodEnum);

        if (StringUtils.isNoneBlank(content)) {
            logger.info("Http Request Data: {}", content);
            content = JSONObject.toJSONString(JSONObject.parse(content));
        }

        Long now = System.currentTimeMillis();
        HttpClientUtil httpClientUtil = null;
        if (socketTimeout == null) {
            httpClientUtil = new HttpClientUtil();
        } else {
            httpClientUtil = new HttpClientUtil(socketTimeout, 0);
        }
        HttpResp httpResp = null;

        switch (httpMethodEnum) {
        case GET:
            httpResp = httpClientUtil.sendGetRequest(url);
            break;
        case POST:
            httpResp = httpClientUtil.sendPostRequest(url, content);
            break;
        case PUT:
            httpResp = httpClientUtil.sendPutRequest(url, content);
            break;
        case DELETE:
            httpResp = httpClientUtil.sendDeleteRequest(url);
            break;
        default:
            break;
        }

        if (httpResp.getStatusCode() != HttpStatus.SC_OK && httpResp.getStatusCode() != HttpStatus.SC_CREATED
                && httpResp.getStatusCode() != HttpStatus.SC_NO_CONTENT) {
            logger.error("Http Response Status: {}", httpResp.getStatusCode());
            logger.error("Http Response Content: {}", httpResp.getResponseContent());
        } else {
            logger.info("Http Response Status: {}", httpResp.getStatusCode());
            if (httpResp.getResponseContent() != null && httpResp.getResponseContent().length() > 10240) {
                logger.info("Http Response Content: Too large (>20K)");
            } else {
                logger.info("Http Response Content: {}", httpResp.getResponseContent());
            }
        }

        logger.info("Http Response Time: {}ms", System.currentTimeMillis() - now);

        return httpResp;
    }

    public String loginfo(ChkRsInterface checkResult, Object... arguments) {
        Object[] temp = { checkResult.getCode() };
        FormattingTuple ft = MessageFormatter.arrayFormat("[{}] " + checkResult.getMsg(),
                ArrayUtils.addAll(temp, arguments));
        return ft.getMessage();
    }

}
