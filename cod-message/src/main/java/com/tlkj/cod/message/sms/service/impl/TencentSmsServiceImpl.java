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

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.tlkj.cod.message.sms.service.TencentSmsService;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Desc 腾讯短信服务
 *
 * @author sourcod
 * @version 1.0
 * @className TencentSmsServiceImpl
 * @date 2019/1/31 1:01 PM
 */
@Service
public class TencentSmsServiceImpl implements TencentSmsService {

    @Override
    public boolean send(String nationcode, String phone, String code, String signName, int templateCode) {

        // 短信应用SDK AppID
        // 1400开头
        int appid = 1400009099;
        // 短信应用SDK AppKey
        String appkey = "9ff91d87c2cd7cd0ea762f141975d1df37481d48700d70ac37470aefc60f9bad";
        // 需要发送短信的手机号码
        String[] phoneNumbers = {"21212313123", "12345678902", "12345678903"};
        // 短信模板ID，需要在短信应用中申请
        // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
        int templateId = 7839;
        // 签名
        // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`
        String smsSign = "腾讯云";

        try {
            String[] params = {"5678"};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            // 签名参数未提供或者为空时，会使用默认签名发送短信
            SmsSingleSenderResult result = ssender.sendWithParam("86", phone, templateCode, params, signName, "", "");
            System.out.println(result);
        } catch (HTTPException | JSONException | IOException e) {
            // HTTP响应码错误
            e.printStackTrace();
            return false;
        }

        return false;
    }
}
