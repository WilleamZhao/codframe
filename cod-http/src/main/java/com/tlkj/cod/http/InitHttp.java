package com.tlkj.cod.http;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.model.LauncherModel;

/**
 * Desc cod http模块
 *
 * @author sourcod
 * @version 1.0
 * @className InitHttp
 * @date 2019/4/30 6:55 PM
 */
public class InitHttp implements CodModuleInitialize {

    @Override
    public String name() {
        return "HTTP";
    }

    @Override
    public int order() {
        return 101;
    }

    @Override
    public void init(LauncherModel launcherModel) {
        System.out.println("开始初始化http模块");
    }

    @Override
    public void fail(Throwable e) {
        System.out.println("初始化http模块失败");
    }
}
