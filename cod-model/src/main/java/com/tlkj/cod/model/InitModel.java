package com.tlkj.cod.model;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;

/**
 * Desc 初始化model
 *
 * @author sourcod
 * @version 1.0
 * @className InitModel
 * @date 2019/5/30 10:25 AM
 */
public class InitModel implements CodModuleInitialize {

    @Override
    public int order() {
        return -5;
    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {
        /*Object o = codModuleLauncherModel.getSpring().getBean(CodDataSourceConfig.class);
        System.out.println(CodCommonJson.dump(o));
        o = codModuleLauncherModel.getSpring().getBean(CodCoreConfig.class);
        System.out.println(CodCommonJson.dump(o));*/
    }

    @Override
    public void fail(Throwable e) {

    }
}
