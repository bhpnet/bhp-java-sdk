package com.bhp.sdk.rpc.config;


import com.bhp.sdk.constant.BaseConstant;

import java.util.concurrent.TimeUnit;

/**
 * @author Trigger
 */

public class RpcConfiguration {

    private String url;
    private long readTimeOut = BaseConstant.RpcConstant.READ_TIMEOUT.getValue();
    private long writeTimeOut = BaseConstant.RpcConstant.WRITE_TIMEOUT.getValue();
    private long connectTimeout = BaseConstant.RpcConstant.CONNECT_TIMEOUT.getValue();
    private boolean underScoreToCamelCase;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getReadTimeOut() {
        return readTimeOut;
    }

    public void setReadTimeOut(long readTimeOut) {
        this.readTimeOut = readTimeOut;
    }

    public long getWriteTimeOut() {
        return writeTimeOut;
    }

    public void setWriteTimeOut(long writeTimeOut) {
        this.writeTimeOut = writeTimeOut;
    }

    public long getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(long connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public boolean isUnderScoreToCamelCase() {
        return underScoreToCamelCase;
    }

    public void setUnderScoreToCamelCase(boolean underScoreToCamelCase) {
        this.underScoreToCamelCase = underScoreToCamelCase;
    }
}
