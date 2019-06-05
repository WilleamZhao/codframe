package com.tlkj.cod.filter;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;
import com.tlkj.cod.server.model.CodServerFilterModel;
import com.tlkj.cod.server.model.server.CodServerModel;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className InitFilter
 * @date 2019/6/5 2:24 PM
 */
public class InitFilter implements CodModuleInitialize {
    @Override
    public int order() {
        return 0;
    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {
        CodServerModel codServer = CodServerModel.getInstance();
        setCors(codServer);
        setJwt(codServer);
        setParamConvert(codServer);
    }

    @Override
    public void fail(Throwable e) {

    }

    /**
     * 设置cors
     * @param codServer
     */
    private void setCors(CodServerModel codServer){
        CodServerFilterModel codServerFilterModel = new CodServerFilterModel();
        codServerFilterModel.setMapping("/*");
        codServerFilterModel.setFilter(new CodFilterCORS());
        codServerFilterModel.setName("cors");
        codServer.addFilter(codServerFilterModel);
    }

    /**
     * 设置cors
     * @param codServer
     */
    private void setAllowDisable(CodServerModel codServer){
        CodServerFilterModel codServerFilterModel = new CodServerFilterModel();
        codServerFilterModel.setMapping("/*");
        codServerFilterModel.setFilter(new CodFilterCORS());
        codServerFilterModel.setName("cors");
        codServer.addFilter(codServerFilterModel);
    }

    /**
     * 设置JWT
     * @param codServer
     */
    private void setJwt(CodServerModel codServer){
        CodServerFilterModel codServerFilterModel = new CodServerFilterModel();
        codServerFilterModel.setMapping("/*");
        codServerFilterModel.setFilter(new CodFilterJWT());
        codServerFilterModel.setName("cors");
        codServer.addFilter(codServerFilterModel);
    }

    /**
     * 设置参数转换
     */
    private void setParamConvert(CodServerModel codServer){
        CodServerFilterModel codServerFilterModel = new CodServerFilterModel();
        codServerFilterModel.setMapping("/*");
        codServerFilterModel.setFilter(new CodFilterJWT());
        codServerFilterModel.setName("cors");
        codServer.addFilter(codServerFilterModel);
    }
}
