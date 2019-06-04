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

/**
 * Desc 角色菜单表
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameRoleMenuDo
 * @date 2018/12/26 1:13 PM
 */
public class CodFrameRoleMenuDo {

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }
}
