/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.message.sms.service.impl;

import com.tlkj.cod.message.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Desc 短信服务
 *
 * @author sourcod
 * @version 1.0
 * @className SmsServiceImpl
 * @date 2019/2/2 12:53 PM
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    AliSmsServiceImpl aliSmsService;

    @Override
    public boolean dynamicSend(String nationcode, String phone, String content, String signName, String templateCode) {

        return false;
    }
}
