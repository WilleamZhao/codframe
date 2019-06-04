/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.config.facade;

import com.tlkj.cod.config.model.enums.CodConfigSourceType;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Desc 配置service
 *
 * @author sourcod
 * @version 1.0
 * @className CodConfigFacade
 * @date 2019/4/9 4:59 PM
 */
public interface CodConfigFacade {

    /**
     * 初始化
     * @return
     */
    boolean init();

    /**
     * 加载配置
     * 把所有实现 CodConfigService 类都按顺序加载进来
     */
    List<Map<String, Object>> load();

    /**
     * 重新加载配置
     * 全部重新加载
     */
    List reload();

    /**
     * 按类型重新加载
     * @param type 类型
     * @return
     */
    List reload(CodConfigSourceType type);

    /**
     * 配置列表
     */
    List list();

    /**
     * 获取配置
     * @param key          key
     * @param defaultValue 默认值
     * @param type         源
     * @return
     */
    String getProperty(String key, String defaultValue, CodConfigSourceType type);

    /**
     * 在全部源里获取配置
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


}

