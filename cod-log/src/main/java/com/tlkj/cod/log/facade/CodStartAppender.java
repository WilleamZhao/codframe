/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.log.facade;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodStartAppender
 * @date 2019/3/27 10:37 PM
 */
public class CodStartAppender extends AppenderSkeleton {

    // private Logger logService = LoggerFactory.getLogger(Log4jCodAppender.class);

    @Override
    protected void append(LoggingEvent event) {
        System.out.println(event.getMessage().toString());
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}
