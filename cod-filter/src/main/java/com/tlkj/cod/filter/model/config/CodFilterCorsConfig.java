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
 * Desc cors 跨域设置过滤器
 *
 * @author sourcod
 * @version 1.0
 * @className CodFilterCorsConfig
 * @date 2019/11/8 7:55 PM
 */
@Component
public class CodFilterCorsConfig {

    /**
     * 状态 ( 默认开启 )
     * 0: 关闭
     * 1: 开启
     */
    @Value("cod.filter.cors.state:1")
    private String state;

}
