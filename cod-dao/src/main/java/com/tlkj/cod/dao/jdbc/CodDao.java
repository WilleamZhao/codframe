package com.tlkj.cod.dao.jdbc;

import com.tlkj.cod.dao.model.enums.CodDaoDatasourceTypeEnum;
import com.tlkj.cod.dao.util.CodDaoConnectionPool;

import javax.sql.DataSource;

/**
 * Desc cod-dao
 * spring single
 *
 * @author sourcod
 * @version 1.0
 * @className CodDao
 * @date 2019/5/17 2:52 PM
 */
public class CodDao extends CodDaoConnectionPool{

    /**
     * 获取数据源
     * @param name 数据源名称
     * @return
     */
    public DataSource getDatasource(String name){
        return getInstance().getDataSource(name);
    }

    /**
     * 添加数据源
     * @param name       name
     * @param dataSource 数据源
     */
    public void addDatasource(String name, DataSource dataSource) {
        if (!verifyDatasource(name)) {
            getInstance().setDataSource(name, dataSource);
        }
    }

    /**
     * 设置默认数据源
     *
     * @param dataSource 数据源
     */
    public void setDefault(DataSource dataSource) {
        getInstance().setDataSource(CodDaoDatasourceTypeEnum.DEFAULT.name(), dataSource);
    }

    /**
     * 获取默认数据源
     *
     * @return DataSource
     */
    public DataSource getDefault(){
        return getInstance().getDataSource(CodDaoDatasourceTypeEnum.DEFAULT.name());
    }

    /**
     * 验证是否是内置数据源
     * @param name name
     * @return boolean
     */
    private boolean verifyDatasource(String name){
        for (CodDaoDatasourceTypeEnum typeEnum : CodDaoDatasourceTypeEnum.values()){
            if (typeEnum.name().equals(name)){
                return true;
            }
        }
        return false;
    }
}
