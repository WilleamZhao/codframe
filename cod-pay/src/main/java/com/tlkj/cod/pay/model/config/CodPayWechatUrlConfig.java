/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.pay.model.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Desc 微信请求地址
 *
 * @author sourcod
 * @version 1.0
 * @className WechatRequestUrlConstant
 * @date 2019/5/21 3:03 PM
 */
@Component
public class CodPayWechatUrlConfig {

    /**
     * 统一下单
     */
    // @Value(value = "${cod.pay.config.wechat.unified:https://api.mch.weixin.qq.com/pay/unifiedorder}")
    public static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 订单查询
     */
    public static final String ORDER_QUERY = "https://api.mch.weixin.qq.com/pay/orderquery";

    /**
     * 订单关闭
     */
    public static final String ORDER_CLOSE = "https://api.mch.weixin.qq.com/pay/closeorder";

    /**
     * 申请退款
     */
    public static final String PAY_REFUND = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    /**
     * 查询退款
     */
    public static final String PAY_REFUND_QUERY = "https://api.mch.weixin.qq.com/pay/refundquery";

    /**
     * 下载对账单
     */
    public static final String PAY_DOWNLOAD_BILL = "https://api.mch.weixin.qq.com/pay/downloadbill";

    /**
     * 下载资金账单
     * 请求需要双向证书。 详见证书使用
     * https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=4_3
     */
    public static final String PAY_DOWNLOAD_FUND_FLOW = "https://api.mch.weixin.qq.com/pay/downloadfundflow";


    /**
     * 测试上报/交易保障
     * 中国国内
     * 注：商户可根据实际请求情况选择最优域名进行访问，建议在接入时做好兼容，当访问其中一个域名出现异常时，可自动切换为其他域名。
     */
    public static final String PAY_ITIL_REPORT = "https://api.mch.weixin.qq.com/payitil/report";

    /**
     * 东南亚
     */
    public static final String PAY_ITIL_HK_REPORT = "https://apihk.mch.weixin.qq.com/payitil/report";

    /**
     * 其它
     */
    public static final String PAY_ITIL_US_REPORT = "https://apius.mch.weixin.qq.com/payitil/report";

    /**
     * 汇率查询
     */
    public static final String EXCHAGERATE_QUERY = "https://api.mch.weixin.qq.com/pay/queryexchagerate";

    /**
     * 拉取订单评价数据
     */
    public static final String COMMENT_QUERY_BATCH = "https://api.mch.weixin.qq.com/billcommentsp/batchquerycomment";

    /**
     * 转换短链接
     * 该接口主要用于Native支付模式一中的二维码链接转成短链接(weixin://wxpay/s/XXXXXX)，减小二维码数据量，提升扫描速度和精确度。
     */
    public static final String SHORT_URL = "https://api.mch.weixin.qq.com/tools/shorturl";

    /**
     * 授权码查询openid
     * 通过授权码查询公众号Openid，调用查询后，该授权码只能由此商户号发起扣款，直至授权码更新。
     */
    public static final String AUTHCODE_TO_OPENID = "https://api.mch.weixin.qq.com/tools/authcodetoopenid";


}
