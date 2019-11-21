/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.server.service.impl;


import com.tlkj.cod.launcher.model.CodModuleLauncherModel;
import com.tlkj.cod.server.model.config.CodServerConfig;
import com.tlkj.cod.server.service.CodServerService;
import org.springframework.stereotype.Component;

/**
 * Desc cod启动resinService
 *
 * @author sourcod
 * @version 1.0
 * @className CodServerServiceResinImpl
 * @date 2019/4/10 3:41 PM
 */
@Component("codServerResin")
public class CodServerServiceResinImpl implements CodServerService {

    @Override
    public String support() {
        return "codServerResin";
    }

    @Override
    public void start(CodModuleLauncherModel codModuleLauncherModel, CodServerConfig codServerConfig) {

    }

    @Override
    public void stop() {

    }

    @Override
    public void restart() {

    }
}
