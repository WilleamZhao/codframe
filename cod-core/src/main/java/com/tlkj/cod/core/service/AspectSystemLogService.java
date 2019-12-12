/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.core.service;

import com.tlkj.cod.model.enums.StatusCode;

/**
 * Desc 日志注解service
 *
 * @author sourcod
 * @version 1.0
 * @className CodLogService
 * @date 2018/11/6 下午4:36
 */
public interface AspectSystemLogService {

    /**
     * 保存系统日志接口
     */
    StatusCode saveSystemLog(String methodName, String content, String ip, String username, String operationName, String operationType, Object[] params, Object[] values, Object o);
}
