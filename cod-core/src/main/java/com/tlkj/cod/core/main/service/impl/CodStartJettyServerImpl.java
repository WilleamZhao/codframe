/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.main.service.impl;

import com.tlkj.cod.core.listener.LogoListener;
import com.tlkj.cod.launcher.init.CodServerInitialize;
import com.tlkj.cod.core.main.CodSpringConfiguration;
import com.tlkj.cod.core.main.service.CodStartServer;
import com.tlkj.cod.core.model.bo.CodStartModel;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.EventListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Desc cod启动JettyServer
 *
 * @author sourcod
 * @version 1.0
 * @className CodStartJettyServerImpl
 * @date 2019/4/10 2:33 PM
 */
@Primary
@Component
public class CodStartJettyServerImpl implements CodStartServer {

    private static Server server = null;
    private static CodStartModel startModel = CodStartModel.getInstance();

    private static final int DEFAULT_PORT = 8080;
    private static final String CONTEXT_PATH = "/";
    private static final String MAPPING_URL = "/*";

    /**
     * filter
     */
    private static List<Filter> filterList = new ArrayList<>();

    /**
     * listener
     */
    private static List<EventListener> listenerList = new ArrayList<>();

    private DispatcherServlet dispatcherServlet = null;
    private CodServerInitialize startServerInit = null;

    @Override
    public void init(CodServerInitialize startServerInit) {
        this.startServerInit = startServerInit;
    }

