/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.config;

import com.google.common.collect.Lists;
import com.tlkj.cod.config.service.impl.CodConfigDataServiceImpl;
import com.tlkj.cod.config.spring.config.PropertySourcesProcessor;
import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;
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
        return CodModuleOrderEnum.CONFIG.getOrder();
    }

    @Override
    public String alias() {
        return "配置";
    }

    @Override
    public void success(CodModuleLauncherModel codModuleLauncherModel) {

    }

    @Override
    public void fail(CodModuleLauncherModel codModuleLauncherModel, Throwable e) {
        codModuleLauncherModel.stop();
    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {
        AnnotationConfigWebApplicationContext applicationContext = codModuleLauncherModel.getSpring();

        // 支持placeholder
        applicationContext.register(PropertySourcesPlaceholderConfigurer.class);

        // 注册codData数据源
        PropertySourcesProcessor.addCodConfigDataSource(Lists.newArrayList(new CodConfigDataServiceImpl()), 0);

        // 定义processor
        PropertySourcesProcessor propertySourcesProcessor = new PropertySourcesProcessor();
        propertySourcesProcessor.setEnvironment(applicationContext.getEnvironment());
        applicationContext.addBeanFactoryPostProcessor(propertySourcesProcessor);
        codModuleLauncherModel.finish();
    }

    @Override
    public void fail(Throwable e) {
        System.out.println("停止服务");
    }
}
