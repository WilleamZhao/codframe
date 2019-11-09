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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Desc 初始化设置 过滤器
 *
 * @author sourcod
 * @version 1.0
 * @className CodFilterInitAdminImpl
 * @date 2019/9/3 2:35 PM
 */
public class CodFilterInitAdminImpl implements CodFilterService {

    private static Logger logger = LoggerFactory.getLogger(CodFilterInitAdminImpl.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("进入初始化过滤器");
        // DataSource dataSource = CodDaoConnectionPool.getInstance().getDataSource(CodDaoDatasourceTypeEnum.DEFAULT.name());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    @Override
    public String name() {
        return "初始化 admin 模块";
    }

    @Override
    public String alias() {
        return null;
    }

    @Override
    public int sort() {
        return 0;
    }
}
