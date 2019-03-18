/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.cache.json.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Desc codFrame json缓存接口
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheJsonService
 * @date 2019/2/13 10:54 AM
 */
public interface CodCacheJsonService {

    boolean set(String key, String value);

    boolean set(String key, Map value);

    boolean set(String key, Set value);

    boolean set(String key, List value);

    boolean set(String key, InputStream value);

    boolean set(String key, File file);

    boolean set(String key, Object value);

    boolean set(String key, String value, int expire);

    boolean set(String key, Map value, int expire);

    boolean set(String key, Set value, int expire);

    boolean set(String key, List value, int expire);

    boolean set(String key, InputStream value, int expire);

    boolean set(String key, Object value, int expire);

}
