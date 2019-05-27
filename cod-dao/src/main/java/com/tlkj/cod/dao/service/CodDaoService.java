package com.tlkj.cod.dao.service;

import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Desc Dao Service
 *
 * @author sourcod
 * @version 1.0
 * @className CodDaoService
 * @date 2019/5/17 2:14 PM
 */
public interface CodDaoService {

    /**
     * JdbcTemplate jdbcTemplate
     * @return
     */
    JdbcTemplate getJdbcTemplate();

    Finder getFinder();

    Updater getUpdater();




}
