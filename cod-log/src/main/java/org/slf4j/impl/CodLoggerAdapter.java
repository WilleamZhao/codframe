/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package org.slf4j.impl;

import com.tlkj.cod.model.common.LogMessageModel;
import com.tlkj.cod.model.system.core.SystemModel;
import com.tlkj.cod.model.system.core.SystemSetLog;
import com.tlkj.cod.common.CodCommonDate;
import com.tlkj.cod.common.CodCommonIO;
import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.common.CodCommonUUID;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;

/**
 * Desc slf4j-cod-log adapter
 *
 * @author sourcod
 * @version 1.0
 * @className CodLoggerAdapter
 * @date 2018/12/4 8:42 PM
 */
public final class CodLoggerAdapter extends MarkerIgnoringBase implements LocationAwareLogger, Serializable {

    private long nextCheck = System.currentTimeMillis () - 1;
    private static Date now = new Date();
    private static RollingCalendar rc = new RollingCalendar();
    private static String path = "";
    private static SystemModel systemModel = SystemModel.getInstance();
    private static SystemSetLog systemSetLog = systemModel.getLog();
    /**
     * 是否分割
     */
    private static boolean isSplit = systemSetLog != null && StringUtils.isNotBlank(systemSetLog.getSplit()) ? Boolean.valueOf(systemSetLog.getSplit()) : false;

    /**
     * 日志路径
     */
    private static String logHref = systemSetLog != null && StringUtils.isNotBlank(systemSetLog.getHref()) ? systemSetLog.getHref() : "./logs/";

    /**
     * 日期路径
     */
    private static String dateHref = systemSetLog != null && StringUtils.isNotBlank(systemSetLog.getPattern()) ? systemSetLog.getPattern() : "yyyy/MM/dd";

    /**
     * 等级
     */
    private static String level = systemSetLog != null && StringUtils.isNotBlank(systemSetLog.getLevel()) ? systemSetLog.getLevel() : "info";

    private static volatile CodLoggerAdapter instance;

    private CodLoggerAdapter() {

    }

    private CodLoggerAdapter(String name){
        this.name = name;
    }

    public static CodLoggerAdapter getInstance(String name){
        if (instance == null) {
            synchronized (CodLoggerAdapter.class) {
                if (instance == null) {
                    instance = new CodLoggerAdapter(name);
                }
            }
        }
        return instance;
    }

    /**
     * 线程安全
     */
    public static CodLoggerAdapter getInstance() {
        if (instance == null) {
            synchronized (CodLoggerAdapter.class) {
                if (instance == null) {
                    instance = new CodLoggerAdapter();
                }
            }
        }
        return instance;
    }

    @Override
    public void log(Marker marker, String fqcn, int level, String message, Object[] argArray, Throwable t) {
        String clogLevel = levelToClog(level);
        output(this.name, clogLevel, message, argArray);
    }

    @Override
    public boolean isTraceEnabled() {
        return clogToLevel(level) <= LocationAwareLogger.TRACE_INT;
    }

    @Override
    public void trace(String msg) {
        output("trace", msg);
    }

    @Override
    public void trace(String format, Object arg) {
        output("trace", format, arg);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        output("trace", format, arg1, arg2);
    }

    @Override
    public void trace(String format, Object... arguments) {
        output("trace", format, arguments);
    }

    @Override
    public void trace(String msg, Throwable t) {
        output("trace", msg, t);
    }

    @Override
    public boolean isDebugEnabled() {
        return clogToLevel(level) <= LocationAwareLogger.DEBUG_INT;
    }

    @Override
    public void debug(String msg) {
        output("debug", msg);
    }

    @Override
    public void debug(String format, Object arg) {
        output("debug", format, arg);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        output("debug", format, arg1, arg2);
    }

    @Override
    public void debug(String format, Object... arguments) {
        output("debug", format, arguments);
    }

    @Override
    public void debug(String msg, Throwable t) {
        output("debug", msg, t);
    }

    @Override
    public boolean isInfoEnabled() {
        return clogToLevel(level) <= LocationAwareLogger.INFO_INT;
    }

    @Override
    public void info(String msg) {
        output("info", msg);
    }

    @Override
    public void info(String format, Object arg) {
        output("info", format, arg);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        output("info", format, arg1, arg2);
    }

    @Override
    public void info(String format, Object... arguments) {
        output("info", format, arguments);
    }

    @Override
    public void info(String msg, Throwable t) {
        output("info", msg, t);
    }

    @Override
    public boolean isWarnEnabled() {
        return clogToLevel(level) <= LocationAwareLogger.WARN_INT;
    }

    @Override
    public void warn(String msg) {
        output("warn", msg);
    }

    @Override
    public void warn(String format, Object arg) {
        output("warn", format, arg);
    }

    @Override
    public void warn(String format, Object... arguments) {
        output("warn", format, arguments);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        output("warn", format, arg1, arg2);
    }

    @Override
    public void warn(String msg, Throwable t) {
        output("warn", msg, t);
    }

    @Override
    public boolean isErrorEnabled() {
        return clogToLevel(level) <= LocationAwareLogger.ERROR_INT;
    }

    @Override
    public void error(String msg) {
        output("error", msg);
    }

    @Override
    public void error(String format, Object arg) {
        output("error", format, arg);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        output("error", format, arg1, arg2);
    }

