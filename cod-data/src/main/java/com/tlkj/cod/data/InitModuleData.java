package com.tlkj.cod.data;

import com.tlkj.cod.dao.bean.DataConnectBean;
import com.tlkj.cod.dao.model.enums.CodDaoDatasourceTypeEnum;
import com.tlkj.cod.dao.util.DBConnectionPool;
import com.tlkj.cod.data.model.config.CodDataConfig;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.init.CodModuleDataInitialize;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;

/**
 * Desc 初始化数据
 *
 * @author sourcod
 * @version 1.0
 * @className InitModuleData
 * @date 2019/5/28 11:06 AM
 */
public class InitModuleData implements CodModuleDataInitialize {

    @Override
    public int order() {
        return CodModuleOrderEnum.DATA.getOrder();
    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {
        // 设置 CodData 数据库连接信息 H2
        DataConnectBean bean = new DataConnectBean();
        CodDataConfig codDataConfig = new CodDataConfig();
        bean.setPassword(codDataConfig.getPassword());
        bean.setUsername(codDataConfig.getUsername());
        bean.setDriverClass(codDataConfig.getDriver());
        bean.setUrl(codDataConfig.getUrl());
        bean.setCharacterEncoding(codDataConfig.getEncoding());
        DBConnectionPool.getInstance().setDataSource(CodDaoDatasourceTypeEnum.DATA.name(), bean);

        // new 配置
        // CodDataService codDataService = new CodDataServiceImpl();

        // 执行初始化方法
        // codDataService.init();

        /*DataConnectBean dataConnectBean = new DataConnectBean();
        dataConnectBean.setCharacterEncoding("utf-8");
        dataConnectBean.setDriverClass("org.h2.Driver");
        // dataConnectBean.setMaxActive();
        dataConnectBean.setUrl("jdbc:h2:./codConfigDB;AUTO_SERVER=TRUE;DB_CLOSE_DELAY=-1;MODE=MySQL");
        dataConnectBean.setUsername("codframe");
        dataConnectBean.setPassword("123456");
        DBConnectionPool dbConnectionPool = new DBConnectionPool();
        DataSource dataSource = dbConnectionPool.getHikariDataSource(dataConnectBean);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Finder finder = new Finder(jdbcTemplate);
        Updater updater = new Updater(jdbcTemplate);
        CodDaoBean codDaoBean = new CodDaoBean();
        codDaoBean.setFinder(finder);
        codDaoBean.setUpdater(updater);*/
        // codModuleLauncherModel.setCodData();
    }

    @Override
    public void fail(Throwable e) {

    }


}
