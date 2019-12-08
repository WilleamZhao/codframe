/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.file.model.enums;

/**
 * Desc 文件名枚举
 *
 * @author sourcod
 * @version 1.0
 * @className CodFileNameEnum
 * @date 2019/11/7 7:51 PM
 */
public enum  CodFileNameEnum {

    /**
     * 年月日
     */
    YMD,

    /**
     * 年月日时分秒
     */
    YMDHMS,

    /**
     * 年月日时分秒毫秒
     */
    YMDHMSS,

    /**
     * 年月日+UUID
     */
    YMD_UUID,

    /**
     * 年月日时分秒+UUID
     */
    YMDHMS_UUID,

    /**
     * 年月日时分秒毫秒+UUID
     */
    YMDHMSS_UUID,

    /**
     * 年月日时分秒+随机数
     */
    YMDHMS_RANDOM,

    /**
     * 按年月日 分隔文件夹
     */
    YMD_SPLIT,

    /**
     * 按年月 分隔文件夹
     */
    YM_SPLIT,

    /**
     * 按年月日 分隔文件夹 文件名UUID
     */
    YMD_SPLIT_UUID;

}
