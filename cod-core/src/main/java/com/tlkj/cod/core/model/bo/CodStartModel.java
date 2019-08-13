/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.core.model.bo;

import java.io.Serializable;
import java.util.EventListener;
import java.util.LinkedList;
import java.util.logging.Filter;

/**
 * Desc cod启动model
 *
 * @author sourcod
 * @version 1.0
 * @className CodStartModel
 * @date 2019/4/9 7:21 PM
 */
public class CodStartModel implements Serializable {

    private static volatile CodStartModel instance;

    /**
     * 线程安全
     */
    public static CodStartModel getInstance() {
        if (instance == null) {
            synchronized (CodStartModel.class) {
                if (instance == null) {
                    instance = new CodStartModel();
                }
            }
        }
        return instance;
    }

    /**
     * 端口号
     */
    private int port = 9999;

    /**
     * ip地址
     */
    private String ip;

    /**
     * host
     */
    private String host;

    /**
     * 环境
     * 0: 开发环境
     * 1: 测试环境
     * 2: 正式环境
     */
    private String env = "0";

    /**
     * 服务
     * 1: jetty
     * 2: tomcat
     * 3: resin
     */
    private String server = "jetty";

    /**
     * 项目名称
     */
    private String projectName = "codframe";

    private LinkedList<EventListener> listeners;

    private LinkedList<Filter> filters;

    /**
     * tomcat存储自身信息的目录，比如日志等信息，根目录
     * 根目录
     */
    private String baseDir = ".";

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }


}
