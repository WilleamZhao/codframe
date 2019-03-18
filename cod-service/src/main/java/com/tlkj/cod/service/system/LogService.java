/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.system;

/**
 * Desc 日志服务
 *
 * trace 00;
 * debug 10;
 * info 20;
 * warn 30;
 * error 40;
 *
 * @author sourcod
 * @version 1.0
 * @className LogService
 * @date 2018/11/7 下午10:33
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
