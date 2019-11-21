/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.server.service.impl;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;
import com.tlkj.cod.server.model.CodServerFilterModel;
import com.tlkj.cod.server.model.CodServerServletModel;
import com.tlkj.cod.server.model.config.CodServerSpringMVCConfiguration;
import com.tlkj.cod.server.model.config.CodServerJettyConfig;
import com.tlkj.cod.server.model.config.CodServerConfig;
import com.tlkj.cod.server.service.CodServerService;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.StatisticsHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.lang.management.ManagementFactory;
import java.util.EnumSet;
import java.util.EventListener;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Desc cod 启动 JettyServer
 *
 * @author sourcod
 * @version 1.0
 * @className CodServerServiceJettyImpl
 * @date 2019/4/10 2:33 PM
 */
@Primary
@Component(value = "codServerJetty")
public class CodServerServiceJettyImpl implements CodServerService {

    private static Logger logger = LoggerFactory.getLogger(CodServerServiceJettyImpl.class);

    private static Server server = null;

    /**
     * 服务是否停止
     * 0: 启动
     * 1: 从起
     */
    private static int state = 0;

    private static CodModuleLauncherModel codModuleLauncherModel;
    private static CodServerConfig codServerConfig;

    public CodServerServiceJettyImpl(){

    }

    @Override
    public String support() {
        return "codServerJetty";
    }

    private Runnable runnable = () -> {
        codServerConfig = codServerConfig == null ? new CodServerJettyConfig() : codServerConfig;

        AnnotationConfigWebApplicationContext applicationContext = codModuleLauncherModel.getSpring();

        // 注册springMVC
        applicationContext.register(CodServerSpringMVCConfiguration.class);
        // applicationContext.refresh();

        if (state == 1){
            // applicationContext.refresh();
        }

        applicationContext.setServletContext(new ContextHandler.StaticContext());

        // 刷新 springMVC
        DispatcherServlet dispatcherServlet = null;
        if (state == 0){
            dispatcherServlet = new DispatcherServlet(applicationContext);
            dispatcherServlet.refresh();
        }

        // initServlet();

        // set Dispatcher
        ServletHolder servletHolder = new ServletHolder(dispatcherServlet);
        ServletContextHandler context = new ServletContextHandler();

        // 项目根目录
        context.setContextPath("/");

        // servlet
        context.addServlet(servletHolder, "/" + codServerConfig.getProjectName() + "/*");

        // 获取 filters
        LinkedList<CodServerFilterModel> filters = CodServerServiceJettyImpl.codModuleLauncherModel.getData(CodModuleOrderEnum.FILTER.getOrder(), LinkedList.class);

        // 动态添加 filter
        if (filters != null && filters.size() != 0){
            for (CodServerFilterModel codServerFilterModel : filters){
                // ServletHandler servletHandler = new ServletHandler();
                FilterHolder filterHolder = new FilterHolder(codServerFilterModel.getFilter());
                if (codServerFilterModel.getParamList() != null){
                    filterHolder.setInitParameters(codServerFilterModel.getParamList());
                }
                context.addFilter(filterHolder, codServerFilterModel.getMapping(), EnumSet.of(codServerFilterModel.getDispatcher()));
            }
        }

        // 动态添加 servlet
        LinkedList<CodServerServletModel> servlets = CodServerServiceJettyImpl.codModuleLauncherModel.getData(CodModuleOrderEnum.SERVLET.getOrder(), LinkedList.class);
        if (servlets != null && servlets.size() != 0){
            for (CodServerServletModel codServerServletModel : servlets){
                ServletHolder dynamicServlet = new ServletHolder(codServerServletModel.getServlet());
                context.addServlet(dynamicServlet, codServerServletModel.getMapping());
            }
        }

        // 动态添加listener
        LinkedList<EventListener> listeners = codServerConfig.getListeners();
        if (listeners != null && listeners.size() != 0){
            for (EventListener listener : listeners){
                context.addEventListener(listener);
            }
        }

        server = new Server();


        // 添加统计 StatisticsHandler 优雅的关闭 jetty
        StatisticsHandler statisticsHandler = new StatisticsHandler();
        statisticsHandler.setHandler(context);
        server.setHandler(statisticsHandler);

        server.setStopAtShutdown(true);

        ServerConnector connector = new ServerConnector(server);
        // 设置超时时间 默认 30 秒
        connector.setStopTimeout(30000);
        connector.setPort(codServerConfig.getPort());
        String host = codServerConfig.getHost();
        if (StringUtils.isNotBlank(host)) {
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
    };

    @Override
    public void start(CodModuleLauncherModel codModuleLauncherModel, CodServerConfig codServerConfig) {
        CodServerServiceJettyImpl.codModuleLauncherModel = codModuleLauncherModel;
        CodServerServiceJettyImpl.codServerConfig = codServerConfig;
        // 设置服务信息
        setServerInfo();
        // 新建线程启动服务
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("cod-server-start-pool").build();
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 10,TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), threadFactory);
        executorService.execute(runnable);
    }

    private void setServerInfo(){
        ContextHandler.setServerInfo("");
    }

    @Override
    public void stop() {
        System.out.println("正在停止服务");
        try {
            ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("cod-server-stop-pool").build();
            ExecutorService executorService = new ThreadPoolExecutor(1, 1, 10,TimeUnit.SECONDS, new ArrayBlockingQueue<>(5), threadFactory);
            executorService.execute(() -> {
                try {
                    server.stop();
                    Thread.sleep(10000);
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
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            System.err.println("关闭服务异常");
        }
    }



    @Override
    public void restart() {
        state = 1;
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("cod-server-restart-pool").build();
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 10,TimeUnit.SECONDS, new ArrayBlockingQueue<>(5), threadFactory);
        executorService.execute(() -> {
            try {
                this.stop();
                this.start(codModuleLauncherModel, codServerConfig);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
