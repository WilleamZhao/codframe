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

import com.tlkj.cod.core.main.service.CodStartServer;
import com.tlkj.cod.core.main.service.impl.CodStartJettyServerImpl;
import com.tlkj.cod.core.main.service.impl.CodStartResinServerImpl;
/*import com.tlkj.cod.core.main.service.impl.CodStartTomcatServerImpl;*/
import com.tlkj.cod.model.system.core.SystemModel;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

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

    private static Yaml yaml = new Yaml();
    private static SystemModel system = null;

    static {
        InputStream in;
        try {
            System.out.println("开始启动codFrame");
            System.out.println("开始初始化配置文件......");
            // 获取config配置文件夹路径
            in = new FileInputStream("application.yml");
            if (in.available() != 0) {
                system = yaml.loadAs(in, SystemModel.getInstance().getClass());
                System.out.println("初始化配置文件成功");
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

    /**
     * 启动cod框架
     * 1. 选择环境
     * 2. 选择服务
     * 3. 选择
     */
    public void main(String[] args) throws Exception {
        CodStartServer codStartServer = null;
        // TODO 启动tomcat
        // startTomcat(getPortFromArgs(args));
        System.out.println("请选择运行环境: [0]: 开发环境(默认) [1]: 测试环境 [2]: 正式环境");
        Scanner scan = new Scanner(System.in);
        String env = scan.nextLine();
        System.out.println(env);
        if (StringUtils.isNotBlank(env)){
            switch (env){
                case "0":

                    break;
                case "1":

                    break;
                case "2":

                    break;
                default:

                    break;
            }
        }

        System.out.println("请选择运行服务: [0]: jetty; [1]: tomcat [2]: resin");
        scan = new Scanner(System.in);
        String server = scan.nextLine();
        System.out.println(server);
        if (StringUtils.isNotBlank(env)){
            switch (server){
                case "0":
                    codStartServer = new CodStartJettyServerImpl();

                    codStartServer.start();
                    break;
                case "1":
                    /*codStartServer = new CodStartTomcatServerImpl();

                    codStartServer.start();*/
                    break;
                case "2":
                    codStartServer = new CodStartResinServerImpl();

                    codStartServer.start();
                    break;
                default:
                    break;
            }
        }
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
}
