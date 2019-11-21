/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.filter.service.impl;

import com.tlkj.cod.common.CodCommonNetWork;
import com.tlkj.cod.core.spring.SpringContextUtil;
import com.tlkj.cod.filter.model.config.CodFilterRequestFlowLimitConfig;
import com.tlkj.cod.filter.service.CodFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Desc 请求流量限制
 *
 * @author sourcod
 * @version 1.0
 * @className CodFilterRequestFlowLimitImpl
 * @date 2019/11/19 5:28 PM
 */
@Service("codFilterRequestFlowLimit")
public class CodFilterRequestFlowLimitImpl implements CodFilterService {

    private CodFilterRequestFlowLimitConfig codFilterRequestFlowLimitConfig;

    @Autowired
    public CodFilterRequestFlowLimitImpl(CodFilterRequestFlowLimitConfig codFilterRequestFlowLimitConfig){
        this.codFilterRequestFlowLimitConfig = codFilterRequestFlowLimitConfig;
    }

    @Override
    public boolean state() {
        return "1".equals(codFilterRequestFlowLimitConfig.getState());
    }

    @Override
    public String name() {
        return "codFilterFlowLimit";
    }

    @Override
    public String alias() {
        return "流量限制";
    }

    @Override
    public int sort() {
        return 0;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.codFilterRequestFlowLimitConfig = SpringContextUtil.getApplicationContext().getBean(CodFilterRequestFlowLimitConfig.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String ip = CodCommonNetWork.getIpAddress(request);
        chain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
