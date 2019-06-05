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
import com.tlkj.cod.server.model.CodServerServletModel;
import com.tlkj.cod.server.model.config.CodServerSpringMVCConfiguration;
import com.tlkj.cod.server.model.server.CodServerJettyModel;
import com.tlkj.cod.server.model.server.CodServerModel;
import com.tlkj.cod.server.service.CodServerService;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.lang.management.ManagementFactory;
import java.util.EnumSet;
import java.util.EventListener;
import java.util.LinkedList;

/**
 * Desc cod启动JettyServer
 *
 * @author sourcod
 * @version 1.0
 * @className CodServerServiceJettyImpl
 * @date 2019/4/10 2:33 PM
 */
@Primary
@Component(value = "codServerJetty")
public class CodServerServiceJettyImpl implements CodServerService {

    private static Server server = null;
    private static CodServerModel startModel = CodServerModel.getInstance();

    private static final int DEFAULT_PORT = 8080;
    private static final String CONTEXT_PATH = "/";
    private static final String MAPPING_URL = "/*";

    private CodModuleLauncherModel codModuleLauncherModel;

    @Override
    public void start(CodModuleLauncherModel codModuleLauncherModel) {
        this.codModuleLauncherModel = codModuleLauncherModel;
        // 设置服务信息
        setServerInfo();
        CodServerModel codServerModel = (CodServerModel) this.codModuleLauncherModel.getServer();
        codServerModel = codServerModel == null ? new CodServerJettyModel() : codServerModel;
        AnnotationConfigWebApplicationContext applicationContext = (AnnotationConfigWebApplicationContext) codModuleLauncherModel.getSpring();

        // 注册springMVC
        applicationContext.register(CodServerSpringMVCConfiguration.class);

        applicationContext.setServletContext(new ContextHandler.StaticContext());
        // applicationContext.refresh();
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
        // dispatcherServlet.setContextInitializers();
        dispatcherServlet.refresh();

        // initServlet();

        // set Dispatcher
        ServletHolder servletHolder = new ServletHolder(dispatcherServlet);
        ServletContextHandler context = new ServletContextHandler();
        context.addServlet(servletHolder, "/*");

        // TODO 初始化
        // context.setInitParameter("contextInitializerClasses", CodInitializer.class.getName());

        LinkedList<CodServerFilterModel> filters = codServerModel.getFilters();
        // 动态添加filter
        if (filters != null && filters.size() != 0){
            for (CodServerFilterModel codServerFilterModel : filters){
                // ServletHandler servletHandler = new ServletHandler();
                FilterHolder filterHolder = new FilterHolder(codServerFilterModel.getFilter());
                if (codServerFilterModel.getParamList() != null){
                    filterHolder.setInitParameters(codServerFilterModel.getParamList());
                }
                // FilterMapping mapping = new FilterMapping();
                // servletHandler.addFilterWithMapping(filterHolder, codServerFilterModel.getMapping(), EnumSet.of(codServerFilterModel.getDispatcher()));
                context.addFilter(filterHolder, codServerFilterModel.getMapping(), EnumSet.of(codServerFilterModel.getDispatcher()));
            }
        }

        // 动态添加servlet
        LinkedList<CodServerServletModel> servlets = codServerModel.getServlets();
        if (servlets != null && servlets.size() != 0){
            for (CodServerServletModel codServerServletModel : servlets){
                // ServletHandler servletHandler = new ServletHandler();
                ServletHolder dynamicServlet = new ServletHolder(codServerServletModel.getServlet());
                // servletHandler.addServletWithMapping(dynamicServlet, codServerServletModel.getMapping());
                context.addServlet(dynamicServlet, codServerServletModel.getMapping());
            }
        }

        // 动态添加listener
        LinkedList<EventListener> listeners = codServerModel.getListeners();
        if (listeners != null && listeners.size() != 0){
            for (EventListener listener : listeners){
                context.addEventListener(listener);
            }
        }

        // context.addEventListener(new ContextLoaderListener(annotationConfigWebApplicationContext));
        // context.addEventListener(new RequestContextListener());

        server = new Server();

        server.setHandler(context);
        server.setStopAtShutdown(true);


        ServerConnector connector = new ServerConnector(server);
        connector.setPort(codServerModel.getPort());

        String host = codServerModel.getIp();
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
