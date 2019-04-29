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

import com.tlkj.cod.core.filter.ParameterRequestFileter;
import com.tlkj.cod.core.listener.LogoListener;
import com.tlkj.cod.core.main.CodSpringConfiguration;
import com.tlkj.cod.core.main.service.CodStartServer;
import com.tlkj.cod.core.model.bo.CodStartModel;
import com.tlkj.cod.core.model.bo.CodStartTomcatModel;
import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodServerInitialize;
import com.tlkj.cod.launcher.model.LauncherModel;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.util.EventListener;
import java.util.LinkedList;

/**
 * Desc cod启动tomcatService
 *
 * @author sourcod
 * @version 1.0
 * @className CodStartServerImpl
 * @date 2019/4/9 7:26 PM
 */
@Component
public class CodStartTomcatServerImpl implements CodStartServer, CodModuleInitialize {

    private static Tomcat tomcat = null;
    private static Logger logger = LoggerFactory.getLogger(CodStartTomcatServerImpl.class);

    private static String DEFAULT_PROTOCOL = "org.apache.coyote.http11.Http11NioProtocol";

    private CodStartModel startModel = CodStartTomcatModel.getInstance();
    private CodStartTomcatModel tomcatModel = new CodStartTomcatModel();

    private static final String CONTEXT_PATH = "";

    private static String base = "java.io.tempdir";

    private static StandardContext standardContext = null;
    private DispatcherServlet dispatcherServlet = null;
    private boolean initSpring = false;

    @Override
    public void init(CodServerInitialize codStartServerInit) {

    }

    private void initTomcat(){
        tomcat = new Tomcat();
        /*
         * set host
         */
        StandardHost host = new StandardHost();
        host.setAppBase(base);
        host.setDeployOnStartup(true);
        host.setAutoDeploy(true);
        host.setName("localhost");
        standardContext = new StandardContext();
        standardContext.setPath(CONTEXT_PATH);
        standardContext.addLifecycleListener(new Tomcat.FixContextListener());
        host.addChild(standardContext);

        tomcat.setHost(host);
    }

    private void initSpring(){
        if (!initSpring){
            /*
            StandardContext standardContext = new StandardContext();
            standardContext.setPath(CONTEXT_PATH);
            standardContext.addLifecycleListener(new Tomcat.FixContextListener());
            */

            AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
            annotationConfigWebApplicationContext.register(CodSpringConfiguration.class);
            // setServletContext
            annotationConfigWebApplicationContext.setServletContext(new ApplicationContext(standardContext));

            dispatcherServlet = new DispatcherServlet(annotationConfigWebApplicationContext);
            dispatcherServlet.refresh();
            initSpring = true;
        }
    }

