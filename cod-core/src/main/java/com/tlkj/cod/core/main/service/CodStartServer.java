/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.main.service;

import com.tlkj.cod.launcher.CodServerInitialize;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.util.EventListener;
import java.util.LinkedList;

/**
 * Desc 抽象web服务
 *
 * @author sourcod
 * @version 1.0
 * @className AbstractCodWebServer
 * @date 2019/3/28 5:20 PM
 */
public interface CodStartServer {

    /**
     * 初始化
     */
    void init(CodServerInitialize codStartServerInit);

    /**
     * 启动
     */
    void start();

    /**
     * 停止
     */
    void stop();

    /**
     * 重启
     */
    void restart();

    /**
     * set filter
     * @param filters 过滤器
     */
    void setFilter(LinkedList<Filter> filters);

    /**
     * add filter
     * @param filters 过滤器
     */
    void addFilter(LinkedList<Filter> filters);

    /**
     * add filter
     * @param filter 过滤器
     */
    void addFilter(Filter filter);

    /**
     * set listener
     * @param eventListeners 监听器
     */
    void setListener(LinkedList<EventListener> eventListeners);

    /**
     * add listener
     * @param eventListeners 监听器
     */
    void addListener(LinkedList<EventListener> eventListeners);

    /**
     * add listener
     * @param eventListener 监听器
     */
    void addListener(EventListener eventListener);

    /**
     * add servlet
     * @param servlets servlet
     */
    void setServlet(LinkedList<Servlet> servlets);


}
