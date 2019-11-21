/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.spring.model.config;

import com.tlkj.cod.spring.common.CodSpringContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Desc Cod SpringBean 配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodConfiguration
 * @date 2018/12/19 10:59 PM
 */
@Configuration
/**
 * 支持注解
 * cglib 代理
 */
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Lazy
public class CodSpringConfiguration extends WebMvcConfigurerAdapter{

    /**
     * 支持@Value
     * 支持 placeholder
     * @return
     */
    @Bean
    public PropertySourcesPlaceholderConfigurer sourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * 全局获取 spring 上下文
     */
    @Bean
    public CodSpringContext codCommonSpringContext(){
        return new CodSpringContext();
    }

}
