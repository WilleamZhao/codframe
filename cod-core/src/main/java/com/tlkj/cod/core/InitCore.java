/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.core;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;

/**
 * Desc 初始化 core
 *
 * @author sourcod
 * @version 1.0
 * @className InitCore
 * @date 2019/4/26 10:34 AM
 */
public class InitCore implements CodModuleInitialize {

    @Override
    public int order() {
        return CodModuleOrderEnum.CORE.getOrder();
    }

    @Override
    public String alias() {
        return "核心";
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

    }

    @Override
    public void fail(Throwable throwable) {

    }
}
