/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.common;

import org.apache.commons.lang3.StringUtils;

/**
 * Desc 文件工具类
 *
 * @author sourcod
 * @version 1.0
 * @className FileUtil
 * @date 2018/12/1 11:51 PM
 */
public class CodCommonFile {

    /**
     * 获取后缀
     * @param fileName 文件名
     * @return
     */
    public static String getSuffix(String fileName){
        return fileName.lastIndexOf(".") == -1 ? "" : StringUtils.isNotBlank(fileName) ? fileName.substring(fileName.lastIndexOf(".") + 1) : "";
    }

    /**
     * 获取前缀
     * @param fileName 文件名
     * @return
     */
    public static String getPrefix(String fileName){
        return StringUtils.isNotBlank(fileName) ? fileName.substring(0, fileName.lastIndexOf(".")) : "";
    }


}
