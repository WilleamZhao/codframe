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

import com.tlkj.cod.filter.CorsFilter;
import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.init.CodServerInitialize;
import com.tlkj.cod.launcher.model.LauncherModel;
import com.tlkj.cod.server.model.FilterModel;
import com.tlkj.cod.server.model.server.CodServerModel;
import com.tlkj.cod.server.service.CodServer;
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
public class InitServer implements CodServerInitialize {

    @Override
    public int order() {
        return CodModuleOrderEnum.SERVER.getOrder();
    }


    @Override
    public void init(LauncherModel launcherModel) {

        launcherModel.getSpring().refresh();

        // TODO 从配置模块里读取
        CodServer codServer = (CodServer) launcherModel.getSpring().getBean("codServerJetty");
        System.out.println("初始化server");
        launcherModel.setServer(this.setCodServerDefault());
        codServer.start(launcherModel);
    }

    @Override
    public void fail(Throwable e) {
        System.out.println("启动服务失败");
        e.printStackTrace();
    }

    /**
     * TODO 设置默认配置
     * @return
     */
    private CodServerModel setCodServerDefault(){
        CodServerModel codServer = new CodServerModel();
        FilterModel filterModel = new FilterModel();
        filterModel.setMapping("/*");
        filterModel.setFilter(new CorsFilter());
        filterModel.setName("cors");
        codServer.addFilter(filterModel);

        FilterModel filterModel1 = new FilterModel();
        filterModel1.setMapping("/*");
        filterModel1.setFilter(new CharacterEncodingFilter());
        filterModel1.setName("character");

        Map map = new HashMap();
        map.put("encoding", "UTF-8");
        map.put("forceEncoding", "true");
        filterModel1.setParamList(map);

        codServer.addFilter(filterModel1);
        return codServer;
    }
}
