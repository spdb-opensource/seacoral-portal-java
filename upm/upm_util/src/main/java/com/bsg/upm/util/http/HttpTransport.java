package com.bsg.upm.util.http;

import java.io.IOException;

public interface HttpTransport {

    public HttpResp sendGetRequest(String url) throws IOException;

    public HttpResp sendPostRequest(String url, String content) throws IOException;

    public HttpResp sendPutRequest(String url, String content) throws IOException;

    public HttpResp sendDeleteRequest(String url) throws IOException;
}
