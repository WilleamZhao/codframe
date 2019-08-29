/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.cache;


import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Desc codFrame框架缓存管理
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheManager
 * @date 2019/2/11 6:42 PM
 */
public interface CodCacheManager {

    /**
     * 默认支持ehcache
     * @return 默认支持
     */
    default String getSupportType(){
        return "codCacheEhcache";
    }

    /**
     * get
     * @param key key
     * @return Object
     */
    Object get(String key);

    /**
     * set
     * @param key    key
     * @param klass      泛型
     * @return 是否set成功
     */
    <T> T get(String key, Class<T> klass);

    Map getAll(Set key);

    /**
     * set 永不过期
     * @param key   key
     * @param value value
     * @return 是否set成功
     */
    boolean set(String key, String value);

    boolean set(String key, Map value);

    boolean set(String key, List value);

    boolean set(String key, Set value);

    boolean set(String key, Object value);

    /**
     * set
     * @param key    key
     * @param value  value
     * @param expire 过期时间
     * @return 是否set成功
     */
    boolean set(String key, String value, int expire);

    boolean set(String key, Map value, int expire);

    boolean set(String key, List value, int expire);

    boolean set(String key, Set value, int expire);

    boolean set(String key, Object value, int expire);

    /**
     * 替换 永不过期
     * @param key   key
     * @param value value
     */
    boolean replace(String key, String value);

    boolean replace(String key, Map value);

    boolean replace(String key, List value);

    boolean replace(String key, Set value);

    boolean replace(String key, Object value);

    /**
     * 替换
     * @param key    key
     * @param value  value
     * @param expire 过期时间
     * @return
     */
    boolean replace(String key, String value, int expire);

    boolean replace(String key, Map value, int expire);

    boolean replace(String key, List value, int expire);

    boolean replace(String key, Set value, int expire);

    boolean replace(String key, Object value, int expire);

    /**
     * 删除
     * @param key key
     * @return 是否del成功
     */
    boolean del(String key);

    /**
     * 清空缓存
     * @return
     */
    boolean clear();


}
