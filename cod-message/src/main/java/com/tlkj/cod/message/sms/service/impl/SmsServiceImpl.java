/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.message.sms.service.impl;

import com.tlkj.cod.message.model.enums.CodMessageSmsSupportEnum;
import com.tlkj.cod.message.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    List<SmsService> smsServices;

    /**
     * 默认调用阿里云短信
     * @return
     */
    @Override
    public String support() {
        return CodMessageSmsSupportEnum.ALI_SMS.name();
    }



    @Override
    public boolean dynamicSend(String nationcode, String phone, String content, String signName, String templateCode) {

        return false;
    }
}
