/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.dao.service.impl;

import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.dao.service.CodDaoService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Desc cod dao service
 *
 * @author sourcod
 * @version 1.0
 * @className CodDaoServiceImpl
 * @date 2019/10/21 1:26 PM
 */
@Service
public class CodDaoServiceImpl implements CodDaoService {

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return null;
    }

    @Override
    public Finder getFinder() {
        return null;
    }

    @Override
    public Updater getUpdater() {
        return null;
    }
}
