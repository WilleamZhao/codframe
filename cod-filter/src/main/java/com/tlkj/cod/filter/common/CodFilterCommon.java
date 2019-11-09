/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.filter.common;

import com.tlkj.cod.model.common.Response;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Desc 过滤器通用方法
 *
 * @author sourcod
 * @version 1.0
 * @className CodFilterCommon
 * @date 2019/11/8 8:21 PM
 */
public class CodFilterCommon {

    /**
     * 直接返回响应
     * @param httpServletResponse
     * @param response            返回对象
     * @return
     */
    public static void response(HttpServletResponse httpServletResponse, Response response) {
        String json = response.toString();
        PrintWriter writer = null;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html; charset=utf-8");
        try {
            writer = httpServletResponse.getWriter();
            writer.print(json);
        } catch (IOException ex) {

        } finally {
            if (writer != null){
                writer.close();
            }
        }
    }
}
