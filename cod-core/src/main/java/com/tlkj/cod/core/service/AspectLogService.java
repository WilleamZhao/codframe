/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.service;

import com.tlkj.cod.model.enums.StatusCode;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className LogService
 * @date 2018/11/6 下午4:36
 */
public interface AspectLogService {

    /**
     * 保存入参日志接口
     * @param name     接口名称
     * @param type    打印日志类型(clog log4j aliyun logback)
     * @param params   参数名称
     * @param values   参数值
     * @return
     */
    StatusCode saveLog(String type, String name, Object[] params, Object[] values);

    /**
     * 保存出参日志接口
     * @param content 内容
     * @param type    打印日志类型(clog log4j aliyun logback)
     * @param o       返回对象
     * @return
     */
    StatusCode saveLog(String type, String content, Object o);

    /**
     * 保存系统日志接口
     */
    StatusCode saveSystemLog(String methodName, String content, String ip, String username, String operationName, String operationType, Object[] params, Object[] values, Object o);
}
