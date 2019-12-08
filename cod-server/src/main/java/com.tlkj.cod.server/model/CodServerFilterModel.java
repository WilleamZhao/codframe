/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.LinkedList;
import java.util.Map;

/**
 * Desc filter model
 *
 * @author sourcod
 * @version 1.0
 * @className CodServerFilterModel
 * @date 2019/4/28 6:00 PM
 */
@Getter
@Setter
public class CodServerFilterModel {

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

    /**
     * dispatcher
     */
    private DispatcherType dispatcher = DispatcherType.REQUEST;

}
