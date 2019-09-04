package com.tlkj.cod.admin.model.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

    @Value("${cod.admin.database.config.driver:#{codDataConfig.driver}}")
    private String driver;

    @Value("${cod.admin.database.config.url:#{codDataConfig.url}}")
    private String url;

    @Value("${cod.admin.database.config.username:#{codDataConfig.username}}")
    private String username;

    @Value("${cod.admin.database.config.password:#{codDataConfig.password}}")
    private String password;

}
