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

import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.spi.MarkerFactoryBinder;

/**
 * Desc Marker
 *
 * @author sourcod
 * @version 1.0
 * @className StaticMarkerBinder
 * @date 2018/12/4 9:03 PM
 */
public class StaticMarkerBinder implements MarkerFactoryBinder {

    public static final StaticMarkerBinder SINGLETON = new StaticMarkerBinder();

    final IMarkerFactory markerFactory = new BasicMarkerFactory();

    private StaticMarkerBinder() {

    }

    /**
     * Return the singleton of this class.
     *
     * @return the StaticMarkerBinder singleton
     * @since 1.7.14
     */
    public static StaticMarkerBinder getSingleton() {
        return SINGLETON;
    }

    @Override
    public IMarkerFactory getMarkerFactory() {
        return markerFactory;
    }

    @Override
    public String getMarkerFactoryClassStr() {
        return BasicMarkerFactory.class.getName();
    }
}
