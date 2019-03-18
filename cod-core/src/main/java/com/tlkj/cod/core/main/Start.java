/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.main;

import com.tlkj.cod.model.system.core.SystemModel;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;

/**
 * Desc 开始服务主方法
 *
 * @author sourcod
 * @version 1.0
 * @className Start
 * @date 2018/11/3 上午12:09
 */
public class Start {
    private static final int DEFAULT_PORT = 9999;

    private static Server server;
    private static Yaml yaml = new Yaml();
    private static SystemModel system = null;

    static {
        InputStream in;
        try {
            System.out.println("开始加载codFrame框架配置文件！");
            // 获取config配置文件夹路径
            in = new FileInputStream("application.yml");
            if (in.available() != 0) {
                system = yaml.loadAs(in, SystemModel.getInstance().getClass());
                System.out.println("加载codFrame框架配置文件application.yml成功！");
            }
        } catch (IOException e) {
            try {
                in = Start.class.getResource("/application.yml").openStream();
                if (in.available() != 0) {
                    system = yaml.loadAs(in, SystemModel.getInstance().getClass());
                    System.out.println("加载codFrame框架配置文件application.yml成功！");
                }
            } catch (IOException e1) {
                System.out.println("加载codFrame配置文件错误" + e.getMessage());
            }
        }
        SystemModel.getInstance().setInstance(system);
    }

    public Start(){

    }

    public void main(String[] args) throws Exception {
        Start.startJetty(getPortFromArgs(args));
    }

    /**
     * 获取端口
     * @param args
     * @return
     */
    private static int getPortFromArgs(String[] args) {
        if (args.length > 0) {
            try {
                return Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }

        if (system == null){
            return 0;
        }

        if (system.getCodframe().getPort() != 0){
            return system.getCodframe().getPort();
        }

        return DEFAULT_PORT;
    }

    private static void startJetty(int port) throws Exception {
        server = new Server(port);
        server.setHandler(getWebAppContext());
        server.setStopAtShutdown(true);

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        String host = system.getCodframe().getHost();
        if (StringUtils.isNotBlank(host)){
            connector.setHost(host);
        }
        connector.setReuseAddress(false);
        server.setConnectors(new Connector[] { connector });

        server.start();
        server.join();
    }

    /**
     * 停止jetty并结束进程
     */
    public static void stopJetty(){
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

    private static WebAppContext getWebAppContext(){
        WebAppContext context = new WebAppContext();

        context.setContextPath(system.getCodframe().getProject());
        context.setDescriptor(system.getCodframe().getWeb());
        context.setResourceBase(system.getCodframe().getWebapp());
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
