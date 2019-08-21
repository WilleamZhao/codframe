/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.pay.service;

import com.alipay.api.domain.AlipayFundAuthOrderFreezeModel;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeCancelModel;
import com.alipay.api.domain.AlipayTradeCloseModel;
import com.alipay.api.domain.AlipayTradeCreateModel;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.domain.AlipayTradeOrderSettleModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;

/**
 * Desc 支付宝
 *
 * @author sourcod
 * @version 1.0
 * @className CodPayAlipayService
 * @date 2019/8/21 9:24 AM
 */
public interface CodPayAlipayService {
    /**
     * 统一收单交易退款查询
     * @param model
     */
    void tradeFastpayRefundQuery(AlipayTradeFastpayRefundQueryModel model);

    /**
     * 统一收单交易结算接口
     */
    void tradeOrderSettle(AlipayTradeOrderSettleModel model);

    /**
     * 统一收单交易关闭接口
     */
    void tradeClose(AlipayTradeCloseModel model);

    /**
     * 统一收单交易撤销接口
     */
    void tradeCancel(AlipayTradeCancelModel model);

    /**
     * 统一收单线下交易预创建
     */
    void tradePrecreate(AlipayTradePrecreateModel model);

    /**
     * 统一收单交易创建接口
     */
    void tradeCreate(AlipayTradeCreateModel model);

    /**
     * 统一收单交易支付接口
     */
    void tradePay(AlipayTradePayModel model);

    /**
     * 口碑商品交易查询接口
     */
    // void tradeOrderinfoSync(Alipay);

    /**
     * 口碑商品交易购买接口
     */

    /**
     * 口碑商品交易退货接口
     */

    /**
     * 码商发码成功回调接口
     */

    /**
     * 口碑凭证延期接口
     */

    /**
     * 口碑凭证码撤销核销
     */

    /**
     * 口碑凭证码查询
     */

    /**
     * 支付宝订单信息同步接口
     */

    /**
     * 口碑订单预下单
     */

    /**
     * 网商银行全渠道收单业务订单创建
     */

    /**
     * 统一收单交易退款接口
     */
    void tradeRefund(AlipayTradeRefundModel model);

    /**
     * 花呗先享会员结算申请
     */

    /**
     * 统一收单下单并支付页面接口
     */
    void tradePagePay(AlipayTradePagePayModel model);

    /**
     * 手机网站支付接口2.0
     */
    void tradeWapPay(AlipayTradeWapPayModel model);

    /**
     * app支付接口2.0
     */
    void tradeAppPay(AlipayTradeAppPayModel model);

    /**
     * 统一收单线下交易查询
     */
    void tradeQuery(AlipayTradeQueryModel model);

    /**
     * 资金授权冻结接口
     */
    void fundAuthOrderFreeze(AlipayFundAuthOrderFreezeModel model);
}
