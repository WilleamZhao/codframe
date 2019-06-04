package com.tlkj.cod.config.model.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Desc
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

    @Value("${port:111:222}")
    private String port;

    @Value("${ip:999}")
    private String ip;


}
