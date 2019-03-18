/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.system.impl;

import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.common.CodCommonUUID;
import com.tlkj.cod.model.common.LogMessageModel;
import com.tlkj.cod.model.system.core.SystemModel;
import com.tlkj.cod.service.system.LogService;
import com.tlkj.cod.common.CodCommonDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Desc cod log
 *
 * @author sourcod
 * @version 1.0
 * @className CodLogServiceImpl
 * @date 2018/12/5 1:19 PM
 */
@Service("clogImpl")
public class CodLogServiceImpl implements LogService {

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
        StackTraceElement[] elements = new Throwable().getStackTrace();
        Logger logger = LoggerFactory.getLogger(elements[2].getClassName());

        boolean isThrowable = false;
        Throwable throwable = null;
        // 是否打印
        if (SystemModel.getInstance().getLog().isConsole()){
            // 源数组
            Object[] tempObject = objects.clone();

            if (objects.length != 0){
                // 判断最后一个是不是异常信息
                // 如果是打印异常信息
                if (objects[objects.length - 1] instanceof Throwable){
                    // 截取后数组
                    tempObject = Arrays.copyOf(objects, tempObject.length-1);
                    isThrowable = true;
                    throwable = (Throwable) tempObject[tempObject.length - 1];
                    // 打印异常信息
                    throwable.printStackTrace();
                }
            }
            msg = MessageFormatter.arrayFormat(msg, tempObject).getMessage();
            // 日志消息体
            LogMessageModel model = new LogMessageModel(CodCommonUUID.getUUID(),
                    elements[2].getFileName(), elements[2].getClassName(),
                    elements[2].getMethodName(), elements[2].getLineNumber(), level,
                    CodCommonDate.getDate("yyyy-MM-dd HH:mm:ss,SSS"), msg);
            String log = CodCommonJson.dump(model);
            System.out.println(log);
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
