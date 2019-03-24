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
 * Desc codFrame Ehcache缓存配置model
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheEhcacheModel
 * @date 2019/2/11 9:20 PM
 */
public class CodCacheEhcacheModel {

    /**
     * 缓存名称
     */
    private String name = "defaultEhcache";

    /**
     * 缓存目录
     */
    private String rootDirectory = "codCache/ehcache";

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
    private int heap = 10000;

    /**
     * 设置堆外储存大小(内存存储) 超出offheap的大小会被淘汰规则淘汰。
     * 单位MB
     */
    private int offHeap = 300;

    /**
     * 配置磁盘持久化储存(硬盘存储)用来持久化到磁盘
     * 单位MB
     */
    private int disk = 500;

    /**
     * 配置磁盘持久化储存(硬盘存储)用来持久化到磁盘,默认false不启用
     */
    private boolean isDisk = false;

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

    public Class<?> getKeyType() {
        return keyType;
    }

    public void setKeyType(Class<?> keyType) {
        this.keyType = keyType;
    }

    public Class<?> getValueType() {
        return valueType;
    }

    public void setValueType(Class<?> valueType) {
        this.valueType = valueType;
    }

    public int getHeap() {
        return heap;
    }

    public void setHeap(int heap) {
        this.heap = heap;
    }

    public int getOffHeap() {
        return offHeap;
    }

    public int getDisk() {
        return disk;
    }

    public void setOffHeap(int offHeap) {
        this.offHeap = offHeap;
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

    public boolean isDisk() {
        return isDisk;
    }

    public void setDisk(boolean disk) {
        isDisk = disk;
    }

    public String getRootDirectory() {
        return rootDirectory;
    }

    public void setRootDirectory(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }
}
