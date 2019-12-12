/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.launcher.model;

import lombok.Getter;
import lombok.Setter;

import java.util.EventListener;
import java.util.LinkedList;

/**
 * Desc server model
 *
 * @author sourcod
 * @version 1.0
 * @className CodModuleServerModel
 * @date 2019/4/28 6:26 PM
 */
@Getter
@Setter
public abstract class CodModuleServerModel {

    private LinkedList<EventListener> listeners = new LinkedList<>();

    private LinkedList filters = new LinkedList();

    private LinkedList servlets = new LinkedList();

    public void addListeners(LinkedList listeners){
        this.listeners.addAll(listeners);
    }

    public void addListener(EventListener listener){
        this.listeners.add(listener);
    }

    public void addFilters(LinkedList filters){
        this.filters.addAll(filters);
    }

    public void addFilter(Object filter){
        this.filters.add(filter);
    }

    public void addServlets(LinkedList servlets){
        this.servlets.addAll(servlets);
    }

    public void addServlet(Object servlet){
        this.servlets.add(servlet);
    }

    public boolean removeListeners(EventListener listener){
        return this.listeners.remove(listener);
    }

    public boolean removeFilters(Object o){
        return this.filters.remove(o);
    }

    public boolean removeServlets(Object o){
        return this.servlets.remove(o);
    }

}
