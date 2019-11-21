/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.filter.service.impl;

import com.tlkj.cod.config.model.config.CodCoreConfig;
import com.tlkj.cod.core.spring.SpringContextUtil;
import com.tlkj.cod.filter.model.config.CodFilterCorsConfig;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 全局跨域设置
 * @author sourcod
 */
@Service("codFilterCors")
public class CodFilterCorsImpl implements CodFilterService {

    private static Pattern pattern = Pattern.compile("MSIE (\\d+)");

    private CodFilterCorsConfig codFilterCorsConfig;
    private CodCoreConfig codCoreConfig;

    @Autowired
    public CodFilterCorsImpl(CodFilterCorsConfig codFilterCorsConfig){
        this.codFilterCorsConfig = codFilterCorsConfig;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (codFilterCorsConfig == null){
            this.codFilterCorsConfig = SpringContextUtil.getApplicationContext().getBean(CodFilterCorsConfig.class);
        }

        if (codCoreConfig == null){
            this.codCoreConfig = (CodCoreConfig) SpringContextUtil.getApplicationContext().getBean("codCoreConfig");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String agent = request.getHeader("User-Agent");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 如果是IE9以下设置text/html, 解决下载问题
        if (verifyBrowserIe(agent)){
            response.setContentType("text/html;charset=utf-8");
        }

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Authorization, cod_admin_token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // TODO 动态返回服务版本
        response.setHeader("server", "codFrame v" + codCoreConfig.getFrameVersion());
        // response.setHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(servletRequest, servletResponse);
        // User-Agent: Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0)
    }

    /**
     * 验证浏览器是否是ie9以下
     * @param agent agent
     * @return 是
     */
    private boolean verifyBrowserIe(String agent){

        // TODO 没有agent情况
        if (StringUtils.isBlank(agent)){
            return false;
        }

        Matcher matcher = pattern.matcher(agent);
        if (matcher.find()){
            int ieVersion = Integer.parseInt(matcher.group(1));
            return ieVersion <= 9;
        }
        /*
        if (agent.indexOf("MSIE 7") > 0){
            return true;
        }

        if (agent.indexOf("MSIE 8") > 0){
            return true;
        }

        if (agent.indexOf("MSIE 9") > 0){
            return true;
        }*/
        return false;
    }

    @Override
    public void destroy() {

    }

    @Override
    public boolean state() {
        return "1".equals(codFilterCorsConfig.getState());
    }

    @Override
    public String name() {
        return "codFilterCors";
    }

    @Override
    public String alias() {
        return "CORS 跨域过滤器";
    }


    @Override
    public int sort() {
        return 0;
    }
}
