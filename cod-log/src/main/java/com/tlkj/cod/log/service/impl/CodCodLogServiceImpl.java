/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.log.service.impl;

import com.tlkj.cod.log.service.CodLogService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.CodLoggerAdapter;
import org.springframework.stereotype.Service;


/**
 * Desc cod log
 *
 * @author sourcod
 * @version 1.0
 * @className CodCodLogServiceImpl
 * @date 2018/12/5 1:19 PM
 */
@Service("clogImpl")
public class CodCodLogServiceImpl implements CodLogService {

    @Override
    public String getSupportType() {
        return "clog";
    }

    @Override
    public void trace(String msg, Object... objects) {
        output("trace", msg, objects);
    }

    @Override
    public void debug(String msg, Object... objects) {
        output("debug", msg, objects);
    }

    @Override
    public void info(String msg, Object... objects) {
        output("info", msg, objects);
    }

    @Override
    public void error(String msg, Object... objects) {
        output("error", msg, objects);
    }

    @Override
    public void warn(String msg, Object... objects) {
        output("warn", msg, objects);
    }

    /**
     * 输出
     */
    private void output(String level, String msg, Object... objects){
        Logger logger = LoggerFactory.getLogger(this.getClass());

        boolean isThrowable = false;
        Throwable throwable = null;
        if (CodLoggerAdapter.getInstance().isErrorEnabled()){
            if (objects.length != 0){
                // 判断最后一个是不是异常信息
                // 如果是打印异常信息
                if (objects[objects.length - 1] instanceof Throwable){
                    isThrowable = true;
                    throwable = (Throwable) objects[objects.length - 1];
                }
            }
        }

        switch (level) {
            case "trace":
                if (isThrowable) {
                    logger.trace(msg, throwable);
                } else {
                    logger.trace(msg, objects);
                }
                break;
            case "debug":
                if (isThrowable) {
                    logger.debug(msg, throwable);
                } else {
                    logger.debug(msg, objects);
                }
                break;
            case "info":
                if (isThrowable) {
                    logger.info(msg, throwable);
                } else {
                    logger.info(msg, objects);
                }
                break;
            case "warn":
                if (isThrowable) {
                    logger.warn(msg, throwable);
                } else {
                    logger.warn(msg, objects);
                }
                break;
            case "error":
                if (isThrowable) {
                    logger.error(msg, throwable);
                } else {
                    logger.error(msg, objects);
                }
                break;
            default:
                logger.info(msg, objects);
        }
    }
}
