/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.model.enums;

/**
 * Desc 系统内部状态码
 *
 * @author sourcod
 * @version 1.0
 * @className SystemStatusCode
 * @date 2019/1/31 9:47 PM
 */
public enum SystemStatusCode {

    /**
     * 操作成功
     */
    SUCCESS_CODE("111111", "操作成功", "操作成功", StatusCode.SUCCESS_CODE),

    /**
     * 操作失败
     */
    FAIL_CODE("000000", "操作失败", "操作失败", StatusCode.FAIL_CODE),

    /**
     * SQL错误
     */
    SQL_ERROR_CODE("000000", "操作失败", "sql错误", StatusCode.FAIL_CODE),

    /**
     * 登录失败
     */
    LOGIN_FAIL_CODE("000001", "登录失败", "暂未登陆", StatusCode.LOGIN_FAIL_CODE);

    /**
     * 状态码
     */
    private String code;

    /**
     * 错误名称
     */
    private String name;

    /**
     * 错误描述
     */
    private String desc;

    /**
     * 关联返回状态码
     */
    private StatusCode statusCode;

    SystemStatusCode(String code, String name, String desc, StatusCode statusCode) {
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.statusCode = statusCode;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }
}
