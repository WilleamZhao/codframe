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
 * Desc license 过滤器
 *
 * @author sourcod
 * @version 1.0
 * @className CodFilterLicenseConfig
 * @date 2019/12/4 8:14 PM
 */
@Getter
@Setter
@Component
public class CodFilterLicenseConfig {

    /**
     * 状态 ( 默认开启 )
     * TODO 开发环境默认关闭, 线上环境默认开启
     * 0: 关闭
     * 1: 开启
     */
    @Value("${cod.filter.license.state:1}")
    private String state;

    @Value("#{codData.getDataValue(\"cod.config.project.name\")}")
    private String projectName;
}
