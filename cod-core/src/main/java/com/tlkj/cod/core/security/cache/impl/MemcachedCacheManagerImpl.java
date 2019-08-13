/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.core.security.cache.impl;

import net.sf.ehcache.CacheException;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * Desc ShiroMemcached 管理器实现
 *
 * @author sourcod
 * @version 1.0
 * @className MemcachedCacheManagerImpl
 * @date 2018/7/10 下午10:30
 */
public class MemcachedCacheManagerImpl implements CacheManager {


    /*@Override
    public <K, V> void createCache(String name, Cache<K, V> cache) throws CacheException {
        MemCachedUtil.set(name, cache, 0);
    }*/

    @Override
    public Cache<Object, Object> getCache(String name) throws CacheException {
        // return (Cache<Object, Object>) MemCachedUtil.get(name);
        return null;
    }

    /*@Override
    public void removeCache(String name) throws CacheException {
        MemCachedUtil.delete("name");
    }*/

    /*@Override
    public void updateCahce(String name, Cache<Object, Object> cache) throws CacheException {
        MemCachedUtil.replace(name, cache, 0);
    }*/

    /*@Override
    public void destroy() throws CacheException {
        // MemCachedUtil.shutdown();

    }*/
}
