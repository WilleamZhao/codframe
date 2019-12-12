package com.tlkj.cod.server.facade;

import com.tlkj.cod.launcher.model.CodModuleLauncherModel;

/**
 * Desc cod-server facade
 *
 * @author sourcod
 * @version 1.0
 * @className CodServerFacade
 * @date 2019/6/18 11:09 AM
 */
public interface CodServerFacade {

    /**
     * 启动
     */
    void start(CodModuleLauncherModel codModuleLauncherModel);

    /**
     * 停止
     */
    void stop();

    /**
     * 重启
     */
    void restart();
}
