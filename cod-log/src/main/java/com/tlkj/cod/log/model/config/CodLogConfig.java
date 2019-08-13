package com.tlkj.cod.log.model.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodLogConfig
 * @date 2019/6/21 9:11 PM
 */
@Getter
@Setter
@Component
public class CodLogConfig {

    @Value("${cod.log.config.type:clog}")
    private String type;


}
