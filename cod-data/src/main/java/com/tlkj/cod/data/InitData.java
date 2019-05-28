package com.tlkj.cod.data;

import com.tlkj.cod.dao.bean.CodDaoBean;
import com.tlkj.cod.dao.bean.DataConnectBean;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.dao.util.DBConnectionPool;
import com.tlkj.cod.data.service.CodDataService;
import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.model.LauncherModel;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Desc 初始化数据
 *
 * @author sourcod
 * @version 1.0
 * @className InitData
 * @date 2019/5/28 11:06 AM
 */
public class InitData implements CodModuleInitialize {

    @Override
    public String name() {
        return null;
    }

    @Override
    public int order() {
        return CodModuleOrderEnum.DATA.getOrder();
    }

    @Override
    public void init(LauncherModel launcherModel) {
        CodDataService codDataService = new CodDataService();
        DataConnectBean dataConnectBean = new DataConnectBean();
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
        codDaoBean.setUpdater(updater);
        // launcherModel.setCodData();
    }

    @Override
    public void fail(Throwable e) {

    }


}
