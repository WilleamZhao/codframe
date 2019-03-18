/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.common;

import java.util.UUID;

/**
 * Desc UUID工具类
 *
 * @author sourcod
 * @version 1.0
 * @className UUIDUtil
 * @date 2018/12/3 4:37 PM
 */
public class CodCommonUUID {

    /**
     * 获取UUID 默认去掉- 32位
     * @return UUID
     */
    public static String getUUID(){
        return getUUID(0);
    }

    /**
     * 获取UUID
     * 按传入参数格式化
     * @param s 格式化字符
     * @return UUID
     */
    public static String getUUID(String s){
        return getUUID(0, s);
    }

    /**
     * 获取UUID
     * @param num 长度
     * @return UUID
     */
    public static String getUUID(int num){
        return getUUID(num, "");
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
