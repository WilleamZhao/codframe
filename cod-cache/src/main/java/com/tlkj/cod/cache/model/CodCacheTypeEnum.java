/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.cache.model;

import com.tlkj.cod.cache.database.service.CodCacheDBService;
import com.tlkj.cod.cache.ehcache.service.CodCacheEhcacheService;
import com.tlkj.cod.cache.file.service.CodCacheFileService;
import com.tlkj.cod.cache.json.service.CodCacheJsonService;
import com.tlkj.cod.cache.memcached.service.CodCacheMemcachedService;
import com.tlkj.cod.cache.redis.service.CodCacheRedisService;
import org.apache.commons.lang3.StringUtils;

/**
 * Desc codFrame缓存类型枚举
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheTypeEnum
 * @date 2019/2/13 5:37 PM
 */
@Deprecated
public enum CodCacheTypeEnum {

    /**
     * ehcache缓存
     */
    EHCACHE(1, "ehcache", "e", CodCacheEhcacheService.class),

    /**
     * file缓存
     */
    FILE(2, "file", "f", CodCacheFileService.class),

    /**
     * json缓存
     */
    JSON(3, "json", "j", CodCacheJsonService.class),

    /**
     * 数据库缓存
     */
    DATABASE(4, "database", "db", CodCacheDBService.class),

    /**
     * memcache缓存
     */
    MEMCACHE(5, "memcache", "m", CodCacheMemcachedService.class),

    /**
     * redis缓存
     */
    REDIS(6, "redis", "r", CodCacheRedisService.class);

    private int state;
    private String name;
    private String shortName;
    private Class zlass;

    /**
     * 缓存类型枚举
     * @param state     标识
     * @param name      名称
     * @param shortName 简写
     */
    CodCacheTypeEnum(int state, String name, String shortName, Class zlass){
        this.state = state;
        this.name = name;
        this.shortName = shortName;
        this.zlass = zlass;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Class getZlass() {
        return zlass;
    }

    public void setZlass(Class zlass) {
        this.zlass = zlass;
    }

    public static Class getCache(String name) {
        if (StringUtils.isBlank(name)){
            return null;
        }
        for (CodCacheTypeEnum typeEnum : CodCacheTypeEnum.values()){
            if (typeEnum.getName().equals(name) || typeEnum.getShortName().equals(name) || typeEnum.getState() == Integer.parseInt(name)){
                return typeEnum.getZlass();
            }
        }
        return null;
    }
}
