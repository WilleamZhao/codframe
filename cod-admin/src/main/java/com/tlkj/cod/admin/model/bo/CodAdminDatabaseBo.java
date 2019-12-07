/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.model.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * Desc 数据库管理 Bo
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminDatabaseBo
 * @date 2019/12/7 2:26 PM
 */
@Getter
@Setter
public class CodAdminDatabaseBo {


    /**
     * 数据源名称
     */
    private String name;

    /**
     * 数据源类型
     * dbcp;
     * c3p0;
     * druid;
     * HikariCP;
     */
    private String type;

    /**
     * 驱动类
     */
    private String driverClass;

    /**
     * URL
     */
    private String url;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 初始连接数
     */
    private int initialSize;

    /**
     * 最大连接数
     */
    private int maxActive;

    /**
     * 最大空闲
     */
    private int maxIdle;

    /**
     * 最小空闲
     */
    private int minIdle;

    /**
     * 最大等待时长
     */
    private String maxWait;

    /**
     * 是否自动编解码
     */
    private String useUnicode;

    /**
     * 编码方式
     */
    private String characterEncoding;

    /**
     * 初始化Sql
     */
    private String initSql;

    /**
     * 测试query
     */
    private String testQuery;

    /**
     * 是否默认提交
     */
    private boolean autoCommit;

}
