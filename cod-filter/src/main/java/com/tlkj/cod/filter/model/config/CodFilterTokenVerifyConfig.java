/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.filter.model.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Desc token 验证
 *
 * @author sourcod
 * @version 1.0
 * @className CodFilterTokenVerifyConfig
 * @date 2019/11/17 10:24 AM
 */
@Getter
@Setter
@Component
public class CodFilterTokenVerifyConfig {

    /**
     * 状态
     * 0: 关闭
     * 1: 开启
     */
    @Value("${cod.filter.tokenVerify.state:0}")
    private String state;

    /**
     * 类型
     *
     */
    @Value("${cod.filter.tokenVerify.type:1}")
    private String type;

    /**
     * 规则 ( 默认微信规则 )
     * 1: 参考微信规则 字典序排序拼接加secret+key md5 sign
     * 2:
     * 3:
     * 4:
     */
    @Value("${cod.filter.tokenVerify.rule:1}")
    private String rule;

}
