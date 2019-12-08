package com.tlkj.cod.log;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className com.tlkj.cod.log.InitLog
 * @date 2019/5/30 10:36 AM
 */
public class InitLog implements CodModuleInitialize {

    @Override
    public int order() {
        return CodModuleOrderEnum.LOG.getOrder();
    }

    @Override
    public String alias() {
        return "日志";
    }

    @Override
    public void success(CodModuleLauncherModel codModuleLauncherModel) {

    }

    @Override
    public void fail(CodModuleLauncherModel codModuleLauncherModel, Throwable e) {

    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {
        codModuleLauncherModel.finish();
    }

    @Override
    public void fail(Throwable e) {

    }
}
