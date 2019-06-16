/*
 * Copyright (c) 2019.
 * sourcod.com
 * All rights reserved
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.file.model.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

/**
 * cod-file 模块配置model
 * @author sourcod
 * @date 2019年6月16日 20点48分
 */

@Getter
@Setter
public class CodFileConfig implements Serializable {

    /**
     * 上传文件类型
     */
    @Value("${cod.file.type:local}")
    private String type;

    private String value;

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
