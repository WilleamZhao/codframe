/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.log.service;

/**
 * Desc 日志服务接口
 *
 * @author sourcod
 * @version 1.0
 * @className LogService
 * @date 2019/2/12 12:51 AM
 */
public interface LogService {

    /**
     * 默认支持clog
     * @return 默认支持
     */
    default String getSupportType(){
        return "clog";
    }

    /**
     * 追踪
     */
    void trace(String msg, Object... objects);

    void debug(String msg, Object... objects);

    void info(String msg, Object... objects);

    void error(String msg, Object... objects);

    void warn(String msg, Object... objects);
}
