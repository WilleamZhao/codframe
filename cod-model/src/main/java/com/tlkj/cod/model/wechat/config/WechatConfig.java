/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.model.wechat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Desc 微信配置
 *
 * @author sourcod
 * @version 1.0
 * @className WechatConfig
 * @date 2018/9/13 下午1:55
 */
@Component
public class WechatConfig {

    /**
     * appId
     */
    @Value("${wechat.small.appid:wx66ff5939e0e1e1fa}")
    private String appId;

    /**
     * 秘钥
     */
    @Value("${wechat.small.secret:99110ac0b0a99837b1fbcc79342eef8f}")
    private String secret;

    /**
     * 商户号
     */
    @Value("${wechat.pay.mchId:1513557591}")
    private String mchId;

    /**
     * 通知地址
     */
    @Value("${wechat.pay.notifyUrl:https://api.sourcod.com/zdqdp/wechat/pay/notify}")
    private String notifyUrl;

    public String getAppId() {
        return appId;
    }

    public String getSecret() {
        return secret;
    }

    public String getMchId() {
        return mchId;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }
}
