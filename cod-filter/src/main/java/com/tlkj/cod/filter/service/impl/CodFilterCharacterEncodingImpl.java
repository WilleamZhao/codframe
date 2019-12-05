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
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc 编码过滤器
 *
 * @author sourcod
 * @version 1.0
 * @className CodFilterCharacterEncoding
 * @date 2019/11/20 3:49 PM
 */
@Service
public class CodFilterCharacterEncodingImpl implements CodFilterService {

    @Override
    public boolean state() {
        return true;
    }

    @Override
    public String name() {
        return "codFilterCharacterEncodingImpl";
    }

    @Override
    public String alias() {
        return "编码过滤器";
    }

    @Override
    public int sort() {
        return 9998;
    }

    @Override
    public String mapping() {
        return "/*";
    }

    @Override
    public Filter filter() {
        return new CharacterEncodingFilter();
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> map = new HashMap<>();
        map.put("encoding", "UTF-8");
        map.put("forceEncoding", "true");
        return map;
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
