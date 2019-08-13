/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.cache.database.service.impl;

import com.tlkj.cod.cache.CodCacheManager;
import com.tlkj.cod.cache.database.model.entity.CodCacheDBDo;
import com.tlkj.cod.cache.database.service.CodCacheDBService;
import com.tlkj.cod.cache.model.CodCacheModel;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Desc codFrame 数据库缓存实现
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheDBServiceImpl
 * @date 2019/2/13 10:50 AM
 */
@Service("codCacheDatabase")
public class CodCacheDBServiceImpl implements CodCacheDBService, CodCacheManager {

    @Autowired
    Updater updater;

    @Autowired
    Finder finder;

    @Override
    public Object get(String key) {
        CodCacheDBDo codCacheDBDo = finder.from(CodCacheDBDo.TABLE_NAME).where("k", key).where("due < now()").first(CodCacheDBDo.class);
        if (codCacheDBDo == null){
            return null;
        }

        return codCacheDBDo.getV();
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

    private boolean setDB(String key, Object value, int expire){
        CodCacheModel codCacheModel = null;
        return true;
    }
}
