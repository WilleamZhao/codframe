package com.tlkj.cod.model;

import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.config.model.config.CodCoreConfig;
import com.tlkj.cod.dao.model.config.CodDataSourceConfig;
import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.model.LauncherModel;

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
    public void init(LauncherModel launcherModel) {
        Object o = launcherModel.getSpring().getBean(CodDataSourceConfig.class);
        System.out.println(CodCommonJson.dump(o));
        o = launcherModel.getSpring().getBean(CodCoreConfig.class);
        System.out.println(CodCommonJson.dump(o));
    }

    @Override
    public void fail(Throwable e) {

    }
}
