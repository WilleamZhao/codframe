package com.tlkj.cod.dao.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tlkj.cod.dao.bean.DataConnectBean;
import com.tlkj.cod.dao.exception.NoSupportDataSourceException;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 数据库连接池
 * Desc
 * 支持dbcp, c3p0, druid, HikariCP 数据库
 * 支持主备数据库, 多数据源
 *
 * @author sourcod
 * @version 1.0
 * @className DbConnectionPool
 * @date 2018/8/8 下午1:08
 */
@Component("codDataSource")
public class DBConnectionPool {

    private DBConnectionPool dbConnectionPool;

    private static DataConnectBean dataConnectBean = new DataConnectBean();
    private static DataSource dataSource;

    private static Properties prop = new Properties();
    private static Yaml yaml = new Yaml();
    static private Map map = new HashMap();



    /**
     * 数据库序号 从1开始
     */
    private static int dataSourceNo = 1;

    /**
     * 一共多少个数据库
     */
    private static String number = "";

    private static String type = "";

    /*
    static {
        InputStream in;
        try {
            System.out.println("开始加载数据库配置文件！");
            // 获取config配置文件夹路径
            in = new FileInputStream("conf/db.yml");
            if (in.available() != 0) {
                map = (Map) yaml.load(in);
                System.out.println("加载数据库配置文件db.yml成功！");
            }
        } catch (IOException e) {
            try {
                in = DBConnectionPool.class.getResource("/conf/db.yml").openStream();
                if (in.available() != 0) {
                    map = (Map) yaml.load(in);
                    System.out.println("加载数据库配置文件db.yml成功！");
                } else {
                    System.out.println("读取配置文件错误" + e.getMessage());
                }
            } catch (IOException e1) {
                System.out.println("读取配置文件错误" + e1.getMessage());
            }
        }
    }
    */

    /**
     * 默认构造器
     * 读取配置文件设置数据源类型
     * 默认使用dbcp数据源
     */
    public DBConnectionPool() {
        number = getValueByKey("datasource.number");
        type = getValueByKey("datasource.type");
        if (StringUtils.isEmpty(dataConnectBean.getDriverClass())){
            setDataConnectBean();
        }
        if (dataSource == null){
            setDataSource();
        }
    }


    /**
     * 设置数据库源
     */
    private void setDataSource(){
        if (StringUtils.isEmpty(type)){
            type = "dbcp";
        }
        try {
            switch (type){
                case ("dbcp"):
                    getBasicDataSource();
                    break;
                case ("HikariCP"):
                    dataSource = getHikariDataSource(dataConnectBean);
                    break;
                case ("c3p0"):
                    getComboPooledDataSource();
                    break;
                case ("druid"):
                    getDruidDataSource();
                    break;
                default:
                    throw new NoSupportDataSourceException();
            }
        } catch (NoSupportDataSourceException e){
            System.out.println("数据源" + type + "数据库" + dataSourceNo + "连接失败" + e.getMessage());
            throw new NoSupportDataSourceException("数据源" + type + "数据库" + dataSourceNo + "连接失败" + e.getMessage());
        }
    }

    /**
     * 获取datasource
     * @return datasource
     */
    public final synchronized DataSource getDataSource() {
        try {
            if (dataSource.getConnection() == null){
                if (dataSourceNo < Integer.parseInt(number)){
                    dataSourceNo++;
                    setDataSource();
                    return this.getDataSource();
                }
            }
        } catch (SQLException e) {
            if (!(dataSourceNo < Integer.parseInt(number))){
                System.err.println("所有数据库连接全部连不上");
            } else {
                return this.getDataSource();
            }
        }
        return dataSource;
    }

    /**
     * Hikari 数据源
     * @return Hikari 数据源
     */
    public DataSource getHikariDataSource(DataConnectBean dataConnectBean){
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(dataConnectBean.getDriverClass());
        hikariDataSource.setUsername(dataConnectBean.getUsername());
        hikariDataSource.setPassword(dataConnectBean.getPassword());
        hikariDataSource.setJdbcUrl(dataConnectBean.getUrl());
        hikariDataSource.setMaximumPoolSize(Integer.parseInt(dataConnectBean.getMaxActive()));
        hikariDataSource.setMinimumIdle(Integer.parseInt(dataConnectBean.getMinIdle()));
        hikariDataSource.setConnectionTestQuery(dataConnectBean.getTestQuery());
        hikariDataSource.setAutoCommit(true);
        // 设置编码
        if (StringUtils.isNotBlank(dataConnectBean.getUseUnicode())){
            hikariDataSource.addDataSourceProperty("useUnicode", dataConnectBean.getUseUnicode());
        }
        if (StringUtils.isNotBlank(dataConnectBean.getCharacterEncoding())){
            hikariDataSource.addDataSourceProperty("characterEncoding", dataConnectBean.getCharacterEncoding());
        }
        // 初始化Sql
        if (StringUtils.isNotBlank(dataConnectBean.getInitSql())){
            hikariDataSource.setConnectionInitSql(dataConnectBean.getInitialSize());
        }
        // dataSource = hikariDataSource;
        return hikariDataSource;
    }

