package com.bsg.upm.util.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractHttpTransport implements HttpTransport {

    static final int DEFAULT_MAX_CONNECTIONS = 1000;
    static final int DEFAULT_MAX_PER_ROUTE_CONNECTIONS = 500;

    static final int DEFAULT_SOCKET_TIMEOUT = 60000; // 60 sec
    static final int DEFAULT_CONNECTION_TIMEOUT = 2000; // 2 sec
    static final int DEFAULT_CONNECTION_REQUEST_TIMEOUT = 2000; // 2 sec

    static final int DEFAULT_RETRY_COUNT = 3;

    protected abstract HttpClient getHttpClient();

    @Override
    public HttpResp sendGetRequest(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        return sendRequest(httpGet);
    }

    @Override
    public HttpResp sendPostRequest(String url, String content) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        if (content != null) {
            StringEntity stringEntity = new StringEntity(content, "UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
        }
        return sendRequest(httpPost);
    }

    @Override
    public HttpResp sendPutRequest(String url, String content) throws IOException {
        HttpPut httpPut = new HttpPut(url);
        if (content != null) {
            StringEntity stringEntity = new StringEntity(content, "UTF-8");
            stringEntity.setContentType("application/json");
            httpPut.setEntity(stringEntity);
        }
        return sendRequest(httpPut);
    }

    @Override
    public HttpResp sendDeleteRequest(String url) throws IOException {
        HttpDelete httpDelete = new HttpDelete(url);
        return sendRequest(httpDelete);
    }

    Logger logger = LoggerFactory.getLogger(getClass());

    private HttpResp sendRequest(HttpUriRequest httpRequest) throws IOException {
        HttpClient httpClient = getHttpClient();
        HttpResponse response = httpClient.execute(httpRequest);
        int statusCode = response.getStatusLine().getStatusCode();

        HttpEntity entity = response.getEntity();
        String responseContent = null;
        if (entity != null) {
            responseContent = EntityUtils.toString(entity, "UTF-8");
        }
        return new HttpResp(statusCode, responseContent);
    }

}
