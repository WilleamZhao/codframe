/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
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
     * 数据源类型
     * dbcp;
     * c3p0;
     * druid;
     * HikariCP;
     */
    @Value("${cod.database.source.type:}")
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
    @Value("${cod.database.initial.size:}")
    private String initialSize;

    /**
     * 最大连接数
     */
    @Value("${cod.database.active.max:}")
    private String maxActive = "10";

    /**
     * 最大空闲
     */
    @Value("${cod.database.idle.max:}")
    private String maxIdle = "20";

    /**
     * 最小空闲
     */
    @Value("${cod.database.idle.min:}")
    private String minIdle = "1";

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

    public static void main(String[] args) {

    }

}
