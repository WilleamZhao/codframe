/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.action.login;

import com.tlkj.cod.core.annotation.ParamNotNull;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.service.login.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Desc 登录Action
 *
 * @author sourcod
 * @version 1.0
 * @className LoginAction
 * @date 2018/6/30 下午6:40
 */
@RestController
@RequestMapping("system/login")
public class LoginAction extends GeneralResponse {

    @Autowired
    LoginService loginService;

    /**
     * 获取token
     */
    @RequestMapping(value = "getToken", method = {RequestMethod.GET})
    public Response getToken(HttpServletRequest request){
        String token = request.getParameter("token");
        return super.success();
    }

    /**
     * 登录接口
     *
     * @return 登录结果
     */
    @ParamNotNull(parameter = "username, password")
    @RequestMapping("/login")
    public Response login(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String isRemember = request.getParameter("isRemember");
        String code = request.getParameter("code");

        String token = loginService.login(username, password, code, isRemember, request);
        return StringUtils.isNotBlank(token) ? super.success(token) : super.fail();
    }

    /**
     * 创建验证码 并放到session
     * @return 验证码路径
     */
    @RequestMapping("createCode")
    public String createCode(HttpServletRequest request, HttpServletResponse response){
        String token = request.getParameter("token");
        //生成随机字串
        Map verifyCode = loginService.createCode(token);
        // 存入会话session
        //HttpSession session = request.getSession(true);
        // 删除以前的
        //session.removeAttribute("verCode");
        //session.setAttribute("verCode", verifyCode.get("code"));
        return verifyCode.get("codeImgUrl").toString();
    }
}
