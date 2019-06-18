package com.tlkj.cod.server.facade.impl;

import com.tlkj.cod.launcher.model.CodModuleLauncherModel;
import com.tlkj.cod.server.facade.CodServerFacade;
import com.tlkj.cod.server.service.CodServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desc cod-server facade impl
 *
 * @author sourcod
 * @version 1.0
 * @className CodServerFacadeImpl
 * @date 2019/6/18 11:10 AM
 */
@Service
public class CodServerFacadeImpl implements CodServerFacade {

    @Autowired
    List<CodServerService> codServerServiceList;

    @Override
    public void start(CodModuleLauncherModel codModuleLauncherModel) {

    }

    @Override
    public void stop() {

    }

    @Override
    public void restart() {

    }
}
