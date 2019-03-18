/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.message.sms;

/**
 * Desc 短信服务接口
 *
 * @author sourcod
 * @version 1.0
 * @className SmsService
 * @date 2019/1/31 3:20 PM
 */
public interface SmsService {

    /**
     * 动态选择服务商发送短信
     * @param nationcode   国家码
     * @param phone        电话号
     * @param content      内容
     * @param signName     前面
     * @param templateCode 模版
     * @return 发送状态
     */
    boolean dynamicSend(String nationcode, String phone, String content, String signName, String templateCode);
}
