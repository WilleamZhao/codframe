package com.tlkj.cod.admin.model.config;

import com.tlkj.cod.dao.bean.DataConnectBean;
import com.tlkj.cod.dao.model.enums.CodDaoDatasourceTypeEnum;
import com.tlkj.cod.dao.util.CodDaoConnectionPool;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Desc 管理端配置文件
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminDatabaseConfig
 * @date 2019/6/5 5:42 PM
 */
@Getter
@Setter
@Component
public class CodAdminDatabaseConfig {

    @Value("${cod.admin.database.config.driver:com.mysql.jdbc.Driver}")
    private String driver;

    @Value("${cod.admin.database.config.url:jdbc:mysql://rm-2ze073417o2rogq4dyo.mysql.rds.aliyuncs.com:3306/codframe}")
    private String url;

    @Value("${cod.admin.database.config.username:codframe}")
    private String username;

    @Value("${cod.admin.database.config.password:codframep@ssw0rd}")
    private String password;

    @Autowired
    DataConnectBean bean;

    @PostConstruct
    public void init(){
        bean.setPassword(this.getPassword());
        bean.setUsername(this.getUsername());
        bean.setDriverClass(this.getDriver());
        bean.setUrl(this.getUrl());
        bean.setAutoCommit(true);
        CodDaoConnectionPool.getInstance().setDataSource(CodDaoDatasourceTypeEnum.DEFAULT.name(), bean);
    }


}
