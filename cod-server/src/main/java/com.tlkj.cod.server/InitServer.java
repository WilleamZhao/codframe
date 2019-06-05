/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.server;

import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.init.CodModuleServerInitialize;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;
import com.tlkj.cod.server.model.CodServerFilterModel;
import com.tlkj.cod.server.model.server.CodServerModel;
import com.tlkj.cod.server.service.CodServerService;
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

    @Override
    public int order() {
        return CodModuleOrderEnum.SERVER.getOrder();
    }


    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {

        codModuleLauncherModel.getSpring().refresh();

        // TODO 从配置模块里读取
        CodServerService codServerService = (CodServerService) codModuleLauncherModel.getSpring().getBean("codServerJetty");
        setCharacterEncodingFilter();
        codModuleLauncherModel.setServer(CodServerModel.getInstance());
        codServerService.start(codModuleLauncherModel);
    }

    @Override
    public void fail(Throwable e) {
        System.out.println("启动服务失败");
        e.printStackTrace();
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
