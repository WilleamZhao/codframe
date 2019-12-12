/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.model.entity;

import com.tlkj.cod.dao.model.CodDaoDo;
import lombok.Getter;
import lombok.Setter;


/**
 * Desc 资源表Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminResourceDo
 * @date 2018/10/30 下午3:03
 */
@Getter
@Setter
public class CodAdminResourceDo extends CodDaoDo {

    private static final long serialVersionUID = 9176834014039767102L;

    public static String TABLE_NAME = "cod_sys_resource";

    /**
     * 主键
     */
    private String id;

    /**
     * 资源名称
     */
    private String resource_name;

    /**
     * 资源描述
     */
    private String resource_desc;

    /**
     * 资源url
     */
    private String resource_url;

    /**
     * 是否是相对路径 0: 不是; 1: 是;
     */
    private String resource_r_status;

    /**
     * 全局状态 0: 禁用; 1: 启用;
     */
    private String state;

    /**
     * 权限id
     */
    private String permission_id;

    /**
     * 权限code
     */
    private String permission_code;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 更新时间
     */
    private String update_time;

}
