/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.action;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.model.LauncherModel;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className InitAction
 * @date 2019/4/28 9:49 AM
 */
public class InitAction implements CodModuleInitialize {

    @Override
    public String name() {
        return "action模块";
    }

    @Override
    public int order() {
        return -2;
    }

    @Override
    public void init(LauncherModel launcherModel) {

    }

    @Override
    public void fail(Throwable e) {

    }
}
