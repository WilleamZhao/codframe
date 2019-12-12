/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.pay.model.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Desc 微信支付配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodPayWechatConfig
 * @date 2019/8/21 12:28 PM
 */
@Getter
@Setter
@Component
public class CodPayWechatConfig {

    private String appid;
    private String key;
    private String partnerid;
}