    /**
     * 启动服务
     */
    @Override
    public void start() {

        // initSpring();

        tomcat = new Tomcat();



        /*
         * set host
         */
        StandardHost host = new StandardHost();
        host.setAppBase(base);
        host.setDeployOnStartup(true);
        host.setAutoDeploy(true);
        host.setName("localhost");

        // StandardContext standardContext = new StandardContext();
        initTomcat();
        // host.addChild(standardContext);

        // tomcat.setHost(host);

        /*
         * connector
         */
        Connector connector = new Connector(DEFAULT_PROTOCOL);
        connector.setPort(8080);
        connector.setURIEncoding("UTF-8");
        tomcat.setConnector(connector);

        /*
         * spring annotation
         */
        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.register(CodSpringConfiguration.class);
        // setServletContext
        annotationConfigWebApplicationContext.setServletContext(new ApplicationContext(standardContext));

        DispatcherServlet dispatcherServlet = new DispatcherServlet(annotationConfigWebApplicationContext);
        dispatcherServlet.refresh();

        // standardContext.addApplicationListener(ContextClassLoaderLocal.class.getName());
        standardContext.addApplicationListener(LogoListener.class.getName());

        // Context context = tomcat.addContext(host, "/*", new File(base).getAbsolutePath());

        /*
         * 2. add dispatcherServlet
         */
        tomcat.addServlet(CONTEXT_PATH, "dispatcher", dispatcherServlet);
        standardContext.addServletMappingDecoded("/*", "dispatcher");


        // tomcat.getHost().addChild(standardContext);
        // context.getServletContext();
        Class servletClass = DispatcherServlet.class;
        String servletName = servletClass.getSimpleName();

        // tomcat.addServlet("", servletName, dispatcherServlet);

        // Tomcat.addServlet(context, servletName, servletClass.getName());

        // context.addServletMappingDecoded("/*", servletName);

        // context.addApplicationListener(LogoListener.class.getName());
        // context.addApplicationListener(ContextLoaderListener.class.getName());

        // annotationConfigWebApplicationContext.setServletContext(context.getServletContext());
        // annotationConfigWebApplicationContext.refresh();
        // context = tomcat.addWebapp(host,"/*", new File(".").getAbsolutePath());
        // tomcat.addServlet("/*", "dispatcher", dispatcherServlet);
        // ServletContext servletContext = context.getServletContext();
        // servletContext.addServlet("/a", dispatcherServlet);
        // context.addServletMappingDecoded("/*", "dispatcher");

        // context.addServletMappingDecoded("/*", "dispatcher");


        StandardServer standardServer = (StandardServer) tomcat.getServer();
        AprLifecycleListener lifecycleListener = new AprLifecycleListener();
        standardServer.addLifecycleListener(lifecycleListener);


        // context.addApplicationListener(ContextLoaderListener.class.getName());

        // host.setContextClass(Tomcat.DefaultWebXmlListener.class.getName());
        String a = tomcat.getHost().getConfigClass();
        System.out.println(a);
        FilterDef filter1definition = new FilterDef();
        // filter1definition.setFilter(new ParameterRequestFileter());
        filter1definition.setFilterClass(ParameterRequestFileter.class.getName());
        filter1definition.setFilterName(ParameterRequestFileter.class.getSimpleName());
        // filter1definition.
        //context.addFilterDef(filter1definition);


        FilterMap filterMap = new FilterMap();
        filterMap.setFilterName(ParameterRequestFileter.class.getName());
        filterMap.addURLPattern("/*");
        // standardContext.addFilterDef(filter1definition);
        // context.addFilterMap(filterMap);

        // context.addApplicationListener(ContextLoaderListener.class.getName());

        // tomcat.getServer().addLifecycleListener(lifecycleListener);
        // context.addServletContainerInitializer(new SpringServletContainerInitializer(),  new HashSet<>());



        // context.addServletMappingDecoded("/go", DispatcherServlet.class.getName());

        // tomcat.getDefaultWebXmlListener();

        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            System.err.println("启动tomcat失败");
            logger.error("启动tomcat失败, {}", e.getMessage());
            e.printStackTrace();
        }
    }

    private Host initHost(){
        StandardHost host = new StandardHost();
        host.setAppBase(base);
        host.setDeployOnStartup(true);
        host.setAutoDeploy(true);
        host.setName("localhost");

        // StandardContext standardContext = new StandardContext();

        host.addChild(standardContext);
        return host;
    }

    private Connector initConnector(){
        Connector connector = new Connector(DEFAULT_PROTOCOL);
        connector.setPort(8080);
        connector.setURIEncoding("UTF-8");
        tomcat.setConnector(connector);
        return connector;
    }

    @Override
    public void stop() {
        try {
            tomcat.stop();
        } catch (LifecycleException e) {
            System.err.println("关闭tomcat失败");
            e.printStackTrace();
        }
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

    @Override
    public int order() {
        return 0;
    }

    @Override
    public void init(LauncherModel launcherModel) {

    }

    @Override
    public void fail(Throwable e) {

    }
}
