package com.tlkj.cod.file.model.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Desc cod-file 数据库 配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodFileDatabaseConfig
 * @date 2019/6/18 2:37 PM
 */
@Getter
@Setter
@Component("codFileDatabaseConfig")
public class CodFileDatabaseConfig extends CodFileConfig implements Serializable {

    private static final long serialVersionUID = -53722484064797351L;

    /**
     * ip
     */
    //@Value(value = "${cod.file.config.database.host:#{codAdminDatabaseConfig.driver}}")
    @Value(value = "${cod.file.config.database.host:}")
    private String host;

    /**
     * 端口
     */
    @Value(value = "${cod.file.config.database.port:}")
    private String port;

    /**
     * 用户名
     */
    @Value(value = "${cod.file.config.database.username:codframe}")
    private String username;

    /**
     * 密码
     */
    @Value(value = "${cod.file.config.database.password:123456}")
    private String password;

    /**
     * 文件表名
     */
    @Value(value = "${cod.file.config.database.tableName:cod_file}")
    private String tableName;

    /**
     * 最大可上传文件
     */
    @Value(value = "${cod.file.config.database.maxSize:100M}")
    private String maxSize;
}
