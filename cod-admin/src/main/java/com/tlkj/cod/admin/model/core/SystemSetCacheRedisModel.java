package com.tlkj.cod.admin.model.core;

/**
 * Desc redis缓存
 *
 * @author sourcod
 * @version 1.0
 * @className SystemSetCacheRedisModel
 * @date 2019/2/16 9:15 PM
 */
public class SystemSetCacheRedisModel {

    /**
     * 地址
     */
    private String host;

    /**
     * 端口
     */
    private String port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
