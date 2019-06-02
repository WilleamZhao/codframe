/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.api;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.model.LauncherModel;

import javax.servlet.ServletContext;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className InitApi
 * @date 2019/4/28 9:47 AM
 */
public class InitApi implements CodModuleInitialize {

    @Override
    public int order() {
        return -1;
    }

    @Override
    public void init(LauncherModel launcherModel) {

    }

    @Override
    public void fail(Throwable e) {

    }
}
