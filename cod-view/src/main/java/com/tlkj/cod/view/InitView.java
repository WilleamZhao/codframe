package com.tlkj.cod.view;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;

/**
 * Desc 初始化 view
 *
 * @author sourcod
 * @version 1.0
 * @className InitView
 * @date 2019/6/4 10:22 PM
 */
public class InitView implements CodModuleInitialize {

    @Override
    public int order() {
        return CodModuleOrderEnum.NO.getOrder();
    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {

    }

    @Override
    public void fail(Throwable e) {

    }
}
