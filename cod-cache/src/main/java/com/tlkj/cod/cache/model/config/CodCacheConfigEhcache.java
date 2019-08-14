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
 * Desc codFrame Ehcache缓存配置model
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheEhcacheModel
 * @date 2019/2/11 9:20 PM
 */
@Getter
@Setter
@Component
public class CodCacheConfigEhcache extends CodCacheConfigBase {

    private static final long serialVersionUID = -4227140643820188959L;

    /**
     * 缓存名称
     */
    @Value(value = "${cod.cache.config.ehcache.name:defaultEhcache}")
    private String name = "defaultEhcache";

    /**
     * 缓存目录
     */
    @Value(value = "${cod.cache.config.ehcache.url:.cod-temp/codCache/ehcache}")
    private String rootDirectory;

    /**
     * key类型
     */
    private Class<?> keyType = String.class;

    /**
     * value类型
     */
    private Class<?> valueType = String.class;

    /**
     * 设置缓存堆容纳元素个数(JVM内存空间)超出个数后会存到offheap中
     */
    @Value(value = "${cod.cache.config.ehcache.heap:10000}")
    private Integer heap = 10000;

    /**
     * 设置堆外储存大小(内存存储) 超出offheap的大小会被淘汰规则淘汰。
     * 单位MB
     */
    @Value(value = "${cod.cache.config.ehcache.offHeap:30}")
    private Integer offHeap = 30;

    /**
     * 配置磁盘持久化储存(硬盘存储)用来持久化到磁盘
     * 单位MB
     */
    @Value(value = "${cod.cache.config.ehcache.disk:500}")
    private Integer disk = 500;

    /**
     * 配置磁盘持久化储存(硬盘存储)用来持久化到磁盘,默认false不启用
     */
    @Value(value = "${cod.cache.config.ehcache.isDisk:false}")
    private boolean isDisk = false;

    /**
     * TTL 创建后过期时间（单位：秒）
     */
    @Value(value = "${cod.cache.config.ehcache.timeToLiveSeconds:21600}")
    private Integer timeToLiveSeconds = 60 * 60 * 6;

    /**
     * TTI 最后一次访问后过期时间
     */
    @Value(value = "${cod.cache.config.ehcache.timeToIdleSeconds:21600}")
    private Integer timeToIdleSeconds = 60 * 60 * 6;

}
