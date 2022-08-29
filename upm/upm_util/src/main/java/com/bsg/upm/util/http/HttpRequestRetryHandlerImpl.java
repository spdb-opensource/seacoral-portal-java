package com.bsg.upm.util.http;

import java.io.IOException;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.protocol.HttpContext;

public class HttpRequestRetryHandlerImpl implements HttpRequestRetryHandler {

    /**
     * 重试次数
     */
    private int retryCount;

    public HttpRequestRetryHandlerImpl() {
        retryCount = 0;
    }

    public HttpRequestRetryHandlerImpl(int retryCount) {
        if (retryCount < 0) {
            retryCount = 0;
        }
        this.retryCount = retryCount;
    }

    @Override
    public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
        if (executionCount > retryCount) {
            // Do not retry if over max retry count
            return false;
        }
        // if (exception instanceof InterruptedIOException) {
        // // Timeout
        // return false;
        // }
        // if (exception instanceof UnknownHostException) {
        // // Unknown host
        // return false;
        // }
        // if (exception instanceof ConnectTimeoutException) {
        // // Connection refused
        // return false;
        // }
        // if (exception instanceof SSLException) {
        // // SSL handshake exception
        // return false;
        // }
        HttpClientContext clientContext = HttpClientContext.adapt(context);
        HttpRequest request = clientContext.getRequest();
        // 幂等
        boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
        if (idempotent) {
            return true;
        }
        return false;

    }

    /**
     * 获取重试次数
     * 
     * @return retryCount 重试次数
     */
    public int getRetryCount() {
        return retryCount;
    }

    /**
     * 设置重试次数
     * 
     * @param retryCount
     *            重试次数
     */
    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

}
