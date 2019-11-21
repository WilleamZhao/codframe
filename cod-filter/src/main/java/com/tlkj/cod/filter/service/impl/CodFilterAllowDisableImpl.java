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
import com.tlkj.cod.filter.model.config.CodFilterAllowDisableConfig;
import com.tlkj.cod.filter.service.CodFilterService;
import org.apache.commons.lang3.StringUtils;
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
 * Desc 黑白名单 过滤器 实现
 *
 * @author sourcod
 * @version 1.0
 * @className CodFilterServiceImpl
 * @date 2019/9/3 2:39 PM
 */
@Service("codFilterAllowDisable")
public class CodFilterAllowDisableImpl implements CodFilterService {

    private CodFilterAllowDisableConfig codFilterAllowDisableConfig;

    @Autowired
    public CodFilterAllowDisableImpl(CodFilterAllowDisableConfig codFilterAllowDisableConfig){
        this.codFilterAllowDisableConfig = codFilterAllowDisableConfig;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {


    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String state = codFilterAllowDisableConfig.getState();
        if ("1".equals(state)){
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String ip = CodCommonNetWork.getIpAddress(httpServletRequest);
            String type = codFilterAllowDisableConfig.getType();
            boolean isVisit = false;
            // 白名单
            if ("1".equals(type)){
                String allow = codFilterAllowDisableConfig.getAllow();
                if (StringUtils.isNotBlank(allow)){
                    String[] allows = allow.split(",");
                    for (String allowIp : allows){
                        isVisit = CodCommonNetWork.isInRange(ip, allowIp);

                    }
                }
            }

            // 黑名单
            if ("0".equals(type)){
                String disable = codFilterAllowDisableConfig.getAllow();
                if (StringUtils.isNotBlank(disable)){
                    isVisit = CodCommonNetWork.isInRange(ip, disable);
                }
                CodCommonNetWork.isInRange(ip, codFilterAllowDisableConfig.getDisable());

            }
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public boolean state() {
        return "1".equals(codFilterAllowDisableConfig.getState());
    }

    @Override
    public String name() {
        return "codFilterAllowDisable";
    }

    @Override
    public String alias() {
        return "黑白名单过滤器";
    }

    @Override
    public int sort() {
        return 0;
    }

    @Override
    public String mapping() {
        return null;
    }
}
