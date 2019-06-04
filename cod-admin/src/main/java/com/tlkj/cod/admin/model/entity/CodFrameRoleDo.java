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
 * Desc 角色表DO
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameRoleDo
 * @date 2018/10/29 上午10:47
 */
public class CodFrameRoleDo {

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_desc() {
        return role_desc;
    }

    public void setRole_desc(String role_desc) {
        this.role_desc = role_desc;
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

    public String getRole_remark() {
        return role_remark;
    }

    public void setRole_remark(String role_remark) {
        this.role_remark = role_remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
