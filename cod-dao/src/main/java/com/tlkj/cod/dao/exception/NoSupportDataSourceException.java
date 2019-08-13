/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.dao.exception;

/**
 * 不支持数据源异常类
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className NoSupportDataSourceException
 * @date 2018/8/8 下午3:38
 */
public class NoSupportDataSourceException extends RuntimeException {

    public NoSupportDataSourceException() {
        super();
    }

    public NoSupportDataSourceException(String errMessage) {
        super(errMessage);
    }
}
