/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.config.service;

import com.tlkj.cod.model.common.SystemResponse;

/**
 * Desc 配置service
 *
 * @author sourcod
 * @version 1.0
 * @className CodConfigService
 * @date 2019/4/9 4:59 PM
 */
public interface CodConfigService {

    SystemResponse init();

    /**
     * 加载配置
     */
    SystemResponse load();

    /**
     * 重新加载配置
     */
    SystemResponse reload();

    /**
     * 配置列表
     */
    SystemResponse list();
}

