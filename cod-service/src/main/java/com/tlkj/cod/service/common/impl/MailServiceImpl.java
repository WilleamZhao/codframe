/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.common.impl;

import com.tlkj.cod.service.common.MailService;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Desc 邮件ServiceImpl
 *
 * @author sourcod
 * @version 1.0
 * @className MailServiceImpl
 * @date 2018/12/25 3:43 PM
 */
@Service
public class MailServiceImpl implements MailService {



    /**
     * 发送邮件
     */
    @Override
    public void send(String subject, String content, String toAddress, String ccAddress, String bccAddress, File[] attachment) {

    }

}
