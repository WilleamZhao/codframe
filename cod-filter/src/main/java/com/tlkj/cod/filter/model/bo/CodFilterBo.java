/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.filter.model.bo;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import java.util.List;


/**
 * Desc filter bo
 *
 * @author sourcod
 * @version 1.0
 * @className CodFilterBo
 * @date 2019/9/3 2:40 PM
 */
@Component("codFilterBo")
public class CodFilterBo {

    private static volatile CodFilterBo instance;

    /**
     * 线程安全
     */
    public static CodFilterBo getInstance() {
        if (instance == null) {
            synchronized (CodFilterBo.class) {
                if (instance == null) {
                    instance = new CodFilterBo();
                }
            }
        }
        return instance;
    }

    /**
     * 所有 filter
     */
    private List<Filter> filters;

    public void addFilter(Filter filter){
        filters.add(filter);
    }

    public List<Filter> getFilters() {
        return filters;
    }
}
