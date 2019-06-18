package com.tlkj.cod.config.model.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Desc codFrame 核心配置
 * 所有配置继承它
 *
 * @author sourcod
 * @version 1.0
 * @className CodCoreConfig
 * @date 2019/5/31 5:12 PM
 */
@Getter
@Setter
@Component
public class CodCoreConfig {

    /**
     * 框架临时目录
     */
    @Value("${cod.core.temp.dir:.cod-temp}")
    private String codCoreTempDir;

    /**
     * 试用时间
     * 30天
     * 30*24*60*60
     */
    @Value("${cod.core.trial.time:2592000}")
    private String codCoreTrialTime;

    /**
     * 服务名
     */
    @Value("${cod.core.server.name:codframe}")
    private String serverName;


}
