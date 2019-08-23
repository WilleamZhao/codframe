package com.tlkj.cod.dao.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tlkj.cod.common.CodCommonUUID;
import com.tlkj.cod.dao.bean.DataConnectBean;
import com.tlkj.cod.dao.exception.NoSupportDataSourceException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
public class CodDaoConnectionPool {

    private DataSource dataSource;

    private static volatile CodDaoConnectionPool instance;

    /**
     * 线程安全
     */
    public static CodDaoConnectionPool getInstance() {
        if (instance == null) {
            synchronized (CodDaoConnectionPool.class) {
                if (instance == null) {
                    instance = new CodDaoConnectionPool();
                }
            }
        }
        return instance;
    }

    /**
     * 连接缓存
     * 线程安全
     */
    private Map<String, LinkedList<DataSource>> map = new ConcurrentHashMap<>();

    /**
     * 第几个数据源
     * 序号
     */
    private Map<String, Integer> mapNo = new ConcurrentHashMap<>();

    /**
     * 数据库序号 从1开始
     */
    private static int dataSourceNo = 1;

    /**
     * 一共多少个数据库
     */
    private static String number = "";

    private static String type = "";

    /**
     * 默认构造方法
     */
    public CodDaoConnectionPool(){

    }

    /**
     * 默认构造器
     * 读取配置文件设置数据源类型
     * 默认使用dbcp数据源
     */
    public CodDaoConnectionPool(DataConnectBean dataConnectBean) {
        this.dataSource = setDataSource(dataConnectBean);
        setDataSource(dataConnectBean.getName(), this.dataSource);
        /*
        number = getValueByKey("datasource.number");
        type = getValueByKey("datasource.type");
        if (StringUtils.isEmpty(dataConnectBean.getDriverClass())){
            setDataConnectBean();
        }
        if (dataSource == null){
            setDataSource();
        }
        */
    }

    /**
     * 获取数据源
     * @param name 数据源名称
     * @return datasource
     */
    public DataSource getDataSource(String name){
        return CodDaoConnectionPool.getInstance().getDataSource(name, null);
    }

    /**
     * 获取数据源
     * @param dataConnectBean 数据源信息
     * @return datasource
     */
    public DataSource getDataSource(DataConnectBean dataConnectBean){
        return CodDaoConnectionPool.getInstance().getDataSource("", dataConnectBean);
    }

    /**
     * 获取数据源
     * @param name            数据源名称
     * @param dataConnectBean 数据源信息
     * @return datasource
     */
    public DataSource getDataSource(String name, DataConnectBean dataConnectBean){
        DataSource dataSource = null;
        if (StringUtils.isNotBlank(name)){
            dataSource = dynamicGetDataSource(name);
        }
        if (dataSource == null && dataConnectBean != null){
            dataSource = setDataSource(name, dataConnectBean);
        }
        return dataSource;
    }


    /**
     * 设置数据源
     */
    public synchronized DataSource setDataSource(DataConnectBean dataConnectBean){
        return setDataSource(dataConnectBean.getName(), dataConnectBean);
    }

    /**
     * 设置数据库源
     */
    public synchronized DataSource setDataSource(String name, DataConnectBean dataConnectBean){
        if (dataConnectBean == null){
            return null;
        }
        String type = dataConnectBean.getType();
        if (StringUtils.isEmpty(type)){
            type = "dbcp";
        }
        DataSource dataSource = null;
        try {
            switch (type){
                case ("dbcp"):
                    dataSource = getBasicDataSource(dataConnectBean);
                    break;
                case ("HikariCP"):
                    dataSource = getHikariDataSource(dataConnectBean);
                    break;
                case ("c3p0"):
                    dataSource = getComboPooledDataSource(dataConnectBean);
                    break;
                case ("druid"):
                    dataSource = getDruidDataSource(dataConnectBean);
                    break;
                default:
                    throw new NoSupportDataSourceException();
            }
        } catch (NoSupportDataSourceException e){
            System.out.println("数据源" + type + "数据库" + dataSourceNo + "连接失败" + e.getMessage());
            throw new NoSupportDataSourceException("数据源" + type + "数据库" + dataSourceNo + "连接失败" + e.getMessage());
        }
        if (dataSource != null){
            if (StringUtils.isNotBlank(name)){
                setDataSource(name, dataSource);
            } else {
                setDataSource(name, dataSource);
            }
        }
        return dataSource;
    }

