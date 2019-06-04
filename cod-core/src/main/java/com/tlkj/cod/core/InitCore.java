/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.model.LauncherModel;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

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
        return 1;
    }

    @Override
    public void init(LauncherModel launcherModel) {
        // WebApplicationContextUtils.getWebApplicationContext(servletContext);
    }

    @Override
    public void fail(Throwable throwable) {

    }
}
