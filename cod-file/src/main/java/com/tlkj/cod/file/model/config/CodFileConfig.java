/*
 * Copyright (c) 2019.
 * sourcod.com
 * All rights reserved
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.file.model.config;

import com.tlkj.cod.config.model.config.CodCoreConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * cod-file 模块配置model
 * @author sourcod
 * @date 2019年6月16日 20点48分
 */

@Getter
@Setter
@Component
public class CodFileConfig extends CodCoreConfig implements Serializable {

    private static final long serialVersionUID = -1391591271796828626L;

    /**
     * 上传文件类型
     */
    @Value("${cod.file.type:local}")
    private String type;

    private String value;





}