    /**
     * 设置数据源
     */
    public synchronized void setDataSource(String name, DataSource dataSource){
        if (dataSource != null){
            if (StringUtils.isNotBlank(name)){
                this.setDataSources(name, dataSource);
            }
        }
    }

    /**
     * 获取datasource
     * @return datasource
     */
    /*public final synchronized DataSource getDataSource(DataConnectBean dataConnectBean) {
        try {
            if (dataSource.getConnection() == null){
                if (dataSourceNo < Integer.parseInt(number)){
                    dataSourceNo++;
                    getHikariDataSource(dataConnectBean);
                    return this.getDataSource(dataConnectBean);
                }
            }
        } catch (SQLException e) {
            if (!(dataSourceNo < Integer.parseInt(number))){
                System.err.println("所有数据库连接全部连不上");
            } else {
                return this.getDataSource(dataConnectBean);
            }
        }
        return dataSource;
    }*/

    /**
     * Hikari 数据源
     * @return Hikari 数据源
     */
    private DataSource getHikariDataSource(DataConnectBean dataConnectBean){
        HikariConfig jdbcConfig = new HikariConfig();
        jdbcConfig.setPoolName(getClass().getName());
        jdbcConfig.setDriverClassName(dataConnectBean.getDriverClass());
        jdbcConfig.setJdbcUrl(dataConnectBean.getUrl());
        jdbcConfig.setUsername(dataConnectBean.getUsername());
        jdbcConfig.setPassword(dataConnectBean.getPassword());
        jdbcConfig.setMaximumPoolSize(dataConnectBean.getMaxActive());
        jdbcConfig.setAutoCommit(dataConnectBean.isAutoCommit());
        HikariDataSource hikariDataSource = null;
        try {
            hikariDataSource = new HikariDataSource(jdbcConfig);
        } catch (Exception  e){
            System.out.println("数据库连接异常");
            return null;
        }

        /*hikariDataSource.setDriverClassName(dataConnectBean.getDriverClass());
        hikariDataSource.setUsername(dataConnectBean.getUsername());
        hikariDataSource.setPassword(dataConnectBean.getPassword());
        hikariDataSource.setJdbcUrl(dataConnectBean.getUrl());

        hikariDataSource.setMaximumPoolSize(dataConnectBean.getMaxActive());
        hikariDataSource.setMinimumIdle(dataConnectBean.getMinIdle());
        hikariDataSource.setConnectionTestQuery(dataConnectBean.getTestQuery());
        hikariDataSource.setAutoCommit(dataConnectBean.isAutoCommit());*/
        // 设置编码
        if (StringUtils.isNotBlank(dataConnectBean.getUseUnicode())){
            hikariDataSource.addDataSourceProperty("useUnicode", dataConnectBean.getUseUnicode());
        }
        if (StringUtils.isNotBlank(dataConnectBean.getCharacterEncoding())){
            hikariDataSource.addDataSourceProperty("characterEncoding", dataConnectBean.getCharacterEncoding());
        }
        // 初始化Sql
        if (StringUtils.isNotBlank(dataConnectBean.getInitSql())){
            hikariDataSource.setConnectionInitSql(dataConnectBean.getInitSql());
        }
        return hikariDataSource;
    }

    /**
     * dbcp 数据源
     * @return dbcp数据源
     */
    private DataSource getBasicDataSource(DataConnectBean dataConnectBean){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(dataConnectBean.getDriverClass());
        basicDataSource.setDriverClassName(dataConnectBean.getDriverClass());
        basicDataSource.setUsername(dataConnectBean.getUsername());
        basicDataSource.setPassword(dataConnectBean.getPassword());
        basicDataSource.setUrl(dataConnectBean.getUrl());
        basicDataSource.setInitialSize(dataConnectBean.getInitialSize());
        basicDataSource.setDefaultAutoCommit(dataConnectBean.isAutoCommit());
        // 设置初始化Sql, ";"分割
        if (StringUtils.isNotBlank(dataConnectBean.getInitSql())){
            String[] initSqls = dataConnectBean.getInitSql().split(";");
            List<String> list = new ArrayList<>(Arrays.asList(initSqls));
            basicDataSource.setConnectionInitSqls(list);
        }
        return basicDataSource;
    }

