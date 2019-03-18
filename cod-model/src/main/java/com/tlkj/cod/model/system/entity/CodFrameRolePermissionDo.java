/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.model.system.entity;

/**
 * Desc 角色权限中间表
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameRolePermissionDo
 * @date 2018/12/9 6:01 PM
 */
public class CodFrameRolePermissionDo {

    public static String TABLE_NAME = "cod_sys_role_permission";

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

    public String getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(String permission_id) {
        this.permission_id = permission_id;
    }
}
