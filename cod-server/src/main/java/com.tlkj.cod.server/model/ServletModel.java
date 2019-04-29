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

import javax.servlet.Servlet;
import java.util.List;
import java.util.Map;

/**
 * Desc servlet model
 *
 * @author sourcod
 * @version 1.0
 * @className ServletModel
 * @date 2019/4/28 6:18 PM
 */
public class ServletModel {

    /**
     * 名称
     */
    private String name;

    /**
     * mapping
     */
    private String mapping;

    /**
     * load-on-startup
     */
    private String load;

    private Servlet servlet;

    /**
     * param
     */
    private List<Map<String, String>> paramList;

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

    public String getLoad() {
        return load;
    }

    public void setLoad(String load) {
        this.load = load;
    }

    public List<Map<String, String>> getParamList() {
        return paramList;
    }

    public void setParamList(List<Map<String, String>> paramList) {
        this.paramList = paramList;
    }

    public Servlet getServlet() {
        return servlet;
    }

    public void setServlet(Servlet servlet) {
        this.servlet = servlet;
    }
}
