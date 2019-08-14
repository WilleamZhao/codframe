/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.cache.file.service.impl;

import com.tlkj.cod.cache.CodCacheManager;
import com.tlkj.cod.cache.file.service.CodCacheFileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Desc codFrame 文件缓存实现
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheFileServiceImpl
 * @date 2019/2/13 10:48 AM
 */
@Service("codCacheFile")
public class CodCacheFileServiceImpl implements CodCacheFileService, CodCacheManager {

    @Override
    public String getSupportType() {
        return "codCacheFile";
    }

    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public <T> T get(String key, Class<T> klass) {
        return null;
    }

    @Override
    public Map getAll(Set key) {
        return null;
    }

    @Override
    public boolean set(String key, String value) {
        return false;
    }

    @Override
    public boolean set(String key, Map value) {
        return false;
    }

    @Override
    public boolean set(String key, List value) {
        return false;
    }

    @Override
    public boolean set(String key, Set value) {
        return false;
    }

    @Override
    public boolean set(String key, Object value) {
        return false;
    }

    @Override
    public boolean set(String key, String value, int expire) {
        return false;
    }

    @Override
    public boolean set(String key, Map value, int expire) {
        return false;
    }

    @Override
    public boolean set(String key, List value, int expire) {
        return false;
    }

    @Override
    public boolean set(String key, Set value, int expire) {
        return false;
    }

    @Override
    public boolean set(String key, Object value, int expire) {
        return false;
    }

    @Override
    public boolean replace(String key, String value) {
        return false;
    }

    @Override
    public boolean replace(String key, Map value) {
        return false;
    }

    @Override
    public boolean replace(String key, List value) {
        return false;
    }

    @Override
    public boolean replace(String key, Set value) {
        return false;
    }

    @Override
    public boolean replace(String key, Object value) {
        return false;
    }

    @Override
    public boolean replace(String key, String value, int expire) {
        return false;
    }

    @Override
    public boolean replace(String key, Map value, int expire) {
        return false;
    }

    @Override
    public boolean replace(String key, List value, int expire) {
        return false;
    }

    @Override
    public boolean replace(String key, Set value, int expire) {
        return false;
    }

    @Override
    public boolean replace(String key, Object value, int expire) {
        return false;
    }

    @Override
    public boolean del(String key) {
        return false;
    }

    @Override
    public boolean clear() {
        return false;
    }
}
