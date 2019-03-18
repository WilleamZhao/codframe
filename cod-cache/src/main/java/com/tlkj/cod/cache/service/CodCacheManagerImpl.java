/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.cache.service;

import com.tlkj.cod.cache.CodCacheManager;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.model.system.core.SystemModel;
import com.tlkj.cod.model.system.entity.CodFrameSetDo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Desc codFrame 缓存管理器
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheManagerImpl
 * @date 2019/2/13 11:20 AM
 */
@Primary
@Service
public class CodCacheManagerImpl implements CodCacheManager {

    @Autowired
    @Lazy
    List<CodCacheManager> codCacheManagers;

    @Autowired
    Finder finder;


    /**
     * 获取 CodCacheManager
     */
    private CodCacheManager getCodCache(){
        SystemModel model = SystemModel.getInstance();
        String setValue;
        if (model.getCache() != null && StringUtils.isNotBlank(model.getCache().getType())){
            setValue = model.getCache().getType();
        } else {
            setValue = getSetValue();
        }

        for (CodCacheManager f : codCacheManagers){
            if (f.getSupportType().equals(setValue)){
                return f;
            }
        }
        System.err.println("获取缓存service错误");
        return null;
    }


    @Override
    public Object get(String key) {
        return getCodCache() == null ? null : getCodCache().get(key);
    }

    @Override
    public <T> T get(String key, Class<T> T) {
        return getCodCache() == null ? null : getCodCache().get(key, T);
    }

    @Override
    public Map getAll(Set key) {
        return getCodCache() == null ? null : getCodCache().getAll(key);
    }

    @Override
    public boolean set(String key, String value) {
        return getCodCache() != null && getCodCache().set(key, value);
    }

    @Override
    public boolean set(String key, Map value) {
        return getCodCache() != null && getCodCache().set(key, value);
    }

    @Override
    public boolean set(String key, List value) {
        return getCodCache() != null && getCodCache().set(key, value);
    }

    @Override
    public boolean set(String key, Set value) {
        return getCodCache() != null && getCodCache().set(key, value);
    }

    @Override
    public boolean set(String key, Object value) {
        return getCodCache() != null && getCodCache().set(key, value);
    }

    @Override
    public boolean set(String key, String value, int expire) {
        return getCodCache() != null && getCodCache().set(key, value, expire);
    }

    @Override
    public boolean set(String key, Map value, int expire) {
        return getCodCache() != null && getCodCache().set(key, value, expire);
    }

    @Override
    public boolean set(String key, List value, int expire) {
        return getCodCache() != null && getCodCache().set(key, value, expire);
    }

    @Override
    public boolean set(String key, Set value, int expire) {
        return getCodCache() != null && getCodCache().set(key, value, expire);
    }

    @Override
    public boolean set(String key, Object value, int expire) {
        return getCodCache() != null && getCodCache().set(key, value, expire);
    }

    @Override
    public boolean replace(String key, String value) {
        return getCodCache() != null && getCodCache().replace(key, value);
    }

    @Override
    public boolean replace(String key, Map value) {
        return getCodCache() != null && getCodCache().replace(key, value);
    }

    @Override
    public boolean replace(String key, List value) {
        return getCodCache() != null && getCodCache().replace(key, value);
    }

    @Override
    public boolean replace(String key, Set value) {
        return getCodCache() != null && getCodCache().replace(key, value);
    }

    @Override
    public boolean replace(String key, Object value) {
        return getCodCache() != null && getCodCache().replace(key, value);
    }

    @Override
    public boolean replace(String key, String value, int expire) {
        return getCodCache() != null && getCodCache().replace(key, value, expire);
    }

    @Override
    public boolean replace(String key, Map value, int expire) {
        return getCodCache() != null && getCodCache().replace(key, value, expire);
    }

    @Override
    public boolean replace(String key, List value, int expire) {
        return getCodCache() != null && getCodCache().replace(key, value, expire);
    }

    @Override
    public boolean replace(String key, Set value, int expire) {
        return getCodCache() != null && getCodCache().replace(key, value, expire);
    }

    @Override
    public boolean replace(String key, Object value, int expire) {
        return getCodCache() != null && getCodCache().replace(key, value, expire);
    }

    @Override
    public boolean del(String key) {
        return getCodCache() != null && getCodCache().del(key);
    }

    @Override
    public boolean clear() {
        return getCodCache() != null && getCodCache().clear();
    }

    /**
     * 获取设置Value
     * @return 设置值
     */
    private String getSetValue() {
        CodFrameSetDo setDo = finder.from(CodFrameSetDo.TABLE_NAME).where("set_code", "cache").first(CodFrameSetDo.class);
        return setDo.getSet_value();
    }
}
