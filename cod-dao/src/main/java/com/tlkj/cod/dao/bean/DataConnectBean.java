/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.dao.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Desc 数据库连接配置
 * @ClassName: DataConnectBean
 * @Description: TODO
 * @Author yjk
 * @Date 2018/7/26 下午11:07
 */
@Getter
@Setter
@Component("dataSource")
public class DataConnectBean {

    /**
     * 数据源名称
     */
    @Value("${cod.database.source.name:}")
    private String name;

    /**
     * 数据源类型
     * dbcp;
     * c3p0;
     * druid;
     * HikariCP;
     */
    @Value("${cod.database.source.type:HikariCP}")
    private String type;

    /**
     * 驱动类
     */
    @Value("${cod.database.driverClass:com.mysql.jdbc.Driver}")
    private String driverClass;

    /**
     * URL
     */
    @Value("${cod.database.url:}")
    private String url;

    /**
     * 用户名
     */
    @Value("${cod.database.username:}")
    private String username;

    /**
     * 密码
     */
    @Value("${cod.database.password:}")
    private String password;

    /**
     * 初始连接数
     */
    @Value("${cod.database.initial.size:10}")
    private int initialSize;

    /**
     * 最大连接数
     */
    @Value("${cod.database.active.max:1000}")
    private int maxActive;

    /**
     * 最大空闲
     */
    @Value("${cod.database.idle.max:20}")
    private int maxIdle;

    /**
     * 最小空闲
     */
    @Value("${cod.database.idle.min:1}")
    private int minIdle;

    /**
     * 最大等待时长
     */
    @Value("${cod.database.max.wait:}")
    private String maxWait;

    /**
     * 是否自动编解码
     */
    @Value("${cod.database.unicode:}")
    private String useUnicode;

    /**
     * 编码方式
     */
    @Value("${cod.database.encode:}")
    private String characterEncoding;

    /**
     * 初始化Sql
     */
    @Value("${cod.database.init.sql:}")
    private String initSql;

    /**
     * 测试query
     */
    @Value("${cod.database.test:}")
    private String testQuery;

    /**
     * 是否默认提交
     */
    // @Value("${cod.database.autoCommit:false}")
    private boolean autoCommit = false;

    public static void main(String[] args) {

    }

}
