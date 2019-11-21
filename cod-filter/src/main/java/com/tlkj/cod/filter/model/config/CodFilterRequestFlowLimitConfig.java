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
 * Desc 请求流量限制配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodFilterRequestFlowLimitConfig
 * @date 2019/11/19 5:29 PM
 */
@Getter
@Setter
@Component
public class CodFilterRequestFlowLimitConfig {

    /**
     * 状态
     * 0: 关闭
     * 1: 开启
     */
    @Value("${cod.filter.flowLimit.state:0}")
    private String state;

    /**
     * 允许最大阀值 ( 每秒请求次数 )
     */
    @Value("${cod.filter.flowLimit.maxAllow:10000}")
    private String maxAllow;

    /**
     * 是否自动加入黑名单
     * 0: 不自动
     * 1: 自动
     */
    @Value("${cod.filter.flowLimit.isBlack:1}")
    private String isBlack;

    /**
     * 不监控接口
     * TODO 用逗号分隔, 或每行一个
     */
    @Value("${cod.filter.flowLimit.urls:}")
    private String urls;
}
