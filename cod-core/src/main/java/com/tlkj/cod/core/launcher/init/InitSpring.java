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
import com.tlkj.cod.launcher.model.LauncherModel;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className InitSpring
 * @date 2019/4/28 4:03 PM
 */
public class InitSpring implements CodModuleInitialize {
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
        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.register(CodSpringConfiguration.class);
        annotationConfigWebApplicationContext.refresh();
        // set spring
        launcherModel.setSpring(annotationConfigWebApplicationContext);
    }

    @Override
    public void fail(Throwable e) {
        System.out.println("初始化失败");
    }
}
