/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc 用户表Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminUserDto
 * @date 2018/10/29 上午11:57
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodAdminUserDto {

    /**
     * 主键
     */
    private String id;

    /**
     * 登录账号
     */
    private String loginAccount;

    /**
     * 登录密码
     */
    private String loginPass;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userHead;

    /**
     * 用户电话
     */
    private String userPhone;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户性别
     */
    private String userSex;

    /**
     * 用户生日
     */
    private String userBirthday;

    /**
     * 注册时间
     */
    private String registerTime;

    /**
     * 员工id
     */
    private String employeeId;

    /**
     * 公司id
     */
    private String companyId;

    /**
     * 部门id
     */
    private String deptId;

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
    private String updateTime;

}
