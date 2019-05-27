package com.tlkj.cod.dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Desc 父Dao
 *
 * @author sourcod
 * @version 1.0
 * @className CodDaoer
 * @date 2019/5/17 2:52 PM
 */
public class CodDaoer {


    public CodDaoer(){

    }

    public CodDaoer(JdbcTemplate jdbcTemplate) {

    }

    public Finder getFinder(){
        return this.getFinder();
    }

    public Updater getUpdater(){
        return new Updater();
    }
}
