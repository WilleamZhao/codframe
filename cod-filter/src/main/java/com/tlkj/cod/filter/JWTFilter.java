/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.filter;

import com.tlkj.cod.common.ComCommonJwt;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className JWTFilter
 * @date 2018/6/30 下午4:19
 */
@Component
public class JWTFilter implements Filter {
    private final static Logger logger = LoggerFactory.getLogger(JWTFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI=request.getRequestURI();
        String tokenStr=request.getParameter("token");
        String token="";
        if(requestURI.contains("/api/")){
            token=request.getHeader("token");
            if(token==null && tokenStr==null){
                System.out.println("real token:======================is null");
                String str="{'errorCode':801,'message':'缺少token，无法验证','data':null}";
                dealErrorReturn(response,str);
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


    /**
     *
     * desc 检测到没有token，直接返回不验证
     * @author sourcod
     * @date 2018/6/30
     * @param
     * @return
     **/
    private void dealErrorReturn(HttpServletResponse httpServletResponse,Object obj){
        String json = (String)obj;
        PrintWriter writer = null;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html; charset=utf-8");
        try {
            writer = httpServletResponse.getWriter();
            writer.print(json);

        } catch (IOException ex) {
            logger.error("response error",ex);
        } finally {
            if (writer != null){
                writer.close();
            }
        }
    }

}
