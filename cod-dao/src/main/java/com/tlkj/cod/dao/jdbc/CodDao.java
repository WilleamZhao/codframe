package com.tlkj.cod.dao.jdbc;

import com.tlkj.cod.dao.model.enums.CodDaoDatasourceTypeEnum;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc 父Dao
 * spring single
 *
 * @author sourcod
 * @version 1.0
 * @className CodDao
 * @date 2019/5/17 2:52 PM
 */
@Repository
public class CodDao {

    /**
     * 当前jdbc
     */
    private JdbcTemplate jdbcTemplate = null;

    private static volatile CodDao instance;

    /**
     * 线程安全
     */
    public static CodDao getInstance() {
        if (instance == null) {
            synchronized (CodDao.class) {
                if (instance == null) {
                    instance = new CodDao();
                }
            }
        }
        return instance;
    }

    /**
     * 数据源缓存
     */
    private Map<String, DataSource> datasource = new HashMap<>();

    public CodDao(){

    }

    public CodDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 设置数据源
     *
     * @param key         key
     * @param dataSource  数据源
     */
    public void setDatasource(String key, DataSource dataSource) {
        // if (!verifyDatasource(key)){
        CodDao.getInstance().datasource.put(key, dataSource);
        // }
    }

    public DataSource getDatasource(String key){
        return CodDao.getInstance().datasource.get(key);
    }

    /**
     * 添加数据源
     * @param key
     * @param dataSource
     */
    public void addDatasource(String key, DataSource dataSource) {

        // if (!verifyDatasource(key)){
        CodDao.getInstance().datasource.put(key, dataSource);
        // }
    }

    /**
     * 设置默认数据源
     *
     * @param dataSource 数据源
     */
    public void setDefault(DataSource dataSource) {
        CodDao.getInstance().datasource.put(CodDaoDatasourceTypeEnum.DEFAULT.name(), dataSource);
    }

    /**
     *
     * 获取默认数据源
     * @param dataSource
     * @return
     */
    public JdbcTemplate getDefault(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(CodDao.getInstance().datasource.get(CodDaoDatasourceTypeEnum.DEFAULT.name()));
        return jdbcTemplate;
    }

    /**
     * 设置数据源
     *
     * @param key key
     * @return
     */
    public CodDao ds(String key) {
        this.jdbcTemplate = new JdbcTemplate(CodDao.getInstance().datasource.get(key));
        return this;
    }

    /**
     * 获取Finder
     * @return
     */
    public Finder getFinder(){
        return new Finder(CodDao.getInstance().jdbcTemplate);
    }

    /**
     * 获取指定数据源Finder
     * @return
     */
    public Finder getFinder(String key){
        if (getDataSource(key) == null){
            return null;
        }
        return new Finder(new JdbcTemplate(getDataSource(key)));
    }

    /**
     * 获取Updater
     * @return
     */
    public Updater getUpdater(){
        return new Updater(jdbcTemplate);
    }

    /**
     * 获取指定数据源Updater
     * @param key
     * @return
     */
    public Updater getUpdater(String key){
        return getDataSource(key) == null ? null : new Updater(new JdbcTemplate(getDataSource(key)));
    }

    /**
     * 验证是否是内置数据源
     * @param key key
     * @return
     */
    boolean verifyDatasource(String key){
        for (CodDaoDatasourceTypeEnum typeEnum : CodDaoDatasourceTypeEnum.values()){
            if (typeEnum.name().equals(key)){
                return true;
            }
        }
        return false;
    }

    private DataSource getDataSource(String key){
        return datasource.get(key);
    }
}
