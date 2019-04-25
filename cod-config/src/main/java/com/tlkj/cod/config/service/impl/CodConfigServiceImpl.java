/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.config.service.impl;

import com.tlkj.cod.config.service.CodConfigService;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.model.common.SystemResponse;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Desc 配置服务
 *
 * @author sourcod
 * @version 1.0
 * @className CodConfigServiceImpl
 * @date 2019/4/9 5:02 PM
 */
@Service
public class CodConfigServiceImpl implements CodConfigService {

    private Finder finder;

    private Updater updater;

    @Autowired
    public CodConfigServiceImpl(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:h2:./codConfigDB;AUTO_SERVER=TRUE;DB_CLOSE_DELAY=-1;MODE=MySQL");
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("codConfig");
        dataSource.setPassword("123456");
        finder = new Finder(new JdbcTemplate(dataSource));
        updater = new Updater(new JdbcTemplate(dataSource));
    }

    @Override
    public SystemResponse init() {
        String table = "CREATE TABLE Persons\n" +
                "(\n" +
                "Id_P int,\n" +
                "LastName varchar(255),\n" +
                "FirstName varchar(255),\n" +
                "Address varchar(255),\n" +
                "City varchar(255)\n" +
                ")\n";
        updater.execute(table);
        return null;
    }

    @Override
    public SystemResponse load() {

        return null;
    }

    @Override
    public SystemResponse reload() {
        return null;
    }

    @Override
    public SystemResponse list() {
        return null;
    }
}
