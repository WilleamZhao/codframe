package com.tlkj.cod.file.model.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Desc cod-file 本地存储配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodFileLocalConfig
 * @date 2019/6/18 2:37 PM
 */
@Getter
@Setter
@Component
public class CodFileLocalConfig {

    @Value("${cod.file.config.local.url:./.cod-temp/codFile/}")
    private String url;


}
