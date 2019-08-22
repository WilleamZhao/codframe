/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.pay.common;

import com.tlkj.cod.common.CodCommonEncryption;
import httl.util.StringUtils;
import org.apache.commons.collections.map.HashedMap;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Desc 支付通用 sign
 *
 * @author sourcod
 * @version 1.0
 * @className CodPayCommonSign
 * @date 2019/8/21 11:18 AM
 */
public class CodPayCommonSign {

    public static String createWechatSign(Map<String, Object> map, String key){
        List<Map.Entry<String, Object>> infoIds = new ArrayList<>(map.entrySet());
        // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
        infoIds.sort(Comparator.comparing(o -> (o.getKey())));
        // 构造签名键值对的格式
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> item : infoIds) {
            if (StringUtils.isNotBlank(item.getKey())) {
                String mapKey = item.getKey();
                System.out.println(mapKey);
                Object val = item.getValue();
                if (val != null && !"".equals(val)) {
                    // sign 不参与
                    if ("sign".equals(mapKey)){
                        continue;
                    }
                    if (val instanceof String){
                        try {
                            val = new String(((String) val).getBytes(), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            System.out.println("转码错误");
                            e.printStackTrace();
                        }
                    }
                    sb.append(mapKey).append("=").append(val).append("&");
                }
            }
        }
        sb.append("key=").append(key);
        String sign = sb.toString();
        //进行MD5加密
        sign = CodCommonEncryption.EncoderByMd5(sign).toUpperCase();
        return sign;
    }

    public static void main(String[] args) {
        CodPayCommonSign codPayCommonSign = new CodPayCommonSign();
        Map map = new HashedMap();
        map.put("a", "a");
        String sign = codPayCommonSign.createWechatSign(map, "glssdzJH20fdsf23419bhsdfa3558e27");
        System.out.println(sign);
    }
}
