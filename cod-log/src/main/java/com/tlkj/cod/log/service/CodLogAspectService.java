/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.log.service;

import com.tlkj.cod.model.common.SystemResponse;
import com.tlkj.cod.model.enums.StatusCode;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodLogService
 * @date 2018/11/6 下午4:36
 */
public interface CodLogAspectService {

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
     * 打印异常日志
     * @param type    打印日志类型(clog log4j aliyun logback)
     * @param name    接口名称
     * @return
     */
    SystemResponse saveError(String type, String name, Throwable e);
}
