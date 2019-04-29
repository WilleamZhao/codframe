/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.server.service;

import com.tlkj.cod.launcher.model.LauncherModel;

/**
 * Desc 抽象web服务
 *
 * @author sourcod
 * @version 1.0
 * @className AbstractCodWebServer
 * @date 2019/3/28 5:20 PM
 */
public interface CodServer {

    /**
     * 启动
     */
    void start(LauncherModel launcherModel);

    /**
     * 停止
     */
    void stop();

    /**
     * 重启
     */
    void restart();

}
