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
 * Desc 菜单表Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameMenuDo
 * @date 2018/10/29 下午2:05
 */
public class CodFrameMenuDo {

    public static String TABLE_NAME = "cod_sys_menu";

    /**
     * 主键
     */
    private String id;

    /**
     * 菜单名称
     */
    private String menu_name;

    /**
     * 菜单显示名称
     */
    private String menu_title;

    /**
     * 跳转路径
     */
    private String jump;

    /**
     * 父id
     */
    private String p_id;

    /**
     * 菜单级别
     */
    private String level;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单图标
     */
    private String status;

    /**
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

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_title() {
        return menu_title;
    }

    public void setMenu_title(String menu_title) {
        this.menu_title = menu_title;
    }

    public String getJump() {
        return jump;
    }

    public void setJump(String jump) {
        this.jump = jump;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
