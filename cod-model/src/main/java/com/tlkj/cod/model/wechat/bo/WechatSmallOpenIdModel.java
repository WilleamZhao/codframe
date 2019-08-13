/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.model.wechat.bo;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Desc 微信小程序openId
 *
 * @author sourcod
 * @version 1.0
 * @className WechatSmallOpenIdModel
 * @date 2018/9/13 下午2:06
 */
public class WechatSmallOpenIdModel {

    @JsonProperty(value = "openid")
    private String openId;

    @JsonProperty(value = "session_key")
    private String sessionKey;

    @JsonProperty(value = "unionid")
    private String unionid;

    @JsonProperty(value = "errcode")
    private String errcode;

    @JsonProperty(value = "errmsg")
    private String errmsg;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
