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
 * 参数表
 * cod_sys_api_param
 *
 *
 * codFrame create
 * @author sourcod
 */
@Getter
@Setter
public class CodAdminApiParamDo extends CodDaoDo {

    /**
     * 表名
     */
    public static final String TABLE_NAME = "cod_sys_api_param";

    private static final long serialVersionUID = 5869697681300037745L;

    /**
     * 主键
     */
    private String id;

    /**
     * 参数名称
     */
    private String param_name;

    /**
     * 参数key
     */
    private String param_key;

    /**
     * 参数值
     */
    private String param_value;

    /**
     * 参数类型;
     * 1: 请求参数
     * 2: 返回参数
     */
    private String param_type;

    /**
     * 接口id
     */
    private String api_id;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 更新时间
     */
    private String update_time;

}