package com.tlkj.cod.search;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className InitSearch
 * @date 2019/6/5 10:47 AM
 */
public class InitSearch implements CodModuleInitialize {

    @Override
    public int order() {
        return Integer.MIN_VALUE;
    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {

    }

    @Override
    public void fail(Throwable e) {

    }
}
