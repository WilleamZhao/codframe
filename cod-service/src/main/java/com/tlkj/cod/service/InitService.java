package com.tlkj.cod.service;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.model.LauncherModel;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className InitService
 * @date 2019/5/30 10:20 AM
 */
public class InitService implements CodModuleInitialize {

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
