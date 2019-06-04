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
import com.tlkj.cod.h2.model.CodH2Config;
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
    public int order() {
        return CodModuleOrderEnum.DATA.getOrder();
    }

    @Override
    public void init(LauncherModel launcherModel) {
        launcherModel.setCodData(defaultDataSource());
    }

    @Override
    public void fail(Throwable e) {

    }

    /**
     * h2数据库连接
     * @return
     */
    private DataSource defaultDataSource(){
        CodH2Config codH2Config = new CodH2Config();
        DataConnectBean dataConnectBean = new DataConnectBean();
        dataConnectBean.setCharacterEncoding(codH2Config.getEncoding());
        dataConnectBean.setDriverClass(codH2Config.getDriver());
        dataConnectBean.setUrl(codH2Config.getUrl());
        dataConnectBean.setUsername(codH2Config.getUsername());
        dataConnectBean.setPassword(codH2Config.getPassword());
        DBConnectionPool dbConnectionPool = new DBConnectionPool();
        return dbConnectionPool.getHikariDataSource(dataConnectBean);
    }
}
