/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.log.facade;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodAppender
 * @date 2019/3/27 9:51 PM
 */
public class Log4jCodAppender extends AppenderSkeleton {

    private Logger logService = LoggerFactory.getLogger(Log4jCodAppender.class);

    @Override
    protected void append(LoggingEvent event) {
        switch (event.getLevel().toInt()){
            case Level.TRACE_INT:
                logService.trace(event.getMessage().toString());
                break;
            case Level.DEBUG_INT:
                logService.debug(event.getMessage().toString());
                break;
            case Level.INFO_INT:
                logService.info(event.getMessage().toString());
                break;
            case Level.ERROR_INT:
                logService.error(event.getMessage().toString());
                break;
            case Level.FATAL_INT:
                logService.error(event.getMessage().toString());
                break;
            case Level.OFF_INT:
                logService.error(event.getMessage().toString());
                break;
            case Level.ALL_INT:
                logService.trace(event.getMessage().toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}
