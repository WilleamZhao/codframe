/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.h2;

import com.tlkj.cod.dao.bean.DataConnectBean;
import com.tlkj.cod.dao.util.DBConnectionPool;
import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.model.LauncherModel;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Desc 初始化h2
 *
 * @author sourcod
 * @version 1.0
 * @className InitH2
 * @date 2019/4/25 1:44 PM
 */
public class InitH2 implements CodModuleInitialize {

    @Override
    public String name() {
        return "H2";
    }

    @Override
    public int order() {
        return CodModuleOrderEnum.H2.getOrder();
    }

    @Override
    public void init(LauncherModel launcherModel) {
        launcherModel.setH2(defaultDataSource());
    }

    @Override
    public void fail(Throwable e) {

    }

    /**
     * h2数据库连接
     * @return
     */
    private DataSource defaultDataSource(){
        DataConnectBean dataConnectBean = new DataConnectBean();
        dataConnectBean.setCharacterEncoding("utf-8");
        dataConnectBean.setDriverClass("org.h2.Driver");
        // dataConnectBean.setMaxActive();
        dataConnectBean.setUrl("jdbc:h2:./codConfigDB;AUTO_SERVER=TRUE;DB_CLOSE_DELAY=-1;MODE=MySQL");
        dataConnectBean.setUsername("codframe");
        dataConnectBean.setPassword("123456");
        DBConnectionPool dbConnectionPool = new DBConnectionPool();
        return dbConnectionPool.getHikariDataSource(dataConnectBean);
    }
}
