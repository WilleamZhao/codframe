/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.common;

import java.io.File;

/**
 * Desc 邮件Service
 *
 * @author sourcod
 * @version 1.0
 * @className MailService
 * @date 2018/12/25 3:42 PM
 */
public interface MailService {

    /**
     * 发送邮件
     * @param subject    邮件主题
     * @param content    邮件内容
     * @param toAddress  收件人
     * @param ccAddress  抄送人
     * @param bccAddress 密送人
     * @param attachment 附件
     */
    void send(String subject, String content, String toAddress, String ccAddress, String bccAddress, File[] attachment);


}
