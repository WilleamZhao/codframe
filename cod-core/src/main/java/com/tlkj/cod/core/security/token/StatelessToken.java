/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.security.token;

import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc 无状态token
 *
 * @author sourcod
 * @version 1.0
 * @className StatelessToken
 * @date 2018/10/26 下午12:13
 */
public class StatelessToken implements AuthenticationToken {

    /**
     * 用户名
     */
    private String username;

    /**
     * 参数
     */
    private Map<String, ?> params;

    /**
     * 令牌
     */
    private String clientDigest;

    public StatelessToken(String username, Map<String, ?> params, String clientDigest) {
        this.username = username;
        this.params = params;
        this.clientDigest = clientDigest;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public  Map<String, ?> getParams() {
        return params;
    }

    public void setParams( Map<String, Object> params) {
        this.params = params;
    }

    public String getClientDigest() {
        return clientDigest;
    }

    public void setClientDigest(String clientDigest) {
        this.clientDigest = clientDigest;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return clientDigest;
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
    public static void test1() {
        StatelessToken token = new StatelessToken("asd", null, "asdas");
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(token);
        beanWrapper.setPropertyValue(new PropertyValue("username", "hjzgg"));
        System.out.println(token.getUsername());
    }

    public static void test2() {
        StatelessToken token = new StatelessToken("asdqwe", null, null);
        DataBinder dataBinder = new DataBinder(token);
        Map<String, Object> params = new HashMap<>();
        params.put("username", "hjzgg1");
        PropertyValues propertyValues = new MutablePropertyValues(params);
        dataBinder.bind(propertyValues);
        System.out.println(token.getUsername());
    }
}
