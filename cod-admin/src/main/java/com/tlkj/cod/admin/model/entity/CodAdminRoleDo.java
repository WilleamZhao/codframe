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
 * Desc 角色表DO
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminRoleDo
 * @date 2018/10/29 上午10:47
 */
@Getter
@Setter
public class CodAdminRoleDo extends CodDaoDo {

    private static final long serialVersionUID = 8405380691744315279L;

    public static String TABLE_NAME = "cod_sys_role";

    /**
     * 主键uuid
     */
    private String id;

    /**
     * 角色名称
     */
    private String role_name;

    /**
     * 角色描述
     */
    private String role_desc;

    /**
     * 角色备注
     */
    private String role_remark;

    /**
     * 状态
     * -1: 删除
     * 0: 禁用
     * 1: 启用
     */
    private String state;

    /**
     * sort
     * 排序
     */
    private String sort;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 修改时间
     */
    private String update_time;

    
}