    @Override
    public void start() {
        // startServerInit.init();

        setServerInfo();

        // TODO
        // HiddenHttpMethodFilter a = new HiddenHttpMethodFilter();
        // a.setMethodParam("X-HTTP-Method-Override");
        /*
        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.register(MainConfiguration.class);
        annotationConfigWebApplicationContext.setServletContext(new ContextHandler.StaticContext());

        DispatcherServlet dispatcherServlet = new DispatcherServlet(annotationConfigWebApplicationContext);
        dispatcherServlet.refresh();
        */
        // initSpring();

        // initServlet();

        ServletHolder servletHolder = new ServletHolder(dispatcherServlet);

        ServletContextHandler context = new ServletContextHandler();
        context.addServlet(servletHolder, "/*");

        // 初始化
        // context.setInitParameter("contextInitializerClasses", CodInitializer.class.getName());

        // 动态添加filter
        if (filterList.size() != 0){
            for (int i = 0; i < filterList.size(); i++){
                // FilterConfig filterConfig = new HolderConfig;
                ServletHandler servletHandler = new ServletHandler();
                FilterHolder filterHolder = new FilterHolder(filterList.get(i));
                // filterHolder.setInitParameter();
                servletHandler.addFilterWithMapping(filterHolder, "/*", EnumSet.of(DispatcherType.REQUEST));
            }
        }

        // 动态添加listener
        if (listenerList.size() != 0){
            for (int i = 0; i < listenerList.size(); i++){
                context.addEventListener(listenerList.get(i));
            }
        }

        context.addEventListener(new LogoListener());

        // context.addEventListener(new ContextLoaderListener(annotationConfigWebApplicationContext));
        // context.addEventListener(new RequestContextListener());

        server = new Server(startModel.getPort());
        server.setHandler(context);

        server.setStopAtShutdown(true);

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(startModel.getPort());
        String host = startModel.getIp();
        if (StringUtils.isNotBlank(host)){
            connector.setHost(host);
        }

        connector.setReuseAddress(false);
        server.setConnectors(new Connector[] { connector });

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            System.err.println("启动jetty服务失败");
            e.printStackTrace();
        }
    }

    /**
     * init spring
     */
    private void initSpring(){
        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.register(CodSpringConfiguration.class);
        annotationConfigWebApplicationContext.setServletContext(new ContextHandler.StaticContext());
        dispatcherServlet = new DispatcherServlet(annotationConfigWebApplicationContext);
        dispatcherServlet.refresh();
    }

    private void initServlet(){

    }

    private void setServerInfo(){
        ContextHandler.setServerInfo("");
    }

    @Override
    public void stop() {
        try {
            server.stop();
            String name = ManagementFactory.getRuntimeMXBean().getName();
            String pid = name.split("@")[0];
            String os = System.getProperty("os.name");
            if (os != null && os.startsWith("Windows")){
                Runtime.getRuntime().exec("Taskkill /f /IM " + pid);
            }else{
                String[] cmd ={"sh","-c","kill -9 "+pid};
                Runtime.getRuntime().exec(cmd);
            }
        } catch (Exception e) {
            System.err.println("关闭服务异常");
        }
    }

    private ServletContextHandler servletContextHandler(WebApplicationContext context) {
        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath(CONTEXT_PATH);
        handler.addServlet(new ServletHolder(new DispatcherServlet(context)), MAPPING_URL);
        handler.addEventListener(new ContextLoaderListener(context));
        return handler;
    }

    private WebApplicationContext webApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(CodSpringConfiguration.class);
        return context;
    }



    @Override
    public void restart() {
        this.stop();
        // 等待3秒
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.start();
    }

    @Override
    public void setFilter(LinkedList<Filter> filters) {

    }

    @Override
    public void addFilter(LinkedList<Filter> filters) {

    }

    @Override
    public void addFilter(Filter filter) {

    }

    @Override
    public void setListener(LinkedList<EventListener> eventListeners) {

    }

    @Override
    public void addListener(LinkedList<EventListener> eventListeners) {

    }

    @Override
    public void addListener(EventListener eventListener) {

    }

    @Override
    public void setServlet(LinkedList<Servlet> servlets) {

    }

    private static WebAppContext getWebAppContext(){
        WebAppContext context = new WebAppContext();

        context.setContextPath(startModel.getProjectName());
        context.setDescriptor(startModel.getBaseDir());
        context.setResourceBase(startModel.getBaseDir());

        // context.setDisplayName(system.getCodframe().getProject());

        // context.setResourceBase(WebAppInitializer.class.);
        context.setParentLoaderPriority(true);



        /*

        context.addEventListener(new ContextLoaderListener());
        // 注解配置
        context.setInitParameter("contentClass", AnnotationConfigWebApplicationContext.class.getName());
        System.out.println(SpringConfiguration.class.getName());
        //"classpath*:"+
        context.setInitParameter("contextConfigLocation", "classpath*:" + SpringConfiguration.class.getName());
        // logo信息
        context.addEventListener(new LogoListener());
        // content
        context.addEventListener(new ContextLoaderListener());
        context.addEventListener(new RequestContextListener());

        context.addEventListener(new ScheduledAnnotationBeanPostProcessor());
        //context.addEventListener(new SpringConfiguration());

        ServletHandler handler = new ServletHandler();

        // cors
        FilterHolder corsFilter = context.addFilter(CorsFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

        // shiro
        FilterHolder shiroFilter = context.addFilter(DelegatingFilterProxy.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        shiroFilter.setInitParameter("targetFilterLifecycle", "true");

        // characterEncodingFilter
        FilterHolder characterEncodingFilter = context.addFilter(CharacterEncodingFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        characterEncodingFilter.setInitParameter("encoding", "UTF-8");
        characterEncodingFilter.setInitParameter("forceEncoding", "true");
        characterEncodingFilter.setFilter(new CharacterEncodingFilter());

        */

        // context.addFilter(new DispatcherServlet(), "/*", "")
        // WebXmlConfiguration webXmlConfiguration = new WebXmlConfiguration();
        // WebAppContext webAppContext = new WebAppContext();
        // context.setConfigurations(new Configuration[]{webXmlConfiguration});


        /*
        MetaData metaData = _ctx.getMetaData();
        Resource webappInitializer = Resource.newResource(WebAppInitializer.class.getProtectionDomain().getCodeSource().getLocation());
        metaData.addContainerResource(webappInitializer);
        AnnotationConfiguration config = new AnnotationConfiguration();
        context.setConfigurations(new Configuration[] { config });
        List<Handler> handlers = new ArrayList<Handler>();
        handlers.add(context);
        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(handlers.toArray(new Handler[0]));
        HandlerCollection handlerCollection = new HandlerCollection();
        handlerCollection.setHandlers(new Handler[] { handlerList });
        */
        return context;
    }
}
