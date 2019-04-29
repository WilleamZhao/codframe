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

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.model.LauncherModel;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className InitServlet
 * @date 2019/4/28 4:16 PM
 */
public class InitServlet implements CodModuleInitialize {

    @Override
    public int order() {
        return 50;
    }

    @Override
    public void init(LauncherModel launcherModel) {

        System.out.println("初始化servlet");
    }

    @Override
    public void fail(Throwable e) {
        System.out.println("初始化servlet失败");
    }
}
