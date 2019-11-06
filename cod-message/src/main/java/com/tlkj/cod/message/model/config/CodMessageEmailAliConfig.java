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
 * Desc 阿里云 email 配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodMessageEmailAliConfig
 * @date 2019/11/3 8:50 PM
 */
@Getter
@Setter
@Component
public class CodMessageEmailAliConfig {

    @Value("${cod.message.email.ali.accessKeyId:LTAIRa94f6t5si7d}")
    private String accessKeyId;

    @Value("${cod.message.email.ali.accessKeySecret:h5bH9DZdx36WXTOYaYgpTC3Fuyk7ZR}")
    private String accessKeySecret;

    @Value("${cod.message.email.ali.outId:tlkjOutId}")
    private String outId;

    @Value("${cod.message.email.ali.bdSignName:}")
    private String signName;

    @Value("${cod.message.email.ali.bdTemplateCode:SMS_143866792}")
    private String templateCode;
}
