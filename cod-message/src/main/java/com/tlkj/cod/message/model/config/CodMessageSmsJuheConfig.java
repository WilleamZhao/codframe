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
 * Desc 聚合短信配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodMessageSmsJuheConfig
 * @date 2019/11/3 8:45 PM
 */
@Getter
@Setter
@Component
public class CodMessageSmsJuheConfig {

    @Value("${cod.message.sms.juhe.accessKeyId:}")
    private String accessKeyId;

    @Value("${cod.message.sms.juhe.accessKeySecret:}")
    private String accessKeySecret;

    @Value("${cod.message.sms.juhe.outId:tlkjOutId}")
    private String outId;

    @Value("${cod.message.sms.juhe.bdSignName:}")
    private String signName;

    @Value("${cod.message.sms.juhe.bdTemplateCode:}")
    private String templateCode;
}
