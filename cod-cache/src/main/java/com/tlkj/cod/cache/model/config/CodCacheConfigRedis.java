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
 * Desc codFrame redis缓存配置model
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheRedisModel
 * @date 2019/2/13 11:00 AM
 */
@Component
@Getter
@Setter
public class CodCacheConfigRedis extends CodCacheConfigBase {

    private static final long serialVersionUID = 3654008158571798543L;

    /**
     * 缓存生存时间
     */
    @Value("${cod.cache.config.redis.due:1000}")
    private String due;

    /**
     * 地址
     */
    @Value("${cod.cache.config.redis.host:}")
    private String host;

    /**
     * 端口
     */
    @Value("${cod.cache.config.redis.port:}")
    private String port;

    /**
     * password
     */
    @Value("${cod.cache.config.redis.password:}")
    private String password;

    /**
     * maxTotal
     */
    @Value("${cod.cache.config.redis.maxTotal:}")
    private String maxTotal;

    /**
     * maxIdle
     */
    @Value("${cod.cache.config.redis.maxIdle:}")
    private String maxIdle;

    /**
     * maxWaitMillis
     */
    @Value("${cod.cache.config.redis.maxWaitMillis:}")
    private String maxWaitMillis;

    /**
     * testOnBorrow
     */
    @Value("${cod.cache.config.redis.testOnBorrow:}")
    private String testOnBorrow;

}
