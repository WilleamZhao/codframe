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
 * Desc 部门表Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameDeptDo
 * @date 2018/10/30 下午2:43
 */
public class CodFrameDeptDo {

    public static final String TABLE_NAME = "cod_sys_dept";

    /**
     * 主键
     */
    private String id;

    /**
     * 部门名称
     */
    private String dept_name;

    /**
     * 部门编号
     */
    private String dept_no;

    /**
     * 部门级别
     */
    private String dept_level;

    /**
     * 部门管理员
     */
    private String dept_admin;

    /**
     * 上级部门
     */
    private String p_id;

    /**
     * 公司id
     */
    private String company_id;

    /**
     * 状态
     */
    private String state;

    /**
     * 排序
     */
    private String sort;

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

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getDept_no() {
        return dept_no;
    }

    public void setDept_no(String dept_no) {
        this.dept_no = dept_no;
    }

    public String getDept_level() {
        return dept_level;
    }

    public void setDept_level(String dept_level) {
        this.dept_level = dept_level;
    }

    public String getDept_admin() {
        return dept_admin;
    }

    public void setDept_admin(String dept_admin) {
        this.dept_admin = dept_admin;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
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

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
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
