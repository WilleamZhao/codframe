/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.cache.model.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Desc codFrame memcached 缓存配置model
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheMemcachedModel
 * @date 2019/2/12 10:16 AM
 */
@Component
@Getter
@Setter
public class CodCacheConfigMemcached extends CodCacheConfigBase{

    private static final long serialVersionUID = -7272381346637554148L;

    private static volatile CodCacheConfigMemcached instance;

    public void setInstance(CodCacheConfigMemcached instance) {
        CodCacheConfigMemcached.instance = instance;
    }

    /**
     * 线程安全
     */
    public static CodCacheConfigMemcached getInstance() {
        if (instance == null) {
            synchronized (CodCacheConfigMemcached.class) {
                if (instance == null) {
                    instance = new CodCacheConfigMemcached();
                }
            }
        }
        return instance;
    }

    /**
     * 地址
     */
    @Value("${cod.cache.config.memcached.host:}")
    private String host;

    /**
     * 端口
     */
    @Value("${cod.cache.config.memcached.port:}")
    private String port;

    /**
     * 用户名
     */
    @Value("${cod.cache.config.memcached.username:}")
    private String username;

    /**
     * 密码
     */
    @Value("${cod.cache.config.memcached.password:}")
    private String password;

    /**
     * due
     */
    @Value("${cod.cache.config.memcached.due:}")
    private Integer due;

}
