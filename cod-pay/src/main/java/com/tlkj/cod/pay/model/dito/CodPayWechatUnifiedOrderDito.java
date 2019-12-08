/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.pay.model.dito;

import com.tlkj.cod.pay.model.dto.CodPayWechatUnifiedOrderDto;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Desc 统一下单响应 Model
 *
 * @author sourcod
 * @version 1.0
 * @className UnifiedOrderResponseModel
 * @date 2019/5/21 2:14 PM
 */
@Getter
@Setter
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class CodPayWechatUnifiedOrderDito {

    /**
     * 返回状态码
     * 必填
     */
    private String return_code;

    /**
     * 返回信息
     */
    private String return_msg;

    /*
     * 以下字段在return_code为SUCCESS的时候有返回
     */

    /**
     * 应用APPID
     * 必填
     */
    private String appid;

    /**
     * 商户号
     * 必填
     */
    private String mch_id;

    /**
     * 设备号
     */
    private String device_info;

    /**
     * 随机字符串
     * 必填
     */
    private String nonce_str;

    /**
     * 签名
     * 必填
     * 微信返回的签名
     */
    private String sign;

    /**
     * 业务结果
     * 必填
     * SUCCESS/FAIL
     */
    private String result_code;

    /**
     * 错误代码
     */
    private String err_code;

    /**
     * 错误代码描述
     * 错误返回的信息描述
     */
    private String err_code_des;

    /*
     * 以下字段在return_code 和result_code都为SUCCESS的时候有返回
     */

    /**
     * 交易类型
     * 必填
     * 用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP
     */
    private String trade_type;

    /**
     * 预支付交易会话标识
     * 必填
     */
    private String prepay_id;

    public CodPayWechatUnifiedOrderDto toCall(){
        CodPayWechatUnifiedOrderDto callPayModel = new CodPayWechatUnifiedOrderDto();
        callPayModel.setAppid(this.getAppid());
        callPayModel.setPrepayid(this.getPrepay_id());
        callPayModel.setPartnerid(this.getMch_id());
        callPayModel.setSign(callPayModel.createSign());
        return callPayModel;
    }

}
