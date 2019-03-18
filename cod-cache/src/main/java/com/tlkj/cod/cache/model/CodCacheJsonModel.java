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
 * Desc codFrame json缓存配置model
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheJsonModel
 * @date 2019/2/13 11:00 AM
 */
public class CodCacheJsonModel {

    /**
     * 默认缓存名
     */
    private String name = "defaultCodJsonCache";

    /**
     * 缓存目录
     */
    private String rootDirectory = "java.io.tempDir/codJsonCache";

    /**
     * 设置每个缓存容纳元素个数
     */
    private int heap = 10000;

    /**
     * 设置缓存储存大小 超出的会被淘汰规则淘汰
     * 单位MB
     */
    private int disk = 500;

    /**
     * TTL 创建后过期时间（单位：秒）
     */
    private int timeToLiveSeconds = 60 * 60 * 6;

    /**
     * TTI 最后一次访问后过期时间
     */
    private int timeToIdleSeconds = 60 * 60 * 6;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRootDirectory() {
        return rootDirectory;
    }

    public void setRootDirectory(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public int getHeap() {
        return heap;
    }

    public void setHeap(int heap) {
        this.heap = heap;
    }

    public int getDisk() {
        return disk;
    }

    public void setDisk(int disk) {
        this.disk = disk;
    }

    public int getTimeToLiveSeconds() {
        return timeToLiveSeconds;
    }

    public void setTimeToLiveSeconds(int timeToLiveSeconds) {
        this.timeToLiveSeconds = timeToLiveSeconds;
    }

    public int getTimeToIdleSeconds() {
        return timeToIdleSeconds;
    }

    public void setTimeToIdleSeconds(int timeToIdleSeconds) {
        this.timeToIdleSeconds = timeToIdleSeconds;
    }
}
