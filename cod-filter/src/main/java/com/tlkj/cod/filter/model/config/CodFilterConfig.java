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
 * Desc 过滤器配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodFilterConfig
 * @date 2019/11/8 7:18 PM
 */
@Component
@Getter
@Setter
public class CodFilterConfig {

    @Value("cod.filter.config.type:")
    private String config;

    private String sort;

}
