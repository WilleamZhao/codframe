/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.jboss.C3P0PooledDataSource;
import com.tlkj.cod.admin.model.bo.CodAdminDatabaseBo;
import com.tlkj.cod.admin.service.CodAdminDatabaseService;
import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.model.enums.CodDaoDatasourceTypeEnum;
import com.tlkj.cod.dao.util.CodDaoConnectionPool;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Desc 数据库管理service
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminDatabaseServerImpl
 * @date 2019/12/7 2:24 PM
 */
@Service
public class CodAdminDatabaseServiceImpl implements CodAdminDatabaseService {

    private Finder finder;

    @Autowired
    public CodAdminDatabaseServiceImpl(Finder finder){
        // DataSource dataSource = CodDaoConnectionPool.getInstance().getDataSource(CodDaoDatasourceTypeEnum.DEFAULT.name());
        this.finder = finder;
    }

    /**
     * 获取数据库信息
     * @return
     */
    @Override
    public DataSource getDatabaseInfo(){
        return getDatabaseInfo(CodDaoDatasourceTypeEnum.DEFAULT.name());
    }

    /**
     * 根据名称 获取数据库信息
     * @param name
     * @return
     */
    @Override
    public DataSource getDatabaseInfo(String name) {
        if (StringUtils.isBlank(name)){
            name = CodDaoDatasourceTypeEnum.DEFAULT.name();
        }

        DataSource dataSource = CodDaoConnectionPool.getInstance().getDataSource(name);
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            if (dataSource instanceof BasicDataSource){
                BasicDataSource basicDataSource = (BasicDataSource) dataSource;
                basicDataSource.getDriverClassName();
                basicDataSource.getPassword();
                basicDataSource.getUsername();
                basicDataSource.getUrl();
                basicDataSource.getInitialSize();
                basicDataSource.getMaxActive();
                basicDataSource.getMaxIdle();
                basicDataSource.getMaxWait();
                basicDataSource.getMinIdle();
                basicDataSource.getDefaultAutoCommit();
            }

            if (dataSource instanceof ComboPooledDataSource){
                ComboPooledDataSource comboPooledDataSource = (ComboPooledDataSource) dataSource;
                comboPooledDataSource.getDriverClass();
                comboPooledDataSource.getPassword();
                comboPooledDataSource.getUser();
                comboPooledDataSource.getJdbcUrl();
                comboPooledDataSource.getInitialPoolSize();
                comboPooledDataSource.getMaxConnectionAge();
                comboPooledDataSource.getMaxIdleTime();

            }

            if (dataSource instanceof DruidDataSource){
                DruidDataSource druidDataSource = (DruidDataSource) dataSource;
                druidDataSource.getDriverClassName();
                druidDataSource.getPassword();
                druidDataSource.getUsername();
                druidDataSource.getUrl();
                druidDataSource.getInitialSize();
                druidDataSource.getMaxActive();
                druidDataSource.getMaxIdle();
                druidDataSource.getMaxWait();
                druidDataSource.getMinIdle();
            }

            if (dataSource instanceof HikariDataSource){
                HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
                hikariDataSource.getDriverClassName();
                hikariDataSource.getPassword();
                hikariDataSource.getUsername();
                hikariDataSource.getJdbcUrl();
                hikariDataSource.getMaximumPoolSize();
                hikariDataSource.getMaximumPoolSize();
                hikariDataSource.getMinimumIdle();
            }

            boolean autoCommit = connection.getAutoCommit();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            Properties properties = connection.getClientInfo();
            System.out.println("----------------------");
            System.out.println(CodCommonJson.dump(properties));

            int a = databaseMetaData.getDriverMajorVersion();
            int b = databaseMetaData.getDriverMinorVersion();
            String c = databaseMetaData.getDriverName();
            String d = databaseMetaData.getDriverVersion();
            int e = databaseMetaData.getDatabaseMajorVersion();
            int f = databaseMetaData.getDatabaseMinorVersion();
            String g = databaseMetaData.getDatabaseProductName();
            String h = databaseMetaData.getDatabaseProductVersion();
            int j = databaseMetaData.getMaxConnections();
            String k = databaseMetaData.getURL();
            String l = databaseMetaData.getUserName();
            System.out.println(a);
            System.out.println(b);
            System.out.println(c);
            System.out.println(d);
            System.out.println(e);
            System.out.println(f);
            System.out.println(g);
            System.out.println(h);
            System.out.println(j);
            System.out.println(k);
            System.out.println(l);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;

    }

}
