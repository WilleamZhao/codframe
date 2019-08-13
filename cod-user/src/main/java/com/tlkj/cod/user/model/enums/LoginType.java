/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.user.model.enums;

/**
 * Desc 登录类型
 *
 * @author sourcod
 * @version 1.0
 * @className LoginType
 * @date 2019/3/24 2:21 PM
 */
public enum LoginType {

    PHONE(1, "手机号"),
    EMAIL(2, "邮件"),
    WEHCAT(3, "微信"),
    WEIBO(4, "微博"),
    FACEBOOK(5, "Facebook"),
    TWITTER(6, "Twitter"),
    GITHUB(7, "GitHub"),
    ALIPAY(8, "Alipay"),
    TAOBAO(9, "taobao"),
    OTHER1(10, "其他1"),
    OTHER2(11, "其他2"),
    OTHER3(12, "其他3");

    private int type;
    private String name;

    LoginType(int type, String name){
        this.type =  type;
        this.name = name;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static LoginType getType(String type){
        int i = Integer.parseInt(type);
        for (LoginType loginType : LoginType.values()){
            if (loginType.type == i){
                return loginType;
            }
        }
        return null;
    }

}
