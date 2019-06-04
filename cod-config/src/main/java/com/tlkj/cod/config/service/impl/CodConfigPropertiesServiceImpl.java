package com.tlkj.cod.config.service.impl;

import com.tlkj.cod.config.model.enums.CodConfigSourceType;
import com.tlkj.cod.config.service.CodConfigService;
import com.tlkj.cod.config.service.ConfigChangeListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Desc 配置文件 数据源
 *
 * @author sourcod
 * @version 1.0
 * @className CodConfigPropertiesServiceImpl
 * @date 2019/5/30 11:18 AM
 */
@Service("codConfigPropertiesServiceImpl")
public class CodConfigPropertiesServiceImpl implements CodConfigService {

    private final List<Map<String, Object>> list = new ArrayList<>();

    @Override
    public CodConfigSourceType getType() {
        return CodConfigSourceType.PROPERTIES;
    }

    @Override
    public boolean init() {
        return false;
    }

    @Override
    public int order() {
        return 0;
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
    public void addChangeListener(ConfigChangeListener listener) {

    }

    @Override
    public void addChangeListener(ConfigChangeListener listener, Set<String> interestedKeys) {

    }

    @Override
    public void addChangeListener(ConfigChangeListener listener, Set<String> interestedKeys, Set<String> interestedKeyPrefixes) {

    }

    @Override
    public boolean removeChangeListener(ConfigChangeListener listener) {
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
