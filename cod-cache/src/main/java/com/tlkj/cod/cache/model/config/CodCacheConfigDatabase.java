/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.cache.model.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Desc codFrame database缓存配置model
 *
 * @author sourcod
 * @version 1.0
 * @className CodCacheConfigDatabase
 * @date 2019/2/13 11:00 AM
 */
@Getter
@Setter
@Component
public class CodCacheConfigDatabase extends CodCacheConfigBase {

    private static final long serialVersionUID = -344373373981045496L;

    /**
     * 默认过期时间
     */
    private String due;


}
