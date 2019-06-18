package com.tlkj.cod.file.model.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Desc cod-file alioss 配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodFileAliOssConfig
 * @date 2019/6/18 2:36 PM
 */

@Getter
@Setter
@Component
public class CodFileAliOssConfig extends CodFileConfig{

    /**
     * oss endpoint
     */
    @Value("${cod.file.alioss.endpoint:http://oss-cn-beijing.aliyuncs.com}")
    private String endpoint;

    /**
     * oss accessKeyId
     */
    @Value("${cod.file.alioss.accessKeyId:LTAIK0L9vMXtT3xs}")
    private String accessKeyId;

    /**
     * oss accessKeySecret
     */
    @Value("${cod.file.alioss.accessKeySecret:ZYRrh37nY2w6JooVgwc7qhqJdLVgAp}")
    private String accessKeySecret;

    /**
     * oss bucketName
     */
    @Value("${cod.file.alioss.bucket:codframe}")
    private String bucketName;

    /**
     * oss maxConnect
     */
    @Value("${cod.file.alioss.maxConnect:20}")
    private int maxConnect;

    /**
     * oss socketTimeOut
     */
    @Value("${cod.file.alioss.socketTimeout:50000}")
    private int socketTimeout;

    /**
     * oss 请求重试次数
     */
    @Value("${cod.file.alioss.maxErrorRetry:3}")
    private int maxErrorRetry;
}
