/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.config.facade.impl;

import com.tlkj.cod.config.facade.CodConfigFacade;
import com.tlkj.cod.config.model.enums.CodConfigSourceType;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Desc 缓存配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodConfigCacheServiceImpl
 * @date 2019/8/22 7:07 PM
 */
public class CodConfigCacheServiceImpl implements CodConfigFacade {
    @Override
    public boolean init() {
        return false;
    }

    @Override
    public List<Map<String, Object>> load() {
        return null;
    }

    @Override
    public List reload() {
        return null;
    }

    @Override
    public List reload(CodConfigSourceType type) {
        return null;
    }

    @Override
    public List list() {
        return null;
    }

    @Override
    public String getProperty(String key, String defaultValue, CodConfigSourceType type) {
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
}
