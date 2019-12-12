/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.launcher;

import com.tlkj.cod.launcher.model.CodModuleLauncherModel;

/**
 * Desc 初始化模块方法
 * 0: 初始化 spring
 * 100: 启动服务
 *
 * @author sourcod
 * @version 1.0
 * @className CodModuleInitialize
 * @date 2019/4/25 3:37 PM
 */
public interface CodModuleInitialize {

    /**
     * spring scan package name
     * @return scan package name
     */
    default String name(){
        return this.getClass().getPackage().getName();
    }

    /**
     * 模块别名
     * @return 别名
     */
    default String alias(){
        return "";
    }

    /**
     * 设置模块顺序
     * 小于0 启动服务之前 (优先级最高)
     * 大于0 启动服务之后 (可获取到ServletContext)
     * @return 模块启动顺序
     */
    int order();

    /**
     * 模块初始化
     * @param codModuleLauncherModel 小于0 null
     * @return
     */
    void init(CodModuleLauncherModel codModuleLauncherModel);

    /**
     * 模块初始化成功
     * @param codModuleLauncherModel 启动引导对象
     */
    default void success(CodModuleLauncherModel codModuleLauncherModel){
        
    }

    /**
     * 模块初始化失败
     * @param codModuleLauncherModel 启动引导对象
     * @param e 异常
     */
    default void fail(CodModuleLauncherModel codModuleLauncherModel, Throwable e){

    }

    /**
     * 模块初始化失败
     * @param e 异常
     */
    default void fail(Throwable e){

    }
}
