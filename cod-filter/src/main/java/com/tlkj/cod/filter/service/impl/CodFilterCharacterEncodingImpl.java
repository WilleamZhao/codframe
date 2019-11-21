/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.filter.service.impl;

import com.tlkj.cod.filter.service.CodFilterService;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Desc 编码过滤器
 *
 * @author sourcod
 * @version 1.0
 * @className CodFilterCharacterEncoding
 * @date 2019/11/20 3:49 PM
 */
@Service
public class CodFilterCharacterEncoding implements CodFilterService {

    @Override
    public boolean state() {
        return false;
    }

    @Override
    public String name() {
        return "character";
    }

    @Override
    public String alias() {
        return null;
    }

    @Override
    public int sort() {
        return 0;
    }

    @Override
    public String mapping() {
        return "/*";
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
