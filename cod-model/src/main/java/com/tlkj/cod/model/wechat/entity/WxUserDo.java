/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.model.wechat.entity;

import com.tlkj.cod.config.annotation.CodValue;
import com.tlkj.cod.config.model.enums.CodConfigSourceType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className WxUserDo
 * @date 2018/11/6 下午10:53
 */
@Component
public class WxUserDo {

    @Value("${asasdd:mmm}")
    @CodValue(type = CodConfigSourceType.DATABASE)
    private String id;

    @Value("${spring.liveBeansView.mbeanDomain:123444}")
    private String openid;

    private String unionid;
    private String nickname;
    private String head_url;
    private String phone;
    private String username;
    private String password;
    private String login_time;
    private String email;
    private String create_time;
    private String salt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHead_url() {
        return head_url;
    }

    public void setHead_url(String head_url) {
        this.head_url = head_url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin_time() {
        return login_time;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public static void main(String[] args) {
        // Java 系统属性
        String key = "java.home";


        StandardEnvironment environment = new StandardEnvironment();

        // 获取 ${environment} 的值
        String property = environment.getProperty(key);
        System.out.println(String.format("Environment#getProperty(%s) = %s", key, property));

        // 解析占位符 ${environment}
        String placeholders = environment.resolvePlaceholders("${java.home}");
        System.out.println(String.format("Environment#resolvePlaceholders(${java.home}) = %s", placeholders));
    }
}
