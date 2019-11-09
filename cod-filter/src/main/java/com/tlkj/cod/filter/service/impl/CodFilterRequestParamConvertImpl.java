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
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
 * Desc 参数转换 Filter
 * 把参数统一转成Attribute
 *
 * @author sourcod
 * @version 1.0
 * @className CodFilterRequestParamConvertImpl
 * @date 2019/3/12 1:33 PM
 */
@Service("codFilterRequestParamConvert")
public class CodFilterRequestParamConvertImpl implements CodFilterService {

    private static Logger logger = LoggerFactory.getLogger(CodFilterRequestParamConvertImpl.class);

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String type = httpServletRequest.getContentType();
        JSONObject jsonObject = null;
        if (StringUtils.isNotBlank(type)){
            switch (type){
                case "application/json":
                    jsonObject = getJSONParam(httpServletRequest);
                    Iterator iterator = jsonObject.keys();
                    while (iterator.hasNext()){
                        String key = iterator.next().toString();
                        String value = jsonObject.getString(key);
                        request.setAttribute(key, value);
                    }
                    break;
                case "application/xml":

                    break;
                case "text/html":

                    break;
                case "text/xml":

                    break;
                case "text/plain":

                    break;
                case "application/octet-stream":

                    break;
                case "application/x-www-form-urlencoded":
                    Map<String, String[]> map = request.getParameterMap();
                    if (map != null){
                        for (String s : map.keySet()){
                            request.setAttribute(s, map.get(s));
                        }
                    }
                    break;
                default:

                    break;
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
            // 写入数据到 StringBuilder
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

    @Override
    public String name() {
        return "codFilterRequestParamConvert";
    }

    @Override
    public String alias() {
        return "请求参数转换过滤器";
    }

    @Override
    public int sort() {
        return 0;
    }
}
