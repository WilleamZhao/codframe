/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.system.impl;

import com.tlkj.cod.service.system.MemcachedService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Future;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className MemcachedServiceImpl
 * @date 2018/12/19 8:25 PM
 */
@Service
public class MemcachedServiceImpl implements MemcachedService {
    
    @Override
    public boolean set(String key, Object value) {
        return false;
    }

    @Override
    public boolean set(String key, Object value, int expire) {
        return false;
    }

    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public Object asyncGet(String key) {
        return null;
    }

    @Override
    public boolean add(String key, Object value) {
        return false;
    }

    @Override
    public boolean add(String key, Object value, int expire) {
        return false;
    }

    @Override
    public boolean replace(String key, Object value, int expire) {
        return false;
    }

    @Override
    public boolean delete(String key) {
        return false;
    }

    @Override
    public boolean flush() {
        return false;
    }

    @Override
    public Map<String, Object> getMulti(Collection<String> keys) {
        return null;
    }

    @Override
    public Map<String, Object> getMulti(String[] keys) {
        return null;
    }

    @Override
    public Map<String, Object> asyncGetMulti(Collection<String> keys) {
        return null;
    }

    @Override
    public Map<String, Object> asyncGetMulti(String[] keys) {
        return null;
    }

    @Override
    public long increment(String key, int by, long defaultValue, int expire) {
        return 0;
    }

    @Override
    public long increment(String key, int by) {
        return 0;
    }

    @Override
    public long decrement(String key, int by, long defaultValue, int expire) {
        return 0;
    }

    @Override
    public long decrement(String key, int by) {
        return 0;
    }

    @Override
    public long asyncIncrement(String key, int by) {
        return 0;
    }

    @Override
    public long asyncDecrement(String key, int by) {
        return 0;
    }

    @Override
    public boolean getBooleanValue(Future<Boolean> f) {
        return false;
    }

    @Override
    public long getLongValue(Future<Long> f) {
        return 0;
    }
}
