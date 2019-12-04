/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.filter;


import com.tlkj.cod.common.CodCommonSpringContext;
import com.tlkj.cod.core.spring.SpringContextUtil;
import com.tlkj.cod.data.service.CodDataService;
import com.tlkj.cod.data.service.impl.CodDataServiceImpl;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodFilterLicense
 * @date 2019/12/3 6:24 PM
 */
public class CodFilterLicense implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * license
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        CodDataService codDataService = CodCommonSpringContext.getBean("codData", CodDataService.class);
        System.out.println(codDataService);
        String projectName = codDataService.getDataValue("cod.config.project.name");
        String license = codDataService.getDataValue(projectName + "-license");
        if (StringUtils.isNotBlank(license) && "1".equals(license) ){
            chain.doFilter(request, response);
        } else {
            String str="{'code':0901,'msg':'license过期','data':null, 'name': '操作失败'}";
            dealErrorReturn(response, str);
        }
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
    private void dealErrorReturn(ServletResponse httpServletResponse, Object obj){
        String json = (String)obj;
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
