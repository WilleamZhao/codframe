/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.service.impl;

import com.tlkj.cod.admin.service.CodAdminSystemSetService;
import com.tlkj.cod.admin.service.CodAdminWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminWebServiceImpl
 * @date 2018/12/21 10:00 AM
 */
@Service
public class CodAdminWebServiceImpl implements CodAdminWebService {

    @Autowired
    private CodAdminSystemSetService codAdminSystemSetService;

    @Override
    public String getFrontUrl() {

        return null;
    }
}
