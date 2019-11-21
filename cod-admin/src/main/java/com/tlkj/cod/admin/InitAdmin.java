package com.tlkj.cod.admin;

import com.tlkj.cod.admin.model.config.CodAdminDatabaseConfig;
import com.tlkj.cod.dao.bean.DataConnectBean;
import com.tlkj.cod.dao.model.enums.CodDaoDatasourceTypeEnum;
import com.tlkj.cod.dao.util.CodDaoConnectionPool;
import com.tlkj.cod.data.service.CodDataService;
import com.tlkj.cod.data.service.impl.CodDataServiceImpl;
import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;
import com.tlkj.cod.launcher.model.CodModuleServerModel;

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
        return CodModuleOrderEnum.ADMIN.getOrder();
    }

    @Override
    public String alias() {
        return "管理";
    }

    @Override
    public void success(CodModuleLauncherModel codModuleLauncherModel) {
        CodAdminDatabaseConfig codAdminDatabaseConfig = (CodAdminDatabaseConfig) codModuleLauncherModel.getSpring().getBean("codAdminDatabaseConfig");
        DataConnectBean bean = (DataConnectBean) codModuleLauncherModel.getSpring().getBean("dataSource");
        bean.setPassword(codAdminDatabaseConfig.getPassword());
        bean.setUsername(codAdminDatabaseConfig.getUsername());
        bean.setDriverClass(codAdminDatabaseConfig.getDriver());
        bean.setUrl(codAdminDatabaseConfig.getUrl());
        bean.setAutoCommit(true);
        CodDaoConnectionPool.getInstance().setDataSource(CodDaoDatasourceTypeEnum.DEFAULT.name(), bean);
    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {
        // DataConnectBean bean = new DataConnectBean();
        CodDataService codDataService = codModuleLauncherModel.getBean(CodDataServiceImpl.class);
        codDataService.setData("cod.admin.database.config.driver", "com.mysql.jdbc.Driver");
        codDataService.setData("cod.admin.database.config.url", "jdbc:mysql://sourcoddatabase.mysql.rds.aliyuncs.com/mczoo_admin?characterEncoding=UTF-8");
        codDataService.setData("cod.admin.database.config.username", "sourcod");
        codDataService.setData("cod.admin.database.config.password", "sourcodp@ssw0rd");
        /*CodAdminDatabaseConfig databaseConfig = (CodAdminDatabaseConfig) codModuleLauncherModel.getSpring().getBean("CodAdminDatabaseConfig");
        bean.setPassword(databaseConfig.getPassword());
        bean.setUsername(databaseConfig.getUsername());
        bean.setDriverClass(databaseConfig.getDriver());
        bean.setUrl(databaseConfig.getUrl());

        DataSource dataSource = CodDaoConnectionPool.getInstance().getDataSource(CodDaoDatasourceTypeEnum.DATA.name());
        Finder finder = new Finder(dataSource);
        finder.from();

        CodDaoConnectionPool.getInstance().setDataSource(CodDaoDatasourceTypeEnum.DEFAULT.name(), bean);*/
        codModuleLauncherModel.finish();

    }

    @Override
    public void fail(Throwable e) {

    }
}
