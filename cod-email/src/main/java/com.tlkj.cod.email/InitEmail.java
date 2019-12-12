/*
 * Copyright (c) 2019.
 * sourcod.com
 * All rights reserved
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.email;

import com.tlkj.cod.launcher.init.CodModuleDataInitialize;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;

/**
 * 邮件模块
 * @author sourcod
 */
public class InitEmail implements CodModuleDataInitialize {

    @Override
    public int order() {
        return 0;
    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {

    }

    @Override
    public void fail(java.lang.Throwable e) {

    }
}