    /**
     * c3p0 数据源
     * @return c3p0数据源
     */
    private DataSource getComboPooledDataSource(DataConnectBean dataConnectBean){
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        try {
            comboPooledDataSource.setDriverClass(dataConnectBean.getDriverClass());
        } catch (PropertyVetoException e) {
            System.out.println("找不到驱动异常 " + e.getMessage());
            return null;
        }
        comboPooledDataSource.setUser(dataConnectBean.getUsername());
        comboPooledDataSource.setPassword(dataConnectBean.getPassword());
        comboPooledDataSource.setJdbcUrl(dataConnectBean.getUrl());
        comboPooledDataSource.setInitialPoolSize(dataConnectBean.getInitialSize());
        comboPooledDataSource.setMaxPoolSize(dataConnectBean.getMaxActive());
        comboPooledDataSource.setMaxIdleTime(dataConnectBean.getMaxIdle());
        comboPooledDataSource.setAutoCommitOnClose(dataConnectBean.isAutoCommit());
        return comboPooledDataSource;
    }

    /**
     * druid 数据源
     * @return druid 数据源
     */
    private DataSource getDruidDataSource(DataConnectBean dataConnectBean){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(dataConnectBean.getDriverClass());
        druidDataSource.setUsername(dataConnectBean.getUsername());
        druidDataSource.setPassword(dataConnectBean.getPassword());
        druidDataSource.setUrl(dataConnectBean.getUrl());
        druidDataSource.setInitialSize(dataConnectBean.getInitialSize());
        druidDataSource.setMaxActive(dataConnectBean.getMaxActive());
        druidDataSource.setMinIdle(dataConnectBean.getMinIdle());
        druidDataSource.setValidationQuery(dataConnectBean.getTestQuery());
        druidDataSource.setMaxWait(Integer.parseInt(dataConnectBean.getMaxWait()));
        druidDataSource.setDefaultAutoCommit(dataConnectBean.isAutoCommit());
        // 设置初始化Sql, ";"分割
        if (StringUtils.isNotBlank(dataConnectBean.getInitSql())){
            String[] initSqls = dataConnectBean.getInitSql().split(";");
            List<String> list = new ArrayList<>(Arrays.asList(initSqls));
            druidDataSource.setConnectionInitSqls(list);
        }
        return druidDataSource;
    }

    /**
     * 添加多重数据源
     * @param name       名称
     * @param dataSource 多重数据源
     */
    private void setDataSources(String name, DataSource... dataSource){
        LinkedList<DataSource> linkedList = map.get(name);
        if (linkedList == null || linkedList.isEmpty()){
            linkedList = new LinkedList<>();
            linkedList.addAll(Arrays.asList(dataSource));
        } else {
            linkedList.addAll(Arrays.asList(dataSource));
        }
        map.put(name, linkedList);
    }

    /**
     * 动态获取数据源
     * @param name 数据源名称
     * @return
     */
    private DataSource dynamicGetDataSource(String name){

        // 设置数据源 (获取数据源序号)
        Integer num = this.mapNo.get(name) == null ? 0 : this.mapNo.get(name);
        DataSource dataSource;

        // 判断是否超过已有数据源
        if (num < getDataSourceSize(name)){
            dataSource = this.map.get(name).get(num);
            // 下一次数据源(取下一个数据源)
            if (dataSource == null){
                // 设置下次数据源序号
                this.mapNo.put(name, num + 1);
                dataSource = dynamicGetDataSource(name);
            }
            return dataSource;
        }
        return null;
    }

    /**
     * 动态获取数据源
     * 第一个连不上, 取第二个
     * @param name 数据源名称
     * @param num  序号
     * @return
     */
    private DataSource dynamicGetDataSource(String name, int num){
        // 设置数据源
        Integer no = this.mapNo.get(name);
        DataSource dataSource;

        if (no != null){
            num = no+1;
        }

        if (no == null){
            num = 0;
        }

        // 判断是否超过已有数据源
        if (num < getDataSourceSize(name)){
            dataSource = this.map.get(name).get(num);
            // 下一次数据源(取下一个数据源)
            if (dataSource == null){
                this.mapNo.put(name, num);
                dataSource = dynamicGetDataSource(name, num);
            }
            return dataSource;
        }
        return null;
    }

    /**
     * 获取数据源大小
     * @return
     */
    private int getDataSourceSize(String name){
        List list = this.map.get(name);
        return list == null ? 0 : list.size();
    }


}
