/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.test;

import com.tlkj.cod.core.main.CodSpringConfiguration;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.Log4jConfigListener;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className WebAppInitializer
 * @date 2018/12/25 5:11 PM
 */
public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        initializeSpringConfig(servletContext);
        initializeSpringMVCConfig(servletContext);
        initializeLog4jConfig(servletContext);
    }

    private void initializeSpringConfig(ServletContext container) {
        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(CodSpringConfiguration.class);
        // Manage the life cycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));
    }

    private void initializeSpringMVCConfig(ServletContext container) {
        // Create the spring rest servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(CodSpringConfiguration.class);
        // Register and map the spring rest servlet
        ServletRegistration.Dynamic dispatcher = container.addServlet("SpringMvc",
                new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(2);
        dispatcher.setAsyncSupported(true);
        dispatcher.addMapping("/springmvc/*");
    }

    private void initializeLog4jConfig(ServletContext container) {
        // Log4jConfigListener
        container.setInitParameter("log4jConfigLocation", "file:${rdm.home}/log4j.properties");
        container.addListener(Log4jConfigListener.class);
        PropertyConfigurator.configureAndWatch(System.getProperty("rdm.home") + "/log4j.properties", 60);
    }


    private void registerFilter(ServletContext container) {
        initializeSAMLFilter(container);
    }

    private void registerListener(ServletContext container) {
        container.addListener(RequestContextListener.class);
    }

    private void initializeSAMLFilter(ServletContext container) {
        FilterRegistration.Dynamic filterRegistration = container.addFilter("SAMLFilter", DelegatingFilterProxy.class);
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");
        filterRegistration.setAsyncSupported(true);
    }

}
