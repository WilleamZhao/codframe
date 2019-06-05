/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.config.service.impl;

import com.tlkj.cod.config.model.enums.CodConfigSourceType;
import com.tlkj.cod.config.service.CodConfigService;
import com.tlkj.cod.config.service.CodConfigChangeListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Desc apollo 配置服务
 * 从apollo获取配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodConfigServiceImpl
 * @date 2019/4/9 5:02 PM
 */
// @Service("codConfigApolloServiceImpl")
public class CodConfigApolloServiceImpl implements CodConfigService {

    private Map<String, Object> map = new HashMap<>();

    @Override
    public CodConfigSourceType getType() {
        return CodConfigSourceType.APOLLO;
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
