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

import com.tlkj.cod.common.CodCommonModelConvert;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc 用户表DO
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminUserDo
 * @date 2018/10/29 上午10:34
 */
@Getter
@Setter
public class CodAdminUserDo extends CodCommonModelConvert implements Serializable {

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

}
