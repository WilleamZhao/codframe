package com.tlkj.cod.facade;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.model.LauncherModel;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className InitFacade
 * @date 2019/5/30 10:21 AM
 */
public class InitFacade implements CodModuleInitialize {
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
