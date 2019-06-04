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
import com.tlkj.cod.launcher.CodModuleOrderEnum;
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
    public int order() {
        return CodModuleOrderEnum.NO.getOrder();
    }

    @Override
    public void init(LauncherModel launcherModel) {

    }

    @Override
    public void fail(Throwable e) {

    }
}
