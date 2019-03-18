/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.message.sms.service;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className TencentSmsService
 * @date 2019/1/31 2:46 PM
 */
public interface TencentSmsService {

    /**
     * 发送短信接口
     * @param nationcode 国家码
     * @param phone 手机号 支持以逗号分隔的形式进行批量调用,批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
     * @param code 码
     * @param signName 签名
     * @param templateCode 模板码
     */
    boolean send(String nationcode, String phone, String code, String signName, int templateCode);
}
