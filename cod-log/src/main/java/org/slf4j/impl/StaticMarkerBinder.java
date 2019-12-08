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
    private final IMarkerFactory markerFactory = new BasicMarkerFactory();

    private StaticMarkerBinder() {

    }

    @Override
    public IMarkerFactory getMarkerFactory() {
        return this.markerFactory;
    }

    @Override
    public String getMarkerFactoryClassStr() {
        return BasicMarkerFactory.class.getName();
    }
}
