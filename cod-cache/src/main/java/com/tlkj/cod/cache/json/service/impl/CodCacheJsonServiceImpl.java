/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.cache.json.service.impl;

import com.tlkj.cod.cache.json.service.CodCacheJsonService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Desc codFrame Json缓存实现
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheJsonServiceImpl
 * @date 2019/2/13 10:55 AM
 */
@Service("codCacheJson")
public class CodCacheJsonServiceImpl implements CodCacheJsonService {

    @Override
    public boolean set(String key, String value) {
        return false;
    }

    @Override
    public boolean set(String key, Map value) {
        return false;
    }

    @Override
    public boolean set(String key, Set value) {
        return false;
    }

    @Override
    public boolean set(String key, List value) {
        return false;
    }

    @Override
    public boolean set(String key, InputStream value) {
        return false;
    }

    @Override
    public boolean set(String key, File file) {
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
    public boolean set(String key, Set value, int expire) {
        return false;
    }

    @Override
    public boolean set(String key, List value, int expire) {
        return false;
    }

    @Override
    public boolean set(String key, InputStream value, int expire) {
        return false;
    }

    @Override
    public boolean set(String key, Object value, int expire) {
        return false;
    }
}
