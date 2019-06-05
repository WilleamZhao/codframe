package com.tlkj.cod.admin;

import com.tlkj.cod.admin.model.config.CodAdminDatabaseConfig;
import com.tlkj.cod.dao.bean.DataConnectBean;
import com.tlkj.cod.dao.model.enums.CodDaoDatasourceTypeEnum;
import com.tlkj.cod.dao.util.CodDaoConnectionPool;
import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;

/**
 * Desc 初始化管理模块
 *
 * @author sourcod
 * @version 1.0
 * @className InitAdmin
 * @date 2019/5/17 1:39 PM
 */
public class InitAdmin implements CodModuleInitialize {

    @Override
    public int order() {
        return 1;
    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {
        // codModuleLauncherModel.getSpring().refresh();

        DataConnectBean bean = new DataConnectBean();

        /*CodAdminDatabaseConfig databaseConfig = (CodAdminDatabaseConfig) codModuleLauncherModel.getSpring().getBean("codAdminDatabaseConfig");
        bean.setPassword(databaseConfig.getPassword());
        bean.setUsername(databaseConfig.getUsername());
        bean.setDriverClass(databaseConfig.getDriver());
        bean.setUrl(databaseConfig.getUrl());
*/
        CodDaoConnectionPool.getInstance().setDataSource(CodDaoDatasourceTypeEnum.DEFAULT.name(), bean);
        System.out.println("初始化成功");
        codModuleLauncherModel.finish();

    }

    @Override
    public void fail(Throwable e) {

    }
}
