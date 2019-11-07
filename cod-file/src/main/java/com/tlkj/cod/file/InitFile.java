package com.tlkj.cod.file;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;

/**
 * Desc 初始化文件模块
 *
 * @author sourcod
 * @version 1.0
 * @className InitFile
 * @date 2019/6/4 11:36 PM
 */
public class InitFile implements CodModuleInitialize {

    @Override
    public int order() {
        return CodModuleOrderEnum.MODULE.getOrder();
    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {
        codModuleLauncherModel.finish();
    }

    @Override
    public void fail(Throwable e) {

    }
}
