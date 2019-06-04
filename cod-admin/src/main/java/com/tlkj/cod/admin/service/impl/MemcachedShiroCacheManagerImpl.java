/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.service.impl;

import com.tlkj.cod.admin.service.MemcachedService;
import com.tlkj.cod.core.security.cache.MemcachedCacheManager;
import net.sf.ehcache.CacheException;
import org.apache.shiro.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className MemcachedShiroCacheManagerImpl
 * @date 2018/12/19 8:18 PM
 */
@Service
public class MemcachedShiroCacheManagerImpl implements MemcachedCacheManager {

    @Autowired
    MemcachedService memcachedService;

    @Override
    public <K, V> void createCache(String name, Cache<K, V> cache) throws CacheException {

    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return null;
    }

    @Override
    public void removeCache(String name) throws CacheException {

    }

    @Override
    public void updateCahce(String name, Cache<Object, Object> cache) throws CacheException {

    }

    @Override
    public void destroy() throws CacheException {

    }
}
