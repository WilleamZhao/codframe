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

import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.init.CodModuleServletInitialize;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;

/**
 * Desc servlet 初始化
 *
 * @author sourcod
 * @version 1.0
 * @className InitModuleServlet
 * @date 2019/4/28 4:16 PM
 */
public class InitModuleServlet implements CodModuleServletInitialize {

    @Override
    public String name() {
        return "servlet";
    }

    @Override
    public int order() {
        return CodModuleOrderEnum.SERVLET.getOrder();
    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {

        // System.out.println("初始化servlet");
    }

    @Override
    public void fail(Throwable e) {
        // System.out.println("初始化servlet失败");
    }
}
