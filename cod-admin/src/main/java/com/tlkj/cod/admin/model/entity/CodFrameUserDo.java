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

import java.io.Serializable;

/**
 * Desc 用户表DO
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameUserDo
 * @date 2018/10/29 上午10:34
 */
public class CodFrameUserDo implements Serializable {

    public static final String TABLE_NAME = "cod_sys_user";

    /**
     * 主键
     */
    private String id;

    /**
     * 登录账号
     */
    private String login_account;

    /**
     * 登录密码
     */
    private String login_pass;

    /**
     * 用户名
     */
    private String user_name;

    /**
     * 用户头像
     */
    private String user_head;

    /**
     * 用户电话
     */
    private String user_phone;

    /**
     * 用户邮箱
     */
    private String user_email;

    /**
     * 用户性别
     */
    private String user_sex;

    /**
     * 用户生日
     */
    private String user_birthday;

    /**
     * 注册时间
     */
    private String register_time;

    /**
     * 员工id
     */
    private String employee_id;

    /**
     * 公司id
     */
    private String company_id;

    /**
     * 部门id
     */
    private String dept_id;

    /**
     * 到期时间
     */
    private String expiration;

    /**
     * 简介
     */
    private String intro;

    /**
     * 状态
     */
    private String state;

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

    public String getLogin_account() {
        return login_account;
    }

    public void setLogin_account(String login_account) {
        this.login_account = login_account;
    }

    public String getLogin_pass() {
        return login_pass;
    }

    public void setLogin_pass(String login_pass) {
        this.login_pass = login_pass;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_head() {
        return user_head;
    }

    public void setUser_head(String user_head) {
        this.user_head = user_head;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_birthday() {
        return user_birthday;
    }

    public void setUser_birthday(String user_birthday) {
        this.user_birthday = user_birthday;
    }

    public String getRegister_time() {
        return register_time;
    }

    public void setRegister_time(String register_time) {
        this.register_time = register_time;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
