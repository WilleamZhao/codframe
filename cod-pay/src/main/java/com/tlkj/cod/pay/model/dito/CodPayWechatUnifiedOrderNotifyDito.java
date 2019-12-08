/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.pay.model.dito;

import lombok.Getter;
import lombok.Setter;

/**
 * Desc 统一下单通知 Model
 *
 * @author sourcod
 * @version 1.0
 * @className UnifiedOrderNotifyResponseModel
 * @date 2019/6/2 8:46 PM
 */
@Getter
@Setter
public class CodPayWechatUnifiedOrderNotifyDito {

    /**
     * 返回状态码
     */
    private String return_code;

    /**
     * 返回信息
     */
    private String return_msg;

    /**
     * 应用ID
     */
    private String appid;

    /**
     * 商家数据包
     */
    private String attach;

    /**
     * 付款银行
     */
    private String bank_type;

    /**
     * 货币种类
     * CNY
     */
    private String fee_type;

    /**
     * 是否关注公众账号
     */
    private String is_subscribe;

    /**
     * 商户号
     */
    private String mch_id;

    /**
     * 随机字符串
     */
    private String nonce_str;

    /**
     * 用户标识
     */
    private String openid;

    /**
     * 商户订单号
     */
    private String out_trade_no;

    /**
     * 业务结果
     * SUCCESS/FAIL
     */
    private String result_code;

    /**
     * 签名
     */
    private String sign;

    /**
     * 代理商户号
     */
    private String sub_mch_id;

    /**
     * 支付完成时间
     */
    private String time_end;

    /**
     * 总金额
     * 订单总金额，单位为分
     */
    private String total_fee;

    /**
     * 现金支付金额
     * 现金支付金额订单现金支付金额
     */
    private int cash_fee;

    /**
     * 现金支付货币类型
     * CNY
     */
    private String cash_fee_type;

    /**
     * 代金券金额
     */
    private String coupon_fee;

    /**
     * 单个代金券支付金额
     * 单个代金券或立减优惠支付金额,$n为下标，从0开始编号
     */
    private String coupon_fee_0;

    /**
     * 代金券类型
     */
    private String coupon_type;

    /**
     * 代金券ID
     */
    private String coupon_id;

    /**
     * 单个代金券ID
     * coupon_id_$n
     * 代金券或立减优惠ID,$n为下标，从0开始编号
     */
    private String coupon_id_0;


    /**
     * 代金券使用数量
     */
    private String coupon_count;

    /**
     * 交易类型
     */
    private String trade_type;

    /**
     * 微信支付订单号
     */
    private String transaction_id;

    /**
     * 错误代码
     */
    private String err_code;

    /**
     * 错误代码描述
     */
    private String err_code_des;
}
