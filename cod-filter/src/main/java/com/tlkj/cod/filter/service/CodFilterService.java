/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.filter.service;

import javax.servlet.Filter;

/**
 * Desc 过滤器 service
 *
 * @author sourcod
 * @version 1.0
 * @className CodFilterManager
 * @date 2019/9/3 2:38 PM
 */
public interface CodFilterService extends Filter{

    /**
     * 过滤器名称
     * @return
     */
    String name();

    /**
     * 过滤器别名
     * @return
     */
    String alias();

    /**
     * 过滤器序号
     * @return
     */
    int sort();
}
