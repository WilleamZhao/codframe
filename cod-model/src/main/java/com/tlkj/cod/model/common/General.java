/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.model.common;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc 通用获取参数方法
 *
 * @author sourcod
 * @version 1.0
 * @className General
 * @date 2019/3/24 4:46 PM
 */
public abstract class General {

    /**
     * 获取参数方法
     * 1. 从parameter里取
     * 2. 从attribute里取
     * @param request 请求
     * @param name    参数名
     * @return 参数值
     */
    protected String getParams(HttpServletRequest request, String name){
        String value = request.getParameter(name);
        if (StringUtils.isEmpty(value)){
            Object o = request.getAttribute(name);
            if (o == null){
                return "";
            }
            return o.toString();
        }
        return value;
    }
}
