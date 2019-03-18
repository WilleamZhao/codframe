/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.model.common;

import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.enums.SystemStatusCode;

import java.io.Serializable;

/**
 * Desc 内部传递model类
 *
 * @author sourcod
 * @version 1.0
 * @className SystemResponse
 * @date 2019/1/31 9:52 PM
 */
public class SystemResponse<T> implements Serializable {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 3742739163648269167L;

    /**
     * 详细状态码
     */
    private String code;

    /**
     * 状态描述
     */
    private String msg;

    /**
     * 状态名称
     */
    private String name;

    /**
     * 关联返回状态码
     */
    private StatusCode statusCode;

    /**
     * 返回数据
     */
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public SystemResponse(){

    }

    public SystemResponse(SystemStatusCode statusCode){
        this.code = statusCode.getCode();
        this.name = statusCode.getName();
        this.msg = statusCode.getDesc();
    }
}
