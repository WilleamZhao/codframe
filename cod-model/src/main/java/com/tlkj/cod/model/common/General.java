/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.model.common;

import net.sf.json.JSON;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.protocol.HttpClientContext;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.spi.http.HttpContext;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Desc 通用获取参数方法
 *
 * @author sourcod
 * @version 1.0
 * @className General
 * @date 2019/3/24 4:46 PM
 */
public abstract class General {

    /**
     * 获取参数方法
     * 1. 从 parameter 里取
     * 2. 从 attribute 里取
     * 3. 从 body 里取
     * 4. 从 header 里取
     * @param request 请求
     * @param name    参数名
     * @param isStream 是否从流里读取
     * @return 参数值
     */
    protected String getParams(HttpServletRequest request, String name, boolean isStream){
        String value = request.getParameter(name);
        if (StringUtils.isEmpty(value)){
            Object o = request.getAttribute(name);
            if (o == null){
                if (!isMultipart(request) && isStream){
                    value = charReader(request);
                    if (StringUtils.isBlank(value)){
                        return request.getHeader(name);
                    }
                    return value;
                }
                return "";
            }
            return String.valueOf(o);
        }
        return value;
    }

    /**
     * 获取参数方法
     * 1. 从 parameter 里取
     * 2. 从 attribute 里取
     * 3. 从 body 里取
     * 4. 从 header 里取
     * @param request 请求
     * @param name    参数名
     * @return 参数值
     */
    protected String getParams(HttpServletRequest request, String name){
        return getParams(request, name, false);
    }

    /**
     * 获取参数方法
     * 1. 从 header 里取
     * @param request 请求
     * @param name    参数名
     * @return 参数值
     */
    protected String getParamsByHeader(HttpServletRequest request, String name){
        String value = request.getHeader(name);
        return StringUtils.isEmpty(value) ? "" : value;
    }

    /**
     * 获取参数方法
     * 1. 从 param 里取
     * @param request 请求
     * @param name    参数名
     * @return 参数值
     */
    protected String getParamsByParam(HttpServletRequest request, String name){
        String value = request.getParameter(name);
        return StringUtils.isEmpty(value) ? "" : value;
    }

    /**
     * 获取参数方法
     * 1. 从 body 里取
     * @param request 请求
     * @param name    参数名
     * @return 参数值
     */
    protected String getParamsByBody(HttpServletRequest request, String name){
        String value = request.getHeader(name);
        if (!isMultipart(request)){
            return charReader(request);
        }
        return StringUtils.isEmpty(value) ? "" : value;
    }

    /**
     * 获取参数方法
     * 1. 从 attribute 里取
     * @param request 请求
     * @param name    参数名
     * @return 参数值
     */
    protected Object getParamsByAttribute(HttpServletRequest request, String name){
        return request.getAttribute(name);
    }

    /**
     * 字符读取请求 body
     * @param request
     * @return
     */
    private String charReader(HttpServletRequest request){
        String str;
        StringBuilder wholeStr = new StringBuilder();
        try {
            BufferedReader br = request.getReader();
            while((str = br.readLine()) != null){
                wholeStr.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wholeStr.toString();
    }

    /**
     * 二进制读取请求 body
     * @param request
     * @return
     */
    private byte[] binaryReader(HttpServletRequest request){
        int len = request.getContentLength();
        byte[] buffer = new byte[len];
        ServletInputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            inputStream.read(buffer, 0, len);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer;
    }

    /**
     * 判断是否是上传文件类型
     * @param request
     * @return
     */
    private boolean isMultipart(HttpServletRequest request){
        String contentType = request.getContentType();
        if ("multipart/form-data".equals(contentType)) {
            return true;
        }
        return false;
    }
}
