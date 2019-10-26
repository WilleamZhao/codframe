/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.dao.exception;

/**
 * Desc 数据模型转换异常
 *
 * @author sourcod
 * @version 1.0
 * @className CodDataModelConvertException
 * @date 2019/10/26 12:25 PM
 */
public class CodDataModelConvertException extends RuntimeException{
    private static final long serialVersionUID = 6235034284649177528L;

    public CodDataModelConvertException() {
        super();
    }

    public CodDataModelConvertException(String errMessage) {
        super(errMessage);
    }
}
