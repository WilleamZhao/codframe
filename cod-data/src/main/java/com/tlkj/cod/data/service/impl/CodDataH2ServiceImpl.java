package com.tlkj.cod.data.service.impl;

/*import com.tlkj.cod.dao.bean.CodDaoBean;
import com.tlkj.cod.dao.bean.DataConnectBean;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.dao.util.DBConnectionPool;*/
import com.tlkj.cod.data.model.CodDataModel;
import com.tlkj.cod.data.model.config.CodDataConfig;
import com.tlkj.cod.data.model.enums.CodDataTypeEnum;
import com.tlkj.cod.data.service.CodDataService;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * Desc CodData H2数据库
 *
 * @author sourcod
 * @version 1.0
 * @className CodDataH2ServiceImpl
 * @date 2019/5/28 1:48 PM
 */
@Service
public class CodDataH2ServiceImpl implements CodDataService {

    private static CodDataConfig codDataConfig = new CodDataConfig();

    public static void main(String[] args) {
        /*DataConnectBean dataConnectBean = new DataConnectBean();
        dataConnectBean.setCharacterEncoding(codDataConfig.getEncoding());
        dataConnectBean.setDriverClass(codDataConfig.getDriver());
        dataConnectBean.setUrl(codDataConfig.getUrl());
        dataConnectBean.setUsername(codDataConfig.getUsername());
        dataConnectBean.setPassword(codDataConfig.getPassword());
        DBConnectionPool dbConnectionPool = new DBConnectionPool();
        DataSource dataSource = dbConnectionPool.getHikariDataSource(dataConnectBean);
        try {
            DatabaseMetaData data = dataSource.getConnection().getMetaData();
            System.out.println(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * 1. 建立数据库连接
     * 2. 检查表是否存在
     * 3. 如果存在执行查询
     * 4. 不存在执行初始化数据库
     * 5. 初始化完成
     */
    @Override
    public void init() {
        /*DataConnectBean dataConnectBean = new DataConnectBean();
        dataConnectBean.setCharacterEncoding(codDataConfig.getEncoding());
        dataConnectBean.setDriverClass(codDataConfig.getDriver());
        dataConnectBean.setUrl(codDataConfig.getUrl());
        dataConnectBean.setUsername(codDataConfig.getUsername());
        dataConnectBean.setPassword(codDataConfig.getPassword());
        DBConnectionPool dbConnectionPool = new DBConnectionPool();
        DataSource dataSource = dbConnectionPool.getHikariDataSource(dataConnectBean);
        try {
            dataSource.getConnection().getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Finder finder = new Finder(jdbcTemplate);
        Updater updater = new Updater(jdbcTemplate);
        CodDaoBean codDaoBean = new CodDaoBean();
        codDaoBean.setFinder(finder);
        codDaoBean.setUpdater(updater);

        finder.from("");*/

        CodDataModel codDataModel = new CodDataModel();
        codDataModel.setInit(true);
        codDataModel.setCodDataService(this);
        codDataModel.setType(CodDataTypeEnum.H2);
    }

    @Override
    public void getData() {

    }

    @Override
    public void setData() {

    }
}
