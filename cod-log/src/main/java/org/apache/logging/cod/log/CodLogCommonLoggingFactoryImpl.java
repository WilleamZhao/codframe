/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package org.apache.logging.cod.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;
import org.apache.commons.logging.LogFactory;
import org.slf4j.impl.CodLoggerAdapter;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodLogCommonLoggingFactoryImpl
 * @date 2019/8/23 3:13 AM
 */
public class CodLogCommonLoggingFactoryImpl extends LogFactory {

    private String type = "clog";

    @Override
    public Object getAttribute(String name) {
        return null;
    }

    @Override
    public String[] getAttributeNames() {
        return new String[0];
    }

    @Override
    public Log getInstance(Class clazz) throws LogConfigurationException {
        return new CodLoggerAdapter();
    }

    @Override
    public Log getInstance(String name) throws LogConfigurationException {
        return new CodLoggerAdapter(name);
    }

    @Override
    public void release() {

    }

    @Override
    public void removeAttribute(String name) {

    }

    @Override
    public void setAttribute(String name, Object value) {

    }
}
