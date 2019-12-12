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
 * Desc 角色权限中间表
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminRolePermissionDo
 * @date 2018/12/9 6:01 PM
 */
@Getter
@Setter
public class CodAdminRolePermissionDo extends CodDaoDo {

    public static String TABLE_NAME = "cod_sys_role_permission";

    private static final long serialVersionUID = 359422011166615292L;

    /**
     * 主键
     */
    private String id;

    /**
     * 角色id
     */
    private String role_id;

    /**
     * 权限id
     */
    private String permission_id;

}
