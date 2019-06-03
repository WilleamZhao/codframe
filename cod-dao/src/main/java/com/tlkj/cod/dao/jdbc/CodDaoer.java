package com.tlkj.cod.dao.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Desc 父Dao
 *
 * @author sourcod
 * @version 1.0
 * @className CodDaoer
 * @date 2019/5/17 2:52 PM
 */
@Repository
public class CodDaoer {

    private JdbcTemplate jdbcTemplate = null;

    public CodDaoer(){

    }

    public CodDaoer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 获取finder
     * @return
     */
    public Finder getFinder(){
        return new Finder(jdbcTemplate);
    }

    /**
     * 获取updater
     * @return
     */
    public Updater getUpdater(){
        return new Updater(jdbcTemplate);
    }
}
