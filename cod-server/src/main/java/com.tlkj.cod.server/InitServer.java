/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.server;

import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.init.CodModuleServerInitialize;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;
import com.tlkj.cod.server.facade.CodServerFacade;
import com.tlkj.cod.server.facade.impl.CodServerFacadeImpl;
import com.tlkj.cod.server.model.config.CodServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Desc 初始化服务
 *
 * @author sourcod
 * @version 1.0
 * @className InitServer
 * @date 2019/4/28 5:11 PM
 */
public class InitServer implements CodModuleServerInitialize {

    private static Logger logger = LoggerFactory.getLogger(InitServer.class);

    @Override
    public int order() {
        return CodModuleOrderEnum.SERVER.getOrder();
    }

    @Override
    public String alias() {
        return "服务";
    }

    @Override
    public void success(CodModuleLauncherModel codModuleLauncherModel) {
        // TODO 从配置模块里读取
        CodServerFacade codServerFacade = codModuleLauncherModel.getBean(CodServerFacadeImpl.class);
        // CodServerConfig codServerModel = codModuleLauncherModel.getBean(CodServerConfig.class);
        // codModuleLauncherModel.setData(CodModuleOrderEnum.SERVER.getOrder(), codServerModel, false);
        codServerFacade.start(codModuleLauncherModel);
    }

    /**
     * 初始化
     * order < 0 : null;
     * @param codModuleLauncherModel 启动引导对象
     */
    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {
        codModuleLauncherModel.finish();
    }

    @Override
    public void fail(CodModuleLauncherModel codModuleLauncherModel, Throwable e) {
        logger.info("启动服务失败", e);
        codModuleLauncherModel.stop();
    }

    @Override
    public void fail(Throwable e) {

    }
}
