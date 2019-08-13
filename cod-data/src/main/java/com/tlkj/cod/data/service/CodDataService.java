package com.tlkj.cod.data.service;

import java.util.Map;

/**
 * Desc cod data service
 *
 * @author sourcod
 * @version 1.0
 * @className CodDataService
 * @date 2019/5/28 11:33 AM
 */
public interface CodDataService {

    /**
     * 初始化
     */
    void init();

    /**
     * 获取配置
     * @return 全部配置
     */
    Map<String, String> getConfig();

    /**
     * 获取数据
     */
    String getData(String key);


    /**
     * 设置数据
     * @param key key
     * @param value value
     */
    void setData(String key, String value);

}

