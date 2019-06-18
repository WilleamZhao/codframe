package com.tlkj.cod.file.model.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Desc cod-file 七牛配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodFileQiniuConfig
 * @date 2019/6/18 2:37 PM
 */

@Getter
@Setter
@Component
public class CodFileQiniuConfig extends CodFileConfig{

    /**
     * qiniu accessKey
     */
    @Value("${cod.file.qiniu.accessKey:LTAIK0L9vMXtT3xs}")
    private String accessKey;

    /**
     * qiniu secretKey
     */
    @Value("${cod.file.qiniu.secretKey:ZYRrh37nY2w6JooVgwc7qhqJdLVgAp}")
    private String secretKey;
}
