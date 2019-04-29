/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.server.model;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.LinkedList;
import java.util.Map;

/**
 * Desc filter model
 *
 * @author sourcod
 * @version 1.0
 * @className FilterModel
 * @date 2019/4/28 6:00 PM
 */
public class FilterModel {

    /**
     * 名称
     */
    private String name;

    /**
     * mapping
     */
    private String mapping;

    /**
     * filter
     */
    private Filter filter;

    /**
     * param
     */
    private Map<String, String> paramList;

    private DispatcherType dispatcher = DispatcherType.REQUEST;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public Map<String, String> getParamList() {
        return paramList;
    }

    public void setParamList(Map<String, String> paramList) {
        this.paramList = paramList;
    }

    public DispatcherType getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(DispatcherType dispatcher) {
        this.dispatcher = dispatcher;
    }
}
