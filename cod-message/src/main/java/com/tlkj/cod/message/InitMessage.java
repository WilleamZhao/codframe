/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.message;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;

/**
 * Desc 初始化消息模块
 *
 * @author sourcod
 * @version 1.0
 * @className InitMessage
 * @date 2019/8/23 4:17 PM
 */
public class InitMessage implements CodModuleInitialize {

    @Override
    public String alias() {
        return "消息";
    }

    @Override
    public int order() {
        return CodModuleOrderEnum.NO.getOrder();
    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {

    }

    @Override
    public void success(CodModuleLauncherModel codModuleLauncherModel) {

    }

    @Override
    public void fail(CodModuleLauncherModel codModuleLauncherModel, Throwable e) {

    }
}
