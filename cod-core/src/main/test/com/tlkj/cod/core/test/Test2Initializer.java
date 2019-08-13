/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.core.test;

import com.tlkj.cod.core.main.CodSpringConfiguration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className Test2Initializer
 * @date 2019/4/23 7:16 PM
 */
public class Test2Initializer {//implements WebApplicationInitializer {

    // @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(CodSpringConfiguration.class);
        ctx.setServletContext(servletContext);
        // ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet());
        // servlet.addMapping("/");
        // servlet.setLoadOnStartup(1);
    }
}
