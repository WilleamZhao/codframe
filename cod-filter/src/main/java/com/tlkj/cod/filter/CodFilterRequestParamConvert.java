/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.filter;

import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

/**
 * Desc 参数转换Filter
 * 把参数统一转成Attribute
 *
 * @author sourcod
 * @version 1.0
 * @className CodFilterRequestParamConvert
 * @date 2019/3/12 1:33 PM
 */
public class CodFilterRequestParamConvert implements Filter {

    private static Logger logger = LoggerFactory.getLogger(CodFilterRequestParamConvert.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String a = request.getContentType();
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String type = httpServletRequest.getContentType();
        JSONObject jsonObject = null;
        if (StringUtils.isNotBlank(type)){
            if (type.contains("application/json")) {
                jsonObject = getJSONParam(httpServletRequest);
                Iterator iterator = jsonObject.keys();
                while (iterator.hasNext()){
                    String key = iterator.next().toString();
                    String value = jsonObject.getString(key);
                    request.setAttribute(key, value);
                }
            } else if (type.contains("application/xml")){
                // 根据xml头指定的编码格式来编码

            } else if (type.contains("text/html")){
                // html格式的正文

            } else if (type.contains("text/xml")){
                // 忽略xml头所指定编码格式而默认采用us-ascii编码

            } else if (type.contains("text/plain")){
                // 无格式正文

            } else if(type.contains("application/octet-stream")){
                // 二进制

            } else if (type.contains("application/x-www-form-urlencoded")){
                Map<String, String[]> map = request.getParameterMap();
                if (map != null){
                    for (String s : map.keySet()){
                        request.setAttribute(s, map.get(s));
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }


    /**
     * json 转对象
     */
    private JSONObject getJSONParam(HttpServletRequest request){
        JSONObject jsonParam = null;
        try {
            // 获取输入流
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            // 写入数据到Stringbuilder
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = streamReader.readLine()) != null) {
                sb.append(line);
            }
            jsonParam = JSONObject.fromObject(sb.toString());
            // 直接将json信息打印出来
            logger.debug("参数={}", jsonParam.toString());
        } catch (Exception e){
            System.err.println("转换参数错误");
        }
        return jsonParam;
    }
}
