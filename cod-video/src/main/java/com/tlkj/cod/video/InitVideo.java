package com.tlkj.cod.video;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;

/**
 * Desc 初始化视频模块
 *
 * @author sourcod
 * @version 1.0
 * @className InitVideo
 * @date 2019/6/19 12:56 PM
 */
public class InitVideo implements CodModuleInitialize {
    @Override
    public int order() {
        return 0;
    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {

    }

    @Override
    public void fail(Throwable e) {

    }
}