    @Override
    public void error(String format, Object... arguments) {
        output("error", format, arguments);
    }

    @Override
    public void error(String msg, Throwable t) {
        output("error", msg, t);
    }


    /**
     * 打印日志
     * @param msg     消息
     * @param objects 参数
     */
    private synchronized void output(String level, String msg, Object... objects){
        long n = System.currentTimeMillis();
        if (n <= nextCheck) {
            now.setTime(n);
            nextCheck = rc.getNextCheckMillis(now);
            path = "";
        }

        if (StringUtils.isBlank(path)){
            getPath();
        }
        // 是否throwable
        boolean isThrowable = false;
        Throwable throwable = null;

        // 转消息
        if (objects != null && objects.length != 0){
            Object[] tempObject = objects.clone();
            if (tempObject[tempObject.length - 1] instanceof Throwable){
                isThrowable = true;
                throwable = (Throwable) tempObject[tempObject.length - 1];
            }
            msg = isThrowable ? MessageFormatter.arrayFormat(msg, tempObject).getMessage() : MessageFormatter.arrayFormat(msg, tempObject).getMessage();
        }

        // 是否异常信息
        if (isThrowable){
            for (StackTraceElement stackTraceElement : throwable.getStackTrace()){
                printLog(level, msg, stackTraceElement);
            }
        }
        printLog(level, msg, null);

    }

    /**
     * 打印日志
     * @param level              等级
     * @param msg                消息
     * @param stackTraceElements 日志
     */
    private void printLog(String level, String msg, StackTraceElement stackTraceElements){
        StackTraceElement[] elements = new Throwable().getStackTrace();
        StackTraceElement element;
        if (elements.length <= 4){
            element = elements[elements.length - 1];
        } else {
            element = elements[4];
        }
        if (stackTraceElements != null){
            element = stackTraceElements;
        }

        String classFileName = element.getFileName();
        String className = element.getClassName();
        if (StringUtils.isNotEmpty(instance.getName())){
            className = instance.getName();
        }

        // 日志消息体
        LogMessageModel model = new LogMessageModel(CodCommonUUID.getUUID(),
                classFileName, className,
                element.getMethodName(), element.getLineNumber(), level,
                CodCommonDate.getDate("yyyy-MM-dd HH:mm:ss,SSS"), msg);
        String log = CodCommonJson.dump(model);
        String fileName = "";
        if (isSplit){
            // 创建目录
            if (CodCommonIO.createPath(path + level)){
                fileName = level + "/" + CodCommonDate.getDate("yyyy-MM-dd") + ".log";
            }
        } else {
            fileName = level + "." + CodCommonDate.getDate("yyyy-MM-dd", now) + ".log";
        }

        // 是否能打印
        boolean isPrint;
        switch (level) {
            case "trace":
                isPrint = isTraceEnabled();
                break;
            case "debug":
                isPrint = isDebugEnabled();
                break;
            case "info":
                isPrint = isInfoEnabled();
                break;
            case "warn":
                isPrint = isWarnEnabled();
                break;
            case "error":
                isPrint = isErrorEnabled();
                break;
            default:
                isPrint = isInfoEnabled();
                break;
        }

        try {
            if (isPrint){
                CodCommonIO.outputFile(path, fileName, log+"\r\n", true);
            }
        } catch (IOException e) {
            System.err.println("打印日志失败");
        }
    }

    /**
     * 获取目录
     */
    private void getPath(){
        logHref = StringUtils.isBlank(logHref) ? "./" : logHref;
        dateHref = CodCommonDate.getDate(StringUtils.isNotBlank(dateHref) ? dateHref : "yyyy/MM/dd", now);
        // 分割路径
        String splitHref = isSplit ? "/" : "";
        CodLoggerAdapter.path = logHref + dateHref + splitHref;
    }

    /**
     * slf4j等级转clog等级
     * @param level
     * @return
     */
    private String levelToClog(int level) {
        String clogLevel;
        switch (level) {
            case LocationAwareLogger.TRACE_INT:
                clogLevel = "trace";
                break;
            case LocationAwareLogger.DEBUG_INT:
                clogLevel = "debug";
                break;
            case LocationAwareLogger.INFO_INT:
                clogLevel = "info";
                break;
            case LocationAwareLogger.WARN_INT:
                clogLevel = "warn";
                break;
            case LocationAwareLogger.ERROR_INT:
                clogLevel = "error";
                break;
            default:
                clogLevel = "info";
                break;
        }
        return clogLevel;
    }

    /**
     * clog等级转slf4j等级
     * @param level
     * @return
     */
    private int clogToLevel(String level) {
        int slf4jLevel;
        switch (level) {
            case "trace":
                slf4jLevel = LocationAwareLogger.TRACE_INT;
                break;
            case "debug":
                slf4jLevel = LocationAwareLogger.DEBUG_INT;
                break;
            case "info":
                slf4jLevel = LocationAwareLogger.INFO_INT;
                break;
            case "warn":
                slf4jLevel = LocationAwareLogger.WARN_INT;
                break;
            case "error":
                slf4jLevel = LocationAwareLogger.ERROR_INT;
                break;
            default:
                slf4jLevel = LocationAwareLogger.INFO_INT;
        }
        return slf4jLevel;
    }
}

