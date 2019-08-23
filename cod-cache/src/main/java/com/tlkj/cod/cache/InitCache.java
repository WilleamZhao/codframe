/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.cache;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;


/**
 * Desc 模块初始化方法
 *
 * @author sourcod
 * @version 1.0
 * @className InitCache
 * @date 2019/2/12 10:30 AM
 */
public class InitCache implements CodModuleInitialize {

    @Override
    public int order() {
        return -4;
    }

    @Override
    public String alias() {
        return "缓存";
    }

    @Override
    public void success(CodModuleLauncherModel codModuleLauncherModel) {

    }

    @Override
    public void fail(CodModuleLauncherModel codModuleLauncherModel, Throwable e) {

    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {
        /*Object o = codModuleLauncherModel.getSpring().getBean(CodDataSourceConfig.class);
        System.out.println(CodCommonJson.dump(o));*/

    }

    @Override
    public void fail(Throwable e) {

    }
}
