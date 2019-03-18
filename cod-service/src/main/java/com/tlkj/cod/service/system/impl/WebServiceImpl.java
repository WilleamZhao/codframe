/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.system.impl;

import com.tlkj.cod.service.system.SystemSetService;
import com.tlkj.cod.service.system.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className WebServiceImpl
 * @date 2018/12/21 10:00 AM
 */
@Service
public class WebServiceImpl implements WebService {

    @Autowired
    private SystemSetService systemSetService;

    @Override
    public String getFrontUrl() {

        return null;
    }
}
