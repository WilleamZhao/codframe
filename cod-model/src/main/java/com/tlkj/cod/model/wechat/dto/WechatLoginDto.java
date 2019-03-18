/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.model.wechat.dto;

/**
 * Desc 登录接口返回Dto
 *
 * @author sourcod
 * @version 1.0
 * @className WechatLoginDto
 * @date 2018/11/5 下午6:05
 */
public class WechatLoginDto {

    /**
     * openId
     */
    private String openId;

    /**
     * unionId
     */
    private String unionid;

    /**
     * token;
     */
    private String token;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
