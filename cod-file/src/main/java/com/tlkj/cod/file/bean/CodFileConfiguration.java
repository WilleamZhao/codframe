/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.file.bean;

import com.tlkj.cod.file.model.config.CodFileConfig;
import com.tlkj.cod.spring.model.config.CodSpringConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Desc 文件配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodFileConfiguration
 * @date 2019/11/20 2:05 PM
 */
@Configuration
public class CodFileConfiguration extends CodSpringConfiguration {

    @Bean(name = "codFileConfig")
    public CodFileConfig codFileConfig(){
        return new CodFileConfig();
    }

    /**
     * 文件上传
     */
    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver(CodFileConfig codFileConfig){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        //resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
        resolver.setResolveLazily(true);

        int memorySize = codFileConfig.getMemorySize();
        resolver.setMaxInMemorySize(memorySize);

        //上传文件大小 50M 50*1024*1024
        int uploadSize = codFileConfig.getUploadSize();
        resolver.setMaxUploadSize(uploadSize);
        return resolver;
    }
}
