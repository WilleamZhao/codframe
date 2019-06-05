/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.server.service.impl;

import com.tlkj.cod.launcher.model.CodModuleLauncherModel;
import com.tlkj.cod.server.model.CodServerFilterModel;
import com.tlkj.cod.server.model.server.CodServerTomcatModel;
import com.tlkj.cod.server.service.CodServerService;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.ApplicationContext;
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

import java.util.EventListener;
import java.util.LinkedList;
import java.util.Map;

/**
 * Desc cod启动tomcatService
 *
 * @author sourcod
 * @version 1.0
 * @className CodStartServerImpl
 * @date 2019/4/9 7:26 PM
 */
@Component("codServerTomcat")
public class CodServerServiceTomcatImpl implements CodServerService {

    private static Tomcat tomcat = null;
    private static Logger logger = LoggerFactory.getLogger(CodServerServiceTomcatImpl.class);

    private String DEFAULT_PROTOCOL = "";

    private String CONTEXT_PATH = "";

    private static String base = "java.io.tempdir";

    private static StandardContext standardContext = null;
    private DispatcherServlet dispatcherServlet = null;
    private boolean initSpring = false;

    private CodModuleLauncherModel codModuleLauncherModel;


    private void initSpring(){
        if (!initSpring){
            /*
            StandardContext standardContext = new StandardContext();
            standardContext.setPath(CONTEXT_PATH);
            standardContext.addLifecycleListener(new Tomcat.FixContextListener());
            */

            AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
            // annotationConfigWebApplicationContext.register(CodSpringConfiguration.class);
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
    public void start(CodModuleLauncherModel codModuleLauncherModel) {
        this.codModuleLauncherModel = codModuleLauncherModel;
        // 设置服务信息
        // setServerInfo();
        CodServerTomcatModel codServerModel = (CodServerTomcatModel) this.codModuleLauncherModel.getServer();

        if (tomcat != null){
            return;
        }

        tomcat = new Tomcat();

        /*
         * set host
         */
        StandardHost host = new StandardHost();
        host.setAppBase(codServerModel.getBaseDir());
        host.setDeployOnStartup(true);
        host.setAutoDeploy(true);
        host.setName(codServerModel.getHost());

        this.CONTEXT_PATH = codServerModel.getContextPath();
        this.DEFAULT_PROTOCOL = codServerModel.getProtocol();

        StandardContext standardContext = new StandardContext();
        standardContext.setPath(CONTEXT_PATH);
        standardContext.addLifecycleListener(new Tomcat.FixContextListener());
        host.addChild(standardContext);

        tomcat.setHost(host);

        AnnotationConfigWebApplicationContext applicationContext = (AnnotationConfigWebApplicationContext) codModuleLauncherModel.getSpring();

        // 注册springMVC
        // applicationContext.register(CodServerSpringMVCConfiguration.class);

        applicationContext.setServletContext(new ApplicationContext(standardContext));
        // applicationContext.register(CodSpringConfiguration.class);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);

        tomcat = new Tomcat();

        /*
         * connector
         */
        Connector connector = new Connector(DEFAULT_PROTOCOL);
        connector.setPort(codServerModel.getPort());
        connector.setURIEncoding("UTF-8");
        tomcat.setConnector(connector);

        /*
         * 2. add dispatcherServlet
         */
        tomcat.addServlet(codServerModel.getContextPath(), "dispatcher", dispatcherServlet);
        standardContext.addServletMappingDecoded("/*", "dispatcher");


        // tomcat.getHost().addChild(standardContext);
        // context.getServletContext();
        Class servletClass = DispatcherServlet.class;
        String servletName = servletClass.getSimpleName();

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

        // set listener
        LinkedList<EventListener> listeners = codServerModel.getListeners();
        for (EventListener listener : listeners){
            // 状态必须是 STARTING_PREP
            if (!standardContext.getState().equals(LifecycleState.STARTING_PREP)) {
                System.out.println("STARTING_PREP");
                standardContext.addApplicationEventListener(listener);
            }
        }

        // context.addApplicationListener(ContextLoaderListener.class.getName());

        // host.setContextClass(Tomcat.DefaultWebXmlListener.class.getName());

        // set filter
        LinkedList<CodServerFilterModel> filters = codServerModel.getFilters();
        for (CodServerFilterModel codServerFilterModel : filters){
            FilterDef filterDef = new FilterDef();
            filterDef.setFilterClass(codServerFilterModel.getName());
            filterDef.setFilter(codServerFilterModel.getFilter());
            Map<String, String> maps = codServerFilterModel.getParamList();
            // add params
            while(maps !=null && !maps.isEmpty()){
                for (String key : maps.keySet()){
                    filterDef.addInitParameter(key, maps.get(key));
                }
            }

            FilterMap filterMap = new FilterMap();
            filterMap.setFilterName(codServerFilterModel.getName());
            filterMap.addURLPattern(codServerFilterModel.getMapping());
            filterMap.setDispatcher(codServerFilterModel.getDispatcher().name());

            standardContext.addFilterDef(filterDef);

            standardContext.addFilterMap(filterMap);
        }

        // context.addServletContainerInitializer(new SpringServletContainerInitializer(),  new HashSet<>());

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
        this.start(codModuleLauncherModel);
    }
}
