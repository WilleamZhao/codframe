/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.dao.exception;

/**
 * Desc 数据视图异常
 *
 * @author sourcod
 * @version 1.0
 * @className CodDataViewException
 * @date 2019/1/7 7:28 PM
 */
public class CodDataViewException extends RuntimeException {

    private static final long serialVersionUID = 3096318412518961159L;

    public CodDataViewException() {
        super();
    }

    public CodDataViewException(String errMessage) {
        super(errMessage);
    }
}
