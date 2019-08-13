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

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.tlkj.cod.log.annotation.CodLog;
import com.tlkj.cod.message.model.config.AliYunSmsConfig;
import com.tlkj.cod.message.sms.service.AliSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Desc 阿里短信服务
 *
 * @author sourcod
 * @version 1.0
 * @className AliSmsServiceImpl
 * @date 2019/1/29 9:14 PM
 */
@Service
public class AliSmsServiceImpl implements AliSmsService {

    @Autowired
    private AliYunSmsConfig aliYunSmsConfig;

    /**
     * 短信API产品名称（短信产品名固定，无需修改）
     */
    private final static String product = "Dysmsapi";

    /**
     * 短信API产品域名（接口地址固定，无需修改）
     */
    private final static String domain = "dysmsapi.aliyuncs.com";

    /**
     * 发送短信消息
     * @param phone 手机号 支持以逗号分隔的形式进行批量调用,批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
     * @param code 码
     * @param signName 签名
     * @param templateCode 模板码
     * @return
     */
    @Override
    @CodLog(name = "发送短信")
    public boolean send(String phone, String code, String signName, String templateCode) {
        // systemSetService.getLog().info("发送短信[阿里云], 电话号={}, 验证码={}", phone, code);

        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliYunSmsConfig.getAccessKeyId(), aliYunSmsConfig.getAccessKeySecret());
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            // systemSetService.getLog().error("调用失败", e.getMessage());
            return false;
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用, 批量上限为1000个手机号码, 批量调用相对于单条调用及时性稍有延迟, 验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId(aliYunSmsConfig.getOutId());
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            // systemSetService.getLog().error("调用失败: {}", e.getMessage());
            return false;
        }

        if (sendSmsResponse != null && sendSmsResponse.getCode() != null && "OK".equals(sendSmsResponse.getCode())) {
            //请求成功
            System.out.println("请求成功");
            // systemSetService.getLog().info("短信发送成功");
            return true;
        }

        /*
         * TODO 记录错误类型
         */
        return false;
    }

    @Override
    public boolean dynamicSend(String nationcode, String phone, String content, String signName, String templateCode) {
        // TODO this.send()
        return false;
    }
}
