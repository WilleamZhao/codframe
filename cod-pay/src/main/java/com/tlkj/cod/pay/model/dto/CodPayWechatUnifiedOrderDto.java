/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.pay.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tlkj.cod.common.CodCommonEncryption;
import com.tlkj.cod.pay.common.CodPayCommonDate;
import com.tlkj.cod.pay.common.CodPayCommonSign;
import httl.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Desc 统一下的返回 Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodPayWechatUnifiedOrderDto
 * @date 2019/8/21 11:00 AM
 */
@Getter
@Setter
public class CodPayWechatUnifiedOrderDto {
    /**
     * appid
     */
    private String appid;

    /**
     * 商户号
     */
    private String partnerid;

    /**
     * 预支付交易会话ID
     */
    private String prepayid;

    /**
     * 扩展字段
     */
    @JsonProperty("package")
    private String commitPackage = "Sign=WXPay";

    /**
     * 随机字符串
     */
    private String noncestr = CodPayCommonDate.getNonceStr();

    /**
     * 时间戳
     */
    private long timestamp = CodPayCommonDate.getWechatTime();

    /**
     * 签名
     */
    private String sign;

    /**
     * 获取sign
     * @return
     */
    public String createSign(){
        String sign = "";
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(this.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class") && !key.equals("params")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(this);
                    if (value != null && !"".equals(value)){
                        if ("commitPackage".equals(key)){
                            map.put("package", value);
                        } else {
                            map.put(key, value);
                        }
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        sign = CodPayCommonSign.createWechatSign(map, "glssdzJH20fdsf23419bhsdfa3558e27");
        return sign;
    }

}
