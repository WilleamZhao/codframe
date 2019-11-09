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
 * 接口表
 * cod_sys_api
 * 
 * 
 * codFrame create
 * @author sourcod
 */
@Getter
@Setter
public class CodAdminApiDo extends CodDaoDo {

    /**
     * 表名
     */
    public static final String TABLE_NAME = "cod_sys_api";

    private static final long serialVersionUID = 8773955599103037417L;

    /**
     * 主键 
     */
    private String id;
    
    /**
     * 接口名称 
     */
    private String api_name;
    
    /**
     * 接口类型;对外接口, 对内接口 
     */
    private String api_type;
    
    /**
     * 接口分类
     */
    private String api_classify_id;
    
    /**
     * 接口价格;消耗积分 
     */
    private Integer api_price;
    
    /**
     * 请求方式 
     */
    private String api_method;
    
    /**
     * 用户级别 
     */
    private String user_level;
    
    /**
     * 返回格式;1: json
     * 2: xml
     * 3: txt
     */
    private String result_format;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 资源id 
     */
    private String resource_id;
    
    /**
     * 创建时间 
     */
    private String create_time;
    
    /**
     * 更新时间 
     */
    private String update_time;

}