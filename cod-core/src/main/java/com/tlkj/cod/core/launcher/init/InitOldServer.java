/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.launcher.init;

import com.tlkj.cod.core.main.Start;
import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;
import com.tlkj.cod.model.system.core.SystemModel;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className InitOldServer
 * @date 2019/4/29 3:34 PM
 */
public class InitOldServer implements CodModuleInitialize {

    private static final int DEFAULT_PORT = 9999;

    private static Yaml yaml = new Yaml();
    private static SystemModel system = null;

    @Override
    public int order() {
        return CodModuleOrderEnum.NO.getOrder();
    }

    /**
     * TODO 1.0.2
     * @param codModuleLauncherModel 小于0 null
     */
    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {
        System.out.println("开始初始化旧项目配置");
        InputStream in;
        try {
            System.out.println("开始启动codFrame");
            System.out.println("开始初始化配置文件......");
            // 获取config配置文件夹路径
            in = new FileInputStream("application.yml");
            if (in.available() != 0) {
                system = yaml.loadAs(in, SystemModel.getInstance().getClass());
                System.out.println("初始化配置文件成功");
            }
        } catch (IOException e) {
            try {
                in = Start.class.getResource("/application.yml").openStream();
                if (in.available() != 0) {
                    system = yaml.loadAs(in, SystemModel.getInstance().getClass());
                    System.out.println("加载codFrame框架配置文件application.yml成功！");
                }
            } catch (IOException e1) {
                System.out.println("加载codFrame配置文件错误" + e.getMessage());
            }
        }
        SystemModel.getInstance().setInstance(system);
    }

    @Override
    public void fail(Throwable e) {

    }
}
