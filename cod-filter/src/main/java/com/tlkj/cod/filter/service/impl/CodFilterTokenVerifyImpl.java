/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.filter.service.impl;

import com.tlkj.cod.cache.CodCacheManager;
import com.tlkj.cod.common.constant.CodCommonParamDefined;
import com.tlkj.cod.core.spring.SpringContextUtil;
import com.tlkj.cod.filter.common.CodFilterCommon;
import com.tlkj.cod.filter.service.CodFilterService;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.enums.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Desc token验证
 *
 * @author sourcod
 * @version 1.0
 * @className CodFilterTokenVerifyImpl
 * @date 2019/11/8 8:04 PM
 */
@Service("codFilterTokenVerify")
public class CodFilterTokenVerifyImpl implements CodFilterService {

    private CodCacheManager codCacheManager;

    @Override
    public boolean state() {
        return false;
    }

    @Override
    public String name() {
        return "codFilterTokenVerify";
    }

    @Override
    public String alias() {
        return "token 验证过滤器";
    }

    @Override
    public int sort() {
        return 0;
    }

    @Override
    public String mapping() {
        return null;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
         this.codCacheManager = (CodCacheManager) SpringContextUtil.getBean("codCacheManager");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 获取请求 url
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);

        // 1.获取全路径
        //得到http://localhost:8888/CRM/loginController/login
        StringBuffer url1 = request.getRequestURL();
        System.out.println(url1.toString());

        // 2.获取协议名和域名
        //得到协议名 例如：http
        String scheme = request.getScheme();
        System.out.println(scheme);

        //得到域名 localhost
        String serverName = request.getServerName();
        System.out.println(serverName);

        // 3.获取请求所有参数  //map类型
        // request.getParameterMap();
        // System.out.println(serverName);

        // 4.获取项目名
        //   /CRM
        String contextPath = request.getContextPath();
        System.out.println(contextPath);

        // 5.获取请求方法
        //   /loginController/login
        String servletPath = request.getServletPath();
        System.out.println(servletPath);
        String pathInfo = request.getPathInfo();
        System.out.println(pathInfo);

        // 获取 token
        String tokenStr = request.getParameter(CodCommonParamDefined.API_TOKEN_NAME);
        if (StringUtils.isBlank(tokenStr)) {
            tokenStr = request.getHeader(CodCommonParamDefined.API_TOKEN_NAME);
        }

        // 如果 token 为空直接返回错误代码
        if(StringUtils.isBlank(tokenStr)){
            Response response1 = new Response();
            response1.setName(StatusCode.TOKEN_ERROR_CODE.getStatusName());
            response1.setCode(StatusCode.TOKEN_ERROR_CODE.getStatusCode());
            response1.setMsg(StatusCode.TOKEN_ERROR_CODE.getStatusDesc());
            CodFilterCommon.response(response, response1);
            return;
        }

        String tokenValue = codCacheManager.get(tokenStr, String.class);
        System.out.println(tokenValue);

        Enumeration enumeration = request.getParameterNames();
        Map<String, String> map = new TreeMap<>();
        Set<String> key = new HashSet<>();
        while (enumeration.hasMoreElements()){
            String paramName = (String) enumeration.nextElement();
            String paramValue = request.getParameter(paramName);
            key.add(paramName);
            map.put(paramName, paramValue);
        }

        /*

        try {
            Claims claims = ComCommonJwt.parseJWT(token);
            response.setHeader("token",token);
            System.out.println("real token:=============================="+token);
            System.out.println("real ohter:=============================="+request.getHeader("Cookie"));
        } catch (Exception e){
            System.out.println("token错误");
            logger.info("token错误");
        }
 */
        filterChain.doFilter(servletRequest, servletResponse);

    }

    public static void main(String[] args) {
        CodFilterTokenVerifyImpl codFilterTokenVerify = new CodFilterTokenVerifyImpl();
        // codFilterTokenVerify.doFilter();
    }

    @Override
    public void destroy() {

    }
}
