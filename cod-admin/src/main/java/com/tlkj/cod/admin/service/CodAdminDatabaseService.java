/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.service;

import javax.sql.DataSource;

/**
 * Desc 数据库管理
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminDatabaseService
 * @date 2019/12/7 2:23 PM
 */
public interface CodAdminDatabaseService {

    /**
     * 获取数据库信息
     * @return
     */
    DataSource getDatabaseInfo();

    /**
     * 根据数据库名 获取数据库信息
     * @param name
     * @return
     */
    DataSource getDatabaseInfo(String name);

}
