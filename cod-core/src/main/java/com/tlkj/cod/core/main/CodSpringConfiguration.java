/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.core.main;

import com.tlkj.cod.common.CodCommonSpringContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
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
     * 文件上传
     */
    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        //resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
        resolver.setResolveLazily(true);
        resolver.setMaxInMemorySize(40960);
        //上传文件大小 50M 50*1024*1024
        resolver.setMaxUploadSize(50*1024*1024);
        return resolver;
    }

    /**
     * 全局获取 spring 上下文
     */
    @Bean
    public CodCommonSpringContext codCommonSpringContext(){
        return new CodCommonSpringContext();
    }

}
