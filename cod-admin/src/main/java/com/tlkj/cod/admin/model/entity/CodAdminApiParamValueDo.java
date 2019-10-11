/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.model.entity;

import com.tlkj.cod.dao.model.CodDaoDo;
import lombok.Getter;
import lombok.Setter;


/**
 * 参数值表
 * cod_sys_api_param_value
 *
 *
 * codFrame create
 * @author sourcod
 */
@Getter
@Setter
public class CodAdminApiParamValueDo extends CodDaoDo {

    /**
     * 表名
     */
    public static final String TABLE_NAME = "cod_sys_api_param_value";

    private static final long serialVersionUID = 4356083194278380034L;

    /**
     * 主键
     */
    private String id;

    /**
     * 值
     */
    private String value;

    /**
     * 值描述
     */
    private String value_desc;

    /**
     * 参数id
     */
    private String param_id;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 更新时间
     */
    private String update_time;

}