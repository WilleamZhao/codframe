package com.tlkj.cod.gwgz;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.model.LauncherModel;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className InitGwgz
 * @date 2019/4/29 6:36 PM
 */
public class InitGwgz implements CodModuleInitialize {

    @Override
    public int order() {
        return 10;
    }

    @Override
    public void init(LauncherModel launcherModel) {
        System.out.println("初始化古文观止");
    }

    @Override
    public void fail(Throwable e) {

    }
}
