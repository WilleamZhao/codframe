/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.model.core;

import java.io.Serializable;

/**
 * Desc 缓存设置model
 *
 * @author sourcod
 * @version 1.0
 * @className SystemSetCacheModel
 * @date 2019/2/13 6:03 PM
 */
public class SystemSetCacheModel implements Serializable {

    private static final long serialVersionUID = -664305606167510463L;

    /**
     * 缓存类型
     */
    private String type = "codCacheEhcache";

    /**
     * 是否开启缓存
     * 开启: 1/true
     * 关闭: 0/false
     */
    private String isOpen;

    /**
     * 是否开启全局缓存(不推荐，开启后所有数据全部缓存，系统可以脱离数据库运行)
     * 开启: 1/true
     * 关闭: 0/false
     */
    private String isGlobal = "0";

    /**
     * 过期时间(秒)
     * 默认30分钟
     */
    private int expire = 60 * 30;

    /**
     * 是不是最后一次访问在过期
     * 默认true
     */
    private boolean last = true;

    /**
     * memcached缓存设置
     */
    private SystemSetCacheMemcachedModel memcached;

    /**
     * redis缓存设置
     */
    private SystemSetCacheRedisModel redis;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getIsGlobal() {
        return isGlobal;
    }

    public void setIsGlobal(String isGlobal) {
        this.isGlobal = isGlobal;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public SystemSetCacheMemcachedModel getMemcached() {
        return memcached;
    }

    public void setMemcached(SystemSetCacheMemcachedModel memcached) {
        this.memcached = memcached;
    }

    public SystemSetCacheRedisModel getRedis() {
        return redis;
    }

    public void setRedis(SystemSetCacheRedisModel redis) {
        this.redis = redis;
    }
}
