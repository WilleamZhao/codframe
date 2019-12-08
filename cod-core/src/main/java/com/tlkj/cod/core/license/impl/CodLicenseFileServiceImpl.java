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
import org.springframework.stereotype.Service;

/**
 * Desc 文件 license
 *
 * @author sourcod
 * @version 1.0
 * @className CodLicenseFileServiceImpl
 * @date 2019/10/21 12:52 PM
 */
@Service
public class CodLicenseFileServiceImpl implements CodLicenseService {

    @Override
    public String getSupportType() {
        return "codLicenseFile";
    }

    @Override
    public boolean verify() {
        return false;
    }
}
