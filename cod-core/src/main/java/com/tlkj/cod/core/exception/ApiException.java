/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.exception;

/**
 * desc 自定义Exception,主要是为了返回JSON信息
 *
 * @author sourcod
 * @create 2018-11-27
 **/
public class ApiException extends RuntimeException{

    public ApiException(String message) {
        super(message);
    }

}
