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
 * Desc usb key ( 加密狗 ) 加密 license
 *
 * @author sourcod
 * @version 1.0
 * @className CodLicenseUsbKeyServiceImpl
 * @date 2019/10/21 12:59 PM
 */
@Service
public class CodLicenseUsbKeyServiceImpl implements CodLicenseService {

    @Override
    public String getSupportType() {
        return "codLicenseUsbKey";
    }

    @Override
    public boolean verify() {
        return false;
    }
}
