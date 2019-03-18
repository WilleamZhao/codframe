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
 * Desc 权限表Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodFramePermissionDo
 * @date 2018/10/30 下午2:40
 */
public class CodFramePermissionDo {

    public static String TABLE_NAME = "cod_sys_permission";

    /**
     * 主键
     */
    private String id;

    /**
     * 权限名称
     */
    private String permission_name;

    /**
     * 权限描述
     */
    private String permission_desc;

    /**
     * 权限代码
     */
    private String permission_code;

    /**
     * 权限
     */
    private int permission;

    /**
     * 状态
     */
    private int state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }

    public String getPermission_desc() {
        return permission_desc;
    }

    public void setPermission_desc(String permission_desc) {
        this.permission_desc = permission_desc;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public String getPermission_code() {
        return permission_code;
    }

    public void setPermission_code(String permission_code) {
        this.permission_code = permission_code;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
