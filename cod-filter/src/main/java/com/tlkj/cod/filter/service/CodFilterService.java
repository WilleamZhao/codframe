/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.filter.service;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.Map;

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
     * 是否开启
     * 0: 关闭
     * 1: 开启
     * @return
     */
    boolean state();

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

    /**
     * mapping
     * 默认所有
     *
     * @return
     */
    default String mapping(){
        return "/*";
    }

    /**
     * dispathcerType
     * 默认请求
     *
     * @return
     */
    default DispatcherType dispatcherType(){
        return DispatcherType.REQUEST;
    }

    /**
     * filter
     * @return
     */
    default Filter filter(){
        return this;
    }

    /**
     * 参数
     * @return
     */
    default Map<String, String> params(){
        return null;
    }
}
