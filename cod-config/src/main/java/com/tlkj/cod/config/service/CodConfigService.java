/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.config.service;

import com.tlkj.cod.config.model.enums.CodConfigSourceType;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Desc 配置service
 *
 * @author sourcod
 * @version 1.0
 * @className CodConfigFacade
 * @date 2019/4/9 4:59 PM
 */
public interface CodConfigService {

    CodConfigSourceType getType();

    /**
     * 初始化配置
     * 1. 连接源
     * 2. 初始化数据/或者不做操作
     * @return 成功/失败  true/false
     */
    boolean init();

    /**
     * 加载顺序
     * 默认 0
     * 一样随机排序
     *
     * @return 序号
     */
    default int order(){
        return 0;
    }

    /**
     * 加载配置
     * 所有配置
     * 1. 获取配置
     * @return 所有配置项
     */
    Map<String, Object> load();

    /**
     * 配置列表
     */
    Set<String> list();

    /**
     * 获取配置
     * @param key          key
     * @param defaultValue 默认值
     * @return
     */
    String getProperty(String key, String defaultValue);

    /**
     * 获取配置名
     * @return
     */
    Set<String> getPropertyNames();

    /**
     * 添加listener
     * @param listener listener
     */
    void addChangeListener(CodConfigChangeListener listener);

    void addChangeListener(CodConfigChangeListener listener, Set<String> interestedKeys);

    void addChangeListener(CodConfigChangeListener listener, Set<String> interestedKeys, Set<String> interestedKeyPrefixes);

    boolean removeChangeListener(CodConfigChangeListener listener);

    <T> T getProperty(String key, Function<String, T> function, T defaultValue);

    CodConfigService getSourceType();


}

