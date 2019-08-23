/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.log.common;

import org.slf4j.spi.LocationAwareLogger;

/**
 * Desc 等级工具
 *
 * @author sourcod
 * @version 1.0
 * @className CodLogLevel
 * @date 2019/8/23 1:40 AM
 */
public class CodLogLevel {

    /**
     * slf4j等级转clog等级
     * @param level
     * @return
     */
    public static String levelToClog(int level) {
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
    public static int clogToLevel(String level) {
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
