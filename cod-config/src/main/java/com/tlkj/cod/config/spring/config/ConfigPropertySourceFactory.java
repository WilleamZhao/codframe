package com.tlkj.cod.config.spring.config;


import com.tlkj.cod.config.model.enums.CodConfigSourceType;
import com.tlkj.cod.config.service.CodConfigService;
import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 * 配置源工厂
 *
 * @author sourcod
 * @date 2019/05/31
 */
public class ConfigPropertySourceFactory {

    private static volatile ConfigPropertySourceFactory singleton;

    public static ConfigPropertySourceFactory getInstance() {
        if (singleton == null) {
            synchronized (ConfigPropertySourceFactory.class) {
                if (singleton == null) {
                    singleton = new ConfigPropertySourceFactory();
                }
            }
        }
        return singleton;
    }

    private final List<ConfigPropertySource> configPropertySources = Lists.newLinkedList();

    public ConfigPropertySource getConfigPropertySource(CodConfigSourceType type, CodConfigService source) {
        ConfigPropertySource configPropertySource = new ConfigPropertySource(type, source);

        configPropertySources.add(configPropertySource);

        return configPropertySource;
    }

    public List<ConfigPropertySource> getAllConfigPropertySources() {
        return Lists.newLinkedList(configPropertySources);
    }
}
