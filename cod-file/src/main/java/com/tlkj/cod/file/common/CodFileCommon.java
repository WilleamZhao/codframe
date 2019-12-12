/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.file.common;

import com.tlkj.cod.common.CodCommonDate;
import com.tlkj.cod.common.CodCommonRandom;
import com.tlkj.cod.common.CodCommonUUID;
import com.tlkj.cod.file.model.enums.CodFileNameEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * Desc 文件相关通用组件
 *
 * @author sourcod
 * @version 1.0
 * @className CodFileCommon
 * @date 2019/11/7 6:46 PM
 */
public class CodFileCommon {

    /**
     * 拼接文件路径
     *
     * @param fileName 文件名
     * @param prefix   文件目录
     * @return 拼接后文件名
     */
    public static String getFileHref(String fileName, String... prefix) {
        // 文件名不存在自动创建, 当前时间+随机数
        if (StringUtils.isEmpty(fileName)) {
            fileName = getFileName(CodFileNameEnum.YMDHMSS_UUID);
        }

        // 拼接文件夹
        if (prefix != null && prefix.length > 0) {
            fileName = StringUtils.join(prefix.clone(), File.separator) + File.separator + fileName;
        }

        /*
         * 去掉最前面 分隔符
         * 保证返回第一个字符不是 /
         */
        fileName = fileName.startsWith(File.separator) ? fileName.substring(File.separator.length(), fileName.length()) : fileName;
        return fileName;
    }

    /**
     * 获取文件名
     * @param codFileNameEnum 文件名规则
     * @return
     */
    private static String getFileName(CodFileNameEnum codFileNameEnum){
        return CodFileCommon.getFileName(codFileNameEnum, "-");
    }

    /**
     * 获取文件名
     * @param codFileNameEnum 文件名规则
     * @param separator       分隔符
     * @return
     */
    private static String getFileName(CodFileNameEnum codFileNameEnum, String separator){
        switch (codFileNameEnum){
            case YMD:

                return CodCommonDate.getDate("yyyyMMdd");
            case YMD_SPLIT:

                return CodCommonDate.getDate("yyyy/MM/dd/") +  CodCommonUUID.getUUID();
            case YMD_UUID:

                return CodCommonDate.getDate("yyyyMMdd") + separator +  CodCommonUUID.getUUID();
            case YMDHMS:

                return CodCommonDate.getDate("yyyyMMddHHmmss");

            case YMDHMS_RANDOM:

                return CodCommonDate.getDate("yyyyMMddHHmmss") + separator + CodCommonRandom.getRandomForSize(4);

            case YMDHMS_UUID:

                return CodCommonDate.getDate("yyyyMMddHHmmss") + separator + CodCommonUUID.getUUID();

            case YMDHMSS:

                return CodCommonDate.getDate("yyyyMMddHHmmssSSSS");
            case YMDHMSS_UUID:

                return CodCommonDate.getDate("yyyyMMddHHmmssSSSS") + separator + CodCommonUUID.getUUID();
            case YM_SPLIT:

                return CodCommonDate.getDate("yyyy/MM/") + CodCommonUUID.getUUID();

            default:
                return CodCommonDate.getDate("yyyyMMddHHmmssSSSS") + separator + CodCommonUUID.getUUID();
        }
    }

    public static void main(String[] args) {
        for (CodFileNameEnum codFileNameEnum : CodFileNameEnum.values()){
            String name = CodFileCommon.getFileName(codFileNameEnum);
            System.out.println(name);
        }

    }
}
