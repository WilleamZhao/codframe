/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.security.cache;

import net.sf.ehcache.CacheException;
import org.apache.shiro.cache.Cache;

/**
 * Desc ShiroMemcached 缓存管理器
 *
 * @author sourcod
 * @version 1.0
 * @className MemcachedCacheManager
 * @date 2018/7/10 下午10:27
 */
public interface MemcachedCacheManager {

    /**
     * 新增缓存堆到管理器
     *
     * @param name
     * @param cache
     */
    public <K, V> void createCache(String name, Cache<K, V> cache) throws CacheException;

    /**
     * 获取缓存堆
     *
     * @param name
     * @return
     * @throws CacheException
     */
    public <K, V> Cache<K, V> getCache(String name) throws CacheException;

    /**
     * 移除缓存堆
     *
     * @param name
     * @throws CacheException
     */
    public void removeCache(String name) throws CacheException;

    /**
     * 更新缓存堆
     *
     * @param name
     * @param cache
     */
    public void updateCahce(String name, Cache<Object, Object> cache) throws CacheException;

    /**
     * 注销管理器
     */
    public void destroy() throws CacheException;
}
