package com.tlkj.cod.server.facade.impl;

import com.tlkj.cod.launcher.model.CodModuleLauncherModel;
import com.tlkj.cod.server.facade.CodServerFacade;
import com.tlkj.cod.server.model.config.CodServerConfig;
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

    private List<CodServerService> codServerServiceList;

    private CodServerService codServerService;

    private CodServerConfig codServerConfig;

    public CodServerFacadeImpl(){

    }

    @Autowired
    public CodServerFacadeImpl(List<CodServerService> codServerServices, CodServerConfig codServerConfig){
        this.codServerServiceList = codServerServices;
        this.codServerConfig = codServerConfig;
        this.codServerService = getCodServerService();
    }

    @Override
    public void start(CodModuleLauncherModel codModuleLauncherModel) {
        codServerService.start(codModuleLauncherModel, codServerConfig);
    }

    @Override
    public void stop() {
        this.codServerService.stop();
    }

    @Override
    public void restart() {
        this.codServerService.restart();
    }

    private CodServerService getCodServerService(){
        for (CodServerService codServerService : codServerServiceList){
            if(codServerService.support().equals(codServerConfig.getType())){
                this.codServerService = codServerService;
                return codServerService;
            }
        }
        return null;
    }
}
