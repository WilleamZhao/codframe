/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.config.service.impl;

import com.tlkj.cod.config.model.enums.CodConfigSourceType;
import com.tlkj.cod.config.service.CodConfigChangeListener;
import com.tlkj.cod.config.service.CodConfigService;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Desc 缓存读取配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodConfigDatabaseServiceImpl
 * @date 2019/8/22 7:04 PM
 */
public class CodConfigDatabaseServiceImpl implements CodConfigService {
    @Override
    public CodConfigSourceType getType() {
        return CodConfigSourceType.DATABASE;
    }

    @Override
    public boolean init() {
        return false;
    }

    @Override
    public Map<String, Object> load() {
        return null;
    }

    @Override
    public Set<String> list() {
        return null;
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        return null;
    }

    @Override
    public Set<String> getPropertyNames() {
        return null;
    }

    @Override
    public void addChangeListener(CodConfigChangeListener listener) {

    }

    @Override
    public void addChangeListener(CodConfigChangeListener listener, Set<String> interestedKeys) {

    }

    @Override
    public void addChangeListener(CodConfigChangeListener listener, Set<String> interestedKeys, Set<String> interestedKeyPrefixes) {

    }

    @Override
    public boolean removeChangeListener(CodConfigChangeListener listener) {
        return false;
    }

    @Override
    public <T> T getProperty(String key, Function<String, T> function, T defaultValue) {
        return null;
    }

    @Override
    public CodConfigService getSourceType() {
        return null;
    }
}
