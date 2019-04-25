/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.jx.impl;

import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.model.common.SystemResponse;
import com.tlkj.cod.service.jx.JxOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className JxOrderServiceImpl
 * @date 2019/3/26 11:50 AM
 */
@Service
public class JxOrderServiceImpl implements JxOrderService {

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    @Override
    public SystemResponse save() {
        return null;
    }

    @Override
    public SystemResponse list() {
        return null;
    }

    @Override
    public SystemResponse get() {
        return null;
    }

    @Override
    public SystemResponse delete() {
        return null;
    }
}
