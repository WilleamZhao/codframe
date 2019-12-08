/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.spring;


import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.init.CodModuleSpringInitialize;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;
import com.tlkj.cod.spring.model.config.CodSpringConfiguration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


/**
 * Desc 初始化 spring
 *
 * @author sourcod
 * @version 1.0
 * @className InitModuleSpring
 * @date 2019/4/28 4:03 PM
 */
public class InitSpring implements CodModuleSpringInitialize {

    @Override
    public String alias() {
        return "spring";
    }

    @Override
    public int order() {
        return CodModuleOrderEnum.SPRING.getOrder();
    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {
        // set spring
        codModuleLauncherModel.finish();
    }

    @Override
    public void success(CodModuleLauncherModel codModuleLauncherModel) {
        // AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = codModuleLauncherModel.getSpring();
        // codModuleLauncherModel.setSpring(annotationConfigWebApplicationContext);
    }

    @Override
    public void fail(Throwable e) {
        System.out.println("初始化失败");
    }
}
