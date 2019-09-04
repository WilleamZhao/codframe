/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.server.model.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Desc 启动服务配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodServerServiceConfig
 * @date 2019/9/3 3:05 PM
 */
@Getter
@Setter
@Component
public class CodServerServiceConfig {

    /**
     * 服务类型
     */
    @Value("${cod.server.config.type:codServerJetty}")
    private String type;

    /**
     * 端口
     */
    @Value("${cod.server.config.port:9999}")
    private String port;

    /**
     * 环境
     * 0: 开发环境
     * 1: 测试环境
     * 2: 正式环境
     *
     */
    @Value("${cod.server.config.port:0}")
    private String env;

    /**
     * 项目名称
     */
    @Value("${cod.server.config.project.name:codframe}")
    private String projectName;

    /**
     * 编码方式
     */
    @Value("${cod.server.config.encode:UTF-8}")
    private String encode;

    /**
     * 服务版本
     */
    @Value("${cod.server.config.version:1.0.2}")
    private String version;

    /**
     * 单机/集群运行
     * 0: 单机
     * 1: 集群
     */
    @Value("${cod.server.config.mode:0}")
    private String mode;


}
