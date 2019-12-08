/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.core.license.impl;

import com.tlkj.cod.core.license.CodLicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Desc 在线 license
 *
 * @author sourcod
 * @version 1.0
 * @className CodLicenseOnlineServiceImpl
 * @date 2019/10/21 12:51 PM
 */
@Service
public class CodLicenseOnlineServiceImpl implements CodLicenseService {

    @Autowired

    @Override
    public String getSupportType() {
        return "codLicenseOnline";
    }

    @Override
    public boolean verify() {
        return false;
    }
}
