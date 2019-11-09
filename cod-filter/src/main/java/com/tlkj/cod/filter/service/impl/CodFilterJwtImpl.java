/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.filter.service.impl;

import com.tlkj.cod.common.ComCommonJwt;
import com.tlkj.cod.filter.common.CodFilterCommon;
import com.tlkj.cod.filter.service.CodFilterService;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.enums.StatusCode;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Desc jwt 过滤器模块
 *
 * @author sourcod
 * @version 1.0
 * @className JWTFilter
 * @date 2018/6/30 下午4:19
 */
@Service("codFilterJwt")
public class CodFilterJwtImpl implements CodFilterService {

    private final static Logger logger = LoggerFactory.getLogger(CodFilterJwtImpl.class);

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI=request.getRequestURI();
        String tokenStr=request.getParameter("token");
        String token = "";
        if(requestURI.contains("/api/")){
            token=request.getHeader("token");
            if(token==null && tokenStr==null){
                System.out.println("real token:======================is null");
                String str = "{'errorCode':801,'message':'缺少token，无法验证','data':null}";
                Response response1 = new Response();
                response1.setName(StatusCode.JWT_ERROR_CODE.getStatusName());
                response1.setCode(StatusCode.JWT_ERROR_CODE.getStatusCode());
                response1.setMsg(StatusCode.JWT_ERROR_CODE.getStatusDesc());
                CodFilterCommon.response(response, response1);
                return;
            }
            if(tokenStr!=null){
                token=tokenStr;
            }
            try {
                Claims claims = ComCommonJwt.parseJWT(token);
                response.setHeader("token",token);
                System.out.println("real token:=============================="+token);
                System.out.println("real ohter:=============================="+request.getHeader("Cookie"));
            } catch (Exception e){
                System.out.println("token错误");
                logger.info("token错误");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    @Override
    public String name() {
        return "codFilterJwt";
    }

    @Override
    public String alias() {
        return "JWT 验证过滤器";
    }

    @Override
    public int sort() {
        return 0;
    }
}
