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
 * Desc 资源表Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminResourceDo
 * @date 2018/10/30 下午3:03
 */
public class CodAdminResourceDo {

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResource_name() {
        return resource_name;
    }

    public void setResource_name(String resource_name) {
        this.resource_name = resource_name;
    }

    public String getResource_desc() {
        return resource_desc;
    }

    public void setResource_desc(String resource_desc) {
        this.resource_desc = resource_desc;
    }

    public String getResource_url() {
        return resource_url;
    }

    public void setResource_url(String resource_url) {
        this.resource_url = resource_url;
    }

    public String getResource_r_status() {
        return resource_r_status;
    }

    public void setResource_r_status(String resource_r_status) {
        this.resource_r_status = resource_r_status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(String permission_id) {
        this.permission_id = permission_id;
    }

    public String getPermission_code() {
        return permission_code;
    }

    public void setPermission_code(String permission_code) {
        this.permission_code = permission_code;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
