/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.log.service.impl;

import com.tlkj.cod.common.CodCommonAnsiPrint;
import com.tlkj.cod.common.CodCommonDate;
import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.common.CodCommonUUID;
import com.tlkj.cod.common.constant.CodCommonAnsiConstant;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.log.service.CodLogService;
import com.tlkj.cod.model.common.LogMessageModel;
import com.tlkj.cod.model.system.core.SystemModel;
import com.tlkj.cod.model.system.entity.CodFrameSetDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Desc cod log
 *
 * @author sourcod
 * @version 1.0
 * @className CodCodLogServiceImpl
 * @date 2018/12/5 1:19 PM
 */
// @Primary
@Service("clogImpl")
public class CodCodLogServiceImpl implements CodLogService {
/*
    private static SystemModel model = SystemModel.getInstance();

    @Autowired
    List<CodLogService> codLogServices;*/

    @Override
    public String getSupportType() {
        return "clog";
    }

    @Autowired
    Finder finder;

    private static String setValue = "";

    private static boolean isAnsi = true;


    /**
     * 获取日志设置
     * 日志支持的类型 clog(默认), slf4j, logback, aliyunLog
     * 1. 默认获取配置文件设置
     * 2. 配置文件没有获取数据库设置
     * 3. 数据库没有默认采用clog
     * @return CodLogService 日志服务
     */
    /*public CodLogService getLog() {
        if (model.getLog() != null && StringUtils.isNotBlank(model.getLog().getType())){
            setValue = model.getLog().getType();
        } else {
            setValue = getSetValue("clog");
        }
        for (CodLogService f : codLogServices){
            if (f.getSupportType().equals(setValue)){
                return f;
            }
        }
        System.err.println("获取日志service错误");
        return null;
    }*/

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
        if (isAnsi){
            msg = CodCommonAnsiPrint.toString(CodCommonAnsiConstant.RED, msg);
        }
        output("error", msg, objects);
    }

    @Override
    public void warn(String msg, Object... objects) {
        if (isAnsi){
            msg = CodCommonAnsiPrint.toString(CodCommonAnsiConstant.YELLOW, msg);
        }
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

    /**
     * 获取设置Value
     * @param setCode 设置代码
     * @return 设置值
     */
    private String getSetValue(String setCode) {
        CodFrameSetDo setDo = finder.from(CodFrameSetDo.TABLE_NAME).where("set_code", setCode).first(CodFrameSetDo.class);
        return setDo.getSet_value();
    }
}
