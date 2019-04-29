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

import com.tlkj.cod.core.main.service.CodStartServer;
import com.tlkj.cod.launcher.CodServerInitialize;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.util.EventListener;
import java.util.LinkedList;

/**
 * Desc cod启动resinService
 *
 * @author sourcod
 * @version 1.0
 * @className CodStartResinServerImpl
 * @date 2019/4/10 3:41 PM
 */
@Component
public class CodStartResinServerImpl implements CodStartServer {

    @Override
    public void init(CodServerInitialize codStartServerInit) {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void restart() {

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
}
