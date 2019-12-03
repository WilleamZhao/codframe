package com.tlkj.cod.filter;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;
import com.tlkj.cod.server.model.CodServerFilterModel;
import com.tlkj.cod.server.model.server.CodServerModel;

import javax.servlet.DispatcherType;

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
        return CodModuleOrderEnum.FILTER.getOrder();
    }

    @Override
    public String alias() {
        return "过滤器";
    }

    @Override
    public void success(CodModuleLauncherModel codModuleLauncherModel) {

    }

    @Override
    public void fail(CodModuleLauncherModel codModuleLauncherModel, Throwable e) {

    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {
        CodServerModel codServer = CodServerModel.getInstance();
        setCors(codServer);
        // setJwt(codServer);
        setParamConvert(codServer);
        setInitAdmin(codServer);
        setLicense(codServer);
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
        codServerFilterModel.setDispatcher(DispatcherType.REQUEST);
        codServer.addFilter(codServerFilterModel);
    }

    /**
     * 设置黑白名单
     * @param codServer
     */
    private void setAllowDisable(CodServerModel codServer){
        CodServerFilterModel codServerFilterModel = new CodServerFilterModel();
        codServerFilterModel.setMapping("/*");
        codServerFilterModel.setFilter(new CodFilterCORS());
        codServerFilterModel.setName("allowDisable");
        codServerFilterModel.setDispatcher(DispatcherType.REQUEST);
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
        codServerFilterModel.setName("token");
        codServerFilterModel.setDispatcher(DispatcherType.REQUEST);
        codServer.addFilter(codServerFilterModel);
    }

    /**
     * 设置参数转换
     */
    private void setParamConvert(CodServerModel codServer){
        CodServerFilterModel codServerFilterModel = new CodServerFilterModel();
        codServerFilterModel.setMapping("/*");
        codServerFilterModel.setFilter(new CodFilterRequestParamConvert());
        codServerFilterModel.setName("param");
        codServerFilterModel.setDispatcher(DispatcherType.REQUEST);
        codServer.addFilter(codServerFilterModel);
    }

    /**
     * 设置是否初始化过滤器
     */
    private void setInitAdmin(CodServerModel codServerModel){
        CodServerFilterModel codServerFilterModel = new CodServerFilterModel();
        codServerFilterModel.setMapping("/*");
        codServerFilterModel.setFilter(new CodFilterInitAdmin());
        codServerFilterModel.setName("initAdmin");
        codServerFilterModel.setDispatcher(DispatcherType.REQUEST);
        codServerModel.addFilter(codServerFilterModel);
    }

    /**
     * 设置license过滤器
     */
    private void setLicense(CodServerModel codServerModel){
        CodServerFilterModel codServerFilterModel = new CodServerFilterModel();
        codServerFilterModel.setMapping("/*");
        codServerFilterModel.setFilter(new CodFilterLicense());
        codServerFilterModel.setName("license");
        codServerFilterModel.setDispatcher(DispatcherType.REQUEST);
        codServerModel.addFilter(codServerFilterModel);
    }
}
