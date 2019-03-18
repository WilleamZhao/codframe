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

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/**
 * Desc slf4j-cod-log factory
 *
 * @author sourcod
 * @version 1.0
 * @className CodLoggerFactory
 * @date 2018/12/4 8:16 PM
 */
public class CodLoggerFactory implements ILoggerFactory {

    public CodLoggerFactory() {

    }

    @Override
    public Logger getLogger(String name) {
        return CodLoggerAdapter.getInstance();
    }
}
