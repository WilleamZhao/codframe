package com.tlkj.cod.dao.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tlkj.cod.dao.bean.DataConnectBean;
import com.tlkj.cod.dao.exception.NoSupportDataSourceException;
import com.tlkj.cod.dao.model.enums.CodDaoDatasourceTypeEnum;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

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
        this.dataSource = convert(dataConnectBean);
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

        // 没有取到数据源 设置一个数据源
        if (dataSource == null && dataConnectBean != null){
            setDataSource(name, dataConnectBean);
        }
        return dataSource;
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
            // 验证数据源是否可用
            if (!verifyDataSource(dataSource)){
                // 下一次数据源(取下一个数据源)
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
     * @param num  序号 从第几个开始取
     * @return
     */
    private DataSource dynamicGetDataSource(String name, int num){
        this.mapNo.put(name, num);
        return dynamicGetDataSource(name);
    }

    /**
     * 验证数据源
     * @param dataSource 数据源
     * @return true 有效 false 无效
     */
    private boolean verifyDataSource(DataSource dataSource){
        if (dataSource == null){
            return false;
        }
        try {
            Connection connection = dataSource.getConnection();
            if (connection.isClosed()){
                return false;
            } else {
                return connection.isValid(3000);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 设置数据源
     */
    public synchronized void setDataSource(String name, DataConnectBean dataConnectBean){
        List<DataConnectBean> list = new ArrayList<>();
        list.add(dataConnectBean);
        setDataSource(name, list, true);
    }

    /**
     * 设置数据源
     */
    public synchronized void setDataSource(DataConnectBean dataConnectBean){
        List<DataConnectBean> list = new ArrayList<>();
        list.add(dataConnectBean);
        setDataSource(dataConnectBean.getName(), list, true);
    }

    /**
     * 设置多个数据源
     */
    public synchronized void setDataSources(String name, List<DataConnectBean> dataConnectBean){
        setDataSource(name, dataConnectBean, true);
    }

    /**
     * 设置多个数据源
     */
    public synchronized void setDataSources(List<DataConnectBean> dataConnectBean){
        setDataSource("", dataConnectBean, true);
    }

    /**
     * 添加数据源
     */
    public synchronized void addDataSource(DataConnectBean dataConnectBean){
        List<DataConnectBean> list = new ArrayList<>();
        list.add(dataConnectBean);
        setDataSource(dataConnectBean.getName(), list, false);
    }

    /**
     * 添加数据源
     */
    public synchronized void addDataSource(String name, DataConnectBean dataConnectBean){
        List<DataConnectBean> list = new ArrayList<>();
        list.add(dataConnectBean);
        setDataSource(name, list, false);
    }

    /**
     * 添加数据源
     */
    public synchronized void addDataSource(List<DataConnectBean> dataConnectBeans){
        setDataSource("", dataConnectBeans, false);
    }

    /**
     * 添加数据源
     */
    public synchronized void addDataSource(String name, List<DataConnectBean> dataConnectBeans) {
        setDataSource(name, dataConnectBeans, false);
    }

    /**
     * 设置数据源
     */
    public synchronized void setDataSource(String name, DataSource dataSource){
        if (dataSource != null){
            if (StringUtils.isNotBlank(name)){
                // 1. 清空数据源
                clear(name);
                // 2. 添加数据源
                this.setDataSources(name, dataSource);
            }
        }
    }

    /**
     * 添加数据源
     */
    public synchronized void addDataSource(String name, DataSource dataSource){
        if (dataSource != null){
            if (StringUtils.isNotBlank(name)){
                this.addDataSources(name, dataSource);
            }
        }
    }

    /**
     * 设置多重数据源
     * @param name       名称
     * @param dataSource 多重数据源
     */
    private void setDataSources(String name, DataSource... dataSource){
        // 1. 设置
        LinkedList<DataSource> linkedList = new LinkedList<>();
        linkedList.addAll(Arrays.asList(dataSource));
        this.map.put(name, linkedList);
    }

    /**
     * 添加多重数据源
     * @param name       名称
     * @param dataSource 多重数据源
     */
    private void addDataSources(String name, DataSource... dataSource){
        // 1. 读取旧配置
        LinkedList<DataSource> linkedList = this.map.get(name);
        if (linkedList == null || linkedList.isEmpty()){
            linkedList = new LinkedList<>();
            linkedList.addAll(Arrays.asList(dataSource));
        } else {
            // 2. 添加
            linkedList.addAll(Arrays.asList(dataSource));
        }
        this.map.put(name, linkedList);
    }

    /**
     * 删除数据源
     * @param name 数据源名称
     * @param num  删除第几个  -1 全部删除
     */
    public void delDataSource(String name, int num){
        LinkedList<DataSource> dataSources = this.map.get(name);
        if (num == -1){
            dataSources.remove();
        } else {
            dataSources.remove(num);
        }
    }

    /**
     * 检查数据源
     * 不可用删除
     * @param name
     */
    private void checkDataSource(String name, int num){
        if (StringUtils.isNotBlank(name)){
            LinkedList<DataSource> dataSources = this.map.get(name);
            if (num == -1){
                for (DataSource dataSource : dataSources){
                    if (!verifyDataSource(dataSource)){
                        dataSources.remove(dataSource);
                    }
                }
            } else {
                DataSource dataSource = dataSources.get(num);
                if (dataSource != null && !verifyDataSource(dataSources.get(num))){
                    dataSources.remove(num);
                }
            }
        }
    }


    /**
     * 获取数据源大小
     * @param name 数据源名称  不传查所有数据源大小
     * @return 数据源大小
     */
    private int getDataSourceSize(String name){
        AtomicInteger num = new AtomicInteger();
        if (StringUtils.isEmpty(name)){
            this.map.forEach((k, v) -> num.addAndGet(v.size()));
        } else {
            List list = this.map.get(name);
            num.addAndGet(list == null ? 0 : list.size());
        }
        return num.get();
    }

    /**
     * 设置数据库源
     * @param name             数据源名称
     * @param dataConnectBeans 数据源
     * @param isReset          是否重置 (true重置)
     */
    public synchronized void setDataSource(String name, List<DataConnectBean> dataConnectBeans, boolean isReset){
        // 重置数据源
        if (isReset){
            clear(name, dataConnectBeans);
        }
        if (dataConnectBeans != null && !dataConnectBeans.isEmpty()){
            for (DataConnectBean dataConnectBean : dataConnectBeans){
                if (dataConnectBean == null){
                    return;
                }
                DataSource dataSource = convert(dataConnectBean);
                if (dataSource != null){
                    if (StringUtils.isNotBlank(name)){
                        addDataSource(name, dataSource);
                    } else {
                        addDataSource(CodDaoDatasourceTypeEnum.DEFAULT.name(), dataSource);
                    }
                }
            }
        }
    }

    /**
     * 清除 datasource
     * @param name 数据源名称
     */
    private void clear(String name){
        clear(name, null);
    }

    /**
     * 清除 datasource
     * @param name 数据源名称
     */
    private void clear(String name, List<DataConnectBean> dataConnectBeans){
        if (StringUtils.isNotBlank(name)){
            List<DataSource> list = this.map.get(name);
            // 关闭链接
            if (list == null){
                return;
            }
            for (DataSource dataSource : list){
                try {
                    dataSource.getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // 清空数据源
            list.clear();
        } else {
            // 循环重置
            if (dataConnectBeans == null){
                return;
            }
            for (DataConnectBean dataConnectBean : dataConnectBeans){
                if (StringUtils.isNotBlank(dataConnectBean.getName())){
                    List<DataSource> list = this.map.get(dataConnectBean.getName());
                    // 关闭链接
                    for (DataSource dataSource : list){
                        try {
                            dataSource.getConnection().close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    // 清空数据源
                    list.clear();
                }
            }
        }
    }

    /**
     * Bean to DataSource
     * @param dataConnectBean bean
     * @return dataSource
     */
    private DataSource convert(DataConnectBean dataConnectBean){
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

        return dataSource;
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

}
