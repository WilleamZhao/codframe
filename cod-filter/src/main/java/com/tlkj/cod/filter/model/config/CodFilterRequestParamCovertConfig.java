/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.filter.model.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Desc 请求参数转换 过滤器配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodFilterRequestParamCovertConfig
 * @date 2019/11/8 7:58 PM
 */
@Component
public class CodFilterRequestParamCovertConfig {

    /**
     * 状态
     * 0: 关闭
     * 1: 开启
     */
    @Value("${cod.filter.paramConvert.state:0}")
    private String state;

}
