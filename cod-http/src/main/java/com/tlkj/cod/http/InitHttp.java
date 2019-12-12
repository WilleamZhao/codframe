package com.tlkj.cod.http;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;

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
    public int order() {
        return CodModuleOrderEnum.NO.getOrder();
    }

    @Override
    public String alias() {
        return "HTTP";
    }

    @Override
    public void success(CodModuleLauncherModel codModuleLauncherModel) {

    }

    @Override
    public void fail(CodModuleLauncherModel codModuleLauncherModel, Throwable e) {

    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {

    }

    @Override
    public void fail(Throwable e) {
        System.out.println("初始化http模块失败");
    }
}
