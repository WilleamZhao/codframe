/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.cache.model;

/**
 * Desc codFrame memcached 缓存配置model
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheMemcachedModel
 * @date 2019/2/12 10:16 AM
 */
public class CodCacheMemcachedModel {

    private static volatile CodCacheMemcachedModel instance;

    public void setInstance(CodCacheMemcachedModel instance) {
        CodCacheMemcachedModel.instance = instance;
    }

    /**
     * 线程安全
     */
    public static CodCacheMemcachedModel getInstance() {
        if (instance == null) {
            synchronized (CodCacheMemcachedModel.class) {
                if (instance == null) {
                    instance = new CodCacheMemcachedModel();
                }
            }
        }
        return instance;
    }

    /**
     * 地址
     */
    private String host;

    /**
     * 端口
     */
    private String port;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 超时
     */
    private int timeout;

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getTimeout() {
        return timeout;
    }
}
