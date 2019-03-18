/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.cache.memcached.service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Desc codFrame memcached缓存接口
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheMemcachedService
 * @date 2019/2/11 7:02 PM
 */
public interface CodCacheMemcachedService {
    /**
     * 超时时间0
     *
     * @param key   key
     * @param value value
     * @return 是否set成功
     */
    boolean set(String key, Object value);

    /**
     * memcache Set 方法
     *
     * @param key    key
     * @param value  value
     * @param expire 过期时间
     * @return 是否set成功
     */
    boolean set(String key, Object value, int expire);

    /**
     * memcache get 方法
     *
     * @param key key
     * @return value
     */
    Object get(String key);

    /**
     * memcache 异步 get 方法
     *
     * @param key key
     * @return value
     */
    Object asyncGet(String key);

    /**
     * 超时时间0
     *
     * @param key   key
     * @param value value
     * @return 是否add成功
     */
    boolean add(String key, Object value);

    /**
     * memcache add 方法
     *
     * @param key    key
     * @param value  value
     * @param expire 过期时间
     * @return 是否add成功
     */
    boolean add(String key, Object value, int expire);

    /**
     * memcacht 替换方法
     *
     * @param key    key
     * @param value  value
     * @param expire 过期时间
     * @return 是否替换成功
     */
    boolean replace(String key, Object value, int expire);

    /**
     * memcache 删除方法
     *
     * @param key key
     * @return 是否删除成功
     */
    boolean delete(String key);

    /**
     * memcache 删除所有方法
     *
     * @return 删除所有成功
     */
    boolean flush();

    Map<String, Object> getMulti(Collection<String> keys);

    Map<String, Object> getMulti(String[] keys);

    Map<String, Object> asyncGetMulti(Collection<String> keys);

    Map<String, Object> asyncGetMulti(String keys[]);

    long increment(String key, int by, long defaultValue, int expire);

    long increment(String key, int by);

    long decrement(String key, int by, long defaultValue, int expire);

    long decrement(String key, int by);

    long asyncIncrement(String key, int by);

    long asyncDecrement(String key, int by);

    boolean getBooleanValue(Future<Boolean> f);

    long getLongValue(Future<Long> f);
}
