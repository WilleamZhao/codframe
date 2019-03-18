/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.login.impl;

import com.tlkj.cod.core.security.token.StatelessToken;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.model.system.entity.CodFrameUserDo;
import com.tlkj.cod.service.login.LoginService;
import com.tlkj.cod.service.system.MemcachedService;
import com.tlkj.cod.common.CodCommonNetWork;
import com.tlkj.cod.common.CodCommonUUID;
import com.tlkj.cod.common.CodCommonVerifyCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Desc 登录实现
 *
 * @author sourcod
 * @version 1.0
 * @className LoginServiceImpl
 * @date 2018/7/1 上午11:23
 */
@Service
public class LoginServiceImpl implements LoginService {


    private static Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    Finder finder;

    @Autowired
    MemcachedService memcachedService;


    /**
     * 1. 验证验证码是否正确
     * 2. 验证用户名密码是否正确
     * 3. 生成token
     * 4. 返回结果
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @return 登录结果
     */
    @Override
    public String login(String username, String password, String code, String isRemeber, HttpServletRequest request){

        String host;
        try {
            host = CodCommonNetWork.getIpAddress(request);
        } catch (IOException e) {
            logger.error("获取ip失败", e.getMessage());
            return "";
        }

        /*
         * 1. 验证验证码是否正确
         */
        /*if (StringUtils.isNotEmpty(code) && StringUtils.isNotEmpty(code) && code.equals(code)){
            memcachedService.get(code);
            logger.debug("用户{}: 验证码验证成功={}", username, code);
        }*/

        String error = "未知错误异常";
        Subject subject = SecurityUtils.getSubject();
        try {
            StatelessToken statelessToken = new StatelessToken(username, request.getParameterMap(), password);
            subject.login(statelessToken);
        } catch (UnknownAccountException e){
            //抛出账号不存在异常
            error="账号不存在";
            return "";
        } catch (IncorrectCredentialsException e){
            error = "密码错误";
            return "";
        }
        subject.hasRole("admin");
        Set set = subject.getPrincipals().getRealmNames();
        Iterator iterator = set.iterator();
        String s = "";
        while (iterator.hasNext()){
            s = iterator.next().toString();
        }
        return s;
    }

    /**
     * 验证用户名密码是否正确
     * @param username 用户名
     * @param password 密码
     * @return 是否正确
     */
    private boolean verifyUserPass(String username, String password){
        CodFrameUserDo codFrameUserDo = finder.from(CodFrameUserDo.TABLE_NAME)
                .where("login_account", username)
                .where("login_pass", password)
                .where("state", "1").first(CodFrameUserDo.class);
        if (codFrameUserDo != null){
            return true;
        }

        return false;
    }

    /**
     * 创建验证码并返回路径
     * @return 验证码, 验证码路径
     */
    @Override
    public Map createCode(String token) {
        /*
         * 1. 生成验证码
         */
        String verifyCode = CodCommonVerifyCode.generateVerifyCode();

        if (StringUtils.isBlank(token)){
            token = getToken();
        }

        /*
         * 设置缓存
         */
        //memcachedService.set(UUIDUtil.getUUID(), verifyCode);

        /*
         * 2 生成图片
         */
        InputStream is = null;
        try {
            is = CodCommonVerifyCode.createCode(verifyCode);
        } catch (IOException e) {
            logger.error("生成图片错误");
        }

        /*
         * 3. 上传文件
         * @return 图片相对路径
         */
        // String url = OSSUtil.uploadOSS("code/" + String.valueOf(CodCommonDate.now().getTime()) + ".jpg", is);
        String url = "";
        Map<String, String> map = new HashMap<>(2);
        map.put("code", verifyCode);
        map.put("codeImgUrl", url);
        return map;
    }

    /**
     * 获取token
     * @return
     */
    private String getToken(){
        return CodCommonUUID.getUUID();
    }


}
