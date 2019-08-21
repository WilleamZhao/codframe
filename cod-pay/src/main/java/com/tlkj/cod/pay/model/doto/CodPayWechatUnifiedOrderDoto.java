/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.pay.model.doto;

import com.tlkj.cod.common.CodCommonEncryption;
import com.tlkj.cod.http.model.CodHttpRequestParamsModelBase;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Desc 统一下单请求 Model
 *
 * @author sourcod
 * @version 1.0
 * @className UnifiedOrderRequestModel
 * @date 2019/5/21 2:14 PM
 */

@Getter
@Setter
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class CodPayWechatUnifiedOrderDoto extends CodHttpRequestParamsModelBase implements Serializable {

    private static final long serialVersionUID = 6884799010888567813L;

    /**
     * 应用ID
     * 必填
     * 信开放平台审核通过的应用APPID（请登录open.weixin.qq.com查看，注意与公众号的APPID不同）
     */
    // @XmlElement(name = "appid")
    private String appid = "wxef2fe5e209cc4196";

    /**
     * 商户号
     * 必填
     * 微信支付分配的商户号
     */
    private String mch_id = "1540424741";

    /**
     * 设备号
     * 终端设备号(门店号或收银设备ID)，默认请传"WEB"
     */
    private String device_info = "WEB";

    /**
     * 随机字符串
     * 必填
     * 随机字符串，不长于32位。推荐随机数生成算法
     * https://pay.weixin.qq.com/wiki/doc/api/app/app_jw.php?chapter=4_3
     */
    private String nonce_str;

    /**
     * 签名
     * 必填
     * 签名，详见签名生成算法
     * https://pay.weixin.qq.com/wiki/doc/api/app/app_jw.php?chapter=4_3
     */
    private String sign;

    /**
     * 签名类型
     * 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
     */
    private String sign_type;

    /**
     * 商品描述
     * 必填
     *
     * 商品描述交易字段格式根据不同的应用场景按照以下格式：
     *
     * APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。
     */
    private String body;

    /**
     * 商品详情
     * 商品详细描述，对于使用单品优惠的商户，改字段必须按照规范上传
     * https://pay.weixin.qq.com/wiki/doc/api/danpin.php?chapter=9_102&index=2
     */
    private String detail;

    /**
     * 附加数据
     * 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
     */
    private String attach;

    /**
     * 商户订单号
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一。
     * 必填
     */
    private String out_trade_no;

    /**
     * 货币类型
     * 符合ISO 4217标准的三位字母代码，默认人民币：CNY
     * https://pay.weixin.qq.com/wiki/doc/api/app/app_jw.php?chapter=4_2
     */
    private String fee_type;

    /**
     * 总金额
     * 订单总金额，单位为分，
     * https://pay.weixin.qq.com/wiki/doc/api/app/app_jw.php?chapter=4_2
     * 必填
     */
    private int total_fee;

    /**
     * 终端IP
     * 支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
     * 必填
     */
    private String spbill_create_ip;

    /**
     * 交易起始时间
     * 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。
     */
    private String time_start;

    /**
     * 交易结束时间
     * 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。
     * 订单失效时间是针对订单号而言的，由于在请求支付的时候有一个必传参数prepay_id只有两小时的有效期，所以在重入时间超过2小时的时候需要重新请求下单接口获取新的prepay_id
     */
    private String time_expire;

    /**
     * 订单优惠标记
     * 订单优惠标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠
     * https://pay.weixin.qq.com/wiki/doc/api/tools/sp_coupon.php?chapter=12_1
     */
    private String goods_tag;

    /**
     * 通知地址
     * 必填
     * 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
     */
    private String notify_url;

    /**
     * 交易类型
     * 必填
     * 支付类型
     * APP
     */
    private String trade_type;

    /**
     * 指定支付方式
     * no_credit--指定不能使用信用卡支付
     */
    private String limit_pay;

    /**
     * 开发票入口开放标识
     * Y，传入Y时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效
     */
    private String receipt;

    /**
     * 场景信息
     * H5 支付 必填
     * 该字段用于上报支付的场景信息,针对H5支付有以下三种场景,请根据对应场景上报, H5支付不建议在APP端使用，针对场景1，2请接入APP支付，不然可能会出现兼容性问题
     * {"h5_info": {"type":"Wap","wap_url": "https://pay.qq.com","wap_name": "腾讯充值"}}
     * {"h5_info": //h5支付固定传"h5_info"
     *    {"type": "",  //场景类型
     *     "wap_url": "",//WAP网站URL地址
     *     "wap_name": ""  //WAP 网站名
     *     }
     * }
     */
    private String scene_info;

    /*public String getOrder() {
        if (StringUtils.isNotBlank(this.out_trade_no)){
            this.out_trade_no = DateUtils.getDate("yyyyMMddHHmmsss") + UUIDUtil.getUUID();
        }
        return out_trade_no;
    }*/

    /**
     * 获取sign
     * @return
     */
    public String createSign(){
        String sign = "";
        // Map map = new BeanMap(this);
        Map map = new HashMap();
        try{
            BeanInfo beanInfo = Introspector.getBeanInfo(this.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class") && !key.equals("params")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(this);
                    map.put(key, value);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        List<Map.Entry<String, Object>> infoIds = new ArrayList<Map.Entry<String, Object>>(map.entrySet());
        // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
        infoIds.sort(Comparator.comparing(o -> (o.getKey())));
        // 构造签名键值对的格式
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> item : infoIds) {
            if (StringUtils.isNotBlank(item.getKey())) {
                String key = item.getKey();
                System.out.println(key);
                Object val = item.getValue();
                if (val != null && !"".equals(val)) {
                    sb.append(key).append("=").append(val).append("&");
                }
            }
        }
        sign = sb.toString();
        sign = sign + "&key=glssdzJH20fdsf23419bhsdfa3558e27";
        //进行MD5加密
        sign = CodCommonEncryption.EncoderByMd5(sign);
        return sign;
    }

    public static void main(String[] args) {
        CodPayWechatUnifiedOrderDoto unifiedOrderRequestModel = new CodPayWechatUnifiedOrderDoto();
        unifiedOrderRequestModel.setAppid("sd");
        unifiedOrderRequestModel.setMch_id("af");
        unifiedOrderRequestModel.setBody("af");
        System.out.println(unifiedOrderRequestModel.createSign());
    }

}
