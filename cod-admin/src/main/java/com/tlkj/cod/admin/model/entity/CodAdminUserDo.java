/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.model.entity;

import com.tlkj.cod.dao.model.CodDaoDo;
import lombok.Getter;
import lombok.Setter;


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
public class CodAdminUserDo extends CodDaoDo {

    public static final String TABLE_NAME = "cod_sys_user";

    private static final long serialVersionUID = -4662175725786435895L;

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
     * 加密类型
     * 1: MD5
     * 2: RSA
     * 3: SHA256
     */
    private int encry_type;

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

    /**
     * 私钥id
     */
    private String private_key_id;

    /**
     * 公钥id
     */
    private String public_key_id;

    /**
     * 是否记住我
     */
    private String is_remeber;

    /**
     * token
     */
    private String token;

}