    /**
     * dbcp 数据源
     * @return dbcp数据源
     */
    private void getBasicDataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(dataConnectBean.getDriverClass());
        basicDataSource.setDriverClassName(dataConnectBean.getDriverClass());
        basicDataSource.setUsername(dataConnectBean.getUsername());
        basicDataSource.setPassword(dataConnectBean.getPassword());
        basicDataSource.setUrl(dataConnectBean.getUrl());

        // 设置初始化Sql, ";"分割
        if (StringUtils.isNotBlank(dataConnectBean.getInitSql())){
            String[] initSqls = dataConnectBean.getInitSql().split(";");
            List<String> list = new ArrayList<>(Arrays.asList(initSqls));
            basicDataSource.setConnectionInitSqls(list);
        }
        dataSource = basicDataSource;
    }

    /**
     * c3p0 数据源
     * @return c3p0数据源
     */
    private void getComboPooledDataSource(){
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        try {
            comboPooledDataSource.setDriverClass(dataConnectBean.getDriverClass());
        } catch (PropertyVetoException e) {
            System.out.println("找不到驱动异常 " + e.getMessage());
        }
        comboPooledDataSource.setUser(dataConnectBean.getUsername());
        comboPooledDataSource.setPassword(dataConnectBean.getPassword());
        comboPooledDataSource.setJdbcUrl(dataConnectBean.getUrl());
        comboPooledDataSource.setInitialPoolSize(Integer.parseInt(dataConnectBean.getInitialSize()));
        comboPooledDataSource.setMaxPoolSize(Integer.parseInt(dataConnectBean.getMaxActive()));
        comboPooledDataSource.setMaxIdleTime(Integer.parseInt(dataConnectBean.getMaxIdle()));
        dataSource = comboPooledDataSource;
    }

    /**
     * druid 数据源
     * @return druid 数据源
     */
    private void getDruidDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(dataConnectBean.getDriverClass());
        druidDataSource.setUsername(dataConnectBean.getUsername());
        druidDataSource.setPassword(dataConnectBean.getPassword());
        druidDataSource.setUrl(dataConnectBean.getUrl());
        druidDataSource.setInitialSize(Integer.parseInt(dataConnectBean.getInitialSize()));
        druidDataSource.setMaxActive(Integer.parseInt(dataConnectBean.getMaxActive()));
        druidDataSource.setMinIdle(Integer.parseInt(dataConnectBean.getMinIdle()));
        druidDataSource.setValidationQuery(dataConnectBean.getTestQuery());
        druidDataSource.setMaxWait(Integer.parseInt(dataConnectBean.getMaxWait()));
        // 设置初始化Sql, ";"分割
        if (StringUtils.isNotBlank(dataConnectBean.getInitSql())){
            String[] initSqls = dataConnectBean.getInitSql().split(";");
            List<String> list = new ArrayList<>(Arrays.asList(initSqls));
            druidDataSource.setConnectionInitSqls(list);
        }
        dataSource = druidDataSource;
    }

    /**
     * 设置DataConnectBean数据库连接类
     */
    private static void setDataConnectBean(){
        dataConnectBean.setDriverClass(getValueByKey("datasource" + dataSourceNo + ".driverClass"));
        dataConnectBean.setUrl(getValueByKey("datasource" + dataSourceNo + ".url"));
        dataConnectBean.setUsername(getValueByKey("datasource" + dataSourceNo + ".username"));
        dataConnectBean.setPassword(getValueByKey("datasource" + dataSourceNo + ".password"));
        dataConnectBean.setInitialSize(getValueByKey("datasource" + dataSourceNo + ".initialSize"));
        dataConnectBean.setMaxActive(getValueByKey("datasource" + dataSourceNo + ".maxActive"));
        dataConnectBean.setMaxIdle(getValueByKey("datasource" + dataSourceNo + ".maxIdle"));
        dataConnectBean.setMinIdle(getValueByKey("datasource" + dataSourceNo + ".minIdle"));
        dataConnectBean.setMaxWait(getValueByKey("datasource" + dataSourceNo + ".maxWait"));
        dataConnectBean.setTestQuery(getValueByKey("datasource" + dataSourceNo + ".testQuery"));
        dataConnectBean.setUseUnicode(getValueByKey("datasource" + dataSourceNo + ".useUnicode"));
        dataConnectBean.setCharacterEncoding(getValueByKey("datasource" + dataSourceNo + ".characterEncoding"));
        dataConnectBean.setInitSql(getValueByKey("datasource" + dataSourceNo + ".initSql"));
    }

    /**
     * 获取数据库配置
     * @param key
     * @return
     */
    public static String getValueByKey(String key) {
        if (StringUtils.isEmpty(key)){
            return null;
        }
        String propKey = prop.getProperty(key);
        if (StringUtils.isNotEmpty(propKey)){
            return propKey;
        }
        String[] strings = key.split("\\.");
        Map tempMap = map;
        for (int i = 0; i < strings.length; i++){
            if (i < strings.length - 1){
                tempMap = (Map) tempMap.get(strings[i]);
            } else {
                propKey = tempMap.get(strings[i]) != null ? tempMap.get(strings[i]).toString() : "";
            }
        }
        return propKey;
    }

}
