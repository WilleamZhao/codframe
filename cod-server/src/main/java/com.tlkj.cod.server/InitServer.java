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
import com.tlkj.cod.server.model.CodServerFilterModel;
import com.tlkj.cod.server.model.server.CodServerModel;
import com.tlkj.cod.server.service.CodServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.HashMap;
import java.util.Map;

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
        CodServerFacade codServerFacade = (CodServerFacade) codModuleLauncherModel.getSpring().getBean("codServerFacadeImpl");
        // 设置编码过滤器
        setCharacterEncodingFilter();
        codModuleLauncherModel.setServer(CodServerModel.getInstance());
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

    /**
     * TODO 编码方式
     */
    private void setCharacterEncodingFilter(){
        CodServerModel codServer = CodServerModel.getInstance();
        CodServerFilterModel codServerFilterModel = new CodServerFilterModel();
        codServerFilterModel.setMapping("/*");
        codServerFilterModel.setFilter(new CharacterEncodingFilter());
        codServerFilterModel.setName("character");

        Map map = new HashMap();
        map.put("encoding", "UTF-8");
        map.put("forceEncoding", "true");
        codServerFilterModel.setParamList(map);

        codServer.addFilter(codServerFilterModel);

    }
}
