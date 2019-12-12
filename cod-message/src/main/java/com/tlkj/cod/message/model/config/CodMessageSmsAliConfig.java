/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.message.model.config;

import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
@Component
public class CodMessageSmsAliConfig {

    @Value("${cod.message.sms.ali.accessKeyId:LTAIRa94f6t5si7d}")
    private String accessKeyId;

    @Value("${cod.message.sms.ali.accessKeySecret:h5bH9DZdx36WXTOYaYgpTC3Fuyk7ZR}")
    private String accessKeySecret;

    @Value("${cod.message.sms.ali.outId:tlkjOutId}")
    private String outId;

    @Value("${cod.message.sms.ali.bdSignName:}")
    private String signName;

    @Value("${cod.message.sms.ali.bdTemplateCode:SMS_143866792}")
    private String templateCode;

}
