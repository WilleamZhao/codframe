/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Desc memcached缓存实现
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminMemcachedService
 * @date 2018/12/19 8:19 PM
 */
public interface CodAdminMemcachedService {

    boolean set(String key, Object value);

    boolean set(String key, Object value, int expire);

    Object get(String key);

    Object asyncGet(String key);

    boolean add(String key, Object value);

    boolean add(String key, Object value, int expire);

    boolean replace(String key, Object value, int expire);

    boolean delete(String key);

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
