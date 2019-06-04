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
     *
     */
    void init();

    /**
     * 获取配置
     */
    Map<String, String> config();

    /**
     * 获取数据
     */
    void getData();


    /**
     * 设置数据
     */
    void setData();





}

