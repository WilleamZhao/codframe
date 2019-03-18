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

/**
 * Desc 通用返回
 *
 * @author sourcod
 * @version 1.0
 * @className GeneralResponse
 * @date 2018/9/12 下午12:48
 */
public class GeneralResponse {

    /**
     * 成功返回
     * @param object 返回信息
     * @return
     */
    protected Response success(Object object){
        return this.setResponseData(StatusCode.SUCCESS_CODE, object);
    }

    /**
     * 成功返回
     * @return
     */
    protected Response success(){
        return this.setResponseData(StatusCode.SUCCESS_CODE, null);
    }

    /**
     * 成功返回
     * @return
     */
    protected Response success(StatusCode statusCode){
        return this.setResponseData(statusCode, null);
    }

    /**
     * 返回失败
     * @param object 返回信息
     * @return
     */
    protected Response fail(Object object){
        return this.setResponseData(StatusCode.FAIL_CODE, object);
    }

    /**
     * 返回失败
     * @param object 返回信息
     * @param message 提示消息
     * @return
     */
    protected Response fail(Object object, String message){
        return this.setResponseData(StatusCode.FAIL_CODE, object, message);
    }

    /**
     * 返回失败
     * 状态码
     * @return
     */
    protected Response fail(StatusCode statusCode){
        return this.setResponseData(statusCode, null);
    }

    /**
     * 返回失败
     * 状态码
     * @param statusCode 状态码
     * @param object 返回信息
     * @return
     */
    protected Response fail(StatusCode statusCode, Object object){
        return this.setResponseData(statusCode, object);
    }

    /**
     * 返回失败
     * @return
     */
    protected Response fail(){
        return this.setResponseData(StatusCode.FAIL_CODE, null);
    }

    /**
     * 输出状态码
     * @param statusCode 状态码
     * @return
     */
    public Response output(StatusCode statusCode){
        return StatusCode.verifyStatusCode(statusCode) ? this.success() : this.fail();
    }

    /**
     * 输出状态码
     * @param object 返回信息
     * @return
     */
    protected Response output(Object object){
        return object != null ? this.success(object) : this.fail();
    }

    /**
     * 输出状态码
     * @param statusCode 状态码
     * @param object 返回信息
     * @return
     */
    protected Response output(StatusCode statusCode, Object object){
        return StatusCode.verifyStatusCode(statusCode) ? this.success(object) : this.fail();
    }

    /**
     * 设置ResponseData
     * @param statusCode 状态码
     * @param object 返回信息
     * @return
     */
    private Response setResponseData(StatusCode statusCode, Object object){
        return this.setResponseData(statusCode, object, statusCode.getStatusDesc());
    }

    /**
     * 设置ResponseData
     * @param statusCode 状态码
     * @param object 返回信息
     * @return
     */
    private Response setResponseData(StatusCode statusCode, Object object, String message){
        Response responseData = new Response();
        responseData.setCode(statusCode.getStatusCode());
        responseData.setName(statusCode.getStatusName());
        responseData.setMsg(message);
        if (object != null) {
            responseData.setData(object);
        }

        return responseData;
    }
}
