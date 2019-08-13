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
 * Desc codFrame json缓存配置model
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheJsonModel
 * @date 2019/2/13 11:00 AM
 */
@Getter
@Setter
@Component
public class CodCacheConfigJson extends CodCacheConfigBase{

    private static final long serialVersionUID = 2517330305512191310L;

    /**
     * 默认缓存名
     */
    @Value(value = "${cod.cache.config.json.name:defaultCodJsonCache}")
    private String name = "defaultCodJsonCache";

    /**
     * 缓存目录
     */
    @Value(value = "${cod.cache.config.json.url:.cod-temp/codCache/json}")
    private String rootDirectory;

    /**
     * 设置每个缓存容纳元素个数
     */
    @Value(value = "${cod.cache.config.json.heap:heap}")
    private int heap = 10000;

    /**
     * 设置缓存储存大小 超出的会被淘汰规则淘汰
     * 单位MB
     */
    @Value(value = "${cod.cache.config.json.disk:500}")
    private int disk = 500;

    /**
     * TTL 创建后过期时间（单位：秒）
     */
    @Value(value = "${cod.cache.config.json.timeToLiveSeconds:21600}")
    private int timeToLiveSeconds = 60 * 60 * 6;

    /**
     * TTI 最后一次访问后过期时间
     */
    @Value(value = "${cod.cache.config.json.timeToIdleSeconds:21600}")
    private int timeToIdleSeconds = 60 * 60 * 6;
}
