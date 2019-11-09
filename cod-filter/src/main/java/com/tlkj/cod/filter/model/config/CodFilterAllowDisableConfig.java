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
 * Desc 黑白名单配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodFilterAllowDisableConfig
 * @date 2019/11/8 7:45 PM
 */
@Component
@Getter
@Setter
public class CodFilterAllowDisableConfig {

    /**
     * 状态
     * 0: 关闭
     * 1: 开启
     */
    @Value("cod.filter.allowDisable.state:0")
    private String state;

    /**
     * 类型
     *
     * 0: 黑名单
     * 1: 白名单
     */
    @Value("cod.filter.allowDisable.type:0")
    private String type;

    /**
     * 白名单 ip
     * 方式
     * 一: 网段 (192.168.1.0/16)
     * 二: 范围 (192.168.1.1-192.168.1.254)
     * 三: 固定 ip (192.168.1.3)
     * 多个允许用逗号分隔 (192.168.1.3,192.168.2.0/24,192.168.1.1-192.168.1.254)
     */
    @Value("cod.filter.allowDisable.allow:")
    private String allow;

    /**
     * 黑名单 ip
     * 方式
     * 一: 网段 (192.168.1.0/16)
     * 二: 范围 (192.168.1.1-192.168.1.254)
     * 三: 固定 ip (192.168.1.3)
     * 多个允许用逗号分隔 (192.168.1.3,192.168.2.0/24,192.168.1.1-192.168.1.254)
     */
    @Value("cod.filter.allowDisable.disable:")
    private String disable;


}
