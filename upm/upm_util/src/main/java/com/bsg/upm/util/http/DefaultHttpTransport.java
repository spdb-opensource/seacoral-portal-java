package com.bsg.upm.util.http;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;

public class DefaultHttpTransport extends AbstractHttpTransport {

    private final HttpClient httpClient;

    public DefaultHttpTransport() {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT)
                .setConnectionRequestTimeout(DEFAULT_CONNECTION_REQUEST_TIMEOUT)
                .setSocketTimeout(DEFAULT_SOCKET_TIMEOUT).build();

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
                .useSystemProperties();

        this.httpClient = httpClientBuilder.build();
    }

    public DefaultHttpTransport(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    protected HttpClient getHttpClient() {
        return this.httpClient;
    }

}
