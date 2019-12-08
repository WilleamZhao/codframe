/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.pay.common;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Desc 支付通用工具
 *
 * @author sourcod
 * @version 1.0
 * @className CodPayCommonDate
 * @date 2019/6/29 2:31 PM
 */
public class CodPayCommonDate {

    /**
     * 微信支付获取东八区 1970年01月01日00时00分00秒 到现在秒数
     * @return
     */
    public static long getWechatTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        Date datetime = new Date();
        calendar.setTime(datetime);
        TimeZone timeZone = TimeZone.getTimeZone("GMT+08:00");
        calendar.setTimeZone(timeZone);
        return calendar.getTimeInMillis()/1000;
    }

    /**
     * 获取随机字符串 UUID
     * @return UUID
     */
    public static String getNonceStr(){
        return getUUID(0, "");
    }

    /**
     * 获取UUID
     * 先格式化
     * 然后在截取长度
     * @param num 长度
     * @param s   格式化字符
     * @return UUID
     */
    public static String getUUID(int num, String s){
        String uuid = UUID.randomUUID().toString();
        if (num > 36){
            int a = (int)Math.ceil(num/36);
            StringBuilder uuidBuilder = new StringBuilder(uuid);
            while (a >=0 ){
                uuidBuilder.append(UUID.randomUUID().toString());
                a = a-1;
            }
            uuid = uuidBuilder.toString();
        }
        uuid = uuid.replaceAll("-", s);
        if (num != 0){
            uuid = uuid.substring(0, num);
        }
        return uuid;
    }
}
