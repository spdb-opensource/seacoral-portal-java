package com.bsg.upm.util.http;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class HttpRequestUtil {

    /**
     * 日志对象
     */
    protected static Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

    public static Integer SOCKETTIMEOUT_GET = 60;

    public static Integer SOCKETTIMEOUT_POST = 60;

    public static Integer SOCKETTIMEOUT_PUT = 60;

    public static Integer SOCKETTIMEOUT_DELETE = 300;

    public static HttpResp sendGetRequest(String url) throws IOException {
        return sendRequest(url, HttpMethodEnum.GET, null, SOCKETTIMEOUT_GET);
    }

    public static HttpResp sendGetRequest(String url, Integer socketTimeout) throws IOException {
        if (socketTimeout == null) {
            socketTimeout = SOCKETTIMEOUT_GET;
        }
        return sendRequest(url, HttpMethodEnum.GET, null, socketTimeout);
    }

    public static HttpResp sendPostRequest(String url, String bodyContent) throws IOException {
        return sendRequest(url, HttpMethodEnum.POST, bodyContent, SOCKETTIMEOUT_POST);
    }

    public static HttpResp sendPostRequest(String url, String bodyContent, Integer socketTimeout) throws IOException {
        if (socketTimeout == null) {
            socketTimeout = SOCKETTIMEOUT_POST;
        }
        return sendRequest(url, HttpMethodEnum.POST, bodyContent, socketTimeout);
    }

    public static HttpResp sendPutRequest(String url, String bodyContent) throws IOException {
        return sendRequest(url, HttpMethodEnum.PUT, bodyContent, SOCKETTIMEOUT_PUT);
    }

    public static HttpResp sendPutRequest(String url, String bodyContent, Integer socketTimeout) throws IOException {
        if (socketTimeout == null) {
            socketTimeout = SOCKETTIMEOUT_PUT;
        }
        return sendRequest(url, HttpMethodEnum.PUT, bodyContent, socketTimeout);
    }

    public static HttpResp sendDeleteRequest(String url) throws IOException {
        return sendDeleteRequest(url, SOCKETTIMEOUT_DELETE);
    }

    public static HttpResp sendDeleteRequest(String url, Integer socketTimeout) throws IOException {
        if (socketTimeout == null) {
            socketTimeout = SOCKETTIMEOUT_DELETE;
        }
        return sendRequest(url, HttpMethodEnum.DELETE, null, socketTimeout);
    }

    private static HttpResp sendRequest(String url, HttpMethodEnum httpMethodEnum, String content,
            Integer socketTimeout) throws IOException {
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
            httpClientUtil = new HttpClientUtil(socketTimeout * 1000, 0);
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

        logger.info("Http Response Time: {}ms", System.currentTimeMillis() - now);
        if (httpResp.getStatusCode() != HttpStatus.SC_OK && httpResp.getStatusCode() != HttpStatus.SC_CREATED
                && httpResp.getStatusCode() != HttpStatus.SC_NO_CONTENT) {
            logger.error("Http Response Status: {}", httpResp.getStatusCode());
            logger.error("Http Response Content: {}", httpResp.getResponseContent());
        } else {
            logger.info("Http Response Status: {}", httpResp.getStatusCode());
            logger.info("Http Response Content: {}", httpResp.getResponseContent());
        }

        return httpResp;
    }
}
