package com.tlkj.cod.search;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
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
        return CodModuleOrderEnum.NO.getOrder();
    }

    @Override
    public String alias() {
        return "搜索";
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

    }
}
