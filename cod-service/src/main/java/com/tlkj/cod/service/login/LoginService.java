/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.login;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Desc 登录service
 *
 * @author sourcod
 * @version 1.0
 * @className LoginService
 * @date 2018/7/1 上午11:19
 */

public interface LoginService {

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param isRemeber 是否记住我
     * @return 登录是否成功
     */
    String login(String username, String password, String code, String isRemeber, HttpServletRequest request);

    /**
     * 创建验证码
     * @return 验证码, 验证码路径
     */
    Map createCode(String token);
}
