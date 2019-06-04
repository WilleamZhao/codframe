package com.tlkj.cod.data.service.impl;

import com.tlkj.cod.dao.bean.DataConnectBean;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.dao.model.enums.CodDaoDatasourceTypeEnum;
import com.tlkj.cod.dao.util.CreateTable;
import com.tlkj.cod.dao.util.DBConnectionPool;
import com.tlkj.cod.data.model.config.CodDataConfig;
import com.tlkj.cod.data.model.entity.CodDataConfigDo;
import com.tlkj.cod.data.service.CodDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Desc cod data service impl
 *
 * @author sourcod
 * @version 1.0
 * @className CodDataServiceImpl
 * @date 2019/6/3 2:02 PM
 */
@Service
public class CodDataServiceImpl implements CodDataService {

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    @PostConstruct
    public void inita(){
        CodDataConfig codDataConfig = new CodDataConfig();
        DataConnectBean dataConnectBean = new DataConnectBean();
        dataConnectBean.setCharacterEncoding(codDataConfig.getEncoding());
        dataConnectBean.setDriverClass(codDataConfig.getDriver());
        dataConnectBean.setUrl(codDataConfig.getUrl());
        dataConnectBean.setUsername(codDataConfig.getUsername());
        dataConnectBean.setPassword(codDataConfig.getPassword());
        dataConnectBean.setName(CodDaoDatasourceTypeEnum.DATA.name());
        DBConnectionPool.getInstance().setDataSource(dataConnectBean);
        finder.dsf(CodDaoDatasourceTypeEnum.DATA.name());
    }

    /**
     * 初始化
     */
    @Override
    public void init() {
        CodDataConfig codDataConfig = new CodDataConfig();
        DataConnectBean dataConnectBean = new DataConnectBean();
        dataConnectBean.setCharacterEncoding(codDataConfig.getEncoding());
        dataConnectBean.setDriverClass(codDataConfig.getDriver());
        dataConnectBean.setUrl(codDataConfig.getUrl());
        dataConnectBean.setUsername(codDataConfig.getUsername());
        dataConnectBean.setPassword(codDataConfig.getPassword());
        dataConnectBean.setName(CodDaoDatasourceTypeEnum.DATA.name());
        DBConnectionPool.getInstance().setDataSource(dataConnectBean);
        if (finder == null){
            // finder.dsf(CodDaoDatasourceTypeEnum.DATA.name());
            finder = new Finder(DBConnectionPool.getInstance().getDataSource(CodDaoDatasourceTypeEnum.DATA.name()));
        }

        if (updater == null){
            updater = new Updater(DBConnectionPool.getInstance().getDataSource(CodDaoDatasourceTypeEnum.DATA.name()));
        }

        // config
        if (verifyExists(CodDataConfigDo.TABLE_NAME)){
            String sql = CreateTable.createTable(CodDataConfigDo.class, CodDataConfigDo.TABLE_NAME);
            updater.execute(sql);
        }

        /*int num = finder.from("all_tables").where("table_name", CodDataConfigDo.TABLE_NAME).select("count(*)").firstForObject(Integer.class);
        */
    }

    /**
     * 验证是否存在
     * @param table 表名
     * @return 是否不存在
     */
    private boolean verifyExists(String table){
        Integer num = finder.from("INFORMATION_SCHEMA.TABLES")
                .where("table_name", table)
                .select("count(*)")
                .firstForObject(Integer.class);
        return num == 0;
    }

    /**
     * 配置
     */
    @Override
    public Map<String, String> getConfig() {
        List<CodDataConfigDo> dataConfigDos = finder.from(CodDataConfigDo.TABLE_NAME).all(CodDataConfigDo.class);
        return dataConfigDos.stream().collect(Collectors.toMap(CodDataConfigDo::getC_key, CodDataConfigDo::getC_value));
    }

    /**
     * 获取数据
     */
    @Override
    public String getData(String key) {
        List<CodDataConfigDo> dataConfigDos = finder.from(CodDataConfigDo.TABLE_NAME).all(CodDataConfigDo.class);
        Map<String, String> map = dataConfigDos.stream().collect(Collectors.toMap(CodDataConfigDo::getC_key, CodDataConfigDo::getC_value));
        return map.get(key);
    }

    /**
     * 设置数据
     * @param key   key
     * @param value value
     */
    @Override
    public void setData(String key, String value) {
        updater.insert(CodDataConfigDo.TABLE_NAME).setId().set("c_value", value).set("c_key", key).update();
    }

    public static void main(String[] args) {
        CreateTable.createTable(CodDataConfigDo.class, CodDataConfigDo.TABLE_NAME);
    }
}
