package com.bsg.upm.util.http;

import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;

public class DefaultHttpRetryTransport extends AbstractHttpTransport {

    private final HttpClient httpClient;

    public DefaultHttpRetryTransport() {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT)
                .setConnectionRequestTimeout(DEFAULT_CONNECTION_REQUEST_TIMEOUT)
                .setSocketTimeout(DEFAULT_SOCKET_TIMEOUT).build();

        HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandlerImpl(DEFAULT_RETRY_COUNT);

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setRetryHandler(retryHandler)
                .setDefaultRequestConfig(requestConfig).useSystemProperties();

        this.httpClient = httpClientBuilder.build();
    }

    public DefaultHttpRetryTransport(HttpRequestRetryHandler retryHandler) {

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT)
                .setConnectionRequestTimeout(DEFAULT_CONNECTION_REQUEST_TIMEOUT)
                .setSocketTimeout(DEFAULT_SOCKET_TIMEOUT).build();

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setRetryHandler(retryHandler)
                .setDefaultRequestConfig(requestConfig).useSystemProperties();

        this.httpClient = httpClientBuilder.build();
    }

    public DefaultHttpRetryTransport(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    protected HttpClient getHttpClient() {
        return this.httpClient;
    }

}
