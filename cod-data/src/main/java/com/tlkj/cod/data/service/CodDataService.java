package com.tlkj.cod.data.service;

import com.tlkj.cod.data.model.dto.CodDataConfigDto;

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
     * 获取配置
     * @return 全部配置
     */
    Map<String, String> getConfig();

    /**
     * 获取数据
     */
    String getDataValue(String key);

    /**
     *
     * @param key
     * @return
     */
    CodDataConfigDto getData(String key);


    /**
     * 设置数据
     * @param key key
     * @param value value
     */
    void setData(String key, String value);

    /**
     * 设置数据
     * @param key   key
     * @param value value
     * @param name  配置名称
     */
    void setData(String key, String value, String name);

    /**
     * 设置数据
     * @param key   key
     * @param value value
     * @param name  配置名称
     * @param sort  序号
     */
    void setData(String key, String value, String name, String sort);

    /**
     * 设置数据
     * @param key   key
     * @param value value
     * @param name  配置名称
     * @param sort  序号
     * @param desc  描述
     */
    void setData(String key, String value, String name, String desc, String sort);

    /**
     * 删除
     * @param key   key
     */
    void delete(String key);

    long getRefreshTime();

}

