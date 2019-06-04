package com.tlkj.cod.dao.model.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Desc 数据库配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodDataSourceConfig
 * @date 2019/5/31 5:01 PM
 */
@Getter
@Setter
@Component("codDatasource")
public class CodDataSourceConfig {

    @Value("${cod.database.type:}")
    private String url;

    @Value("${cod.database.url:}")
    private String username;
}
