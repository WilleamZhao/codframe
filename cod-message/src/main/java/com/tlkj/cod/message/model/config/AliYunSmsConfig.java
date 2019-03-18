/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.message.model.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Desc 阿里云消息服务配置
 *
 * @author sourcod
 * @version 1.0
 * @className AliYunSMSConfig
 * @date 2018/9/5 下午1:11
 */
@Component
public class AliYunSmsConfig {

    @Value("${sms.ali.accessKeyId:LTAIRa94f6t5si7d}")
    private String accessKeyId;

    @Value("${sms.ali.accessKeySecret:h5bH9DZdx36WXTOYaYgpTC3Fuyk7ZR}")
    private String accessKeySecret;

    @Value("${sms.ali.outId:tlkjOutId}")
    private String outId;

    @Value("${sms.ali.bdSignName:自动抢订票}")
    private String signName;

    @Value("${sms.ali.bdTemplateCode:SMS_143866792}")
    private String templateCode;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public String getOutId() {
        return outId;
    }

    public String getSignName() {
        return signName;
    }

    public String getTemplateCode() {
        return templateCode;
    }
}
