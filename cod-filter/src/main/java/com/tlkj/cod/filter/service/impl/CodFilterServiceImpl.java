/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.filter.service.impl;

import com.tlkj.cod.filter.model.bo.CodFilterBo;
import com.tlkj.cod.filter.service.CodFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.Filter;

/**
 * Desc filter 实现
 *
 * @author sourcod
 * @version 1.0
 * @className CodFilterServiceImpl
 * @date 2019/9/3 2:39 PM
 */
@Service
public class CodFilterServiceImpl implements CodFilterService {

    @Autowired
    CodFilterBo codFilterBo;

    /**
     * 添加过滤器
     * @param filter 过滤器
     */
    @Override
    public void addFilter(Filter filter) {
        CodFilterBo.getInstance().addFilter(filter);
        /*if (codFilterBo != null){
            codFilterBo.addFilter(filter);
        }*/
    }
}
