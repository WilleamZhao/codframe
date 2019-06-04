/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.launcher.init;


import com.tlkj.cod.core.main.CodSpringConfiguration;
import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.init.CodSpringInitialize;
import com.tlkj.cod.launcher.model.LauncherModel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


/**
 * Desc 初始化 spring
 *
 * @author sourcod
 * @version 1.0
 * @className InitSpring
 * @date 2019/4/28 4:03 PM
 */
public class InitSpring implements CodSpringInitialize {

    /**
     * spring scan
     * @return
     */
    @Override
    public String name() {
        return "com.tlkj.cod.spring";
    }

    @Override
    public int order() {
        return CodModuleOrderEnum.SPRING.getOrder();
    }

    @Override
    public void init(LauncherModel launcherModel) {
        System.out.println("开始初始化spring");

        /*
         * spring annotation
         */
        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = launcherModel.getSpring();
        annotationConfigWebApplicationContext.register(CodSpringConfiguration.class);
        annotationConfigWebApplicationContext.scan("com.tlkj.cod.core");
        annotationConfigWebApplicationContext.refresh();
        // set spring
        launcherModel.setSpring(annotationConfigWebApplicationContext);
    }

    @Override
    public void fail(Throwable e) {
        System.out.println("初始化失败");
    }
}
