/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.model.system.core;

/**
 * Desc 服务器设置Model
 *
 * @author sourcod
 * @version 1.0
 * @className SystemSetModel
 * @date 2018/11/6 下午5:32
 */
public class SystemSetModel {

    /**
     * 端口
     */
    private int port;

    /**
     * WEB-INF/web.xml目录
     */
    private String web;

    /**
     * webapp目录
     */
    private String webapp;

    /**
     * 项目名称
     */
    private String project;

    private String host;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getWebapp() {
        return webapp;
    }

    public void setWebapp(String webapp) {
        this.webapp = webapp;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
