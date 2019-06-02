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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Desc 数据库连接配置
 * @ClassName: DataConnectBean
 * @Description: TODO
 * @Author yjk
 * @Date 2018/7/26 下午11:07
 */
@Component("dataSource")
public class DataConnectBean {

    @Value("${cod.database.type}")
    private String datasourcd;

    /**
     * 驱动类
     */
    @Value("${cod.database.driverClass}")
    private String driverClass;

    /**
     * URL
     */
    @Value("${cod.database.url}")
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
    private String initialSize;

    /**
     * 最大连接数
     */
    private String maxActive = "10";

    /**
     * 最大空闲
     */
    private String maxIdle = "20";

    /**
     * 最小空闲
     */
    private String minIdle = "1";

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


    public static void main(String[] args) {

    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(String initialSize) {
        this.initialSize = initialSize;
    }

    public String getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(String maxActive) {
        this.maxActive = maxActive;
    }

    public String getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(String maxIdle) {
        this.maxIdle = maxIdle;
    }

    public String getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(String minIdle) {
        this.minIdle = minIdle;
    }

    public String getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(String maxWait) {
        this.maxWait = maxWait;
    }

    public String getTestQuery() {
        return testQuery;
    }

    public void setTestQuery(String testQuery) {
        this.testQuery = testQuery;
    }

    public String getDatasourcd() {
        return datasourcd;
    }

    public void setDatasourcd(String datasourcd) {
        this.datasourcd = datasourcd;
    }

    public String getInitSql() {
        return initSql;
    }

    public void setInitSql(String initSql) {
        this.initSql = initSql;
    }

    public String getUseUnicode() {
        return useUnicode;
    }

    public void setUseUnicode(String useUnicode) {
        this.useUnicode = useUnicode;
    }

    public String getCharacterEncoding() {
        return characterEncoding;
    }

    public void setCharacterEncoding(String characterEncoding) {
        this.characterEncoding = characterEncoding;
    }
}
