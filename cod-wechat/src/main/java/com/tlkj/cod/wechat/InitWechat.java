/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.wechat;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className InitWechat
 * @date 2019/8/23 4:09 PM
 */
public class InitWechat implements CodModuleInitialize {

    @Override
    public String alias() {
        return "微信";
    }

    @Override
    public int order() {
        return 60;
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
