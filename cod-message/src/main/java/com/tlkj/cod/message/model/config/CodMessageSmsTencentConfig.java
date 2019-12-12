/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.message.model.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Desc 腾讯短信配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodMessageSmsTencentConfig
 * @date 2019/11/3 8:42 PM
 */
@Getter
@Setter
@Component
public class CodMessageSmsTencentConfig {

    @Value("${cod.message.sms.tencent.accessKeyId:}")
    private String accessKeyId;

    @Value("${cod.message.sms.tencent.accessKeySecret:}")
    private String accessKeySecret;

    @Value("${cod.message.sms.tencent.outId:tlkjOutId}")
    private String outId;

    @Value("${cod.message.sms.tencent.bdSignName:}")
    private String signName;

    @Value("${cod.message.sms.tencent.bdTemplateCode:}")
    private String templateCode;
}
