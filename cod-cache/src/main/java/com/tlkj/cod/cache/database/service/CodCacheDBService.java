/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.cache.database.service;

/**
 * Desc codFrame 数据库缓存接口
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheDBService
 * @date 2019/2/13 10:50 AM
 */
public interface CodCacheDBService {

    /**
     * 获取值
     * @param key key
     * @return value
     */
    Object get(String key);

    /**
     * 设置值
     * @param key   key
     * @param value value
     */
    boolean set(String key, String value);
}
