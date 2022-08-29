package com.bsg.upm.util.http;

import java.io.IOException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * HttpClient tool
 * 
 * @author HCK
 *
 */
public class HttpClientUtil {

    private final HttpTransport httpTransport;

    public HttpClientUtil() {
        this(new DefaultHttpTransport());
    }

    public HttpClientUtil(int retryCount) {
        this(new DefaultHttpRetryTransport(new HttpRequestRetryHandlerImpl(retryCount)));
    }

    public HttpClientUtil(int socketTimeout, int retryCount) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(AbstractHttpTransport.DEFAULT_CONNECTION_TIMEOUT)
                .setConnectionRequestTimeout(AbstractHttpTransport.DEFAULT_CONNECTION_REQUEST_TIMEOUT)
                .setSocketTimeout(socketTimeout).build();

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
                .setRetryHandler(new HttpRequestRetryHandlerImpl(retryCount)).useSystemProperties();

        HttpClient httpClient = httpClientBuilder.build();
        this.httpTransport = new DefaultHttpTransport(httpClient);

    }

    public HttpClientUtil(HttpTransport httpTransport) {
        this.httpTransport = httpTransport;
    }

    /**
     * send get request
     * 
     * @param url
     * @return
     * @throws IOException
     */
    public HttpResp sendGetRequest(String url) throws IOException {
        return httpTransport.sendGetRequest(url);
    }

    /**
     * send post request
     * 
     * @param url
     * @param content
     * @return
     * @throws IOException
     */
    public HttpResp sendPostRequest(String url, String content) throws IOException {
        return httpTransport.sendPostRequest(url, content);
    }

    /**
     * send put request
     * 
     * @param url
     * @param content
     * @return
     * @throws IOException
     */
    public HttpResp sendPutRequest(String url, String content) throws IOException {
        return httpTransport.sendPutRequest(url, content);
    }

    /**
     * send delete request
     * 
     * @param url
     * @return
     * @throws IOException
     */
    public HttpResp sendDeleteRequest(String url) throws IOException {
        return httpTransport.sendDeleteRequest(url);
    }

}
