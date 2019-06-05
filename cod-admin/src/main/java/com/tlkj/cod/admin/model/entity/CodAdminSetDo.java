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
 * Desc 系统设置Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminSetDo
 * @date 2018/11/27 7:52 PM
 */
public class CodAdminSetDo {

    public static String TABLE_NAME = "cod_sys_set";

    /**
     * 主键
     */
    private String id;

    /**
     * 设置名称
     */
    private String set_name;

    /**
     * 设置代码
     */
    private String set_code;

    /**
     * 设置值
     */
    private String set_value;

    /**
     * 操作用户id
     */
    private String user_id;

    /**
     * 排序
     */
    private String sort;

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

    public String getSet_name() {
        return set_name;
    }

    public void setSet_name(String set_name) {
        this.set_name = set_name;
    }

    public String getSet_code() {
        return set_code;
    }

    public void setSet_code(String set_code) {
        this.set_code = set_code;
    }

    public String getSet_value() {
        return set_value;
    }

    public void setSet_value(String set_value) {
        this.set_value = set_value;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
