/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.message.model.enums;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodMessageSmsSupportEnum
 * @date 2019/11/3 8:10 PM
 */
public enum CodMessageSmsSupportEnum {

    /**
     * 阿里云短信
     */
    ALI_SMS,

    /**
     * 腾讯云短信
     */
    TENCENT_SMS,

    /**
     * 云片云短信
     * http://www.yunpian.com
     */
    YUNPIAN_SMS,

    /**
     * 聚合短信
     * https://www.juhe.cn/
     */
    JUHE_SMS,

    /**
     * 赛邮云通信
     * https://www.mysubmail.com/
     */
    SAIDI_SMS;
}
