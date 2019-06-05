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
 * Desc 权限资源关联表Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminPermissionResource
 * @date 2018/10/30 下午3:12
 */
public class CodAdminPermissionResource {

    public static String TABLE_NAME = "cod_sys_permission_resource";

    /**
     * 主键
     */
    private String id;

    /**
     * 权限id
     */
    private String permission_id;

    /**
     * 资源id
     */
    private String resource_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(String permission_id) {
        this.permission_id = permission_id;
    }

    public String getResource_id() {
        return resource_id;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }
}
