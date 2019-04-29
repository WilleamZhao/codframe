/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.config;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.model.LauncherModel;

/**
 * Desc 配置初始化
 *
 * @author sourcod
 * @version 1.0
 * @className init
 * @date 2019/4/9 4:58 PM
 */
public class InitConfig implements CodModuleInitialize {

    @Override
    public int order() {
        return 1;
    }

    @Override
    public void init(LauncherModel launcherModel) {
        System.out.println("开始初始化配置");
    }

    @Override
    public void fail(Throwable e) {

    }
}
