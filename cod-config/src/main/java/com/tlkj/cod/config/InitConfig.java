/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.config;

import com.google.common.collect.Lists;
import com.tlkj.cod.config.service.impl.CodConfigDataServiceImpl;
import com.tlkj.cod.config.spring.config.PropertySourcesProcessor;
import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.model.LauncherModel;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Desc 配置初始化
 *
 * @author sourcod
 * @version 1.0
 * @className init
 * @date 2019/4/9 4:58 PM
 */
public class InitConfig implements CodModuleInitialize {

    @Override
    public int order() {
        return -50;
    }

    @Override
    public void init(LauncherModel launcherModel) {
        System.out.println("开始初始化配置");
        AnnotationConfigWebApplicationContext applicationContext = launcherModel.getSpring();

        // 支持placeholder
        applicationContext.register(PropertySourcesPlaceholderConfigurer.class);

        // 注册codData数据源
        PropertySourcesProcessor.addCodConfigDataSource(Lists.newArrayList(new CodConfigDataServiceImpl()), 0);

        // 定义processor
        PropertySourcesProcessor propertySourcesProcessor = new PropertySourcesProcessor();
        propertySourcesProcessor.setEnvironment(applicationContext.getEnvironment());
        applicationContext.addBeanFactoryPostProcessor(propertySourcesProcessor);
        launcherModel.finish();
    }

    @Override
    public void fail(Throwable e) {

    }
}
