package com.tlkj.cod.admin;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.model.LauncherModel;

/**
 * Desc 初始化管理模块
 *
 * @author sourcod
 * @version 1.0
 * @className InitAdmin
 * @date 2019/5/17 1:39 PM
 */
public class InitAdmin implements CodModuleInitialize {

    @Override
    public int order() {
        return 1;
    }

    @Override
    public void init(LauncherModel launcherModel) {
        System.out.println("初始化成功");
    }

    @Override
    public void fail(Throwable e) {

    }
}
