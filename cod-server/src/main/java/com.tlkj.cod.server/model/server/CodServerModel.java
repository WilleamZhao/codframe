/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.server.model.server;

import com.tlkj.cod.launcher.model.ServerModel;
import com.tlkj.cod.server.model.FilterModel;
import com.tlkj.cod.server.model.ServletModel;

import java.io.Serializable;
import java.util.EventListener;
import java.util.LinkedList;


/**
 * Desc cod启动model
 *
 * @author sourcod
 * @version 1.0
 * @className CodServerModel
 * @date 2019/4/9 7:21 PM
 */
public class CodServerModel extends ServerModel implements Serializable {

    private static volatile CodServerModel instance;

    /**
     * 线程安全
     */
    public static CodServerModel getInstance() {
        if (instance == null) {
            synchronized (CodServerModel.class) {
                if (instance == null) {
                    instance = new CodServerModel();
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
    private String host = "localhost";

    /**
     * 环境
     * 0: 开发环境
     * 1: 测试环境
     * 2: 正式环境
     */
    private String env = "0";

    private String encode = "";

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

    private LinkedList<EventListener> listeners = new LinkedList<>();

    private LinkedList<FilterModel> filters = new LinkedList<>();

    private LinkedList<ServletModel> servlets = new LinkedList<>();

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


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public LinkedList<EventListener> getListeners() {
        return listeners;
    }

    public void addListeners(LinkedList<EventListener> listeners) {
        this.listeners.addAll(listeners);
    }

    public void addListener(EventListener listener) {
        this.listeners.add(listener);
    }

    public LinkedList<FilterModel> getFilters() {
        return filters;
    }

    public void addFilters(LinkedList<FilterModel> filters) {
        this.filters.addAll(filters);
    }

    public void addFilter(FilterModel filter) {
        this.filters.add(filter);
    }

    public LinkedList<ServletModel> getServlets() {
        return servlets;
    }

    public void addServlets(LinkedList<ServletModel> servlets) {
        this.servlets.addAll(servlets);
    }

    public void addServlet(ServletModel servlet) {
        this.servlets.add(servlet);
    }
}
