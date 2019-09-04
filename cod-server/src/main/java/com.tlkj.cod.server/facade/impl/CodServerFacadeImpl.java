package com.tlkj.cod.server.facade.impl;

import com.tlkj.cod.launcher.model.CodModuleLauncherModel;
import com.tlkj.cod.server.facade.CodServerFacade;
import com.tlkj.cod.server.model.config.CodServerServiceConfig;
import com.tlkj.cod.server.model.server.CodServerJettyModel;
import com.tlkj.cod.server.model.server.CodServerModel;
import com.tlkj.cod.server.model.server.CodServerResinModel;
import com.tlkj.cod.server.model.server.CodServerTomcatModel;
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
@Service("codServerFacadeImpl")
public class CodServerFacadeImpl implements CodServerFacade {

    @Autowired
    List<CodServerService> codServerServiceList;

    private CodServerService codServerService;

    @Autowired
    CodServerServiceConfig codServerServiceConfig;

    @Override
    public void start(CodModuleLauncherModel codModuleLauncherModel) {
        CodServerModel codServerModel;
        switch (codServerServiceConfig.getType()){
            case "codServerJetty":
                codServerModel = CodServerJettyModel.getInstance();
                codServerModel.setServer("codServerJetty");
                break;
            case "codServerTomcat":
                codServerModel = CodServerTomcatModel.getInstance();
                codServerModel.setServer("codServerTomcat");
                break;
            case "codServerResin":
                codServerModel = CodServerResinModel.getInstance();
                codServerModel.setServer("codServerResin");
                break;
            default:
                codServerModel = CodServerJettyModel.getInstance();
                codServerModel.setServer("codServerJetty");
                break;
        }
        codServerModel.setEncode(codServerServiceConfig.getEncode());
        codServerModel.setEnv(codServerServiceConfig.getEnv());
        codServerModel.setProjectName(codServerServiceConfig.getProjectName());
        codServerModel.setPort(Integer.parseInt(codServerServiceConfig.getPort()));
        for (CodServerService codServerService : codServerServiceList){
            if(codServerService.support().equals(codServerServiceConfig.getType())){
                this.codServerService = codServerService;
                codServerService.start(codModuleLauncherModel);
            }
        }
    }

    @Override
    public void stop() {
        this.codServerService.stop();
    }

    @Override
    public void restart() {
        this.codServerService.restart();
    }
}
