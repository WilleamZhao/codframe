/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.model.entity;

import com.tlkj.cod.common.CodCommonModelConvert;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc 角色菜单表
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminRoleMenuDo
 * @date 2018/12/26 1:13 PM
 */
@Getter
@Setter
public class CodAdminRoleMenuDo extends CodCommonModelConvert implements Serializable {

    public static String TABLE_NAME = "cod_sys_role_menu";

    /**
     * 主键
     */
    private String id;

    /**
     * 角色id
     */
    private String role_id;

    /**
     * 菜单id
     */
    private String menu_id;

}
