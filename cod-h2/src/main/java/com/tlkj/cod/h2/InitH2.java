/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.h2;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.model.LauncherModel;

import javax.servlet.ServletContext;

/**
 * Desc 初始化h2
 *
 * @author sourcod
 * @version 1.0
 * @className InitH2
 * @date 2019/4/25 1:44 PM
 */
public class InitH2 implements CodModuleInitialize {

    @Override
    public int order() {
        return 0;
    }

    @Override
    public void init(LauncherModel launcherModel) {

    }

    @Override
    public void fail(Throwable e) {

    }
}
