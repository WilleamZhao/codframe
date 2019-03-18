/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.cache.ehcache.service;

import org.ehcache.Cache;

import java.util.Map;
import java.util.Set;

/**
 * Desc CodCacheEhcacheService
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheEhcacheService
 * @date 2019/2/11 7:11 PM
 */
public interface CodCacheEhcacheService {

    Cache getCache(String name);

    Cache getCache(Class<?> keyType, Class<?> valueType);

    Cache getCache(String name, Class<?> keyType, Class<?> valueType);

    Object get(Object key);

    boolean set(Object key, Object value);

    Object get(String name, Object key);

    boolean set(String name, Object key, Object value);

    Object get(String name, Object key, Class<?> keyType, Class<?> valueType);

    boolean set(String name, Object key, Object value, Class keyType, Class valueType);

    boolean setAll(String name, Map map);

    boolean setAll(String name, Map map, Class keyType, Class valueType);

    boolean containsKey(Object key);

    boolean containsKey(String name, Object key, Class keyType, Class valueType);

    boolean remove(Object key);

    boolean remove(String name, Object key, Object value, Class keyType, Class valueType);

    boolean removeAll(Set set);

    boolean removeAll(String name, Set set, Class keyType, Class valueType);
}
