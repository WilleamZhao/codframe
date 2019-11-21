/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package org.slf4j.impl;

import com.tlkj.cod.common.CodCommonAnsiPrint;
import com.tlkj.cod.common.constant.CodCommonAnsiConstant;
import com.tlkj.cod.log.common.CodLogLevel;
import com.tlkj.cod.log.service.model.CodLogMessageModel;
import com.tlkj.cod.model.system.core.SystemModel;
import com.tlkj.cod.model.system.core.SystemSetLog;
import com.tlkj.cod.common.CodCommonDate;
import com.tlkj.cod.common.CodCommonIO;
import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.common.CodCommonUUID;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.slf4j.Marker;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * Desc slf4j-cod-log adapter
 * commons logging to cod-log
 *
 * @author sourcod
 * @version 1.0
 * @className CodLoggerAdapter
 * @date 2018/12/4 8:42 PM
 */
public final class CodLoggerAdapter extends MarkerIgnoringBase implements LocationAwareLogger, Log, Serializable {

    private static final long serialVersionUID = -6560271005436328378L;

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
    private static String logHref = systemSetLog != null && StringUtils.isNotBlank(systemSetLog.getHref()) ? systemSetLog.getHref() : "./.cod-temp/codLog/";

    /**
     * 日期路径
     */
    private static String dateHref = systemSetLog != null && StringUtils.isNotBlank(systemSetLog.getPattern()) ? systemSetLog.getPattern() : "yyyy/MM/dd";

    /**
     * 等级
     */
    private volatile static String level = systemSetLog != null && StringUtils.isNotBlank(systemSetLog.getLevel()) ? systemSetLog.getLevel() : "info";

    private volatile static boolean isConsole = (systemSetLog != null && systemSetLog.isConsole()) && systemSetLog.isConsole();

    private static volatile CodLoggerAdapter instance;

    private static boolean isAnsi = true;

    public CodLoggerAdapter() {

    }

    public CodLoggerAdapter(String name){
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
        String clogLevel = CodLogLevel.levelToClog(level);
        output(clogLevel, message, argArray, t);
    }

    @Override
    public boolean isTraceEnabled() {
        return CodLogLevel.clogToLevel(level) <= LocationAwareLogger.TRACE_INT;
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

    /**
     * 打印日志
     * @param msg     消息
     * @param objects 参数
     */
    private synchronized void output(String level, Object msg, Object... objects){
        output(level, msg.toString(), objects);
    }

    @Override
    public boolean isDebugEnabled() {
        return CodLogLevel.clogToLevel(level) <= LocationAwareLogger.DEBUG_INT;
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
        return CodLogLevel.clogToLevel(level) <= LocationAwareLogger.INFO_INT;
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
        return CodLogLevel.clogToLevel(level) <= LocationAwareLogger.WARN_INT;
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
        return CodLogLevel.clogToLevel(level) <= LocationAwareLogger.ERROR_INT;
    }

    @Override
    public boolean isFatalEnabled() {
        return false;
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
     * common-logging
     */
    @Override
    public void debug(Object message) {
        output("debug", message);
    }

    @Override
    public void debug(Object message, Throwable t) {
        output("debug", message, t);
    }

    @Override
    public void error(Object message) {
        output("error", message);
    }

    @Override
    public void error(Object message, Throwable t) {
        output("error", message, t);
    }

    @Override
    public void fatal(Object message) {
        output("fatal", message);
    }

    @Override
    public void fatal(Object message, Throwable t) {
        output("fatal", message, t);
    }

    @Override
    public void info(Object message) {
        output("info", message);
    }

    @Override
    public void info(Object message, Throwable t) {
        output("info", message, t);
    }


    @Override
    public void trace(Object message) {
        output("trace", message);
    }

    @Override
    public void trace(Object message, Throwable t) {
        output("trace", message, t);

    }

    @Override
    public void warn(Object message) {
        output("warn", message);

    }

    @Override
    public void warn(Object message, Throwable t) {
        output("warn", message, t);
    }


    /**
     * 打印日志
     * @param msg     消息
     * @param objects 参数
     */
    private synchronized void output(String level, String msg, Object... objects){
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

        if (!isPrint){
            return;
        }
        StackTraceElement[] elements = new Throwable().getStackTrace();

        // 取调用者
        StackTraceElement element = null;
        int i = 0;
        for (StackTraceElement traceElement : elements){
            if (i >= 2 && !traceElement.getClassName().startsWith("com.tlkj.cod.log") && !traceElement.getClassName().startsWith("org.slf4j.impl")){
                element = traceElement;
                break;
            }
            i++;
        }

        boolean isThrowable = false;
        Throwable throwable = null;
        // 是否打印
        if (isConsole){
            // 源数组
            Object[] tempObject = objects.clone();

            if (objects.length != 0){
                if (isErrorEnabled()){
                    // 判断最后一个是不是异常信息
                    // 如果是打印异常信息
                    if (objects[objects.length - 1] instanceof Throwable){
                        // 截取后数组
                        tempObject = tempObject.length == 1 ? tempObject : Arrays.copyOf(objects, tempObject.length-1);
                        isThrowable = true;
                        throwable = (Throwable) tempObject[tempObject.length - 1];
                        // 打印异常信息
                        if (throwable != null){
                            throwable.printStackTrace();
                        }
                    }
                }
            }
            msg = MessageFormatter.arrayFormat(msg, tempObject).getMessage();
            // 日志消息体
            String log = getMessage(element, level, msg);
            if (isAnsi){
                log = CodCommonAnsiPrint.toString(CodCommonAnsiConstant.BLUE, log);
            }
            System.out.println(log);
        }

        long n = System.currentTimeMillis();
        if (n <= nextCheck) {
            now.setTime(n);
            nextCheck = rc.getNextCheckMillis(now);
            path = "";
        }

        if (StringUtils.isBlank(path)){
            getPath();
        }


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
        CodLogMessageModel model = new CodLogMessageModel(CodCommonUUID.getUUID(),
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

        try {
            CodCommonIO.outputFile(path, fileName, log+"\r\n", true);
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

    private String getMessage(StackTraceElement element, String level, String msg){
        CodLogMessageModel model = new CodLogMessageModel();
        model.setId(CodCommonUUID.getUUID());
        model.setFileName(element == null ? "" : element.getFileName());
        model.setClassName(element == null ? "" :element.getClassName());
        model.setMethodName(element == null ? "" :element.getMethodName());
        model.setLine(element == null ? 0 :element.getLineNumber());
        model.setLevel(level);
        model.setTime(CodCommonDate.getDate("yyyy-MM-dd HH:mm:ss,SSS"));
        model.setMsg(msg);
        String log = CodCommonJson.dump(model);
        if (isAnsi){
            log = CodCommonAnsiPrint.toString(CodCommonAnsiConstant.CYAN, log);
        }
        return log;
    }


}

