/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 */
package com.tlkj.cod.gwgz.model.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;


/**
 * codFrame auto create
 */
@Getter
@Setter
public class GwgzUserDo implements Serializable,Cloneable{
    /**
     * 表名
     */
    public static final String TABLE_NAME = "gwgz_user";

    /**
     * 用户id
     */
    private String id;

    /**
     * openid
     */
    private String openid;

    /**
     * unionid
     */
    private String unionid;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像url
     */
    private String head_url;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 登录时间
     */
    private String login_time;

    /**
     * 邮件
     */
    private String email;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 加密盐
     */
    private String salt;

    /**
     * 用户状态;用户状态
     0:禁用
     1:正常
     2:锁定
     */
    private String state;

}